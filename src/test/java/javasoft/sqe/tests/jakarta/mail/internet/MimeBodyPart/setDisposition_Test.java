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
 * This class tests the <strong>setDisposition()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the "Content-Disposition" header field of BodyPart. <p>
 * api2test: public void setDisposition(String)  <p>
 *
 * how2test: Call API with string arguments, then call getDisposition(), if the
 *	     set/get values are equal then testcase passes, otherwise it fails.
 */

public class setDisposition_Test extends MailTest {

    public static String[] setd = { "x-y-z","","0123456789abc@#$%^&*+=" };

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class MimeBodyPart: setDisposition()\n");

        try {
          // Create a MimeBodyPart object
	     MimeBodyPart bp = new MimeBodyPart();

             if( bp == null )
                 return Status.failed("Failed to create MimeBodyPart object!");

	  // BEGIN UNIT TEST 1:
             out.println("UNIT TEST 1: setDisposition(Part.INLINE)");
	     bp.setDisposition(Part.INLINE); 	// API TEST

             if( Part.INLINE.equals(bp.getDisposition()) )
                 out.println("UNIT TEST 1:  passed.\n");
             else {
                   out.println("UNIT TEST 1:  FAILED.\n");
                   errors++;
             }
          // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.println("UNIT TEST 2: setDisposition(Part.ATTACHMENT)");
             bp.setDisposition(Part.ATTACHMENT);        // API TEST

             if( Part.ATTACHMENT.equals(bp.getDisposition()) )
                 out.println("UNIT TEST 2:  passed.\n");
             else {
                   out.println("UNIT TEST 2:  FAILED.\n");
                   errors++;
             }
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
             out.println("UNIT TEST 3: setDisposition(null)");
             bp.setDisposition(null);        // API TEST

             if( bp.getDisposition() == null )
                 out.println("UNIT TEST 3:  passed.\n");
             else {
                   out.println("UNIT TEST 3:  FAILED.\n");
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
