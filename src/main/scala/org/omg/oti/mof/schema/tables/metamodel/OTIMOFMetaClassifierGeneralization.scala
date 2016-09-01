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

package org.omg.oti.mof.schema.tables.metamodel

import org.omg.oti.mof.schema.common._
import play.api.libs.json._
import scala.Boolean

sealed trait OTIMOFMetaClassifierGeneralization {
  val resource: ResourceIRI
  val specific: EntityUUID
  val general: EntityUUID
}

case class OTIMOFMetaClassGeneralization
( override val resource: ResourceIRI,
  override val specific: EntityUUID,
  override val general: EntityUUID)
extends OTIMOFMetaClassifierGeneralization

object OTIMOFMetaClassGeneralization {

  implicit val formats
  : Format[OTIMOFMetaClassGeneralization]
  = Json.format[OTIMOFMetaClassGeneralization]

}

case class OTIMOFMetaAssociationGeneralization
( override val resource: ResourceIRI,
  override val specific: EntityUUID,
  override val general: EntityUUID,
  sourceRestriction: Boolean,
  targetRestriction: Boolean)
  extends OTIMOFMetaClassifierGeneralization

object OTIMOFMetaAssociationGeneralization {

  implicit val formats
  : Format[OTIMOFMetaAssociationGeneralization]
  = Json.format[OTIMOFMetaAssociationGeneralization]

}