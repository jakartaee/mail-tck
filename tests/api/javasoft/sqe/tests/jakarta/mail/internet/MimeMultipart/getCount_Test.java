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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMultipart;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getCount()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return the number of enclosed BodyPart objects. <p>
 * api2test: public int getCount()  <p>
 *
 * how2test: Call API, then verify it returns integer. If this is
 *	     so then testcase passes, otherwise it fails.
 */

public class getCount_Test extends MailTest {

    public static void main( String argv[] )
    {
        getCount_Test test = new getCount_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeMultipart: getCount()\n");

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
		// get message content
		Object co = msg.getContent();
		
		if ( co == null ) {
		     log.println("Warning: Failed to get message content "+ i);
                     continue;
                }

		if( co instanceof Multipart )
		{
		   // cast object to MimeMultipart
		     MimeMultipart mp = (MimeMultipart)co;

                     out.println("UNIT TEST "+ i +": getCount()");

                  // get the number of bodyparts enclosed for message
                     int partCount = mp.getCount();   // API TEST

                     if( partCount > 0 ) {
                         out.println("Message's bodypart count is "+ partCount);
                         out.println("UNIT TEST "+ i +": passed");
                     } else
                           out.println("UNIT TEST "+ i +": has no bodyparts!\n");
		} else
			continue;

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
