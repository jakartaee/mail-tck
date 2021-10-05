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

package javasoft.sqe.tests.jakarta.mail.event.ConnectionEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.event.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addConnectionListener()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type/value of the returned object.	<p>
 *
 *              Listen and notifies of open/close events to Folder/Store/Transport. <p>
 * api2test: public addConnectionListener(ConnectionListener)  <p>
 *
 * how2test: Call this API. Then verify that that the notification occur when changes
 *           occur to a folder/store/transport. If this happens then this test passed
 *           otherwise it fails.
 */

public class addConnectionListener_Test extends MailTest implements ConnectionListener {

    boolean return_code;
    volatile boolean opened = false;
    volatile boolean closed = false;
    volatile boolean disconnected = false;

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public void opened(ConnectionEvent e)
    {
	out.fine("\nFolder/Store/Transport opened successfully!");
	opened = true;
    }

    public void closed(ConnectionEvent e)
    {
        out.fine("\nFolder/Store/Transport closed successfully!");
        closed = true;
    }

    public void disconnected(ConnectionEvent e)
    {
        out.fine("\nStore/Transport disconnected successfully!");
        disconnected = true;
    }

    public Status run()
    {
	

        out.fine("\nTesting class ConnectionEvent: addConnectionListener(ConnectionListener)");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
	     Folder folder = root.getFolder(testbox);

	     if ( folder == null ) {
                  return Status.failed("Invalid folder object!");
             }
          // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1: addConnectionListener(ConnectionListener)\n");
	     folder.addConnectionListener(this);

	     if( folder.exists() ) {
		 out.fine("Deleting existing folder: " + folder);
		 folder.delete(false);
	     }

	     return_code = folder.create(Folder.HOLDS_MESSAGES);	// API TEST
	     if( return_code ) {
		 out.fine("Open folder: " + folder);
		 folder.open(Folder.READ_ONLY);
		 out.fine("Close folder: " + folder);
		 folder.close(false);
		 out.fine("Delete folder: " + folder);
		 folder.delete(false);
	     } else
		 out.fine("Failed to create folder: " + folder);

	     // added so that notification has time to update
	     for (int tries = 0; tries < 10; tries++) {
		 if (opened && closed)
		    break;
		 Thread.sleep(50);
	     }

	     if( opened && closed )
		 out.fine("UNIT TEST 1:  passed\n");
	     else {
		   if (!debug) {
		       out.fine("Retry with debugging");
		       debug = true;
		       return run();
		   } else {
		   out.fine("Failed to invoke ConnectionListener events!");
		   out.fine("UNIT TEST 1:  FAILED, opened " + opened +
				", closed " + closed + "\n");
		   errors++;
		   }
	     }
	  // END UNIT TEST:

	     folder.removeConnectionListener(this);
	     store.close();
	     checkStatus();
        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }

     public void dotest(Folder folder) throws Exception {
     }
}
