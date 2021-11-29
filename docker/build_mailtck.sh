#!/bin/bash -x

#
# Copyright (c) 2018, 2021 Oracle and/or its affiliates. All rights reserved.
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

export JAVA_HOME=${JDK11_HOME}
export PATH=$JAVA_HOME/bin:$PATH

echo "ANT_HOME=$ANT_HOME"
echo "export JAVA_HOME=$JAVA_HOME"
echo "export MAVEN_HOME=$MAVEN_HOME"
echo "export PATH=$PATH"

sed -i "s#^TS_HOME=.*#TS_HOME=$WORKSPACE#g" "$WORKSPACE/lib/ts.jte"
sed -i "s#^JAVA_HOME=.*#JAVA_HOME=$JAVA_HOME#g" "$WORKSPACE/lib/ts.jte"
sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" "$WORKSPACE/lib/ts.jte"

sed -i "s#^TS_HOME=.*#TS_HOME=$WORKSPACE#g" "$WORKSPACE/lib/ts.pluggability.jte"
sed -i "s#^JAVA_HOME=.*#JAVA_HOME=$JAVA_HOME#g" "$WORKSPACE/lib/ts.pluggability.jte"
sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" "$WORKSPACE/lib/ts.pluggability.jte"


mkdir -p ${HOME}/.m2

cd $WORKSPACE

if [ ! -z "$TCK_BUNDLE_BASE_URL" ]; then
  #use pre-built tck bundle from this location to run test
  mkdir -p ${WORKSPACE}/bundles
  wget  --progress=bar:force --no-cache ${TCK_BUNDLE_BASE_URL}/${TCK_BUNDLE_FILE_NAME} -O ${WORKSPACE}/bundles/mail-tck-2.1_latest.zip
  exit 0
fi

WGET_PROPS="--progress=bar:force --no-cache"
if [ -z "$JAF_BUNDLE_URL" ];then
  export JAF_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/activation/jakarta.activation-api/2.1.0/jakarta.activation-api-2.1.0.jar
fi
if [ -z "$MAIL_BUNDLE_URL" ];then
  export MAIL_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/mail/jakarta.mail-api/2.1.0/jakarta.mail-api-2.1.0.jar
fi
if [ -z "$ANGUS_BUNDLE_URL" ];then
  export ANGUS_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/org/eclipse/angus/angus-mail/1.0.0-SNAPSHOT/angus-mail-1.0.0-20211103.105245-4.jar
fi
if [ -z "$ANGUS_ACTIVATION_BUNDLE_URL" ];then
  export ANGUS_ACTIVATION_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/org/eclipse/angus/angus-activation/1.0.0-SNAPSHOT/angus-activation-1.0.0-20210811.141336-3.jar
fi

wget $WGET_PROPS $JAF_BUNDLE_URL -O ${WORKSPACE}/jakarta.activation-api.jar
wget $WGET_PROPS $MAIL_BUNDLE_URL -O ${WORKSPACE}/jakarta.mail-api.jar
wget $WGET_PROPS $ANGUS_BUNDLE_URL -O ${WORKSPACE}/angus-mail.jar
wget $WGET_PROPS $ANGUS_ACTIVATION_BUNDLE_URL -O ${WORKSPACE}/angus-activation.jar


which ant
ant -version

which mvn
mvn -version

export ANT_OPTS="-DTS_HOME=$WORKSPACE -DJAVA_HOME=$JAVA_HOME -DJARPATH=$WORKSPACE"
if [[ "$LICENSE" == "EFTL" || "$LICENSE" == "eftl" ]]; then
  ant -f release.xml clean core -DuseEFTLicensefile="true"
else
  ant -f release.xml clean core
fi

mkdir -p ${WORKSPACE}/bundles
chmod 777 ${WORKSPACE}/*.zip
for entry in `ls mail*.zip`; do
  strippedEntry=`basename "$entry" .zip`
  echo "copying ${WORKSPACE}/$entry to ${WORKSPACE}/bundles/$entry"
  if [[ "$LICENSE" == "EFTL" || "$LICENSE" == "eftl" ]]; then
    cp ${WORKSPACE}/$entry ${WORKSPACE}/bundles/jakarta-$entry
    chmod 777 ${WORKSPACE}/bundles/jakarta-$entry
  else
    cp ${WORKSPACE}/$entry ${WORKSPACE}/bundles/$entry
    chmod 777 ${WORKSPACE}/bundles/$entry
  fi
done
