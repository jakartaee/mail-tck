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

package javasoft.sqe.tests.jakarta.mail.Session;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getDefaultInstance(Properties, Authenticator)</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the returned object. <p>
 *
 * Test getting the default Session object. If a default has not yet been setup,
 * a new Session object is created and installed as the default. <p>
 *
 * api2test: public Session getDefaultInstance(Properties, Authenticator)  <p>
 *
 * how2test: Call the getDefaultInstance(..) API with certain values, if it then <p>
 *	     returns a object of type Session then this test is considered passing! <p>
 *
 *    input: <p>
 *              param1 type    | param2 type    | return type	<p>
 *            -----------------------------------------------	<p>
 *              properties     | null		| Session	<p>
 *              default props  | null           | Session	
 */

public class getDefaultInstance_Test extends MailTest {

    public static void main( String argv[] )
    {
        getDefaultInstance_Test test = new getDefaultInstance_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);	
	parseArgs(argv);	// parse command-line options

	out.println("\nTesting class Session: getDefaultInstance(Properties, Authenticator)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1: getDefaultInstance(Properties, null)");
             Session session_1 = Session.getDefaultInstance(properties, null);   // API TEST

             if( session_1 != null && ( session_1 instanceof Session ) ) {
                 Store store = session_1.getStore(protocol);
		 out.println("UNIT TEST 1:  passed\n");
             } else {
		     out.println("UNIT TEST 1:  FAILED\n");
		     errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     // Get Properties object
	     Properties props = new Properties();
	     if (user != null)
		 props.put("mail."+ protocol +".user", user);
	     if (host != null)
		 props.put("mail."+ protocol +".host", host);

	     out.println("UNIT TEST 2: getDefaultInstance(props, null)");
	     Session session_2 = Session.getDefaultInstance(props, null);	// API TEST

             if( session_2 != null && ( session_2 instanceof Session ) ) {
                 Store store = session_2.getStore(protocol);
                 out.println("UNIT TEST 2:  passed\n");
             } else {
                     out.println("UNIT TEST 2:  FAILED\n");
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
