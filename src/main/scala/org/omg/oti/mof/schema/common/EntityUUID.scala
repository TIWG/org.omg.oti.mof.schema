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
  * @group id
  */
case class EntityUUID
(value: String)

/**
  * @group id
  */
object EntityUUID {

  implicit val ordering
  : Ordering[EntityUUID]
  = new Ordering[EntityUUID] {

    def compare(x: EntityUUID, y: EntityUUID)
    : Int
    = x.value.compareTo(y.value)

  }

  implicit val formats
  : Format[EntityUUID]
  = new Format[EntityUUID] {

    def reads(json: JsValue): JsResult[EntityUUID] = json match {
      case JsString(v) => JsSuccess(EntityUUID(v))
      case unknown => JsError(s"EntityUUID: String value expected, got: $unknown")
    }

    def writes(uuid: EntityUUID): JsValue = JsString(uuid.value)

  }

}