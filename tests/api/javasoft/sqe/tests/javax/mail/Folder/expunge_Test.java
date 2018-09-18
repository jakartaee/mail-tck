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
 * This class tests the <strong>expunge()</strong> API.
 * It does this by invoking the test api and then checking the values
 * of the returned Message array object.  <p>
 *
 *		Expunge (permanently remove) messages marked DELETED. <p>
 * api2test: public Message[] expunge()  <p>
 *
 * how2test: Call this API, check that it returns a message array. Verify that <p>
 *	     this array contains the expunged message objects by getting their <p>
 *	     message numbers. If after this operation the test folder contains <p>
 *	     no messages, then this testcase passes otherwise it fails.
 */

public class expunge_Test extends MailTest {

    public static void main( String argv[] )
    {
        expunge_Test test = new expunge_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Folder: expunge()\n");

        try {
            // Connect to host server
             Store store = this.connect2host(protocol, host, user, password);

            // Get a folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

	     if( folder == null ) {
	         return Status.failed("Invalid folder object!");
	     }
             // Get a Folder object from the same Session
             Folder testfolder = root.getFolder(testbox);

             if( testfolder == null ) {
                 return Status.failed("Invalid test folder");
             }
	     if(!(testfolder.create(Folder.HOLDS_MESSAGES))) {
		return Status.failed("Warning: failed to create a testfolder: "+ testbox);
	     }
	     folder.open(Folder.READ_WRITE);
	     testfolder.open(Folder.READ_WRITE);
	     
	     // copy messages to test folder
	     Message[] msgcopy = folder.getMessages();
	     folder.copyMessages(msgcopy, testfolder);

             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     folder.close(false);

	     // Mark messages in testfolder as deleted
             for( int j = 1; j <= msgcount; j++) {
                  Message msg = testfolder.getMessage(j);
                  if ( msg != null )
                       msg.setFlag(Flags.Flag.DELETED, true);   // expunging message
             }
	     out.println("Before expunge messages = "+ testfolder.getMessageCount());

	  // BEGIN UNIT TEST 1:
             out.println("UNIT TEST 1:  expunge()");

	     boolean wasnotExpunged = false;
	     Message[] msglist = testfolder.expunge();	// API TEST

	     for(int i = 0; i < msglist.length; i++) {
		 if (! msglist[i].isExpunged() ) {
		       wasnotExpunged = true;
		       break;
		 }
	     }
	     int newTotal = testfolder.getMessageCount();
	     out.println("After expunge messages = " + newTotal);

             if ( newTotal == 0 && ! wasnotExpunged )
                  out.println("UNIT TEST 1: passed\n");
             else {
                   out.println("UNIT TEST 1: FAILED\n");
                   errors++;
             }
	  // END UNIT TEST:
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
