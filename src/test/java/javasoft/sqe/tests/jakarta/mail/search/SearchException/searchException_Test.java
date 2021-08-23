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

package javasoft.sqe.tests.jakarta.mail.search.SearchException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>SearchException()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Constructs a SearchException with no detail message. <p>
 * api2test: public SearchException()  <p>
 *              Constructs a SearchException with the specified detail message.  <p>
 * api2test: public SearchException(String)  <p>
 *
 * how2test: Call these APIs with/out string argument and if it successfully 
 *	     creates objects of type SearchException, then this testcase passes.
 */

public class searchException_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class SearchException: SearchException(void | String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1: SearchException()");

             SearchException se1 = new SearchException();	// API TEST

	     if( se1 != null )
	     	 out.fine("UNIT TEST 1: passed.\n");
	     else {
		   out.fine("UNIT TEST 1: FAILED.\n");
		   errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.fine("UNIT TEST 2: SearchException(String)");

             SearchException se2 = new SearchException(subject);       // API TEST

             if( se2 != null )
                 out.fine("UNIT TEST 2: passed.\n");
             else {
                   out.fine("UNIT TEST 2: FAILED.\n");
                   errors++;
             }
	  // END UNIT TEST 2:

	     store.close();
             checkStatus();
        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
