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

package javasoft.sqe.tests.jakarta.mail.FetchProfile;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getHeaderNames()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Get the names of the header-fields set in this profile. <p>
 * api2test: public void getHeaderNames()  <p>
 *
 * how2test: Call this API, check the returned header-fields values. If these are
 *	     same as add() arguments, then this testcase passes, otherwise it fails.
 */

public class getHeaderNames_Test extends MailTest {

    public boolean notFound = false;
    public String[] itemName = { "From","Subject","X-mailer","abcxyz123" };

    public static void main( String argv[] )
    {
        getHeaderNames_Test test = new getHeaderNames_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class FetchProfile: getHeaderNames()\n");

        try {
	   // Create an empty FetchProfile
	      FetchProfile fp = new FetchProfile();
	     
	      if( fp == null ) {
		  return Status.failed("Failed to create an empty FetchProfile object!");
	      }
	   // Add header names to Profile object

	      for( int i = 0; i < itemName.length; i++ )
		   fp.add(itemName[i]);

	   // BEGIN UNIT TEST 1:

	      out.println("UNIT TEST 1: getHeaderNames()");

	      String[] headname = fp.getHeaderNames(); 	// API TEST

	      for( int j = 0; j < headname.length; j++ )
	      {
		   out.println(headname[j]);

		   if( !itemName[j].equals(headname[j]) ) {
		       notFound = true;
		       break;
		   }
              }

	      if( !notFound )
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
