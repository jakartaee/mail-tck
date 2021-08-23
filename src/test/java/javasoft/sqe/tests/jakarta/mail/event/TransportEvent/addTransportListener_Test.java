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

package javasoft.sqe.tests.jakarta.mail.event.TransportEvent;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.event.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addTransportListener()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type/value of the returned object.	<p>
 *
 *              Listen and notifies of success/failure events for message delivery. <p>
 * api2test: public addTransportListener(TransportListener)  <p>
 *
 * how2test: Call this API. Then verify that the notification occur when message
 *           delivery events occur via a Transport. If this happens then this test 
 *           passed otherwise it fails.
 */

public class addTransportListener_Test extends MailTest implements ConnectionListener, TransportListener {

    boolean delivered = false;
    boolean notdelivered = false;
    boolean partialldelivery = false;

    public static final String SUBJECT = "Transport class test";
    public static final String TEXT = "Testing Transport class send() API";
    public static final String MAILER = "JavaMail";
    static String msgText = "This is a message body.\nHere's the second line.";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // implement ConnectionListener interface
    public void opened(ConnectionEvent e)
    {
	out.fine(">>> ConnectionListener.opened()");
    }
    public void disconnected(ConnectionEvent e) {}

    public void closed(ConnectionEvent e)
    {
	out.fine(">>> ConnectionListener.closed()");
    }

    // implement TransportListener interface
    public void messageDelivered(TransportEvent e)
    {
	out.fine(">>> TransportListener.messageDelivered().Valid Addresses:");
	Address[] valid = e.getValidSentAddresses();
	delivered = true;

	if( valid != null ) {
	    for(int i = 0; i < valid.length; i++)
		out.fine("    " + valid[i]);
	}
    }
    public void messagePartiallyDelivered(TransportEvent e) { partialldelivery = true; }

    public void messageNotDelivered(TransportEvent e)
    {
	out.fine(">>> TransportListener.messageNotDelivered().Invalid Addresses:");
	Address[] invalid = e.getInvalidAddresses();
	notdelivered = true;

	if (invalid != null) {
	    for (int i = 0; i < invalid.length; i++) 
		out.fine("    "+ invalid[i]);
	}
    }

    public Status run()
    {
	

        out.fine("\nTesting class TransportEvent: addTransportListener(TransportListener)");

        try {
           // Get a Session object
              Session session = createSession();

	   // begin unit testcase 1: => message delivered

           // Create a message object
              MimeMessage msg1 = new MimeMessage(session);

              if( msg1 == null ) {
                  return Status.failed("WARNING: Failed to create a message object!");
              }
           // Construct an address array
              InternetAddress addr1 = new InternetAddress(to);

              if( addr1 == null ) {
                  return Status.failed("WARNING: Failed to create a InternetAddress object!");
              }
              InternetAddress addrs1[] = new InternetAddress[1];
              addrs1[0] = addr1;

              msg1.setFrom(addr1);
              msg1.setRecipients(Message.RecipientType.TO, addrs1);
              msg1.setSubject("JavaMail send() API Test");
              msg1.setContent(msgText, "text/plain");

           // Get smtp transport object
              Transport transport = session.getTransport(transport_protocol);

           // BEGIN UNIT TEST:
              out.fine("UNIT TEST 1: addTransportListener(TransportListener)\n");

              transport.addConnectionListener(this);
              transport.addTransportListener(this);     // API TEST

           // Connect
              if( transport_host != null ) {
		  if( auth )
		      transport.connect(transport_host, user, password);
		  else
		      transport.connect(transport_host, null, null);
              } else
                  transport.connect();

           // Send the mail message
              transport.sendMessage(msg1, addrs1);

           // end unit testcase 1:
	   // begin unit testcase 2: => message not delivered

           // Create a message object
              MimeMessage msg2 = new MimeMessage(session);

              if( msg2 == null ) {
                  return Status.failed("WARNING: Failed to create a message object!");
              }
           // Construct an address array
              InternetAddress addr2 = new InternetAddress(from);

              if( addr2 == null ) {
                  return Status.failed("WARNING: Failed to create a InternetAddress object!");
              }
              InternetAddress addrs2[] = new InternetAddress[1];
              addrs2[0] = addr2;

              msg2.setFrom(addr1);
              msg2.setRecipients(Message.RecipientType.TO, addrs2);
              msg2.setSubject("JavaMail send() API Test");
              msg2.setContent(msgText, "text/plain");

           // Send the mail message off via the specified addresses
              transport.sendMessage(msg2, addrs2);

           // end unit testcase 2:
           // begin unit testcase 3: => message partially delivered

           // Create a message object
              MimeMessage msg3 = new MimeMessage(session);

              if( msg3 == null ) {
                  return Status.failed("WARNING: Failed to create a message object!");
              }
           // Construct an address array

              InternetAddress addrs3[] = new InternetAddress[2];
              addrs3[0] = addr1;
	      addrs3[1] = addr2;

              msg3.setFrom(addr1);
              msg3.setRecipients(Message.RecipientType.TO, addrs3);
              msg3.setSubject("JavaMail send() API Test");
              msg3.setContent(msgText, "text/plain");

           // Send the mail message off via the specified addresses
              transport.sendMessage(msg3, addrs3);

           // end unit testcase 3:

	      try { Thread.sleep(100); } catch(InterruptedException e) { }

	      if( (delivered || notdelivered ) || partialldelivery )
		   out.fine("UNIT TEST 1:  passed\n");
	      else {
		    out.fine("Failed to invoke TransportListener events!");
		    out.fine("UNIT TEST 1:  FAILED\n");
		    errors++;
	      }
	   // END UNIT TEST:

	      transport.removeTransportListener(this);		// API TEST
	      transport.removeConnectionListener(this);		// API TEST
	      transport.close();

	      checkStatus();

        } catch ( MessagingException mex ) {
	    try { Thread.sleep(100); } catch(InterruptedException e) { }

	    MessagingException _mex = mex;
	    int n = 0;
	    while (_mex != null) {
		out.fine("--- Chained exception "+ (++n) +"----");
		_mex.printStackTrace();
		if (_mex instanceof SendFailedException) {
		    SendFailedException sfex = (SendFailedException)_mex;
		    Address[] invalid = sfex.getInvalidAddresses();
		    if (invalid != null) {
			out.fine("    ** Invalid Addresses");
			if (invalid != null) {
			    for (int i = 0; i < invalid.length; i++) 
				out.fine("         "+ invalid[i]);
			}
		    }
		    Address[] valid = sfex.getValidUnsentAddresses();
		    if (valid != null) {
			out.fine("    ** Valid Addresses");
			if (valid != null) {
			    for(int i = 0; i < valid.length; i++) 
				out.fine("         "+ valid[i]);
			}
		    }
		}
		Exception _tempex = _mex.getNextException();
		if (_tempex == null)
		    return null;
		else if (_tempex instanceof MessagingException)
		    _mex = (MessagingException) _tempex;
		else {
		    _tempex.printStackTrace();
		    return null;
		}
	    }
	}
	return status;
     }
}
