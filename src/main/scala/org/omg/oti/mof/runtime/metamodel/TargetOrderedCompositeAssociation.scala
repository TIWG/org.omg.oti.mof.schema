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

case class TargetOrderedCompositeAssociation[S <: runtime.model.ModelElement, US >: S, T <: runtime.model.ModelElement, UT >: T]
( override val metaAssociation: runtime.metamodel.MetaAssociation[S, US, T, UT, runtime.model.ModelOrderedLink[S, T]] )
  extends Association[S,US,T,UT, runtime.model.ModelOrderedLink[S, T]] {

  override def hashCode(): Int
  = metaAssociation.hashCode()

  /**
    * examples 1
                MagicDrawUMLAssociation.scala  (1 usage found)
                    F:A_ownedEnd_owningAssociation.queryIterable

    * @return
    */
  def queryIterable(source: S)
  : Iterable[UT]
  = scala.Predef.???

  /**
    * examples 39
                MagicDrawUMLAcceptEventAction.scala  (1 usage found)
                    F:A_result_acceptEventAction.queryOrdered
                MagicDrawUMLAction.scala  (2 usages found)
                    F:A_input_action.queryOrdered
                    F:A_output_action.queryOrdered
                MagicDrawUMLArtifact.scala  (2 usages found)
                    F:A_ownedAttribute_artifact.queryOrdered
                    F:A_ownedOperation_artifact.queryOrdered
                MagicDrawUMLBehavior.scala  (1 usage found)
                    F:A_ownedParameter_behavior.queryOrdered
                MagicDrawUMLBehavioralFeature.scala  (1 usage found)
                    F:A_ownedParameter_ownerFormalParam.queryOrdered
                MagicDrawUMLCallAction.scala  (1 usage found)
                    F:A_result_callAction.queryOrdered
                MagicDrawUMLClass.scala  (3 usages found)
                    F:A_nestedClassifier_nestingClass.queryOrdered
                    F:A_ownedAttribute_class.queryOrdered
                    F:A_ownedOperation_class.queryOrdered
                MagicDrawUMLCombinedFragment.scala  (1 usage found)
                    F:A_operand_combinedFragment.queryOrdered
                MagicDrawUMLConditionalNode.scala  (1 usage found)
                    F:A_result_conditionalNode.queryOrdered
                MagicDrawUMLConnector.scala  (1 usage found)
                    F:A_end_connector.queryOrdered
                MagicDrawUMLDataType.scala  (2 usages found)
                    F:A_ownedAttribute_datatype.queryOrdered
                    F:A_ownedOperation_datatype.queryOrdered
                MagicDrawUMLEnumeration.scala  (1 usage found)
                    F:A_ownedLiteral_enumeration.queryOrdered
                MagicDrawUMLExpression.scala  (1 usage found)
                    F:A_operand_expression.queryOrdered
                MagicDrawUMLInteraction.scala  (1 usage found)
                    F:A_fragment_enclosingInteraction.queryOrdered
                MagicDrawUMLInteractionOperand.scala  (1 usage found)
                    F:A_fragment_enclosingOperand.queryOrdered
                MagicDrawUMLInteractionUse.scala  (1 usage found)
                    F:A_argument_interactionUse.queryOrdered
                MagicDrawUMLInterface.scala  (3 usages found)
                    F:A_nestedClassifier_interface.queryOrdered
                    F:A_ownedAttribute_interface.queryOrdered
                    F:A_ownedOperation_interface.queryOrdered
                MagicDrawUMLInvocationAction.scala  (1 usage found)
                    F:A_argument_invocationAction.queryOrdered
                MagicDrawUMLLoopNode.scala  (3 usages found)
                    F:A_loopVariable_loopNode.queryOrdered
                    F:A_loopVariableInput_loopNode.queryOrdered
                    F:A_result_loopNode.queryOrdered
                MagicDrawUMLMessage.scala  (1 usage found)
                    F:A_argument_message.queryOrdered
                MagicDrawUMLOperation.scala  (1 usage found)
                    F:A_ownedParameter_operation.queryOrdered
                MagicDrawUMLProperty.scala  (1 usage found)
                    F:A_qualifier_associationEnd.queryOrdered
                MagicDrawUMLReplyAction.scala  (1 usage found)
                    F:A_replyValue_replyAction.queryOrdered
                MagicDrawUMLSequenceNode.scala  (1 usage found)
                    F:A_executableNode_sequenceNode.queryOrdered
                MagicDrawUMLSignal.scala  (1 usage found)
                    F:A_ownedAttribute_owningSignal.queryOrdered
                MagicDrawUMLSlot.scala  (1 usage found)
                    F:A_value_owningSlot.queryOrdered
                MagicDrawUMLStringExpression.scala  (1 usage found)
                    F:A_subExpression_owningExpression.queryOrdered
                MagicDrawUMLStructuredClassifier.scala  (1 usage found)
                    F:A_ownedAttribute_structuredClassifier.queryOrdered
                MagicDrawUMLTemplateSignature.scala  (1 usage found)
                    F:A_ownedParameter_signature.queryOrdered
                MagicDrawUMLUnmarshallAction.scala  (1 usage found)
                    F:A_result_unmarshallAction.queryOrdered

    * @return
    */
  def queryOrdered(source: S)
  : Seq[UT]
  = scala.Predef.???

  /**
    * examples 40
                MagicDrawUMLClassifier.scala  (2 usages found)
                    R:A_nestedClassifier_nestingClass.queryOppositeOptional
                    R:A_nestedClassifier_interface.queryOppositeOptional
                MagicDrawUMLConnectorEnd.scala  (1 usage found)
                    R:A_end_connector.queryOppositeOptional
                MagicDrawUMLEnumerationLiteral.scala  (1 usage found)
                    R:A_ownedLiteral_enumeration.queryOppositeOptional
                MagicDrawUMLExecutableNode.scala  (1 usage found)
                    R:A_executableNode_sequenceNode.queryOppositeOptional
                MagicDrawUMLInputPin.scala  (4 usages found)
                    R:A_input_action.queryOppositeOptional
                    R:A_argument_invocationAction.queryOppositeOptional
                    R:A_loopVariableInput_loopNode.queryOppositeOptional
                    R:A_replyValue_replyAction.queryOppositeOptional
                MagicDrawUMLInteractionFragment.scala  (2 usages found)
                    R:A_fragment_enclosingInteraction.queryOppositeOptional
                    R:A_fragment_enclosingOperand.queryOppositeOptional
                MagicDrawUMLInteractionOperand.scala  (1 usage found)
                    R:A_operand_combinedFragment.queryOppositeOptional
                MagicDrawUMLOperation.scala  (4 usages found)
                    R:A_ownedOperation_class.queryOppositeOptional
                    R:A_ownedOperation_artifact.queryOppositeOptional
                    R:A_ownedOperation_datatype.queryOppositeOptional
                    R:A_ownedOperation_interface.queryOppositeOptional
                MagicDrawUMLOutputPin.scala  (7 usages found)
                    R:A_result_acceptEventAction.queryOppositeOptional
                    R:A_output_action.queryOppositeOptional
                    R:A_result_callAction.queryOppositeOptional
                    R:A_result_conditionalNode.queryOppositeOptional
                    R:A_loopVariable_loopNode.queryOppositeOptional
                    R:A_result_loopNode.queryOppositeOptional
                    R:A_result_unmarshallAction.queryOppositeOptional
                MagicDrawUMLParameter.scala  (3 usages found)
                    R:A_ownedParameter_behavior.queryOppositeOptional
                    R:A_ownedParameter_operation.queryOppositeOptional
                    R:A_ownedParameter_ownerFormalParam.queryOppositeOptional
                MagicDrawUMLProperty.scala  (8 usages found)
                    R:A_ownedAttribute_class.queryOppositeOptional
                    R:A_ownedAttribute_artifact.queryOppositeOptional
                    R:A_ownedAttribute_structuredClassifier.queryOppositeOptional
                    R:A_qualifier_associationEnd.queryOppositeOptional
                    R:A_ownedAttribute_datatype.queryOppositeOptional
                    R:A_ownedAttribute_interface.queryOppositeOptional
                    R:A_ownedEnd_owningAssociation.queryOppositeOptional
                    R:A_ownedAttribute_owningSignal.queryOppositeOptional
                MagicDrawUMLStringExpression.scala  (1 usage found)
                    R:A_subExpression_owningExpression.queryOppositeOptional
                MagicDrawUMLTemplateParameter.scala  (1 usage found)
                    R:A_ownedParameter_signature.queryOppositeOptional
                MagicDrawUMLValueSpecification.scala  (4 usages found)
                    R:A_argument_interactionUse.queryOppositeOptional
                    R:A_argument_message.queryOppositeOptional
                    R:A_operand_expression.queryOppositeOptional
                    R:A_value_owningSlot.queryOppositeOptional

    * @return
    */
  def queryOppositeOptional(target: T)
  : Option[US]
  = scala.Predef.???

}