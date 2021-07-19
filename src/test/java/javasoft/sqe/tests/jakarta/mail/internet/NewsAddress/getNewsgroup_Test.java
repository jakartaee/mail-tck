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

package javasoft.sqe.tests.jakarta.mail.internet.NewsAddress;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getNewsgroup()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Get the name of the newsgroup. <p>
 * api2test: public String getNewsgroup()  <p>
 *
 * how2test: Call API, verify that it returns a string object
 *	     If so then testcase passes, otherwise it fails.
 */

public class getNewsgroup_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class NewsAddress: getNewsgroups()\n");

        try {
	   // Construct a NewsAddress with the given newsgroup
	      NewsAddress na = new NewsAddress(pattern);

	      if( na == null ) {
		  return Status.failed("Failed to create "+pattern+" newsgroup!");
	      }
	   // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1:  getNewsgroup()");
	      String newsgp = na.getNewsgroup(); 	// API TEST

              if( newsgp != null ) {
		  out.println("Newsgroup name is "+ newsgp);
                  out.println("UNIT TEST 1: passed");
              } else {
		      out.println("Warning: Newsgroup name is null!");
                      out.println("UNIT TEST 1: FAILED");
                      errors++;
              }
           // END UNIT TEST 1:
           // BEGIN UNIT TEST 2:
              // Construct a NewsAddress with the given newsgroup

	      pattern = "xx.yy.zz#z.11@1.99&9";
              NewsAddress ng = new NewsAddress(pattern, host);

              if( ng == null ) {
                  return Status.failed("Failed to create "+pattern+" newsgroup!");
              } 
              out.println("UNIT TEST 2:  getNewsgroup()");
              String badng = ng.getNewsgroup();    // API TEST

              if( badng != null ) {
		  out.println("Newsgroup name is "+ badng);
                  out.println("UNIT TEST 2: passed");
              } else {
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
