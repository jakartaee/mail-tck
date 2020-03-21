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
 * This class tests the <strong>getNonMatchingHeaders()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	Return non-matching headers from this envelope as an Enumeration of Header objects.<p>
 * api2test: public Enumeration getNonMatchingHeaders(String[])  <p>
 *
 * how2test: Call this API with array of strings that you want to filter out. Then write
 *	     the enumerated list returned of non-matching header strings. If this operation
 *	     is successfull then this testcase passes, otherwise it fails.
 */

public class getNonMatchingHeaders_Test extends MailTest {

    public static String[] headerlist = { "Return-Path","Message-ID","Received",
                                           "X-Mailer","Content-Transfer-Encoding",
                                           "Content-MD5","Organization","Precedence"
                                        };

    public static void main( String argv[] )
    {
        getNonMatchingHeaders_Test test = new getNonMatchingHeaders_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: getNonMatchingHeaders(String[])\n");

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

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
		// Get non-matching headers for given message(s)
	        out.println("UNIT TEST "+ i +":  getNonMatchingHeaders(String[])");

	        Enumeration nonMatcheaders = msg.getNonMatchingHeaders(headerlist);	// API TEST

	        while( nonMatcheaders.hasMoreElements() ) {
		       Header headers = (Header)nonMatcheaders.nextElement();
		       out.println(headers.getName());
	        }
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
