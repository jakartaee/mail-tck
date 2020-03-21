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

package javasoft.sqe.tests.jakarta.mail.internet.AddressException;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getRef()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	       Get the string that was being parsed when the error was detected. <p>
 * api2test: public int getRef()  <p>
 *
 * how2test: Call this API and if it successfully return String value,
 *	     then this testcase passes.
 */

public class getRef_Test extends MailTest {

    private AddressException ae;

    public static void main( String argv[] )
    {
        getRef_Test test = new getRef_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class AddressException: getRef(void)\n");

        try {
           // Connect to host server
              Store store = connect2host(protocol, host, user, password);

	   // Create AddressException object
              ae = new AddressException(subject, pattern, 11);

              if( ae == null )
                  return Status.failed("Invalid/null AddressException object!");

           // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1: getRef()");

              String str = ae.getRef();       // API TEST

              if( str != null )
                  out.println("UNIT TEST 1: passed.\n");
              else {
                    out.println("UNIT TEST 1: FAILED.\n");
                    errors++;
              }
           // END UNIT TEST 1:
	      store.close();
              checkStatus();

        } catch ( Exception e ) {
              handlException(e);
        }
	return status;
     }
}
