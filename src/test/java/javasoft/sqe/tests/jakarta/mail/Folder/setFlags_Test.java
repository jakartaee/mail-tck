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
 * This class tests the <strong>setFlags(..)</strong> API.
 * It does this by passing various valid input values and then checking
 * by invoking getFlags() api and comparing values.  	<p>
 *
 *		Set the specified flags on the messages specified in the array. <p>
 * api2test: public void setFlags(Message msgs[], Flags flag, boolean value) <p>
 *
 * how2test: Call this API with various arguments. It then call getFlags to check
 *	     that specified value was set correctly. If so then this testcase passes,
 *	     otherwise it fails. <p>
 *
 *	  a) The specified Flags.objects must belong to this folder. <p>
 *	  b) An implementation must not abort the operation if a Flags.<p>
 *	     in the array turns out to be an expunged Flags.
 */

public class setFlags_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }
    // checks if setFlags API did its job successfully!
    
    public boolean testFlag(Message[] msg, Flags.Flag f)
    {
	try {
	     for( int i = 0; i < msg.length; i++ ) {
	    	  if (!msg[i].isSet(f))
		      return false;
	     }
	} catch ( Exception e ) {
		this.handlException(e);
	}
	return true;
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: setFlags(...)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object.
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if (folder == null ) {
	         return Status.failed("Invalid folder object!");
       	     }
          // Get another Folder object in same store/session
             Folder testfolder = root.getFolder(testbox);

             if (testfolder == null ) {
                 return Status.failed("Invalid test folder");
             }
             if ( !(testfolder.create(Folder.HOLDS_MESSAGES)) ) {
					out.fine("\nFailed to create test folder: " + testfolder.getFullName() + "\n");
                  return Status.failed("Failed to create test folder.");
             }
	     folder.open(Folder.READ_ONLY);
             testfolder.open(Folder.READ_WRITE);

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     // copy messages to test folder
	     Message[] msgs = folder.getMessages();
	     folder.copyMessages(msgs, testfolder);
	
	     if ( testfolder.getMessageCount() != msgcount ) {
		  return Status.failed("Failed to make copy messages to folder "+ testbox);
	     }
	     // get messages from test folder
	     Message[] tmsg = testfolder.getMessages();

	     // fill array with message numbers
	     int imsg[] = new int[msgcount];
	     for( int k = 0; k < msgcount; k++ )
		  imsg[k] = k+1;

          // BEGIN UNIT TEST:
             out.fine("UNIT TEST 1: setFlags(start, end, Flag.SEEN, true)");

	     Flags flag = new Flags(Flags.Flag.SEEN);
             testfolder.setFlags(1, msgcount, flag, true);	    // API TEST

	     if (testFlag(tmsg, Flags.Flag.SEEN))
		  out.fine("UNIT TEST 1: passed\n");
	     else {
		    out.fine("UNIT TEST 1: FAILED\n");
		    errors++;
	     }
	     out.fine("UNIT TEST 2: setFlags(Message[], Flag.ANSWERED, true)");

	     flag = new Flags(Flags.Flag.ANSWERED);
	     testfolder.setFlags(tmsg, flag, true);	// API TEST

             if ( testFlag(tmsg, Flags.Flag.ANSWERED) )
                  out.fine("UNIT TEST 2: passed\n");
             else {
                    out.fine("UNIT TEST 2: FAILED\n");
                    errors++;
             }
	     out.fine("UNIT TEST 3: setFlags(int[], Flag.DELETED, true)");

	     flag = new Flags(Flags.Flag.DELETED);
	     testfolder.setFlags(imsg, flag, true);	// API TEST

             if( testFlag(tmsg, Flags.Flag.DELETED) )
                 out.fine("UNIT TEST 3: passed\n");
             else {
                    out.fine("UNIT TEST 3: FAILED\n");
                    errors++;
             }
	     out.fine("UNIT TEST 4: setFlags(Message[], Flag.FLAGGED, true)");

	     flag = new Flags(Flags.Flag.FLAGGED);
	     testfolder.setFlags(tmsg, flag, true);	// API TEST

             if ( testFlag(tmsg, Flags.Flag.FLAGGED) )
                  out.fine("UNIT TEST 4: passed\n");
             else {
                    out.fine("UNIT TEST 4: FAILED\n");
                    errors++;
             }
	     out.fine("UNIT TEST 5: setFlags(int[], Flag.DRAFT, true)");

	     flag = new Flags(Flags.Flag.DRAFT);
	     testfolder.setFlags(imsg, flag, true);	// API TEST

             if( testFlag(tmsg, Flags.Flag.DRAFT) )
                 out.fine("UNIT TEST 5: passed\n");
             else {
                    out.fine("UNIT TEST 5: FAILED\n");
                    errors++;
             }
             out.fine("UNIT TEST 6: setFlags(start, end, Flag.SEEN, false)");

	     flag = new Flags(Flags.Flag.SEEN);
	     testfolder.setFlags(1, msgcount, flag, false);	// API TEST

             if( ! testFlag(tmsg, Flags.Flag.SEEN) )
                 out.fine("UNIT TEST 6: passed\n");
             else {
                    out.fine("UNIT TEST 6: FAILED\n");
                    errors++;
             }
             out.fine("UNIT TEST 7: setFlags(Message[], Flag.ANSWERED, false)");

	     flag = new Flags(Flags.Flag.ANSWERED);
	     testfolder.setFlags(tmsg, flag, false);	// API TEST

             if ( ! testFlag(tmsg, Flags.Flag.ANSWERED) )
                  out.fine("UNIT TEST 7: passed\n");
             else {
                    out.fine("UNIT TEST 7: FAILED\n");
                    errors++;
             }
             out.fine("UNIT TEST 8: setFlags(int[], Flag.DELETED, false)");

	     flag = new Flags(Flags.Flag.DELETED);
	     testfolder.setFlags(imsg, flag, false);	// API TEST

             if ( ! testFlag(tmsg, Flags.Flag.DELETED) )
                  out.fine("UNIT TEST 8: passed\n");
             else {
                   out.fine("UNIT TEST 8: FAILED\n");
                   errors++;
             }
          // END UNIT TEST:

	     testfolder.close(false);
	     testfolder.delete(false);
	     folder.close(false);
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
