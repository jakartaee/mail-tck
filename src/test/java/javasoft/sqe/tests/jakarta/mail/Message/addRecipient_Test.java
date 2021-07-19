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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addRecipient()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Add these recipient addresses to the existing ones of the given type. <p>
 * api2test: public void addRecipient(int, Address)  <p>
 *
 * how2test: Call this API with '1|2|3' input values, then call getRecipients() api
 *	     to check that api under test did its job, output to stdio.
 */

public class addRecipient_Test extends MailTest {

    public static String TO = "tester1@eng.sun.com";
    public static String CC = "tester2@eng";
    public static InternetAddress To  = null;
    public static InternetAddress cc  = null;
    public static InternetAddress bcc = null;
    public Address[] From;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

        out.println("\nTesting class Message: addRecipient(int, Address)\n");

        try {
	  // Create a Session object
             Session session = Session.getInstance(properties, null);

             if( session == null ) { 
                 return Status.failed("Warning: Failedto create a Session object.");
             }
	  // Create a MimeMessage object
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 return Status.failed("Warning: Failedto create a MimeMessage object.");
             }
             To = new InternetAddress(TO);
             cc = new InternetAddress(CC);
	     bcc = cc;

          // BEGIN UNIT TEST:
	     out.println("UNIT TEST 1:  addRecipient(int, Address)");

		// add message recipients
	     msg.addRecipient(Message.RecipientType.TO, To); 	// API TEST
	     msg.addRecipient(Message.RecipientType.CC, cc);	// API TEST
	     msg.addRecipient(Message.RecipientType.BCC, bcc);	// API TEST

	     From = msg.getRecipients(Message.RecipientType.TO);
	     out.print("addRecipients(1");

	     for( int j = 0; j < From.length; j++ )
	          out.print(", " + From[j]);
	     out.println(")");

	     From = msg.getRecipients(Message.RecipientType.CC);
	     out.print("addRecipients(2");

             for( int j = 0; j < From.length; j++ )
                  out.print(", " + From[j]);
             out.println(")");

	     From = msg.getRecipients(Message.RecipientType.BCC);
	     out.println("addRecipients(3, " + From[0] + ")");

             out.println("UNIT TEST 1:  passed\n");
	  // END UNIT TEST:

	     checkStatus();
        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
