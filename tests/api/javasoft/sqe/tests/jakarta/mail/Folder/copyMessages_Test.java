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
 * This class tests the <strong>copyMessages(..)</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	Copy the specified Messages from this Folder into another Folder. <p>
 * api2test: public boolean copyMessages(Message msgs[], Folder folder)  <p>
 *
 * how2test: Call this API, copy messages to a test folder, check that expected <p>
 *	     number of messages got copied. If so then test is passing. <p>
 *
 *	  a) This operation appends these Messages to the destination Folder. <p>
 *	  b) The destination Folder does not have to be opened. <p>
 *	  c) The specified Message objects must belong to this folder. Folder  <p>
 *	     implementations might be able to optimize this method by doing server-side <p>
 *	     copies. <p>
 *	  d) An implementation must not abort the operation if a Message in the array  <p>
 *	     turns out to be an expunged Message.
 */

public class copyMessages_Test extends MailTest {

    public static void main( String argv[] )
    {
        copyMessages_Test test = new copyMessages_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Folder: copyMessages(Message msgs[], Folder folder)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Open a Folder. Create it if it doesn't exist
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
		  return Status.failed("Invalid folder object!");
       	     }
             folder.open(Folder.READ_ONLY);

             // Create a new test folder in same protocol store
             Folder testfolder = root.getFolder(testbox);

             if( testfolder == null ) {
                 return Status.failed("Invalid test folder");
             }

	     if( ! testfolder.exists() ) {
		 if( !(testfolder.create(Folder.HOLDS_MESSAGES)) )
		       return Status.failed("Failed to create test folder.");
	     }
	  // BEGIN UNIT TEST 1:
	     // case 1: open test folder
	     testfolder.open(Folder.READ_WRITE);
	     
             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
             // Get all messages
	     Message[] msgs = folder.getMessages();

             out.println("UNIT TEST 1: copyMessages(Message msgs[]," + testbox + ")");
	     
	     // Copy all messages
	     folder.copyMessages(msgs, testfolder);   // API TEST

	     testfolder.open(Folder.READ_WRITE); 

	     if ( testfolder.getMessageCount() == msgcount )
		  out.println("UNIT TEST 1 with mods: passed\n");
	     else {
	           out.println("UNIT TEST 1 with mods: FAILED\n");
		   errors++;
	     }

	     testfolder.close(false);	// closing the testfolder


          // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     // case 2: close test folder

             out.println("UNIT TEST 2: copyMessages(Message msgs[]," + testbox + ")");

             folder.copyMessages(msgs, testfolder);   // API TEST
	     testfolder.open(Folder.READ_WRITE);

	     Thread.sleep(5000);

             if ( testfolder.getMessageCount() == ( 2 * msgcount ))
                  out.println("UNIT TEST 2: passed\n");
             else {
               	   out.println("UNIT TEST 2: FAILED\n");
               	   errors++;
	     }
	  // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
             // case 3: copy expunged messages to test folder
             // Copy given message count of them to new test folder

             for( int k = 0; k < msgs.length; k++ )
                  msgs[k].setFlag(Flags.Flag.DELETED, true);

             out.println("UNIT TEST 3: copyMessages(Message msgs[]," + testbox + ")");

             folder.copyMessages(msgs, testfolder);   // API TEST

             if ( testfolder.getMessageCount() == ( 3 * msgcount ) )
                  out.println("UNIT TEST 3: passed\n");
             else {
                    out.println("UNIT TEST 3: FAILED\n");
                    errors++;
	     }
	  // END UNIT TEST 3:
	  
	     testfolder.close(false);
	     testfolder.delete(false);
             folder.close(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
