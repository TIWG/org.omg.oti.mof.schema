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

import play.json.extra._
import play.api.libs.json._

import org.omg.oti.mof.schema._
import org.omg.oti.mof.schema.Identification._
import org.omg.oti.mof.schema.Common._
import scala.Predef.String
import scalaz.@@

/**
  * Every DatatypeClassifier has a name and an uuid primary identifier
  */
sealed trait DatatypeClassifier {
  val uuid: String @@ (_ <: LibraryClassifierUUID)
  val name: String @@ Name
}

sealed trait AtomicDatatype

case class PrimitiveDataType
( override val uuid: String @@ LibraryPrimitiveTypeUUID,
  override val name: String @@ Name,
  datatypeMapDefinition: String @@ DatatypeAbbrevIRI )
  extends DatatypeClassifier
  with AtomicDatatype

case class EnumerationDataType
( override val uuid: String @@ LibraryEnumerationUUID,
  override val name: String @@ Name )
  extends DatatypeClassifier
  with AtomicDatatype

case class StructuredDataType
(override val uuid: String @@ LibraryStructuredClassifierUUID,
 override val name: String @@ Name )
  extends DatatypeClassifier

object AtomicDataType {

  implicit val writes
  : Writes[AtomicDatatype]
  = Variants.writes[AtomicDatatype]((__ \ "type").write[String])

  implicit val reads
  : Reads[AtomicDatatype]
  = Variants.reads[AtomicDatatype]((__ \ "type").read[String])

  implicit val formats
  : Format[AtomicDatatype]
  = Variants.format[AtomicDatatype]((__ \ "type").format[String])

}

object DatatypeClassifier {

  implicit val writes
  : Writes[DatatypeClassifier]
  = Variants.writes[DatatypeClassifier]((__ \ "type").write[String])

  implicit val reads
  : Reads[DatatypeClassifier]
  = Variants.reads[DatatypeClassifier]((__ \ "type").read[String])

  implicit val formats
  : Format[DatatypeClassifier]
  = Variants.format[DatatypeClassifier]((__ \ "type").format[String])
}