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
package org.omg.oti.mof.runtime

/**
  * What are the different kinds of meta-associations used in metamodels?
  *
  * All well-formed metamodel associations are ordered from a source to a target association end property.
  *
  * It depends on several characteristics:
  * - is the source/target association end property ordered?
  * - is the source/target association end property composite?
  *
  * For each kind of association, there are two sets of query operations:
  * - for forward navigation from the association source to target
  * - for reverse navigation from the association target to source
  *
  * For each kind of association, there is always a forward query in the association source metaclass
  * and a reverse query in the association target metaclass.
  *
  * This means that a simple test of well-formed code generation is to verify
  * that the count of forward query usages equals the count of reverse query usages for each kind of association.
  *
  * @example MagicDraw 18's implementation of OMG UML 2.5 has 430 associations:
  * - 9 are MD-specific (no code is generated for them currently)
  * - 421 correspond to OMG UML 2.5 + two extra metaclasses (Diagram, ElementValue)
  *
  * These 421 associations fall in 5 categories as described below:
  * Note that in each category, there is a matching number of forward & reverse query operations.
  *
  * - 1 [[org.omg.oti.mof.runtime.metamodel.SourceOrderedReferenceAssociation]]
  * Forward:
  * -- 1 queryIterable
  *
  * Reverse:
  * -- 1 queryOppositeOrdered
  *
  * - 40 [[org.omg.oti.mof.runtime.metamodel.TargetOrderedCompositeAssociation]]
  * Forward:
  * -- 1 queryIterable
  * -- 39 queryOrdered
  *
  * Reverse:
  * -- 40 queryOppositeOptional
  *
  * - 9 [[org.omg.oti.mof.runtime.metamodel.TargetOrderedReferenceAssociation]]
  *
  * Forward:
  * -- 1 queryIterable
  * -- 8 queryOrdered
  *
  * Reverse:
  * -- 2 queryOppositeOptional
  * -- 7 queryOppositeUnordered
  *
  * - 163 [[org.omg.oti.mof.runtime.metamodel.UnorderedCompositeAssociation]]
  *
  * Forward:
  * -- 4 queryIterable
  * -- 80 queryOptional
  * -- 79 queryUnordered
  *
  * Reverse:
  * -- 163 queryOppositeOptional
  *
  * - 208 [[org.omg.oti.mof.runtime.metamodel.UnorderedReferenceAssociation]]
  *
  * Forward:
  * -- 7 queryIterable
  * -- 105 queryOptional
  * -- 96 queryUnordered
  *
  * Reverse:
  * -- 60 queryOppositeOptional
  * -- 148 queryOppositeUnordered
  *
  */
package object metamodel {

}