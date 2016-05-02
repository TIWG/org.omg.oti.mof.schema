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

import org.omg.oti.mof.schema.features.{FeatureLowerBound, FeatureOrdering, FeatureUpperBound}
import org.omg.oti.mof.schema.Identification._
import org.omg.oti.mof.schema.library.{Enumeration2Literal, StructuredDatatype2Attribute}
import org.omg.oti.mof.schema.metamodel.{MetaAssociation2SourceEndProperty, MetaAssociation2TargetEndProperty}
import org.omg.oti.mof.schema.metamodel.{MetaAssociationEndProperty2MetaClassType, MetaClass2Attribute}
import org.omg.oti.mof.schema.model.ModelLink
import org.omg.oti.mof.schema.profile.{StereotypeAssociationTargetEndMetaClassProperty, StereotypeAssociationTargetEndStereotypeProperty}

import scala.Predef.String
import scalaz.@@

sealed trait OTIMOFResourceItem {
  val resourceIRI: String @@ (_ <: ResourceIRI)
}

/**
  * OTIMOFLibraryItem is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Library resource.
  *
  * The `resourceIRI` is a foreign key identifying an [[OTIMOFLibrary]] resource.
  */
sealed trait OTIMOFLibraryItem extends OTIMOFResourceItem {
  override val resourceIRI: String @@ LibraryIRI
}

case class OTIMOFLibraryClassifierItem
( override val resourceIRI: String @@ LibraryIRI,
  classifier: String @@ (_ <: LibraryClassifierUUID) )
  extends OTIMOFLibraryItem

case class OTIMOFLibraryEnumerationLiteralItem
( override val resourceIRI: String @@ LibraryIRI,
  enumerationliteral: String @@ LibraryEnumerationLiteralUUID )
  extends OTIMOFLibraryItem

case class OTIMOFLibraryEnumeration2LiteralItem
( override val resourceIRI: String @@ LibraryIRI,
  enumeration2literal: Enumeration2Literal )
  extends OTIMOFLibraryItem

case class OTIMOFLibraryStructuredAttributeItem
( override val resourceIRI: String @@ LibraryIRI,
  attributeProperty: String @@ DatatypedAttributePropertyUUID )
  extends OTIMOFLibraryItem

case class OTIMOFLibraryStructuredAttributeLowerBoundItem
( override val resourceIRI: String @@ LibraryIRI,
  attributeLowerBound: FeatureLowerBound )
  extends OTIMOFLibraryItem

case class OTIMOFLibraryStructuredAttributeUpperBoundItem
( override val resourceIRI: String @@ LibraryIRI,
  attributeUpperBound: FeatureUpperBound )
  extends OTIMOFLibraryItem

case class OTIMOFLibraryStructuredAttributeOrderingItem
( override val resourceIRI: String @@ LibraryIRI,
  attributeOrdering: FeatureOrdering )
  extends OTIMOFLibraryItem

case class OTIMOFLibraryStructuredDatatype2AttributeItem
( override val resourceIRI: String @@ LibraryIRI,
  structuredDatatype2attribute: StructuredDatatype2Attribute )
  extends OTIMOFLibraryItem

/**
  * OTIMOFMetamodelItem is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Metamodel resource.
  *
  * The `resourceIRI` is a foreign key identifying an [[OTIMOFMetamodel]] resource.
  */
sealed trait OTIMOFMetamodelItem extends OTIMOFResourceItem {
  override val resourceIRI: String @@ MetamodelIRI
}

case class OTIMOFMetamodelClassifierItem
( override val resourceIRI: String @@ MetamodelIRI,
  classifier: String @@ MetamodelClassifierUUID )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelAttributeItem
( override val resourceIRI: String @@ MetamodelIRI,
  attributeProperty: String @@ DatatypedAttributePropertyUUID )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelMetaClass2AttributeItem
( override val resourceIRI: String @@ MetamodelIRI,
  metaClass2attribute: MetaClass2Attribute )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelAssociationEndProperty2MetaClassTypeItem
( override val resourceIRI: String @@ MetamodelIRI,
  associationEndType: MetaAssociationEndProperty2MetaClassType )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelAssociationEndPropertyItem
( override val resourceIRI: String @@ MetamodelIRI,
  associationEnd: String @@ AssociationEndUUID )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelFeatureLowerBoundItem
( override val resourceIRI: String @@ MetamodelIRI,
  lowerBound: FeatureLowerBound )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelFeatureUpperBoundItem
( override val resourceIRI: String @@ MetamodelIRI,
  upperBound: FeatureUpperBound )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelFeatureOrderingItem
( override val resourceIRI: String @@ MetamodelIRI,
  ordering: FeatureOrdering )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelAssociation2SourceEndPropertyItem
( override val resourceIRI: String @@ MetamodelIRI,
  association2sourceEnd: MetaAssociation2SourceEndProperty )
  extends OTIMOFMetamodelItem

case class OTIMOFMetamodelAssociation2TargetEndPropertyItem
( override val resourceIRI: String @@ MetamodelIRI,
  association2targetEnd: MetaAssociation2TargetEndProperty )
  extends OTIMOFMetamodelItem

/**
  * OTIMOFProfileItem is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Profile resource.
  *
  * The `resourceIRI` is a foreign key identifying an [[OTIMOFProfile]] resource.
  */
sealed trait OTIMOFProfileItem extends OTIMOFResourceItem {
  override val resourceIRI: String @@ ProfileIRI
}

case class OTIMOFProfileStereotypeItem
( override val resourceIRI: String @@ ProfileIRI,
  stereotype: String @@ StereotypeUUID )
  extends OTIMOFProfileItem

case class OTIMOFProfileStereotypeAssociationTargetEndMetaClassPropertyItem
( override val resourceIRI: String @@ ProfileIRI,
  metaClassProperty: StereotypeAssociationTargetEndMetaClassProperty )
  extends OTIMOFProfileItem

case class OTIMOFProfileStereotypeAssociationTargetEndStereotypePropertyItem
( override val resourceIRI: String @@ ProfileIRI,
  stereotypeProperty: StereotypeAssociationTargetEndStereotypeProperty )
  extends OTIMOFProfileItem

case class OTIMOFProfileStereotypeAssociationTargetEndLowerBoundItem
( override val resourceIRI: String @@ ProfileIRI,
  attributeLowerBound: FeatureLowerBound )
  extends OTIMOFProfileItem

case class OTIMOFProfileStereotypeAssociationTargetEndUpperBoundItem
( override val resourceIRI: String @@ ProfileIRI,
  attributeUpperBound: FeatureUpperBound )
  extends OTIMOFProfileItem

case class OTIMOFProfileStereotypeAssociationTargetEndOrderingItem
( override val resourceIRI: String @@ ProfileIRI,
  attributeOrdering: FeatureOrdering )
  extends OTIMOFProfileItem

/**
  * OTIMOFModelItem is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Model resource.
  *
  * The `resourceIRI` is a foreign key identifying an [[OTIMOFModel]] resource.
  */
sealed trait OTIMOFModelItem extends OTIMOFResourceItem {
  override val resourceIRI: String @@ ModelIRI
}

case class OTIMOFModelElementItem
( override val resourceIRI: String @@ ModelIRI,
  element: String @@ ModelElementUUID )
  extends OTIMOFModelItem

case class OTIMOFModelLinkItem
( override val resourceIRI: String @@ ModelIRI,
  link: ModelLink )
  extends OTIMOFModelItem