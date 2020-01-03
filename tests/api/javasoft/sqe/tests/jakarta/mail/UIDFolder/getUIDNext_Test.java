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

package javasoft.sqe.tests.jakarta.mail.UIDFolder;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getUIDNext()</strong> API. <p>
 *
 * api2test: public void getUIDNext()  <p>
 *
 * how2test: Test this API by getting the UIDNEXT value for a folder and
 *	     ensuring that it increases as messages are appended to the folder.
 */

public class getUIDNext_Test extends MailTest {

    public static void main( String argv[] )
    {
        getUIDNext_Test test = new getUIDNext_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting interface UIDFolder: getUIDNext()\n");

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

	     UIDFolder uidtestfolder = (UIDFolder)testfolder;

          // BEGIN UNIT TEST 1:
	     // get UIDNEXT value

	     out.println("UNIT TEST 1: getUIDNext() empty folder");

	     long uidfirst = uidtestfolder.getUIDNext();
	     if (uidfirst == -1 || (uidfirst >= 0 && uidfirst <= 0xffffffffL))
	          out.println("UNIT TEST 1:  passed\n");
	     else {
		   out.println("UNIT TEST 1:  FAILED\n");
		   errors++;
	     }

          // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     // get UIDNEXT value after messages have been appended

	     out.println("UNIT TEST 2: getUIDNext() non-empty folder");

	     testfolder.appendMessages(msg);	    // API TEST

	     long uidnext = uidtestfolder.getUIDNext();
	     if (uidnext == -1 ||
		   (uidnext >= uidfirst + msg.length && uidnext <= 0xffffffffL))
	          out.println("UNIT TEST 2:  passed\n");
	     else {
		   out.println("UNIT TEST 2:  FAILED\n");
		   errors++;
	     }

          // END UNIT TEST 2:
	  // BEGIN UNIT TEST 3:
	     // get UIDNEXT value after opening folder

	     out.println("UNIT TEST 3: getUIDNext() open folder");

	     testfolder.open(Folder.READ_WRITE);
	     long uidcur = uidtestfolder.getUIDNext();
	     if (uidcur == -1 || (uidcur >= uidnext && uidcur <= 0xffffffffL))
	          out.println("UNIT TEST 3:  passed\n");
	     else {
		   out.println("UNIT TEST 3:  FAILED\n");
		   errors++;
	     }
	     testfolder.close(false);

	  // END UNIT TEST 3:

             folder.close(false);
             testfolder.delete(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
