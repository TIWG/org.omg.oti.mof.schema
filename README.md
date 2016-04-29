# OMG Tool-Infrastructure MOF Information Schema & API

This is work-in-progress.

The goal is to experimentally validate a radical simplification for managing models
compared to the current paradigm based on implementations of OMG's modeling specifications.

Currently, UML models are serialized as XMI trees and tool-specific UML APIs reflect
the class + property organization of the OMG UML 2.5 metamodel. From a database perspective,
this corresponds to a materialized view of a database schema -- i.e., the OMG UML 2.5 metamodel
and tool-specific implementations of it. That is, from a database theory perspective,
there is no normalized database schema.

This project is based on the idea of applying the principles of database theory, particularly that of
defining a normalized schema, as the foundation for modeling.