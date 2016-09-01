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

package org.omg.oti.mof.schema.events

import play.api.libs.json._
import scala.StringContext

trait ChangeKind

object AddedChange extends ChangeKind

object DeletedChange extends ChangeKind

object ModifiedChange extends ChangeKind

object ChangeKind {

  implicit val formats
  : Format[ChangeKind]
  = new Format[ChangeKind] {

    def reads(json: JsValue): JsResult[ChangeKind] = json match {
      case JsString("Added") =>
        JsSuccess(AddedChange)

      case JsString("Deleted") =>
        JsSuccess(DeletedChange)

      case JsString("Modified") =>
        JsSuccess(ModifiedChange)

      case unknown =>
        JsError(s"ChangeKind: String value expected, got: $unknown")
    }

    def writes(v: ChangeKind): JsValue =
      v match {
        case AddedChange =>
          JsString("Added")

        case DeletedChange =>
          JsString("Deleted")

        case ModifiedChange =>
          JsString("Modified")
      }
  }

}