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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getSize()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return the size of this part in bytes. <p>
 * api2test: public int getSize()  <p>
 *
 * how2test: Call this API with given message object, verify that it returns a integer
 *	     that is size of message in bytes, write this to stdio. If this operation
 *	     is successfull then this testcase passes otherwise it fails.
 */

public class getSize_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: getSize()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
                  return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     int sizeBytes;

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
		// Get size of this message
	        out.fine("UNIT TEST "+ i +":  getSize()");

	        sizeBytes = msg.getSize();	// API TEST

	        if ( sizeBytes > 0 ) {
		     out.fine("Message size is = "+ sizeBytes);
                     out.fine("UNIT TEST "+ i +":  passed\n");
	        } else {
		        out.fine("UNIT TEST "+ i +":  FAILED\n");
		        errors++;
	        }
	     // END UNIT TEST:
	     }
	     folder.close(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
