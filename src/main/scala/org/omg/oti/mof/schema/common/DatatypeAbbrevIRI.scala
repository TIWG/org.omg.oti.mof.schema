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