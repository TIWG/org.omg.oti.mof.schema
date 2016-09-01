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
  * OTIMOFResourceInstantiatedMetamodel does not have any explicitly
  * defined abstract syntax in OMG modeling specifications.
  * Informally, this relationship is usually shown in diagrams
  * as if it were a dependency stereotyped 'instanceOf'.
  *
  * @param instantiatingModel The [[org.omg.oti.mof.schema.common.ResourceIRI]] of the instantiating OTI MOF Model
  * @param instantiatedMetamodel The [[org.omg.oti.mof.schema.common.ResourceIRI]] of the instantiated OTI MOF Metamodel
  * @group model
  */
case class OTIMOFResourceInstantiatedMetamodel
( instantiatingModel: ResourceIRI,
  instantiatedMetamodel: ResourceIRI )

/**
  * @group model
  */
object OTIMOFResourceInstantiatedMetamodel {

  implicit val formats
  : Format[OTIMOFResourceInstantiatedMetamodel]
  = Json.format[OTIMOFResourceInstantiatedMetamodel]

}