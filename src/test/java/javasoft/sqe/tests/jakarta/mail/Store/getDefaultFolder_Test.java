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

package javasoft.sqe.tests.jakarta.mail.Store;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getDefaultFolder()</strong> API.
 * Returns a Folder object that represents the 'root' of the default namespace
 * presented to the user by the Store. Check they type of returned object. <p>
 *
 *		Returns a Folder object of the default namespace.   <p>
 * api2test: public Folder getDefaultFolder() throws MessagingException <p>
 *
 * how2test: Test with various kinds of Store objects. Call the API, check <p>
 *	     type of returned object, if it is Folder then the test passes, <p>
 *	     otherwsie it fails.
 */

public class getDefaultFolder_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Store: getDefaultFolder()\n");

        try {
          // Connect to host server
             Store store_1 = this.connect2host(protocol, host, user, password);

          // BEGIN UNIT TEST 1:
             out.fine("UNIT TEST 1: getDefaultFolder()");

          // Get a default Folder for this namespace
             Folder folder_1 = store_1.getDefaultFolder();      // API TEST

             if ( folder_1 == null ) {
		  out.fine("UNIT TEST 1:  FAILED\n");
                  return Status.failed("Invalid folder object!");
             } else if ( folder_1 != null && ( folder_1 instanceof Folder ))
                        out.fine("UNIT TEST 1:  passed\n");

             store_1.close();
          // END UNIT TEST 1:

	  // BEGIN UNIT TEST 2:
             // Get a Session object
             Session session_2 = Session.getInstance(properties, null);

             // Get store object
             Store store_2 = session_2.getStore(protocol);

             out.fine("UNIT TEST 2: getDefaultFolder()");

             // Connect to host
	     if( host != null || user != null || password != null)
		 if (portnum > 0)
		     store_2.connect(host, portnum, user, password);
		 else
		     store_2.connect(host, user, password);
	     else
		 store_2.connect();

             // Get a default Folder
             Folder folder_2 = store_2.getDefaultFolder();	// API TEST

             if( folder_2 == null ) {
		 out.fine("UNIT TEST 2:  FAILED\n");
		 return Status.failed("Invalid folder object!");
             } else if( folder_2 != null && ( folder_2 instanceof Folder ))
                  	out.fine("UNIT TEST 2:  passed\n");

             store_2.close();
	  // END UNIT TEST 2:
             status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
