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

import org.omg.oti.mof.schema.common._
import play.api.libs.json._
import scala.Predef.String

/**
  * Information about a tool-specific identifier for a MOF entity that has a primary key (EntityUUID)
  * (e.g., metaclass, metaassociation, model element, profile stereotype, library datatype/attribute)
  * Note that this is not applicable to MOF relation tables (e.g. ModelOrderedLink, AppliedStereotype)
  *
  * A given entity (as globally identified via its EntityUUID primary key)
  * may have zero or more tool-specific URLs.
  * That is, for a given uuid, there can be multiple pairs of (toolVendorID, toolElementURL).
  * This table provides information about a single pair (toolVendorID, toolElementURL) for a given uuid.
  *
  * @param resource The resource containing a representation of an entity.
  * @param uuid The global UUID primary key identifying an entity
  * @param toolVendorID The vendor ID of the tool that has a tool-specific representation of this entity.
  * @param toolElementURL The tool-specific URL of this entity's representation
  */
case class OTIMOFToolSpecificURL
( resource: ResourceIRI,
  uuid: EntityUUID,
  toolVendorID: String,
  toolElementURL: String)

object OTIMOFToolSpecificURL {

  implicit val formats
  : Format[OTIMOFToolSpecificURL]
  = Json.format[OTIMOFToolSpecificURL]

}