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

import java.io.File
import java.lang.System
import java.nio.file.Path

import akka.NotUsed
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString
import play.api.libs.json._

import scala.collection.immutable._
import scala.concurrent._
import scala.Unit
import scala.Predef.String
import scala.util.{Success, Try}

object Tables {

  def resolveFile(dir: Path, filename: String)
  : File
  = dir.resolve(filename).toFile

  def fileSink[T](file: File)(implicit formats: Format[T])
  : Sink[T, Future[IOResult]]
  = Flow[T]
    .map(r => ByteString(Json.stringify(Json.toJson(r)) + "\n"))
    .toMat(FileIO.toFile(file))(Keep.right)

  def inputJsonFile
  (dir: Path, fileName: String)
  : Source[ByteString, Future[IOResult]]
  = FileIO
    .fromFile(resolveFile(dir, fileName))

  def stream2json[T]
  (implicit formats: Format[T])
  : Flow[ByteString, JsResult[T], NotUsed]
  = Flow[ByteString]
    .via(Framing.delimiter(ByteString(System.lineSeparator), maximumFrameLength = 8192, allowTruncation = true))
    .map(x => x.decodeString(ByteString.UTF_8))
    .map(s =>
      Json.parse(s))
    .map(js =>
      js.validate[T])

  def json2Iterable[T]
  : Sink[JsResult[T], Future[(JsError, Vector[T])]]
  = Sink.fold(JsError(), Vector.empty[T]) {
    case ((error, results), JsSuccess(r, _)) =>
      (error, results :+ r)
    case ((e1, results), e2: JsError) =>
      (JsError.merge(e1, e2), results)
  }

  def jsonFile2iterable[T]
  (dir: Path, fileName: String)
  (implicit formats: Format[T])
  : RunnableGraph[Future[(JsError, Vector[T])]]
  = inputJsonFile(dir, fileName).via(stream2json[T]).toMat(json2Iterable[T])(Keep.right)

  def loadLibrary
  (dir: Path)
  (implicit mat: ActorMaterializer, ec: ExecutionContext)
  : Future[(JsError, OTIMOFLibraryTables)]
  = {
    val resources =
      jsonFile2iterable[tables.OTIMOFResourceType](dir, "resources.json").run()
    val importedLibraries =
      jsonFile2iterable[OTIMOFResourceLibraryImport](dir, "importedLibraries.json").run()
    val primitiveDataTypes =
      jsonFile2iterable[tables.library.OTIMOFPrimitiveDataType](dir, "primitiveDataTypes.json").run()
    val enumerationDataTypes =
      jsonFile2iterable[tables.library.OTIMOFEnumerationDataType](dir, "enumerationDataTypes.json").run()
    val enumeration2literals =
      jsonFile2iterable[tables.library.OTIMOFEnumeration2Literal](dir, "enumeration2literals.json").run()
    val enumerationLiterals =
      jsonFile2iterable[common.EnumerationLiteralValue](dir, "enumerationLiterals.json").run()
    val structuredDataTypes =
      jsonFile2iterable[tables.library.OTIMOFStructuredDataType](dir, "structuredDataTypes.json").run()
    val generalizations =
      jsonFile2iterable[tables.library.OTIMOFStructuredDataTypeGeneralization](dir, "generalizations.json").run()
    val structure2attribute =
      jsonFile2iterable[tables.library.OTIMOFStructuredDatatype2Attribute](dir, "structure2attribute.json").run()
    val attributes =
      jsonFile2iterable[features.DataTypedAttributeProperty](dir, "attributes.json").run()
    val featureLowerBounds =
      jsonFile2iterable[features.FeatureLowerBound](dir, "featureLowerBounds.json").run()
    val featureUpperBounds =
      jsonFile2iterable[features.FeatureUpperBound](dir, "featureUpperBounds.json").run()
    val featureOrdering =
      jsonFile2iterable[features.FeatureOrdering](dir, "featureOrdering.json").run()
    val attribute2type =
      jsonFile2iterable[features.AttributeProperty2DataType](dir, "attribute2type.json").run()

    for {
      r <- resources
      il <- importedLibraries
      pd <- primitiveDataTypes
      e <- enumerationDataTypes
      e2l <- enumeration2literals
      el <- enumerationLiterals
      s <- structuredDataTypes
      g <- generalizations
      s2a <- structure2attribute
      a <- attributes
      fl <- featureLowerBounds
      fu <- featureUpperBounds
      fo <- featureOrdering
      a2t <- attribute2type
    } yield {
      val jsError =
        Seq(r, il, pd, e, e2l, s, g, s2a, a, fl, fu, fo, a2t)
          .foldLeft[JsError](JsError()) { case (e1, (e2, _)) => JsError.merge(e1, e2) }
      val tables =
        OTIMOFLibraryTables(
          resourceType = r._2,
          importedLibraries = il._2,
          primitiveDataTypes = pd._2,
          enumerationDataTypes = e._2,
          enumeration2literals = e2l._2,
          enumerationLiterals = el._2,
          structuredDataTypes = s._2,
          generalizations = g._2,
          structure2attribute = s2a._2,
          attributes = a._2,
          featureLowerBounds = fl._2,
          featureUpperBounds = fu._2,
          featureOrdering = fo._2,
          attribute2type = a2t._2)

      (jsError, tables)
    }
  }

  def exportLibraries
  (resultDir: Path,
   libs: Vector[OTIMOFLibraryTables])
  ( implicit mat: ActorMaterializer)
  : Try[Unit]
    = {
    val resourcesOut = Source(libs.flatMap(_.resourceType))
    val resourcesFile = resolveFile(resultDir, "resources.json")
    val resourcesIn = fileSink[tables.OTIMOFResourceType](resourcesFile)

    val importedLibrariesOut = Source(libs.flatMap(_.importedLibraries))
    val importedLibrariesFile = resolveFile(resultDir, "importedLibraries.json")
    val importedLibrariesIn = fileSink[OTIMOFResourceLibraryImport](importedLibrariesFile)

    val primitiveDataTypesOut = Source(libs.flatMap(_.primitiveDataTypes))
    val primitiveDataTypesFile = resolveFile(resultDir, "primitiveDataTypes.json")
    val primitiveDataTypesIn = fileSink[tables.library.OTIMOFPrimitiveDataType](primitiveDataTypesFile)

    val enumerationDataTypesOut = Source(libs.flatMap(_.enumerationDataTypes))
    val enumerationDataTypesFile = resolveFile(resultDir, "enumerationDataTypes.json")
    val enumerationDataTypesIn = fileSink[tables.library.OTIMOFEnumerationDataType](enumerationDataTypesFile)

    val enumeration2literalsOut = Source(libs.flatMap(_.enumeration2literals))
    val enumeration2literalsFile = resolveFile(resultDir, "enumeration2literals.json")
    val enumeration2literalsIn = fileSink[tables.library.OTIMOFEnumeration2Literal](enumeration2literalsFile)

    val enumerationLiteralsOut = Source(libs.flatMap(_.enumerationLiterals))
    val enumerationLiteralsFile = resolveFile(resultDir, "enumerationLiterals.json")
    val enumerationLiteralsIn = fileSink[common.EnumerationLiteralValue](enumerationLiteralsFile)

    val structuredDataTypesOut = Source(libs.flatMap(_.structuredDataTypes))
    val structuredDataTypesFile = resolveFile(resultDir, "structuredDataTypes.json")
    val structuredDataTypesIn = fileSink[tables.library.OTIMOFStructuredDataType](structuredDataTypesFile)

    val generalizationsOut = Source(libs.flatMap(_.generalizations))
    val generalizationsFile = resolveFile(resultDir, "generalizations.json")
    val generalizationsIn = fileSink[tables.library.OTIMOFStructuredDataTypeGeneralization](generalizationsFile)

    val structure2attributeOut = Source(libs.flatMap(_.structure2attribute))
    val structure2attributeFile = resolveFile(resultDir, "structure2attribute.json")
    val structure2attributeIn = fileSink[tables.library.OTIMOFStructuredDatatype2Attribute](structure2attributeFile)

    val attributesOut = Source(libs.flatMap(_.attributes))
    val attributesFile = resolveFile(resultDir, "attributes.json")
    val attributesIn = fileSink[features.DataTypedAttributeProperty](attributesFile)

    val featureLowerBoundsOut = Source(libs.flatMap(_.featureLowerBounds))
    val featureLowerBoundsFile = resolveFile(resultDir, "featureLowerBounds.json")
    val featureLowerBoundsIn = fileSink[features.FeatureLowerBound](featureLowerBoundsFile)

    val featureUpperBoundsOut = Source(libs.flatMap(_.featureUpperBounds))
    val featureUpperBoundsFile = resolveFile(resultDir, "featureUpperBounds.json")
    val featureUpperBoundsIn = fileSink[features.FeatureUpperBound](featureUpperBoundsFile)

    val featureOrderingOut = Source(libs.flatMap(_.featureOrdering))
    val featureOrderingFile = resolveFile(resultDir, "featureOrdering.json")
    val featureOrderingIn = fileSink[features.FeatureOrdering](featureOrderingFile)


    val attribute2typeOut = Source(libs.flatMap(_.attribute2type))
    val attribute2typeFile = resolveFile(resultDir, "attribute2type.json")
    val attribute2typeIn = fileSink[features.AttributeProperty2DataType](attribute2typeFile)


    // flows

    val graph = RunnableGraph.fromGraph(GraphDSL.create() { implicit b =>
      import GraphDSL.Implicits._

      resourcesOut ~> resourcesIn

      importedLibrariesOut ~> importedLibrariesIn

      primitiveDataTypesOut ~> primitiveDataTypesIn

      enumerationDataTypesOut ~> enumerationDataTypesIn

      enumeration2literalsOut ~> enumeration2literalsIn

      enumerationLiteralsOut ~> enumerationLiteralsIn

      structuredDataTypesOut ~> structuredDataTypesIn

      generalizationsOut ~> generalizationsIn

      structure2attributeOut ~> structure2attributeIn

      attributesOut ~> attributesIn

      featureLowerBoundsOut ~> featureLowerBoundsIn

      featureUpperBoundsOut ~> featureUpperBoundsIn

      featureOrderingOut ~> featureOrderingIn

      attribute2typeOut ~> attribute2typeIn

      ClosedShape
    })

    graph.run()

    Success(())
  }

  def loadMetamodel
  (dir: Path)
  (implicit mat: ActorMaterializer, ec: ExecutionContext)
  : Future[(JsError, OTIMOFMetamodelTables)]
  = {
    val resources =
      jsonFile2iterable[tables.OTIMOFResourceType](dir, "resources.json").run()
    val importedMetamodels =
      jsonFile2iterable[OTIMOFResourceMetamodelImport](dir, "importedMetamodels.json").run()
    val importedLibraries =
      jsonFile2iterable[OTIMOFResourceLibraryImport](dir, "importedLibraries.json").run()
    val metaClasses =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaClass](dir, "metaClasses.json").run()
    val metaClassGeneralizations =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaClassGeneralization](dir, "metaClassGeneralizations.json").run()
    val metaAssociations =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaAssociation](dir, "metaAssociations.json").run()
    val metaAssociationGeneralizations =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaAssociationGeneralization](dir, "metaAssociationGeneralizations.json").run()

    val metaClass2orderedAtomicAttribute =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaClass2Attribute](dir, "metaClass2orderedAtomicAttribute.json").run()
    val metaClass2orderedEnumerationAttribute =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaClass2Attribute](dir, "metaClass2orderedEnumerationAttribute.json").run()
    val metaClass2orderedStructuredAttribute =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaClass2Attribute](dir, "metaClass2orderedStructuredAttribute.json").run()
    val metaClass2unorderedAtomicAttribute =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaClass2Attribute](dir, "metaClass2unorderedAtomicAttribute.json").run()
    val metaClass2unorderedEnumerationAttribute =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaClass2Attribute](dir, "metaClass2unorderedEnumerationAttribute.json").run()
    val metaClass2unorderedStructuredAttribute =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaClass2Attribute](dir, "metaClass2unorderedStructuredAttribute.json").run()

    val metaAssociation2Source =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaAssociation2SourceEndProperty](dir, "metaAssociation2Source.json").run()
    val metaAssociation2Target =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaAssociation2TargetEndProperty](dir, "metaAssociation2Target.json").run()
    val metaAssociationEnds =
      jsonFile2iterable[features.AssociationEnd](dir, "metaAssociationEnds.json").run()
    val metaAssociationEnd2MetaClass =
      jsonFile2iterable[tables.metamodel.OTIMOFMetaAssociationEndProperty2MetaClassType](dir, "metaAssociationEnd2MetaClass.json").run()

    val attributes =
      jsonFile2iterable[features.DataTypedAttributeProperty](dir, "attributes.json").run()
    val featureLowerBounds =
      jsonFile2iterable[features.FeatureLowerBound](dir, "featureLowerBounds.json").run()
    val featureUpperBounds =
      jsonFile2iterable[features.FeatureUpperBound](dir, "featureUpperBounds.json").run()
    val featureOrdering =
      jsonFile2iterable[features.FeatureOrdering](dir, "featureOrdering.json").run()
    val attribute2type =
      jsonFile2iterable[features.AttributeProperty2DataType](dir, "attribute2type.json").run()

    for {
      r <- resources
      im <- importedMetamodels
      il <- importedLibraries
      mc <- metaClasses
      gc <- metaClassGeneralizations
      ma <- metaAssociations
      ga <- metaAssociationGeneralizations
      mc2oa <- metaClass2orderedAtomicAttribute
      mc2oe <- metaClass2orderedEnumerationAttribute
      mc2os <- metaClass2orderedStructuredAttribute
      mc2ua <- metaClass2unorderedAtomicAttribute
      mc2ue <- metaClass2unorderedEnumerationAttribute
      mc2us <- metaClass2unorderedStructuredAttribute
      ma2s <- metaAssociation2Source
      ma2t <- metaAssociation2Target
      mae <- metaAssociationEnds
      mae2mc <- metaAssociationEnd2MetaClass
      a <- attributes
      fl <- featureLowerBounds
      fu <- featureUpperBounds
      fo <- featureOrdering
      a2t <- attribute2type
    } yield
      (
        Seq(r, im, il, mc, gc, ma, ga, mc2oa, mc2oe, mc2os, mc2ua, mc2ue, mc2us, ma2s, ma2t, mae, mae2mc, a, fl, fu, fo, a2t)
          .foldLeft[JsError](JsError()) { case (e1, (e2, _)) => JsError.merge(e1, e2) },
        OTIMOFMetamodelTables(
          resourceType = r._2,
          importedMetamodels = im._2,
          importedLibraries = il._2,
          metaClasses = mc._2,
          metaClassGeneralizations = gc._2,
          metaAssociations = ma._2,
          metaAssociationGeneralizations = ga._2,
          metaClass2orderedAtomicAttribute = mc2oa._2,
          metaClass2orderedEnumerationAttribute = mc2oe._2,
          metaClass2orderedStructuredAttribute = mc2os._2,
          metaClass2unorderedAtomicAttribute = mc2ua._2,
          metaClass2unorderedEnumerationAttribute = mc2ue._2,
          metaClass2unorderedStructuredAttribute = mc2us._2,
          metaAssociation2Source = ma2s._2,
          metaAssociation2Target = ma2t._2,
          metaAssociationEnds = mae._2,
          metaAssociationEnd2MetaClass = mae2mc._2,
          attributes = a._2,
          featureLowerBounds = fl._2,
          featureUpperBounds = fu._2,
          featureOrdering = fo._2,
          attribute2type = a2t._2)
        )

  }

  def exportMetamodels
  (resultDir: Path,
   mms: Vector[OTIMOFMetamodelTables])
  (implicit mat: ActorMaterializer)
  : Try[Unit]
  = {
    val resourcesOut = Source(mms.flatMap(_.resourceType))
    val resourcesFile = resolveFile(resultDir, "resources.json")
    val resourcesIn = fileSink[tables.OTIMOFResourceType](resourcesFile)

    val importedMetamodelsOut = Source(mms.flatMap(_.importedMetamodels))
    val importedMetamodelsFile = resolveFile(resultDir, "importedMetamodels.json")
    val importedMetamodelsIn = fileSink[OTIMOFResourceMetamodelImport](importedMetamodelsFile)

    val importedLibrariesOut = Source(mms.flatMap(_.importedLibraries))
    val importedLibrariesFile = resolveFile(resultDir, "importedLibraries.json")
    val importedLibrariesIn = fileSink[OTIMOFResourceLibraryImport](importedLibrariesFile)

    val metaClassesOut = Source(mms.flatMap(_.metaClasses))
    val metaClassesFile = resolveFile(resultDir, "metaClasses.json")
    val metaClassesIn = fileSink[tables.metamodel.OTIMOFMetaClass](metaClassesFile)

    val metaClassGeneralizationsOut = Source(mms.flatMap(_.metaClassGeneralizations))
    val metaClassGeneralizationsFile = resolveFile(resultDir, "metaClassGeneralizations.json")
    val metaClassGeneralizationsIn = fileSink[tables.metamodel.OTIMOFMetaClassGeneralization](metaClassGeneralizationsFile)

    val metaAssociationsOut = Source(mms.flatMap(_.metaAssociations))
    val metaAssociationsFile = resolveFile(resultDir, "metaAssociations.json")
    val metaAssociationsIn = fileSink[tables.metamodel.OTIMOFMetaAssociation](metaAssociationsFile)

    val metaAssociationGeneralizationsOut = Source(mms.flatMap(_.metaAssociationGeneralizations))
    val metaAssociationGeneralizationsFile = resolveFile(resultDir, "metaAssociationGeneralizations.json")
    val metaAssociationGeneralizationsIn = fileSink[tables.metamodel.OTIMOFMetaAssociationGeneralization](metaAssociationGeneralizationsFile)

    val metaClass2orderedAtomicAttributeOut = Source(mms.flatMap(_.metaClass2orderedAtomicAttribute))
    val metaClass2orderedAtomicAttributeFile = resolveFile(resultDir, "metaClass2orderedAtomicAttribute.json")
    val metaClass2orderedAtomicAttributeIn = fileSink[tables.metamodel.OTIMOFMetaClass2Attribute](metaClass2orderedAtomicAttributeFile)

    val metaClass2orderedEnumerationAttributeOut = Source(mms.flatMap(_.metaClass2orderedEnumerationAttribute))
    val metaClass2orderedEnumerationAttributeFile = resolveFile(resultDir, "metaClass2orderedEnumerationAttribute.json")
    val metaClass2orderedEnumerationAttributeIn = fileSink[tables.metamodel.OTIMOFMetaClass2Attribute](metaClass2orderedEnumerationAttributeFile)

    val metaClass2orderedStructuredAttributeOut = Source(mms.flatMap(_.metaClass2orderedStructuredAttribute))
    val metaClass2orderedStructuredAttributeFile = resolveFile(resultDir, "metaClass2orderedStructuredAttribute.json")
    val metaClass2orderedStructuredAttributeIn = fileSink[tables.metamodel.OTIMOFMetaClass2Attribute](metaClass2orderedStructuredAttributeFile)

    val metaClass2unorderedAtomicAttributeOut = Source(mms.flatMap(_.metaClass2unorderedAtomicAttribute))
    val metaClass2unorderedAtomicAttributeFile = resolveFile(resultDir, "metaClass2unorderedAtomicAttribute.json")
    val metaClass2unorderedAtomicAttributeIn = fileSink[tables.metamodel.OTIMOFMetaClass2Attribute](metaClass2unorderedAtomicAttributeFile)

    val metaClass2unorderedEnumerationAttributeOut = Source(mms.flatMap(_.metaClass2unorderedEnumerationAttribute))
    val metaClass2unorderedEnumerationAttributeFile = resolveFile(resultDir, "metaClass2unorderedEnumerationAttribute.json")
    val metaClass2unorderedEnumerationAttributeIn = fileSink[tables.metamodel.OTIMOFMetaClass2Attribute](metaClass2unorderedEnumerationAttributeFile)

    val metaClass2unorderedStructuredAttributeOut = Source(mms.flatMap(_.metaClass2unorderedStructuredAttribute))
    val metaClass2unorderedStructuredAttributeFile = resolveFile(resultDir, "metaClass2unorderedStructuredAttribute.json")
    val metaClass2unorderedStructuredAttributeIn = fileSink[tables.metamodel.OTIMOFMetaClass2Attribute](metaClass2unorderedStructuredAttributeFile)

    val metaAssociation2SourceOut = Source(mms.flatMap(_.metaAssociation2Source))
    val metaAssociation2SourceFile = resolveFile(resultDir, "metaAssociation2Source.json")
    val metaAssociation2SourceIn = fileSink[tables.metamodel.OTIMOFMetaAssociation2SourceEndProperty](metaAssociation2SourceFile)

    val metaAssociation2TargetOut = Source(mms.flatMap(_.metaAssociation2Target))
    val metaAssociation2TargetFile = resolveFile(resultDir, "metaAssociation2Target.json")
    val metaAssociation2TargetIn = fileSink[tables.metamodel.OTIMOFMetaAssociation2TargetEndProperty](metaAssociation2TargetFile)

    val metaAssociationEndsOut = Source(mms.flatMap(_.metaAssociationEnds))
    val metaAssociationEndsFile = resolveFile(resultDir, "metaAssociationEnds.json")
    val metaAssociationEndsIn = fileSink[features.AssociationEnd](metaAssociationEndsFile)

    val metaAssociationEnd2MetaClassOut = Source(mms.flatMap(_.metaAssociationEnd2MetaClass))
    val metaAssociationEnd2MetaClassFile = resolveFile(resultDir, "metaAssociationEnd2MetaClass.json")
    val metaAssociationEnd2MetaClassIn = fileSink[tables.metamodel.OTIMOFMetaAssociationEndProperty2MetaClassType](metaAssociationEnd2MetaClassFile)

    val attributesOut = Source(mms.flatMap(_.attributes))
    val attributesFile = resolveFile(resultDir, "attributes.json")
    val attributesIn = fileSink[features.DataTypedAttributeProperty](attributesFile)

    val featureLowerBoundsOut = Source(mms.flatMap(_.featureLowerBounds))
    val featureLowerBoundsFile = resolveFile(resultDir, "featureLowerBounds.json")
    val featureLowerBoundsIn = fileSink[features.FeatureLowerBound](featureLowerBoundsFile)

    val featureUpperBoundsOut = Source(mms.flatMap(_.featureUpperBounds))
    val featureUpperBoundsFile = resolveFile(resultDir, "featureUpperBounds.json")
    val featureUpperBoundsIn = fileSink[features.FeatureUpperBound](featureUpperBoundsFile)

    val featureOrderingOut = Source(mms.flatMap(_.featureOrdering))
    val featureOrderingFile = resolveFile(resultDir, "featureOrdering.json")
    val featureOrderingIn = fileSink[features.FeatureOrdering](featureOrderingFile)

    val attribute2typeOut = Source(mms.flatMap(_.attribute2type))
    val attribute2typeFile = resolveFile(resultDir, "attribute2type.json")
    val attribute2typeIn = fileSink[features.AttributeProperty2DataType](attribute2typeFile)


    // flows

    val graph = RunnableGraph.fromGraph(GraphDSL.create() { implicit b =>
      import GraphDSL.Implicits._

      resourcesOut ~> resourcesIn

      importedMetamodelsOut ~> importedMetamodelsIn

      importedLibrariesOut ~> importedLibrariesIn

      metaClassesOut ~> metaClassesIn

      metaClassGeneralizationsOut ~> metaClassGeneralizationsIn

      metaAssociationsOut ~> metaAssociationsIn

      metaAssociationGeneralizationsOut ~> metaAssociationGeneralizationsIn

      metaClassGeneralizationsOut ~> metaClassGeneralizationsIn

      metaClass2orderedAtomicAttributeOut ~> metaClass2orderedAtomicAttributeIn

      metaClass2orderedEnumerationAttributeOut ~> metaClass2orderedEnumerationAttributeIn

      metaClass2orderedStructuredAttributeOut ~> metaClass2orderedStructuredAttributeIn

      metaClass2unorderedAtomicAttributeOut ~> metaClass2unorderedAtomicAttributeIn

      metaClass2unorderedEnumerationAttributeOut ~> metaClass2unorderedEnumerationAttributeIn

      metaClass2unorderedStructuredAttributeOut ~> metaClass2unorderedStructuredAttributeIn

      metaAssociation2SourceOut ~> metaAssociation2SourceIn

      metaAssociation2TargetOut ~> metaAssociation2TargetIn

      metaAssociationEndsOut ~> metaAssociationEndsIn

      metaAssociationEnd2MetaClassOut ~> metaAssociationEnd2MetaClassIn


      attributesOut ~> attributesIn

      featureLowerBoundsOut ~> featureLowerBoundsIn

      featureUpperBoundsOut ~> featureUpperBoundsIn

      featureOrderingOut ~> featureOrderingIn

      attribute2typeOut ~> attribute2typeIn

      ClosedShape
    })

    graph.run()

    Success(())
  }

  def exportProfilesAndModels
  (resultDir: Path,
   pfs: Vector[OTIMOFProfileTables],
   pks: Vector[OTIMOFModelTables])
  (implicit mat: ActorMaterializer)
  : Try[Unit]
  = {
    val resourcesOut = Source(pfs.flatMap(_.resourceType) ++ pks.flatMap(_.resourceType))

    val resourcesFile = resolveFile(resultDir, "resources.json")

    val resourcesIn = fileSink[tables.OTIMOFResourceType](resourcesFile)

    // profiles

    val extendedMetamodelsOut = Source(pfs.flatMap(_.extendedMetamodels))
    val extendedMetamodelsFile = resolveFile(resultDir, "extendedMetamodels.json")
    val extendedMetamodelsIn = fileSink[tables.profile.OTIMOFProfile2ExtendedMetamodel](extendedMetamodelsFile)

    val pfImportsOut = Source(pfs.flatMap(_.importedProfiles))
    val pfImportsFile = resolveFile(resultDir, "profileImports.json")
    val pfImportsIn = fileSink[OTIMOFResourceProfileImport](pfImportsFile)

    val libImportOut = Source(pfs.flatMap(_.importedLibraries))
    val libImportFile = resolveFile(resultDir, "libraryImports.json")
    val libImportIn = fileSink[OTIMOFResourceLibraryImport](libImportFile)

    val stereotypesOut = Source(pfs.flatMap(_.stereotypes))
    val stereotypesFile = resolveFile(resultDir, "stereotypes.json")
    val stereotypesIn = fileSink[tables.profile.OTIMOFStereotype](stereotypesFile)

    val extendedMetaclassesOut = Source(pfs.flatMap(_.extendedMetaclasses))
    val extendedMetaclassesFile = resolveFile(resultDir, "extendedMetaclasses.json")
    val extendedMetaclassesIn = fileSink[tables.profile.OTIMOFStereotype2ExtendedMetaclass](extendedMetaclassesFile)

    // models

    val metamodelsOut = Source(pks.flatMap(_.instantiatedMetamodels))
    val metamodelsFile = resolveFile(resultDir, "instantiatedMetamodels.json")
    val metamodelsIn = fileSink[OTIMOFResourceInstantiatedMetamodel](metamodelsFile)

    val appliedProfilesOut = Source(pks.flatMap(_.appliedProfiles))
    val appliedProfilesFile = resolveFile(resultDir, "appliedProfiles.json")
    val appliedProfilesIn = fileSink[OTIMOFResourceModelAppliedProfile](appliedProfilesFile)

    val elementsOut = Source(pks.flatMap(_.elements))
    val elementsFile = resolveFile(resultDir, "elements.json")
    val elementsIn = fileSink[tables.model.OTIMOFModelElement](elementsFile)

    val toolSpecificElementIDsOut = Source(pks.flatMap(_.toolSpecificElementIDs))
    val toolSpecificElementIDsFile = resolveFile(resultDir, "toolSpecificElementIDs.json")
    val toolSpecificElementIDsIn = fileSink[tables.OTIMOFToolSpecificID](toolSpecificElementIDsFile)

    val toolSpecificElementURLsOut = Source(pks.flatMap(_.toolSpecificElementURLs))
    val toolSpecificElementURLsFile = resolveFile(resultDir, "toolSpecificElementURLs.json")
    val toolSpecificElementURLsIn = fileSink[tables.OTIMOFToolSpecificURL](toolSpecificElementURLsFile)

    val appliedStereotypesOut = Source(pks.flatMap(_.appliedStereotypes))
    val appliedStereotypesFile = resolveFile(resultDir, "appliedStereotypes.json")
    val appliedStereotypesIn = fileSink[tables.model.OTIMOFAppliedStereotype](appliedStereotypesFile)

    val orderedLinksOut = Source(pks.flatMap(_.orderedLinks))
    val orderedLinksFile = resolveFile(resultDir, "orderedLinks.json")
    val orderedLinksIn = fileSink[tables.model.OTIMOFModelOrderedLink](orderedLinksFile)

    val unorderedLinksOut = Source(pks.flatMap(_.unorderedLinks))
    val unorderedLinksFile = resolveFile(resultDir, "unorderedLinks.json")
    val unorderedLinksIn = fileSink[tables.model.OTIMOFModelUnorderedLink](unorderedLinksFile)

    val orderedAtomicValuesOut = Source(pks.flatMap(_.orderedAtomicValues))
    val orderedAtomicValuesFile = resolveFile(resultDir, "orderedAtomicValues.json")
    val orderedAtomicValuesIn = fileSink[tables.values.OTIMOFOrderedAttributeAtomicValue](orderedAtomicValuesFile)

    val orderedLiteralValuesOut = Source(pks.flatMap(_.orderedLiteralValues))
    val orderedLiteralValuesFile = resolveFile(resultDir, "orderedEnumerationLiteralValues.json")
    val orderedLiteralValuesIn = fileSink[tables.values.OTIMOFOrderedAttributeEnumerationLiteralValue](orderedLiteralValuesFile)

    val orderedStructuredValuesOut = Source(pks.flatMap(_.orderedStructuredValues))
    val orderedStructuredValuesFile = resolveFile(resultDir, "orderedStructuredValues.json")
    val orderedStructuredValuesIn = fileSink[tables.values.OTIMOFOrderedAttributeStructuredValueLink](orderedStructuredValuesFile)

    val unorderedAtomicValuesOut = Source(pks.flatMap(_.unorderedAtomicValues))
    val unorderedAtomicValuesFile = resolveFile(resultDir, "unorderedAtomicValues.json")
    val unorderedAtomicValuesIn = fileSink[tables.values.OTIMOFUnorderedAttributeAtomicValue](unorderedAtomicValuesFile)

    val unorderedLiteralValuesOut = Source(pks.flatMap(_.unorderedLiteralValues))
    val unorderedLiteralValuesFile = resolveFile(resultDir, "unorderedEnumerationLiteralValues.json")
    val unorderedLiteralValuesIn = fileSink[tables.values.OTIMOFUnorderedAttributeEnumerationLiteralValue](unorderedLiteralValuesFile)

    val unorderedStructuredValuesOut = Source(pks.flatMap(_.unorderedStructuredValues))
    val unorderedStructuredValuesFile = resolveFile(resultDir, "unorderedStructuredValues.json")
    val unorderedStructuredValuesIn = fileSink[tables.values.OTIMOFUnorderedAttributeStructuredValueLink](unorderedStructuredValuesFile)

    // flows

    val graph = RunnableGraph.fromGraph(GraphDSL.create() { implicit b =>
      import GraphDSL.Implicits._

      resourcesOut ~> resourcesIn

      // profiles

      extendedMetamodelsOut ~> extendedMetamodelsIn

      pfImportsOut ~> pfImportsIn

      libImportOut ~> libImportIn

      stereotypesOut ~> stereotypesIn

      extendedMetaclassesOut ~> extendedMetaclassesIn

      metamodelsOut ~> metamodelsIn

      appliedProfilesOut ~> appliedProfilesIn

      // models

      metamodelsOut ~> metamodelsIn

      elementsOut ~> elementsIn

      toolSpecificElementIDsOut ~> toolSpecificElementIDsIn

      toolSpecificElementURLsOut ~> toolSpecificElementURLsIn

      orderedAtomicValuesOut ~> orderedAtomicValuesIn

      orderedLiteralValuesOut ~> orderedLiteralValuesIn

      orderedStructuredValuesOut ~> orderedStructuredValuesIn

      unorderedAtomicValuesOut ~> unorderedAtomicValuesIn

      unorderedLiteralValuesOut ~> unorderedLiteralValuesIn

      unorderedStructuredValuesOut ~> unorderedStructuredValuesIn

      appliedStereotypesOut ~> appliedStereotypesIn

      orderedLinksOut ~> orderedLinksIn

      unorderedLinksOut ~> unorderedLinksIn

      ClosedShape
    })

    graph.run()

    Success(())
  }

}