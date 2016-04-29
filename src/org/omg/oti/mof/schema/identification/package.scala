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

/**
  * Identification is essential for the exchange of information about anything in MOF.
  *
  * In terms of OMG's MOF 2.5, MOF resources such as metamodels, profiles, libraries and models
  * are identified by their Package URI. According to the MOF 2.5 specification,
  * MOF elements in the extent of a MOF resource can be identified; presumably in terms of the values
  * of their metaclass porperties that have `{isID=true}`.
  *
  * Here, identification is defined differently for MOF resources (i.e., metamodels, profiles, libraries and models)
  * than it is for MOF elements in their MOF extent. In the former case, MOF resources are identified by their IRI;
  * in the latter case, MOF elements are identified by the value of their identifying UUID metaclass property attribute.
  *
  * Since IRIs and UUIDs are involved in the serialization of references to OTI MOF resources and entities respectively,
  * the OTI MOF schema defines a lightweight, compile-time partitioning of all OTI MOF IRIs and UUIDs to capture
  * the intent of the references to OTI MOF resources and entities of a particular kind.
  */
package object identification {}