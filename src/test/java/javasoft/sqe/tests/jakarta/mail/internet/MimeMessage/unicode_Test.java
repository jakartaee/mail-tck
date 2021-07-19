/*
 * Copyright (c) 2002, 2021 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.Properties;
import java.nio.charset.StandardCharsets;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

public class unicode_Test extends MailTest {

    private static final String mailbox = "test\u03b1@example\u03b1.com";
    private static final String personal = "test\u03b1 user\u03b1";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting MimeMessage class Unicode APIs\n");

	try {
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1: mail.mime.allowutf8 writeTo()\n");
	     Properties props = new Properties();
	     props.setProperty("mail.mime.allowutf8", "true");
	     Session s = Session.getInstance(props);
	     MimeMessage msg = new MimeMessage(s);
	     msg.setRecipient(Message.RecipientType.TO,
				new InternetAddress(mailbox));
	     msg.setHeader("Header", personal);
	     msg.setText("");
	     ByteArrayOutputStream bos = new ByteArrayOutputStream();
	     msg.writeTo(bos);
	     bos.close();
	     ByteArrayInputStream bis =
			    new ByteArrayInputStream(bos.toByteArray());
	     BufferedReader r = new BufferedReader(
			    new InputStreamReader(bis, StandardCharsets.UTF_8));
	     boolean foundTo = false;
	     boolean foundHeader = false;
	     String expectedTo = "To: " + mailbox;
	     String expectedHeader = "Header: " + personal;
	     String line;
	     while ((line = r.readLine()) != null) {
		if (line.equals(expectedTo))
		    foundTo = true;
		if (line.equals(expectedHeader))
		    foundHeader = true;
	     }
	     if (foundTo && foundHeader)
		  out.println("UNIT TEST 1:  passed\n");
	     else {
		  out.println("UNIT TEST 1:  FAILED\n");
		  errors++;
	     }
	  // END UNIT TEST 1:

	  // BEGIN UNIT TEST 2:
	     out.println("UNIT TEST 2: mail.mime.allowutf8 constructor\n");
	     bis = new ByteArrayInputStream(bos.toByteArray());
	     msg = new MimeMessage(s, bis);
	     InternetAddress to =
		(InternetAddress)msg.getRecipients(Message.RecipientType.TO)[0];
	     String header = msg.getHeader("Header", null);
	     if (to.getAddress().equals(mailbox) && header.equals(personal))
		  out.println("UNIT TEST 2:  passed\n");
	     else {
		  out.println("UNIT TEST 2:  FAILED\n");
		  errors++;
	     }
	  // END UNIT TEST 2:

	     checkStatus();
        } catch (Exception e) {
	     handlException(e);
          }
	  return status;
    }
}
