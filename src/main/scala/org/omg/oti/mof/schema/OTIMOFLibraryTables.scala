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

case class OTIMOFLibraryTables
(override val resourceType: Iterable[OTIMOFResourceType],

 importedLibraries: Iterable[OTIMOFResourceLibraryImport],

 primitiveDataTypes: Iterable[tables.library.OTIMOFPrimitiveDataType],

 enumerationDataTypes: Iterable[tables.library.OTIMOFEnumerationDataType],
 enumeration2literals: Iterable[tables.library.OTIMOFEnumeration2Literal],
 enumerationLiterals: Iterable[common.EnumerationLiteralValue],

 structuredDataTypes: Iterable[tables.library.OTIMOFStructuredDataType],
 generalizations: Iterable[tables.library.OTIMOFStructuredDataTypeGeneralization],
 structure2attribute: Iterable[tables.library.OTIMOFStructuredDatatype2Attribute],

 attributes: Iterable[features.DataTypedAttributeProperty],

 featureLowerBounds: Iterable[features.FeatureLowerBound],
 featureUpperBounds: Iterable[features.FeatureUpperBound],
 featureOrdering: Iterable[features.FeatureOrdering],

 attribute2type: Iterable[features.AttributeProperty2DataType])
  extends OTIMOFResourceTables {

  def classifiers: Iterable[tables.library.OTIMOFLibraryClassifier]
  = primitiveDataTypes ++ enumerationDataTypes ++ structuredDataTypes
  
  def append(other: OTIMOFLibraryTables)
  : OTIMOFLibraryTables
  = copy(
    resourceType = this.resourceType ++ other.resourceType,

    importedLibraries = this.importedLibraries ++ other.importedLibraries,

    primitiveDataTypes = this.primitiveDataTypes ++ other.primitiveDataTypes,

    enumerationDataTypes = this.enumerationDataTypes ++ other.enumerationDataTypes,
    enumeration2literals = this.enumeration2literals ++ other.enumeration2literals,
    enumerationLiterals = this.enumerationLiterals ++ other.enumerationLiterals,

    structuredDataTypes = this.structuredDataTypes ++ other.structuredDataTypes,
    generalizations = this.generalizations ++ other.generalizations,
    structure2attribute = this.structure2attribute ++ other.structure2attribute,

    attributes = this.attributes ++ other.attributes,

    featureLowerBounds = this.featureLowerBounds ++ other.featureLowerBounds,
    featureUpperBounds = this.featureUpperBounds ++ other.featureUpperBounds,
    featureOrdering = this.featureOrdering ++ other.featureOrdering,

    attribute2type = this.attribute2type ++ other.attribute2type)
  
}