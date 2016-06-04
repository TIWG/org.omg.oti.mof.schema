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

import play.api.libs.json._

import org.omg.oti.mof.schema.common._

/**
  * The OMG modeling architecture has traditionally been described in terms of 4 levels: M0, M1, M2, M3.
  * In principle, the OMF MOF 2.x architecture supports an arbitrary number of modeling levels with a minimum of 2.
  * Unfortunately, this results in several problems, including the ambiguity about profiles, which are neither completely
  * at M1 because stereotypes refer to metaclasses in an M2 metamodel, nor at M2 because stereotypes are applied to
  * M1 elements that are instances of M2 metaclasses.
  *
  * In OTI MOF, there are no modeling levels per se; rather, there are four kinds of models:
  * - libraries that define datatypes
  * - metamodels that define metaclasses related via metaassociations; metaclasses can have attribute properties
  *   typed by datatypes defined in imported libraries
  * - profiles that reference a subset of a metamodel to define stereotypes that extend metaclasses in that subset;
  *   stereotypes can be related to other stereotypes via profile stereotype associations; stereotypes can be related
  *   to metaclasses via profile metaclass associations; stereotypes can have attribute
  *   properties typed by datatypes defined in imported libraries
  * - models that instantiate one or more metamodel possibly extended by the application of one or more
  *   profile and whose contents are elements that are instances of metaclasses where such elements can be related
  *   to other elements by link instances of metaassociations and can be extended by applying stereotypes
  *   and linking applied stereotypes according to profile stereotype associations and profile metaclass associations.
  * @group resources
  */
sealed trait OTIMOFResource {
  val iri: ResourceIRI
}

/**
  * A resource corresponding to an OMG MOF library package used for OMG MOF metamodels, profiles or models.
  *
  * @example The OMG PrimitiveTypes library from OMG UML 2.5 could be defined as follows:
  * {{{
  *   val primitiveTypesLibrary = OTIMOFLibrary(LibraryIRI.wrap("http://www.omg.org/spec/UML/20131001/PrimitiveTypes"))
  * }}}
  *
  * The extent comprises:
  * - OTIMOFLibraryExtent
  * - OTIMOFResourceLibraryImport (where the importingResourceIRI is a LibraryIRI)
  *
  * @param iri Corresponds to the OMG `UML::Package::URI` unique identifier of
  *            the corresponding OMG `UML::Package` library.
  * @group library
  */
case class OTIMOFLibrary
( override val iri: ResourceIRI )
  extends OTIMOFResource

/**
  * @group library
  */
object OTIMOFLibrary {

  implicit val formats
  : Format[OTIMOFLibrary]
  = Json.format[OTIMOFLibrary]

}

/**
  * A resource corresponding to an OMG MOF metamodel package.
  *
  * @example The OMG UML Metamodel package from OMG UML 2.5 could be defined as follows:
  * {{{
  *   val umlMetamodel = OTIMOFMetamodel(MetamodelIRI.wrap("http://www.omg.org/spec/UML/20131001"))
  * }}}
  *
  * @example The OMG UMLDI Metamodel package from OMG UML 2.5 could be defined as follows:
  * {{{
  *   val umldiMetamodel = OTIMOFMetamodel(MetamodelIRI.wrap("http://www.omg.org/spec/UML/20131001/UMLDI"))
  * }}}
  *
  * The extent comprises:
  * - OTIMOFMetamodelExtent
  * - OTIMOFResourceLibraryImport (where the importingResourceIRI is a MetamodelIRI)
  * - OTIMOFResourceMetamodelImport
  *
  * @param iri Corresponds to the OMG `UML::Package::URI` unique identifier of
  *            the corresponding OMG `UML::Package` metamodel.
  * @group metamodel
  */
case class OTIMOFMetamodel
( override val iri: ResourceIRI )
extends OTIMOFResource

/**
  * @group metamodel
  */
object OTIMOFMetamodel {

  implicit val formats
  : Format[OTIMOFMetamodel]
  = Json.format[OTIMOFMetamodel]

}

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
  * @example The OMG UML StandardProfile profile from OMG UML 2.5 could be defined as follows:
  * {{{
  *   val standardProfile = OTIMOFProfile(ProfileIRI.wrap("http://www.omg.org/spec/UML/20131001/StandardProfile"))
  * }}}
  *
  * @example The OMG SysML profile from OMG SysML 1.4 could be defined as follows:
  * {{{
  *   val sysmlProfile = OTIMOFProfile(ProfileIRI.wrap("http://www.omg.org/spec/SysML/20150709/SysML"))
  * }}}
  * @param iri Corresponds to the OMG `UML::Package::URI` unique identifier of
  *            the corresponding OMG `UML::Profile` profile.
  * @group profile
  */
case class OTIMOFProfile
( override val iri: ResourceIRI )
  extends OTIMOFResource

/**
  * @group profile
  */
object OTIMOFProfile {

  implicit val formats
  : Format[OTIMOFProfile]
  = Json.format[OTIMOFProfile]

}

/**
  * A resource corresponding to an OMG MOF package that does not define datatypes
  *
  * @example The OMG conceptual model for Quantities, Units, Dimensions and Values (QUDV) from
  *          OMG SysML 1.4 could be defined as a combination of:
  *          - a QUDV library (for `QUDV::Number` and `QUDV::Rational` value types)
  *          - a QUDV model (for the rest), as follows:
  * {{{
  *   val qudvModel = OTIMOFModel(ModelIRI.wrap("http://www.omg.org/spec/SysML/20150709/QUDV"))
  * }}}
  *
  *
  * @param iri Corresponds to the OMG `UML::Package::URI` unique identifier of
  *            the corresponding OMG `UML::Package` metamodel
  * @group model
  */
case class OTIMOFModel
( override val iri: ResourceIRI )
  extends OTIMOFResource

/**
  * @group model
  */
object OTIMOFModel {

  implicit val formats
  : Format[OTIMOFModel]
  = Json.format[OTIMOFModel]

}
