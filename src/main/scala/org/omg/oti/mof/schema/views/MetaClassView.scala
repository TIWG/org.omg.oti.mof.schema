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

package org.omg.oti.mof.schema.views

import org.omg.oti.mof.schema._

import scala.collection.immutable._
import scala.{Int, Option}
import scala.Predef.String

case class MetaClassView
(metaClass: tables.metamodel.OTIMOFMetaClass,
 directGeneralizations: Set[tables.metamodel.OTIMOFMetaClass],
 directSpecializations: Set[tables.metamodel.OTIMOFMetaClass],
 forwardAssociations: Vector[AssociationInfo],
 reverseAssociations: Vector[AssociationInfo],
 orderedAtomicAttributes: Map[Int, views.DataTypedAttributeInfo],
 orderedEnumerationAttributes: Map[Int, views.DataTypedAttributeInfo],
 orderedStructuredAttributes: Map[Int, views.DataTypedAttributeInfo],
 unorderedAtomicAttributes: Map[Int, views.DataTypedAttributeInfo],
 unorderedEnumerationAttributes: Map[Int, views.DataTypedAttributeInfo],
 unorderedStructuredAttributes: Map[Int, views.DataTypedAttributeInfo]) {

  def getDataAttributeInfo(aName: String)
  : (Int, views.DataTypedAttributeInfo)
  = getDataOrderedAtomicAttributeInfo(aName) getOrElse {
    getDataOrderedEnumerationAttributeInfo(aName) getOrElse {
      getDataOrderedStructuredAttributeInfo(aName) getOrElse {
        getDataUnorderedAtomicAttributeInfo(aName) getOrElse {
          getDataUnorderedEnumerationAttributeInfo(aName) getOrElse {
            getDataUnorderedStructuredAttributeInfo(aName) getOrElse {
              throw new java.lang.IllegalArgumentException(
                "MetaClassView(" + metaClass.name.value + ") No data attribute: " + aName)
            }
          }
        }
      }
    }
  }

  def getDataOrderedAtomicAttributeInfo(aName: String)
  : Option[(Int, views.DataTypedAttributeInfo)]
  = orderedAtomicAttributes.find { _._2.name.value == aName }

  def getDataOrderedEnumerationAttributeInfo(aName: String)
  : Option[(Int, views.DataTypedAttributeInfo)]
  = orderedEnumerationAttributes.find { _._2.name.value == aName }

  def getDataOrderedStructuredAttributeInfo(aName: String)
  : Option[(Int, views.DataTypedAttributeInfo)]
  = orderedStructuredAttributes.find { _._2.name.value == aName }

  def getDataUnorderedAtomicAttributeInfo(aName: String)
  : Option[(Int, views.DataTypedAttributeInfo)]
  = unorderedAtomicAttributes.find { _._2.name.value == aName }

  def getDataUnorderedEnumerationAttributeInfo(aName: String)
  : Option[(Int, views.DataTypedAttributeInfo)]
  = unorderedEnumerationAttributes.find { _._2.name.value == aName }

  def getDataUnorderedStructuredAttributeInfo(aName: String)
  : Option[(Int, views.DataTypedAttributeInfo)]
  = unorderedStructuredAttributes.find { _._2.name.value == aName }

}