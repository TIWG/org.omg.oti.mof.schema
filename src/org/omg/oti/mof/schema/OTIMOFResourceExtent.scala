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
package org.omg.oti.mof.schema

import scala.collection.immutable._

/**
  * OTIMOFEntityExtent is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF resource.
  *
  * The `resourceIRI` is a foreign key identifying an OTI MOF Resource of some kind.
  *
  * A `resourceIRI` can be referenced as the importing resource
  * (@see [[OTIMOFResourceLibraryImport.importingResourceIRI]])
  *
  * A `resourceIRI` identifying an OTI MOF Library resource can be referenced as the imported resource
  * (@see [[OTIMOFResourceLibraryImport.importedLibrary]]
  */
sealed trait OTIMOFResourceExtent {
  val resource: OTIMOFResource
}

/**
  * The contents of an OTI MOF Library Resource
  *
  * @param resource
  * @param classifiers           Entity
  * @param attributes            Entity
  * @param featureLowerBounds    Characteristic
  * @param featureUpperBounds    Characteristic
  * @param featureOrdering       Characteristic
  * @param attribute2type        Relation
  * @param enumeration2literals  Relation
  * @param structure2attribute   Relation
  * @param generalizations       Relation
  * @param importedLibraries     Relation
  */
case class OTIMOFLibraryResourceExtent
( override val resource: OTIMOFLibrary,

  classifiers: Set[library.DatatypeClassifier] = Set(),
  attributes: Set[features.DataTypedFeature] = Set(),

  featureLowerBounds: Set[features.FeatureLowerBound] = Set(),
  featureUpperBounds: Set[features.FeatureUpperBound] = Set(),
  featureOrdering: Set[features.FeatureOrdering] = Set(),

  attribute2type: Set[features.AttributeProperty2Type] = Set(),
  enumeration2literals: Set[library.Enumeration2Literal] = Set(),
  structure2attribute: Set[library.StructuredDatatype2Attribute] = Set(),
  generalizations: Set[library.StructuredDataTypeGeneralization] = Set(),
  importedLibraries: Set[OTIMOFResourceLibraryImport] = Set())
  extends OTIMOFResourceExtent

/**
  * The contents of an OTI MOF Metamodel Resource
  *
  * @param resource
  * @param classifiers               Entity
  * @param associationEnds           Entity
  * @param attributes                Entity
  * @param featureLowerBounds        Characteristic
  * @param featureUpperBounds        Characteristic
  * @param featureOrdering           Characteristic
  * @param attribute2type            Relation
  * @param importedLibraries         Relation
  * @param association2source        Relation
  * @param association2Target        Relation
  * @param associationEnd2Metaclass  Relation
  * @param metaclass2attribute       Relation
  * @param generalizations           Relation
  * @param importedMetamodels        Relation
  */
case class OTIMOFMetamodelResourceExtent
( override val resource: OTIMOFMetamodel,

  classifiers: Set[metamodel.MetamodelClassifier] = Set(),
  associationEnds: Set[features.AssociationEndProperty] = Set(),
  attributes: Set[features.DataTypedFeature] = Set(),

  featureLowerBounds: Set[features.FeatureLowerBound] = Set(),
  featureUpperBounds: Set[features.FeatureUpperBound] = Set(),
  featureOrdering: Set[features.FeatureOrdering] = Set(),

  attribute2type: Set[features.AttributeProperty2Type] = Set(),
  importedLibraries: Set[OTIMOFResourceLibraryImport] = Set(),
  association2source: Set[metamodel.MetaAssociation2SourceEndProperty] = Set(),
  association2Target: Set[metamodel.MetaAssociation2TargetEndProperty] = Set(),
  associationEnd2Metaclass: Set[metamodel.MetaAssociationEndProperty2MetaClassType] = Set(),
  metaclass2attribute: Set[metamodel.MetaClass2Attribute] = Set(),
  generalizations: Set[metamodel.MetaClassifierGeneralization] = Set(),
  importedMetamodels: Set[OTIMOFResourceMetamodelImport] = Set())
  extends OTIMOFResourceExtent

/**
  * The contents of an OTI MOF Profile Resource
  *
  * @param resource
  * @param classifiers           Entity
  * @param attributes            Entity
  * @param featureLowerBounds    Characteristic
  * @param featureUpperBounds    Characteristic
  * @param featureOrdering       Characteristic
  * @param attribute2type        Relation
  * @param importedLibraries     Relation
  * @param generalizations       Relation
  * @param stereotype2attribute  Relation
  * @param stereotype2associationEndMetaClassProperty   Relation
  * @param stereotype2associationEndStereotypeProperty  Relation
  * @param importedProfiles      Relation
  */
case class OTIMOFProfileResourceExtent
( override val resource: OTIMOFProfile,

  classifiers: Set[profile.Stereotype] = Set(),
  attributes: Set[features.DataTypedFeature] = Set(),

  featureLowerBounds: Set[features.FeatureLowerBound] = Set(),
  featureUpperBounds: Set[features.FeatureUpperBound] = Set(),
  featureOrdering: Set[features.FeatureOrdering] = Set(),

  attribute2type: Set[features.AttributeProperty2Type] = Set(),
  importedLibraries: Set[OTIMOFResourceLibraryImport] = Set(),
  generalizations: Set[profile.StereotypeGeneralization] = Set(),
  stereotype2attribute: Set[profile.Stereotype2Attribute] = Set(),
  stereotype2associationEndMetaClassProperty: Set[profile.StereotypeAssociationTargetEndMetaClassProperty] = Set(),
  stereotype2associationEndStereotypeProperty: Set[profile.StereotypeAssociationTargetEndStereotypeProperty] = Set(),
  importedProfiles: Set[OTIMOFResourceProfileImport] = Set())
  extends OTIMOFResourceExtent

/**
  * The contents of an OTI MOF Model Resource
  *
  * @param resource
  * @param elements                             Element
  * @param links                                Relation
  * @param appliedStereotype                    Relation
  * @param appliedStereotypePropertyReferences  Relation
  * @param elementAttributeValues               Characteristic
  * @param instantiatedMetamodels               Relation
  * @param appliedProfiles                      Relation
  */
case class OTIMOFModelResourceExtent
( override val resource: OTIMOFModel,

  elements: Set[model.ModelElement],

  links: Set[model.ModelLink] = Set(),
  appliedStereotype: Set[model.AppliedStereotype] = Set(),
  appliedStereotypePropertyReferences: Set[model.AppliedStereotypePropertyReference] = Set(),
  elementAttributeValues: Set[model.ModelElementAttributeValue] = Set(),
  instantiatedMetamodels: Set[OTIMOFResourceInstantiatedMetamodel] = Set(),
  appliedProfiles: Set[OTIMOFResourceModelAppliedProfile] = Set())
  extends OTIMOFResourceExtent