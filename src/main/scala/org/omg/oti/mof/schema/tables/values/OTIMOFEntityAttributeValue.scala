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

package org.omg.oti.mof.schema.tables.values

import org.omg.oti.mof.schema.common._
import scala.{Int,Option}

/**
  * Represents the value of an attribute defined in the context of an entity classifier.
  * The entity can be one of 3 kinds:
  * - A model element that is an instance of a metaclass; the metaclass is the classifier context of the attribute.
  * - A model element that with a stereotype applied; the stereotype is the classifier context of the attribute.
  * - A structured value that is an instance of a structured datatype that is the classifier context of the attribute.
  */
trait OTIMOFEntityAttributeValue {
  val resource: ResourceIRI
  val entity: EntityUUID
  val attribute: EntityUUID

  def getIndex: Option[Int]
  def getAtomicValue: Option[AtomicValueRepresentation]
  def getEnumerationLiteralValue: Option[EntityUUID]
  def getStructuredValue: Option[EntityUUID]
}