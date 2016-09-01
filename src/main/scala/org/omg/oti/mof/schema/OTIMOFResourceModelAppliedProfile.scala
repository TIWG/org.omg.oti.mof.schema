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

package org.omg.oti.mof.schema

import org.omg.oti.mof.schema.common.{EntityUUID,ResourceIRI}

import play.api.libs.json._

/**
  * OTIMOFResourceModelAppliedProfile corresponds to a UML ProfileApplication relationship from
  * the applying model UML Package of an OTI Model resource
  * to the applied UML Profile of an OTI Profile resource.
  *
  * @group model
  */
case class OTIMOFResourceModelAppliedProfile
( applyingResource: ResourceIRI,
  applyingPackage: EntityUUID,
  appliedResource: ResourceIRI,
  appliedProfile: EntityUUID)

/**
  * @group model
  */
object OTIMOFResourceModelAppliedProfile {

  implicit val formats
  : Format[OTIMOFResourceModelAppliedProfile]
  = Json.format[OTIMOFResourceModelAppliedProfile]

}