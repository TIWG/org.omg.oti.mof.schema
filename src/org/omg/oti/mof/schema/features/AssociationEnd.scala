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

import scala.Boolean
import scala.Predef.String

/**
  * An AssociationEnd property that is either the source or target
  * of a [[org.omg.oti.mof.schema.metamodel.MetaAssociation]]
  *
  * @group AssociationEnd
  */
sealed trait AssociationEnd
  extends Feature {

  def isSource: Boolean
  def isTarget: Boolean
  def isCompositeTarget: Boolean
  def isReferenceTarget: Boolean
}

/**
  * An AssociationEnd that is the source of a [[org.omg.oti.mof.schema.metamodel.MetaAssociation]]
  * @param uuid The primary key of the association end source property
  * @param name The name of the association end source property
  * @group AssociationEnd
  */
case class AssociationSourceEnd
( override val resource: ResourceIRI,
  override val uuid: EntityUUID,
  override val name: Name )
  extends AssociationEnd {

  override def isSource: Boolean = true
  override def isTarget: Boolean = false
  override def isCompositeTarget: Boolean = false
  override def isReferenceTarget: Boolean = false
}

/**
  * An AssociationEnd property that is the target
  * of a [[org.omg.oti.mof.schema.metamodel.MetaAssociation]]
  *
  * @group AssociationEnd
  */
sealed trait AssociationTargetEnd {
  def uuid: EntityUUID
}

/**
  * An Association target end property with non-composite aggregation
  * @param uuid The primary key of the association end target property
  * @param name The name of the association end target property
  * @group AssociationEnd
  */
case class AssociationTargetReferenceEnd
( override val resource: ResourceIRI,
  override val uuid: EntityUUID,
  override val name: Name )
  extends AssociationEnd
  with AssociationTargetEnd {

  override def isSource: Boolean = false
  override def isTarget: Boolean = true
  override def isCompositeTarget: Boolean = false
  override def isReferenceTarget: Boolean = true
}

/**
  * An Association target end property with composite aggregation
  * @param uuid The primary key of the association end target property
  * @param name The name of the association end target property
  * @group AssociationEnd
  */
case class AssociationTargetCompositeEnd
( override val resource: ResourceIRI,
  override val uuid: EntityUUID,
  override val name: Name )
  extends AssociationEnd
  with AssociationTargetEnd {

  override def isSource: Boolean = false
  override def isTarget: Boolean = true
  override def isCompositeTarget: Boolean = true
  override def isReferenceTarget: Boolean = false
}

/**
  * @group AssociationEnd
  */
object AssociationTargetEnd {

  implicit val formats
  : Format[AssociationTargetEnd]
  = Variants.format[AssociationTargetEnd]((__ \ "type").format[String])

}

/**
  * @group AssociationEnd
  */
object AssociationEnd {

  implicit val formats
  : Format[AssociationEnd]
  = Variants.format[AssociationEnd]((__ \ "type").format[String])

}