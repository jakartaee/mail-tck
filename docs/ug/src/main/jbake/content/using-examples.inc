<!--

    Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved.

    This program and the accompanying materials are made available under the
    terms of the Eclipse Public License v. 2.0, which is available at
    http://www.eclipse.org/legal/epl-2.0.

    This Source Code may also be made available under the following Secondary
    Licenses when the conditions for such availability set forth in the
    Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
    version 2 with the GNU Classpath Exception, which is available at
    https://www.gnu.org/software/classpath/license.html.

    SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0

-->

///////////////////////////////////////////////////////////////////////
NOTE TO WRITERS:
These CLI examples can be customized as necessary.
///////////////////////////////////////////////////////////////////////

1.  Start JavaTest using the following command:
+
--
[source,oac_no_warn]
----
ant run
----
--

[[GCMCU]]

Example 5-1 {TechnologyShortName} TCK Signature Tests

To run the {TechnologyShortName} TCK signature tests, enter the
following commands:

[source,subs="attributes"]
----
ant -Dtests=SignatureTest.html run
----

[[GCMBV]]


Example 5-2 Single Test Directory

To run a single test directory, enter the following commands:

[source,subs="attributes"]
----
ant -Dtests=api/javasoft/sqe/tests/jakarta/mail/Address run
----

[[GCMCA]]


Example 5-3 Subset of Test Directories

To run a subset of test directories, enter the following commands:

[source,subs="attributes"]
----
ant -Dtests=api/javasoft/sqe/tests/jakarta/mail/internet run
----

