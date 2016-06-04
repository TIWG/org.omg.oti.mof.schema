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
package org.omg.oti.mof.schema.common

import play.api.libs.json._
import scala.StringContext
import scala.Predef.String

/**
  * The partition of Strings that represent the abbreviated form of the
  * IRI of an atomic datatype definition in the normative OWL2-DL datatype map.
  *
  * An abbreviated IRI has the form 'pn:rc' where 'pn' is the prefix IRI and 'rc' is the remaining
  * characters in the IRI after 'pn'.
  *
  * @see [[http://www.w3.org/TR/owl2-syntax/#Datatype_Maps]]
  * @see [[http://www.w3.org/TR/owl2-syntax/#IRIs]]
  * @group id
  */
case class DatatypeAbbrevIRI
(value: String)

/**
  * @group id
  */
object DatatypeAbbrevIRI {

  implicit val formats
  : Format[DatatypeAbbrevIRI]
  = new Format[DatatypeAbbrevIRI] {

    def reads(json: JsValue): JsResult[DatatypeAbbrevIRI] = json match {
      case JsString(v) => JsSuccess(DatatypeAbbrevIRI(v))
      case unknown => JsError(s"DatatypeAbbrevIRI: String value expected, got: $unknown")
    }

    def writes(abiri: DatatypeAbbrevIRI): JsValue = JsString(abiri.value)

  }
}