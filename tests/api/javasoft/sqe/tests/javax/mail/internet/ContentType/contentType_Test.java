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

    public static void main( String argv[] )
    {
        contentType_Test test = new contentType_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	 super.run(argv, log, out);
	// parse command-line options
	parseArgs(argv);

        out.println("\nTesting class ContentType: ContentType(void|String|ParameterList)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1:  ContentType()");
	     ContentType ct1 = new ContentType();    // API TEST

	     if( ct1.toString() != null )
		 out.println("UNIT TEST 1: passed");
	     else {
		   out.println("UNIT TEST 1: FAILED");
		   errors++;
	     }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.println("UNIT TEST 2:  ContentType("+pattern+")");
             ContentType ct2 = new ContentType(pattern);    // API TEST

             if( ct2.toString() != null )
                 out.println("UNIT TEST 2: passed");
             else {
                   out.println("UNIT TEST 2: FAILED");
                   errors++;
             }
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
	     String primaryType = "text";
	     String subType = "plain";

             out.println("UNIT TEST 3:  ContentType("+primaryType+","+subType+")");
             ContentType ct3 = new ContentType(primaryType, subType, null);    // API TEST

             if (ct3.toString().equals(primaryType + "/" + subType))
                 out.println("UNIT TEST 3: passed");
             else {
                 out.println("UNIT TEST 3: FAILED");
                 errors++;
             }
          // END UNIT TEST 3:
          // BEGIN UNIT TEST 4:
	  // Create a ParameterList object
	     ParameterList pl = new ParameterList(";charset=uscii");

	     if( pl == null )
		 return Status.failed("Failed to create ParameterList object!");

             out.println("UNIT TEST 4:  ContentType("+primaryType+","+subType+","+pl+")");
             ContentType ct4 = new ContentType(primaryType, subType, pl);    // API TEST

             if( ct4.toString() != null )
                 out.println("UNIT TEST 4: passed");
             else {
                 out.println("UNIT TEST 4: FAILED");
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
