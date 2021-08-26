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
 * This class tests the <strong>getName()</strong> API.
 * It does this by invoking the api under test and then checking
 * the type of the returned object.	<p>
 *
 *		Returns the name of this Folder
 * api2test: public String getName()  <p>
 *
 * how2test: Call this API. Verify that it returns a string. <p>
 *	     This method can be invoked on a open/closed Folder.
 */

public class getName_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: getName()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if (folder == null ) {
	         return Status.failed("Invalid folder object!");
       	     }
	     folder.open(Folder.READ_ONLY);
	     
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1: getName()");

	     String folderName1 = folder.getName();	// API TEST
	     folder.close(false);
	     String folderName2 = folder.getName();	// API TEST
	     
	     if(folderName1 != null && folderName2 != null ) {
		if( folderName1.equals(folderName2) ) {
		    out.fine("Folder name is: "+ folderName1);
		    out.fine("UNIT TEST 1: passed\n");
		} else {
			out.fine("UNIT TEST 1: FAILED\n");
			errors++;
		}
	     }
	  // END UNIT TEST 1:
	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
