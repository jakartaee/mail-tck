/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.javax.mail.Message;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>setRecipients()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		    Set the recipient addresses. <p>
 * api2test: public void setRecipients(int, Address[])  <p>
 *
 * how2test: Call this API with {1|2|3} and address arguments, then call getRecipients(), if
 *	     the set and get values are the same then the testcase passes, otherwise it fails.
 */

public class setRecipients_Test extends MailTest {

    public static String BCC = "javatest@eng";

    public static void main( String argv[] )
    {
        setRecipients_Test test = new setRecipients_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: setRecipients(int, Address[])\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.println("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
	     // Create an address object
             Address[] to;
	     Address[] cc;
	     Address[] bcc;
	     InternetAddress[] Bcc = new InternetAddress[1];
	     Bcc[0] = new InternetAddress(BCC);

          // BEGIN UNIT TEST:
	     // Set the message's recipients
	     out.println("UNIT TEST 1:  setRecipients(int, Address[])");

	     /* to unit test */
	     to = msg.getRecipients(Message.RecipientType.TO);
	     msg.setRecipients(Message.RecipientType.TO, to);	// API TEST
	     to = msg.getRecipients(Message.RecipientType.TO);
	     out.print("setRecipients(1");

	     if( to != null && to.length != 0 ) {
	         for( int j = 0; j < to.length; j++ )
                      out.print(", "+ to[j]);
                 out.println(")");
	     } else
		    out.println(", empty field)");

	     /* cc unit test */
	     cc = msg.getRecipients(Message.RecipientType.CC);
	     msg.setRecipients(Message.RecipientType.CC, cc);	// API TEST
	     cc = msg.getRecipients(Message.RecipientType.CC);
             out.print("setRecipients(2");

	     if( cc != null && cc.length != 0 ) {
                 for( int j = 0; j < cc.length; j++ )
                      out.print(", "+ cc[j]);
                 out.println(")");
	     } else
		   out.println(", empty field)");

	     /* bcc unit test */
	    msg.setRecipients(Message.RecipientType.BCC, (Address[])Bcc);	// API TEST
	    bcc = msg.getRecipients(Message.RecipientType.BCC);

	    if( bcc != null )
	        out.println("setRecipients(3, "+ bcc[0] +")");
	    else
		out.println("setRecipients(3, empty field)");

            out.println("UNIT TEST 1:  passed\n");
	 // END UNIT TEST:

	    status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
