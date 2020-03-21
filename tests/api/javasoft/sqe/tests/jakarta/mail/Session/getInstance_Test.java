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
 * This class tests the <strong>getInstance(Properties, Authenticator)</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of object returned.  <p>
 *
 * api2test: public Session getInstance(Properties, Authenticator) 	<p>
 *
 * how2test: Call this API with properties/authenticator parameter values, <p>
 *	     if this method returns a Session object then the test passes. <p>
 *
 *    input:	<p>
 *              param1 type    | param2 type    | return type	<p>
 *            -----------------------------------------------	<p>
 *              properties     | null		| Session	<p>
 *              properties     | null           | Session	
 */

public class getInstance_Test extends MailTest {

    public static void main( String argv[] )
    {
        getInstance_Test test = new getInstance_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Session: getInstance(Properties, Authenticator)\n");

        try {
          // BEGIN UNIT TEST 1:
             out.println("UNIT TEST 1: getInstance(System.getProperties(), null)");
             Session session_1 = Session.getInstance(properties, null); // API TEST

             if( session_1 != null ) {
                 Store store = session_1.getStore(protocol);
                 out.println("UNIT TEST 1:  passed\n");
             } else {
                     out.println("UNIT TEST 1:  FAILED\n");
                     errors++;
             }
          // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
	     // Get a Properties object
             Properties props = new Properties();
	     if (user != null)
		 props.put("mail."+ protocol +".user", user);
	     if (host != null)
		 props.put("mail."+ protocol +".host", host);

             out.println("UNIT TEST 2: getInstance(props, null)");
             Session session_2 = Session.getInstance(props, null);     // API TEST

             if ( session_2 != null ) {
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
