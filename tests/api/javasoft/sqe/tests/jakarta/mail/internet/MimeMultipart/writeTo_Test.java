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
 * This class tests the <strong>writeTo()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	  Iterates through all the parts and outputs each Mime part separated by a boundary.<p>
 * api2test: public void writeTo(OutputStream)  <p>
 *
 * how2test: Call API with output stream argument. If this operation is successfull
 *	     then testcase passes, otherwise it fails.
 */

public class writeTo_Test extends MailTest {

    public static void main( String argv[] )
    {
        writeTo_Test test = new writeTo_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeMultipart: writeTo(OutputStream)\n");

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
	     // create a OutputStream object
	     OutputStream os = new FileOutputStream(workdir + iofile);

	     if( os == null ) {
		 return Status.failed("Invalid/Null OutputStream object!");
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
                out.println("UNIT TEST "+ i +": writeTo(OutputStream)");
		Object ob = msg.getContent();
		
		if ( ob instanceof Multipart )
		{   
		     MimeMultipart mp = (MimeMultipart)ob;
		     mp.writeTo( os );      // API TEST

		     if ( os != null )
			  out.println("UNIT TEST "+ i +": passed");
		     else {
			    out.println("UNIT TEST "+ i +": FAILED");
			    errors++;
		     }
                }
		else {
		      out.println("UNIT TEST "+ i +": Warning: Content not of type Multipart!");
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
