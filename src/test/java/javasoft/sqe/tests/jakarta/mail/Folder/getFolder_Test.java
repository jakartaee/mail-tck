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
 * This class tests the <strong>getFolder(String)</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return the Folder object corresponding to the given name <p>
 * api2test: public Folder getFolder(String name) throws MessagingException <p>
 *
 * how2test: Call this API with various parameters. Verify that the type of the <p>
 *	     object returned if its Folder, then the testcase passes, otherwise <p>
 *	     it fails. <p>
 *
 *	  a) Note that this folder does not physically have to exist in the Store. <p>
 *           [ The exists() method on a Folder indicates whether it really exists on
 *	       the Store. ] <p>
 *
 *	  b) name can be an absolute path if it starts with the hierarchy delimiter. <p>
 *	     Otherwise, it is interpreted relative to this Folder. <p>
 *
 *	  c) Invoking this method on the same name multiple times will return that <p>
 *	     many distinct Folder objects.  <p>
 *
 *	  d) This method can be invoked on a closed Folder.
 */

public class getFolder_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: getFolder(String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

	  // BEGIN UNIT TEST 1:
	     // Invoked on closed an existing mail folder
	     out.fine("UNIT TEST 1: getFolder(" + mailbox + ")");

             // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder_1 = root.getFolder(mailbox);	// API TEST

             if ( folder_1 != null )
	         out.fine("UNIT TEST 1: passed\n");
             else {
	             out.fine("Invalid folder name");
                     out.fine("UNIT TEST 1: FAILED\n");
                     errors++;
       	     }
	     folder_1.open(Folder.READ_ONLY);
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
	     // Invoked on an already open mail folder
             out.fine("UNIT TEST 2: getFolder(" + mailbox + ")");

             // Get another Folder object related the same mail folder
             Folder folder_2 = root.getFolder(mailbox);	// API TEST

             if ( folder_2 != null )
                 out.fine("UNIT TEST 2: passed\n");
             else {
                   out.fine("Unable to get a second folder object for same folder name");
                   out.fine("UNIT TEST 2: FAILED\n");
                   errors++;
             }
	     folder_1.close(false);
          // END UNIT TEST 2:
	  // BEGIN UNIT TEST 3:
	     // Invoke on "relative" to this folder test name

	     String testbox = "testerFolder";
             out.fine("UNIT TEST 3: getFolder(" + testbox + ")");

          // Get a Folder object
             Folder folder_3 = root.getFolder(testbox);	// API TEST

	     if ( folder_3 != null ) {
		  if ( folder_3.create(Folder.HOLDS_MESSAGES) ) {
		       if ( folder_3.exists() ) {
			    folder_3.delete(false);
			    out.fine("UNIT TEST 3: passed\n");
		       } else {
				out.fine("Specified folder name does not exist!");
				out.fine("UNIT TEST 3: FAILED\n");
				errors++;
			}
		  } else {
			  out.fine("Failed to create specified folder.");
			  out.fine("UNIT TEST 3: FAILED\n");
			  errors++;
		    }
	     } else {
			out.fine("Failed to get specified folder.");
			out.fine("UNIT TEST 3: FAILED\n");
			errors++;
	     }
	  // END UNIT TEST 3:
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
