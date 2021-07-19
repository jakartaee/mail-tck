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
 * This class tests the MimeMultipart System property settings.
 *
 * It does by parsing a MIME message with different property settings.
 *
 * systemproperty2test: mail.mime.ignoremissingboundaryparameter <p>
 *
 * how2test: Call API with input stream argument. If this operation is 
 *           successful then testcase passes, otherwise it fails.
 */

public class properties_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    static Session session;

    public Status run(PrintWriter log, PrintWriter out) {
	super.run(log, out);

        out.println("\nTesting class MimeMultipart: effect of " +
            "system property settings\n");
        try {
            session = Session.getInstance(properties, null);
	    MimeMessage m;
	    MimeMultipart mp;

            // BEGIN UNIT TEST:
	    // test simple correct case
            out.println("UNIT TEST 1:  test for parse(InputStream) with " +
			"no System properties set");
	    clearAll();
	    m = createMessage("x", "x", true);
	    mp = (MimeMultipart)m.getContent();
	    if (mp.getCount() == 2) {
                out.println("UNIT TEST 1:  passed\n");
	    } else {
                out.println("UNIT TEST 1:  FAILED\n");
                errors++;
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.ignoreexistingboundaryparameter
            out.println("UNIT TEST 2:  test for parse(InputStream) with " +
		"mail.mime.multipart.ignoreexistingboundaryparameter=true");
	    clearAll();
	    System.setProperty(
		"mail.mime.multipart.ignoreexistingboundaryparameter", "true");
	    m = createMessage("x", "-", true);
	    mp = (MimeMultipart)m.getContent();
	    if (mp.getCount() == 2) {
                out.println("UNIT TEST 2:  passed\n");
	    } else {
                out.println("UNIT TEST 2:  FAILED\n");
                errors++;
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.ignoremissingboundaryparameter default
            out.println("UNIT TEST 3:  test for parse(InputStream) with " +
		"no boundary parameter");
	    clearAll();
	    m = createMessage(null, "x", true);
	    mp = (MimeMultipart)m.getContent();
	    if (mp.getCount() == 2) {
                out.println("UNIT TEST 3:  passed\n");
	    } else {
                out.println("UNIT TEST 3:  FAILED\n");
                errors++;
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.ignoremissingboundaryparameter=false
            out.println("UNIT TEST 4:  test for parse(InputStream) with " +
		"no boundary parameter and " +
		"mail.mime.multipart.ignoremissingboundaryparameter=false");
	    clearAll();
	    System.setProperty(
		"mail.mime.multipart.ignoremissingboundaryparameter", "false");
	    try {
		m = createMessage(null, "x", true);
		mp = (MimeMultipart)m.getContent();
		mp.getCount();		// throw exception
                out.println("UNIT TEST 4:  FAILED\n");
                errors++;
	    } catch (MessagingException mex) {
                out.println("UNIT TEST 4:  passed\n");
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.ignoreexistingmissingendboundary default
            out.println("UNIT TEST 5:  test for parse(InputStream) with " +
		"no end boundary");
	    clearAll();
	    m = createMessage("x", "x", false);
	    mp = (MimeMultipart)m.getContent();
	    if (mp.getCount() == 2) {
                out.println("UNIT TEST 5:  passed\n");
	    } else {
                out.println("UNIT TEST 5:  FAILED\n");
                errors++;
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.ignoreexistingmissingendboundary=false
            out.println("UNIT TEST 6:  test for parse(InputStream) with " +
		"no end boundary" +
		"mail.mime.multipart.ignoremissingendboundary=false");
	    clearAll();
	    try {
		System.setProperty(
		    "mail.mime.multipart.ignoremissingendboundary", "false");
		m = createMessage("x", "x", false);
		mp = (MimeMultipart)m.getContent();
		mp.getCount();		// throw exception
                out.println("UNIT TEST 6:  FAILED\n");
                errors++;
	    } catch (MessagingException mex) {
                out.println("UNIT TEST 6:  passed\n");
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.allowempty=true
            out.println("UNIT TEST 7:  test for parse(InputStream) with " +
		"mail.mime.multipart.allowempty=true");
	    clearAll();
	    System.setProperty( "mail.mime.multipart.allowempty", "true");
	    m = createEmptyMessage();
	    mp = (MimeMultipart)m.getContent();
	    if (mp.getCount() == 0) {
                out.println("UNIT TEST 7:  passed\n");
	    } else {
                out.println("UNIT TEST 7:  FAILED\n");
                errors++;
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.allowempty default
            out.println("UNIT TEST 8:  test for parse(InputStream) with " +
		"mail.mime.multipart.allowempty default");
	    clearAll();
	    try {
		m = createEmptyMessage();
		mp = (MimeMultipart)m.getContent();
		mp.getCount();		// throw exception
                out.println("UNIT TEST 8:  FAILED\n");
                errors++;
	    } catch (MessagingException mex) {
                out.println("UNIT TEST 8:  passed\n");
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.allowempty=true with output
            out.println("UNIT TEST 9:  test for writeTo(OutputStream) with " +
		"mail.mime.multipart.allowempty=true");
	    clearAll();
	    System.setProperty( "mail.mime.multipart.allowempty", "true");
	    m = new MimeMessage(session);
	    mp = new MimeMultipart();
	    m.setContent(mp);
	    m.writeTo(new NullOutputStream());
	    if (mp.getCount() == 0) {
                out.println("UNIT TEST 9:  passed\n");
	    } else {
                out.println("UNIT TEST 9:  FAILED\n");
                errors++;
	    }
            // END UNIT TEST:

            // BEGIN UNIT TEST:
	    // test mail.mime.multipart.allowempty default with output
            out.println("UNIT TEST 10:  test for writeTo(OutputStream) with " +
		"mail.mime.multipart.allowempty default");
	    clearAll();
	    try {
		m = new MimeMessage(session);
		mp = new MimeMultipart();
		m.setContent(mp);
		m.writeTo(new NullOutputStream());	// throw exception
                out.println("UNIT TEST 10:  FAILED\n");
                errors++;
	    } catch (IOException ioex) {
                out.println("UNIT TEST 10:  passed\n");
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

    /**
     * Create a test message.
     * If param is not null, it specifies the boundary parameter.
     * The actual boundary is specified by "actual".
     * If "end" is true, include the end boundary.
     */
    @SuppressWarnings("deprecation")
    private static MimeMessage createMessage(String param, String actual,
				boolean end) throws MessagingException {
        String content =
	    "Mime-Version: 1.0\n" +
	    "Subject: Example\n" +
	    "Content-Type: multipart/mixed; " +
		(param != null ? "boundary=\"" + param + "\"" : "") + "\n" +
	    "\n" +
	    "preamble\n" +
	    "--" + actual + "\n" +
	    "\n" +
	    "first part\n" +
	    "\n" +
	    "--" + actual + "\n" +
	    "\n" +
	    "second part\n" +
	    "\n" +
	    (end ? "--" + actual + "--\n" : "");
 
	return new MimeMessage(session, new StringBufferInputStream(content));
    }

    /**
     * Create a test message with no parts.
     */
    @SuppressWarnings("deprecation")
    private static MimeMessage createEmptyMessage() throws MessagingException {
        String content =
	    "Mime-Version: 1.0\n" +
	    "Subject: Example\n" +
	    "Content-Type: multipart/mixed; boundary=\"x\"\n\n";
 
	return new MimeMessage(session, new StringBufferInputStream(content));
    }
}

/**
 * An OutputStream that throws away all data written to it.
 */
class NullOutputStream extends OutputStream {

    public void write(int b) throws IOException {
    }

    public void write(byte[] b) throws IOException {
    }

    public void write(byte[] b, int off, int len) throws IOException {
    }
}
