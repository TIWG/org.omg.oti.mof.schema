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

case class UnorderedReferenceAssociation[S <: runtime.model.ModelElement, US >: S, T <: runtime.model.ModelElement, UT >: T]
( override val metaAssociation: runtime.metamodel.MetaAssociation[S, US, T, UT, runtime.model.ModelUnorderedLink[S, T]] )
  extends Association[S,US,T,UT, runtime.model.ModelUnorderedLink[S, T]] {

  override def hashCode(): Int
  = metaAssociation.hashCode()

  /**
    * @usecase 7
                MagicDrawUMLEnumerationLiteral.scala  (1 usage found)
                    F:A_classifier_enumerationLiteral.queryIterable
                MagicDrawUMLInteractionFragment.scala  (1 usage found)
                    F:A_covered_coveredBy.queryIterable
                MagicDrawUMLRedefinableElement.scala  (1 usage found)
                    F:A_redefinitionContext_redefinableElement.queryIterable
                MagicDrawUMLRegion.scala  (1 usage found)
                    F:A_redefinitionContext_region.queryIterable
                MagicDrawUMLState.scala  (1 usage found)
                    F:A_redefinitionContext_state.queryIterable
                MagicDrawUMLStateInvariant.scala  (1 usage found)
                    F:A_covered_stateInvariant.queryIterable
                MagicDrawUMLTransition.scala  (1 usage found)
                    F:A_redefinitionContext_transition.queryIterable

    * @return
    */
  def queryIterable(source: S)
  : Iterable[UT]
  = scala.Predef.???

  /**
    * @usecase 105
                MagicDrawUMLAction.scala  (1 usage found)
                    F:A_context_action.queryOptional
                MagicDrawUMLActionExecutionSpecification.scala  (1 usage found)
                    F:A_action_actionExecutionSpecification.queryOptional
                MagicDrawUMLActivityParameterNode.scala  (1 usage found)
                    F:A_parameter_activityParameterNode.queryOptional
                MagicDrawUMLActivityPartition.scala  (1 usage found)
                    F:A_represents_activityPartition.queryOptional
                MagicDrawUMLBehavior.scala  (1 usage found)
                    F:A_context_behavior.queryOptional
                MagicDrawUMLBehavioredClassifier.scala  (1 usage found)
                    F:A_classifierBehavior_behavioredClassifier.queryOptional
                MagicDrawUMLBehaviorExecutionSpecification.scala  (1 usage found)
                    F:A_behavior_behaviorExecutionSpecification.queryOptional
                MagicDrawUMLBroadcastSignalAction.scala  (1 usage found)
                    F:A_signal_broadcastSignalAction.queryOptional
                MagicDrawUMLCallBehaviorAction.scala  (1 usage found)
                    F:A_behavior_callBehaviorAction.queryOptional
                MagicDrawUMLCallEvent.scala  (1 usage found)
                    F:A_operation_callEvent.queryOptional
                MagicDrawUMLCallOperationAction.scala  (1 usage found)
                    F:A_operation_callOperationAction.queryOptional
                MagicDrawUMLClassifier.scala  (2 usages found)
                    F:A_representation_classifier.queryOptional
                    F:A_classifier_templateParameter_parameteredElement.queryOptional
                MagicDrawUMLClause.scala  (1 usage found)
                    F:A_decider_clause.queryOptional
                MagicDrawUMLClearAssociationAction.scala  (1 usage found)
                    F:A_association_clearAssociationAction.queryOptional
                MagicDrawUMLCollaborationUse.scala  (1 usage found)
                    F:A_type_collaborationUse.queryOptional
                MagicDrawUMLConnectableElement.scala  (1 usage found)
                    F:A_connectableElement_templateParameter_parameteredElement.queryOptional
                MagicDrawUMLConnector.scala  (1 usage found)
                    F:A_type_connector.queryOptional
                MagicDrawUMLConnectorEnd.scala  (2 usages found)
                    F:A_definingEnd_connectorEnd.queryOptional
                    F:A_partWithPort_connectorEnd.queryOptional
                MagicDrawUMLCreateObjectAction.scala  (1 usage found)
                    F:A_classifier_createObjectAction.queryOptional
                MagicDrawUMLDecisionNode.scala  (2 usages found)
                    F:A_decisionInput_decisionNode.queryOptional
                    F:A_decisionInputFlow_decisionNode.queryOptional
                MagicDrawUMLDiagram.scala  (1 usage found)
                    F:MD_diagramOfContext_context.queryOptional
                MagicDrawUMLDurationInterval.scala  (2 usages found)
                    F:A_max_durationInterval.queryOptional
                    F:A_min_durationInterval.queryOptional
                MagicDrawUMLElementImport.scala  (1 usage found)
                    F:A_importedElement_import.queryOptional
                MagicDrawUMLElementValue.scala  (1 usage found)
                    F:MD_elementValueOfElement_element.queryOptional
                MagicDrawUMLExceptionHandler.scala  (2 usages found)
                    F:A_exceptionInput_exceptionHandler.queryOptional
                    F:A_handlerBody_exceptionHandler.queryOptional
                MagicDrawUMLExecutionOccurrenceSpecification.scala  (1 usage found)
                    F:A_execution_executionOccurrenceSpecification.queryOptional
                MagicDrawUMLExecutionSpecification.scala  (2 usages found)
                    F:A_finish_executionSpecification.queryOptional
                    F:A_start_executionSpecification.queryOptional
                MagicDrawUMLExtend.scala  (1 usage found)
                    F:A_extendedCase_extend.queryOptional
                MagicDrawUMLExtensionEnd.scala  (1 usage found)
                    F:A_type_extensionEnd.queryOptional
                MagicDrawUMLGeneralization.scala  (1 usage found)
                    F:A_general_generalization.queryOptional
                MagicDrawUMLInclude.scala  (1 usage found)
                    F:A_addition_include.queryOptional
                MagicDrawUMLInstanceValue.scala  (1 usage found)
                    F:A_instance_instanceValue.queryOptional
                MagicDrawUMLInteractionUse.scala  (2 usages found)
                    F:A_refersTo_interactionUse.queryOptional
                    F:A_returnValueRecipient_interactionUse.queryOptional
                MagicDrawUMLInterfaceRealization.scala  (1 usage found)
                    F:A_contract_interfaceRealization.queryOptional
                MagicDrawUMLInterval.scala  (2 usages found)
                    F:A_max_interval.queryOptional
                    F:A_min_interval.queryOptional
                MagicDrawUMLInvocationAction.scala  (1 usage found)
                    F:A_onPort_invocationAction.queryOptional
                MagicDrawUMLLifeline.scala  (2 usages found)
                    F:A_decomposedAs_lifeline.queryOptional
                    F:A_represents_lifeline.queryOptional
                MagicDrawUMLLinkEndCreationData.scala  (1 usage found)
                    F:A_insertAt_linkEndCreationData.queryOptional
                MagicDrawUMLLinkEndData.scala  (2 usages found)
                    F:A_end_linkEndData.queryOptional
                    F:A_value_linkEndData.queryOptional
                MagicDrawUMLLinkEndDestructionData.scala  (1 usage found)
                    F:A_destroyAt_linkEndDestructionData.queryOptional
                MagicDrawUMLLoopNode.scala  (1 usage found)
                    F:A_decider_loopNode.queryOptional
                MagicDrawUMLManifestation.scala  (1 usage found)
                    F:A_utilizedElement_manifestation.queryOptional
                MagicDrawUMLMessage.scala  (4 usages found)
                    F:A_connector_message.queryOptional
                    F:A_receiveEvent_endMessage.queryOptional
                    F:A_sendEvent_endMessage.queryOptional
                    F:A_signature_message.queryOptional
                MagicDrawUMLMessageEnd.scala  (1 usage found)
                    F:A_message_messageEnd.queryOptional
                MagicDrawUMLObjectFlow.scala  (2 usages found)
                    F:A_selection_objectFlow.queryOptional
                    F:A_transformation_objectFlow.queryOptional
                MagicDrawUMLObjectNode.scala  (1 usage found)
                    F:A_selection_objectNode.queryOptional
                MagicDrawUMLOpaqueExpression.scala  (2 usages found)
                    F:A_behavior_opaqueExpression.queryOptional
                    F:A_result_opaqueExpression.queryOptional
                MagicDrawUMLOperation.scala  (3 usages found)
                    F:A_bodyCondition_bodyContext.queryOptional
                    F:A_operation_templateParameter_parameteredElement.queryOptional
                    F:A_type_operation.queryOptional
                MagicDrawUMLPackageImport.scala  (1 usage found)
                    F:A_importedPackage_packageImport.queryOptional
                MagicDrawUMLPackageMerge.scala  (1 usage found)
                    F:A_mergedPackage_packageMerge.queryOptional
                MagicDrawUMLPort.scala  (1 usage found)
                    F:A_protocol_port.queryOptional
                MagicDrawUMLProfileApplication.scala  (1 usage found)
                    F:A_appliedProfile_profileApplication.queryOptional
                MagicDrawUMLProperty.scala  (1 usage found)
                    F:A_opposite_property.queryOptional
                MagicDrawUMLProtocolConformance.scala  (1 usage found)
                    F:A_generalMachine_protocolConformance.queryOptional
                MagicDrawUMLProtocolTransition.scala  (2 usages found)
                    F:A_postCondition_owningTransition.queryOptional
                    F:A_preCondition_protocolTransition.queryOptional
                MagicDrawUMLQualifierValue.scala  (2 usages found)
                    F:A_qualifier_qualifierValue.queryOptional
                    F:A_value_qualifierValue.queryOptional
                MagicDrawUMLReadExtentAction.scala  (1 usage found)
                    F:A_classifier_readExtentAction.queryOptional
                MagicDrawUMLReadIsClassifiedObjectAction.scala  (1 usage found)
                    F:A_classifier_readIsClassifiedObjectAction.queryOptional
                MagicDrawUMLReadLinkObjectEndAction.scala  (1 usage found)
                    F:A_end_readLinkObjectEndAction.queryOptional
                MagicDrawUMLReadLinkObjectEndQualifierAction.scala  (1 usage found)
                    F:A_qualifier_readLinkObjectEndQualifierAction.queryOptional
                MagicDrawUMLReception.scala  (1 usage found)
                    F:A_signal_reception.queryOptional
                MagicDrawUMLReduceAction.scala  (1 usage found)
                    F:A_reducer_reduceAction.queryOptional
                MagicDrawUMLRegion.scala  (1 usage found)
                    F:A_extendedRegion_region.queryOptional
                MagicDrawUMLReplyAction.scala  (1 usage found)
                    F:A_replyToCall_replyAction.queryOptional
                MagicDrawUMLSendSignalAction.scala  (1 usage found)
                    F:A_signal_sendSignalAction.queryOptional
                MagicDrawUMLSignalEvent.scala  (1 usage found)
                    F:A_signal_signalEvent.queryOptional
                MagicDrawUMLSlot.scala  (1 usage found)
                    F:A_definingFeature_slot.queryOptional
                MagicDrawUMLState.scala  (2 usages found)
                    F:A_redefinedState_state.queryOptional
                    F:A_stateInvariant_owningState.queryOptional
                MagicDrawUMLStereotype.scala  (1 usage found)
                    F:A_profile_stereotype.queryOptional
                MagicDrawUMLStructuralFeatureAction.scala  (1 usage found)
                    F:A_structuralFeature_structuralFeatureAction.queryOptional
                MagicDrawUMLSubstitution.scala  (1 usage found)
                    F:A_contract_substitution.queryOptional
                MagicDrawUMLTemplateBinding.scala  (1 usage found)
                    F:A_signature_templateBinding.queryOptional
                MagicDrawUMLTemplateParameter.scala  (2 usages found)
                    F:A_default_templateParameter.queryOptional
                    F:A_parameteredElement_templateParameter.queryOptional
                MagicDrawUMLTemplateParameterSubstitution.scala  (2 usages found)
                    F:A_actual_templateParameterSubstitution.queryOptional
                    F:A_formal_templateParameterSubstitution.queryOptional
                MagicDrawUMLTimeInterval.scala  (2 usages found)
                    F:A_max_timeInterval.queryOptional
                    F:A_min_timeInterval.queryOptional
                MagicDrawUMLTimeObservation.scala  (1 usage found)
                    F:A_event_timeObservation.queryOptional
                MagicDrawUMLTransition.scala  (2 usages found)
                    F:A_guard_transition.queryOptional
                    F:A_redefinedTransition_transition.queryOptional
                MagicDrawUMLTrigger.scala  (1 usage found)
                    F:A_event_trigger.queryOptional
                MagicDrawUMLTypedElement.scala  (1 usage found)
                    F:A_type_typedElement.queryOptional
                MagicDrawUMLUnmarshallAction.scala  (1 usage found)
                    F:A_unmarshallType_unmarshallAction.queryOptional
                MagicDrawUMLVariableAction.scala  (1 usage found)
                    F:A_variable_variableAction.queryOptional

    * @return
    */
  def queryOptional(source: S)
  : Option[UT]
  = scala.Predef.???

  /**
    * @usecase 96
                MagicDrawUMLActivity.scala  (1 usage found)
                    F:A_partition_activity.queryUnordered
                MagicDrawUMLActivityEdge.scala  (1 usage found)
                    F:A_redefinedEdge_activityEdge.queryUnordered
                MagicDrawUMLActivityGroup.scala  (2 usages found)
                    F:A_containedEdge_inGroup.queryUnordered
                    F:A_containedNode_inGroup.queryUnordered
                MagicDrawUMLActivityNode.scala  (5 usages found)
                    F:A_inInterruptibleRegion_node.queryUnordered
                    F:A_inPartition_node.queryUnordered
                    F:A_incoming_target_node.queryUnordered
                    F:A_outgoing_source_node.queryUnordered
                    F:A_redefinedNode_activityNode.queryUnordered
                MagicDrawUMLActivityPartition.scala  (1 usage found)
                    F:A_edge_inPartition.queryUnordered
                MagicDrawUMLAssociation.scala  (2 usages found)
                    F:A_endType_association.queryUnordered
                    F:A_navigableOwnedEnd_association.queryUnordered
                MagicDrawUMLBehavior.scala  (3 usages found)
                    F:A_postcondition_behavior.queryUnordered
                    F:A_precondition_behavior.queryUnordered
                    F:A_redefinedBehavior_behavior.queryUnordered
                MagicDrawUMLBehavioralFeature.scala  (2 usages found)
                    F:A_method_specification.queryUnordered
                    F:A_raisedException_behavioralFeature.queryUnordered
                MagicDrawUMLClass.scala  (2 usages found)
                    F:A_extension_metaclass.queryUnordered
                    F:A_superClass_class.queryUnordered
                MagicDrawUMLClassifier.scala  (5 usages found)
                    F:A_feature_featuringClassifier.queryUnordered
                    F:A_general_classifier.queryUnordered
                    F:A_inheritedMember_inheritingClassifier.queryUnordered
                    F:A_powertypeExtent_powertype.queryUnordered
                    F:A_redefinedClassifier_classifier.queryUnordered
                MagicDrawUMLClassifierTemplateParameter.scala  (1 usage found)
                    F:A_constrainingClassifier_classifierTemplateParameter.queryUnordered
                MagicDrawUMLClause.scala  (3 usages found)
                    F:A_body_clause.queryUnordered
                    F:A_predecessorClause_successorClause.queryUnordered
                    F:A_test_clause.queryUnordered
                MagicDrawUMLCollaboration.scala  (1 usage found)
                    F:A_collaborationRole_collaboration.queryUnordered
                MagicDrawUMLComment.scala  (1 usage found)
                    F:A_annotatedElement_comment.queryUnordered
                MagicDrawUMLComponent.scala  (2 usages found)
                    F:A_provided_component.queryUnordered
                    F:A_required_component.queryUnordered
                MagicDrawUMLComponentRealization.scala  (1 usage found)
                    F:A_realizingClassifier_componentRealization.queryUnordered
                MagicDrawUMLConnectableElement.scala  (1 usage found)
                    F:A_end_role.queryUnordered
                MagicDrawUMLConnectionPointReference.scala  (2 usages found)
                    F:A_entry_connectionPointReference.queryUnordered
                    F:A_exit_connectionPointReference.queryUnordered
                MagicDrawUMLConnector.scala  (2 usages found)
                    F:A_contract_connector.queryUnordered
                    F:A_redefinedConnector_connector.queryUnordered
                MagicDrawUMLConsiderIgnoreFragment.scala  (1 usage found)
                    F:A_message_considerIgnoreFragment.queryUnordered
                MagicDrawUMLDependency.scala  (2 usages found)
                    F:A_clientDependency_client.queryUnordered
                    F:A_supplier_supplierDependency.queryUnordered
                MagicDrawUMLDeployment.scala  (1 usage found)
                    F:A_deployedArtifact_deploymentForArtifact.queryUnordered
                MagicDrawUMLDeploymentTarget.scala  (1 usage found)
                    F:A_deployedElement_deploymentTarget.queryUnordered
                MagicDrawUMLDirectedRelationship.scala  (2 usages found)
                    F:A_source_directedRelationship.queryUnordered
                    F:A_target_directedRelationship.queryUnordered
                MagicDrawUMLDuration.scala  (1 usage found)
                    F:A_observation_duration.queryUnordered
                MagicDrawUMLExceptionHandler.scala  (1 usage found)
                    F:A_exceptionType_exceptionHandler.queryUnordered
                MagicDrawUMLExpansionRegion.scala  (2 usages found)
                    F:A_inputElement_regionAsInput.queryUnordered
                    F:A_outputElement_regionAsOutput.queryUnordered
                MagicDrawUMLGeneralization.scala  (1 usage found)
                    F:A_generalizationSet_generalization.queryUnordered
                MagicDrawUMLInformationFlow.scala  (7 usages found)
                    F:A_conveyed_conveyingFlow.queryUnordered
                    F:A_informationSource_informationFlow.queryUnordered
                    F:A_informationTarget_informationFlow.queryUnordered
                    F:A_realization_abstraction_flow.queryUnordered
                    F:A_realizingActivityEdge_informationFlow.queryUnordered
                    F:A_realizingConnector_informationFlow.queryUnordered
                    F:A_realizingMessage_informationFlow.queryUnordered
                MagicDrawUMLInformationItem.scala  (1 usage found)
                    F:A_represented_representation.queryUnordered
                MagicDrawUMLInterface.scala  (1 usage found)
                    F:A_redefinedInterface_interface.queryUnordered
                MagicDrawUMLInterruptibleActivityRegion.scala  (1 usage found)
                    F:A_interruptingEdge_interrupts.queryUnordered
                MagicDrawUMLLoopNode.scala  (3 usages found)
                    F:A_bodyPart_loopNode.queryUnordered
                    F:A_setupPart_loopNode.queryUnordered
                    F:A_test_loopNode.queryUnordered
                MagicDrawUMLNamespace.scala  (2 usages found)
                    F:A_importedMember_namespace.queryUnordered
                    F:A_member_memberNamespace.queryUnordered
                MagicDrawUMLObjectNode.scala  (1 usage found)
                    F:A_inState_objectNode.queryUnordered
                MagicDrawUMLOccurrenceSpecification.scala  (2 usages found)
                    F:A_before_toAfter.queryUnordered
                    F:A_toBefore_after.queryUnordered
                MagicDrawUMLOperation.scala  (4 usages found)
                    F:A_postcondition_postContext.queryUnordered
                    F:A_precondition_preContext.queryUnordered
                    F:A_raisedException_operation.queryUnordered
                    F:A_redefinedOperation_operation.queryUnordered
                MagicDrawUMLParameter.scala  (1 usage found)
                    F:A_parameterSet_parameter.queryUnordered
                MagicDrawUMLPort.scala  (3 usages found)
                    F:A_provided_port.queryUnordered
                    F:A_redefinedPort_port.queryUnordered
                    F:A_required_port.queryUnordered
                MagicDrawUMLProfile.scala  (2 usages found)
                    F:A_metaclassReference_profile.queryUnordered
                    F:A_metamodelReference_profile.queryUnordered
                MagicDrawUMLProperty.scala  (2 usages found)
                    F:A_redefinedProperty_property.queryUnordered
                    F:A_subsettedProperty_property.queryUnordered
                MagicDrawUMLProtocolTransition.scala  (1 usage found)
                    F:A_referred_protocolTransition.queryUnordered
                MagicDrawUMLReclassifyObjectAction.scala  (2 usages found)
                    F:A_newClassifier_reclassifyObjectAction.queryUnordered
                    F:A_oldClassifier_reclassifyObjectAction.queryUnordered
                MagicDrawUMLRedefinableElement.scala  (1 usage found)
                    F:A_redefinedElement_redefinableElement.queryUnordered
                MagicDrawUMLRedefinableTemplateSignature.scala  (2 usages found)
                    F:A_extendedSignature_redefinableTemplateSignature.queryUnordered
                    F:A_inheritedParameter_redefinableTemplateSignature.queryUnordered
                MagicDrawUMLRelationship.scala  (1 usage found)
                    F:A_relatedElement_relationship.queryUnordered
                MagicDrawUMLStateMachine.scala  (2 usages found)
                    F:A_extendedStateMachine_stateMachine.queryUnordered
                    F:A_submachineState_submachine.queryUnordered
                MagicDrawUMLStructuredClassifier.scala  (2 usages found)
                    F:A_part_structuredClassifier.queryUnordered
                    F:A_role_structuredClassifier.queryUnordered
                MagicDrawUMLTimeExpression.scala  (1 usage found)
                    F:A_observation_timeExpression.queryUnordered
                MagicDrawUMLTrigger.scala  (1 usage found)
                    F:A_port_trigger.queryUnordered
                MagicDrawUMLUseCase.scala  (1 usage found)
                    F:A_subject_useCase.queryUnordered
                MagicDrawUMLVertex.scala  (2 usages found)
                    F:A_incoming_target_vertex.queryUnordered
                    F:A_outgoing_source_vertex.queryUnordered

    * @return
    */
  def queryUnordered(source: S)
  : Set[UT]
  = scala.Predef.???

  /**
    * @usecase 60
                MagicDrawUMLActivityEdge.scala  (3 usages found)
                    R:A_interruptingEdge_interrupts.queryOppositeOptional
                    R:A_outgoing_source_node.queryOppositeOptional
                    R:A_incoming_target_node.queryOppositeOptional
                MagicDrawUMLActivityPartition.scala  (1 usage found)
                    R:A_partition_activity.queryOppositeOptional
                MagicDrawUMLAssociation.scala  (1 usage found)
                    R:A_association_clearAssociationAction.queryOppositeOptional
                MagicDrawUMLBehavior.scala  (2 usages found)
                    R:A_classifierBehavior_behavioredClassifier.queryOppositeOptional
                    R:A_method_specification.queryOppositeOptional
                MagicDrawUMLClassifier.scala  (1 usage found)
                    R:A_classifier_readExtentAction.queryOppositeOptional
                MagicDrawUMLClassifierTemplateParameter.scala  (1 usage found)
                    R:A_classifier_templateParameter_parameteredElement.queryOppositeOptional
                MagicDrawUMLCollaborationUse.scala  (1 usage found)
                    R:A_representation_classifier.queryOppositeOptional
                MagicDrawUMLConnectableElementTemplateParameter.scala  (1 usage found)
                    R:A_connectableElement_templateParameter_parameteredElement.queryOppositeOptional
                MagicDrawUMLConnectorEnd.scala  (1 usage found)
                    R:A_end_role.queryOppositeOptional
                MagicDrawUMLConstraint.scala  (9 usages found)
                    R:A_postcondition_behavior.queryOppositeOptional
                    R:A_precondition_behavior.queryOppositeOptional
                    R:A_preCondition_protocolTransition.queryOppositeOptional
                    R:A_guard_transition.queryOppositeOptional
                    R:A_bodyCondition_bodyContext.queryOppositeOptional
                    R:A_stateInvariant_owningState.queryOppositeOptional
                    R:A_postCondition_owningTransition.queryOppositeOptional
                    R:A_postcondition_postContext.queryOppositeOptional
                    R:A_precondition_preContext.queryOppositeOptional
                MagicDrawUMLElementImport.scala  (1 usage found)
                    R:A_metaclassReference_profile.queryOppositeOptional
                MagicDrawUMLExecutableNode.scala  (5 usages found)
                    R:A_body_clause.queryOppositeOptional
                    R:A_test_clause.queryOppositeOptional
                    R:A_bodyPart_loopNode.queryOppositeOptional
                    R:A_setupPart_loopNode.queryOppositeOptional
                    R:A_test_loopNode.queryOppositeOptional
                MagicDrawUMLExpansionNode.scala  (2 usages found)
                    R:A_inputElement_regionAsInput.queryOppositeOptional
                    R:A_outputElement_regionAsOutput.queryOppositeOptional
                MagicDrawUMLExtension.scala  (1 usage found)
                    R:A_extension_metaclass.queryOppositeOptional
                MagicDrawUMLFeature.scala  (1 usage found)
                    R:A_feature_featuringClassifier.queryOppositeOptional
                MagicDrawUMLGeneralizationSet.scala  (1 usage found)
                    R:A_powertypeExtent_powertype.queryOppositeOptional
                MagicDrawUMLGeneralOrdering.scala  (2 usages found)
                    R:A_toBefore_after.queryOppositeOptional
                    R:A_before_toAfter.queryOppositeOptional
                MagicDrawUMLInputPin.scala  (4 usages found)
                    R:A_insertAt_linkEndCreationData.queryOppositeOptional
                    R:A_value_linkEndData.queryOppositeOptional
                    R:A_destroyAt_linkEndDestructionData.queryOppositeOptional
                    R:A_value_qualifierValue.queryOppositeOptional
                MagicDrawUMLMessageEnd.scala  (2 usages found)
                    R:A_receiveEvent_endMessage.queryOppositeOptional
                    R:A_sendEvent_endMessage.queryOppositeOptional
                MagicDrawUMLObjectFlow.scala  (1 usage found)
                    R:A_decisionInputFlow_decisionNode.queryOppositeOptional
                MagicDrawUMLObservation.scala  (2 usages found)
                    R:A_observation_duration.queryOppositeOptional
                    R:A_observation_timeExpression.queryOppositeOptional
                MagicDrawUMLOperationTemplateParameter.scala  (1 usage found)
                    R:A_operation_templateParameter_parameteredElement.queryOppositeOptional
                MagicDrawUMLOutputPin.scala  (2 usages found)
                    R:A_decider_clause.queryOppositeOptional
                    R:A_decider_loopNode.queryOppositeOptional
                MagicDrawUMLPackageImport.scala  (1 usage found)
                    R:A_metamodelReference_profile.queryOppositeOptional
                MagicDrawUMLParameterableElement.scala  (1 usage found)
                    R:A_parameteredElement_templateParameter.queryOppositeOptional
                MagicDrawUMLPartDecomposition.scala  (1 usage found)
                    R:A_decomposedAs_lifeline.queryOppositeOptional
                MagicDrawUMLProperty.scala  (5 usages found)
                    R:A_navigableOwnedEnd_association.queryOppositeOptional
                    R:A_opposite_property.queryOppositeOptional
                    R:A_end_readLinkObjectEndAction.queryOppositeOptional
                    R:A_qualifier_readLinkObjectEndQualifierAction.queryOppositeOptional
                    R:A_part_structuredClassifier.queryOppositeOptional
                MagicDrawUMLPseudostate.scala  (2 usages found)
                    R:A_entry_connectionPointReference.queryOppositeOptional
                    R:A_exit_connectionPointReference.queryOppositeOptional
                MagicDrawUMLState.scala  (1 usage found)
                    R:A_submachineState_submachine.queryOppositeOptional
                MagicDrawUMLTransition.scala  (2 usages found)
                    R:A_outgoing_source_vertex.queryOppositeOptional
                    R:A_incoming_target_vertex.queryOppositeOptional
                MagicDrawUMLTrigger.scala  (1 usage found)
                    R:A_replyToCall_replyAction.queryOppositeOptional

    * @return
    */
  def queryOppositeOptional(target: T)
  : Option[US]
  = scala.Predef.???

  /**
    * @usecase 148
                MagicDrawUMLAction.scala  (1 usage found)
                    R:A_action_actionExecutionSpecification.queryOppositeUnordered
                MagicDrawUMLActivityEdge.scala  (4 usages found)
                    R:A_redefinedEdge_activityEdge.queryOppositeUnordered
                    R:A_realizingActivityEdge_informationFlow.queryOppositeUnordered
                    R:A_containedEdge_inGroup.queryOppositeUnordered
                    R:A_edge_inPartition.queryOppositeUnordered
                MagicDrawUMLActivityNode.scala  (2 usages found)
                    R:A_redefinedNode_activityNode.queryOppositeUnordered
                    R:A_containedNode_inGroup.queryOppositeUnordered
                MagicDrawUMLActivityPartition.scala  (1 usage found)
                    R:A_inPartition_node.queryOppositeUnordered
                MagicDrawUMLAssociation.scala  (1 usage found)
                    R:A_type_connector.queryOppositeUnordered
                MagicDrawUMLBehavior.scala  (10 usages found)
                    R:A_behavior_behaviorExecutionSpecification.queryOppositeUnordered
                    R:A_redefinedBehavior_behavior.queryOppositeUnordered
                    R:A_behavior_callBehaviorAction.queryOppositeUnordered
                    R:A_contract_connector.queryOppositeUnordered
                    R:A_decisionInput_decisionNode.queryOppositeUnordered
                    R:A_selection_objectFlow.queryOppositeUnordered
                    R:A_transformation_objectFlow.queryOppositeUnordered
                    R:A_selection_objectNode.queryOppositeUnordered
                    R:A_behavior_opaqueExpression.queryOppositeUnordered
                    R:A_reducer_reduceAction.queryOppositeUnordered
                MagicDrawUMLBehavioredClassifier.scala  (1 usage found)
                    R:A_context_behavior.queryOppositeUnordered
                MagicDrawUMLClass.scala  (1 usage found)
                    R:A_superClass_class.queryOppositeUnordered
                MagicDrawUMLClassifier.scala  (20 usages found)
                    R:A_context_action.queryOppositeUnordered
                    R:A_redefinedClassifier_classifier.queryOppositeUnordered
                    R:A_constrainingClassifier_classifierTemplateParameter.queryOppositeUnordered
                    R:A_realizingClassifier_componentRealization.queryOppositeUnordered
                    R:A_classifier_createObjectAction.queryOppositeUnordered
                    R:A_exceptionType_exceptionHandler.queryOppositeUnordered
                    R:A_general_generalization.queryOppositeUnordered
                    R:A_conveyed_conveyingFlow.queryOppositeUnordered
                    R:A_represented_representation.queryOppositeUnordered
                    R:A_classifier_readIsClassifiedObjectAction.queryOppositeUnordered
                    R:A_newClassifier_reclassifyObjectAction.queryOppositeUnordered
                    R:A_oldClassifier_reclassifyObjectAction.queryOppositeUnordered
                    R:A_redefinitionContext_region.queryOppositeUnordered
                    R:A_redefinitionContext_state.queryOppositeUnordered
                    R:A_contract_substitution.queryOppositeUnordered
                    R:A_redefinitionContext_transition.queryOppositeUnordered
                    R:A_unmarshallType_unmarshallAction.queryOppositeUnordered
                    R:A_general_classifier.queryOppositeUnordered
                    R:A_redefinitionContext_redefinableElement.queryOppositeUnordered
                    R:A_subject_useCase.queryOppositeUnordered
                MagicDrawUMLClause.scala  (1 usage found)
                    R:A_predecessorClause_successorClause.queryOppositeUnordered
                MagicDrawUMLCollaboration.scala  (1 usage found)
                    R:A_type_collaborationUse.queryOppositeUnordered
                MagicDrawUMLConnectableElement.scala  (3 usages found)
                    R:A_collaborationRole_collaboration.queryOppositeUnordered
                    R:A_represents_lifeline.queryOppositeUnordered
                    R:A_role_structuredClassifier.queryOppositeUnordered
                MagicDrawUMLConnector.scala  (3 usages found)
                    R:A_redefinedConnector_connector.queryOppositeUnordered
                    R:A_realizingConnector_informationFlow.queryOppositeUnordered
                    R:A_connector_message.queryOppositeUnordered
                MagicDrawUMLDeployedArtifact.scala  (1 usage found)
                    R:A_deployedArtifact_deploymentForArtifact.queryOppositeUnordered
                MagicDrawUMLDuration.scala  (2 usages found)
                    R:A_max_durationInterval.queryOppositeUnordered
                    R:A_min_durationInterval.queryOppositeUnordered
                MagicDrawUMLElement.scala  (7 usages found)
                    R:A_represents_activityPartition.queryOppositeUnordered
                    R:A_annotatedElement_comment.queryOppositeUnordered
                    R:MD_diagramOfContext_context.queryOppositeUnordered
                    R:MD_elementValueOfElement_element.queryOppositeUnordered
                    R:A_source_directedRelationship.queryOppositeUnordered
                    R:A_target_directedRelationship.queryOppositeUnordered
                    R:A_relatedElement_relationship.queryOppositeUnordered
                MagicDrawUMLEnumeration.scala  (1 usage found)
                    R:A_classifier_enumerationLiteral.queryOppositeUnordered
                MagicDrawUMLEvent.scala  (1 usage found)
                    R:A_event_trigger.queryOppositeUnordered
                MagicDrawUMLExecutableNode.scala  (1 usage found)
                    R:A_handlerBody_exceptionHandler.queryOppositeUnordered
                MagicDrawUMLExecutionSpecification.scala  (1 usage found)
                    R:A_execution_executionOccurrenceSpecification.queryOppositeUnordered
                MagicDrawUMLGeneralizationSet.scala  (1 usage found)
                    R:A_generalizationSet_generalization.queryOppositeUnordered
                MagicDrawUMLInstanceSpecification.scala  (1 usage found)
                    R:A_instance_instanceValue.queryOppositeUnordered
                MagicDrawUMLInteraction.scala  (1 usage found)
                    R:A_refersTo_interactionUse.queryOppositeUnordered
                MagicDrawUMLInterface.scala  (6 usages found)
                    R:A_provided_component.queryOppositeUnordered
                    R:A_required_component.queryOppositeUnordered
                    R:A_redefinedInterface_interface.queryOppositeUnordered
                    R:A_contract_interfaceRealization.queryOppositeUnordered
                    R:A_provided_port.queryOppositeUnordered
                    R:A_required_port.queryOppositeUnordered
                MagicDrawUMLInterruptibleActivityRegion.scala  (1 usage found)
                    R:A_inInterruptibleRegion_node.queryOppositeUnordered
                MagicDrawUMLLifeline.scala  (2 usages found)
                    R:A_covered_stateInvariant.queryOppositeUnordered
                    R:A_covered_coveredBy.queryOppositeUnordered
                MagicDrawUMLMessage.scala  (2 usages found)
                    R:A_realizingMessage_informationFlow.queryOppositeUnordered
                    R:A_message_messageEnd.queryOppositeUnordered
                MagicDrawUMLNamedElement.scala  (9 usages found)
                    R:A_message_considerIgnoreFragment.queryOppositeUnordered
                    R:A_informationSource_informationFlow.queryOppositeUnordered
                    R:A_informationTarget_informationFlow.queryOppositeUnordered
                    R:A_signature_message.queryOppositeUnordered
                    R:A_event_timeObservation.queryOppositeUnordered
                    R:A_clientDependency_client.queryOppositeUnordered
                    R:A_inheritedMember_inheritingClassifier.queryOppositeUnordered
                    R:A_member_memberNamespace.queryOppositeUnordered
                    R:A_supplier_supplierDependency.queryOppositeUnordered
                MagicDrawUMLObjectNode.scala  (1 usage found)
                    R:A_exceptionInput_exceptionHandler.queryOppositeUnordered
                MagicDrawUMLOccurrenceSpecification.scala  (2 usages found)
                    R:A_finish_executionSpecification.queryOppositeUnordered
                    R:A_start_executionSpecification.queryOppositeUnordered
                MagicDrawUMLOperation.scala  (4 usages found)
                    R:A_operation_callEvent.queryOppositeUnordered
                    R:A_operation_callOperationAction.queryOppositeUnordered
                    R:A_redefinedOperation_operation.queryOppositeUnordered
                    R:A_referred_protocolTransition.queryOppositeUnordered
                MagicDrawUMLPackage.scala  (2 usages found)
                    R:A_importedPackage_packageImport.queryOppositeUnordered
                    R:A_mergedPackage_packageMerge.queryOppositeUnordered
                MagicDrawUMLPackageableElement.scala  (4 usages found)
                    R:A_deployedElement_deploymentTarget.queryOppositeUnordered
                    R:A_importedElement_import.queryOppositeUnordered
                    R:A_utilizedElement_manifestation.queryOppositeUnordered
                    R:A_importedMember_namespace.queryOppositeUnordered
                MagicDrawUMLParameter.scala  (2 usages found)
                    R:A_parameter_activityParameterNode.queryOppositeUnordered
                    R:A_result_opaqueExpression.queryOppositeUnordered
                MagicDrawUMLParameterableElement.scala  (2 usages found)
                    R:A_default_templateParameter.queryOppositeUnordered
                    R:A_actual_templateParameterSubstitution.queryOppositeUnordered
                MagicDrawUMLParameterSet.scala  (1 usage found)
                    R:A_parameterSet_parameter.queryOppositeUnordered
                MagicDrawUMLPort.scala  (3 usages found)
                    R:A_onPort_invocationAction.queryOppositeUnordered
                    R:A_redefinedPort_port.queryOppositeUnordered
                    R:A_port_trigger.queryOppositeUnordered
                MagicDrawUMLProfile.scala  (2 usages found)
                    R:A_appliedProfile_profileApplication.queryOppositeUnordered
                    R:A_profile_stereotype.queryOppositeUnordered
                MagicDrawUMLProperty.scala  (7 usages found)
                    R:A_definingEnd_connectorEnd.queryOppositeUnordered
                    R:A_partWithPort_connectorEnd.queryOppositeUnordered
                    R:A_returnValueRecipient_interactionUse.queryOppositeUnordered
                    R:A_end_linkEndData.queryOppositeUnordered
                    R:A_redefinedProperty_property.queryOppositeUnordered
                    R:A_subsettedProperty_property.queryOppositeUnordered
                    R:A_qualifier_qualifierValue.queryOppositeUnordered
                MagicDrawUMLProtocolStateMachine.scala  (2 usages found)
                    R:A_protocol_port.queryOppositeUnordered
                    R:A_generalMachine_protocolConformance.queryOppositeUnordered
                MagicDrawUMLRedefinableElement.scala  (1 usage found)
                    R:A_redefinedElement_redefinableElement.queryOppositeUnordered
                MagicDrawUMLRedefinableTemplateSignature.scala  (1 usage found)
                    R:A_extendedSignature_redefinableTemplateSignature.queryOppositeUnordered
                MagicDrawUMLRegion.scala  (1 usage found)
                    R:A_extendedRegion_region.queryOppositeUnordered
                MagicDrawUMLRelationship.scala  (1 usage found)
                    R:A_realization_abstraction_flow.queryOppositeUnordered
                MagicDrawUMLSignal.scala  (4 usages found)
                    R:A_signal_broadcastSignalAction.queryOppositeUnordered
                    R:A_signal_reception.queryOppositeUnordered
                    R:A_signal_sendSignalAction.queryOppositeUnordered
                    R:A_signal_signalEvent.queryOppositeUnordered
                MagicDrawUMLState.scala  (2 usages found)
                    R:A_inState_objectNode.queryOppositeUnordered
                    R:A_redefinedState_state.queryOppositeUnordered
                MagicDrawUMLStateMachine.scala  (1 usage found)
                    R:A_extendedStateMachine_stateMachine.queryOppositeUnordered
                MagicDrawUMLStereotype.scala  (1 usage found)
                    R:A_type_extensionEnd.queryOppositeUnordered
                MagicDrawUMLStructuralFeature.scala  (2 usages found)
                    R:A_definingFeature_slot.queryOppositeUnordered
                    R:A_structuralFeature_structuralFeatureAction.queryOppositeUnordered
                MagicDrawUMLTemplateParameter.scala  (2 usages found)
                    R:A_inheritedParameter_redefinableTemplateSignature.queryOppositeUnordered
                    R:A_formal_templateParameterSubstitution.queryOppositeUnordered
                MagicDrawUMLTemplateSignature.scala  (1 usage found)
                    R:A_signature_templateBinding.queryOppositeUnordered
                MagicDrawUMLTimeExpression.scala  (2 usages found)
                    R:A_max_timeInterval.queryOppositeUnordered
                    R:A_min_timeInterval.queryOppositeUnordered
                MagicDrawUMLTransition.scala  (1 usage found)
                    R:A_redefinedTransition_transition.queryOppositeUnordered
                MagicDrawUMLType.scala  (5 usages found)
                    R:A_raisedException_behavioralFeature.queryOppositeUnordered
                    R:A_raisedException_operation.queryOppositeUnordered
                    R:A_type_operation.queryOppositeUnordered
                    R:A_type_typedElement.queryOppositeUnordered
                    R:A_endType_association.queryOppositeUnordered
                MagicDrawUMLUseCase.scala  (2 usages found)
                    R:A_extendedCase_extend.queryOppositeUnordered
                    R:A_addition_include.queryOppositeUnordered
                MagicDrawUMLValueSpecification.scala  (2 usages found)
                    R:A_max_interval.queryOppositeUnordered
                    R:A_min_interval.queryOppositeUnordered
                MagicDrawUMLVariable.scala  (1 usage found)
                    R:A_variable_variableAction.queryOppositeUnordered

    * @return
    */
  def queryOppositeUnordered(target: T)
  : Set[US]
  = scala.Predef.???

}