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

case class OTIMOFModelTables
(override val resourceType: Iterable[OTIMOFResourceType],
 instantiatedMetamodels: Iterable[OTIMOFResourceInstantiatedMetamodel],
 appliedProfiles: Iterable[OTIMOFResourceModelAppliedProfile],
 elements: Iterable[tables.model.OTIMOFModelElement],
 toolSpecificElementIDs: Iterable[tables.OTIMOFToolSpecificID],
 toolSpecificElementURLs: Iterable[tables.OTIMOFToolSpecificURL],
 orderedAtomicValues: Iterable[tables.values.OTIMOFOrderedAttributeAtomicValue],
 orderedLiteralValues: Iterable[tables.values.OTIMOFOrderedAttributeEnumerationLiteralValue],
 orderedStructuredValues: Iterable[tables.values.OTIMOFOrderedAttributeStructuredValueLink],
 unorderedAtomicValues: Iterable[tables.values.OTIMOFUnorderedAttributeAtomicValue],
 unorderedLiteralValues: Iterable[tables.values.OTIMOFUnorderedAttributeEnumerationLiteralValue],
 unorderedStructuredValues: Iterable[tables.values.OTIMOFUnorderedAttributeStructuredValueLink],
 appliedStereotypes: Iterable[tables.model.OTIMOFAppliedStereotype],
 orderedStereotypeReferences: Iterable[tables.model.OTIMOFAppliedStereotypePropertyOrderedReference],
 unorderedStereotypeReferences: Iterable[tables.model.OTIMOFAppliedStereotypePropertyUnorderedReference],
 orderedLinks: Iterable[tables.model.OTIMOFModelOrderedLink],
 unorderedLinks: Iterable[tables.model.OTIMOFModelUnorderedLink])
  extends OTIMOFResourceTables {

  def append(other: OTIMOFModelTables)
  : OTIMOFModelTables
  = copy(
    resourceType = this.resourceType ++ other.resourceType,
    instantiatedMetamodels = this.instantiatedMetamodels ++ other.instantiatedMetamodels,
    appliedProfiles = this.appliedProfiles ++ other.appliedProfiles,
    elements = this.elements ++ other.elements,
    toolSpecificElementIDs = this.toolSpecificElementIDs ++ other.toolSpecificElementIDs,
    toolSpecificElementURLs = this.toolSpecificElementURLs ++ other.toolSpecificElementURLs,
    orderedAtomicValues = this.orderedAtomicValues ++ other.orderedAtomicValues,
    orderedLiteralValues = this.orderedLiteralValues ++ other.orderedLiteralValues,
    orderedStructuredValues = this.orderedStructuredValues ++ other.orderedStructuredValues,
    unorderedAtomicValues = this.unorderedAtomicValues ++ other.unorderedAtomicValues,
    unorderedLiteralValues = this.unorderedLiteralValues ++ other.unorderedLiteralValues,
    unorderedStructuredValues = this.unorderedStructuredValues ++ other.unorderedStructuredValues,
    appliedStereotypes = this.appliedStereotypes ++ other.appliedStereotypes,
    orderedStereotypeReferences = this.orderedStereotypeReferences ++ other.orderedStereotypeReferences,
    unorderedStereotypeReferences = this.unorderedStereotypeReferences ++ other.unorderedStereotypeReferences,
    orderedLinks = this.orderedLinks ++ other.orderedLinks,
    unorderedLinks = this.unorderedLinks ++ other.unorderedLinks)

}