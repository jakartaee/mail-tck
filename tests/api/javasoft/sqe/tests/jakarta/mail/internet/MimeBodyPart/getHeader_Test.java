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

package javasoft.sqe.tests.jakarta.mail.internet.MimeBodyPart;

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
 *	     Get all the headers for header name, returned as a single String,
 *	     with headers separated by the delimiter. <p>
 * api2test: public String[] getHeader(String, String)  <p>
 *
 * how2test: Call API with various input header names. Verify that it returns
 *           string of headers. Write out list to stdio. If this operation
 *           is successfull then testcase passes, otherwise it fails.
 */

public class getHeader_Test extends MailTest {

    public static String[] headerName = { "Date","To","Cc","From","Reply-To" };
    public static String[] delimiter  = { ":",";","+","%","#" };

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

        out.println("\nTesting class MimeBodyPart: getHeader(String, String)\n");

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

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
	        out.println("UNIT TEST "+ i +":  getHeader(String, String)");

		// Get the specified message headers
                // Get the "type" of content
                Object content = msg.getContent();

                if ( content instanceof Multipart ) {
		  // get number of enclosed bodyparts
                     int bodycount = ((MimeMultipart)content).getCount();

                     for( int n = 0; n < bodycount; n++ )
                     {
                          BodyPart bp = ((MimeMultipart)content).getBodyPart( n );

			  for( int j = 0; j < headerName.length; j++ )
			  {
			   // get bodypart header name
			      String header = ((MimeBodyPart)bp).getHeader( headerName[j], delimiter[j] );    // API TEST

			      if ( header != null )
				   out.println("getHeaders("+ headerName[j] +","+ delimiter[j] +") :=> '"+ header +"'");
			      else
				   out.println("getHeaders("+ headerName[j] +","+ delimiter[j] +") :=> 'empty field'");
			  }
		      }
                      out.println("UNIT TEST " + i + ":  passed\n");
		} else
		      out.println("UNIT TEST " + i + ": is not Multipart message!");

	     // END UNIT TEST:
	    }
	    folder.close(false);
	    store.close();
	    status = Status.passed("OKAY");

        } catch ( Exception e ) {
	    handlException(e);
        }
	return status;
     }
}
