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

package javasoft.sqe.tests.javax.mail.internet.MimeBodyPart;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>getMatchingHeaderLines()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	     Get matching header lines as an Enumeration of Strings. <p>
 * api2test: public Enumeration getMatchingHeaderLines(String[])  <p>
 *
 * how2test: Call API, it will return an enumerated list of all header lines. If
 *           operation is a success then this testcase passes, otherwise it fails.
 */

public class getMatchHeadLines_Test extends MailTest {

    public static String[] headerlist = { "Date","To","From","Reply-To","Subject","Return-Path" };
    
    public static void main( String argv[] )
    {
        getMatchHeadLines_Test test = new getMatchHeadLines_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeBodyPart: getMatchingHeaderLines(String[])\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);

             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }

             for( int i = 1; i <= msgcount; i++ )
             {
                // Get message object
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

                if( msg == null ) {
                    log.println("Warning: Failed to get message number "+ i);
                    continue;
                }
	     // BEGIN UNIT TEST:
	        out.println("UNIT TEST "+ i +":  getMatchingHeaderLines(String[])");

		// Get the "type" of content
	        Object content = msg.getContent();

		if ( content instanceof Multipart ) {
		     int bodycount = ((MimeMultipart)content).getCount();
		     for( int k = 0; k < bodycount; k++ )
		     {   // get k-th bodypart
		     	  BodyPart bp = ((MimeMultipart)content).getBodyPart(k);

			 // get multipart content-type
			  Enumeration matchead = ((MimeBodyPart)bp).getMatchingHeaderLines(headerlist);	// API TEST

                	  while( matchead.hasMoreElements() ) {
				 Header headers = (Header)matchead.nextElement();
				 out.println(headers.getName());
			  }
                	  out.println("UNIT TEST "+ i +": passed");
		      }
		}
             // END UNIT TEST:
             }
             folder.close(false);
             store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
