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

package javasoft.sqe.tests.jakarta.mail.internet.InternetHeaders;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addHeaderLine()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	     Add an RFC822 header line to the header store.  <p>
 * api2test: public void addHeaderLine(String); <p>
 *
 * how2test: Call API with various arguments, then verify by calling getHeader() api and
 *	     comparing the 'add/'get' values. If they are equal then testcase passes,
 *	     otherwise it fails.
 *
 */

public class addHeaderLine_Test extends MailTest {

    public static String[] headerline = { "Date: Fri Dec  5 17:48:51 PST 1997-1998","From: tester@sun.com","Subject: JavaMail testing","To: Javamail@sun.com","Cc: editor@javaworld.com" };
    
    public static void main( String argv[] )
    {
        addHeaderLine_Test test = new addHeaderLine_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class InternetHeaders: addHeaderLine(String)\n");

        try {
	  // create internetheader object
	     InternetHeaders ih = new InternetHeaders();
		  
	     if( ih == null ) {
		 return Status.failed("Warning: failed to created InternetHeaders object");
	     }
	  // BEGIN UNIT TEST:
	     // add header 'name|value' pairs

	     for( int i = 0; i < 5; i++ )
	     {
		  out.println("UNIT TEST "+ i +":  addHeaderLine("+ headerline[i] +")");
		  ih.addHeaderLine(headerline[i]);	// API TEST
	     }
	    // get all headerlines
	     Enumeration allheaders = ih.getAllHeaderLines();

	     while( allheaders.hasMoreElements() ) {
		    String headers = (String)allheaders.nextElement();
		    out.println(headers);
	     }
	     out.println("ALL UNIT TEST: passed");
	  // END UNIT TEST:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
