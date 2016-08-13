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