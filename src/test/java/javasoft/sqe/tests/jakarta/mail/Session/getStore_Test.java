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

package javasoft.sqe.tests.jakarta.mail.Session;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getStore(..)</strong> API.
 * It does this by passing various valid input values and then checking
 * the returned object type. <p>
 *
 *		Get a Store object that implements this user's desired Store protcol. <p>
 * api2test: public Store getStore() <p>
 *
 *		Get an instance of the store specified by Provider. <p>
 * api2test: public Store getStore(Provider)  <p>
 *
 *		Get a Store object that implements the specified protocol. <p>
 * api2test: public Store getStore(String) <p>
 *
 *		Get a Store object for the given URLName. <p>
 * api2test: public Store getStore(URLName) <p>
 *
 * how2test: Call these APIs with { null|Provider|String|URLName } valid parameter values. <p>
 *	     Test passes if the API successfully returns a Store object, it fails <p>
 *	     if this method return a null object.
 */
 
public class getStore_Test extends MailTest {

    private Folder folder = null;
    private Store  store = null;

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Session: getStore({void|String|URL})\n");

        try {
	  // BEGIN UNIT TEST 1:
	     Session session = Session.getInstance(properties, null);

             out.fine("UNIT TEST 1: getStore()");
	     store = session.getStore();	// API TEST

             // Connect
             if( host != null || user != null || password != null)
		 if (portnum > 0)
		     store.connect(host, portnum, user, password);
		 else
		     store.connect(host, user, password);
             else
                 store.connect();

	     Folder root = getRootFolder(store);

             if( root != null ) {
                 folder = root.getFolder(mailbox);
                 out.fine("UNIT TEST 1:  passed\n");
             } else {
                     out.fine("UNIT TEST 1:  FAILED\n");
                     errors++;
             }
          // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             session = Session.getInstance(properties, null);
             out.fine("UNIT TEST 2: getStore(Provider)");

	     Provider prov = session.getProvider(protocol);
             Store store = session.getStore(prov);        // API TEST

             // Connect
             if( host != null || user != null || password != null)
		 if (portnum > 0)
		     store.connect(host, portnum, user, password);
		 else
		     store.connect(host, user, password);
             else
                 store.connect();

	     root = getRootFolder(store);
             if( root != null ) {
                 folder = root.getFolder(mailbox);
                 out.fine("UNIT TEST 2:  passed\n");
             } else {
                     out.fine("UNIT TEST 2:  FAILED\n");
                     errors++;
             }
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
	     session = Session.getInstance(properties, null);
             out.fine("UNIT TEST 3: getStore("+ protocol +")");
	     
	     store = session.getStore(protocol);        // API TEST

             // Connect
             if( host != null || user != null || password != null)
		 if (portnum > 0)
		     store.connect(host, portnum, user, password);
		 else
		     store.connect(host, user, password);
             else
                 store.connect();

	     root = getRootFolder(store);
             if( root != null ) {
                 folder = root.getFolder(mailbox);
                 out.fine("UNIT TEST 3:  passed\n");
             } else {
                     out.fine("UNIT TEST 3:  FAILED\n");
                     errors++;
             }
          // END UNIT TEST 3:
          // BEGIN UNIT TEST 4:
             session = Session.getInstance(properties, null);
             out.fine("UNIT TEST 4: getStore(URLName)");

	     URLName urlName = new URLName(protocol,host,portnum,mailbox,user,password);
             store = session.getStore(urlName);	// API TEST

             // Connect
             if( host != null || user != null || password != null)
		 if (portnum > 0)
		     store.connect(host, portnum, user, password);
		 else
		     store.connect(host, user, password);
             else
                 store.connect();

	     root = getRootFolder(store);
             if( root != null ) {
                 folder = root.getFolder(mailbox);
                 out.fine("UNIT TEST 4:  passed\n");
             } else {
                     out.fine("UNIT TEST 4:  FAILED\n");
                     errors++;
             }
          // END UNIT TEST 4:
	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
