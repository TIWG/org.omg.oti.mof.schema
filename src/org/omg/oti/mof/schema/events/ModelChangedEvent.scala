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
  element: model.ModelElement )
extends ModelChangedEvent

case class ModelLinkChangedEvent
( override val changeKind: ChangeKind,
  link: model.ModelLink )
extends ModelChangedEvent

case class ModelElementAttributeValueChangedEvent
( override val changeKind: ChangeKind,
  elementAttributeValue: model.ModelElementAttributeValue )
extends ModelChangedEvent

object ModelChangedEvent {

  implicit val formats
  : Format[ModelChangedEvent]
  = Variants.format[ModelChangedEvent]((__ \ "type").format[String])
}