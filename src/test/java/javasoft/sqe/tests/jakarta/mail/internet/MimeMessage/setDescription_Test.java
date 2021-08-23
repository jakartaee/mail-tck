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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setDescription()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the description of part. <p>
 * api2test: public void setDescription(String)  <p>
 *
 * how2test: Call API with various string arguments, then call getDescription(), if
 *	     operation is successfull then this testcase passes, otherwise it fails.
 */

public class setDescription_Test extends MailTest {

    public static String destr1 = "Quick brown fox jumped over the lazy cow";
    public static String destr2 = "SDWED	@#$5$T^&   =+!~D0F1R4%^$%YB38749X?>";
    public static String destr3 = "";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	
        out.fine("\nTesting class MimeMessage: setDescription(String)\n");

        try {
	  // Create a Session object
             Session session = Session.getInstance(properties, null);

	     if( session == null ) {
                 return Status.failed("Warning: Failed to create Session object!");
             }
	  // Create a MimeMessage object
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 return Status.failed("Warning: Failed to create MimeMessage object!");
             }
          // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1:  setDescription("+destr1+")");

	  // set the message's description header
	     msg.setDescription(destr1);		   // API TEST

             if( destr1.equals(msg.getDescription()) )
                 out.fine("UNIT TEST 1:  passed\n");
             else {
                   out.fine("UNIT TEST 1:  FAILED\n");
		   errors++;
             }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.fine("UNIT TEST 2:  setDescription("+destr2+")");

          // set the message's description header
             msg.setDescription(destr2);                 // API TEST

             if( destr2.equals(msg.getDescription()) )
                 out.fine("UNIT TEST 2:  passed\n");
             else {
                   out.fine("UNIT TEST 2:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
             out.fine("UNIT TEST 3:  setDescription("+destr3+")");

          // set the message's description header
             msg.setDescription(destr3);                 // API TEST

             if( destr3.equals(msg.getDescription()) )
                 out.fine("UNIT TEST 3:  passed\n");
             else {
                   out.fine("UNIT TEST 3:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 3:
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
