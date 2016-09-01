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

import play.json.extra._
import play.api.libs.json._

import org.omg.oti.mof.schema.common._

import scala.Predef.String

/**
  * A feature typed by
  * a [[org.omg.oti.mof.schema.tables.library.OTIMOFEnumerationDataType]] or
  * [[org.omg.oti.mof.schema.tables.library.OTIMOFPrimitiveDataType]] or
  * [[org.omg.oti.mof.schema.tables.library.OTIMOFStructuredDataType]]
  * @group Base
  */
sealed trait DataTypedFeature
  extends Feature

/**
  * A datatyped attribute property
  *
  * @param uuid The primary key of the property
  * @param name The name of the property
  * @group Attribute
  */
case class DataTypedAttributeProperty
( override val resource: ResourceIRI,
  override val uuid: EntityUUID,
  override val name: Name )
  extends DataTypedFeature

object DataTypedAttributeProperty {

  implicit val formats
  : Format[DataTypedAttributeProperty]
  = Json.format[DataTypedAttributeProperty]

}

/**
  * @group Base
  */
object DataTypedFeature {

  implicit val formats
  : Format[DataTypedFeature]
  = Variants.format[DataTypedFeature]((__ \ "type").format[String])

}