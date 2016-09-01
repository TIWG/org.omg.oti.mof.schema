# OMG Tool-Interoperability (OTI) MOF Information Normalized Schemas & API

[![Build Status](https://travis-ci.org/TIWG/org.omg.oti.mof.schema.svg?branch=master)](https://travis-ci.org/TIWG/org.omg.oti.mof.schema)
 [ ![Download](https://api.bintray.com/packages/tiwg/org.omg.tiwg/org.omg.oti.mof.schema/images/download.svg) ](https://bintray.com/tiwg/org.omg.tiwg/org.omg.oti.mof.schema/_latestVersion)

This is work-in-progress.

The goal is to experimentally validate a radical simplification for managing models
compared to the current paradigm based on implementations of OMG's modeling specifications.

Currently, UML models are serialized as XMI trees and tool-specific UML APIs reflect
the class + property organization of the OMG UML 2.5 metamodel. From a database perspective,
this corresponds to a materialized view of a database schema -- i.e., the OMG UML 2.5 metamodel
and tool-specific implementations of it. That is, from a database theory perspective,
there is no normalized database schema.

This project is based on the idea of applying the principles of database theory, particularly that of
defining a normalized schema, as the foundation for metamodeling & modeling.