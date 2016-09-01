/*
 * Copyright 2016 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * License Terms
 */

package org.omg.oti.mof.runtime.metamodel

import org.omg.oti.mof._
import scala.collection._
import scala.reflect.ClassTag
import scala.{Int,Option,None,Some,StringContext,Unit}

case class MetaClass[T <: runtime.model.ModelElement : ClassTag]
(mc: schema.views.MetaClassView) {

  override def hashCode(): Int = mc.metaClass.uuid.hashCode()

  protected val general = mutable.HashSet[MetaClass[_ >: T <: runtime.model.ModelElement]]()
  protected var allGeneral: Option[immutable.Set[MetaClass[_ >: T <: runtime.model.ModelElement]]] = None

  def addGeneralMetaclass(g: MetaClass[_ >: T <: runtime.model.ModelElement]): Unit = {
    general += g
    if (!g.specific.contains(this))
      g.addSpecificMetaclass(this)
    ()
  }

  @scala.annotation.tailrec
  final protected def accumulateGeneralMetaclasses
  (acc: immutable.Set[MetaClass[_ >: T <: runtime.model.ModelElement]],
   queue: immutable.Set[MetaClass[_ >: T <: runtime.model.ModelElement]],
   visited: immutable.Set[MetaClass[_ >: T <: runtime.model.ModelElement]])
  : immutable.Set[MetaClass[_ >: T <: runtime.model.ModelElement]]
  = if (queue.isEmpty)
    acc
  else {
    val (qh, qt) = (queue.head, queue.tail)
    if (visited.contains(qh))
      accumulateGeneralMetaclasses(acc,qt,visited)
    else
      accumulateGeneralMetaclasses(acc + qh, qh.general.to[immutable.Set] ++ qt, visited + qh)
  }

  final def allGeneralMetaclasses()
  : immutable.Set[MetaClass[_ >: T <: runtime.model.ModelElement]]
  = allGeneral match {
    case Some(all) =>
      all

    case None =>
      val all = accumulateGeneralMetaclasses(
        immutable.Set.empty,
        immutable.Set[MetaClass[_ >: T <: runtime.model.ModelElement]](this),
        immutable.Set.empty)
      allGeneral = Some(all)
      all
  }

  protected val specific = mutable.HashSet[MetaClass[_ <: T]]()
  protected var allSpecific: Option[immutable.Set[MetaClass[_ <: T]]] = None

  def addSpecificMetaclass(s: MetaClass[_ <: T]): Unit = {
    specific += s
    if (!s.general.contains(this))
      s.addGeneralMetaclass(this)
    ()
  }

  @scala.annotation.tailrec
  final protected def accumulateSpecificMetaclasses
  (acc: immutable.Set[MetaClass[_ <: T]],
   queue: immutable.Set[MetaClass[_ <: T]],
   visited: immutable.Set[MetaClass[_ <: T]])
  : immutable.Set[MetaClass[_ <: T]]
  = if (queue.isEmpty)
    acc
  else {
    val (qh, qt) = (queue.head, queue.tail)
    if (visited.contains(qh))
      accumulateSpecificMetaclasses(acc,qt,visited)
    else
      accumulateSpecificMetaclasses(acc + qh, qh.specific.to[immutable.Set] ++ qt, visited + qh)
  }

  final def allSpecificMetaclasses()
  : immutable.Set[MetaClass[_ <: T]]
  = allSpecific match {
    case Some(all) =>
      all

    case None =>
      val all = accumulateSpecificMetaclasses(
        immutable.Set.empty,
        immutable.Set[MetaClass[_ <: T]](this),
        immutable.Set.empty)
      allSpecific = Some(all)
      all
  }

  protected val instances = mutable.HashMap[schema.common.EntityUUID, T]()

  def lookupInstance(uuid: schema.common.EntityUUID)
  : Option[T]
  = {
    val i: Option[T] = instances.get(uuid)
    if (i.isDefined)
      i
    else
      allSpecificMetaclasses().foldLeft[Option[T]](None) { case (acc, mc) =>
        if (acc.isDefined)
          acc
        else
          mc.instances.get(uuid)

      }
  }

  def checkInstance(e: runtime.model.ModelElement)
  : Option[T]
  = {
    val i = instances.get(e.dataModelElement.uuid)
    if (i.isDefined)
      i
    else
      allSpecificMetaclasses().foldLeft[Option[T]](Option.empty) { case (acc, mc) =>
        if (acc.isDefined)
          acc
        else
          mc.instances.get(e.dataModelElement.uuid)
      }
  }

  def addInstance(t: T): T
  = instances
      .put(t.dataModelElement.uuid, t)
      .fold(t) { conflict =>
        throw new java.lang.IllegalArgumentException(
          s"Metaclass(${mc.metaClass.name.value}).addInstance: "+
          s"conflict with existing instance uuid: ${conflict.dataModelElement.uuid}")
      }

  def instanceCount: Int = instances.size
}