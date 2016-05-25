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
package org.omg.oti.mof.schema.common

import play.api.libs.json._

import scala.Predef.String

/**
  * Every OTI MOF resource (i.e., (i.e., metamodel, profile, librarie or model)
  * is identified by the IRI of its persistent location (internationalized URI).
  *
  * In OTI MOF, a ResourceIRI is an externally visible and uniquely identifying
  * characteristic of an OTI MOF Resource.
  *
  * In the OTI MOF serialization, OTI MOF resources are referenced by their IRI.
  * By defining a lightweight compile-time partitioning of the space
  * of all possible OTI MOF resource IRIs according to the OTI MOF resource kind,
  * the OTI MOF schema captures important type-level information about
  * well-formed references to OTI MOF resources of a particular kind.
  */
trait ResourceIRI {
  val value: String
}

object ResourceIRI {

  implicit val formats
  : Format[ResourceIRI]
  = new Format[ResourceIRI] {

    def reads(json: JsValue)
    : JsResult[ResourceIRI]
    = MetamodelIRI.formats.reads(json)
      .orElse(ProfileIRI.formats.reads(json))
      .orElse(LibraryIRI.formats.reads(json))
      .orElse(ModelIRI.formats.reads(json))

    def writes(o: ResourceIRI)
    : JsValue
    = o match {
      case mm: MetamodelIRI => MetamodelIRI.formats.writes(mm)
      case pf: ProfileIRI => ProfileIRI.formats.writes(pf)
      case lib: LibraryIRI => LibraryIRI.formats.writes(lib)
      case m: ModelIRI => ModelIRI.formats.writes(m)
    }

  }

}