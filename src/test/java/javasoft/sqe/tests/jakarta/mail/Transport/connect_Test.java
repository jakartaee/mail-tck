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
 * This class tests the <strong>connect()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *              Generic connect method that takes no parameters. <p>
 * api2test: public void connect()     <p>
 *              Connect to the specified address. <p>
 * api2test: public void connect(String, String, String)  <p>
 *
 * how2test: Call these APIs with or without address. If this operation is
 *           successfull then this testcase passes otherwise it fails. <p>
 */

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

public class connect_Test extends MailTest {

    public static String FROM;
    public static String TO;
    public static final String SUBJECT = "Transport protocol mail";
    public static final String TEXT = "This is a test of Transport class!";
    public static final String MAILER = "JavaMail";
    static String msgText = "This is a message body.\nHere's the second line.";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

	FROM = from;
	TO = to;

	out.fine("\nTesting Transport class API => connect(String, String, String)\n");

	try {
	   // Get a Session object
	      Session session = createSession();

	   // Get a Transport object
	      Transport transport = session.getTransport(transport_protocol);

	   // Create a MimeMessage object
	      MimeMessage msg = new MimeMessage(session);

              if( msg == null ) {
                  return Status.failed("WARNING: Failed to create a message object");
              }
           // Construct an address array
              InternetAddress addr = new InternetAddress(to);

	      if ( addr == null ) {
		   return Status.failed("WARNING: Failed to create a InternetAddress object");
	      }
              InternetAddress addrs[] = new InternetAddress[1];
              addrs[0] = addr;

              msg.setFrom(addr);
              msg.setRecipients(Message.RecipientType.TO, addrs);
              msg.setSubject("JavaMail send() API Test");
              msg.setContent(msgText, "text/plain");

	    // BEGIN UNIT TEST 1:
	      out.fine("UNIT TEST 1:  connect(host, user, password)");
	      out.fine("		 connect()");

	     // Connect
              if( transport_host != null ) {
		  if( auth )
		      transport.connect(transport_host, user, password); // API TEST
		  else
		      transport.connect(transport_host, null, null); // API TEST
              } else
                  transport.connect();				// API TEST

	      out.fine("UNIT TEST 1: passed\n");
	    // END UNIT TEST 1:

	    // send the mail message via specified 'protocol'
              transport.sendMessage(msg, addrs);

	      status = Status.passed("OKAY");
        } catch (Exception e) {
              handlException(e);
          }
	  return status;
    }
}
