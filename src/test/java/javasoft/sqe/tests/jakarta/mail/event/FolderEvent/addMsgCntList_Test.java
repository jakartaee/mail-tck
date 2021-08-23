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

package javasoft.sqe.tests.jakarta.mail.event.FolderEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.event.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addMessageCountListener()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type/value of the returned object.	<p>
 *
 *		Listen and notifies changes in the number of messages in a folder. <p>
 * api2test: public addMessageCountListener(MessageCountListener)  <p>
 *
 * how2test: Call this API. Then verify that that the notification occur when changes
 *	     in the number of messages in a folder. If this happens then this test
 *	     passed otherwise it fails.
 */

public class addMsgCntList_Test extends MailTest implements MessageCountListener {

    boolean msgadded = false;
    boolean msgdeleted = false;

    // how many times to check for an event, waiting 1 second each time
    private static final int TRIES = 20;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public void messagesAdded(MessageCountEvent e)
    {
	out.fine("\nMessages added successfully!");
	msgadded = true;
    }

    public void messagesRemoved(MessageCountEvent e)
    {
        out.fine("\nMessages removed successfully!");
        msgdeleted = true;
    }

    public Status run()
    {
	

        out.fine("\nTesting class FolderEvent: addMessageCountListener(MessageCountListener)");

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

          // Get a Folder object
	     Folder testfolder = root.getFolder(testbox);

	     if( testfolder == null ) {
                 return Status.failed("Invalid folder object!");
             }

	     if(testfolder.exists())
	         testfolder.delete(false);
	     testfolder.create(Folder.HOLDS_MESSAGES);

	     testfolder.open(Folder.READ_WRITE);

	  // BEGIN UNIT TEST:
             out.fine("UNIT TEST 1: addMessageCountListener(MessageCountListener)\n");

          // Add messageCountListener to listen for new messages
             testfolder.addMessageCountListener(this);	// API TEST

	     int msgcount = testfolder.getMessageCount();
	     Message[] msgs = folder.getMessages();
	     if ( msgs == null || msgs.length == 0 )
		return Status.failed("Warning: failed to get messages from: " +
			mailbox);

	     testfolder.appendMessages(msgs);

	     // give them time to arrive
	     int tries;
	     for (tries = 0; tries < TRIES; tries++) {
		if (testfolder.getMessageCount() > msgcount)
		    break;
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ex) { }
	     }
	     if (tries == TRIES)
		return Status.failed(
			"Warning: failed to find new messages in: " +
			testbox);

	     // update msgcount to the expected number of messages
	     // (they may not have all arrived yet)
	     msgcount += msgs.length;

	     Message msg = testfolder.getMessage(1);
	     msg.setFlag(Flags.Flag.DELETED, true);
	     testfolder.expunge();

	     // give it time to disappear
	     for (tries = 0; tries < TRIES; tries++) {
		if (testfolder.getMessageCount() < msgcount)
		    break;
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ex) { }
	     }
	     if (tries == TRIES)
		return Status.failed("Warning: failed to delete message in: " +
			testbox);

	     // give event delivery thread time to run
	     for (tries = 0; tries < TRIES; tries++) {
		if (msgadded && msgdeleted) {
		    out.fine("UNIT TEST 1:  passed\n");
		    break;
		}
		try {
		    Thread.sleep(100);
		} catch (InterruptedException ex) { }
	     }
	     if (tries == TRIES) {
		   out.fine("Failed to invoke MessageCountListener events!");
		   out.fine("UNIT TEST 1:  FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST:
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
