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

package javasoft.sqe.tests.javax.mail.Multipart;

import java.util.*;
import java.io.*;
import javax.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>getBodyPart()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Get the specified bodypart object. <p>
 * api2test: public BodyPart getBodyPart(int)  <p>
 
 * how2test: Call this API, then verify both return objects of type BodyPart. If
 *	     this is so then this testcase passes, otherwise it fails.
 */

public class getBodyPart_Test extends MailTest {

    public static void main( String argv[] )
    {
        getBodyPart_Test test = new getBodyPart_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Multipart: getBodyPart(int)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
                  return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     boolean istrue = false;
	     boolean isfalse = false;

             for( int i = 1; i <= msgcount; i++ )
             {
                // Get message object
                Message msg = folder.getMessage(i);

                if( msg == null ) {
                    log.println("Warning: Failed to get message number "+ i);
                    continue;
                }
             // BEGIN UNIT TEST:
		// get message content
		Object co = msg.getContent();
		
		if ( co == null ) {
		     log.println("Warning: Failed to get message content "+ i);
                     continue;
                }
		if( co instanceof Multipart )
		{
		  // cast object to Multipart
		     Multipart mp = (Multipart)co;

                     out.println("UNIT TEST "+ i +": getBodyPart(int)");

                  // get the number of bodyparts enclosed for this message
                     int partCount = mp.getCount();

                     if( partCount > 0 )
		     {
		         for( int j = 0; j < partCount; j++ )
		         {
			     // get the bodyparts enclosed for this message
			     BodyPart bp1 = mp.getBodyPart(j);	// API TEST

			     if( bp1 != null )
			         istrue = true;
			     else
			         isfalse = true;
		         }
		         if( istrue && !isfalse )
			     out.println("UNIT TEST "+ i +": passed");
		         else {
			       out.println("UNIT TEST "+ i +": FAILED");
			       errors++;
		         }
                     } else {
                             out.println("Message's has no bodyparts!");
                             out.println("UNIT TEST "+ i +": FAILED");
                             errors++;
                       }
		} else {
			out.println("This Message is not a Multipart!\n");
			continue;
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
