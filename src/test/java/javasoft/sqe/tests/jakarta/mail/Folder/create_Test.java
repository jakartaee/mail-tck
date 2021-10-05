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
 * This class tests the <strong>create()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type/value of the returned object.	<p>
 *
 *		Create this folder on the Store.	<p>
 * api2test: public boolean create(int)  <p>
 *
 * how2test: Call this API. Chech the status of returned value. If its <p>
 *	     TRUE then test passed otherwise it fails.
 */

public class create_Test extends MailTest {

    boolean return_code;

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: create(int)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
	     Folder f1 = root.getFolder(testbox);

	     if ( f1 == null ) {
                  return Status.failed("Invalid folder object!");
             }
          // BEGIN UNIT TEST 1:

	     out.fine("UNIT TEST 1: create(Folder.HOLDS_MESSAGES)\n");

	     if ( !(f1.exists()) )
	     {
		  return_code = f1.create(Folder.HOLDS_MESSAGES);	// API TEST

		  if ( return_code ) {
		       out.fine("UNIT TEST 1: passed\n");
		       f1.delete(false);
		  } else {
			out.fine("Failed to create folder " + testbox);
			out.fine("UNIT TEST 1: FAILED\n");
			errors++;
		  }
	     }
	  // END OF UNIT TEST 1:
	  // BEGIN UNIT TEST 2:

	     String testbox = "topdog";

	  // Get a Folder object
             Folder f2 = root.getFolder(testbox);

             if( f2 == null ) {
                 return Status.failed("Invalid test folder");
             }
	     out.fine("UNIT TEST 2: create(Folder.HOLDS_FOLDERS)\n");

	     boolean r2 = f2.create(Folder.HOLDS_FOLDERS);  // API TEST

	     if( !f2.exists() ) {
		 return Status.failed("Warning: Failed to create test folder "+ testbox);
	     }

          // Get a Folder object

	     testbox = "tomcat";
             Folder f3 = f2.getFolder(testbox);

             if( f3 == null ) {
                 return Status.failed("Invalid test folder");
             }
	     boolean r3 = f3.create(Folder.HOLDS_MESSAGES);	// API TEST

             if( f3.exists() ) {
                 out.fine("UNIT TEST 2: passed\n");
		 f2.delete(false);
             } else {
                     out.fine("Failed to create directory folder(s) " + testbox);
                     out.fine("UNIT TEST 2: FAILED\n");
                     errors++;
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
