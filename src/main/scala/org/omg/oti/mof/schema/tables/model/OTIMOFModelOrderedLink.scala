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

package org.omg.oti.mof.schema.tables.model

import org.omg.oti.mof.schema.common._
import play.api.libs.json._
import scala.Int

case class OTIMOFModelOrderedLink
(override val resource: ResourceIRI,
 override val sourceElement: EntityUUID,
 override val targetElement: EntityUUID,
 override val metaAssociation: EntityUUID,
 index: Int)
extends OTIMOFModelLink

object OTIMOFModelOrderedLink {

  implicit val formats
  : Format[OTIMOFModelOrderedLink]
  = Json.format[OTIMOFModelOrderedLink]

}