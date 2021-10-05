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
 * This class tests the <strong>equals()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		The equality operator. <p>
 * api2test: public boolean equals(Object)  <p>
 *
 * how2test: Call API with two equal/unequal Object argument, check the boolean
 *           value returned, if equal/unequal then testcase passes.
 */

public class equals_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class NewsAddress: equals(Object)\n");

        try {
	   // Construct a NewsAddress object
	      NewsAddress na = new NewsAddress(pattern);

	      if( na == null ) {
		  return Status.failed("Failed to create newsgroup object!");
	      }
           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1:  equals((Object)equal)");

              if( na.equals((Object)na))	// API TEST
                  out.fine("UNIT TEST 1: passed");
              else {
                      out.fine("UNIT TEST 1: FAILED");
                      errors++;
              }
           // END UNIT TEST 1:
	   // BEGIN UNIT TEST 2:
              // Construct a NewsAddress object
              NewsAddress ng = new NewsAddress();

              if( ng == null ) {
                  return Status.failed("Failed to create second newsgroup object!");
              }
              out.fine("UNIT TEST 2:  equals((Object)not-equal)");

	      if( ! na.equals((Object)ng) )	// API TEST
                  out.fine("UNIT TEST 2: passed");
              else {
                    out.fine("UNIT TEST 2: FAILED");
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
