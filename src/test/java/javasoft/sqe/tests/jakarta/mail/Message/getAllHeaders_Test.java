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
 * This class tests the <strong>getAllHeaders()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	     Return all the headers from this part as an Enumeration of Header objects. <p>
 * api2test: public Enumeration getAllHeaders()  <p>
 *
 * how2test: Call this API, then enumerate all headers fetched by this method, if <p>
 *	     this operation is successfull the testcase passes, otherwise it fails.
 */

public class getAllHeaders_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: getAllHeaders()\n");

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

             for(int i = 1; i <= msgcount; i++)
             {
            // Get the message

               Message msg = folder.getMessage(i);
	       if ( msg == null ) {
		   return Status.failed("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
	       }
	    // BEGIN UNIT TEST:
		// Get all headers for given message(s)
	       out.fine("UNIT TEST "+ i +":  getAllHeaders()");

	       Enumeration allheaders = msg.getAllHeaders();	// API TEST

	       while ( allheaders.hasMoreElements() ) {
		     Header headers = (Header)allheaders.nextElement();
		     out.fine(headers.getName());
	       }
               out.fine("UNIT TEST "+ i +":  passed\n");
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
