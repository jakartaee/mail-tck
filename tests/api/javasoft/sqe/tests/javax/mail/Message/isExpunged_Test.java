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
 * This class tests the <strong>isExpunged()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Checks whether this message is expunged. <p>
 * api2test: public boolean isExpunged()  <p>
 *
 * how2test: Call is API on any message object, check the boolean value returned.
 *	     Regardless of returned value this testcase passes.
 */

public class isExpunged_Test extends MailTest {

    public static void main( String argv[] )
    {
        isExpunged_Test test = new isExpunged_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: isExpunged()\n");

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
	  // BEGIN UNIT TEST

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                Message msg = (Message)folder.getMessage(i);

	        if ( msg == null ) {
		     log.println("FAILED TO GET: "+ i +" MESSAGE");
		     continue;
	        }
             // Check if message is expunged
	        out.println("UNIT TEST "+ i +":  isExpunged()");

                if ( msg.isExpunged() ) 	// API TEST
                     out.println("UNIT TEST "+ i +":  passed\n");
                else {
                      out.println("UNIT TEST "+ i +":  passed\n");
            	}
	     }
	  // END UNIT TEST
	     folder.close(false);
	     store.close();
	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
