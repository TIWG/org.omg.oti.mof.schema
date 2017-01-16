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

package org.omg.oti.mof.schema.features

import play.api.libs.json._
import org.omg.oti.mof.schema.common._

/**
  * The lower bound characteristic of a [[Feature]]
  *
  * @param feature A [[Feature]]
  * @param lower A non-negative integer lower bound
  * @group Base
  */
case class FeatureLowerBound
( override val resource: ResourceIRI,
  override val feature: EntityUUID,
  lower: NonNegativeInt )
extends FeatureInfo

/**
  * @group Base
  */
object FeatureLowerBound {

  implicit val formats
  : Format[FeatureLowerBound]
  = Json.format[FeatureLowerBound]
}