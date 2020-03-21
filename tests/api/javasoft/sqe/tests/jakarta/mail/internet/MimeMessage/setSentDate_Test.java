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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setSentDate()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object. <p>
 *
 *		Set the sent date of message. <p>
 * api2test: public void setSentDate(Date)  <p>
 *
 * how2test: Call API with various date objects. If this operation is
 *	     successfull then testcase passes, otherwise it fails.
 */

public class setSentDate_Test extends MailTest {

    public static void main( String argv[] )
    {
        setSentDate_Test test = new setSentDate_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeMessage: setSentDate(Date)\n");

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
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.println("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }

	     MimeMessage dmsg = (MimeMessage)folder.getMessage(1);
	     if( dmsg == null ) {
                 log.println("WARNING: FAILED TO GET MESSAGE OBJECT");
                 return Status.failed("Failed to get Message object");
             }
          // BEGIN UNIT TEST:
	     out.println("UNIT TEST 1:  setSentDate(Date)");

	     Date current_date = dmsg.getSentDate();
	     SentDateTerm daterm = new SentDateTerm(ComparisonTerm.EQ, current_date);

	     if( current_date != null && daterm != null )
	     {
		// set the message sent date
	           msg.setSentDate(current_date);	// API TEST
	           Date new_date = msg.getSentDate();

		   if( daterm.match(msg) ) {
		       out.println("setSentDate("+ new_date +")");
		       out.println("UNIT TEST 1:  passed\n");
		   } else {
			    out.println("WARNING: Message failed to set date!");
			    errors++;
		   }
	     } else
		   out.println("WARNING: Message has null date header");

	  // END UNIT TEST:
	     folder.close(false);
	     store.close();
	     checkStatus();

	} catch ( Exception e ) {
	     handlException(e);
	}
	return status;
    }
}
