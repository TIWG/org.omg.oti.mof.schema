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

import scala.Boolean
import scala.Predef.String

/**
  * An AssociationEnd property that is either the source or target
  * of a [[org.omg.oti.mof.schema.tables.metamodel.OTIMOFMetaAssociation]]
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
  * An AssociationEnd that is the source of a [[org.omg.oti.mof.schema.tables.metamodel.OTIMOFMetaAssociation]]
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
  * of a [[org.omg.oti.mof.schema.tables.metamodel.OTIMOFMetaAssociation]]
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