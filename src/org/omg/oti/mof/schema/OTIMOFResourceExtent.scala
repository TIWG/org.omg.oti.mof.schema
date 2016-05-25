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

import play.json.extra._
import play.api.libs.json._

import scala.collection.immutable._
import scala.Predef.String

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
  * @param classifiers           Entity Schema::Library::DatatypeClassifier
  * @param dataTypedFeatures     Entity Schema::Features::DataTypedFeature
  * @param featureLowerBounds    Characteristic Schema::Features::FeatureLowerBound
  * @param featureUpperBounds    Characteristic Schema::Features::FeatureUpperBound
  * @param featureOrdering       Characteristic Schema::Features::FeatureOrdering
  * @param attribute2type        Relation Schema::Features::AttributeProperty2DataType
  * @param enumeration2literals  Relation Schema::Library::Enumeration2Literal
  * @param structure2attribute   Relation Schema::Library::StructuredDataType2Attribute
  * @param generalizations       Relation Schema::Library::Generalization
  * @param importedLibraries     Relation Schema::Resources::A_library_imports
  */
case class OTIMOFLibraryResourceExtent
(override val resource: OTIMOFLibrary,

 classifiers: Vector[library.DatatypeClassifier] = Vector(),
 dataTypedFeatures: Vector[features.DataTypedFeature] = Vector(),

 featureLowerBounds: Vector[features.FeatureLowerBound] = Vector(),
 featureUpperBounds: Vector[features.FeatureUpperBound] = Vector(),
 featureOrdering: Vector[features.FeatureOrdering] = Vector(),

 attribute2type: Vector[features.AttributeProperty2DataType] = Vector(),
 enumeration2literals: Vector[library.Enumeration2Literal] = Vector(),
 structure2attribute: Vector[library.StructuredDatatype2Attribute] = Vector(),
 generalizations: Vector[library.StructuredDataTypeGeneralization] = Vector(),
 importedLibraries: Vector[OTIMOFResourceLibraryImport] = Vector())
  extends OTIMOFResourceExtent

object OTIMOFLibraryResourceExtent {

  implicit val formats
  : Format[OTIMOFLibraryResourceExtent]
  = Json.format[OTIMOFLibraryResourceExtent]

}

/**
  * The contents of an OTI MOF Metamodel Resource
  *
  * @param resource
  * @param classifiers               Entity Schema::Metamodel::MetamodelClassifier
  * @param associationEnds           Entity Schema::Features::AssociationEnd
  * @param attributes                Entity Schema::Features::DataTypedAttributeProperty
  * @param featureLowerBounds        Characteristic Schema::Features::FeatureLowerBound
  * @param featureUpperBounds        Characteristic Schema::Features::FeatureUpperBound
  * @param featureOrdering           Characteristic Schema::Features::FeatureOrdering
  * @param attribute2type            Relation Schema::Features::AttributeProperty2DataType
  * @param importedLibraries         Relation Schema::Resources::A_library_imports
  * @param association2source        Relation Schema::Metamodel::MetaAssociation2SourceEndProperty
  * @param association2Target        Relation Schema::Metamodel::MetaAssociation2TargetEndProperty
  * @param associationEnd2Metaclass  Relation Schema::Metamodel::MetaAssociationEndProperty2MetaClass
  * @param metaclass2attribute       Relation Schema::Metamodel::MetaClass2Attribute
  * @param generalizations           Relation Schema::Metamodel::MetaClassifierGeneralization
  * @param importedMetamodels        Relation Schema::Resources::A_metamodel_imports
  */
case class OTIMOFMetamodelResourceExtent
(override val resource: OTIMOFMetamodel,

 classifiers: Vector[metamodel.MetamodelClassifier] = Vector(),
 associationEnds: Vector[features.AssociationEnd] = Vector(),
 attributes: Vector[features.DataTypedAttributeProperty] = Vector(),

 featureLowerBounds: Vector[features.FeatureLowerBound] = Vector(),
 featureUpperBounds: Vector[features.FeatureUpperBound] = Vector(),
 featureOrdering: Vector[features.FeatureOrdering] = Vector(),

 attribute2type: Vector[features.AttributeProperty2DataType] = Vector(),
 importedLibraries: Vector[OTIMOFResourceLibraryImport] = Vector(),
 association2source: Vector[metamodel.MetaAssociation2SourceEndProperty] = Vector(),
 association2Target: Vector[metamodel.MetaAssociation2TargetEndProperty] = Vector(),
 associationEnd2Metaclass: Vector[metamodel.MetaAssociationEndProperty2MetaClassType] = Vector(),
 metaclass2attribute: Vector[metamodel.MetaClass2Attribute] = Vector(),
 generalizations: Vector[metamodel.MetaClassifierGeneralization] = Vector(),
 importedMetamodels: Vector[OTIMOFResourceMetamodelImport] = Vector())
  extends OTIMOFResourceExtent

object OTIMOFMetamodelResourceExtent {

  implicit val formats
  : Format[OTIMOFMetamodelResourceExtent]
  = Json.format[OTIMOFMetamodelResourceExtent]

}

/**
  * The contents of an OTI MOF Profile Resource
  *
  * @param resource
  * @param classifiers           Entity Schema::Profile::Stereotype
  * @param attributes            Entity Schema::Features::DataTypedAttributeProperty
  * @param featureLowerBounds    Characteristic
  * @param featureUpperBounds    Characteristic
  * @param featureOrdering       Characteristic
  * @param attribute2type        Relation
  * @param importedLibraries     Relation
  * @param exendedMetamodels     Relation
  * @param generalizations       Relation
  * @param extendedMetaclass     Relation
  * @param stereotype2attribute  Relation
  * @param stereotype2associationEndMetaClassProperty   Relation
  * @param stereotype2associationEndStereotypeProperty  Relation
  * @param importedProfiles      Relation
  */
case class OTIMOFProfileResourceExtent
(override val resource: OTIMOFProfile,

 classifiers: Vector[profile.Stereotype] = Vector(),
 attributes: Vector[features.DataTypedAttributeProperty] = Vector(),

 featureLowerBounds: Vector[features.FeatureLowerBound] = Vector(),
 featureUpperBounds: Vector[features.FeatureUpperBound] = Vector(),
 featureOrdering: Vector[features.FeatureOrdering] = Vector(),

 attribute2type: Vector[features.AttributeProperty2DataType] = Vector(),
 importedLibraries: Vector[OTIMOFResourceLibraryImport] = Vector(),
 exendedMetamodels: Vector[profile.Profile2ExtendedMetamodel] = Vector(),
 generalizations: Vector[profile.StereotypeGeneralization] = Vector(),
 extendedMetaclass: Vector[profile.Stereotype2ExtendedMetaclass] = Vector(),
 stereotype2attribute: Vector[profile.Stereotype2Attribute] = Vector(),
 stereotype2associationEndMetaClassProperty: Vector[profile.StereotypeAssociationTargetEndMetaClassProperty] = Vector(),
 stereotype2associationEndStereotypeProperty: Vector[profile.StereotypeAssociationTargetEndStereotypeProperty] = Vector(),
 importedProfiles: Vector[OTIMOFResourceProfileImport] = Vector())
  extends OTIMOFResourceExtent

object OTIMOFProfileResourceExtent {

  implicit val formats
  : Format[OTIMOFProfileResourceExtent]
  = Json.format[OTIMOFProfileResourceExtent]

}

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

  elements: Vector[model.ModelElement] = Vector(),

  links: Vector[model.ModelLink] = Vector(),
  appliedStereotype: Vector[model.AppliedStereotype] = Vector(),
  appliedStereotypePropertyReferences: Vector[model.AppliedStereotypePropertyReference] = Vector(),
  elementAttributeValues: Vector[model.ModelElementAttributeValue] = Vector(),
  instantiatedMetamodels: Vector[OTIMOFResourceInstantiatedMetamodel] = Vector(),
  appliedProfiles: Vector[OTIMOFResourceModelAppliedProfile] = Vector())
  extends OTIMOFResourceExtent

object OTIMOFModelResourceExtent {

  implicit val formats
  : Format[OTIMOFModelResourceExtent]
  = Json.format[OTIMOFModelResourceExtent]

}

object OTIMOFResourceExtent {

  implicit val formats
  : Format[OTIMOFResourceExtent]
  = Variants.format[OTIMOFResourceExtent]((__ \ "type").format[String])

}