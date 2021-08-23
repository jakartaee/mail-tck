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
 * This class tests the <strong>getType()</strong> API.
 * It does this by invoking the api being tested and then checking
 * the type of the returned object.	<p>
 *
 *		Returns the type of this Folder
 * api2test: public int getType()  <p>
 *
 * how2test: Call this API and check that it returns an object of type <p>
 *           'int'. If it does the testcase passes, otherwise it fails. <p>
 *           Invoke this method on a closed/open folder.
 */

public class getType_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: getType()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder_1 = root.getFolder(mailbox);

             if (folder_1 == null ) {
	         return Status.failed("Invalid folder object!");
       	     }
	     folder_1.open(Folder.READ_ONLY);
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1: getType()");

	     int foldertype = folder_1.getType();	// API TEST

	     switch (foldertype) {
		case Folder.HOLDS_FOLDERS:
		    out.fine("Folder type is: HOLDS_FOLDERS");
		    out.fine("UNIT TEST 1: FAILED\n");
		    errors++;
		    break;
		case Folder.HOLDS_MESSAGES:
		    out.fine("Folder type is: HOLDS_MESSAGES");
		    out.fine("UNIT TEST 1: passed\n");
		case Folder.HOLDS_FOLDERS|Folder.HOLDS_MESSAGES:
		    out.fine("Folder type is: HOLDS_FOLDERS|HOLDS_MESSAGES");
		    out.fine("UNIT TEST 1: passed\n");
		    break;
		default:
		    out.fine("Warning unknown folder type");
		    out.fine("UNIT TEST 1: FAILED\n");
		    errors++;
	     }
	     folder_1.close(false);
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:

	     // Get another folder object from the same Session/Store
	     Folder folder_2 = root.getFolder(testbox);

             if ( folder_2 == null ) {
                  return Status.failed("Invalid test folder object!");
             }
	     if ( folder_2.exists() ) {
		  folder_2.delete(false);
	     }
             out.fine("UNIT TEST 2: getType()");

	     if ( folder_2.create(Folder.HOLDS_FOLDERS) )
		  foldertype = folder_2.getType();       // API TEST
	     else
		  return Status.failed("Failed to create test folder!");

	     switch (foldertype) {
		case Folder.HOLDS_FOLDERS:
		    out.fine("Folder type is: HOLDS_FOLDERS");
		    out.fine("UNIT TEST 2: passed\n");
		    break;
		case Folder.HOLDS_MESSAGES:
		    out.fine("Folder type is: HOLDS_MESSAGES");
		    out.fine("UNIT TEST 2: FAILED\n");
		    errors++;
		    break;
		case Folder.HOLDS_FOLDERS|Folder.HOLDS_MESSAGES:
		    out.fine("Folder type is: HOLDS_FOLDERS|HOLDS_MESSAGES");
		    out.fine("UNIT TEST 2: passed\n");
		    break;
		default:
		    out.fine("Warning unknown folder type");
		    out.fine("UNIT TEST 2: FAILED\n");
		    errors++;
	     }
          // END UNIT TEST 2:
	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
