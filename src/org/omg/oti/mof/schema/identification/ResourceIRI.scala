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
package org.omg.oti.mof.schema.identification

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
trait ResourceIRI {}

/**
  * The persistent location of an OTI MOF Metamodel resource
  */
trait MetamodelIRI extends ResourceIRI

/**
  * The persistent location of an OTI MOF Profile resource
  */
trait ProfileIRI extends ResourceIRI

/**
  * The persistent location of an OTI MOF Library resource
  */
trait LibraryIRI extends ResourceIRI

/**
  * The persistent location of an OTI MOF Model resource
  */
trait ModelIRI extends ResourceIRI