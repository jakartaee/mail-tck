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

package javasoft.sqe.tests.jakarta.mail.Transport;

/**
 * This class tests the <strong>send()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *              Send this message.  <p>
 * api2test: public void send(msg)     <p>
 *              Send this message to the specified addresses. <p>
 * api2test: public void send(Message, Address[])  <p>
 *              Send this message using the specified username and password. <p>
 * api2test: public void send(Message, String, String)  <p>
 *
 * how2test: Call these APIs for given message objects with or without address list.
 *           If this invocation is successfull then this testcase passes otherwise
 *           it fails. <p>
 */

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

public class send_Test extends MailTest {

    public static String TO = "ksnijjar@eng";
    public static final String SUBJECT = "Transport class test";
    public static final String TEXT = "Testing Transport class send() API";
    public static final String MAILER = "JavaMail";
    static String msgText = "This is a message body.\nHere's the second line.";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

	TO = to;
	out.println("\nTesting Transport class => send(Message, Address[])\n");

	try {
           // Get a Session object
              Session session = createSession();

	   // Create a message object
	      MimeMessage msg = new MimeMessage(session);

              if ( msg == null ) {
                   return Status.failed("WARNING: Failed to create a message object!");
              }
           // Construct an address array
              InternetAddress addr = new InternetAddress(TO);
              InternetAddress addrs[] = new InternetAddress[1];
              addrs[0] = addr;

              msg.setFrom(addr);
              msg.setRecipients(Message.RecipientType.TO, addrs);
              msg.setSubject("JavaMail send() API Test");
              msg.setContent(msgText, "text/plain");

	   // BEGIN UNIT TEST 1:
	      out.println("UNIT TEST 1: send(Message)");

	   // send the mail message
              Transport.send(msg);		// API TEST

              out.println("UNIT TEST 1: passed\n");
	   // END UNIT TEST 1:

           // BEGIN UNIT TEST 2:
              out.println("UNIT TEST 2: send(Message, Address[])");

           // send the mail message off via the specified addresses
              Transport.send(msg, addrs);       // API TEST

              out.println("UNIT TEST 2: passed\n");
           // END UNIT TEST 2:

           // BEGIN UNIT TEST 3:
              out.println("UNIT TEST 3: send(Message, String, String)");

	      // create a Session with no authenticator
	      session = Session.getInstance(properties, null);
	      session.setDebug(debug);
	      msg = new MimeMessage(session);
              msg.setFrom(addr);
              msg.setRecipients(Message.RecipientType.TO, addrs);
              msg.setSubject("JavaMail send() API Test");
              msg.setContent(msgText, "text/plain");

           // send the mail message off with the username and password
	      if (auth)
		  Transport.send(msg, user, password);       // API TEST
	      else
		  Transport.send(msg, null, null);       // API TEST

              out.println("UNIT TEST 3: passed\n");
           // END UNIT TEST 3:

	      status = Status.passed("OKAY");

        } catch (Exception e) {
              handlException(e);
          }
	  return status;
    }
}
