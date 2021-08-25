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

package javasoft.sqe.tests.jakarta.mail.internet.ContentType;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>match()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *		Match with the specified content-type string. <p>
 * api2test: public boolean match(String)  <p>
 *
 *		Match with the specified ContentType object. <p>
 * api2test: public boolean match(ContentType)  <p>
 *
 * how2test: Call API, verify that it return a boolean value.
 *	     If so then testcase passes.
 */

public class match_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	 

        out.fine("\nTesting class ContentType: match(String)\n");

        try {
	   // Create ContentType object
              ContentType ct = new ContentType(pattern);

              if( ct == null )
		  return Status.failed("Failed to create ContentType object!");

           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1: match("+pattern+")");

              if( ct.match(pattern) )		// API TEST
                  out.fine("UNIT TEST 1: passed.\n");
              else {
                    out.fine("UNIT TEST 1: FAILED.\n");
                    errors++;
              }
           // END UNIT TEST 1:
           // BEGIN UNIT TEST 2:
              out.fine("UNIT TEST 2: match(ContentType)");

              if( ct.match(ct) )           // API TEST
                  out.fine("UNIT TEST 2: passed.\n");
              else {
                    out.fine("UNIT TEST 2: FAILED.\n");
                    errors++;
              }
           // END UNIT TEST 2:
           // BEGIN UNIT TEST 3:
              out.fine("UNIT TEST 3: match(ContentType)");
	      ContentType gt = new ContentType();

              if( ! ct.match(gt) )           // API TEST
                  out.fine("UNIT TEST 3: passed.\n");
              else {
                    out.fine("UNIT TEST 3: FAILED.\n");
                    errors++;
              }
           // END UNIT TEST 3:

              checkStatus();
        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
