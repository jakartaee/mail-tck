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

package javasoft.sqe.tests.jakarta.mail.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>IllegalWriteException()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * api2test: public IllegalWriteException(void | String) <p>
 *
 * how2test: Try writing to read-only attribute and if this results
 *	     in an IllegalWrite exception, then this testcase passes.
 *	     Alternatively, if the message is actually changed, this
 *	     test passes (not all stores prohibit changing messages).
 */

public class illegalWriteException_Test extends MailTest {

    private Store store = null;
    private Folder folder = null;
    private Folder testfolder = null;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class IllegalWriteException: IllegalWriteException()\n");

        try {
          // Connect to host server
             store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
          // Get a test Folder object
             testfolder = root.getFolder(testbox);

             if( testfolder == null ) {
                 return Status.failed("Invalid test folder object!");
             }
	  // Delete testfolder if it already exists

	     if( testfolder.exists() ) {
		 testfolder.delete(false);
	     }
	  // Create test folder if it doesn't exist

	     if( !testfolder.exists() ) {
		 testfolder.create(Folder.HOLDS_MESSAGES);
	     }
	     folder.open(Folder.READ_WRITE);	
             testfolder.open(Folder.READ_WRITE);

	  // Get and copy messages to test folder
	     Message[] msgs = folder.getMessages();
	     testfolder.appendMessages(msgs);

	     if( testfolder.getMessageCount() < 1 ) {
		 return Status.failed("Mailbox "+mailbox+" is empty!");
	     }
          // Get a message object
             Message msg = testfolder.getMessage(1);

	     if( msg == null ) {
		 return Status.failed("Warning: Failed to get message number: 1");
	     }

	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1: IllegalWriteException(void | String)");

	  // Change the message's subject
             msg.setSubject("new subject");	// should throw exception
					// API TEST

          // Save changes to this message object
             msg.saveChanges();		// this should throw the expected exception
					// API TEST
          // If no exception, make sure the message was really changed
	     testfolder.close(false);
             testfolder.open(Folder.READ_WRITE);

	     if( testfolder.getMessageCount() < 1 ) {
		 return Status.failed("Mailbox "+mailbox+" is empty!");
	     }
          // Get a message object
             msg = testfolder.getMessage(1);

	     if( msg == null ) {
		 return Status.failed("Warning: Failed to get message number: 1");
	     }

	     if (!msg.getSubject().equals("new subject")) {
		 out.fine("UNIT TEST 1: FAILED.\n");
		 return Status.failed("Failed to change message");
	     }

	     out.fine("UNIT TEST 1: passed (message changed).\n");
          // END UNIT TEST:
	     checkStatus();

        } catch ( IllegalWriteException iwe ) {
		out.fine("UNIT TEST 1: passed (exception thrown).\n");
		ExceptionTest(iwe);
        } catch ( MessagingException me ) {
		handlException(me);
	} finally {
		try {
		      testfolder.close(false);
		      testfolder.delete(false);
		      folder.close(false);
		      store.close();
		} catch ( Exception e ) {
		      handlException(e);
		}
	}
	return status;
     }
}
