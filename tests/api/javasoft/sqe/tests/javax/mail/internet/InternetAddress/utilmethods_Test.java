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

package javasoft.sqe.tests.javax.mail.internet.InternetAddress;

import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

public class utilmethods_Test extends MailTest {

    public static void main (String argv[])
    {
	utilmethods_Test test = new utilmethods_Test();
	Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String[] argv, PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting InternetAddress class utility APIs => equals(), toString()\n");

	try {
	  // BEGIN UNIT TEST:
	     InternetAddress addr = new InternetAddress(to);

	     if ( addr == null ) {
		  return Status.failed("Invalid/Null InternetAddress object!");
	     }
	     out.println("UNIT TEST 1:  equals()\n");

	     if (addr.toString().equals( addr.toString() ))	    // API TEST
		  out.println("UNIT TEST 1:  passed\n");
	     else {
		   out.println("UNIT TEST 1:  FAILED\n");
		   errors++;
	     }

	     out.println("\nUNIT TEST 2:  toString()");
	     out.println(addr.toString());

	     if ( addr.toString() != null )		// API TEST
		  out.println("UNIT TEST 2:  passed\n");
             else {
                   out.println("UNIT TEST 2:  FAILED\n");
		   errors++;
             }
	  // END UNIT TEST:

	     checkStatus();
        } catch (Exception e) {
	     handlException(e);
          }
	  return status;
    }
}
