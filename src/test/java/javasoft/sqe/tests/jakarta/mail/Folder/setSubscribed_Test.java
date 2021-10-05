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
 * This class tests the <strong>setSubscribed(boolean)</strong> API.
 * It does this by passing various valid input values and then checking
 * by invoking isSubscribed() api and checking the returned value.  <p>
 *
 *		Subscribe or unsubscribe this Folder  <p>
 * api2test: public void setSubscribed(boolean subscribe)  <p>
 *
 * how2test: Call this API with either true|false values. Then call 'isSubscribed' <p>
 *	     to check that former api call did its job successfully. If so then
 *	     this testcase passes, otherwise it fails. <p>
 *
 *	  a) Not all Stores support subscription.  <p>
 *	  b) This method can be invoked on a closed Folder.
 */

public class setSubscribed_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: setSubscribed(boolean)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if (folder == null ) {
	         return Status.failed("Invalid folder object!");
       	     }
	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1: setSubscribed(true|false)");

	     try {
		 folder.setSubscribed(false);	// API TEST
		 folder.setSubscribed(true);	// API TEST

		 boolean isSubscribe = folder.isSubscribed();

		 if ( isSubscribe )
		      out.fine("UNIT TEST 1: passed\n");
		 else {
			out.fine("UNIT TEST 1: FAILED\n");
			errors++;
		 }
	     } catch (MethodNotSupportedException ex) {
		 out.fine("UNIT TEST 1: passed (setSubscribed not supported)\n");
	     }
	  // END UNIT TEST:
	     //	     folder.close(false);
	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
