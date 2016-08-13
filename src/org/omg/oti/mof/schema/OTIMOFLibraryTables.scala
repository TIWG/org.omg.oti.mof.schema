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