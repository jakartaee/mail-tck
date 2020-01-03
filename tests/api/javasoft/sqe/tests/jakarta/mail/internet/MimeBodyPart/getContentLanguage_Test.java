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
 * This class tests the <strong>getContentLanguage()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *    Get the languages specified in the Content-Language header of MimePart. <p>
 * api2test: public String[] getContentLanguage()  <p>
 *
 * how2test: Call API, if it returns array of strings then it passes,
 *	     otherwise it fails.
 */

public class getContentLanguage_Test extends MailTest {

    public static void main( String argv[] )
    {
        getContentLanguage_Test test = new getContentLanguage_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeBodyPart: getContentLanguage()\n");

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
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if ( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
	        out.println("UNIT TEST "+ i +":  getContentLanguage()");

		// Get the "type" of content
	        Object content = msg.getContent();

		if ( content instanceof Multipart ) {
		     int bodycount = ((MimeMultipart)content).getCount();

		     for( int k = 0; k < bodycount; k++ )
		     {
		     	  BodyPart bp = ((MimeMultipart)content).getBodyPart(k);

			// get multipart's content langauge
			  String[] getlang = ((MimeBodyPart)bp).getContentLanguage();   // API TEST
			  if ( getlang != null ) {
			       for( int j = 0; j < getlang.length; j++ ) {
			            if( getlang[j] != null )
				        out.println("Mimepart's content language is "+ getlang[j]);
			       }
			       out.println("UNIT TEST "+ i +": passed");
			  } else
			       out.println("UNIT TEST "+ i +": has no content language.");
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
