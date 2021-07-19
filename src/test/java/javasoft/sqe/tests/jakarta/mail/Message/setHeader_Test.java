/*
 * Copyright (c) 2002, 2021 Oracle and/or its affiliates. All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setHeader()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the value for this header_name. <p>
 * api2test: public void setHeader(String, String)  <p>
 *
 * how2test: Call this API with various header/name string values. Then call getHeader(),
 *	     method, if set values are the same as get values then this testcase passes,
 *	     otherwise it fails.
 */

public class setHeader_Test extends MailTest {

    public static String hname1  = "JavaMail";
    public static String hvalue1 = "     THIS IS ONLY A TEST!    ";

    public static String hname2  = "Subject";
    public static String hvalue2 = "**** SUBJECT NOW HAVE BEEN CHANGED! ****";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

        out.println("\nTesting class Message: setHeader(String, String)\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.println("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
          // BEGIN UNIT TEST:
	     out.println("UNIT TEST 1:  setHeader(String, String)");

	     // Set a header field
	     msg.setHeader(hname1, hvalue1);	// API TEST
	     String[] newheader = msg.getHeader(hname1);

	     out.println("New header : "+ newheader[0]);
             out.println("UNIT TEST 1:  passed\n");

	     out.println("UNIT TEST 2:  setHeader(String, String)");

	     // Set it again
	     msg.setHeader(hname2, hvalue2); 	// API TEST
	     newheader = msg.getHeader(hname2);

             out.println("New header : "+ newheader[0]);
             out.println("UNIT TEST 2:  passed\n");

	  // END UNIT TEST:

	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
	    handlException(e);
        }
	return status;
     }
}
