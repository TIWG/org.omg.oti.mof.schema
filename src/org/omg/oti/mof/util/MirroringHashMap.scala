package org.omg.oti.mof.util

import scala.Option
import scala.collection.mutable._

case class MirroringHashMap[K, V, MV >: V]
( mirrors: HashMap[K, MV] *)
  extends HashMap[K, V] {

  override def put(k: K, v: V)
  : Option[V]
  = {
    val result = super.put(k, v)
    mirrors.foreach(_.put(k, v))
    result
  }

}
