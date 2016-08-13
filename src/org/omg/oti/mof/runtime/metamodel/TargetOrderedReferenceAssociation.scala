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
    * @usecase 1
                MagicDrawUMLInstanceSpecification.scala  (1 usage found)
                    F:A_classifier_instanceSpecification.queryIterable

    * @return
    */
  def queryIterable(source: S)
  : Iterable[UT]
  = scala.Predef.???

  /**
    * @usecase 8
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
    * @usecase 2
                MagicDrawUMLProperty.scala  (2 usages found)
                    R:A_memberEnd_association.queryOppositeOptional
                    R:A_attribute_classifier.queryOppositeOptional

    * @return
    */
  def queryOppositeOptional(target: T)
  : Option[US]
  = scala.Predef.???

  /**
    * @usecase 7
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