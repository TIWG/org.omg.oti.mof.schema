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
import org.omg.oti.mof.schema.common.{EntityUUID,ResourceIRI}

/**
  * A relation specifying the [[org.omg.oti.mof.schema.tables.library.OTIMOFEnumerationDataType]] or
  * [[org.omg.oti.mof.schema.tables.library.OTIMOFPrimitiveDataType]] or
  * [[org.omg.oti.mof.schema.tables.library.OTIMOFStructuredDataType]] typing
  * a [[DataTypedAttributeProperty]]
  *
  * @param attribute A [[DataTypedAttributeProperty]]
  * @param `type` A [[org.omg.oti.mof.schema.tables.library.OTIMOFEnumerationDataType]] or
  *               [[org.omg.oti.mof.schema.tables.library.OTIMOFPrimitiveDataType]] or
  *               [[org.omg.oti.mof.schema.tables.library.OTIMOFStructuredDataType]]
  * @group Attribute
  */
case class AttributeProperty2DataType
( resource: ResourceIRI,
  attribute: EntityUUID,
  `type`: EntityUUID )

/**
  * @group Attribute
  */
object AttributeProperty2DataType {

  implicit val formats
  : Format[AttributeProperty2DataType]
  = Json.format[AttributeProperty2DataType]
}