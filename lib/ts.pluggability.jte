#
# Copyright (c) 2021, 2023 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License v. 2.0, which is available at
# http://www.eclipse.org/legal/epl-2.0.
#
# This Source Code may also be made available under the following Secondary
# Licenses when the conditions for such availability set forth in the
# Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
# version 2 with the GNU Classpath Exception, which is available at
# https://www.gnu.org/software/classpath/license.html.
#
# SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
#

#########################################################################
#                                                                       #
#   JavaTest Environment file for TCK                                   #
#                                                                       #
#   The platform specific environments in this file will likely         #
#   have to be modified during installation of the TCK.                 #
#   You can modify values inline, here, or you can override them in     #
#   a separate file, and give both files to JavaTest.                   #
#                                                                       #
#########################################################################

# User defined environment variables

# Set this to your TCK installation directory.

# Use this style naming convention for Windows.
##TS_HOME=D:\\jm_tck\\mailtck

# And this style naming convention for UNIX.
# TS_HOME=/home/abc/mailtck
TS_HOME=MUST-BE-SET

# Set this to the installation of your JDK.

##JAVA_HOME=D:\\jdk1.8
##JAVA_HOME=/usr/jdk1.8
JAVA_HOME=MUST-BE-SET

# Set this to the directory containing the Jakarta Mail .jar 
# files (i.e., jakarta.mail.jar and jakarta.activation.jar)
JARPATH=.

# Adjust these as necessary for Windows or UNIX
# (Must use "${JARPATH}" syntax so ant will expand the nested properties)

API_JAR=${JARPATH}/jakarta.mail-api.jar:${JARPATH}/jakarta.activation-api.jar

##CI_JAR=$TS_HOME/classes-providers/mail_alternate_provider.jar:${JARPATH}/angus-activation.jar
CI_JAR=$TS_HOME/classes-providers/mail_alternate_provider.jar:${JARPATH}/angus-activation.jar
# Or use the following if testing modules:
#CI_JAR=${JARPATH}/smtp.jar:${JARPATH}/imap.jar

##TESTCLASS=$TS_HOME\\classes-pluggability
TESTCLASS=$TS_HOME/classes-pluggability

##extraOtherJVMClassPath=$TS_HOME\\sigtest.jar;$API_JAR;$CI_JAR;$TESTCLASS
extraOtherJVMClassPath=$TS_HOME/sigtest.jar:$API_JAR:$CI_JAR:$TESTCLASS

##javatestClassDir=$TS_HOME\\javatest.jar
javatestClassDir=$TS_HOME/javatest.jar

##SYSTEMROOT=C:\\WINNT

# Directory to store the input files or data for the TCK tests
# This should be set to the installation <Directory of the TCK>/tests/testdata
#JAVAMAIL_TESTDATA_DIR=$TS_HOME\\tests\\testdata
JAVAMAIL_TESTDATA_DIR=$TS_HOME/tests/testdata

#Test URL for signature tests.
# This should be set to the installation <Directory of the TCK>/tests
testURL=file://$TS_HOME/tests


# Shorthand forms, for convenience.
# These should not need to be changed.

# TestFinder macros
TCKTestFinder=com.sun.javatest.finder.HTMLTestFinder

# Script macros
TCKScript=com.sun.javatest.lib.StdTestScript
TCKReportScript=com.sun.javatest.lib.ReportScript

# Command Macros
ExecTCKTestOtherJVMCmd=com.sun.javatest.lib.ExecStdTestOtherJVMCmd
JDKCompileCommand=com.sun.javatest.lib.JavaCompileCommand
ProcessCommand=com.sun.javatest.lib.ProcessCommand

#---------- "Reporting" environment --------------------------------------------


# Use this environment to report on previously executed tests
# without running them again.  You can select a different set
# of tests than that previously run: e.g. to do a big run,
# break it in pieces, run the pieces separately, and then
# do a single report of all tests using this environment.

# Environment jck-report
#
env.jck-report.description=Report on previously executed tests
env.jck-report.finder=$TCKTestFinder
env.jck-report.script=$TCKReportScript



#---------- JDK/UNIX configuration ------------------------------------------

# Environment mailUnix
#
env.mailUnix.description=\
	Run the runtime tests in a separate JVM (process) on UNIX
env.mailUnix.finder=$TCKTestFinder
env.mailUnix.script=$TCKScript
 
#
# The runtime to be tested ...
#
env.mailUnix.command.execute=$ExecTCKTestOtherJVMCmd \
	CLASSPATH=$testSuiteRootDir/../classes-pluggability$:$testClassDir:\
	$javatestClassDir$:$extraOtherJVMClassPath \
	HOME=${user.home} \
	$JAVA_HOME/bin/java -DnoSecurityManager=true -verify $testExecuteClass $testExecuteArgs $testDebug

#---------- JDK/UNIX module configuration -----------------------------------

# Environment mailUnixMod
#
env.mailUnixMod.description=\
	Run the runtime tests in a separate JVM (process) on UNIX
env.mailUnixMod.finder=$TCKTestFinder
env.mailUnixMod.script=$TCKScript
 
#
# The runtime to be tested ...
#
env.mailUnixMod.command.execute=$ExecTCKTestOtherJVMCmd \
	CLASSPATH=$testSuiteRootDir/../classes-pluggability$:$testClassDir:\
	$javatestClassDir$:$extraOtherJVMClassPath \
	HOME=${user.home} \
	$JAVA_HOME/bin/java -DnoSecurityManager=true -verify \
		--add-modules jakarta.activation,jakarta.mail \
		-p $API_JAR \
		$testExecuteClass $testExecuteArgs $testDebug


#---------- JDK/Windows configuration ---------------------------------------

# Environment mailWindows
#
env.mailWindows.description=\
	Run the runtime tests in a separate JVM (process) on Windows
env.mailWindows.finder=$TCKTestFinder
env.mailWindows.script=$TCKScript

#
#
# This environment will run the runtime tests
# in a separate JVM (process) on Windows.
#
# The runtime to be tested ...
# 	The SystemRoot variable should be set to the Windows system 
#	installation directory.
#
# You can either set the value of SystemRoot explicitly, or you can set it to
# refer to a system property that you set on the command line when you start
# JavaTest, which might in turn be set from your Windows SystemRoot variable.
#
env.mailWindows.command.execute=$ExecTCKTestOtherJVMCmd \
	CLASSPATH=$testSuiteRootDir$/..$/classes-pluggability$:$javatestClassDir$:$extraOtherJVMClassPath \
	SystemRoot=${SYSTEMROOT} \
	windir=${SYSTEMROOT} \
	$JAVA_HOME$/bin$/java -DnoSecurityManager=true -verify $testExecuteClass $testExecuteArgs $testDebug

#---------- JDK/Windows module configuration --------------------------------

# Environment mailWindowsMod
#
env.mailWindowsMod.description=\
	Run the runtime tests in a separate JVM (process) on Windows with modules
env.mailWindowsMod.finder=$TCKTestFinder
env.mailWindowsMod.script=$TCKScript

#
#
# This environment will run the runtime tests
# in a separate JVM (process) on Windows.
#
# The runtime to be tested ...
# 	The SystemRoot variable should be set to the Windows system 
#	installation directory.
#
# You can either set the value of SystemRoot explicitly, or you can set it to
# refer to a system property that you set on the command line when you start
# JavaTest, which might in turn be set from your Windows SystemRoot variable.
#
env.mailWindowsMod.command.execute=$ExecTCKTestOtherJVMCmd \
	CLASSPATH=$testSuiteRootDir$/..$/classes-pluggability$:$javatestClassDir$:$extraOtherJVMClassPath \
	SystemRoot=${SYSTEMROOT} \
	windir=${SYSTEMROOT} \
	$JAVA_HOME$/bin$/java -DnoSecurityManager=true -verify \
		--add-modules jakarta.activation,jakarta.mail \
		-p $API_JAR \
		$testExecuteClass $testExecuteArgs $testDebug


#----------------------------------------------------------------------
# Variables for Jakarta Mail Tests
#
# Host, user, and password values can be set to null by using the special
# value NULL.
#
#JAVAMAIL_SERVER=jsesun01.east	# edit it; IMAP server used to run tests against
#JAVAMAIL_SERVER=jsesun01.east -pn 2143       # if non-standard port number
#JAVAMAIL_USERNAME=test2        # edit it; user account on IMAP server
#JAVAMAIL_PASSWORD=test2        # edit it; password for above account name
#JAVAMAIL_PROTOCOL=imap         # edit it; protocol used to run the Store tests
#JAVAMAIL_TRANSPORT_PROTOCOL=smtp  # edit it; Transport protocol used by tests
#JAVAMAIL_TRANSPORT_PROTOCOL=smtp -A  # if server requires authentication
#JAVAMAIL_TRANSPORT_SERVER=smtp.east  # if outgoing server is different
#JAVAMAIL_ROOT_PATH=""		# edit it; rootpath of test mailboxes
#JAVAMAIL_MAILBOX=test2         # edit it; note the mailbox 'test2'
#JAVAMAIL_LISTPATTERN=test      # edit it; search pattern used by list tests in Folder
#SMTP_USERNAME=djiao		# edit it; use your own login name
#SMTP_DOMAIN=east.sun.com	# edit it; i.e.  sun.com, aol.com, ...
#SMTP_FROM=user@example.com	# edit it if not $SMTP_USERNAME@$SMTP_DOMAIN
#SMTP_TO=user@example.com	# edit it if not $SMTP_USERNAME@$SMTP_DOMAIN

#----------------------------------------------------------------------
#
# Variables for Jakarta Mail Tests
#
#

# mail server used to run tests against.
JAVAMAIL_SERVER=

# user account on server
JAVAMAIL_USERNAME=

# password for above account name
JAVAMAIL_PASSWORD=

# protocol used to run the Store tests
JAVAMAIL_PROTOCOL=imap

# Transport protocol used by tests
JAVAMAIL_TRANSPORT_PROTOCOL=smtp

# mail server used to run tests against.
JAVAMAIL_TRANSPORT_SERVER=$JAVAMAIL_SERVER

# rootpath of test mailboxes
JAVAMAIL_ROOT_PATH=""

# note the mailbox 'test1'
JAVAMAIL_MAILBOX=test1

# search pattern used by list tests in Folder
JAVAMAIL_LISTPATTERN=test

# your own login account name
SMTP_USERNAME=$JAVAMAIL_USERNAME

# smtp domain name ; i.e. aol.com, sun.com
SMTP_DOMAIN=$JAVAMAIL_SERVER

# smtp From address
SMTP_FROM=$SMTP_USERNAME@$SMTP_DOMAIN

# smtp To address
SMTP_TO=$SMTP_USERNAME@$SMTP_DOMAIN
