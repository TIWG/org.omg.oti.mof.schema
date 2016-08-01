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
  * (@see [[OTIMOFResourceLibraryImport.importingResource]])
  *
  * A `resourceIRI` identifying an OTI MOF Library resource can be referenced as the imported resource
  * (@see [[OTIMOFResourceLibraryImport.importedLibrary]]
  * @group resources
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
  * @group library
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

/**
  * @group library
  */
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
  * @param association2target        Relation Schema::Metamodel::MetaAssociation2TargetEndProperty
  * @param associationEnd2Metaclass  Relation Schema::Metamodel::MetaAssociationEndProperty2MetaClass
  * @param metaclass2orderedAtomicAttribute       Relation Schema::Metamodel::MetaClass2Attribute
  * @param metaclass2orderedEnumerationAttribute       Relation Schema::Metamodel::MetaClass2Attribute
  * @param metaclass2orderedStructuredAttribute       Relation Schema::Metamodel::MetaClass2Attribute
  * @param metaclass2unorderedAtomicAttribute       Relation Schema::Metamodel::MetaClass2Attribute
  * @param metaclass2unorderedEnumerationAttribute       Relation Schema::Metamodel::MetaClass2Attribute
  * @param metaclass2unorderedStructuredAttribute       Relation Schema::Metamodel::MetaClass2Attribute
  * @param generalizations           Relation Schema::Metamodel::MetaClassifierGeneralization
  * @param importedMetamodels        Relation Schema::Resources::A_metamodel_imports
  * @group metamodel
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
 association2target: Vector[metamodel.MetaAssociation2TargetEndProperty] = Vector(),
 associationEnd2Metaclass: Vector[metamodel.MetaAssociationEndProperty2MetaClassType] = Vector(),
 metaclass2orderedAtomicAttribute: Vector[metamodel.MetaClass2Attribute] = Vector(),
 metaclass2orderedEnumerationAttribute: Vector[metamodel.MetaClass2Attribute] = Vector(),
 metaclass2orderedStructuredAttribute: Vector[metamodel.MetaClass2Attribute] = Vector(),
 metaclass2unorderedAtomicAttribute: Vector[metamodel.MetaClass2Attribute] = Vector(),
 metaclass2unorderedEnumerationAttribute: Vector[metamodel.MetaClass2Attribute] = Vector(),
 metaclass2unorderedStructuredAttribute: Vector[metamodel.MetaClass2Attribute] = Vector(),
 generalizations: Vector[metamodel.MetaClassifierGeneralization] = Vector(),
 importedMetamodels: Vector[OTIMOFResourceMetamodelImport] = Vector())
  extends OTIMOFResourceExtent

/**
  * @group metamodel
  */
object OTIMOFMetamodelResourceExtent {

  implicit val formats
  : Format[OTIMOFMetamodelResourceExtent]
  = Json.format[OTIMOFMetamodelResourceExtent]

}

/**
  * @group resources
  */
object OTIMOFResourceExtent {

  implicit val formats
  : Format[OTIMOFResourceExtent]
  = Variants.format[OTIMOFResourceExtent]((__ \ "type").format[String])

}