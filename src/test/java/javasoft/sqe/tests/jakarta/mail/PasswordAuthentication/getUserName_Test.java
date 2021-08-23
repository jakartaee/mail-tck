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

package javasoft.sqe.tests.jakarta.mail.PasswordAuthentication;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getUserName()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		returns user name. <p>
 * api2test: public String getUserName()  <p>
 *
 * how2test: Call this API, then verify the value returned with original username.
 *	     If they are same then this testcase passes, otherwise it fails.
 */

public class getUserName_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class PasswordAuthentication: getUserName()\n");

        try {
	   // Initialize a PasswordAuthentication object
	      PasswordAuthentication pwa = new PasswordAuthentication(user, password);

	      if( pwa == null )
		  return Status.failed("Failed to create a PasswordAuthentication object!");

	   // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1:  getUserName()");

	      String userName = pwa.getUserName();	// API TEST

	      if( (user != null && userName != null && user.equals(userName)) ||
		    (user == null && userName == null))
		  out.fine("UNIT TEST 1: passed.\n");
	      else {
		    out.fine("UNIT TEST 1: FAILED.\n");
		    errors++;
	      }
	   // END UNIT TEST:

              checkStatus();

        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
