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