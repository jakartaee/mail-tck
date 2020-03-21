/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
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
import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getPassword()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		returns user password. <p>
 * api2test: public String getPassword()  <p>
 *
 * how2test: Call this API, verify the value returned with original user password.
 *	     If they are same then this testcase passes, otherwise it fails.
 */

public class getPassword_Test extends MailTest {

    public static void main( String argv[] )
    {
        getPassword_Test test = new getPassword_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class PasswordAuthentication: getPassword()\n");

        try {
	   // Initialize a PasswordAuthentication object
	      PasswordAuthentication pwa = new PasswordAuthentication(user, password);

	      if( pwa == null )
		  return Status.failed("Failed to create a PasswordAuthentication object!");

	   // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1:  getPassword()");

	      String userPasswd = pwa.getPassword();	// API TEST

	      if( (password != null && userPasswd != null && password.equals(userPasswd)) ||
		    (password == null && userPasswd == null))
		  out.println("UNIT TEST 1: passed.\n");
	      else {
		    out.println("UNIT TEST 1: FAILED.\n");
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
