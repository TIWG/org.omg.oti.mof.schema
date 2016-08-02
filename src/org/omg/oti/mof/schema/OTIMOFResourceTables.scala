package org.omg.oti.mof.schema

import org.omg.oti.mof.schema.tables.OTIMOFResourceType
import scala.collection.immutable.Iterable

trait OTIMOFResourceTables {
 val resourceType: Iterable[OTIMOFResourceType]
}
