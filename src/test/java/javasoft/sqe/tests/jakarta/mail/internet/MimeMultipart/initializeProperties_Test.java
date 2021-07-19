/*
 * Copyright (c) 2006, 2021 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMultipart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.activation.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the MimeMultipart initializeProperties method.
 *
 * It does so by subclassing MimeMultipart and ensuring that fields
 * are set based on System property values.
 */

public class initializeProperties_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    static Session session;

    public Status run(PrintWriter log, PrintWriter out) {
	super.run(log, out);

        out.println("\nTesting class MimeMultipart: " +
	    "initializeProperties method\n");
        try {
	    clearAll();

            // BEGIN UNIT TEST:
            out.println("UNIT TEST 1:  initializeProperties()");
	    MyMimeMultipart mp = new MyMimeMultipart();
	    if (mp.checkDefault()) {
                out.println("UNIT TEST 1:  passed\n");
	    } else {
                out.println("UNIT TEST 1:  FAILED\n");
                errors++;
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
            out.println("UNIT TEST 2:  initializeProperties()");
	    System.setProperty(
		"mail.mime.multipart.ignoreexistingboundaryparameter", "true");
	    System.setProperty(
		"mail.mime.multipart.ignoremissingboundaryparameter", "false");
	    System.setProperty(
		"mail.mime.multipart.ignoremissingendboundary", "false");
	    System.setProperty("mail.mime.multipart.allowempty", "true");
	    mp = new MyMimeMultipart();
	    if (mp.checkNonDefault()) {
                out.println("UNIT TEST 2:  passed\n");
	    } else {
                out.println("UNIT TEST 2:  FAILED\n");
                errors++;
	    }
            // END UNIT TEST:

            checkStatus();
        } catch (Exception e) {
	    handlException(e);
        }
	return status;
     }

    private static void clearAll() {
        System.clearProperty(
	    "mail.mime.multipart.ignoreexistingboundaryparameter");
        System.clearProperty(
	    "mail.mime.multipart.ignoremissingboundaryparameter");
        System.clearProperty(
	    "mail.mime.multipart.ignoremissingendboundary");
        System.clearProperty(
	    "mail.mime.multipart.allowempty");
    }
}

/**
 * An OutputStream that throws away all data written to it.
 */
class MyMimeMultipart extends MimeMultipart {

    public MyMimeMultipart() {
	super();
    }

    // check that fields have default values
    public boolean checkDefault() {
	return ignoreMissingEndBoundary &&
	    ignoreMissingBoundaryParameter &&
	    !ignoreExistingBoundaryParameter &&
	    !allowEmpty;
    }

    // check that all fields have been changed
    public boolean checkNonDefault() {
	return !ignoreMissingEndBoundary &&
	    !ignoreMissingBoundaryParameter &&
	    ignoreExistingBoundaryParameter &&
	    allowEmpty;
    }
}
