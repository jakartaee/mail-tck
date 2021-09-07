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

/**
 * Executes the signature test.
 *
 */
public class SignatureTest extends com.sun.tdk.signaturetest.SignatureTest {

    private static final String SIG_FILE_NAME = System.getProperty("sigtest", "/jakarta.mail_2.0_java_1.8.sig");

    @org.junit.jupiter.api.Test
    public void test() throws IOException {
        File file = new File(System.getProperty("java.io.tmpdir") + SIG_FILE_NAME);
        file.deleteOnExit();
        try (InputStream inputStream = SignatureTest.class.getResourceAsStream(SIG_FILE_NAME)) {
            Files.copy(inputStream, file.toPath());
        }
        String[] args = new String[] {"-Package", "jakarta.mail", "-FileName", file.getAbsolutePath()};
        try (StringWriter out = new StringWriter(); PrintWriter writer = new PrintWriter(out, true)) {
            run(args, writer, null);
            boolean status = isPassed();
            assertTrue(status, () -> SIG_FILE_NAME + "\n" + out.toString());
        }
    }

}
