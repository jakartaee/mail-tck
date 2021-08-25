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
 * This class tests the <strong>getMessage(int)</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Get the Message object corresponding to the given message number <p>
 * api2test: public getMessage(int param) throws MessageException <p>
 *
 * how2test: Call this API with int message id. Check that it returns an object <p>
 *	     of type Message. If so then the testcase passes otherwise it fails. <p>
 *
 *        a) Test fetching the Message object corresponding to the given message
 *	     number with the specified range 1...N  <p>
 *
 *	  b) Repeated calls to getMessage with the same message number will return
 *	     the same Message object. Check for this. <p>
 *
 *	  c) Test fetching a message that has been marked for deletion. <p>
 *        d) Test for 0 and N+1 message numbers. It should throw an exception! <p>
 *	     // This will throw an invalid message number exception, so we are
 *	     // skipping this testcase.
 */

public class getMessage_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: getMessage(int)\n");

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

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	  // BEGIN UNIT TEST:

	     Message msg, cmsg;
	     int size_in_bytes1;
	     int size_in_bytes2;

	     for( int i = 1; i <= msgcount; i++ )
	     {
	          out.fine("UNIT TEST " + i + ": getMessage(" + i + ")");

		   msg = folder.getMessage(i);		// API TEST
		  cmsg = folder.getMessage(i);		// API TEST

		  if( msg != null && ( msg instanceof Message ) ) {
		      size_in_bytes1 = msg.getSize();
		      size_in_bytes2 = cmsg.getSize();
		      if ( size_in_bytes1 == size_in_bytes2 )
                           out.fine("UNIT TEST " + i + ": passed\n");
                  } else {
                          out.fine("UNIT TEST " + i + ": FAILED\n");
                          errors++;
		  }
             }
             out.fine("\n");
	  // END UNIT TEST:

	     folder.close(false);
	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
