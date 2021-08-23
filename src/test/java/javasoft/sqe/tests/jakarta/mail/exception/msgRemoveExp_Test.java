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
 * This class tests the <strong>MessageRemovedException()</strong> API.
 * It does this by invoking the test api and then checking the values
 * of the returned Message array object.  <p>
 *
 *		Constructor thats thrown when an invalid method is invoked on an expunged Message. <p>
 * api2test: public MessageRemovedException(void|String)  <p>
 *
 * how2test: Call getSubject() API on expunged message, if this results in MessageRemoved
 *	     exception being thrown then this testcase passes, otherwise it fails.
 */

public class msgRemoveExp_Test extends MailTest {

    private Folder testfolder;
    private Store  store;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MessageRemovedException: MessageRemovedException(void|String)\n");

        try {
            // Connect to host server
               store = connect2host(protocol, host, user, password);

            // Get a folder object
	       Folder root = getRootFolder(store);
               Folder folder = root.getFolder(mailbox);

	       if( folder == null ) {
	           return Status.failed("Invalid folder object!");
	       }

	    // Get a Folder object from the same Session
               testfolder = root.getFolder(testbox);

               if( testfolder == null ) {
                   return Status.failed("Invalid test folder object!");
               }

	       if( testfolder.exists() ) {
                   testfolder.delete(false);
	       }

	       if( !(testfolder.create(Folder.HOLDS_MESSAGES)) ) {
		    return Status.failed("Warning: failed to create a testfolder: "+ testbox);
	       }

	       folder.open(Folder.READ_WRITE);
	       if ( msgcount == -1 ) {
		    msgcount = folder.getMessageCount();
                    if( msgcount < 1 )
			return Status.failed("Mail folder is empty!");
	       }
	       Message[] msgcopy = folder.getMessages();

	    // Copy messages to test folder
	       folder.copyMessages(msgcopy, testfolder);
               folder.close(false);

	       testfolder.open(Folder.READ_WRITE);
	       if (testfolder.getMessageCount() < msgcount) {
		    // give them time to arrive
		    int tries;
		    for (tries = 0; tries < 10; tries++) {
			if (testfolder.getMessageCount() >= msgcount)
			    break;
			try {
			    Thread.sleep(1000);
			} catch (InterruptedException ex) { }
		    }
		    if (tries == 10)
			return Status.failed("Warning: failed to copy messages to: "+ testbox);
	       }

	    // Mark messages in testfolder as deleted
               for( int j = 1; j <= msgcount; j++)
               {    // Get a message
                    Message msg = testfolder.getMessage(j);

                    if ( msg != null )
                         msg.setFlag(Flags.Flag.DELETED, true);   // expunging message
               }
	    // Expunge messages marked for deletion
	       Message[] msglist = testfolder.expunge();

	    // BEGIN UNIT TEST:
               if ( testfolder.getMessageCount() == 0 )
	       {
		    out.fine("UNIT TEST 1: MessageRemovedException()");
		    String badstr = msglist[1].getSubject();	    // API TEST

		    if( badstr != null )
                        out.fine("UNIT TEST 1: FAILED\n");
               }
            // END UNIT TEST:
	       status = Status.failed(" Failed to catch MessageRemovedException ");

        } catch ( MessageRemovedException me ) {
		out.fine("UNIT TEST 1: passed.\n");
		try {
		      testfolder.close(false);
		      testfolder.delete(false);
		      store.close();
		      ExceptionTest(me);
		} catch ( Exception e ) {
		      handlException(e);
		}
        } catch ( MessagingException mx ) {
		handlException(mx);
	}
	return status;
     }
}
