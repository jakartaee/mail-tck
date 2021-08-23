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
 * This class tests the <strong>connect(...)</strong> API.
 * It does this by passing various valid input values or no input values. <p>
 *
 *		A generic connect method that takes no parameters. <p>
 * api2test: public void connect()  <p>
 *
 *		Connect to the specified address.  <p>
 * api2test: public void connect(String host, String user, String password) <p>
 * api3test: public void connect(String user, String password) <p>
 *
 * how2test: Call the 'connect(..)' API with various valid strings values. If this <p>
 *	     operation is successfull then the test passes otherwise it fails.
 */

public class connect_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Store: connect(String,String,String)\n");

        try {
          // Get a Session object
             Session session_1 = Session.getInstance(properties, null);

          // Get store object
             Store store_1 = session_1.getStore(protocol);

             if( store_1 == null ) {
                 return Status.failed("Invalid/Null Store object!");
             }
          // BEGIN UNIT TEST 1:
             out.fine("UNIT TEST 1: connect(null, null, "+ password +")");

          // Connect
             store_1.connect(null, null, password);		// API TEST

          // Get a Folder object
	     Folder root = getRootFolder(store_1);
             Folder folder_1 = root.getFolder(mailbox);

             if ( folder_1 == null ) {
                  return Status.failed("Invalid/Null folder object!");
             } else
		  out.fine("UNIT TEST 1:  passed\n");

             store_1.close();

	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:

	  // Get a Session object
             Session session_2 = Session.getInstance(properties, null);

             if( session_2 == null ) {
                 return Status.failed("Invalid/Null Session object!");
             }
          // Get store object
             Store store_2 = session_2.getStore(protocol);

             if( store_2 == null ) {
                 return Status.failed("Invalid/Null Store object!");
             }
             out.fine("UNIT TEST 2: connect("+ host +", "+ user +", "+ password +")");

          // Connect
             store_2.connect(host, user, password);		// API TEST

          // Get a Folder object
	     root = getRootFolder(store_2);
             Folder folder_2 = root.getFolder(mailbox);

             if( folder_2 == null )
                 return Status.failed("Invalid folder object!");
             else
		 out.fine("UNIT TEST 2:  passed\n");

	     store_2.close();
          // END UNIT TEST 2:

          // BEGIN UNIT TEST 3:

             Session session_3 = Session.getInstance(properties, null);
             
          // Get store object
             Store store_3 = session_3.getStore(protocol);

             if( store_3 == null ) {
                 return Status.failed("Invalid/Null Store object!");
             }
             out.fine("UNIT TEST 3: connect("+ user +", "+ password +")");

          // Connect
             
             store_3.connect(user, password);		// API TEST

          // Get a Folder object
             root = getRootFolder(store_3);
             Folder folder_3 = root.getFolder(mailbox);

             if (folder_3 == null)
                 return Status.failed("Invalid folder object!");
             else
		 out.fine("UNIT TEST 3:  passed\n");

	     store_3.close();
          // END UNIT TEST 3:             
             status = Status.passed("OKAY");

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
}
