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
 * This class tests the <strong>setDisposition()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the "Content-Disposition" header field of Message. <p>
 * api2test: public void setDisposition(String disposition)  <p>
 *
 * how2test: Call API, with various string arguments, then call getDisposition() api,
 *	     if operation is successfull then this testcase passes, otherwise it fails. <p>
 *
 *	     If the disposition is NONE, any existing "Content-Disposition" header field
 *	     is removed.
 */

public class setDisposition_Test extends MailTest {

    private static String distr1 = "Quickbrownfoxjumpedoverthelazycow";
    private static String distr2 = "SDWEDD0F1R4YB38749X";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);
        out.println("\nTesting class MimeMessage: setDisposition(string)\n");

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
             out.println("UNIT TEST 1:  setDisposition("+distr1+")");

          // set the message's disposition header
             msg.setDisposition(distr1);                   // API TEST

             if( distr1.equals(msg.getDisposition()) )
                 out.println("UNIT TEST 1:  passed\n");
             else {
                   out.println("UNIT TEST 1:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.println("UNIT TEST 2:  setDisposition("+distr2+")");

          // set the message's disposition header
             msg.setDisposition(distr2);                 // API TEST

             if( distr2.equals(msg.getDisposition()) )
                 out.println("UNIT TEST 2:  passed\n");
             else {
                   out.println("UNIT TEST 2:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 2:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
