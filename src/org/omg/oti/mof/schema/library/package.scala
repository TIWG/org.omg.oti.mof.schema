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
  * TODO: To be replaced by tables.library
  * These structures require a tree representation to specify the library resource IRI
  * for all entities, characteristics & relations.
  * The structures in tables.library all include the library resource IRI; therefore they are independent of each
  * other and have a flat, columnar representation.
  *
  * An [[OTIMOFLibrary]] is a resource whose [[OTIMOFLibraryResourceExtent]] is a set of [[OTIMOFLibraryItem]]s:
  *
  * Entities:
  *   - [[library.DatatypeClassifier]]
  *   - [[features.DataTypedFeature]]
  *
  * Characteristics:
  *   - [[features.FeatureLowerBound]]
  *   - [[features.FeatureUpperBound]]
  *   - [[features.FeatureOrdering]]
  *
  * Relations:
  *   - [[features.AttributeProperty2DataType]]
  *   - [[library.Enumeration2Literal]]
  *   - [[library.StructuredDatatype2Attribute]]
  *   - [[library.StructuredDataTypeGeneralization]]
  *   - [[OTIMOFResourceLibraryImport]]
  * @group library
  */
package object library