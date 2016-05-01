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

/**
  * OTIMOFEntityExtent is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF resource.
  *
  * The `resourceIRI` is a foreign key identifying an OTI MOF Resource of some kind.
  *
  * A `resourceIRI` can be referenced as the importing resource
  * (@see [[OTIMOFResourceLibraryImport.importingResourceIRI]])
  *
  * A `resourceIRI` identifying an OTI MOF Library resource can be referenced as the imported resource
  * (@see [[OTIMOFResourceLibraryImport.importedLibrary]]
  */
sealed trait OTIMOFResourceExtent {
  val resourceIRI: String @@ (_ <: ResourceIRI)
}

/**
  * OTIMOFLibraryExtent is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Library resource.
  *
  * The `resourceIRI` is a foreign key identifying an [[OTIMOFLibrary]] resource.
  */
sealed trait OTIMOFLibraryExtent extends OTIMOFResourceExtent {
  override val resourceIRI: String @@ LibraryIRI
}

case class OTIMOFLibraryClassifierExtent
( override val resourceIRI: String @@ LibraryIRI,
  classifier: String @@ (_ <: LibraryClassifierUUID) )
  extends OTIMOFLibraryExtent

case class OTIMOFLibraryEnumerationLiteralExtent
( override val resourceIRI: String @@ LibraryIRI,
  enumerationliteral: String @@ LibraryEnumerationLiteralUUID )
  extends OTIMOFLibraryExtent

case class OTIMOFLibraryEnumeration2LiteralExtent
( override val resourceIRI: String @@ LibraryIRI,
  enumeration2literal: Enumeration2Literal )
  extends OTIMOFLibraryExtent

case class OTIMOFLibraryStructuredAttributeExtent
( override val resourceIRI: String @@ LibraryIRI,
  attributeProperty: String @@ DatatypedAttributePropertyUUID )
  extends OTIMOFLibraryExtent

case class OTIMOFLibraryStructuredAttributeLowerBoundExtent
( override val resourceIRI: String @@ LibraryIRI,
  attributeLowerBound: FeatureLowerBound )
  extends OTIMOFLibraryExtent

case class OTIMOFLibraryStructuredAttributeUpperBoundExtent
( override val resourceIRI: String @@ LibraryIRI,
  attributeUpperBound: FeatureUpperBound )
  extends OTIMOFLibraryExtent

case class OTIMOFLibraryStructuredAttributeOrderingExtent
( override val resourceIRI: String @@ LibraryIRI,
  attributeOrdering: FeatureOrdering )
  extends OTIMOFLibraryExtent

case class OTIMOFLibraryStructuredDatatype2AttributeExtent
( override val resourceIRI: String @@ LibraryIRI,
  structuredDatatype2attribute: StructuredDatatype2Attribute )
  extends OTIMOFLibraryExtent

/**
  * OTIMOFMetamodelExtent is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Metamodel resource.
  *
  * The `resourceIRI` is a foreign key identifying an [[OTIMOFMetamodel]] resource.
  */
sealed trait OTIMOFMetamodelExtent extends OTIMOFResourceExtent {
  override val resourceIRI: String @@ MetamodelIRI
}

case class OTIMOFMetamodelClassifierExtent
( override val resourceIRI: String @@ MetamodelIRI,
  classifier: String @@ MetamodelClassifierUUID )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelAttributeExtent
( override val resourceIRI: String @@ MetamodelIRI,
  attributeProperty: String @@ DatatypedAttributePropertyUUID )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelMetaClass2AttributeExtent
( override val resourceIRI: String @@ MetamodelIRI,
  metaClass2attribute: MetaClass2Attribute )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelAssociationEndProperty2MetaClassTypeExtent
( override val resourceIRI: String @@ MetamodelIRI,
  associationEndType: MetaAssociationEndProperty2MetaClassType )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelAssociationEndPropertyExtent
( override val resourceIRI: String @@ MetamodelIRI,
  associationEnd: String @@ AssociationEndUUID )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelFeatureLowerBoundExtent
( override val resourceIRI: String @@ MetamodelIRI,
  lowerBound: FeatureLowerBound )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelFeatureUpperBoundExtent
( override val resourceIRI: String @@ MetamodelIRI,
  upperBound: FeatureUpperBound )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelFeatureOrderingExtent
( override val resourceIRI: String @@ MetamodelIRI,
  ordering: FeatureOrdering )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelAssociation2SourceEndPropertyExtent
( override val resourceIRI: String @@ MetamodelIRI,
  association2sourceEnd: MetaAssociation2SourceEndProperty )
  extends OTIMOFMetamodelExtent

case class OTIMOFMetamodelAssociation2TargetEndPropertyExtent
( override val resourceIRI: String @@ MetamodelIRI,
  association2targetEnd: MetaAssociation2TargetEndProperty )
  extends OTIMOFMetamodelExtent

/**
  * OTIMOFProfileExtent is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Profile resource.
  *
  * The `resourceIRI` is a foreign key identifying an [[OTIMOFProfile]] resource.
  */
sealed trait OTIMOFProfileExtent extends OTIMOFResourceExtent {
  override val resourceIRI: String @@ ProfileIRI
}

case class OTIMOFProfileStereotypeExtent
( override val resourceIRI: String @@ ProfileIRI,
  stereotype: String @@ StereotypeUUID )
  extends OTIMOFProfileExtent

case class OTIMOFProfileStereotypeAssociationTargetEndMetaClassPropertyExtent
( override val resourceIRI: String @@ ProfileIRI,
  metaClassProperty: StereotypeAssociationTargetEndMetaClassProperty )
  extends OTIMOFProfileExtent

case class OTIMOFProfileStereotypeAssociationTargetEndStereotypePropertyExtent
( override val resourceIRI: String @@ ProfileIRI,
  stereotypeProperty: StereotypeAssociationTargetEndStereotypeProperty )
  extends OTIMOFProfileExtent

case class OTIMOFProfileStereotypeAssociationTargetEndLowerBoundExtent
( override val resourceIRI: String @@ ProfileIRI,
  attributeLowerBound: FeatureLowerBound )
  extends OTIMOFProfileExtent

case class OTIMOFProfileStereotypeAssociationTargetEndUpperBoundExtent
( override val resourceIRI: String @@ ProfileIRI,
  attributeUpperBound: FeatureUpperBound )
  extends OTIMOFProfileExtent

case class OTIMOFProfileStereotypeAssociationTargetEndOrderingExtent
( override val resourceIRI: String @@ ProfileIRI,
  attributeOrdering: FeatureOrdering )
  extends OTIMOFProfileExtent

/**
  * OTIMOFModelExtent is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Model resource.
  *
  * The `resourceIRI` is a foreign key identifying an [[OTIMOFModel]] resource.
  */
sealed trait OTIMOFModelExtent extends OTIMOFResourceExtent {
  override val resourceIRI: String @@ ModelIRI
}

case class OTIMOFModelElementExtent
( override val resourceIRI: String @@ ModelIRI,
  element: String @@ ModelElementUUID )
  extends OTIMOFModelExtent

case class OTIMOFModelLinkExtent
( override val resourceIRI: String @@ ModelIRI,
  link: ModelLink )
  extends OTIMOFModelExtent