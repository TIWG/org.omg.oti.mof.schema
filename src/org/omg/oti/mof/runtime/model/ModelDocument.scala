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
package org.omg.oti.mof.runtime.model

import org.omg.oti.mof._
import scala.collection._
import scala.{Int,Option,Unit}

case class ModelDocument
( resource: schema.common.ResourceIRI,
  store: runtime.ResourceStore ) {

  protected val elements = mutable.HashMap[schema.common.EntityUUID, ModelElement]()

  def addElements(es: Iterable[(schema.common.EntityUUID, ModelElement)])
  : Unit
  = {
    elements ++= es
    store.addElements(es)
    ()
  }

  def lookupElement(uuid: schema.common.EntityUUID)
  : Option[ModelElement]
  = elements.get(uuid)

  def elementCount: Int = elements.size

  protected val orderedLinks = mutable.ListBuffer[runtime.ResourceStore.AnyModelOrderedLink]()

  def addOrderedLinks(os: Iterable[runtime.ResourceStore.AnyModelOrderedLink])
  : Unit
  = {
    orderedLinks ++= os
    store.addOrderedLinks(os)
    ()
  }

  def orderedLinkCount: Int = orderedLinks.size

  protected val unorderedLinks = mutable.ListBuffer[runtime.ResourceStore.AnyModelUnorderedLink]()

  def addUnorderedLinks(os: Iterable[runtime.ResourceStore.AnyModelUnorderedLink])
  : Unit
  = {
    unorderedLinks ++= os
    store.addUnorderedLinks(os)
    ()
  }

  def unorderedLinkCount: Int = unorderedLinks.size

}