package org.omg.oti.mof.schema

import org.omg.oti.mof.schema.tables.OTIMOFResourceType
import scala.collection.immutable.Iterable

case class OTIMOFLibraryTables
(override val resourceType: Iterable[OTIMOFResourceType],

 importedLibraries: Iterable[OTIMOFResourceLibraryImport],

 primitiveDataTypes: Iterable[tables.library.OTIMOFPrimitiveDataType],

 enumerationDataTypes: Iterable[tables.library.OTIMOFEnumerationDataType],
 enumeration2literals: Iterable[tables.library.OTIMOFEnumeration2Literal],

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

    structuredDataTypes = this.structuredDataTypes ++ other.structuredDataTypes,
    generalizations = this.generalizations ++ other.generalizations,
    structure2attribute = this.structure2attribute ++ other.structure2attribute,

    attributes = this.attributes ++ other.attributes,

    featureLowerBounds = this.featureLowerBounds ++ other.featureLowerBounds,
    featureUpperBounds = this.featureUpperBounds ++ other.featureUpperBounds,
    featureOrdering = this.featureOrdering ++ other.featureOrdering,

    attribute2type = this.attribute2type ++ other.attribute2type)
  
}
