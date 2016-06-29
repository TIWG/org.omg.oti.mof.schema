package org.omg.oti.mof.schema.events

import play.api.libs.json._
import scala.StringContext

trait ChangeKind

object AddedChange extends ChangeKind

object DeletedChange extends ChangeKind

object ModifiedChange extends ChangeKind

object ChangeKind {

  implicit val formats
  : Format[ChangeKind]
  = new Format[ChangeKind] {

    def reads(json: JsValue): JsResult[ChangeKind] = json match {
      case JsString("Added") =>
        JsSuccess(AddedChange)

      case JsString("Deleted") =>
        JsSuccess(DeletedChange)

      case JsString("Modified") =>
        JsSuccess(ModifiedChange)

      case unknown =>
        JsError(s"ChangeKind: String value expected, got: $unknown")
    }

    def writes(v: ChangeKind): JsValue =
      v match {
        case AddedChange =>
          JsString("Added")

        case DeletedChange =>
          JsString("Deleted")

        case ModifiedChange =>
          JsString("Modified")
      }
  }

}