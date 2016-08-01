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
package org.omg.oti.mof.schema.features

import play.json.extra._
import play.api.libs.json._

import org.omg.oti.mof.schema.common._

import scala.Predef.String

/**
  * A feature typed by a [[org.omg.oti.mof.schema.library.DatatypeClassifier]]
  * @group Base
  */
sealed trait DataTypedFeature
  extends Feature

/**
  * An enumeration literal feature
  *
  * @param uuid The primary key of the enumeration literal
  * @param name The name of the enumeration literal
  * @group EnumLit
  */
case class EnumerationLiteral
( override val resource: ResourceIRI,
  override val uuid: EntityUUID,
  override val name: Name )
  extends DataTypedFeature

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