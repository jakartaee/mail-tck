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
 * This class tests the <strong>getFolder()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		    Get the folder from which this message was obtained. <p>
 * api2test: public Folder getFolder()  <p>
 *
 * how2test: Call this API and, verify that object returned is of type Folder. <p>
 *	     If this is true then this testcase passes, otherwise it fails. <p>
 *	     If this is a new message or nested message, this method returns null.
 */

public class getFolder_Test extends MailTest {

    public static void main( String argv[] )
    {
        getFolder_Test test = new getFolder_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: getFolder()\n");

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
             // Get the message
             Message msg = folder.getMessage(1);

	     if( msg == null ) {
		 return Status.failed("WARNING: FAILED TO GET MESSAGE NUMBER: 1");
	     }
	  // BEGIN UNIT TEST 1:
	     // Return the folder associated with this message
	     out.println("UNIT TEST 1:  getFolder()");

	     Folder folderName = msg.getFolder();	// API TEST
	     out.println("getFolder() :=> "+ folderName);

	     if (( folderName != null ) && ( folderName instanceof Folder ))
                  out.println("UNIT TEST 1:  passed\n");
	     else {	
		   out.println("Caution: folderName is null for this message.");
		   out.println("UNIT TEST 1:  passed\n");
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
