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

package javasoft.sqe.tests.jakarta.mail.internet.NewsAddress;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getType()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return the type of address. <p>
 * api2test: public String getType()  <p>
 *
 * how2test: Call API, verify that it returns a string object
 *	     If so then testcase passes, otherwise it fails.
 */

public class getType_Test extends MailTest {

    public static void main( String argv[] )
    {
        getType_Test test = new getType_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class NewsAddress: getType()\n");

        try {
	   // Construct a NewsAddress with the given newsgroup
	      NewsAddress na = new NewsAddress(pattern, host);

	      if( na == null ) {
		  return Status.failed("Failed to create "+pattern+" newsgroup on host "+host);
	      }
	   // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1:  getType()");
	      String ngtype = na.getType(); 	// API TEST

              if( ngtype != null ) {
		  out.println("Type of address is "+ ngtype);
                  out.println("UNIT TEST 1: passed");
              } else {
		      out.println("Warning: Type name is null!");
                      out.println("UNIT TEST 1: FAILED");
                      errors++;
              }
           // END UNIT TEST 1:
              checkStatus();

        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
