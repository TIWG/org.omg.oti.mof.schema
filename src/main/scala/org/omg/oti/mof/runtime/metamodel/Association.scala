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
import scala.{Int,Some,StringContext,Unit}
import scala.Predef.String

trait Association[S <: runtime.model.ModelElement, US >: S, T <: runtime.model.ModelElement, UT >: T, L <: runtime.model.ModelLink[S, T]] {

  val metaAssociation: runtime.metamodel.MetaAssociation[S, US, T, UT, L]

  metaAssociation.registerAssociation(this)

  protected val links = mutable.ListBuffer[L]()

  def linkCount: Int = links.size

  def addLink(link: L)
  : Unit
  = {
    links += link
    ()
  }

  def createOrderedLink
  ( o: schema.tables.model.OTIMOFModelOrderedLink,
    s: runtime.model.ModelElement,
    t: runtime.model.ModelElement )
  : runtime.model.ModelOrderedLink[S, T]
  = (metaAssociation.sourceMC.checkInstance(s), metaAssociation.targetMC.checkInstance(t)) match {
    case (Some(ms), Some(mt)) =>
      val l = runtime.model.ModelOrderedLink[S, T](o, ms, mt)
      addLink(l.asInstanceOf[L])
      l
    case (se, te) =>
      throw new java.lang.IllegalArgumentException(
        s"Association(${metaAssociation.aInfo.name.value}).createOrderedLink: invalid source/target types:" +
          s"\nsource: ${s.dataModelElement} (${se.isDefined})\ntarget: ${t.dataModelElement} (${te.isDefined})")
  }

  def createUnorderedLink
  ( u: schema.tables.model.OTIMOFModelUnorderedLink,
    s: runtime.model.ModelElement,
    t: runtime.model.ModelElement )
  : runtime.model.ModelUnorderedLink[S, T]
  = (metaAssociation.sourceMC.checkInstance(s), metaAssociation.targetMC.checkInstance(t)) match {
    case (Some(ms), Some(mt)) =>
      val l = runtime.model.ModelUnorderedLink[S, T](u, ms, mt)
      addLink(l.asInstanceOf[L])
      l
    case _ =>
      throw new java.lang.IllegalArgumentException(
        s"Association(${metaAssociation.aInfo.name.value}).createUnorderedLink: invalid source/target types:" +
          s"\nsource: ${s.dataModelElement}\ntarget: ${t.dataModelElement}")
  }

  def describe: String = s"Association(${metaAssociation.aInfo.name.value}){${links.size} links)"
}