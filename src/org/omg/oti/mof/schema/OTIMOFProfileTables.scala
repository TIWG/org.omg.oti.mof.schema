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


/**
  * A resource corresponding to a restricted OMG MOF profile.
  * The restrictions are:
  *   - An OTI MOF Profile can only define Stereotypes and Stereotype associations
  *
  *     In an OMG MOF Profile, it is possible to define datatypes and classes.
  *     In OTI MOF, a profile-defined datatype must be defined in an OTI MOF Library instead.
  *     OTI MOF does not support profile-defined classes because this capability is too under-specified in
  *     OMG UML 2.5 and, in practice, very seldom used.
  *
  *   - An OTI MOF Profile can only be applied to an OTI MOF Model.
  *
  *     An OMG MOF Profile extension of the OMG UML metamodel can be applied to any UML "model",
  *     including a "model" of the UML metamodel itself, of a profile or of a user-defined model.
  *     Applying a profile to a metamodel or to a package library of datatype definitions does not have
  *     any semantics that affects the models that instiate this metamodel or that have attrbute values
  *     typed by such datatype definitions.
  *
  *     OTI MOF is only concerned with semantically-relevant profile application relationships.
  *     There is only one such case: that of applying a profile to a model.
  *
  * @group profile
  *
  * @param resourceType Identifies one or more profile
  * @param extendedMetamodels Extended metamodels
  * @param importedProfiles Imported profiles
  * @param importedLibraries Imported libraries
  * @param stereotypes Stereotypes defined
  * @param generalizations Generalizations among stereotypes
  * @param extendedMetaclasses Metaclasses extended by stereotypes
  * @param stereotypeAttributes Relates a stereotype to an attribute property
  * @param stereotype2MetaClassProperty Relates a stereotype to a property typed by a metaclasses
  * @param stereotype2StereotypeProperty Relates a stereotype to a property typed by stereotypes
  * @param associationTargetEnds Defines an association target ends property of a stereotype
  * @param attributes Defines a attribute of a stereotype
  * @param featureLowerBounds The lower bound of a stereotype feature (attribute or property)
  * @param featureUpperBounds The upper bound of a stereotype feature (attribute or property)
  * @param featureOrdering The ordering a stereotype feature (attribute or property)
  * @param attribute2type Relates a stereotype attribute to its type (a library datatype)
  */
case class OTIMOFProfileTables
(override val resourceType: Iterable[OTIMOFResourceType],
 extendedMetamodels: Iterable[tables.profile.OTIMOFProfile2ExtendedMetamodel],
 importedProfiles: Iterable[OTIMOFResourceProfileImport],
 importedLibraries: Iterable[OTIMOFResourceLibraryImport],
 stereotypes: Iterable[tables.profile.OTIMOFStereotype],
 generalizations: Iterable[tables.profile.OTIMOFStereotypeGeneralization],
 extendedMetaclasses: Iterable[tables.profile.OTIMOFStereotype2ExtendedMetaclass],
 stereotypeAttributes: Iterable[tables.profile.OTIMOFStereotype2Attribute],
 stereotype2MetaClassProperty: Iterable[tables.profile.OTIMOFStereotypeAssociationTargetEndMetaClassProperty],
 stereotype2StereotypeProperty: Iterable[tables.profile.OTIMOFStereotypeAssociationTargetEndStereotypeProperty],

 associationTargetEnds: Iterable[features.AssociationTargetEnd],
 attributes: Iterable[features.DataTypedAttributeProperty],

 featureLowerBounds: Iterable[features.FeatureLowerBound],
 featureUpperBounds: Iterable[features.FeatureUpperBound],
 featureOrdering: Iterable[features.FeatureOrdering],
 attribute2type: Iterable[features.AttributeProperty2DataType])
  extends OTIMOFResourceTables {

  def append(other: OTIMOFProfileTables)
  : OTIMOFProfileTables
  = copy(
    resourceType = this.resourceType ++ other.resourceType,
    extendedMetamodels = this.extendedMetamodels ++ other.extendedMetamodels,
    importedProfiles = this.importedProfiles ++ other.importedProfiles,
    importedLibraries = this.importedLibraries ++ other.importedLibraries,
    stereotypes = this.stereotypes ++ other.stereotypes,
    generalizations = this.generalizations ++ other.generalizations,
    extendedMetaclasses = this.extendedMetaclasses ++ other.extendedMetaclasses,
    stereotypeAttributes = this.stereotypeAttributes ++ other.stereotypeAttributes,
    stereotype2MetaClassProperty = this.stereotype2MetaClassProperty ++ other.stereotype2MetaClassProperty,
    stereotype2StereotypeProperty = this.stereotype2StereotypeProperty ++ other.stereotype2StereotypeProperty,

    associationTargetEnds = this.associationTargetEnds ++ other.associationTargetEnds,
    attributes = this.attributes ++ other.attributes,

    featureLowerBounds = this.featureLowerBounds ++ other.featureLowerBounds,
    featureUpperBounds = this.featureUpperBounds ++ other.featureUpperBounds,
    featureOrdering = this.featureOrdering ++ other.featureOrdering,
    attribute2type = this.attribute2type ++ other.attribute2type)

}