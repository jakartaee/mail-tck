/*
 * Copyright (c) 2002, 2021 Oracle and/or its affiliates. All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>appendMessages(Message msg[])</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Append given Messages to this folder.  <p>
 * api2test: public void appendMessages(Message msgs[])  <p>
 *
 * how2test: Test this API by appending an entire mailbox to another testfolder. <p>
 *	     Test this method on open/closed folder. Check that the expected <p>
 *	     number of messages got appended, if so then the test passes, <p>
 *	     otherwise it fails. <p>
 *
 *     Note: Folder implementations must not abort this operation if a Message <p>
 *	     in the given message array turns out to be an expunged Message.
 */

public class appendMessages_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: appendMessages(Message msgs[])\n");

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

	     // Create a new test folder in same Session/Store
	     Folder testfolder = root.getFolder(testbox);

             if( testfolder == null ) {
                 return Status.failed("Invalid test folder object!");
             }
	     // Delete any preexisting test folder

	     if( testfolder.exists() ) {
		 testfolder.delete(false);
             }
	     // Create a new test folder

             if( !(testfolder.create(Folder.HOLDS_MESSAGES)) ) {
                  return Status.failed("Failed to create test folder!");
             }
	     testfolder.open(Folder.READ_WRITE);

	     if( msgcount == -1 ) {
		 msgcount = folder.getMessageCount();
		 if( msgcount < 1 )
		     return Status.failed("Mail folder is empty!");
	     }
          // BEGIN UNIT TEST 1:
	     // Append messages 1-N to open tester folder

	     out.fine("UNIT TEST 1: appendMessages(Message msgs[1-N])");

             Message[] msg = folder.getMessages();
	     testfolder.appendMessages(msg);	    // API TEST
	     // give them time to arrive
	     testfolder.close(false);
	     testfolder.open(Folder.READ_WRITE);
	     int newTotal = testfolder.getMessageCount();

	     if ( newTotal == msgcount )
	          out.fine("UNIT TEST 1:  passed\n");
	     else {
		   out.fine("UNIT TEST 1:  FAILED\n");
		   errors++;
	     }
	     testfolder.close(false);	    // close test folder

          // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     // Append messages 1-N to closed tester folder

             out.fine("UNIT TEST 2: appendMessages(Message msgs[1-N])");

	     testfolder.appendMessages(msg);     // API TEST
	     testfolder.open(Folder.READ_WRITE);
             newTotal = testfolder.getMessageCount();

             if ( newTotal == ( 2 * msgcount ))
                  out.fine("UNIT TEST 2:  passed\n");
             else {
                   out.fine("UNIT TEST 2:  FAILED\n");
                   errors++;
             }
	     testfolder.close(false);

          // END UNIT TEST 2:
	  // BEGIN UNIT TEST 3:
	     // Append expunged messages 1-N to closed test folder

             out.fine("UNIT TEST 3: appendMessages(Message msgs[1-N])");
	     testfolder.open(Folder.READ_WRITE);

             for( int j = 0; j < msg.length; j++ ) {
		  msg[j] = new MimeMessage((MimeMessage)msg[j]);
		  msg[j].setFlag(Flags.Flag.DELETED, true);	// expunging message
	     }

	     testfolder.appendMessages(msg);		// API TEST
	     // give them time to arrive
	     testfolder.close(false);
	     testfolder.open(Folder.READ_WRITE);
             newTotal = testfolder.getMessageCount();

             if ( newTotal == ( 3 * msgcount ))
                  out.fine("UNIT TEST 3:  passed\n");
             else  {
                    out.fine("UNIT TEST 3:  FAILED\n");
                    errors++;
             }
	  // END UNIT TEST 3:

             folder.close(false);
	     testfolder.close(false);
             testfolder.delete(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
