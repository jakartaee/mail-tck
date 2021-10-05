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
 * This class tests the <strong>ContentType()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *		Default no-arg constructor <p>
 * api2test: public ContentType()  <p>
 *
 *		Constructor that takes a Content-Type string. <p>
 * api2test: public ContentType(String) <p>
 *
 *		Yet another constructor. <p>
 * api2test: public ContentType(String,String,ParameterList)  <p>
 *
 * how2test: Call these constructors with/out arguments, verify that ContentType object
 *	     type gets created. If so then testcase passes, otherwise it fails.
 */

public class contentType_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	 

        out.fine("\nTesting class ContentType: ContentType(void|String|ParameterList)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1:  ContentType()");
	     ContentType ct1 = new ContentType();    // API TEST

	     if( ct1.toString() != null )
		 out.fine("UNIT TEST 1: passed");
	     else {
		   out.fine("UNIT TEST 1: FAILED");
		   errors++;
	     }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.fine("UNIT TEST 2:  ContentType("+pattern+")");
             ContentType ct2 = new ContentType(pattern);    // API TEST

             if( ct2.toString() != null )
                 out.fine("UNIT TEST 2: passed");
             else {
                   out.fine("UNIT TEST 2: FAILED");
                   errors++;
             }
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
	     String primaryType = "text";
	     String subType = "plain";

             out.fine("UNIT TEST 3:  ContentType("+primaryType+","+subType+")");
             ContentType ct3 = new ContentType(primaryType, subType, null);    // API TEST

             if (ct3.toString().equals(primaryType + "/" + subType))
                 out.fine("UNIT TEST 3: passed");
             else {
                 out.fine("UNIT TEST 3: FAILED");
                 errors++;
             }
          // END UNIT TEST 3:
          // BEGIN UNIT TEST 4:
	  // Create a ParameterList object
	     ParameterList pl = new ParameterList(";charset=uscii");

	     if( pl == null )
		 return Status.failed("Failed to create ParameterList object!");

             out.fine("UNIT TEST 4:  ContentType("+primaryType+","+subType+","+pl+")");
             ContentType ct4 = new ContentType(primaryType, subType, pl);    // API TEST

             if( ct4.toString() != null )
                 out.fine("UNIT TEST 4: passed");
             else {
                 out.fine("UNIT TEST 4: FAILED");
                 errors++;
             }
          // END UNIT TEST 4:

             checkStatus();
        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
