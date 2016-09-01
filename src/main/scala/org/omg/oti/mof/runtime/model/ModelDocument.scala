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