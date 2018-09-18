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

package javasoft.sqe.tests.javax.mail.Provider;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>getVersion()</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the object returned.  <p>
 *		Returns version of this implementation or null if no version. <p>
 * api2test: public String getVersion()  <p>
 *
 * how2test: Call this API, check that it returns some version of this implementation
 *	     or null. If it does then this testcase passes, otherwise it fails.
 */

public class getVersion_Test extends MailTest {

    public static void main( String argv[] )
    {
        getVersion_Test test = new getVersion_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

	out.println("\nTesting class Provider: getVersion()\n");

        try {
	   // Get Session object
              Session session = Session.getInstance(properties, null);
	      Provider prov = session.getProvider(protocol);

              if( prov == null ) {
                  return Status.failed("Failed to get Provider object!");
              }
           // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1: getVersion()");

	      String version = prov.getVersion();	// API TEST

	      out.println("The implementation version is "+ version);
              out.println("UNIT TEST 1:  passed\n");
           // END UNIT TEST 1:

	      status = Status.passed("OKAY");

        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
