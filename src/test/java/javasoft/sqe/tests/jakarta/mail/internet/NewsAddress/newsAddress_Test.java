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
 * This class tests the <strong>NewsAddress()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Constructor, that constructs a NewsAddress object. <p>
 * api2test: public NewsAddress(void|String|String)  <p>
 *
 * how2test: Call these constructors with/out String arguments, verify that NewsAddress object
 *	     type gets created. If so then testcase passes, otherwise it fails.
 */

public class newsAddress_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class NewsAddress: NewsAddress(void|String|String)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1:  NewsAddress()");
	     NewsAddress na1 = new NewsAddress();    // API TEST

	     if( na1 != null )
		 out.fine("UNIT TEST 1: passed");
	     else {
		   out.fine("UNIT TEST 1: FAILED");
		   errors++;
	     }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.fine("UNIT TEST 2:  NewsAddress("+pattern+")");
             NewsAddress na2 = new NewsAddress(pattern);    // API TEST

             if( na2 != null ) {
		 out.fine("Newsgroup name is "+ na2.getNewsgroup());
                 out.fine("UNIT TEST 2: passed");
             } else {
                     out.fine("UNIT TEST 2: FAILED");
                     errors++;
             }
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
             out.fine("UNIT TEST 3:  NewsAddress("+pattern+" ,"+host+")");
             NewsAddress na3 = new NewsAddress(pattern, host);    // API TEST

             if( na3 != null ) {
		 out.fine("Host name is "+ na3.getHost());
		 out.fine("Newsgroup name is "+ na3.getNewsgroup());
                 out.fine("UNIT TEST 3: passed");
             } else {
                     out.fine("UNIT TEST 3: FAILED");
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
