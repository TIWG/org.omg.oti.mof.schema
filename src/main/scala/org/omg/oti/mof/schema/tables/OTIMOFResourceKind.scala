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

package org.omg.oti.mof.schema.tables

import play.api.libs.json._
import scala.{Option, None, Some, StringContext}
import scala.Predef.String

sealed trait OTIMOFResourceKind

object OTIMOFResourceLibraryKind extends OTIMOFResourceKind

object OTIMOFResourceMetamodelKind extends OTIMOFResourceKind

object OTIMOFResourceProfileKind extends OTIMOFResourceKind

object OTIMOFResourceModelKind extends OTIMOFResourceKind

object OTIMOFResourceKind {

  def asString(k: OTIMOFResourceKind)
  : String
  = k match {
    case OTIMOFResourceLibraryKind =>
      "Library"

    case OTIMOFResourceMetamodelKind =>
      "Metamodel"

    case OTIMOFResourceProfileKind =>
      "Profile"

    case OTIMOFResourceModelKind =>
      "Model"
  }

  def fromString(k: String)
  : Option[OTIMOFResourceKind]
  = k match {
    case "Library" =>
      Some(OTIMOFResourceLibraryKind)

    case "Metamodel" =>
      Some(OTIMOFResourceMetamodelKind)

    case "Profile" =>
      Some(OTIMOFResourceProfileKind)

    case "Model" =>
      Some(OTIMOFResourceModelKind)

    case _ =>
      None
  }

  def read(s: String)
  : OTIMOFResourceKind
  = fromString(s) match {
    case Some(k) =>
      k
    case None =>
      throw new java.lang.IllegalArgumentException(s"OTIMOFResourceKind: unrecognized value '$s'")
  }

  implicit val formats
  : Format[OTIMOFResourceKind]
  = new Format[OTIMOFResourceKind] {

    def reads(json: JsValue): JsResult[OTIMOFResourceKind] = json match {
      case JsString(v) =>
        fromString(v) match {
          case Some(k) =>
            JsSuccess(k)

          case None =>
            JsError(s"OTIMOFResourceKind: String value expected, got: $v")
        }

      case unknown =>
        JsError(s"OTIMOFResourceKind: String value expected, got: $unknown")
    }

    def writes(v: OTIMOFResourceKind): JsValue =
       JsString(asString(v))
  }

}