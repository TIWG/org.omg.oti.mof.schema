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
import scala.reflect.ClassTag
import scala.{Int,Option,None,Some,StringContext,Unit}
import scala.Predef.String

case class MetaAssociation[S <: runtime.model.ModelElement, US >: S, T <: runtime.model.ModelElement, UT >: T, L <: runtime.model.ModelLink[S, T]]
( aInfo: schema.views.AssociationInfo,
  sourceMC: MetaClass[S],
  targetMC: MetaClass[T] )(
   implicit val sourceType: ClassTag[S],
   implicit val targetType: ClassTag[T],
   implicit val linkType: ClassTag[L]
) {

  override def hashCode(): Int = aInfo.uuid.value.hashCode

  protected var association: Option[Association[S, US, T, UT, L]] = None

  def linkCount: Int
  = association match {
    case Some(a) =>
      a.linkCount
    case None =>
      0
  }

  def registerAssociation(a: Association[S, US, T, UT, L])
  : Unit
  = {
    if (association.nonEmpty)
      throw new java.lang.IllegalArgumentException(s"MetaAssociation($aInfo) already has a registered association!")
    else
      association = Some(a)
    ()
  }

  def describe
  : String
  = association match {
    case None =>
      s"MetaAssociation(${aInfo.name.value}): No Association"
    case Some(a) =>
      s"MetaAssociation(${aInfo.name.value}): ${a.describe}"
  }

}