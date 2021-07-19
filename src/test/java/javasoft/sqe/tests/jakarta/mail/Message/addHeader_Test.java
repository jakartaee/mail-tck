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
 * This class tests the <strong>addHeader()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Add this value to the existing values for this header_name. <p>
 * api2test: public void addHeader(String, String)  <p>
 *
 * how2test: Call this API with various string arguments, then call 'getHeader()' api
 *	     to check that API under test did its jobs as expected. If it did then
 *	     the testcase is passing, otherwsie it  fails.
 */

public class addHeader_Test extends MailTest {

    public static String name1 = "JavaStation";
    public static String value1 = "     this is a test! ";
    public static String name2 = "Nonsense";
    public static String value2 = "~!@#0$%^2&*+=?>.<,:;";
    public static int thistest;
    public String[] header;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

        out.println("\nTesting class Message: addHeader(String, String)\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.println("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
	  // BEGIN UNIT TEST:
	     // add message header(s)
	     out.println("UNIT TEST 1:  addHeader(String, String)");

             msg.addHeader(name1, value1);		// API TEST
	     header = msg.getHeader(name1);

	     if( header != null )
                 out.println("addHeader(..) :=> '" + header[0] + "'");
	     else {
		   out.println("addHeader(..) :=> 'empty field'");
		   thistest++;
	     }
             msg.addHeader(name2, value2);		// API TEST
             header = msg.getHeader(name2);

             if( header != null )
                 out.println("addHeader(..) :=> '" + header[0] + "'");
             else {
                   out.println("addHeader(..) :=> 'empty field'");
		   thistest++;
	     }

	     if( thistest == 0 )
		 out.println("UNIT TEST 1:  passed\n");
	     else
		 out.println("UNIT TEST 1:  FAILED\n");

	     errors += thistest;
	  // END UNIT TEST:

	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
