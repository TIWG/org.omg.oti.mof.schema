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
  * @group id
  */
case class ResourceIRI(value: String)

/**
  * @group id
  */
object ResourceIRI {

  implicit val formats
  : Format[ResourceIRI]
  = new Format[ResourceIRI] {

    def reads(json: JsValue): JsResult[ResourceIRI] = json match {
      case JsString(v) => JsSuccess(ResourceIRI(v))
      case unknown => JsError(s"ResourceIRI: String value expected, got: $unknown")
    }

    def writes(iri: ResourceIRI): JsValue = JsString(iri.value)

  }

}