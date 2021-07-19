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

package javasoft.sqe.tests.jakarta.mail.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>SendFailedException()</strong> API.
 * It does this by invoking the test api and then checking the values
 * of the returned Message array object.  <p>
 *
 *              Constructor thats thrown when a message could not be sent. <p>
 * api2test: public SendFailedException(void|String|Exception|Address[])  <p>
 *
 * how2test: Use send a message with invalid address, if this results in SendFailedException
 *           exception being thrown then this testcase passes, otherwise it fails.
 */

public class sendFailedException_Test extends MailTest {

    public static String TO = "ksnijjar@@eng";
    public static final String SUBJECT = "Testing SendFailedException() constructor";
    static String msgText = "This is a message body.\nHere's the second line.";

    public Transport transport;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

	TO = to;
        out.println("\nTesting class SendFailedException: SendFailedException(void|String|Exception|Address[])\n");

        try {
           // Get a Session object
              Session session = Session.getInstance(properties, null);

           // Get a Transport object
              transport = session.getTransport(transport_protocol);

           // Create a message object
              MimeMessage msg = new MimeMessage(session);

              if ( msg == null ) {
                   return Status.failed("WARNING: Failed to create a message object!");
              }
           // Construct an address array
              InternetAddress addr = new InternetAddress(TO);
              Address addrs[] = new Address[1];
              addrs[0] = addr;

              msg.setFrom(addr);
              msg.setRecipients(Message.RecipientType.TO, addrs);
              msg.setSubject(SUBJECT);
              msg.setContent(msgText, "text/plain");

             // Connect
              if ( host != null || user != null || password != null )
                  transport.connect(host, user, password);
              else
                  transport.connect();

           // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1: SendFailedException()");

              // send the mail message
              transport.send(msg);	// API TEST

              out.println("UNIT TEST 1: FAILED\n");
           // END UNIT TEST 1:

	      status = Status.failed(" Failed to catch SendFailedException ");
        } catch ( SendFailedException me ) {
		out.println("UNIT TEST 1: passed.\n");
		try {
		      transport.close();
		      ExceptionTest(me);
		} catch ( Exception e ) {
		      handlException(e);
		}
        } catch ( Exception mx ) {
                handlException(mx);
        }
	return status;
     }
}
