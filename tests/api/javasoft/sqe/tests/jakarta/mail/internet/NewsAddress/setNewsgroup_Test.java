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
 * This class tests the <strong>setNewsgroup()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the specified newsgroup. <p>
 * api2test: public void setNewsgroup(String)  <p>
 *
 * how2test: Call API with string argument, verify by calling getNewsgroup(),
 *           compare values if equal then testcase passes, otherwise it fails.
 */

public class setNewsgroup_Test extends MailTest {

    public static void main( String argv[] )
    {
        setNewsgroup_Test test = new setNewsgroup_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class NewsAddress: setNewsgroup(String)\n");

        try {
	   // Construct a NewsAddress object
	      NewsAddress na = new NewsAddress();

	      if( na == null ) {
		  return Status.failed("Failed to create newsgroup object!");
	      }
	   // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1:  setNewsgroup("+pattern+")");

	      na.setNewsgroup(pattern); 	// API TEST
	      String ngName = na.getNewsgroup();

              if( ngName != null && pattern.equals(ngName) )
                  out.println("UNIT TEST 1: passed");
              else {
		    out.println("Warning: Failed to set newsgroup!");
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
