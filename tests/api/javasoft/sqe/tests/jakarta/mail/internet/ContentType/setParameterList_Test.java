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

package javasoft.sqe.tests.jakarta.mail.internet.ContentType;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setParameterList()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *		Set a new ParameterList. <p>
 * api2test: public void setParameterList(ParameterList)  <p>
 *
 * how2test: Call API with ParameterList argument, verify by calling getParameterList(),
 *	     If set/get argument are equal then testcase passes, otherwise it fails.
 */

public class setParameterList_Test extends MailTest {

    public static void main( String argv[] )
    {
        setParameterList_Test test = new setParameterList_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	 super.run(argv, log, out);
	// parse command-line options
	parseArgs(argv);

        out.println("\nTesting class ContentType: setParameterList(ParameterList)\n");

        try {
	   // Create ContentType object
              ContentType ct = new ContentType(pattern);

              if( ct == null )
		  return Status.failed("Failed to create ContentType object!");

           // BEGIN UNIT TEST 1:
           // Create a ParameterList object
              ParameterList paramlist = new ParameterList(";charset=uscii;charset=ISO-9000-1");

              if( paramlist == null )
                  return Status.failed("Failed to create ParameterList object!");

              out.println("UNIT TEST 1: setParameterList(ParameterList)");
	      ct.setParameterList(paramlist);	// API TEST
              ParameterList plist = ct.getParameterList();

              if( plist != null )
                  out.println("UNIT TEST 1: passed.\n");
              else {
                    out.println("UNIT TEST 1: FAILED.\n");
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
