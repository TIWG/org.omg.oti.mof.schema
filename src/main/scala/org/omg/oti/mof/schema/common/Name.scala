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

import scala.{Int,Ordering,StringContext}
import scala.Predef.String

/**
  * NAME is the partition of Strings that represent the name of an OTI MOF entity.
  *
  * In OTI MOF, the name of an OTI MOF entity is only used for constructing the OTI IRI of that entity.
  * That is, in OTI MOF, names are considered to be an internal, private characteristic of an OTI MOF entity.
  * Therefore, it is unnecessary to further partition the space of all OTI MOF entity names according
  * to the OTI MOF entity kind.
  *
  * In contrast to a NAME, a UUID is considered to be the externally visible and uniquely
  * identifying characteristic of OTI MOF entities and the basis for referencing them.
  * Therefore, it is necessary to further partition the space of all OTI MOF entity UUIDs according
  * to the OTI MOF entity kind to capture the intended type of OTI MOF entity reference.
  * @group id
  */
case class Name
(value: String)

/**
  * @group id
  */
object Name {

  implicit val ordering
  : Ordering[Name]
  = new Ordering[Name] {

    def compare(x: Name, y: Name)
    : Int
    = x.value.compareTo(y.value)

  }

  implicit val formats
  : Format[Name]
  = new Format[Name] {

    def reads(json: JsValue): JsResult[Name] = json match {
      case JsString(v) => JsSuccess(Name(v))
      case unknown => JsError(s"Name: String value expected, got: $unknown")
    }

    def writes(name: Name): JsValue = JsString(name.value)

  }

}