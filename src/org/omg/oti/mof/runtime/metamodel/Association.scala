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