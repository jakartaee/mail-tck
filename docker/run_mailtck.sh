#!/bin/bash -xe

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

if ls ${WORKSPACE}/bundles/*mail-tck*.zip 1> /dev/null 2>&1; then
  unzip ${WORKSPACE}/bundles/*mail-tck*.zip -d ${WORKSPACE}
else
  echo "[ERROR] TCK bundle not found"
  exit 1
fi

if [[ "$JDK" == "JDK11" || "$JDK" == "jdk11" ]];then
  export JAVA_HOME=${JDK11_HOME}
  export PATH=$JAVA_HOME/bin:$PATH
fi
  
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

mkdir -p ${HOME}/.m2

WGET_PROPS="--progress=bar:force --no-cache"
if [ -z "$JAF_BUNDLE_URL" ];then
  export JAF_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/activation/jakarta.activation-api/2.1.0-RC1/jakarta.activation-api-2.1.0-RC1.jar
fi
if [ -z "$MAIL_BUNDLE_URL" ];then
  export MAIL_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/mail/jakarta.mail-api/2.1.0-RC1/jakarta.mail-api-2.1.0-RC1.jar
fi
wget $WGET_PROPS $JAF_BUNDLE_URL -O jakarta.activation.jar
wget $WGET_PROPS $MAIL_BUNDLE_URL -O jakarta.mail.jar

cd $TS_HOME/tests/mailboxes
export CLASSPATH=$TS_HOME/tests/mailboxes:$WORKSPACE/jakarta.mail.jar:$WORKSPACE/jakarta.activation.jar:$CLASSPATH
javac -cp $CLASSPATH fpopulate.java
java -cp $CLASSPATH fpopulate -s test1 -d imap://user01%40james.local:1234@localhost:1143

which ant
ant -version

cd $WORKSPACE/mail-tck/
ant -Dreport.dir=$WORKSPACE/JTreport/mail-tck -Dwork.dir=$WORKSPACE/JTwork/mail-tck run


HOST=`hostname -f`
echo "1 mail-tck $HOST" > $WORKSPACE/args.txt

mkdir -p $WORKSPACE/results/junitreports/
JT_REPORT_DIR=$WORKSPACE/JTreport
$JAVA_HOME/bin/java -Djunit.embed.sysout=true -jar ${WORKSPACE}/docker/JTReportParser/JTReportParser.jar $WORKSPACE/args.txt $JT_REPORT_DIR $WORKSPACE/results/junitreports/ 

tar zcvf ${WORKSPACE}/mail-tck-results.tar.gz $WORKSPACE/JTreport/mail-tck $WORKSPACE/JTwork/mail-tck $WORKSPACE/results/junitreports/
