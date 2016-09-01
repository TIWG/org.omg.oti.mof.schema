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

package org.omg.oti.mof.schema

import org.omg.oti.mof.schema.tables.OTIMOFResourceType
import scala.collection.immutable.Iterable

case class OTIMOFMetamodelTables
(override val resourceType: Iterable[OTIMOFResourceType],
 importedMetamodels: Iterable[OTIMOFResourceMetamodelImport],
 importedLibraries: Iterable[OTIMOFResourceLibraryImport],
 metaClasses: Iterable[tables.metamodel.OTIMOFMetaClass],
 metaClassGeneralizations: Iterable[tables.metamodel.OTIMOFMetaClassGeneralization],
 metaAssociations: Iterable[tables.metamodel.OTIMOFMetaAssociation],
 metaAssociationGeneralizations: Iterable[tables.metamodel.OTIMOFMetaAssociationGeneralization],

 metaClass2orderedAtomicAttribute: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute],
 metaClass2orderedEnumerationAttribute: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute],
 metaClass2orderedStructuredAttribute: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute],
 metaClass2unorderedAtomicAttribute: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute],
 metaClass2unorderedEnumerationAttribute: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute],
 metaClass2unorderedStructuredAttribute: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute],

 metaAssociation2Source: Iterable[tables.metamodel.OTIMOFMetaAssociation2SourceEndProperty],
 metaAssociation2Target: Iterable[tables.metamodel.OTIMOFMetaAssociation2TargetEndProperty],
 metaAssociationEnds: Iterable[features.AssociationEnd],
 metaAssociationEnd2MetaClass: Iterable[tables.metamodel.OTIMOFMetaAssociationEndProperty2MetaClassType],

 attributes: Iterable[features.DataTypedAttributeProperty],

 featureLowerBounds: Iterable[features.FeatureLowerBound],
 featureUpperBounds: Iterable[features.FeatureUpperBound],
 featureOrdering: Iterable[features.FeatureOrdering],

 attribute2type: Iterable[features.AttributeProperty2DataType])
 extends OTIMOFResourceTables {

  def append(other: OTIMOFMetamodelTables)
  : OTIMOFMetamodelTables
  = copy(
    resourceType = this.resourceType ++ other.resourceType,
    importedMetamodels = this.importedMetamodels ++ other.importedMetamodels,
    importedLibraries = this.importedLibraries ++ other.importedLibraries,
    metaClasses = this.metaClasses ++ other.metaClasses,
    metaClassGeneralizations = this.metaClassGeneralizations ++ other.metaClassGeneralizations,
    metaAssociations = this.metaAssociations ++ other.metaAssociations,
    metaAssociationGeneralizations = this.metaAssociationGeneralizations ++ other.metaAssociationGeneralizations,
    metaClass2orderedAtomicAttribute = this.metaClass2orderedAtomicAttribute ++ other.metaClass2orderedAtomicAttribute,
    metaClass2orderedEnumerationAttribute = this.metaClass2orderedEnumerationAttribute ++ other.metaClass2orderedEnumerationAttribute,
    metaClass2orderedStructuredAttribute = this.metaClass2orderedStructuredAttribute ++ other.metaClass2orderedStructuredAttribute,
    metaClass2unorderedAtomicAttribute = this.metaClass2unorderedAtomicAttribute ++ other.metaClass2unorderedAtomicAttribute,
    metaClass2unorderedEnumerationAttribute = this.metaClass2unorderedEnumerationAttribute ++ other.metaClass2unorderedEnumerationAttribute,
    metaClass2unorderedStructuredAttribute = this.metaClass2unorderedStructuredAttribute ++ other.metaClass2unorderedStructuredAttribute,
    metaAssociation2Source = this.metaAssociation2Source ++ other.metaAssociation2Source,
    metaAssociation2Target = this.metaAssociation2Target ++ other.metaAssociation2Target,
    metaAssociationEnds = this.metaAssociationEnds ++ other.metaAssociationEnds,
    metaAssociationEnd2MetaClass = this.metaAssociationEnd2MetaClass ++ other.metaAssociationEnd2MetaClass,
    attributes = this.attributes ++ other.attributes,

    featureLowerBounds = this.featureLowerBounds ++ other.featureLowerBounds,
    featureUpperBounds = this.featureUpperBounds ++ other.featureUpperBounds,
    featureOrdering = this.featureOrdering ++ other.featureOrdering,

    attribute2type = this.attribute2type ++ other.attribute2type)

}