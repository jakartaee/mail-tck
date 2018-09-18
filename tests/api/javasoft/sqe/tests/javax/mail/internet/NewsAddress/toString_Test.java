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

package javasoft.sqe.tests.javax.mail.internet.NewsAddress;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>toString()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	Convert address into a RFC 1036 address. <p>
 * api2test: public String toString()  <p>
 *
 *	Convert the given array of NewsAddress objects into a comma separated sequence of address strings.<p>
 * api2test: public String toString(NewsAddress[])  <p>
 *
 * how2test: Call these APIs with/out NewsAddress[] argument, verify by comparing
 *           returned values, if equal then testcase passes, otherwise it fails.
 */

public class toString_Test extends MailTest {

    public static void main( String argv[] )
    {
        toString_Test test = new toString_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class NewsAddress: toString(void | NewsAddress[])\n");

        try {
	   // Construct a NewsAddress object
	      NewsAddress na = new NewsAddress("javasoft.hot.news");

	      if( na == null ) {
		  return Status.failed("Failed to create newsgroup object!");
	      }
           // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1:  toString()");
              String rfctype = na.toString();      // API TEST

              if( rfctype != null ) {
		  out.println("RFC1036 address of newsgroup is "+rfctype);
                  out.println("UNIT TEST 1: passed");
              } else {
		      out.println("Warning: Failed to convert address to RFC1036!");
                      out.println("UNIT TEST 1: FAILED");
                      errors++;
              }
           // END UNIT TEST 1:
	   // BEGIN UNIT TEST 2:
              // Construct a NewsAddress object
              NewsAddress ng = new NewsAddress();

              if( ng == null ) {
                  return Status.failed("Failed to create second newsgroup object!");
              }
              out.println("UNIT TEST 2:  toString(NewsAddress[])");

	      NewsAddress[] nalist = ng.parse(pattern);
	      String addlist = ng.toString(nalist);	// API TEST

	      if( pattern.equals(addlist) )
                  out.println("UNIT TEST 2: passed");
              else {
                    out.println("UNIT TEST 2: FAILED");
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
