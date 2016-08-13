/*
 *
 * License Terms
 *
 * Copyright (c) 2014-2016, California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * *   Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * *   Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the
 *    distribution.
 *
 * *   Neither the name of Caltech nor its operating division, the Jet
 *    Propulsion Laboratory, nor the names of its contributors may be
 *    used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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