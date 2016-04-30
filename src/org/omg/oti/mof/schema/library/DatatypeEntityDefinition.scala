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
package org.omg.oti.mof.schema.library

import org.omg.oti.mof.schema.identification._
import org.omg.oti.mof.schema.Common._
import scala.Predef.String
import scalaz.@@

sealed trait DatatypeEntityDefinition {
  val uuid: String @@ (_ <: LibraryEntityUUID)
  val name: String @@ NAME
}

sealed trait DatatypeDefinition extends DatatypeEntityDefinition {
  override val uuid: String @@ (_ <: LibraryDatatypeUUID)
}

sealed trait AtomicDatatype extends DatatypeDefinition

case class PrimitiveDataType
( override val uuid: String @@ LibraryPrimitiveTypeUUID,
  override val name: String @@ NAME,
  datatypeMapDefinition: String @@ DATATYPE_ABBREVIATED_IRI )
  extends AtomicDatatype

case class EnumerationDataType
( override val uuid: String @@ LibraryEnumerationUUID,
  override val name: String @@ NAME )
  extends AtomicDatatype

case class StructuredDatatype
( override val uuid: String @@ LibraryStructuredDatatypeUUID,
  override val name: String @@ NAME )
  extends DatatypeDefinition

sealed trait DatatypeFeatureDefinition extends DatatypeEntityDefinition

case class EnumerationLiteral
( override val uuid: String @@ LibraryEnumerationLiteralUUID,
  override val name: String @@ NAME )
  extends DatatypeFeatureDefinition

case class StructuredDatatypeAttribute
( override val uuid: String @@ LibraryStructuredAttributeUUID,
  override val name: String @@ NAME )
  extends DatatypeFeatureDefinition