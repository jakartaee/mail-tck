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
 * This class tests the <strong>getMessages()</strong> API.
 * It does this by invoking the api under test and then checking
 * the type of the returned objects.	<p>
 *
 *		Get all the Message objects for a given folder
 * api2test: public Message[] getMessages() <p>
 *
 * how2test: Call this API. Verify that it returns an array of messages. <p>
 *	     Test with different folders and check the type of object returned.
 */

public class getMessages_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class Folder: getMessages()\n");

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
	     int size_in_bytes;

	     Message[] msgs = folder.getMessages();	// API TEST

	     for (int i = 0; i < msgs.length; i++)
	     {
	          out.println("UNIT TEST "+ (i+1) +": getMessages("+ (i+1) +")");

		  if( msgs[i] != null && ( msgs[i] instanceof Message ) ) {
		      size_in_bytes = msgs[i].getSize();
		      if( size_in_bytes > 0 )
                          out.println("UNIT TEST "+ (i+1) +": passed\n");
                  } else {
                          out.println("UNIT TEST "+ (i+1) +": FAILED\n");
                          errors++;
		  }
             }
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
