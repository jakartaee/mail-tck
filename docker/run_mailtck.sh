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

if [[ "$JDK" == "JDK11" || "$JDK" == "jdk11" ]];then
  export JAVA_HOME=${JDK11_HOME}
  export PATH=$JAVA_HOME/bin:$PATH
fi

sed -i "s#^JAVAMAIL_SERVER=.*#JAVAMAIL_SERVER=localhost -pn 1143#g" "lib/ts.properties"
sed -i "s#^JAVAMAIL_PROTOCOL=.*#JAVAMAIL_PROTOCOL=imap#g" "lib/ts.properties"
sed -i "s#^JAVAMAIL_TRANSPORT_PROTOCOL=.*#JAVAMAIL_TRANSPORT_PROTOCOL=smtp#g" "lib/ts.properties"
sed -i "s#^JAVAMAIL_TRANSPORT_SERVER=.*#JAVAMAIL_TRANSPORT_SERVER=localhost -tpn 1025#g" "lib/ts.properties"
sed -i "s#^JAVAMAIL_USERNAME=.*#JAVAMAIL_USERNAME=$MAIL_USER#g" "lib/ts.properties"
sed -i "s#^JAVAMAIL_PASSWORD=.*#JAVAMAIL_PASSWORD=1234#g" "lib/ts.properties"
sed -i "s#^SMTP_DOMAIN=.*#SMTP_DOMAIN=james.local#g" "lib/ts.properties"
sed -i "s#^SMTP_FROM=.*#SMTP_FROM=user01@james.local#g" "lib/ts.properties"
sed -i "s#^SMTP_TO=.*#SMTP_TO=user01@james.local#g" "lib/ts.properties"

which mvn
mvn -version

mvn clean install -DskipTests
java -jar target/jakarta.mail-tck-1.0.0-SNAPSHOT.jar -f -s target/test-classes/mailboxes/test1 -d imap://user01%40james.local:1234@localhost:1143/test1
mvn test

# FIXME report directory?
