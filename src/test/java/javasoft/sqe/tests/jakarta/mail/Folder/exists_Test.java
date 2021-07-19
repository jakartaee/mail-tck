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
 * This class tests the <strong>exists()</strong> API.
 * It does this by invoking the test api on a Store and then checking the
 * value of the returned object.	<p>
 *
 *		Tests if this folder physically exists on the Store. <p>
 * api2test: public boolean exists()  <p>
 *
 * how2test: Call this API, check the value of object returned. If it is <p>
 *	     TRUE the test passes, otherwise it fails. <p>
 *
 *	  a) This method can be invoked on a closed folder. <p>
 *	  b) Try this on differnt store objects.  <p>
 *	  c) Can it be invoked on open folder (?).
 */

public class exists_Test extends MailTest {

    static boolean return_code;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class Folder: exists()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
	     Folder folder = root.getFolder(testbox);

	     if ( folder == null ) {
                  return Status.failed("Invalid folder object!");
             }
	  // BEGIN UNIT TEST 1:
	     // invoke on open (existing) folder

	     out.println("UNIT TEST 1: exists()\n");

	     if ( folder.create(Folder.HOLDS_MESSAGES) ) {
		  folder.open(Folder.READ_ONLY);
		  return_code = folder.exists();	// API TEST

                  if ( return_code )
                       out.println("UNIT TEST 11: passed\n");
                  else {
                        out.println("Failed to find existing open folder " + testbox);
                        out.println("UNIT TEST 1: FAILED\n");
                        errors++;
                  }
             }
          // END OF UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             // invoke on closed (existing) folder

             out.println("UNIT TEST 2: exists()\n");

             if ( folder.isOpen() ) {
		  folder.close(true);      // close test folder
                  return_code = folder.exists();      // API TEST

                  if ( return_code ) {
                       out.println("UNIT TEST 2: passed\n");
                       folder.delete(false);
                  } else {
                        out.println("Failed to find existing closed folder " + testbox);
                        out.println("UNIT TEST 2: FAILED\n");
                        errors++;
                  }
             }
          // END OF UNIT TEST 2:

	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
