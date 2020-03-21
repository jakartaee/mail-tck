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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getContentType()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Returns the Content-Type of the content of this message part. <p>
 * api2test: public String getContentType()  <p>
 *
 * how2test: Call this API, check that it returns an object of type string.
 *	     Make sure that this method does not return null value.
 */

public class getContentType_Test extends MailTest {

    public static String contentype = null;

    public static void main( String argv[] )
    {
        getContentType_Test test = new getContentType_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: getContentType()\n");

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

             for( int i = 1; i <= msgcount; i++ )
             {
            // Get the message
               Message msg = folder.getMessage(i);

	       if ( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	       }
	    // BEGIN UNIT TEST:
		// Get body content type of message(s)
	       out.println("UNIT TEST "+ i +":  getContentType()");

	       contentype = msg.getContentType();		// API TEST
	       out.println("getContentType() :=> "+ contentype);

	       if (( contentype != null ) && ( contentype instanceof String ))
                     out.println("UNIT TEST "+ i +":  passed\n");
	       else {
		     out.println("UNIT TEST "+ i +":  FAILED\n");
		     errors++;
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
