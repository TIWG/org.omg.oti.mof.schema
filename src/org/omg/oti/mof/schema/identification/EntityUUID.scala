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
package org.omg.oti.mof.schema.identification

/**
  * A UUID is a global identifier for any kind of OTI MOF entity
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

/**
  * A Metamodel Entity UUID is a global identify for any kind of OTI MOF entity
  * that is part of the OTI MOF extent of an OTI MOF Metamodel resource.
  */
sealed trait MetamodelEntityUUID extends EntityUUID

trait MetaclassUUID extends MetamodelEntityUUID
trait MetaAttributeUUID extends MetamodelEntityUUID
trait MetaAssociationUUID extends MetamodelEntityUUID
trait MetaAssociationEnd1UUID extends MetamodelEntityUUID
trait MetaAssociationEnd2UUID extends MetamodelEntityUUID

/**
  * A Library Entity UUID is a global identify for any kind of OTI MOF entity
  * that is part of the OTI MOF extent of an OTI MOF Library resource.
  */
sealed trait LibraryEntityUUID extends EntityUUID

sealed trait LibraryDatatypeUUID extends LibraryEntityUUID
sealed trait LibraryAtomicDatatypeUUID extends LibraryDatatypeUUID
trait LibraryPrimitiveTypeUUID extends LibraryAtomicDatatypeUUID
trait LibraryEnumerationUUID extends LibraryAtomicDatatypeUUID
trait LibraryStructuredDatatypeUUID extends LibraryDatatypeUUID

sealed trait LibraryFeatureUUID extends LibraryEntityUUID
trait LibraryEnumerationLiteralUUID extends LibraryFeatureUUID
trait LibraryStructuredAttributeUUID extends LibraryFeatureUUID

/**
  * A Profile Entity UUID is a global identify for any kind of OTI MOF entity
  * that is part of the OTI MOF extent of an OTI MOF Profile resource.
  */
sealed trait ProfileEntityUUID extends EntityUUID

trait StereotypeUUID extends ProfileEntityUUID
trait StereotypeAttributeUUID extends ProfileEntityUUID
trait StereotypeAssociationUUID extends ProfileEntityUUID
trait StereotypeAssociationEnd1UUID extends ProfileEntityUUID
trait StereotypeAssociationEnd2UUID extends ProfileEntityUUID

/**
  * A Model Entity UUID is a global identify for any kind of OTI MOF entity
  * that is part of the OTI MOF extent of an OTI MOF Model resource.
  */
sealed trait ModelEntityUUID extends EntityUUID

trait ModelElementUUID extends ModelEntityUUID