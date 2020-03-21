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

package javasoft.sqe.tests.jakarta.mail.search.ReceivedDateTerm;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>ReceivedDateTerm()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		A contructor that takes an integer and date arguments. <p>
 * api2test: public ReceivedDateTerm(int, Date)  <p>
 *		The match method.   <p>
 * api2test: boolean match(Message)  <p>
 *
 * how2test: Call these APIs with int/date/message arguments and if 'match'
 *	     returns boolean value, then this testcase passes.
 */

public class receiveddateterm_Test extends MailTest {

    public static void main( String argv[] )
    {
        receiveddateterm_Test test = new receiveddateterm_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class ReceivedDateTerm: ReceivedDateTerm(int, Date)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
                  return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     boolean foundit;

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
		Date receivedDate = msg.getReceivedDate();

             // BEGIN UNIT TEST:
                out.println("UNIT TEST "+ i +":  ReceivedDateTerm(ComparisonTerm.EQ, "+ receivedDate +")");

                ReceivedDateTerm rdt = new ReceivedDateTerm(ComparisonTerm.EQ, receivedDate);    // API TEST

                if( rdt == null ) {
                    log.println("Warning: ReceivedDateTerm contructor returned a Null object!");
		    continue;
                } else
                      out.println("UNIT TEST "+ i +": passed\n");

	        out.println("UNIT TEST "+ i +":  match(Message)");

		// match the message number
		foundit = rdt.match(msg);	// API TEST

	        if( foundit ) {
	            out.println("Expected received date found in message header.");
                    out.println("UNIT TEST "+ i +":  passed\n");
	        } else {
		        out.println("Expected received date not found in message header!");
			out.println("UNIT TEST "+ i +":  passed\n");
	        }
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
