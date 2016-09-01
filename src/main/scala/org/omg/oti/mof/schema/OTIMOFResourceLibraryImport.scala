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

import play.api.libs.json._
import org.omg.oti.mof.schema.common.ResourceIRI

/**
  * OTIMOFResourceLibraryImport corresponds to a UML PackageImport relationship to
  * the imported UML Package of an OTI MOF Library resource.
  *
  * @param importingResource The [[org.omg.oti.mof.schema.common.ResourceIRI]] of the importing resource
  * @param importedLibrary The [[org.omg.oti.mof.schema.common.ResourceIRI]] of the imported library
  * @group resources
  */
case class OTIMOFResourceLibraryImport
( importingResource: ResourceIRI,
  importedLibrary: ResourceIRI )

/**
  * @group resources
  */
object OTIMOFResourceLibraryImport {

  implicit val formats
  : Format[OTIMOFResourceLibraryImport]
  = Json.format[OTIMOFResourceLibraryImport]

}