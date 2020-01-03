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

package javasoft.sqe.tests.jakarta.mail.Folder;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>close(boolean)</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		// Close this folder   <p>
 * api2test: public void close(boolean expunge)  <p>
 *
 * how2test: Call this API on an open folder. If this operation <p>
 *	     is successfull then the test passes, otherwise it fails.
 */

public class close_Test extends MailTest {

    public static void main( String argv[] )
    {
        close_Test test = new close_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Folder: close()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Open a Folder. Don't create if it does not exist
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

	     if ( folder == null ) {
	          return Status.failed("Invalid folder object!");
	     }

          // Create a new test folder in same protocol store
             Folder testfolder = root.getFolder(testbox);

             if ( testfolder == null ) {
                  return Status.failed("Invalid test folder object!");
             }

             if ( testfolder.exists() )
		testfolder.delete(false);

             if ( !(testfolder.create(Folder.HOLDS_MESSAGES))) {
                  return Status.failed("Failed to create test folder!");
             }

	     folder.open(Folder.READ_ONLY);
	     if ( msgcount == -1 ) {
		  msgcount = folder.getMessageCount();
		  if ( msgcount < 1 )
		       return Status.failed("Mail folder is empty!");
	     }
          // Copy msgnum of them to new test folder

             folder.copyMessages(folder.getMessages(1, msgcount), testfolder);

	     testfolder.open(Folder.READ_WRITE);

	  // Mark the messages in the test folder for deletion

	     for( int i = 1; i <= msgcount; i++ ) {
                  Message msg = testfolder.getMessage(i);
		  msg.setFlag(Flags.Flag.DELETED, true);	// set the DELETED flag
	     }
	     folder.close(false);

	  // BEGIN UNIT TEST 1:

	     out.println("UNIT TEST 1: close(true | false)");

	     testfolder.close(true);		// API TEST

	     testfolder.open(Folder.READ_ONLY);
	     int newTotal = testfolder.getMessageCount();

	     testfolder.close(false);		// API TEST
	     testfolder.delete(true);

             if ( newTotal == 0 )
                  out.println("UNIT TEST 1: passed\n");	
             else {
                    out.println("UNIT TEST 1: FAILED\n");
                    errors++;
             }
          // END UNIT TEST 1:

	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
