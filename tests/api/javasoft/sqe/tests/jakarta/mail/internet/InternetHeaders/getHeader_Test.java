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
 * This class tests the <strong>getHeader()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return all the values for the specified header. <p>
 * api2test: public String[] getHeader(String)  <p>
 *		Get all the headers for header name, returned as a single String,
 *		with headers separated by the delimiter. <p>
 * api2test: public String[] getHeader(String, String)  <p>
 *
 * how2test: Call these APIs with various input header names. Verify that it returns
 *           array of strings. Write out list to stdio. If this operation is
 *           successfull then testcase passes, otherwise it fails.
 */

public class getHeader_Test extends MailTest {

    public static String[] header = { "Date","To","From","Subject" };
    public static String[] delimiter = { ":","+","%" };

    public static void main( String argv[] )
    {
        getHeader_Test test = new getHeader_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class InternetHeaders: getHeader(String, String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder");
             }
             folder.open(Folder.READ_ONLY);

             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	  // Get the first message
	     MimeMessage msg = (MimeMessage)folder.getMessage(1);

	     if( msg == null ) {
                 return Status.failed("Warning: Failed to get message number: 1");
             }

	  // Create a ByteArrayOutStream object
	     ByteArrayOutputStream bos = new ByteArrayOutputStream();
	     msg.writeTo(bos);

	  // Create a ByteArrayInputStream object
	     ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

	     InternetHeaders headers = new InternetHeaders(bis);

	  // BEGIN UNIT TEST:
	       // Get the specified message headers

	     for( int j = 0; j < header.length; j++ )
	     {
		  out.println("UNIT TEST "+ j +":  getHeader("+ header[j] +")");

		  String[] headerlist1 = headers.getHeader(header[j]);		// API TEST

		  if( headerlist1 != null ) {
		    if( headerlist1.length > 0 ) {
		       for( int n = 0; n < headerlist1.length; n++ ) {
			    if ( headerlist1[n] != null ) {
				out.println( headerlist1[n] );
				out.println("UNIT TEST "+ j +":  passed\n");
			    } else {
				   out.println("UNIT TEST "+ j +":  FAILED\n");
				   errors++;
			    }
		       }
		     }
		   }
	       }
	       // more getHeader tests

	       for( int j = 0; j < header.length; j++ )
	       {
		   for( int k = 0; k < delimiter.length; k++ )
		   {
		      out.println("UNIT TEST "+ j +":  getHeader("+ header[j] +","+ delimiter[k] +")");

		      String headerlist2 = headers.getHeader(header[j], delimiter[k]);	   // API TEST

		      if( headerlist2 != null ) {
			  out.println(headerlist2);
			  out.println("UNIT TEST "+ j +":  passed\n");
		      } else {
			      out.println("UNIT TEST "+ j +":  FAILED\n");
			      errors++;
		      }
		   }
	        }
	     // END UNIT TEST:

		store.close();
	        checkStatus();

        } catch ( Exception e ) {
                handlException(e);
        }
	return status;
     }
}
