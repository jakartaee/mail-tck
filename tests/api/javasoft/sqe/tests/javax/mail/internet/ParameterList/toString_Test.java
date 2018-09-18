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

package javasoft.sqe.tests.javax.mail.internet.ParameterList;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>toString()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    	Set a parameter. <p>
 * api2test: public String toString()  <p>
 *
 * how2test: Call API, then verify calling getNames() method. If this operation
 *	     is successfull then testcase passes, otherwise it fails.
 */

public class toString_Test extends MailTest {

    public static String[] pname  = { "charset","html","audio","i18set","image" };
    public static String[] pvalue = { "us-ascii","html-4.x","us-dolby","ISO-9000-XZ","US-VHS" };

    public static void main( String argv[] )
    {
        toString_Test test = new toString_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class ParameterList: toString()\n");

        try {
	  // create a ParameterList object
	     ParameterList parmlist = new ParameterList();

	     if( parmlist == null ) {
		 return Status.failed("Warning: Failed to create ParameterList object!");
	     }
	  // BEGIN UNIT TEST:

	     for( int i = 0; i < pname.length; i++ )
	     {
		// set a parameter
		  parmlist.set(pname[i], pvalue[i]);
	     }
	     out.println("UNIT TEST 1: toString()");
	     String st = parmlist.toString();		// API TEST

	     if( st != null )
	         out.println("UNIT TEST 1: passed");
	     else {
		   out.println("UNIT TEST 1: FAILED");
		   errors++;
	     }
	  // END UNIT TEST:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
