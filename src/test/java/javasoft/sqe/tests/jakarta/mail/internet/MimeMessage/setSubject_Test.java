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
 * This class tests the <strong>setSubject()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the 'Subject' header field. <p>
 * api2test: public void setSubject(String)  <p>
 *		Set the 'Subject' and 'character set' header fields. <p>
 * api2test: public void setSubject(String, String)  <p>
 *
 * how2test: Call APIs, then check if the string value returned by getSubject()
 *	     is the same as that set by setSubject() method. If it is then this
 *	     testcase passes, otherwise it fails.
 */

public class setSubject_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeMessage: setSubject(String, String)\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.warning("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
          // BEGIN UNIT TEST:
             out.fine("UNIT TEST 1: setSubject(String, String)");

	     String substr = "hot ice cream";

	     msg.setSubject(substr);			// API TEST
	     msg.setSubject(substr, "us-ascii" );	// API TEST

	     if ( substr.equals( msg.getSubject() ))
		  out.fine("UNIT TEST 1: passed\n");
	     else {
		   out.fine("UNIT TEST 1: FAILED\n");
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
