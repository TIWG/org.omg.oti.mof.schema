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

package org.omg.oti.mof.runtime.metamodel

import org.omg.oti.mof._

import scala.collection.immutable._
import scala.collection.Iterable
import scala.{Int,Option}

case class TargetOrderedReferenceAssociation[S <: runtime.model.ModelElement, US >: S, T <: runtime.model.ModelElement, UT >: T]
( override val metaAssociation: runtime.metamodel.MetaAssociation[S, US, T, UT, runtime.model.ModelOrderedLink[S, T]] )
  extends Association[S,US,T,UT, runtime.model.ModelOrderedLink[S, T]] {

  override def hashCode(): Int
  = metaAssociation.hashCode()

  /**
    * examples 1
                MagicDrawUMLInstanceSpecification.scala  (1 usage found)
                    F:A_classifier_instanceSpecification.queryIterable

    * @return
    */
  def queryIterable(source: S)
  : Iterable[UT]
  = scala.Predef.???

  /**
    * examples 8
                MagicDrawUMLAssociation.scala  (1 usage found)
                    F:A_memberEnd_association.queryOrdered
                MagicDrawUMLClassifier.scala  (1 usage found)
                    F:A_attribute_classifier.queryOrdered
                MagicDrawUMLClause.scala  (1 usage found)
                    F:A_bodyOutput_clause.queryOrdered
                MagicDrawUMLConstraint.scala  (1 usage found)
                    F:A_constrainedElement_constraint.queryOrdered
                MagicDrawUMLDurationObservation.scala  (1 usage found)
                    F:A_event_durationObservation.queryOrdered
                MagicDrawUMLExtend.scala  (1 usage found)
                    F:A_extensionLocation_extension.queryOrdered
                MagicDrawUMLLoopNode.scala  (1 usage found)
                    F:A_bodyOutput_loopNode.queryOrdered
                MagicDrawUMLTemplateSignature.scala  (1 usage found)
                    F:A_parameter_templateSignature.queryOrdered

    * @return
    */
  def queryOrdered(source: S)
  : Seq[UT]
  = scala.Predef.???

  /**
    * examples 2
                MagicDrawUMLProperty.scala  (2 usages found)
                    R:A_memberEnd_association.queryOppositeOptional
                    R:A_attribute_classifier.queryOppositeOptional

    * @return
    */
  def queryOppositeOptional(target: T)
  : Option[US]
  = scala.Predef.???

  /**
    * examples 7
                MagicDrawUMLClassifier.scala  (1 usage found)
                    R:A_classifier_instanceSpecification.queryOppositeUnordered
                MagicDrawUMLElement.scala  (1 usage found)
                    R:A_constrainedElement_constraint.queryOppositeUnordered
                MagicDrawUMLExtensionPoint.scala  (1 usage found)
                    R:A_extensionLocation_extension.queryOppositeUnordered
                MagicDrawUMLNamedElement.scala  (1 usage found)
                    R:A_event_durationObservation.queryOppositeUnordered
                MagicDrawUMLOutputPin.scala  (2 usages found)
                    R:A_bodyOutput_clause.queryOppositeUnordered
                    R:A_bodyOutput_loopNode.queryOppositeUnordered
                MagicDrawUMLTemplateParameter.scala  (1 usage found)
                    R:A_parameter_templateSignature.queryOppositeUnordered

    * @return
    */
  def queryOppositeUnordered(target: T)
  : Set[US]
  = scala.Predef.???

}