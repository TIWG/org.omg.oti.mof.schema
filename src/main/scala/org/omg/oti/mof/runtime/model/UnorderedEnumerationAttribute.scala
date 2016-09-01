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

package org.omg.oti.mof.runtime.model

import org.omg.oti._
import scala.collection.immutable._
import scala.{Enumeration,Option,Unit}

case class UnorderedEnumerationAttribute[T <: Enumeration#Value]
( f: mof.schema.views.DataTypedAttributeInfo ) {

  def addValue
  (value: mof.schema.tables.values.OTIMOFUnorderedAttributeEnumerationLiteralValue)
  : Unit
  = scala.Predef.???

  def removeValue
  (value: mof.schema.tables.values.OTIMOFUnorderedAttributeEnumerationLiteralValue)
  : Unit
  = scala.Predef.???

  def queryOptional(e: ModelElement)
  : Option[T]
  = scala.Predef.???

  def queryRequired(e: ModelElement)
  : T
  = scala.Predef.???

  def queryUnordered(e: ModelElement)
  : Set[T]
  = scala.Predef.???

}