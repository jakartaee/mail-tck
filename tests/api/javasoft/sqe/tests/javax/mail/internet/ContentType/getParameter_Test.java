/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.javax.mail.internet.ContentType;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>getParameter()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *		Return the specified parameter value. <p>
 * api2test: public String getParameter(String)  <p>
 *
 * how2test: Call API, verify that it return a string non-null object.
 *	     If so then testcase passes, otherwise it fails.
 */

public class getParameter_Test extends MailTest {

    public static void main( String argv[] )
    {
        getParameter_Test test = new getParameter_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	 super.run(argv, log, out);
	// parse command-line options
	parseArgs(argv);

        out.println("\nTesting class ContentType: getParameter(String)\n");

        try {
	   // Create ContentType object
              ContentType ct = new ContentType(pattern);

              if( ct == null )
		  return Status.failed("Failed to create ContentType object!");

           // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1: getParameter(charset)");
              String param = ct.getParameter("charset");    // API TEST
	      
              if( param != null ) {
		  out.println("Parameter is "+param);
                  out.println("UNIT TEST 1: passed.\n");
              } else {
                    out.println("UNIT TEST 1: FAILED.\n");
                    errors++;
              }
           // END UNIT TEST 1:
           // BEGIN UNIT TEST 2:
              out.println("UNIT TEST 2: getParameter(i18set)");
              param = ct.getParameter("i18set");    // API TEST
              
              if( param != null ) {
                  out.println("Parameter is "+param);
                  out.println("UNIT TEST 2: passed.\n");
              } else {
                    out.println("UNIT TEST 2: FAILED.\n");
                    errors++;
              }
           // END UNIT TEST 2:

              checkStatus();
        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
