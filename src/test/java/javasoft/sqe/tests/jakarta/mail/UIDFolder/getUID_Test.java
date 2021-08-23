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

package javasoft.sqe.tests.jakarta.mail.UIDFolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getUID()</strong> and
 * <strong>getMessageByUID(long uid)</strong> APIs. <p>
 *
 * api2test: public void getUID()  <p>
 * api2test: public void getMessageByUID()  <p>
 *
 * how2test: Test this API by getting the UID for every message in the
 *	     test folder and verifying that getting the message for that
 *	     UID returns the original message.
 */

public class getUID_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting interface UIDFolder: getUID()\n");

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

	     if( msgcount == -1 ) {
		 msgcount = folder.getMessageCount();
		 if( msgcount < 1 )
		     return Status.failed("Mail folder is empty!");
	     }
             Message[] msg = folder.getMessages();
	     testfolder.appendMessages(msg);

	     UIDFolder uidtestfolder = (UIDFolder)testfolder;

          // BEGIN UNIT TEST 1:
	     // get UIDNEXT value

	     out.fine("UNIT TEST 1: getUID(), getMessageByUID()");

	     testfolder.open(Folder.READ_WRITE);
	     Message[] umsg = testfolder.getMessages();
	     boolean success = true;
	     for (int i = 0; i < umsg.length; i++) {
		 long uid = uidtestfolder.getUID(umsg[i]);
		 Message m = uidtestfolder.getMessageByUID(uid);
		 if (m != umsg[i]) {
		    success = false;
		    break;
		 }
	     }
	     if (success)
		  out.fine("UNIT TEST 1:  passed\n");
	     else {
		   out.fine("UNIT TEST 1:  FAILED\n");
		   errors++;
	     }

          // END UNIT TEST 1:

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
