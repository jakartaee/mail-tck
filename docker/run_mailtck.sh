#!/bin/bash -xe

#
# Copyright (c) 2018, 2023 Oracle and/or its affiliates. All rights reserved.
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

if ls ${WORKSPACE}/bundles/*mail-tck*.zip 1> /dev/null 2>&1; then
  unzip ${WORKSPACE}/bundles/*mail-tck*.zip -d ${WORKSPACE}
else
  echo "[ERROR] TCK bundle not found"
  exit 1
fi

if [[ "$JDK" == "JDK21" || "$JDK" == "jdk21" ]];then
  wget https://download.java.net/java/early_access/jdk21/15/GPL/openjdk-21-ea+15_linux-x64_bin.tar.gz -O jdk-21.tar.gz
  tar -xvf jdk-21.tar.gz
  export JAVA_HOME=$WORKSPACE/jdk-21.0.1
fi  

export PATH=$JAVA_HOME/bin:$PATH
  
export TS_HOME=${WORKSPACE}/mail-tck

sed -i "s#^TS_HOME=.*#TS_HOME=$TS_HOME#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^JAVA_HOME=.*#JAVA_HOME=$JAVA_HOME#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^JAVAMAIL_SERVER=.*#JAVAMAIL_SERVER=localhost -pn 1143#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^JAVAMAIL_PROTOCOL=.*#JAVAMAIL_PROTOCOL=imap#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^JAVAMAIL_TRANSPORT_PROTOCOL=.*#JAVAMAIL_TRANSPORT_PROTOCOL=smtp#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^JAVAMAIL_TRANSPORT_SERVER=.*#JAVAMAIL_TRANSPORT_SERVER=localhost -tpn 1025#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^JAVAMAIL_USERNAME=.*#JAVAMAIL_USERNAME=$MAIL_USER#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^JAVAMAIL_PASSWORD=.*#JAVAMAIL_PASSWORD=1234#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^SMTP_DOMAIN=.*#SMTP_DOMAIN=james.local#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^SMTP_FROM=.*#SMTP_FROM=user01@james.local#g" "$TS_HOME/lib/ts.jte"
sed -i "s#^SMTP_TO=.*#SMTP_TO=user01@james.local#g" "$TS_HOME/lib/ts.jte"


sed -i "s#^TS_HOME=.*#TS_HOME=$TS_HOME#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^JAVA_HOME=.*#JAVA_HOME=$JAVA_HOME#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^JAVAMAIL_SERVER=.*#JAVAMAIL_SERVER=localhost -pn 1143#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^JAVAMAIL_PROTOCOL=.*#JAVAMAIL_PROTOCOL=imap#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^JAVAMAIL_TRANSPORT_PROTOCOL=.*#JAVAMAIL_TRANSPORT_PROTOCOL=smtp#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^JAVAMAIL_TRANSPORT_SERVER=.*#JAVAMAIL_TRANSPORT_SERVER=localhost -tpn 1025#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^JAVAMAIL_USERNAME=.*#JAVAMAIL_USERNAME=$MAIL_USER#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^JAVAMAIL_PASSWORD=.*#JAVAMAIL_PASSWORD=1234#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^SMTP_DOMAIN=.*#SMTP_DOMAIN=james.local#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^SMTP_FROM=.*#SMTP_FROM=user01@james.local#g" "$TS_HOME/lib/ts.pluggability.jte"
sed -i "s#^SMTP_TO=.*#SMTP_TO=user01@james.local#g" "$TS_HOME/lib/ts.pluggability.jte"

mkdir -p ${HOME}/.m2

WGET_PROPS="--progress=bar:force --no-cache"
if [ -z "$JAF_BUNDLE_URL" ];then
  export JAF_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/activation/jakarta.activation-api/2.1.2/jakarta.activation-api-2.1.2.jar
fi
if [ -z "$MAIL_BUNDLE_URL" ];then
  export MAIL_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/mail/jakarta.mail-api/2.1.1/jakarta.mail-api-2.1.1.jar
fi
if [ -z "$ANGUS_BUNDLE_URL" ];then
  export ANGUS_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/org/eclipse/angus/angus-mail/2.0.2/angus-mail-2.0.2.jar
fi
if [ -z "$ANGUS_ACTIVATION_BUNDLE_URL" ];then
  export ANGUS_ACTIVATION_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/org/eclipse/angus/angus-activation/2.0.1/angus-activation-2.0.1.jar
fi

wget $WGET_PROPS $JAF_BUNDLE_URL -O ${WORKSPACE}/jakarta.activation-api.jar
wget $WGET_PROPS $MAIL_BUNDLE_URL -O ${WORKSPACE}/jakarta.mail-api.jar
wget $WGET_PROPS $ANGUS_BUNDLE_URL -O ${WORKSPACE}/angus-mail.jar
wget $WGET_PROPS $ANGUS_ACTIVATION_BUNDLE_URL -O ${WORKSPACE}/angus-activation.jar

if [ -z "$GF_BUNDLE_URL" ]; then
  echo "Using default url for GF bundle: $DEFAULT_GF_BUNDLE_URL"
  export GF_BUNDLE_URL=$DEFAULT_GF_BUNDLE_URL
fi

wget $WGET_PROPS $GF_BUNDLE_URL -O latest-glassfish.zip
unzip -q -o latest-glassfish.zip

TOP_GLASSFISH_DIR="glassfish7"
chmod -R 777 ${TOP_GLASSFISH_DIR}

if [[ "$RUNTIME" == "Glassfish" ]]; then
  sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE/$TOP_GLASSFISH_DIR/glassfish/modules#g" ${TS_HOME}/lib/ts.jte
  sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE/$TOP_GLASSFISH_DIR/glassfish/modules#g" ${TS_HOME}/lib/ts.pluggability.jte
  export CLASSPATH=$TS_HOME/tests/mailboxes:$WORKSPACE/$TOP_GLASSFISH_DIR/glassfish/modules/jakarta.mail-api.jar:$WORKSPACE/$TOP_GLASSFISH_DIR/glassfish/modules/jakarta.activation-api.jar:$WORKSPACE/$TOP_GLASSFISH_DIR/glassfish/modules/angus-mail.jar:$WORKSPACE/$TOP_GLASSFISH_DIR/glassfish/modules/angus-activation.jar:$CLASSPATH
else 
  sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" ${TS_HOME}/lib/ts.jte
  sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" ${TS_HOME}/lib/ts.pluggability.jte
  export CLASSPATH=$TS_HOME/tests/mailboxes:$WORKSPACE/jakarta.mail-api.jar:$WORKSPACE/jakarta.activation-api.jar:$WORKSPACE/angus-mail.jar:${WORKSPACE}/angus-activation.jar:$CLASSPATH
fi

cd $TS_HOME/tests/mailboxes
javac -cp $CLASSPATH fpopulate.java
java -cp $CLASSPATH fpopulate -s test1 -d imap://user01%40james.local:1234@localhost:1143

which ant
ant -version

cd $WORKSPACE/mail-tck/
ant -Dreport.dir=$WORKSPACE/JTreport/mail-tck -Dwork.dir=$WORKSPACE/JTwork/mail-tck run run.pluggability


HOST=`hostname -f`
echo "1 mail-tck $HOST" > $WORKSPACE/args.txt

mkdir -p $WORKSPACE/results/junitreports/
JT_REPORT_DIR=$WORKSPACE/JTreport
$JAVA_HOME/bin/java -Djunit.embed.sysout=true -jar ${WORKSPACE}/docker/JTReportParser/JTReportParser.jar $WORKSPACE/args.txt $JT_REPORT_DIR $WORKSPACE/results/junitreports/ 

tar zcvf ${WORKSPACE}/mail-tck-results.tar.gz $WORKSPACE/JTreport/mail-tck $WORKSPACE/JTwork/mail-tck $WORKSPACE/results/junitreports/
