/*
 * Copyright 2016 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * License Terms
 */

package org.omg.oti.mof.schema

import scala.collection.GenTraversable
import scala.collection.generic.CanBuildFrom
import scala.PartialFunction

/**
  * OCL-like select (filter) + collect (downcast)
  *
  * OCL:
  * {{{
  * s->select(oclIsKindOf(V))->collect(oclAsType(V))
  * }}}
  *
  * Scala:
  *
  * {{{
  * import Utils.selectable
  *
  * s.select { case v: V => v }
  * }}}
  *
  * @param s A collection of type C[U]
  * @tparam U A type of elements
  * @tparam C A collection type
  */
class Selectable[U, C[X <: U] <: GenTraversable[X]](s: C[U]) {

  def select[V <: U]
  (pf: PartialFunction[U, V])
  (implicit bf: CanBuildFrom[C[U], V, C[V]])
  : C[V]
  = {
    val b = bf(s)
    s.foreach { u: U =>
      if (pf.isDefinedAt(u)) {
        b += pf(u)
      }
      ()
    }
    b.result
  }

}

object Selectable {

  implicit def selectable[U, C[X <: U] <: GenTraversable[X]](s: C[U])
  : Selectable[U, C]
  = new Selectable[U, C](s)

}