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
 * This class tests the <strong>getSession()</strong> API.
 * It does this by invoking the test api and then checking
 * that the returned object is the same object used to create the message.<p>
 *
 *		Get the session of this message. <p>
 * api2test: public String getSession()  <p>
 *
 * how2test: Call this API on given message object, verify that it returns
 *	     the Session object used to create this message.
 *	     If this operation is successfull then this
 *	     testcase passes, otherwise it fails. <p>
 *
 *	     Returns the Session object used when the message was created.
 *           Returns null if no Session is available.
 */

public class getSession_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

        out.println("\nTesting class Message: getSession()\n");

        try {
	    Session s = createSession();

	    // BEGIN UNIT TEST 1:
	       Message msg = new MimeMessage(s);
	       out.println("UNIT TEST 1:  getSession()");

	       Session sess = msg.getSession();	// API TEST

	       if (sess == s) {
                   out.println("UNIT TEST 1:  passed\n");
	       } else {
		       out.println("got Session: " + sess);
		       out.println("UNIT TEST 1:  Failed\n");
		       errors++;
	       }
	    // END UNIT TEST 1:

	    // BEGIN UNIT TEST 2:
	       msg = new MimeMessage((Session)null);
	       out.println("UNIT TEST 2:  getSession() null");

	       sess = msg.getSession();	// API TEST

	       if (sess == null) {
                   out.println("UNIT TEST 2:  passed\n");
	       } else {
		       out.println("got Session: " + sess);
		       out.println("UNIT TEST 2:  Failed\n");
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
