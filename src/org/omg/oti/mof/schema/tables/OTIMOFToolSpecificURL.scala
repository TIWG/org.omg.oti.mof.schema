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