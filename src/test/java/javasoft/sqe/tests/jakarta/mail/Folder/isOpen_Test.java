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
 * This class tests the <strong>isOpen()</strong> API.
 * It does this by invoking the api under test and then checking
 * the value of the returned object.	<p>
 *
 *		Indicates whether this Folder is in the 'open' state  <p>
 * api2test: public boolean isOpen()  <p>
 *
 * how2test: Call this API on an open or closed folder. If it returns a value <p>
 *	     of TRUE then the folder is open and closed otherwise. In either <p>
 *	     case the testcase is considered passing. Test this against closed
 *	     and open folder.
 */

public class isOpen_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: isOpen()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
	         return Status.failed("Invalid folder object!");
       	     }
	  // BEGIN UNIT TEST 1:
	     // test with closed folder
             out.fine("UNIT TEST 1: isOpen();");

             boolean isOpen = folder.isOpen(); 	// API TEST
	     
             if( !( isOpen ) ) {
		 out.fine("This folder is not open.");
                 out.fine("UNIT TEST 1: passed\n");
             }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
	     // test with open folder

	     folder.open(Folder.READ_ONLY);
             out.fine("UNIT TEST 2: isOpen();");

             isOpen = folder.isOpen();       // API TEST

             if( isOpen ) {
                 out.fine("This folder is open.");
                 out.fine("UNIT TEST 2: passed\n");
             }
          // END UNIT TEST 2:
	     folder.close(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
