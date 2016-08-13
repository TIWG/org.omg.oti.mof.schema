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
package org.omg.oti.mof.runtime

import org.omg.oti._
import scala.collection._
import scala.{Option,StringContext,Unit}
import scala.Predef.String

object ResourceStore {

  type AnyModelLink =
  mof.runtime.model.ModelLink[_ <: mof.runtime.model.ModelElement, _ <: mof.runtime.model.ModelElement]

  type AnyModelOrderedLink =
  mof.runtime.model.ModelOrderedLink[_ <: mof.runtime.model.ModelElement, _ <: mof.runtime.model.ModelElement]

  type AnyModelUnorderedLink =
  mof.runtime.model.ModelUnorderedLink[_ <: mof.runtime.model.ModelElement, _ <: mof.runtime.model.ModelElement]

  type AnyAssociation =
  mof.runtime.metamodel.Association[_ <: mof.runtime.model.ModelElement, _, _ <: mof.runtime.model.ModelElement, _, _ <: AnyModelLink]

  type AnySourceOrderedReferenceAssociation =
  mof.runtime.metamodel.SourceOrderedReferenceAssociation[_ <: mof.runtime.model.ModelElement, _, _ <: mof.runtime.model.ModelElement, _]

  type AnyTargetOrderedReferenceAssociation =
  mof.runtime.metamodel.TargetOrderedReferenceAssociation[_ <: mof.runtime.model.ModelElement, _, _ <: mof.runtime.model.ModelElement, _]

  type AnyTargetOrderedCompositeAssociation =
  mof.runtime.metamodel.TargetOrderedCompositeAssociation[_ <: mof.runtime.model.ModelElement, _, _ <: mof.runtime.model.ModelElement, _]

  type AnyUnorderedReferenceAssociation =
  mof.runtime.metamodel.UnorderedReferenceAssociation[_ <: mof.runtime.model.ModelElement, _, _ <: mof.runtime.model.ModelElement, _]

  type AnyUnorderedCompositeAssociation =
  mof.runtime.metamodel.UnorderedCompositeAssociation[_ <: mof.runtime.model.ModelElement, _, _ <: mof.runtime.model.ModelElement, _]

  type AnyMetaAssociation =
  mof.runtime.metamodel.MetaAssociation[_ <: mof.runtime.model.ModelElement, _, _ <: mof.runtime.model.ModelElement, _, _ <: AnyModelLink]


}
trait ResourceStore {

  import ResourceStore._

  protected val elements = mutable.HashMap[mof.schema.common.EntityUUID, mof.runtime.model.ModelElement]()

  protected[mof] def addElements(es: Iterable[(mof.schema.common.EntityUUID, mof.runtime.model.ModelElement)])
  : Unit
  = {
    elements ++= es
    ()
  }

  protected val orderedLinks = mutable.ListBuffer[AnyModelOrderedLink]()

  def addOrderedLinks(os: Iterable[AnyModelOrderedLink])
  : Unit
  = {
    orderedLinks ++= os
    ()
  }

  protected val unorderedLinks = mutable.ListBuffer[AnyModelUnorderedLink]()

  def addUnorderedLinks(os: Iterable[AnyModelUnorderedLink])
  : Unit
  = {
    unorderedLinks ++= os
    ()
  }

  protected val documents = mutable.HashMap[mof.schema.common.ResourceIRI, mof.runtime.model.ModelDocument]()

  def createModelDocument(resourceIRI: mof.schema.common.ResourceIRI)
  : model.ModelDocument
  = {
    val d = model.ModelDocument(resourceIRI, this)
    documents
      .put(resourceIRI, d)
      .fold(d) { conflict =>
        throw new java.lang.IllegalArgumentException(
          s"createModelDocument: conflict with an existing ModelDocument with resourceIRI=$resourceIRI")
      }
  }

  def lookupModelDocument(resourceIRI: mof.schema.common.ResourceIRI)
  : Option[model.ModelDocument]
  = documents.get(resourceIRI)

  def getModelDocument(resourceIRI: mof.schema.common.ResourceIRI)
  : model.ModelDocument
  = lookupModelDocument(resourceIRI).fold{
    throw new java.lang.IllegalArgumentException(s"getModelDocument: resourceIRI="+resourceIRI)
  } { scala.Predef.identity }

  def lookupModelElement(uuid: mof.schema.common.EntityUUID)
  : Option[model.ModelElement]
  = elements.get(uuid)

  val orderedAtomicAttributes = mutable.HashMap[String, mof.runtime.model.OrderedAtomicAttribute[_]]()
  val unorderedAtomicAttributes = mutable.HashMap[String, mof.runtime.model.UnorderedAtomicAttribute[_]]()
  val unorderedEnumerationAttributes = mutable.HashMap[String, mof.runtime.model.UnorderedEnumerationAttribute[_]]()

  val allAssociations = mutable.HashMap[String, AnyAssociation]()
  val orderedAssociations = mutable.HashMap[String, AnyAssociation]()
  val unorderedAssociations = mutable.HashMap[String, AnyAssociation]()
  val compositeAssociations = mutable.HashMap[String, AnyAssociation]()
  val referenceAssociations = mutable.HashMap[String, AnyAssociation]()

  val sourceOrderedReferenceAssociations
  = mof.util.MirroringHashMap[String,AnySourceOrderedReferenceAssociation,AnyAssociation](allAssociations,orderedAssociations,referenceAssociations)
  val targetOrderedReferenceAssociations
  = mof.util.MirroringHashMap[String,AnyTargetOrderedReferenceAssociation,AnyAssociation](allAssociations,orderedAssociations,referenceAssociations)
  val targetOrderedCompositeAssociations
  = mof.util.MirroringHashMap[String,AnyTargetOrderedCompositeAssociation,AnyAssociation](allAssociations,orderedAssociations,compositeAssociations)

  val unorderedReferenceAssociations
  = mof.util.MirroringHashMap[String,AnyUnorderedReferenceAssociation,AnyAssociation](allAssociations,unorderedAssociations,referenceAssociations)
  val unorderedCompositeAssociations
  = mof.util.MirroringHashMap[String,AnyUnorderedCompositeAssociation,AnyAssociation](allAssociations,unorderedAssociations,compositeAssociations)

  val allMetaAssociations
  = mutable.HashMap[String, AnyMetaAssociation]()

}