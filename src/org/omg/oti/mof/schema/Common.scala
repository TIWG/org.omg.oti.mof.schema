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

import scalaz.Tag

object Common {

  /**
    * DATATYPE_ABBREVIATED_IRI is the partition of Strings that represent the abbreviated form of the
    * IRI of an atomic datatype definition in the normative OWL2-DL datatype map.
    *
    * An abbreviated IRI has the form 'pn:rc' where 'pn' is the prefix IRI and 'rc' is the remaining
    * characters in the IRI after 'pn'.
    *
    * @see [[http://www.w3.org/TR/owl2-syntax/#Datatype_Maps]]
    * @see [[http://www.w3.org/TR/owl2-syntax/#IRIs]]
    */
  sealed trait DatatypeAbbrevIRI
  val DatatypeAbbrevIRI = Tag.of[DatatypeAbbrevIRI]

  /**
    * NAME is the partition of Strings that represent the name of an OTI MOF entity.
    *
    * In OTI MOF, the name of an OTI MOF entity is only used for constructing the OTI IRI of that entity.
    * That is, in OTI MOF, names are considered to be an internal, private characteristic of an OTI MOF entity.
    * Therefore, it is unnecessary to further partition the space of all OTI MOF entity names according
    * to the OTI MOF entity kind.
    *
    * In contrast to a NAME, a UUID is considered to be the externally visible and uniquely
    * identifying characteristic of OTI MOF entities and the basis for referencing them.
    * Therefore, it is necessary to further partition the space of all OTI MOF entity UUIDs according
    * to the OTI MOF entity kind to capture the intended type of OTI MOF entity reference.
    */
  sealed trait Name
  val Name = Tag.of[Name]

  /**
    * Int @@ NonNegative represents the subset of positive Int values including 0
    */
  sealed trait NonNegative
  val NonNegative = Tag.of[NonNegative]

  /**
    * Int @@ UnlimitedNatural represents the subset of positive Int values including 0 and -1 to denote positive infinity
    */
  sealed trait UnlimitedNatural
  val UnlimitedNatural = Tag.of[UnlimitedNatural]
}