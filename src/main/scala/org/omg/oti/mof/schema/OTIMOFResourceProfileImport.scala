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

import org.omg.oti.mof.schema.common.ResourceIRI

import play.api.libs.json._

/**
  * OTIMOFResourceProfileImport corresponds to a UML PackageImport relationship from
  * an importing UML Profile of an OTI Profile resource
  * to the imported UML Profile of an OTI Profile resource.
  *
  * @param importingProfile The [[org.omg.oti.mof.schema.common.ResourceIRI]] of the importing OTI MOF Profile
  * @param importedProfile The [[org.omg.oti.mof.schema.common.ResourceIRI]] of the imported OTI MOF Profile
  * @group profile
  */
case class OTIMOFResourceProfileImport
( importingProfile: ResourceIRI,
  importedProfile: ResourceIRI )

/**
  * @group profile
  */
object OTIMOFResourceProfileImport {

  implicit val formats
  : Format[OTIMOFResourceProfileImport]
  = Json.format[OTIMOFResourceProfileImport]

}