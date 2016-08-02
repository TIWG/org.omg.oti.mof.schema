package org.omg.oti.mof.schema

import play.api.libs.json._
import scala.StringContext
import scala.Predef.String

case class TableLoadException(message: String, jsError: JsError)
extends java.lang.IllegalArgumentException(message) {

  override def getMessage: String =
    s"TableLoadException:\n"+ message + Json.stringify(JsError.toJson(jsError))

}
