//
//
//  Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved.
//
//  This program and the accompanying materials are made available under the
//  terms of the Eclipse Public License v. 2.0, which is available at
//  http://www.eclipse.org/legal/epl-2.0.
//
//  This Source Code may also be made available under the following Secondary
//  Licenses when the conditions for such availability set forth in the
//  Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
//  version 2 with the GNU Classpath Exception, which is available at
//  https://www.gnu.org/software/classpath/license.html.
//
//  SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
//


///////////////////////////////////////////////////////////////////////
NOTE TO WRITERS:
The following sections should be customized for the technology.
This text was originally from the JAX-RS TCK.  Most references
to JAX-RS have been parameterized to serve as a simple starting
point for customization.  There are still many details that will
need to be changed or removed.  The major sections 4.1, 4.2, and
4.3 should be preserved.  If their titles are changed, the links
at the top of config.adoc will need to be changed as well as well
as toc.adoc.
///////////////////////////////////////////////////////////////////////

[[GBFVU]][[configuring-your-environment-to-run-the-tck-against-the-compatible-implementation]]

4.1 Configuring Your Environment to Run the TCK Against the Compatible Implementation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

After configuring your environment as described in this section,
continue with the instructions in link:#GBFUY[Section 4.4, "Using the
JavaTest Harness Software."]


[NOTE]
=======================================================================

In these instructions, variables in angle brackets need to be expanded
for each platform. For example, `<TS_HOME>` becomes `$TS_HOME` on
Solaris/Linux and `%TS_HOME%` on Windows. In addition, the forward
slashes (`/`) used in all of the examples need to be replaced with
backslashes (`\`) for Windows. Finally, be sure to use the appropriate
separator for your operating system when specifying multiple path
entries (`;` on Windows, `:` on UNIX/Linux).

On Windows, you must escape any backslashes with an extra backslash in
path separators used in any of the following properties, or use forward
slashes as a path separator instead.

=======================================================================


1.  Set the following environment variables in your shell environment:
  a.  `JAVA_HOME` to the directory in which Java SE {SEversion} is installed
  b.  `ANT_HOME` to the directory in which Apache Ant {AntVersion} is installed
  c.  `TS_HOME` to the directory in which the {TechnologyShortName} TCK
  {TechnologyVersion} software is installed
  d.  +{TechnologyHomeEnv}+ to the directory in which the {TechnologyShortName}
  {TechnologyVersion} CI has been installed
  e.  `PATH` to include the following directories: `JAVA_HOME/bin`
  and `ANT_HOME/bin`
2.  Edit your +{jteFileName}+ file and set the following
environment variables:
  a.  Set the `JAVA_HOME` property to the directory in which Java SE {SEversion}
  is installed.
  b. Set the `TS_HOME` property to the directory in which the
  {TechnologyShortName} TCK {TechnologyVersion} software is installed.
  c.  Set the +{TechnologyHomeEnv}+ property to the directory in which the
  {TechnologyShortName} {TechnologyVersion} CI has been installed.
3. Run the JavaTest harness in GUI or command-line mode, as described in
link:#GBFUY[Section 4.4, "Using the JavaTest Harness Software."]

[[GCLHU]][[configuring-your-environment-to-run-the-tck-against-the-vendor-implementation]]

4.2 Configuring Your Environment to Run the TCK Against the Vendor Implementation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

After configuring your environment as described in this section,
continue with the instructions in link:#GBFUY[Section 4.4, "Using the
JavaTest Harness Software."]


[NOTE]
=======================================================================

In these instructions, variables in angle brackets need to be expanded
for each platform. For example, `<TS_HOME>` becomes `$TS_HOME` on
Solaris/Linux and `%TS_HOME%` on Windows. In addition, the forward
slashes (`/`) used in all of the examples need to be replaced with
backslashes (`\`) for Windows. Finally, be sure to use the appropriate
separator for your operating system when specifying multiple path
entries (`;` on Windows, `:` on UNIX/Linux).

On Windows, you must escape any backslashes with an extra backslash in
path separators used in any of the following properties, or use forward
slashes as a path separator instead.

=======================================================================


1.  Set the following environment variables in your shell environment:
  a.  `JAVA_HOME` to the directory in which Java SE {SEversion} is installed
  b.  `TS_HOME` to the directory in which the {TechnologyShortName} TCK
  {TechnologyVersion} software is installed
  c.  +{TechnologyHomeEnv}+ to the directory in which the {TechnologyShortName}
  {TechnologyVersion} Vendor Implementation has been installed
  d.  `PATH` to include the following directories: `JAVA_HOME/bin`
  and `ANT_HOME/bin`
2.  Edit your +{jteFileName}+ file and set the following
environment variables:
  a.  Set the `JAVA_HOME` property to the directory in which Java SE {SEversion}
  is installed.
  b. Set the `TS_HOME` property to the directory in which the
  {TechnologyShortName} TCK {TechnologyVersion} software is installed.
  c.  Set the +{TechnologyHomeEnv}+ property to the directory in which the
  {TechnologyShortName} {TechnologyVersion} Vendor Implementation has been
  installed.
3. Run the JavaTest harness in GUI or command-line mode, as described in
link:#GBFUY[Section 4.4, "Using the JavaTest Harness Software."]


[[GHGDG]][[setting-up-a-mailbox-for-the-tck-to-use]]

4.3 Setting Up a Maibox for the TCK to Use
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

1. Install IMAP server software on a machine. +
Note that the IMAP server must support IMAP4 (RFC 1730) or IMAP4rev1 (RFC 2060).
2. Create one user account with password on this server. +
Note that a password is necessary.
3. Populate the mailbox for the user you just created:
  a. Change to the `$TS_HOME/tests/mailboxes` directory.
  b. Execute the following commands:
+
[source,oac_no_warn]
----
javac fpopulate.java
java fpopulate -s test1 -d dest_url
----
+
where `dest_url` is of the form: +
`protocol://username:password@hostname/`
4. Install SMTP software on a machine.
5. Configure a user account so that the SMTP server can deliver email to
that account. +
Refer to your SMTP server documentation for instructions.
6. Edit your +{jteFileName}+ file and set the following variables:
  a. `JAVAMAIL_SERVER` to the server on which the mail server is installed
  b. `JAVAMAIL_USERNAME` to the user name for the user account you created
  in step 2
  c. `JAVAMAIL_PASSWORD` to the password for the user account you created
  in step 2
  d. `JAVAMAIL_ROOT_PATH` to the folder under which your test mailboxes are
  defined +
  This variable must be set to "" or "/" if your mailboxes are directly under
  the root.
  e. `SMTP_USERNAME` to the SMTP user name +
  This is usually the same as the IMAP user name if you are using a mail server.
  f. `SMTP_DOMAIN` to the domain name of the server; for example,
  `sun.com`, `aol.com`
