package org.omg.oti.mof.schema.tables.library

import org.omg.oti.mof.schema.common._

trait OTIMOFLibraryClassifier {
  val resource: ResourceIRI
  val uuid: EntityUUID
  val name: Name
}
