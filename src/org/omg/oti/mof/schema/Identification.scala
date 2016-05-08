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
package org.omg.oti.mof.schema

import scalaz.Tag

/**
  * Identification is essential for the exchange of information about anything in MOF.
  *
  * In terms of OMG's MOF 2.5, MOF resources such as metamodels, profiles, libraries and models
  * are identified by their Package URI. According to the MOF 2.5 specification,
  * MOF elements in the extent of a MOF resource can be identified; presumably in terms of the values
  * of their metaclass porperties that have `{isID=true}`.
  *
  * Here, identification is defined differently for MOF resources (i.e., metamodels, profiles, libraries and models)
  * than it is for MOF elements in their MOF extent. In the former case, MOF resources are identified by their IRI;
  * in the latter case, MOF elements are identified by the value of their identifying UUID metaclass property attribute.
  *
  * Since IRIs and UUIDs are involved in the serialization of references to OTI MOF resources and entities respectively,
  * the OTI MOF schema defines a lightweight, compile-time partitioning of all OTI MOF IRIs and UUIDs to capture
  * the intent of the references to OTI MOF resources and entities of a particular kind.
  */
object Identification {

  /**
    * An Entity UUID is a global identifier for any kind of OTI MOF entity
    * that is part of the OTI MOF extent of an OTI MOF resource.
    *
    * In OTI MOF, an EntityUUID is an externally visible and uniquely identifying
    * characteristic of an OTI MOF Entity.
    *
    * In the OTI MOF serialization, OTI MOF entities are referenced by their UUID.
    * By defining a lightweight compile-time partitioning of the space
    * of all possible OTI MOF entity UUIDs according to the OTI MOF entity kind,
    * the OTI MOF schema captures important type-level information about
    * well-formed references to OTI MOF entities of a particular kind.
    */
  sealed trait EntityUUID

  // ==========

  sealed trait StructuredValueUUID extends EntityUUID
  val StructuredValueUUID = Tag.of[StructuredValueUUID]

  // ==========


  sealed trait FeatureEntityUUID extends EntityUUID

  sealed trait DatatypedAttributePropertyUUID extends FeatureEntityUUID
  val DatatypedAttributePropertyUUID = Tag.of[DatatypedAttributePropertyUUID]

  sealed trait LibraryEnumerationLiteralUUID extends FeatureEntityUUID
  val LibraryEnumerationLiteralUUID = Tag.of[LibraryEnumerationLiteralUUID]

  sealed trait AssociationEndUUID extends FeatureEntityUUID
  val AssociationEndUUID = Tag.of[AssociationEndUUID]

  sealed trait AssociationSourceEndUUID extends AssociationEndUUID
  val AssociationSourceEndUUID = Tag.of[AssociationSourceEndUUID]

  sealed trait AssociationTargetEndUUID extends AssociationEndUUID
  val AssociationTargetEndUUID = Tag.of[AssociationTargetEndUUID]

  // ==========

  sealed trait LibraryClassifierUUID extends EntityUUID

  sealed trait LibraryAtomicClassifierUUID extends LibraryClassifierUUID
  val LibraryAtomicClassifierUUID = Tag.of[LibraryAtomicClassifierUUID]

  sealed trait LibraryPrimitiveTypeUUID extends LibraryAtomicClassifierUUID
  val LibraryPrimitiveTypeUUID = Tag.of[LibraryPrimitiveTypeUUID]

  sealed trait LibraryEnumerationUUID extends LibraryAtomicClassifierUUID
  val LibraryEnumerationUUID = Tag.of[LibraryEnumerationUUID]

  sealed trait LibraryStructuredClassifierUUID extends LibraryClassifierUUID
  val LibraryStructuredClassifierUUID = Tag.of[LibraryStructuredClassifierUUID]

  // ==========

  sealed trait MetamodelClassifierUUID extends EntityUUID

  sealed trait MetaClassUUID extends MetamodelClassifierUUID
  val MetaClassUUID = Tag.of[MetaClassUUID]

  sealed trait MetaAssociationUUID extends MetamodelClassifierUUID
  val MetaAssociationUUID = Tag.of[MetaAssociationUUID]

  // ==========

  sealed trait StereotypeUUID extends EntityUUID
  val StereotypeUUID = Tag.of[StereotypeUUID]

  // ==========

  /**
    * A Model Element UUID is a global identify for an OTI MOF element
    * that is part of the OTI MOF extent of an OTI MOF Model resource.
    */
  sealed trait ModelElementUUID extends EntityUUID
  val ModelElementUUID = Tag.of[ModelElementUUID]


  /**
    * Every OTI MOF resource (i.e., (i.e., metamodel, profile, librarie or model)
    * is identified by the IRI of its persistent location (internationalized URI).
    *
    * In OTI MOF, a ResourceIRI is an externally visible and uniquely identifying
    * characteristic of an OTI MOF Resource.
    *
    * In the OTI MOF serialization, OTI MOF resources are referenced by their IRI.
    * By defining a lightweight compile-time partitioning of the space
    * of all possible OTI MOF resource IRIs according to the OTI MOF resource kind,
    * the OTI MOF schema captures important type-level information about
    * well-formed references to OTI MOF resources of a particular kind.
    */
  sealed trait ResourceIRI

  /**
    * The persistent location of an OTI MOF Metamodel resource
    */
  sealed trait MetamodelIRI extends ResourceIRI
  val MetamodelIRI = Tag.of[MetamodelIRI]

  /**
    * The persistent location of an OTI MOF Profile resource
    */
  sealed trait ProfileIRI extends ResourceIRI
  val ProfileIRI = Tag.of[ProfileIRI]

  /**
    * The persistent location of an OTI MOF Library resource
    */
  sealed trait LibraryIRI extends ResourceIRI
  val LibraryIRI = Tag.of[LibraryIRI]

  /**
    * The persistent location of an OTI MOF Model resource
    */
  sealed trait ModelIRI extends ResourceIRI
  val ModelIRI = Tag.of[ModelIRI]

}