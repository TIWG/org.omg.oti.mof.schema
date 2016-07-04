/*
 *
 * License Terms
 *
 * Copyright (c) 2014-2016, California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * *   Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * *   Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the
 *    distribution.
 *
 * *   Neither the name of Caltech nor its operating division, the Jet
 *    Propulsion Laboratory, nor the names of its contributors may be
 *    used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.omg.oti.mof.schema.model

import org.omg.oti.mof.schema.common.EntityUUID
import org.omg.oti.mof.schema.values.AttributeValue

import play.json.extra._
import play.api.libs.json._

import scala.Int
import scala.Predef.String

/**
  * ModelElementAttributeValue represents the value of a [[org.omg.oti.mof.schema.features.DataTypedAttributeProperty]]
  * that is defined either in a [[org.omg.oti.mof.schema.metamodel.MetaClass]]
  * or a [[org.omg.oti.mof.schema.profile.Stereotype]]
  */
sealed trait ModelElementAttributeValue {
  val modelElement: EntityUUID
  val attributeValue: AttributeValue
}

case class ModelElementUnorderedAttributeValue
( override val modelElement: EntityUUID,
  override val attributeValue: AttributeValue )
  extends ModelElementAttributeValue


case class ModelElementOrderedAttributeValue
( override val modelElement: EntityUUID,
  override val attributeValue: AttributeValue,
  index: Int )
  extends ModelElementAttributeValue

object ModelElementAttributeValue {

  implicit val formats
  : Format[ModelElementAttributeValue]
  = Variants.format[ModelElementAttributeValue]((__ \ "type").format[String])

}