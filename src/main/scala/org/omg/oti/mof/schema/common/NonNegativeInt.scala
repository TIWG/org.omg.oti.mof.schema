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
import scala.{Int,StringContext}

/**
  * Represents the subset of positive Int values including 0
  * @group value
  */
case class NonNegativeInt
(value: Int)

/**
  * @group value
  */
object NonNegativeInt {

  implicit val formats
  : Format[NonNegativeInt]
  = new Format[NonNegativeInt] {

    def reads(json: JsValue): JsResult[NonNegativeInt] = json match {
      case JsNumber(v) => JsSuccess(NonNegativeInt(v.intValue()))
      case unknown => JsError(s"NonNegativeInt: Number value expected, got: $unknown")
    }

    def writes(n: NonNegativeInt): JsValue = JsNumber(n.value)

  }
}