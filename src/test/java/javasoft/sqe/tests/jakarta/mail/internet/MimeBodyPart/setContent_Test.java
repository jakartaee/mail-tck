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

package javasoft.sqe.tests.jakarta.mail.internet.MimeBodyPart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setContent()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the content as a Multipart object. <p>
 * api2test: public void setContent(Multipart)  <p>
 *
 * how2test: Call API, then check the type of java object returned. We test <p>
 *	     for 'string','multipart', and non-null. The testcases passes if the api <p>
 *	     returns any one of these object types, otherwise we mark as it failing.
 */

public class setContent_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeBodyPart: setContent()\n");

        try {
          // Create a MimeBodyPart object
	     MimeBodyPart bp = new MimeBodyPart();

             if( bp == null )
                 return Status.failed("Failed to create MimeBodyPart object!");

          // Create a MultiPart object
             Multipart mp = new MimeMultipart();

             if( mp == null )
                 return Status.failed("Failed to create Multipart object!");

	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1:  setContent(Multipart)");

	     // Set the content
	     bp.setContent(mp); 	// API TEST

	     out.fine("UNIT TEST 1:  passed\n");
	  // END UNIT TEST:
          // BEGIN UNIT TEST:
             out.fine("UNIT TEST 2:  setContent(Object, String)");
	     Object ob = bp.getContent();

             // Set the content
             bp.setContent(ob, "application/x-foobar");         // API TEST

             out.fine("UNIT TEST 2:  passed\n");
          // END UNIT TEST:

	     checkStatus();
        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
