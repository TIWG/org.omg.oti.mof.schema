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

package org.omg.oti.mof.schema.events

import play.json.extra._
import play.api.libs.json._
import org.omg.oti.mof.schema._
import scala.Predef.String

sealed trait ModelChangedEvent {
  val changeKind: ChangeKind
}

case class ModelElementChangedEvent
( override val changeKind: ChangeKind,
  element: tables.model.OTIMOFModelElement )
extends ModelChangedEvent

case class ModelOrderedLinkChangedEvent
( override val changeKind: ChangeKind,
  link: tables.model.OTIMOFModelOrderedLink )
extends ModelChangedEvent

case class ModelUnorderedLinkChangedEvent
( override val changeKind: ChangeKind,
  link: tables.model.OTIMOFModelUnorderedLink )
  extends ModelChangedEvent

case class ModelOrderedAttributeAtomicValueChangedEvent
( override val changeKind: ChangeKind,
  attributeValue: tables.values.OTIMOFOrderedAttributeAtomicValue )
extends ModelChangedEvent

case class ModelOrderedAttributeEnumerationLiteralValueChangedEvent
( override val changeKind: ChangeKind,
  attributeValue: tables.values.OTIMOFOrderedAttributeEnumerationLiteralValue )
  extends ModelChangedEvent

case class ModelOrderedAttributeStructuredValueChangedEvent
( override val changeKind: ChangeKind,
  attributeValue: tables.values.OTIMOFOrderedAttributeStructuredValueLink )
  extends ModelChangedEvent

case class ModelUnorderedAttributeAtomicValueChangedEvent
( override val changeKind: ChangeKind,
  attributeValue: tables.values.OTIMOFUnorderedAttributeAtomicValue )
  extends ModelChangedEvent

case class ModelUnorderedAttributeEnumerationLiteralValueChangedEvent
( override val changeKind: ChangeKind,
  attributeValue: tables.values.OTIMOFUnorderedAttributeEnumerationLiteralValue )
  extends ModelChangedEvent

case class ModelUnorderedAttributeStructuredValueChangedEvent
( override val changeKind: ChangeKind,
  attributeValue: tables.values.OTIMOFUnorderedAttributeStructuredValueLink )
  extends ModelChangedEvent

object ModelChangedEvent {

  implicit val formats
  : Format[ModelChangedEvent]
  = Variants.format[ModelChangedEvent]((__ \ "type").format[String])
}