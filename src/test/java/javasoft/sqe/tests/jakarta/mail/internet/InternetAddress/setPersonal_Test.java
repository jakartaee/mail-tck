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

package javasoft.sqe.tests.jakarta.mail.internet.InternetAddress;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setPersonal()</strong> API.
 * It does by invoking the test API and then checking
 * the type of the returned object.	<p>
 *
 *		set the personal name <p>
 * api2test: public setPersonal(String) <p>
 *		set the personal name and character set <p>
 * api2test: public setPersonal(String, String) <p>
 *
 * how2test: Invoke the setPersonal() APIs, then call getPersonal() method, if it returns a
 *	     non-null object of type String, then the testcase passes, otherwise it fails.
 */

public class setPersonal_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class InternetAddress: setPersonal(String|String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
	  // BEGIN UNIT TEST 1:
	     out.fine("\nUNIT TEST 1:  setPersonal(String)");
	     InternetAddress addr1 = new InternetAddress();

	     addr1.setPersonal("the quick fox jumped over the lazy cow.");   // API TEST
	     String personal1 = addr1.getPersonal();

	     if( personal1 != null ) {
		 out.fine(personal1);
                 out.fine("UNIT TEST 1: passed\n");
	     } else {
		     out.fine("UNIT TEST 1: FAILED\n");
		     errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.fine("\nUNIT TEST 2:  setPersonal(String, String)");
	     InternetAddress addr2 = new InternetAddress();

	     addr2.setPersonal("hotmail ~!@,#$%^&*.+=,<.>?", "US-ASCII");    // API TEST
	     String personal2 = addr2.getPersonal();
	     
             if( personal2 != null ) {
		 out.fine(personal2);
                 out.fine("UNIT TEST 2: passed\n");
	     } else {
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
