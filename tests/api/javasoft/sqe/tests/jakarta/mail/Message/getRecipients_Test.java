/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
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
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getRecipients()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Get all the recipient addresses of the given type. <p>
 * api2test: public Address[] getRecipients(int type)  <p>
 *
 * how2test: Call this API with any of { TO } CC | BCC } arguments, verify that this
 *	     method returns an address list, output it to stdio. If this operation
 *	     is successfull, then this testcase passes, otherwise it fails. <p>
 *
 *	    The mapping between the type and the corresponding RFC822 header
 *	    is as follows: <p>
 *                     Message.TO      "To"   <p>
 *                     Message.CC      "cc"   <p>
 *                     Message.BCC     "bcc"  <p>
 *      
 *          Returns null if the header specified by the type is not found or
 *	    if its value is empty.
 */

public class getRecipients_Test extends MailTest {

    public static Address[] to;
    public static Address[] cc;
    public static Address[] bcc;

    public static void main( String argv[] )
    {
        getRecipients_Test test = new getRecipients_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: getRecipients(int)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg =  (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
		// Get the message's recipients
	        out.println("UNIT TEST "+ i +":  getRecipients(int)");

	        to = msg.getRecipients(Message.RecipientType.TO);

		for( int j = 0; j < to.length; j++ ) {
	             if( to[j] != null )
	                 out.println("getRecipients(TO) :=> '" + to[j] + "'");
	             else
		         out.println("getRecipients(TO) :=> 'empty field'");
		}

	        cc = msg.getRecipients(Message.RecipientType.CC);

	        if( cc != null )
	            out.println("getRecipients(CC) :=> '" + cc[0] + "'");
	        else
		    out.println("getRecipients(CC) :=> 'empty field'");

	        bcc = msg.getRecipients(Message.RecipientType.BCC);

	        if( bcc != null )
	            out.println("getRecipients(BCC) :=> '" + bcc[0] + "'");
	        else
		    out.println("getRecipients(BCC) :=> 'empty field'");

                out.println("UNIT TEST "+ i +":  passed\n");
	     // END UNIT TEST:
	     }
	     folder.close(false);
	     store.close();
	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
