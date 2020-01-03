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
import jakarta.activation.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setDataHandler()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		This method sets the Message's content. <p>
 * api2test: public void setDataHandler(DataHandler content)  <p>
 *
 * how2test: Call this API with content argument, then call getDataHandler() method, if
 *	     this operation is successful then this testcase passes, otherwsie it fails.
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

        out.println("\nTesting class Message: setDataHandler(DataHandler)\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.println("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
	  // BEGIN UNIT TEST:
	     // Set the message's (DataHandler) content

	     out.println("UNIT TEST 1:  setDataHandler(DataHandler)");
	     DataHandler datahandle = msg.getDataHandler();

	     msg.setDataHandler(datahandle); 	// API TEST
	     datahandle = msg.getDataHandler();
	     out.println("setDataHandler("+ datahandle +")");

	     if( datahandle != null && ( datahandle instanceof DataHandler ))
                 out.println("UNIT TEST 1:  passed\n");
	     else {
		   out.println("UNIT TEST 1:  FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST:

	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
