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

package org.omg.oti.mof.schema.views

import org.omg.oti.mof.schema._

import scala.Predef.ArrowAssoc
import scala.collection.immutable._
import scala.{Int,Option,None,Some,Tuple2}
import scala.Predef.String

case class UMLMetamodelResolver
(primitiveTypesR
 : OTIMOFLibraryTables,

 umlR
 : OTIMOFMetamodelTables,

 metaclasses
 : Vector[tables.metamodel.OTIMOFMetaClass],

 metaClassViews
 : Vector[views.MetaClassView],

 associations
 : Vector[tables.metamodel.OTIMOFMetaAssociation],

 associationViews
 : Vector[views.AssociationInfo],

 mc2DirectGeneralizations
 : Map[tables.metamodel.OTIMOFMetaClass, Set[tables.metamodel.OTIMOFMetaClass]],

 mc2DirectSpecializations
 : Map[tables.metamodel.OTIMOFMetaClass, Set[tables.metamodel.OTIMOFMetaClass]],

 ma2SubsetSubsetGeneralizations
 : Map[views.AssociationInfo, Set[views.AssociationInfo]],

 ma2SubsetRedefinitionGeneralizations
 : Map[views.AssociationInfo, Set[views.AssociationInfo]],

 ma2RedefinitionSubsetGeneralizations
 : Map[views.AssociationInfo, Set[views.AssociationInfo]],

 ma2RedefinitionRedefinitionGeneralizations
 : Map[views.AssociationInfo, Set[views.AssociationInfo]],

 mcName2UUID
 : Map[String, common.EntityUUID],

 mc2AllOrderedAtomicAttributes
 : Map[String, Set[views.DataTypedAttributeInfo]],

 mc2AllOrderedEnumerationAttributes
 : Map[String, Set[views.DataTypedAttributeInfo]],

 mc2AllOrderedStructuredAttributes
 : Map[String, Set[views.DataTypedAttributeInfo]],

 mc2AllUnorderedAtomicAttributes
 : Map[String, Set[views.DataTypedAttributeInfo]],

 mc2AllUnorderedEnumerationAttributes
 : Map[String, Set[views.DataTypedAttributeInfo]],

 mc2AllUnorderedStructuredAttributes
 : Map[String, Set[views.DataTypedAttributeInfo]],

 mc2ForwardAssociationsViews
 : Map[String, Vector[views.AssociationInfo]],

 mc2ReverseAssociationsViews
 : Map[String, Vector[views.AssociationInfo]]) {

  def getMetaclassView(mcName: String)
  : views.MetaClassView
  = metaClassViews
    .find(_.metaClass.name.value == mcName)
    .getOrElse {
      throw new java.lang.IllegalArgumentException("UMLMetamodelResolver: lookup error for metaclass: " + mcName)
    }

  def getMetaclassDataAttributeView(mcName: String, aName: String)
  : (Int, views.DataTypedAttributeInfo)
  = getMetaclassView(mcName).getDataAttributeInfo(aName)

  def getMetaAssociationView(aName: String)
  : views.AssociationInfo
  = associationViews
    .find(_.name.value == aName)
    .getOrElse {
      throw new java.lang.IllegalArgumentException("UMLMetamodelResolver: lookup error for meta association: " + aName)
    }
}

object UMLMetamodelResolver {

  def initialize
  ( primitiveTypesR: OTIMOFLibraryTables,
    umlR: OTIMOFMetamodelTables )
  : UMLMetamodelResolver
  = {

    val metaclasses
    : Vector[tables.metamodel.OTIMOFMetaClass]
    = umlR.metaClasses.to[Vector].sortBy(_.name.value)

    val associations
    : Vector[tables.metamodel.OTIMOFMetaAssociation]
    = umlR.metaAssociations.to[Vector].sortBy(_.name.value)

    val associationViews
    : Vector[views.AssociationInfo]
    = for {
      ma <- associations
      maUUID = ma.uuid.value
      ma2source <- umlR.metaAssociation2Source.find(_.association.value == maUUID)
      sourceUUID = ma2source.sourceEnd.value
      sourceEnd <- umlR.metaAssociationEnds.find(_.uuid.value == sourceUUID)
      sourceFind = (f: features.FeatureInfo) => f.feature.value == sourceUUID
      src2lower <- umlR.featureLowerBounds.find(sourceFind)
      src2upper <- umlR.featureUpperBounds.find(sourceFind)
      src2ord <- umlR.featureOrdering.find(sourceFind)
      src2Type <- umlR.metaAssociationEnd2MetaClass.find(sourceUUID == _.associationEnd.value)
      srcMetaclass <- metaclasses.find(src2Type.`type`.value == _.uuid.value)

      srcInfo = views.AssociationEndInfo(
        uuid = sourceEnd.uuid, name=sourceEnd.name,
        lower=src2lower.lower, upper=src2upper.upper,
        isOrdered=src2ord.isOrdered, metaclassType=srcMetaclass)

      ma2target <- umlR.metaAssociation2Target.find(_.association.value == maUUID)
      targetUUID = ma2target.targetEnd.value
      targetEnd <- umlR.metaAssociationEnds.find(_.uuid.value == targetUUID)
      targetFind = (f: features.FeatureInfo) => f.feature.value == targetUUID
      trg2lower <- umlR.featureLowerBounds.find(targetFind)
      trg2upper <- umlR.featureUpperBounds.find(targetFind)
      trg2ord <- umlR.featureOrdering.find(targetFind)
      trg2Type <- umlR.metaAssociationEnd2MetaClass.find(targetUUID == _.associationEnd.value)
      trgMetaclass <- metaclasses.find(trg2Type.`type`.value == _.uuid.value)

      trgInfo = views.AssociationEndInfo(
        uuid = targetEnd.uuid, name=targetEnd.name,
        lower=trg2lower.lower, upper=trg2upper.upper,
        isOrdered=trg2ord.isOrdered, metaclassType=trgMetaclass)

      info = views.AssociationInfo(
        uuid = ma.uuid,
        name = ma.name,
        source = srcInfo,
        target = trgInfo,
        targetIsComposite = targetEnd.isCompositeTarget)

      _ = scala.Predef.require(!(srcInfo.isOrdered && trgInfo.isOrdered), info.toString)
      _ = scala.Predef.require(!(info.targetIsComposite && srcInfo.isOrdered), info.toString)

    //_ = java.lang.System.out.println(info)
    } yield info

    val mc2ForwardAssoc =
      associationViews
        .groupBy(_.source.metaclassType.name.value)
        .map { case (mc, as) =>
            mc -> as.sortBy(_.target.name.value)
        }

    val mc2ReverseAssoc =
      associationViews
        .groupBy(_.target.metaclassType.name.value)
        .map { case (mc, as) =>
          mc -> as.sortBy(_.source.name.value)
        }

    val a2view =
      associationViews
      .map { av => av.uuid -> av }
      .toMap

    val ma2SSGeneralizations =
      a2view
      .flatMap { case (subUUID, subAV) =>
        val sups =
          umlR
            .metaAssociationGeneralizations
            .flatMap {
              case tables.metamodel.OTIMOFMetaAssociationGeneralization(_, sub, sup, false, false) if sub == subUUID =>
                Some(a2view(sup))
              case _ =>
                None
            }
            .toSet
        if (sups.nonEmpty)
          Some(subAV -> sups)
        else
          None
      }

    val ma2SRGeneralizations =
      a2view
        .flatMap { case (subUUID, subAV) =>
          val sups =
            umlR
              .metaAssociationGeneralizations
              .flatMap {
                case tables.metamodel.OTIMOFMetaAssociationGeneralization(_, sub, sup, false, true) if sub == subUUID =>
                  Some(a2view(sup))
                case _ =>
                  None
              }
              .toSet
          if (sups.nonEmpty)
            Some(subAV -> sups)
          else
            None
        }

    val ma2RSGeneralizations =
      a2view
        .flatMap { case (subUUID, subAV) =>
          val sups =
            umlR
              .metaAssociationGeneralizations
              .flatMap {
                case tables.metamodel.OTIMOFMetaAssociationGeneralization(_, sub, sup, true, false) if sub == subUUID =>
                  Some(a2view(sup))
                case _ =>
                  None
              }
              .toSet
          if (sups.nonEmpty)
            Some(subAV -> sups)
          else
            None
        }

    val ma2RRGeneralizations =
      a2view
        .flatMap { case (subUUID, subAV) =>
          val sups =
            umlR
              .metaAssociationGeneralizations
              .flatMap {
                case tables.metamodel.OTIMOFMetaAssociationGeneralization(_, sub, sup, true, true) if sub == subUUID =>
                  Some(a2view(sup))
                case _ =>
                  None
              }
              .toSet
          if (sups.nonEmpty)
            Some(subAV -> sups)
          else
            None
        }

    val mcGeneral2ParentPairs
    : Set[(tables.metamodel.OTIMOFMetaClass, tables.metamodel.OTIMOFMetaClass)]
    = umlR
      .metaClassGeneralizations
      .to[Set]
      .flatMap { g =>
        for {
          s <- metaclasses.find(_.uuid == g.specific)
          p <- metaclasses.find(_.uuid == g.general)
        } yield Tuple2(s, p)
      }

    val mc2DirectGeneralizations
    : Map[tables.metamodel.OTIMOFMetaClass, Set[tables.metamodel.OTIMOFMetaClass]]
    = mcGeneral2ParentPairs
      .groupBy(_._1)
      .map { case (sub, sub2sup) => sub -> sub2sup.map(_._2) }

    val mc2DirectSpecializations
    : Map[tables.metamodel.OTIMOFMetaClass, Set[tables.metamodel.OTIMOFMetaClass]]
    = mcGeneral2ParentPairs
      .groupBy(_._2)
      .map { case (sup, sub2sup) => sup -> sub2sup.map(_._1) }

    def getSpecializedMetaclasses
    ( mc: tables.metamodel.OTIMOFMetaClass )
    : Set[tables.metamodel.OTIMOFMetaClass]
    = mc2DirectSpecializations
      .getOrElse(mc, Set.empty[tables.metamodel.OTIMOFMetaClass])

    def mc2AllAttributes[T]
    (mc2DirectAttributes: Map[tables.metamodel.OTIMOFMetaClass, Set[T]])
    : Map[tables.metamodel.OTIMOFMetaClass, Set[T]]
    = metaclasses
      .to[Set]
      .foldLeft(Map.empty[tables.metamodel.OTIMOFMetaClass, Set[T]]) {
        case (acc: Map[tables.metamodel.OTIMOFMetaClass, Set[T]],
        mc: tables.metamodel.OTIMOFMetaClass) =>

          val mcAttribs = mc2DirectAttributes.getOrElse(mc, Set.empty[T])

          def combine
          (current: Map[tables.metamodel.OTIMOFMetaClass, Set[T]],
           s: tables.metamodel.OTIMOFMetaClass)
          : Map[tables.metamodel.OTIMOFMetaClass, Set[T]]
          = current
            .updated(s,
              mcAttribs ++ current.getOrElse(s, Set.empty[T]))

          transitiveClosure(mc, acc)(getSpecializedMetaclasses, combine)
      }

    import Selectable._

    def attribute2info
    (uuid: common.EntityUUID)
    : Option[views.DataTypedAttributeInfo]
    =  umlR.attributes
      .select { case a: features.DataTypedAttributeProperty => a }
      .find(_.uuid == uuid)
      .flatMap { a =>
        umlR.attribute2type
          .find { a2t => a2t.attribute == a.uuid }
          .flatMap { a2t =>
            umlR.featureLowerBounds
              .find { flb => flb.feature == a.uuid }
              .flatMap { flb =>
                umlR.featureUpperBounds
                  .find { fub => fub.feature == a.uuid }
                  .flatMap { fub =>
                    umlR.featureOrdering
                      .find { fo => fo.feature == a.uuid }
                      .flatMap { fo =>
                        (primitiveTypesR.primitiveDataTypes ++ primitiveTypesR.enumerationDataTypes)
                          .find { dt => dt.uuid == a2t.`type` }
                          .flatMap { dt =>
                            Some(views.DataTypedAttributeInfo(
                              resource = a.resource,
                              uuid = a.uuid,
                              name = a.name,
                              lower = flb.lower,
                              upper = fub.upper,
                              isOrdered = fo.isOrdered,
                              classifier = dt))
                          }
                      }
                  }
              }
          }
      }

    def mc2Attributes
    (attributeMap: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute])
    : Map[tables.metamodel.OTIMOFMetaClass, Set[views.DataTypedAttributeInfo]]
    = attributeMap
      .flatMap { mc2attrib =>
        for {

          mc <-
          metaclasses.find(_.uuid == mc2attrib.metaClass)

          attrib <- attribute2info(mc2attrib.attribute)

        } yield mc -> attrib
      }
      .groupBy(_._1)
      .map { case (mc, m2a) => mc -> m2a.map(_._2).to[Set] }

    def mc2AllNamedAttributes
    (attributeMap: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute])
    : Map[String, Set[views.DataTypedAttributeInfo]]
    = mc2AllAttributes(mc2Attributes(attributeMap)).map { case (s, as) => s.name.value -> as }

    val mcName2UUID
    : Map[String, common.EntityUUID]
    = metaclasses.map { mc =>
      mc.name.value -> mc.uuid
    }.toMap

    def collectMCAttributes
    (mc: tables.metamodel.OTIMOFMetaClass,
     attributeMap: Iterable[tables.metamodel.OTIMOFMetaClass2Attribute])
    : Map[Int, views.DataTypedAttributeInfo]
    = attributeMap
      .flatMap {
        case tables.metamodel.OTIMOFMetaClass2Attribute(_, mcUUID, aUUID, index) if mcUUID == mc.uuid =>
          for {
            a <- attribute2info(aUUID)
          } yield (index -> a)

        case tables.metamodel.OTIMOFMetaClass2Attribute(_, _, _, _) =>
          None
      }
      .toMap

    val metaClassViews
    : Vector[views.MetaClassView]
    = metaclasses.map { mc =>
      val as = mc2Attributes(umlR.metaClass2orderedAtomicAttribute)
      views.MetaClassView(
        metaClass = mc,
        directGeneralizations = mc2DirectGeneralizations.getOrElse(mc, Set.empty),
        directSpecializations = mc2DirectSpecializations.getOrElse(mc, Set.empty),
        forwardAssociations = mc2ForwardAssoc.getOrElse(mc.name.value, Vector.empty),
        reverseAssociations = mc2ReverseAssoc.getOrElse(mc.name.value, Vector.empty),
        orderedAtomicAttributes = collectMCAttributes(mc, umlR.metaClass2orderedAtomicAttribute),
        orderedEnumerationAttributes = collectMCAttributes(mc, umlR.metaClass2orderedEnumerationAttribute),
        orderedStructuredAttributes = collectMCAttributes(mc, umlR.metaClass2orderedStructuredAttribute),
        unorderedAtomicAttributes = collectMCAttributes(mc, umlR.metaClass2unorderedAtomicAttribute),
        unorderedEnumerationAttributes = collectMCAttributes(mc, umlR.metaClass2unorderedEnumerationAttribute),
        unorderedStructuredAttributes = collectMCAttributes(mc, umlR.metaClass2unorderedStructuredAttribute))
    }

    UMLMetamodelResolver(primitiveTypesR, umlR,
      metaclasses,
      metaClassViews,
      associations,
      associationViews,
      mc2DirectGeneralizations,
      mc2DirectSpecializations,
      ma2SSGeneralizations,
      ma2SRGeneralizations,
      ma2RSGeneralizations,
      ma2RRGeneralizations,
      mcName2UUID,
      mc2AllOrderedAtomicAttributes = mc2AllNamedAttributes(umlR.metaClass2orderedAtomicAttribute),
      mc2AllOrderedEnumerationAttributes = mc2AllNamedAttributes(umlR.metaClass2orderedEnumerationAttribute),
      mc2AllOrderedStructuredAttributes = mc2AllNamedAttributes(umlR.metaClass2orderedStructuredAttribute),
      mc2AllUnorderedAtomicAttributes = mc2AllNamedAttributes(umlR.metaClass2unorderedAtomicAttribute),
      mc2AllUnorderedEnumerationAttributes = mc2AllNamedAttributes(umlR.metaClass2unorderedEnumerationAttribute),
      mc2AllUnorderedStructuredAttributes = mc2AllNamedAttributes(umlR.metaClass2unorderedStructuredAttribute),
      mc2ForwardAssociationsViews = mc2ForwardAssoc,
      mc2ReverseAssociationsViews = mc2ReverseAssoc)
  }

  def transitiveClosure[E, V]
  ( initial: E,
    current: V )
  ( implicit next: E => Set[E], combine: (V, E) => V)
  : V
  = transitiveClosureLoop(Set[E](initial), Set.empty[E], current)

  @scala.annotation.tailrec
  def transitiveClosureLoop[E, V]
  ( queue: Set[E],
    visited: Set[E],
    acc: V)
  ( implicit next: E => Set[E], combine: (V, E) => V)
  : V
  = if (queue.isEmpty)
    acc
  else {
    val e = queue.head
    val rest = queue - e
    if (visited.contains(e))
      transitiveClosureLoop(rest, visited, acc)
    else
      transitiveClosureLoop(rest ++ next(e), visited + e, combine(acc, e))
  }


}