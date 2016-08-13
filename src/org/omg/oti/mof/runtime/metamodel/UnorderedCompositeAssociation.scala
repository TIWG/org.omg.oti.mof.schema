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

case class UnorderedCompositeAssociation[S <: runtime.model.ModelElement, US >: S, T <: runtime.model.ModelElement, UT >: T]
( override val metaAssociation: runtime.metamodel.MetaAssociation[S, US, T, UT, runtime.model.ModelUnorderedLink[S, T]] )
  extends Association[S,US,T,UT, runtime.model.ModelUnorderedLink[S, T]] {

  override def hashCode(): Int
  = metaAssociation.hashCode()

  /**
    * @usecase 4
                MagicDrawUMLCreateLinkAction.scala  (1 usage found)
                    F:A_endData_createLinkAction.queryIterable
                MagicDrawUMLDestroyLinkAction.scala  (1 usage found)
                    F:A_endData_destroyLinkAction.queryIterable
                MagicDrawUMLExtension.scala  (1 usage found)
                    F:A_ownedEnd_extension.queryIterable
                MagicDrawUMLLinkAction.scala  (1 usage found)
                    F:A_endData_linkAction.queryIterable

    * @return
    */
  def queryIterable(source: S)
  : Iterable[UT]
  = scala.Predef.???

  /**
    * @usecase 80
                MagicDrawUMLAbstraction.scala  (1 usage found)
                    F:A_mapping_abstraction.queryOptional
                MagicDrawUMLAcceptCallAction.scala  (1 usage found)
                    F:A_returnInformation_acceptCallAction.queryOptional
                MagicDrawUMLActionInputPin.scala  (1 usage found)
                    F:A_fromAction_actionInputPin.queryOptional
                MagicDrawUMLActivityEdge.scala  (2 usages found)
                    F:A_guard_activityEdge.queryOptional
                    F:A_weight_activityEdge.queryOptional
                MagicDrawUMLAddStructuralFeatureValueAction.scala  (1 usage found)
                    F:A_insertAt_addStructuralFeatureValueAction.queryOptional
                MagicDrawUMLAddVariableValueAction.scala  (1 usage found)
                    F:A_insertAt_addVariableValueAction.queryOptional
                MagicDrawUMLCallOperationAction.scala  (1 usage found)
                    F:A_target_callOperationAction.queryOptional
                MagicDrawUMLChangeEvent.scala  (1 usage found)
                    F:A_changeExpression_changeEvent.queryOptional
                MagicDrawUMLClassifier.scala  (1 usage found)
                    F:A_ownedTemplateSignature_classifier.queryOptional
                MagicDrawUMLClearAssociationAction.scala  (1 usage found)
                    F:A_object_clearAssociationAction.queryOptional
                MagicDrawUMLClearStructuralFeatureAction.scala  (1 usage found)
                    F:A_result_clearStructuralFeatureAction.queryOptional
                MagicDrawUMLConstraint.scala  (1 usage found)
                    F:A_specification_owningConstraint.queryOptional
                MagicDrawUMLCreateLinkObjectAction.scala  (1 usage found)
                    F:A_result_createLinkObjectAction.queryOptional
                MagicDrawUMLCreateObjectAction.scala  (1 usage found)
                    F:A_result_createObjectAction.queryOptional
                MagicDrawUMLDestroyObjectAction.scala  (1 usage found)
                    F:A_target_destroyObjectAction.queryOptional
                MagicDrawUMLDuration.scala  (1 usage found)
                    F:A_expr_duration.queryOptional
                MagicDrawUMLDurationConstraint.scala  (1 usage found)
                    F:A_specification_durationConstraint.queryOptional
                MagicDrawUMLExtend.scala  (1 usage found)
                    F:A_condition_extend.queryOptional
                MagicDrawUMLInstanceSpecification.scala  (1 usage found)
                    F:A_specification_owningInstanceSpec.queryOptional
                MagicDrawUMLInteractionConstraint.scala  (2 usages found)
                    F:A_maxint_interactionConstraint.queryOptional
                    F:A_minint_interactionConstraint.queryOptional
                MagicDrawUMLInteractionOperand.scala  (1 usage found)
                    F:A_guard_interactionOperand.queryOptional
                MagicDrawUMLInteractionUse.scala  (1 usage found)
                    F:A_returnValue_interactionUse.queryOptional
                MagicDrawUMLInterface.scala  (1 usage found)
                    F:A_protocol_interface.queryOptional
                MagicDrawUMLIntervalConstraint.scala  (1 usage found)
                    F:A_specification_intervalConstraint.queryOptional
                MagicDrawUMLJoinNode.scala  (1 usage found)
                    F:A_joinSpec_joinNode.queryOptional
                MagicDrawUMLLifeline.scala  (1 usage found)
                    F:A_selector_lifeline.queryOptional
                MagicDrawUMLMultiplicityElement.scala  (2 usages found)
                    F:A_lowerValue_owningLower.queryOptional
                    F:A_upperValue_owningUpper.queryOptional
                MagicDrawUMLNamedElement.scala  (1 usage found)
                    F:A_nameExpression_namedElement.queryOptional
                MagicDrawUMLObjectNode.scala  (1 usage found)
                    F:A_upperBound_objectNode.queryOptional
                MagicDrawUMLParameter.scala  (1 usage found)
                    F:A_defaultValue_owningParameter.queryOptional
                MagicDrawUMLProperty.scala  (1 usage found)
                    F:A_defaultValue_owningProperty.queryOptional
                MagicDrawUMLRaiseExceptionAction.scala  (1 usage found)
                    F:A_exception_raiseExceptionAction.queryOptional
                MagicDrawUMLReadExtentAction.scala  (1 usage found)
                    F:A_result_readExtentAction.queryOptional
                MagicDrawUMLReadIsClassifiedObjectAction.scala  (2 usages found)
                    F:A_object_readIsClassifiedObjectAction.queryOptional
                    F:A_result_readIsClassifiedObjectAction.queryOptional
                MagicDrawUMLReadLinkAction.scala  (1 usage found)
                    F:A_result_readLinkAction.queryOptional
                MagicDrawUMLReadLinkObjectEndAction.scala  (2 usages found)
                    F:A_object_readLinkObjectEndAction.queryOptional
                    F:A_result_readLinkObjectEndAction.queryOptional
                MagicDrawUMLReadLinkObjectEndQualifierAction.scala  (2 usages found)
                    F:A_object_readLinkObjectEndQualifierAction.queryOptional
                    F:A_result_readLinkObjectEndQualifierAction.queryOptional
                MagicDrawUMLReadSelfAction.scala  (1 usage found)
                    F:A_result_readSelfAction.queryOptional
                MagicDrawUMLReadStructuralFeatureAction.scala  (1 usage found)
                    F:A_result_readStructuralFeatureAction.queryOptional
                MagicDrawUMLReadVariableAction.scala  (1 usage found)
                    F:A_result_readVariableAction.queryOptional
                MagicDrawUMLReclassifyObjectAction.scala  (1 usage found)
                    F:A_object_reclassifyObjectAction.queryOptional
                MagicDrawUMLReduceAction.scala  (2 usages found)
                    F:A_collection_reduceAction.queryOptional
                    F:A_result_reduceAction.queryOptional
                MagicDrawUMLRemoveStructuralFeatureValueAction.scala  (1 usage found)
                    F:A_removeAt_removeStructuralFeatureValueAction.queryOptional
                MagicDrawUMLRemoveVariableValueAction.scala  (1 usage found)
                    F:A_removeAt_removeVariableValueAction.queryOptional
                MagicDrawUMLReplyAction.scala  (1 usage found)
                    F:A_returnInformation_replyAction.queryOptional
                MagicDrawUMLSendObjectAction.scala  (2 usages found)
                    F:A_request_sendObjectAction.queryOptional
                    F:A_target_sendObjectAction.queryOptional
                MagicDrawUMLSendSignalAction.scala  (1 usage found)
                    F:A_target_sendSignalAction.queryOptional
                MagicDrawUMLStartClassifierBehaviorAction.scala  (1 usage found)
                    F:A_object_startClassifierBehaviorAction.queryOptional
                MagicDrawUMLStartObjectBehaviorAction.scala  (1 usage found)
                    F:A_object_startObjectBehaviorAction.queryOptional
                MagicDrawUMLState.scala  (3 usages found)
                    F:A_doActivity_state.queryOptional
                    F:A_entry_state.queryOptional
                    F:A_exit_state.queryOptional
                MagicDrawUMLStateInvariant.scala  (1 usage found)
                    F:A_invariant_stateInvariant.queryOptional
                MagicDrawUMLStructuralFeatureAction.scala  (1 usage found)
                    F:A_object_structuralFeatureAction.queryOptional
                MagicDrawUMLTemplateableElement.scala  (1 usage found)
                    F:A_ownedTemplateSignature_template.queryOptional
                MagicDrawUMLTemplateParameter.scala  (2 usages found)
                    F:A_ownedDefault_templateParameter.queryOptional
                    F:A_ownedParameteredElement_owningTemplateParameter.queryOptional
                MagicDrawUMLTemplateParameterSubstitution.scala  (1 usage found)
                    F:A_ownedActual_owningTemplateParameterSubstitution.queryOptional
                MagicDrawUMLTestIdentityAction.scala  (3 usages found)
                    F:A_first_testIdentityAction.queryOptional
                    F:A_result_testIdentityAction.queryOptional
                    F:A_second_testIdentityAction.queryOptional
                MagicDrawUMLTimeConstraint.scala  (1 usage found)
                    F:A_specification_timeConstraint.queryOptional
                MagicDrawUMLTimeEvent.scala  (1 usage found)
                    F:A_when_timeEvent.queryOptional
                MagicDrawUMLTimeExpression.scala  (1 usage found)
                    F:A_expr_timeExpression.queryOptional
                MagicDrawUMLTransition.scala  (1 usage found)
                    F:A_effect_transition.queryOptional
                MagicDrawUMLUnmarshallAction.scala  (1 usage found)
                    F:A_object_unmarshallAction.queryOptional
                MagicDrawUMLValuePin.scala  (1 usage found)
                    F:A_value_valuePin.queryOptional
                MagicDrawUMLValueSpecificationAction.scala  (2 usages found)
                    F:A_result_valueSpecificationAction.queryOptional
                    F:A_value_valueSpecificationAction.queryOptional
                MagicDrawUMLWriteStructuralFeatureAction.scala  (2 usages found)
                    F:A_result_writeStructuralFeatureAction.queryOptional
                    F:A_value_writeStructuralFeatureAction.queryOptional
                MagicDrawUMLWriteVariableAction.scala  (1 usage found)
                    F:A_value_writeVariableAction.queryOptional

    * @return
    */
  def queryOptional(source: S)
  : Option[UT]
  = scala.Predef.???

  /**
    * @usecase 79
                MagicDrawUMLAcceptEventAction.scala  (1 usage found)
                    F:A_trigger_acceptEventAction.queryUnordered
                MagicDrawUMLAction.scala  (2 usages found)
                    F:A_localPostcondition_action.queryUnordered
                    F:A_localPrecondition_action.queryUnordered
                MagicDrawUMLActivity.scala  (5 usages found)
                    F:A_edge_activity.queryUnordered
                    F:A_group_inActivity.queryUnordered
                    F:A_node_activity.queryUnordered
                    F:A_structuredNode_activity.queryUnordered
                    F:A_variable_activityScope.queryUnordered
                MagicDrawUMLActivityGroup.scala  (1 usage found)
                    F:A_subgroup_superGroup.queryUnordered
                MagicDrawUMLActivityPartition.scala  (1 usage found)
                    F:A_subpartition_superPartition.queryUnordered
                MagicDrawUMLArtifact.scala  (2 usages found)
                    F:A_manifestation_artifact.queryUnordered
                    F:A_nestedArtifact_artifact.queryUnordered
                MagicDrawUMLBehavior.scala  (1 usage found)
                    F:A_ownedParameterSet_behavior.queryUnordered
                MagicDrawUMLBehavioralFeature.scala  (1 usage found)
                    F:A_ownedParameterSet_behavioralFeature.queryUnordered
                MagicDrawUMLBehavioredClassifier.scala  (2 usages found)
                    F:A_interfaceRealization_implementingClassifier.queryUnordered
                    F:A_ownedBehavior_behavioredClassifier.queryUnordered
                MagicDrawUMLClass.scala  (1 usage found)
                    F:A_ownedReception_class.queryUnordered
                MagicDrawUMLClassifier.scala  (4 usages found)
                    F:A_collaborationUse_classifier.queryUnordered
                    F:A_generalization_specific.queryUnordered
                    F:A_ownedUseCase_classifier.queryUnordered
                    F:A_substitution_substitutingClassifier.queryUnordered
                MagicDrawUMLCollaborationUse.scala  (1 usage found)
                    F:A_roleBinding_collaborationUse.queryUnordered
                MagicDrawUMLCombinedFragment.scala  (1 usage found)
                    F:A_cfragmentGate_combinedFragment.queryUnordered
                MagicDrawUMLComponent.scala  (2 usages found)
                    F:A_packagedElement_component.queryUnordered
                    F:A_realization_abstraction_component.queryUnordered
                MagicDrawUMLConditionalNode.scala  (1 usage found)
                    F:A_clause_conditionalNode.queryUnordered
                MagicDrawUMLDeployment.scala  (1 usage found)
                    F:A_configuration_deployment.queryUnordered
                MagicDrawUMLDeploymentTarget.scala  (1 usage found)
                    F:A_deployment_location.queryUnordered
                MagicDrawUMLElement.scala  (2 usages found)
                    F:A_ownedComment_owningElement.queryUnordered
                    F:A_ownedElement_owner.queryUnordered
                MagicDrawUMLEncapsulatedClassifier.scala  (1 usage found)
                    F:A_ownedPort_encapsulatedClassifier.queryUnordered
                MagicDrawUMLExecutableNode.scala  (1 usage found)
                    F:A_handler_protectedNode.queryUnordered
                MagicDrawUMLInstanceSpecification.scala  (1 usage found)
                    F:A_slot_owningInstance.queryUnordered
                MagicDrawUMLInteraction.scala  (4 usages found)
                    F:A_action_interaction.queryUnordered
                    F:A_formalGate_interaction.queryUnordered
                    F:A_lifeline_interaction.queryUnordered
                    F:A_message_interaction.queryUnordered
                MagicDrawUMLInteractionFragment.scala  (1 usage found)
                    F:A_generalOrdering_interactionFragment.queryUnordered
                MagicDrawUMLInteractionUse.scala  (1 usage found)
                    F:A_actualGate_interactionUse.queryUnordered
                MagicDrawUMLInterface.scala  (1 usage found)
                    F:A_ownedReception_interface.queryUnordered
                MagicDrawUMLLinkAction.scala  (1 usage found)
                    F:A_inputValue_linkAction.queryUnordered
                MagicDrawUMLLinkEndData.scala  (1 usage found)
                    F:A_qualifier_linkEndData.queryUnordered
                MagicDrawUMLNamespace.scala  (5 usages found)
                    F:A_elementImport_importingNamespace.queryUnordered
                    F:MD_ownerOfDiagram_ownedDiagram.queryUnordered
                    F:A_ownedMember_namespace.queryUnordered
                    F:A_ownedRule_context.queryUnordered
                    F:A_packageImport_importingNamespace.queryUnordered
                MagicDrawUMLNode.scala  (1 usage found)
                    F:A_nestedNode_node.queryUnordered
                MagicDrawUMLOpaqueAction.scala  (2 usages found)
                    F:A_inputValue_opaqueAction.queryUnordered
                    F:A_outputValue_opaqueAction.queryUnordered
                MagicDrawUMLPackage.scala  (6 usages found)
                    F:A_nestedPackage_nestingPackage.queryUnordered
                    F:A_ownedStereotype_owningPackage.queryUnordered
                    F:A_ownedType_package.queryUnordered
                    F:A_packageMerge_receivingPackage.queryUnordered
                    F:A_packagedElement_owningPackage.queryUnordered
                    F:A_profileApplication_applyingPackage.queryUnordered
                MagicDrawUMLParameterSet.scala  (1 usage found)
                    F:A_condition_parameterSet.queryUnordered
                MagicDrawUMLProtocolStateMachine.scala  (1 usage found)
                    F:A_conformance_specificMachine.queryUnordered
                MagicDrawUMLRegion.scala  (2 usages found)
                    F:A_subvertex_container.queryUnordered
                    F:A_transition_container.queryUnordered
                MagicDrawUMLState.scala  (4 usages found)
                    F:A_connection_state.queryUnordered
                    F:A_connectionPoint_state.queryUnordered
                    F:A_deferrableTrigger_state.queryUnordered
                    F:A_region_state.queryUnordered
                MagicDrawUMLStateMachine.scala  (2 usages found)
                    F:A_connectionPoint_stateMachine.queryUnordered
                    F:A_region_stateMachine.queryUnordered
                MagicDrawUMLStereotype.scala  (1 usage found)
                    F:A_icon_stereotype.queryUnordered
                MagicDrawUMLStructuredActivityNode.scala  (5 usages found)
                    F:A_edge_inStructuredNode.queryUnordered
                    F:A_node_inStructuredNode.queryUnordered
                    F:A_structuredNodeInput_structuredActivityNode.queryUnordered
                    F:A_structuredNodeOutput_structuredActivityNode.queryUnordered
                    F:A_variable_scope.queryUnordered
                MagicDrawUMLStructuredClassifier.scala  (1 usage found)
                    F:A_ownedConnector_structuredClassifier.queryUnordered
                MagicDrawUMLTemplateableElement.scala  (1 usage found)
                    F:A_templateBinding_boundElement.queryUnordered
                MagicDrawUMLTemplateBinding.scala  (1 usage found)
                    F:A_parameterSubstitution_templateBinding.queryUnordered
                MagicDrawUMLTransition.scala  (1 usage found)
                    F:A_trigger_transition.queryUnordered
                MagicDrawUMLUseCase.scala  (3 usages found)
                    F:A_extend_extension.queryUnordered
                    F:A_extensionPoint_useCase.queryUnordered
                    F:A_include_includingCase.queryUnordered

    * @return
    */
  def queryUnordered(source: S)
  : Set[UT]
  = scala.Predef.???

  /**
    * @usecase 163
                MagicDrawUMLAction.scala  (2 usages found)
                    R:A_fromAction_actionInputPin.queryOppositeOptional
                    R:A_action_interaction.queryOppositeOptional
                MagicDrawUMLActivityEdge.scala  (2 usages found)
                    R:A_edge_activity.queryOppositeOptional
                    R:A_edge_inStructuredNode.queryOppositeOptional
                MagicDrawUMLActivityGroup.scala  (2 usages found)
                    R:A_group_inActivity.queryOppositeOptional
                    R:A_subgroup_superGroup.queryOppositeOptional
                MagicDrawUMLActivityNode.scala  (2 usages found)
                    R:A_node_activity.queryOppositeOptional
                    R:A_node_inStructuredNode.queryOppositeOptional
                MagicDrawUMLActivityPartition.scala  (1 usage found)
                    R:A_subpartition_superPartition.queryOppositeOptional
                MagicDrawUMLArtifact.scala  (1 usage found)
                    R:A_nestedArtifact_artifact.queryOppositeOptional
                MagicDrawUMLBehavior.scala  (5 usages found)
                    R:A_ownedBehavior_behavioredClassifier.queryOppositeOptional
                    R:A_doActivity_state.queryOppositeOptional
                    R:A_entry_state.queryOppositeOptional
                    R:A_exit_state.queryOppositeOptional
                    R:A_effect_transition.queryOppositeOptional
                MagicDrawUMLClause.scala  (1 usage found)
                    R:A_clause_conditionalNode.queryOppositeOptional
                MagicDrawUMLCollaborationUse.scala  (1 usage found)
                    R:A_collaborationUse_classifier.queryOppositeOptional
                MagicDrawUMLComment.scala  (1 usage found)
                    R:A_ownedComment_owningElement.queryOppositeOptional
                MagicDrawUMLComponentRealization.scala  (1 usage found)
                    R:A_realization_abstraction_component.queryOppositeOptional
                MagicDrawUMLConnectionPointReference.scala  (1 usage found)
                    R:A_connection_state.queryOppositeOptional
                MagicDrawUMLConnector.scala  (1 usage found)
                    R:A_ownedConnector_structuredClassifier.queryOppositeOptional
                MagicDrawUMLConstraint.scala  (6 usages found)
                    R:A_localPostcondition_action.queryOppositeOptional
                    R:A_localPrecondition_action.queryOppositeOptional
                    R:A_condition_extend.queryOppositeOptional
                    R:A_condition_parameterSet.queryOppositeOptional
                    R:A_invariant_stateInvariant.queryOppositeOptional
                    R:A_ownedRule_context.queryOppositeOptional
                MagicDrawUMLDependency.scala  (1 usage found)
                    R:A_roleBinding_collaborationUse.queryOppositeOptional
                MagicDrawUMLDeployment.scala  (1 usage found)
                    R:A_deployment_location.queryOppositeOptional
                MagicDrawUMLDeploymentSpecification.scala  (1 usage found)
                    R:A_configuration_deployment.queryOppositeOptional
                MagicDrawUMLDiagram.scala  (1 usage found)
                    R:MD_ownerOfDiagram_ownedDiagram.queryOppositeOptional
                MagicDrawUMLDurationInterval.scala  (1 usage found)
                    R:A_specification_durationConstraint.queryOppositeOptional
                MagicDrawUMLElement.scala  (1 usage found)
                    R:A_ownedElement_owner.queryOppositeOptional
                MagicDrawUMLElementImport.scala  (1 usage found)
                    R:A_elementImport_importingNamespace.queryOppositeOptional
                MagicDrawUMLExceptionHandler.scala  (1 usage found)
                    R:A_handler_protectedNode.queryOppositeOptional
                MagicDrawUMLExtend.scala  (1 usage found)
                    R:A_extend_extension.queryOppositeOptional
                MagicDrawUMLExtensionEnd.scala  (1 usage found)
                    R:A_ownedEnd_extension.queryOppositeOptional
                MagicDrawUMLExtensionPoint.scala  (1 usage found)
                    R:A_extensionPoint_useCase.queryOppositeOptional
                MagicDrawUMLGate.scala  (3 usages found)
                    R:A_cfragmentGate_combinedFragment.queryOppositeOptional
                    R:A_formalGate_interaction.queryOppositeOptional
                    R:A_actualGate_interactionUse.queryOppositeOptional
                MagicDrawUMLGeneralization.scala  (1 usage found)
                    R:A_generalization_specific.queryOppositeOptional
                MagicDrawUMLGeneralOrdering.scala  (1 usage found)
                    R:A_generalOrdering_interactionFragment.queryOppositeOptional
                MagicDrawUMLImage.scala  (1 usage found)
                    R:A_icon_stereotype.queryOppositeOptional
                MagicDrawUMLInclude.scala  (1 usage found)
                    R:A_include_includingCase.queryOppositeOptional
                MagicDrawUMLInputPin.scala  (28 usages found)
                    R:A_insertAt_addStructuralFeatureValueAction.queryOppositeOptional
                    R:A_insertAt_addVariableValueAction.queryOppositeOptional
                    R:A_target_callOperationAction.queryOppositeOptional
                    R:A_object_clearAssociationAction.queryOppositeOptional
                    R:A_target_destroyObjectAction.queryOppositeOptional
                    R:A_inputValue_linkAction.queryOppositeOptional
                    R:A_inputValue_opaqueAction.queryOppositeOptional
                    R:A_exception_raiseExceptionAction.queryOppositeOptional
                    R:A_object_readIsClassifiedObjectAction.queryOppositeOptional
                    R:A_object_readLinkObjectEndAction.queryOppositeOptional
                    R:A_object_readLinkObjectEndQualifierAction.queryOppositeOptional
                    R:A_object_reclassifyObjectAction.queryOppositeOptional
                    R:A_collection_reduceAction.queryOppositeOptional
                    R:A_removeAt_removeStructuralFeatureValueAction.queryOppositeOptional
                    R:A_removeAt_removeVariableValueAction.queryOppositeOptional
                    R:A_returnInformation_replyAction.queryOppositeOptional
                    R:A_request_sendObjectAction.queryOppositeOptional
                    R:A_target_sendObjectAction.queryOppositeOptional
                    R:A_target_sendSignalAction.queryOppositeOptional
                    R:A_object_startClassifierBehaviorAction.queryOppositeOptional
                    R:A_object_startObjectBehaviorAction.queryOppositeOptional
                    R:A_object_structuralFeatureAction.queryOppositeOptional
                    R:A_structuredNodeInput_structuredActivityNode.queryOppositeOptional
                    R:A_first_testIdentityAction.queryOppositeOptional
                    R:A_second_testIdentityAction.queryOppositeOptional
                    R:A_object_unmarshallAction.queryOppositeOptional
                    R:A_value_writeStructuralFeatureAction.queryOppositeOptional
                    R:A_value_writeVariableAction.queryOppositeOptional
                MagicDrawUMLInteractionConstraint.scala  (1 usage found)
                    R:A_guard_interactionOperand.queryOppositeOptional
                MagicDrawUMLInterfaceRealization.scala  (1 usage found)
                    R:A_interfaceRealization_implementingClassifier.queryOppositeOptional
                MagicDrawUMLInterval.scala  (1 usage found)
                    R:A_specification_intervalConstraint.queryOppositeOptional
                MagicDrawUMLLifeline.scala  (1 usage found)
                    R:A_lifeline_interaction.queryOppositeOptional
                MagicDrawUMLLinkEndCreationData.scala  (1 usage found)
                    R:A_endData_createLinkAction.queryOppositeOptional
                MagicDrawUMLLinkEndData.scala  (1 usage found)
                    R:A_endData_linkAction.queryOppositeOptional
                MagicDrawUMLLinkEndDestructionData.scala  (1 usage found)
                    R:A_endData_destroyLinkAction.queryOppositeOptional
                MagicDrawUMLManifestation.scala  (1 usage found)
                    R:A_manifestation_artifact.queryOppositeOptional
                MagicDrawUMLMessage.scala  (1 usage found)
                    R:A_message_interaction.queryOppositeOptional
                MagicDrawUMLNamedElement.scala  (1 usage found)
                    R:A_ownedMember_namespace.queryOppositeOptional
                MagicDrawUMLNode.scala  (1 usage found)
                    R:A_nestedNode_node.queryOppositeOptional
                MagicDrawUMLOpaqueExpression.scala  (1 usage found)
                    R:A_mapping_abstraction.queryOppositeOptional
                MagicDrawUMLOutputPin.scala  (18 usages found)
                    R:A_returnInformation_acceptCallAction.queryOppositeOptional
                    R:A_result_clearStructuralFeatureAction.queryOppositeOptional
                    R:A_result_createLinkObjectAction.queryOppositeOptional
                    R:A_result_createObjectAction.queryOppositeOptional
                    R:A_outputValue_opaqueAction.queryOppositeOptional
                    R:A_result_readExtentAction.queryOppositeOptional
                    R:A_result_readIsClassifiedObjectAction.queryOppositeOptional
                    R:A_result_readLinkAction.queryOppositeOptional
                    R:A_result_readLinkObjectEndAction.queryOppositeOptional
                    R:A_result_readLinkObjectEndQualifierAction.queryOppositeOptional
                    R:A_result_readSelfAction.queryOppositeOptional
                    R:A_result_readStructuralFeatureAction.queryOppositeOptional
                    R:A_result_readVariableAction.queryOppositeOptional
                    R:A_result_reduceAction.queryOppositeOptional
                    R:A_structuredNodeOutput_structuredActivityNode.queryOppositeOptional
                    R:A_result_testIdentityAction.queryOppositeOptional
                    R:A_result_valueSpecificationAction.queryOppositeOptional
                    R:A_result_writeStructuralFeatureAction.queryOppositeOptional
                MagicDrawUMLPackage.scala  (1 usage found)
                    R:A_nestedPackage_nestingPackage.queryOppositeOptional
                MagicDrawUMLPackageableElement.scala  (2 usages found)
                    R:A_packagedElement_component.queryOppositeOptional
                    R:A_packagedElement_owningPackage.queryOppositeOptional
                MagicDrawUMLPackageImport.scala  (1 usage found)
                    R:A_packageImport_importingNamespace.queryOppositeOptional
                MagicDrawUMLPackageMerge.scala  (1 usage found)
                    R:A_packageMerge_receivingPackage.queryOppositeOptional
                MagicDrawUMLParameterableElement.scala  (3 usages found)
                    R:A_ownedDefault_templateParameter.queryOppositeOptional
                    R:A_ownedActual_owningTemplateParameterSubstitution.queryOppositeOptional
                    R:A_ownedParameteredElement_owningTemplateParameter.queryOppositeOptional
                MagicDrawUMLParameterSet.scala  (2 usages found)
                    R:A_ownedParameterSet_behavior.queryOppositeOptional
                    R:A_ownedParameterSet_behavioralFeature.queryOppositeOptional
                MagicDrawUMLPort.scala  (1 usage found)
                    R:A_ownedPort_encapsulatedClassifier.queryOppositeOptional
                MagicDrawUMLProfileApplication.scala  (1 usage found)
                    R:A_profileApplication_applyingPackage.queryOppositeOptional
                MagicDrawUMLProtocolConformance.scala  (1 usage found)
                    R:A_conformance_specificMachine.queryOppositeOptional
                MagicDrawUMLProtocolStateMachine.scala  (1 usage found)
                    R:A_protocol_interface.queryOppositeOptional
                MagicDrawUMLPseudostate.scala  (2 usages found)
                    R:A_connectionPoint_state.queryOppositeOptional
                    R:A_connectionPoint_stateMachine.queryOppositeOptional
                MagicDrawUMLQualifierValue.scala  (1 usage found)
                    R:A_qualifier_linkEndData.queryOppositeOptional
                MagicDrawUMLReception.scala  (2 usages found)
                    R:A_ownedReception_class.queryOppositeOptional
                    R:A_ownedReception_interface.queryOppositeOptional
                MagicDrawUMLRedefinableTemplateSignature.scala  (1 usage found)
                    R:A_ownedTemplateSignature_classifier.queryOppositeOptional
                MagicDrawUMLRegion.scala  (2 usages found)
                    R:A_region_state.queryOppositeOptional
                    R:A_region_stateMachine.queryOppositeOptional
                MagicDrawUMLSlot.scala  (1 usage found)
                    R:A_slot_owningInstance.queryOppositeOptional
                MagicDrawUMLStereotype.scala  (1 usage found)
                    R:A_ownedStereotype_owningPackage.queryOppositeOptional
                MagicDrawUMLStringExpression.scala  (1 usage found)
                    R:A_nameExpression_namedElement.queryOppositeOptional
                MagicDrawUMLStructuredActivityNode.scala  (1 usage found)
                    R:A_structuredNode_activity.queryOppositeOptional
                MagicDrawUMLSubstitution.scala  (1 usage found)
                    R:A_substitution_substitutingClassifier.queryOppositeOptional
                MagicDrawUMLTemplateBinding.scala  (1 usage found)
                    R:A_templateBinding_boundElement.queryOppositeOptional
                MagicDrawUMLTemplateParameterSubstitution.scala  (1 usage found)
                    R:A_parameterSubstitution_templateBinding.queryOppositeOptional
                MagicDrawUMLTemplateSignature.scala  (1 usage found)
                    R:A_ownedTemplateSignature_template.queryOppositeOptional
                MagicDrawUMLTimeExpression.scala  (1 usage found)
                    R:A_when_timeEvent.queryOppositeOptional
                MagicDrawUMLTimeInterval.scala  (1 usage found)
                    R:A_specification_timeConstraint.queryOppositeOptional
                MagicDrawUMLTransition.scala  (1 usage found)
                    R:A_transition_container.queryOppositeOptional
                MagicDrawUMLTrigger.scala  (3 usages found)
                    R:A_trigger_acceptEventAction.queryOppositeOptional
                    R:A_deferrableTrigger_state.queryOppositeOptional
                    R:A_trigger_transition.queryOppositeOptional
                MagicDrawUMLType.scala  (1 usage found)
                    R:A_ownedType_package.queryOppositeOptional
                MagicDrawUMLUseCase.scala  (1 usage found)
                    R:A_ownedUseCase_classifier.queryOppositeOptional
                MagicDrawUMLValueSpecification.scala  (19 usages found)
                    R:A_guard_activityEdge.queryOppositeOptional
                    R:A_weight_activityEdge.queryOppositeOptional
                    R:A_changeExpression_changeEvent.queryOppositeOptional
                    R:A_expr_duration.queryOppositeOptional
                    R:A_maxint_interactionConstraint.queryOppositeOptional
                    R:A_minint_interactionConstraint.queryOppositeOptional
                    R:A_returnValue_interactionUse.queryOppositeOptional
                    R:A_joinSpec_joinNode.queryOppositeOptional
                    R:A_selector_lifeline.queryOppositeOptional
                    R:A_upperBound_objectNode.queryOppositeOptional
                    R:A_expr_timeExpression.queryOppositeOptional
                    R:A_value_valuePin.queryOppositeOptional
                    R:A_value_valueSpecificationAction.queryOppositeOptional
                    R:A_specification_owningConstraint.queryOppositeOptional
                    R:A_specification_owningInstanceSpec.queryOppositeOptional
                    R:A_lowerValue_owningLower.queryOppositeOptional
                    R:A_defaultValue_owningParameter.queryOppositeOptional
                    R:A_defaultValue_owningProperty.queryOppositeOptional
                    R:A_upperValue_owningUpper.queryOppositeOptional
                MagicDrawUMLVariable.scala  (2 usages found)
                    R:A_variable_activityScope.queryOppositeOptional
                    R:A_variable_scope.queryOppositeOptional
                MagicDrawUMLVertex.scala  (1 usage found)
                    R:A_subvertex_container.queryOppositeOptional

    * @return
    */
  def queryOppositeOptional(target: T)
  : Option[US]
  = scala.Predef.???

}