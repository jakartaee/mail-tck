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

package javasoft.sqe.tests.jakarta.mail.internet.MimePartDataSource;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getName()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		DataSource method to return a name. <p>
 * api2test: public String getName()  <p>
 *
 * how2test: Call API, verify the type of object returned to be an empty
 *	     string. If so then testcase passes, otherwise it fails.
 */

public class getName_Test extends MailTest {

    public static void main( String argv[] )
    {
        getName_Test test = new getName_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimePartDataSource: getName()\n");

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
          // Get message object
             MimeMessage msg = (MimeMessage)folder.getMessage(1);

             if( msg == null ) {
                 return Status.failed("Warning: Failed to get message number: 1");
             }
          // Create a MimePartDataSource object
             MimePartDataSource mpds = new MimePartDataSource((MimePart)msg);

             if( mpds == null ) {
		 return Status.failed("Warning: Failed to create a MimePartDataSource object!");
             }
          // BEGIN UNIT TEST 1:
             out.println("UNIT TEST 1: getName()");

             String name = mpds.getName();    // API TEST

             if( name.equals("") )
                 out.println("UNIT TEST 1: passed\n");
             else {
                   out.println("UNIT TEST 1: FAILED\n");
		   errors++;
             }
          // END UNIT TEST 1:
	     folder.close(false);
	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
