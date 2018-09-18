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

package javasoft.sqe.tests.javax.mail.internet.MimeMultipart;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>MimeMultipart()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the subtype. <p>
 * api2test: public void setSubType(String)  <p>
 *
 * how2test: Call API with string arguments, if this operation is successfull,
 *	     then testcase passes, otherwise it fails.
 */

public class setSubType_Test extends MailTest {

    public static void main( String argv[] )
    {
        setSubType_Test test = new setSubType_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeMultipart: setSubType(String)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1:  setSubType( mixed )");
	     MimeMultipart mp = new MimeMultipart();

	     if ( mp != null ) {
		  mp.setSubType("mixed");	// API TEST
		  out.println("UNIT TEST 1: passed");
	     } else {
		    out.println("UNIT TEST 1: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.println("UNIT TEST 2:  setSubType(>@#$%  &^%!<)");

	     if ( mp != null ) {
		  mp.setSubType(">@#$%	&^%!<");	// API TEST
		  out.println("UNIT TEST 2: passed");
	     } else {
		    out.println("UNIT TEST 2: FAILED");
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
