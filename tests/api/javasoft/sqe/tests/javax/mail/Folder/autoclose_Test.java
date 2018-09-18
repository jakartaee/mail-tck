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

package javasoft.sqe.tests.javax.mail.Folder;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>AutoCloseable</strong> and
 * <strong>close()</strong> APIs. <p>
 *
 *		// Close this folder   <p>
 * api2test: public void close(boolean expunge)  <p>
 * api2test: AutoCloseable <p>
 *
 * how2test: Open a folder in a try-with-resources block, add messages
 *	     to the folder, mark the messages DELETED, exit the block.
 *	     If the folder is closed and the messages are deleted
 *	     then the test passes, otherwise it fails.
 */

public class autoclose_Test extends MailTest {

    public static void main( String argv[] )
    {
        autoclose_Test test = new autoclose_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Folder: auto close()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Open a Folder. Don't create if it does not exist
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

	     if ( folder == null ) {
	          return Status.failed("Invalid folder object!");
	     }

	  // BEGIN UNIT TEST 1:

	     out.println("UNIT TEST 1: auto close()");

          // Create a new test folder in same protocol store
             Folder testfolder0 = null;
             try (Folder testfolder = root.getFolder(testbox)) {

		 testfolder0 = testfolder;
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

	     }

	     if (testfolder0.isOpen()) {
                    out.println("UNIT TEST 1: FAILED\n");
                    errors++;
	     } else {

		 testfolder0.open(Folder.READ_ONLY);
		 int newTotal = testfolder0.getMessageCount();

		 testfolder0.close(false);		// API TEST
		 testfolder0.delete(true);

		 if ( newTotal == 0 )
		      out.println("UNIT TEST 1: passed\n");	
		 else {
			out.println("UNIT TEST 1: FAILED\n");
			errors++;
		 }
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
