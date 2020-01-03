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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.activation.DataHandler;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setDataHandler()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		This method sets the Message's content. <p>
 * api2test: public void setDataHandler(DataHandler)  <p>
 *
 * how2test: Call the above API, then call getDataHandler(), if method returns
 *	     a valid DataHandler object, use it to call getInputStream(), and then
 *	     read/write its contents to standard output. If operation is
 *	     successfull then testcase passes otherwise it fails.
 */

public class setDataHandler_Test extends MailTest {

    public static void main( String argv[] )
    {
        setDataHandler_Test test = new setDataHandler_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeMessage: setDataHandler(DataHandler)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder");
             }
             folder.open(Folder.READ_WRITE);

             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
          // Get message object
             MimeMessage dmsg = (MimeMessage)folder.getMessage(1);

             if( dmsg == null ) {
                 log.println("Warning: Failed to get message number 1");
		 return Status.failed("Failed to get message number 1");
             }
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.println("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
          // BEGIN UNIT TEST:
             out.println("UNIT TEST 1: setDataHandler(DataHandler)");

             msg.setDataHandler( dmsg.getDataHandler() );      // API TEST
	     DataHandler dh = msg.getDataHandler();
	     InputStream is = dh.getInputStream();

	     // Lets try reading all the bytes (we throw away the
	     // bytes that we read)
	     int ch;
	     while(( ch = is.read()) != -1 )
			;

             if( dh != null )
                 out.println("UNIT TEST 1: passed\n");
             else {
                   out.println("UNIT TEST 1: FAILED\n");
                   errors++;
             }
          // END UNIT TEST:

             folder.close(false);
             store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
