/*
 * Copyright (c) 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package sigtest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes the signature test.
 *
 */
public class SignatureTest extends com.sun.tdk.signaturetest.SignatureTest {

    private static final String SIG_FILE_NAME = System.getProperty("sigtest", "/jakarta.mail_2.0_java_1.8.sig");
    private static final String[] EXCLUDE_CLASSES = {
            "java.util.Map",
            "java.lang.Object",
            "java.io.ByteArrayInputStream",
            "java.io.InputStream",
            "java.lang.Deprecated",
            "java.io.Writer",
            "java.io.OutputStream",
            "java.util.List",
            "java.util.Collection",
            "java.lang.instrument.IllegalClassFormatException",
            "javax.transaction.xa.XAException",
            "java.lang.annotation.Repeatable",
            "java.lang.InterruptedException",
            "java.lang.CloneNotSupportedException",
            "java.lang.Throwable",
            "java.lang.Thread",
            "java.lang.Enum"
    };

    @org.junit.jupiter.api.Test
    public void test() throws IOException {
        File file = new File(System.getProperty("java.io.tmpdir") + SIG_FILE_NAME);
        file.deleteOnExit();
        try (InputStream inputStream = SignatureTest.class.getResourceAsStream(SIG_FILE_NAME)) {
            Files.copy(inputStream, file.toPath());
        }
        List<String> commands = new ArrayList<>();
        commands.add("-Package");
        commands.add("jakarta.mail");
        commands.add("-FileName");
        commands.add(file.getAbsolutePath());
        for (String exclude : EXCLUDE_CLASSES) {
            commands.add("-IgnoreJDKClass");
            commands.add(exclude);
        }
        try (StringWriter out = new StringWriter(); PrintWriter writer = new PrintWriter(out, true)) {
            run(commands.toArray(new String[0]), writer, null);
            boolean status = isPassed();
            assertTrue(status, () -> SIG_FILE_NAME + "\n" + out.toString());
        }
    }

}
