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

package javasoft.sqe.tests.javax.mail.Flags;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>retainAll()</strong> API.
 * It does this by passing various valid input values and then checking
 * the result.	<p>
 *
 *		Retain only the specified flags in this Flags object.<p>
 * api2test: public boolean retainAll(Flags flag)  <p>
 *
 * how2test: Create Flags object and Flags-to-retain object.
 *	     Ensure that expected flags are cleared after calling
 *           the retainAll method, and that the correct value is returned.
 */

public class retainAll_Test extends MailTest {

    /*
     * The original Flags object can have no flags set, one particular flag
     * set, a different particular flag set, or two particular flags set.
     * Likewise the argument Flags object.  Check that the resulting Flags
     * object has only the expected flags set and returns the expected value.
     * This gives us a 4 by 4 test matrix for each of system and user flags.
     */
    private static String[] tests = {
	// orig	arg	result
	"none",	"none",	"none",		// system flags
	"none",	"A",	"none",
	"none",	"B",	"none",
	"none",	"AB",	"none",
	"A",	"none",	"none",
	"A",	"A",	"A",
	"A",	"B",	"none",
	"A",	"AB",	"A",
	"B",	"none",	"none",
	"B",	"A",	"none",
	"B",	"B",	"B",
	"B",	"AB",	"B",
	"AB",	"none",	"none",
	"AB",	"A",	"A",
	"AB",	"B",	"B",
	"AB",	"AB",	"AB",
	"none",	"none",	"none",		// user flags
	"none",	"X",	"none",
	"none",	"Y",	"none",
	"none",	"XY",	"none",
	"X",	"none",	"none",
	"X",	"X",	"X",
	"X",	"Y",	"none",
	"X",	"XY",	"X",
	"Y",	"none",	"none",
	"Y",	"X",	"none",
	"Y",	"Y",	"Y",
	"Y",	"XY",	"Y",
	"XY",	"none",	"none",
	"XY",	"X",	"X",
	"XY",	"Y",	"Y",
	"A",	"X",	"none",		// combination
	"X",	"A",	"none",
	"A",	"AX",	"A",
	"X",	"AX",	"X",
	"AX",	"U",	"X",		// USER flag
	"AX",	"AU",	"AX",
	"X",	"U",	"X",
	"A",	"AU",	"A",
	"A",	"U",	"none",
	};

    public static void main( String argv[] )
    {
        retainAll_Test test = new retainAll_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Flags: retainAll(Flags)\n");

        try {
	     int test = 1;
	  // BEGIN UNIT TEST:
	     // first, test system flags
	     for (int i = 0; i < tests.length; i += 3) {
		 Flags orig = createFlags(tests[i]);
		 Flags arg = createFlags(tests[i+1]);
		 Flags result = createFlags(tests[i+2]);
		 out.printf("\nUNIT TEST %d: %s retainAll %s == %s\n", test,
		   p(orig.toString()), p(arg.toString()), p(result.toString()));
		 boolean expected = !tests[i].equals(tests[i+2]);
		 boolean got = orig.retainAll(arg);

		 if (got == expected && orig.equals(result)) {
		       out.printf("UNIT TEST %d: passed\n", test);
		 } else {
		       out.printf("result %s\n", p(result.toString()));
		       out.printf("expected return %b, got %b\n", expected, got);
		       out.printf("UNIT TEST %d: FAILED\n", test);
		       errors++;
		 }
		 test++;
	     }
	  // END UNIT TEST:

	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }

     private static Flags createFlags(String s) {
	Flags f = new Flags();
	if (s.contains("A"))
	    f.add(Flags.Flag.SEEN);
	if (s.contains("B"))
	    f.add(Flags.Flag.RECENT);
	if (s.contains("U"))
	    f.add(Flags.Flag.USER);
	if (s.contains("X"))
	    f.add("X");
	if (s.contains("Y"))
	    f.add("Y");
	return f;
     }

     private static String p(String s) {
	return "(" + s + ")";
     }
}
