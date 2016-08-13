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