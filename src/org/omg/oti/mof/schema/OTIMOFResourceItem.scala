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
import org.omg.oti.mof.schema.common._
import org.omg.oti.mof.schema.library.{Enumeration2Literal, StructuredDatatype2Attribute}
import org.omg.oti.mof.schema.metamodel.{MetaAssociation2SourceEndProperty, MetaAssociation2TargetEndProperty}
import org.omg.oti.mof.schema.metamodel.{MetaAssociationEndProperty2MetaClassType, MetaClass2Attribute}

/**
  * @group resources
  */
sealed trait OTIMOFResourceItem {
  val resourceIRI: ResourceIRI
}

/**
  * OTIMOFLibraryItem is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Library resource.
  *
  * @group library
  * @define declared entity declared in an [[OTIMOFLibrary]]
  * @define relation relation declared in an [[OTIMOFLibrary]]
  * @define primary The primary key identifying
  * @define resourceIRI The foreign key identifying an [[OTIMOFLibrary]] resource
  */
sealed trait OTIMOFLibraryItem extends OTIMOFResourceItem {
  override val resourceIRI: ResourceIRI
}

/**
  * A [[library.DatatypeClassifier]] $declared
  *
  * @param resourceIRI $resourceIRI
  * @param classifier $primary [[library.DatatypeClassifier]]
  * @group library
  */
case class OTIMOFLibraryClassifierItem
( override val resourceIRI: ResourceIRI,
  classifier: EntityUUID )
  extends OTIMOFLibraryItem

/**
  * A [[features.EnumerationLiteral]] $declared
  *
  * @param resourceIRI $resourceIRI
  * @param enumerationliteral $primary an [[features.EnumerationLiteral]]
  * @group library
  */
case class OTIMOFLibraryEnumerationLiteralItem
( override val resourceIRI: ResourceIRI,
  enumerationliteral: EntityUUID )
  extends OTIMOFLibraryItem

/**
  * A [[library.Enumeration2Literal]] $relation
  *
  * @param resourceIRI $resourceIRI
  * @param enumeration2literal [[library.Enumeration2Literal]]
  * @group library
  */
case class OTIMOFLibraryEnumeration2LiteralItem
( override val resourceIRI: ResourceIRI,
  enumeration2literal: Enumeration2Literal )
  extends OTIMOFLibraryItem

/**
  * A [[features.DataTypedAttributeProperty]] $declared
  * @param resourceIRI $resourceIRI
  * @param attributeProperty $primary a [[features.DataTypedAttributeProperty]]
  * @group library
  */
case class OTIMOFLibraryStructuredAttributeItem
( override val resourceIRI: ResourceIRI,
  attributeProperty: EntityUUID )
  extends OTIMOFLibraryItem

/**
  * A [[features.FeatureLowerBound]] for a [[features.DataTypedAttributeProperty]] $relation
  * @param resourceIRI $resourceIRI
  * @param attributeLowerBound [[features.FeatureLowerBound]]
  * @group library
  */
case class OTIMOFLibraryStructuredAttributeLowerBoundItem
( override val resourceIRI: ResourceIRI,
  attributeLowerBound: FeatureLowerBound )
  extends OTIMOFLibraryItem

/**
  * A [[features.FeatureLowerBound]] for a [[features.DataTypedAttributeProperty]] $relation
  * @param resourceIRI $resourceIRI
  * @param attributeUpperBound [[features.FeatureLowerBound]]
  * @group library
  */
case class OTIMOFLibraryStructuredAttributeUpperBoundItem
( override val resourceIRI: ResourceIRI,
  attributeUpperBound: FeatureUpperBound )
  extends OTIMOFLibraryItem

/**
  * A [[features.FeatureOrdering]] for a [[features.DataTypedAttributeProperty]] $relation
  * @param resourceIRI $resourceIRI
  * @param attributeOrdering [[features.FeatureOrdering]]
  * @group library
  */
case class OTIMOFLibraryStructuredAttributeOrderingItem
( override val resourceIRI: ResourceIRI,
  attributeOrdering: FeatureOrdering )
  extends OTIMOFLibraryItem

/**
  * A [[library.StructuredDatatype2Attribute]] $relation
  * @param resourceIRI $resourceIRI
  * @param structuredDatatype2attribute [[library.StructuredDatatype2Attribute]]
  * @group library
  */
case class OTIMOFLibraryStructuredDatatype2AttributeItem
( override val resourceIRI: ResourceIRI,
  structuredDatatype2attribute: StructuredDatatype2Attribute )
  extends OTIMOFLibraryItem

/**
  * OTIMOFMetamodelItem is an abstract type for the database table relationships
  * that specify the extent of an OTI MOF Metamodel resource.
  *
  * @group metamodel
  * @define declared entity declared in an [[OTIMOFMetamodel]]
  * @define relation relation declared in an [[OTIMOFMetamodel]]
  * @define primary The primary key identifying
  * @define resourceIRI The foreign key identifying an [[OTIMOFMetamodel]] resource
  */
sealed trait OTIMOFMetamodelItem extends OTIMOFResourceItem {
  override val resourceIRI: ResourceIRI
}

/**
  * A [[metamodel.MetamodelClassifier]] $declared
  * @param resourceIRI $resourceIRI
  * @param classifier $primary a [[metamodel.MetamodelClassifier]]
  * @group metamodel
  */
case class OTIMOFMetamodelClassifierItem
( override val resourceIRI: ResourceIRI,
  classifier: EntityUUID )
  extends OTIMOFMetamodelItem

/**
  * A [[features.DataTypedAttributeProperty]] $declared
  * @param resourceIRI $resourceIRI
  * @param attributeProperty $primary a [[features.DataTypedAttributeProperty]]
  * @group metamodel
  */
case class OTIMOFMetamodelAttributeItem
( override val resourceIRI: ResourceIRI,
  attributeProperty: EntityUUID )
  extends OTIMOFMetamodelItem

/**
  * A [[metamodel.MetaClass2Attribute]] $relation
  * @param resourceIRI $resourceIRI
  * @param metaClass2attribute [[metamodel.MetaClass2Attribute]]
  * @group metamodel
  */
case class OTIMOFMetamodelMetaClass2AttributeItem
( override val resourceIRI: ResourceIRI,
  metaClass2attribute: MetaClass2Attribute )
  extends OTIMOFMetamodelItem

/**
  * A [[metamodel.MetaAssociationEndProperty2MetaClassType]] $relation
  * @param resourceIRI $resourceIRI
  * @param associationEndType [[metamodel.MetaAssociationEndProperty2MetaClassType]]
  * @group metamodel
  */
case class OTIMOFMetamodelAssociationEndProperty2MetaClassTypeItem
( override val resourceIRI: ResourceIRI,
  associationEndType: MetaAssociationEndProperty2MetaClassType )
  extends OTIMOFMetamodelItem

/**
  * A [[features.AssociationEnd]] $declared
  * @param resourceIRI $resourceIRI
  * @param associationEnd [[features.AssociationEnd]]
  * @group metamodel
  */
case class OTIMOFMetamodelAssociationEndPropertyItem
( override val resourceIRI: ResourceIRI,
  associationEnd: EntityUUID )
  extends OTIMOFMetamodelItem

/**
  * A [[features.FeatureLowerBound]] for a [[features.Feature]] $relation
  * @param resourceIRI $resourceIRI
  * @param lowerBound [[features.FeatureLowerBound]]
  * @group metamodel
  */
case class OTIMOFMetamodelFeatureLowerBoundItem
( override val resourceIRI: ResourceIRI,
  lowerBound: FeatureLowerBound )
  extends OTIMOFMetamodelItem

/**
  * A [[features.FeatureUpperBound]] for a [[features.Feature]] $relation
  * @param resourceIRI $resourceIRI
  * @param upperBound [[features.FeatureUpperBound]]
  * @group metamodel
  */
case class OTIMOFMetamodelFeatureUpperBoundItem
( override val resourceIRI: ResourceIRI,
  upperBound: FeatureUpperBound )
  extends OTIMOFMetamodelItem

/**
  * A [[features.FeatureOrdering]] for a [[features.Feature]] $relation
  * @param resourceIRI $resourceIRI
  * @param ordering [[features.FeatureOrdering]]
  * @group metamodel
  */
case class OTIMOFMetamodelFeatureOrderingItem
( override val resourceIRI: ResourceIRI,
  ordering: FeatureOrdering )
  extends OTIMOFMetamodelItem

/**
  * A [[features.AssociationSourceEnd]] for a [[metamodel.MetaAssociation]] $relation
  * @param resourceIRI $resourceIRI
  * @param association2sourceEnd [[metamodel.MetaAssociation2SourceEndProperty]]
  * @group metamodel
  */
case class OTIMOFMetamodelAssociation2SourceEndPropertyItem
( override val resourceIRI: ResourceIRI,
  association2sourceEnd: MetaAssociation2SourceEndProperty )
  extends OTIMOFMetamodelItem

/**
  * A [[features.AssociationTargetEnd]] for a [[metamodel.MetaAssociation]] $relation
  * @param resourceIRI $resourceIRI
  * @param association2targetEnd [[metamodel.MetaAssociation2TargetEndProperty]]
  * @group metamodel
  */
case class OTIMOFMetamodelAssociation2TargetEndPropertyItem
( override val resourceIRI: ResourceIRI,
  association2targetEnd: MetaAssociation2TargetEndProperty )
  extends OTIMOFMetamodelItem
