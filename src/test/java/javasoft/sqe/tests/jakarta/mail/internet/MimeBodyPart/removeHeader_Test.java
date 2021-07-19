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
 * This class tests the <strong>removeHeader()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Remove all headers with name. <p>
 * api2test: public void removeHeader(String name)  <p>
 *
 * how2test: Call API with different header names. If this operation is
 *	     successfull then testcase passes otherwise it fails.
 */

public class removeHeader_Test extends MailTest {

    public static String name1 = "Subject";
    public static String value1 = "Testing addHeader() APIs";
    public static String name2 = "Nonsense";
    public static String value2 = "This~!@9#0$is%^2&*1+a=?>.<,:;test";
    public static String name3 = "EmptyField";
    public static String value3 = "";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class MimeBodyPart: removeHeader(String)\n");

        try {
	   // Create MimeBodyPart object
	      MimeBodyPart mbp = new MimeBodyPart();

	      if( mbp == null )
		  return Status.failed("Failed to create a MimeBodyPart object");

	   // BEGIN UNIT TEST 1:
	      out.println("UNIT TEST 1: removeHeader("+name1+")");

	      mbp.addHeader(name1, value1);
	      mbp.removeHeader(name1);		// API TEST

	      out.println("UNIT TEST 1: passed\n");
	   // END UNIT TEST 1:
           // BEGIN UNIT TEST 2:
              out.println("UNIT TEST 2: removeHeader("+name2+")");

              mbp.addHeader(name2, value2);
	      mbp.removeHeader(name2);		// API TEST

              out.println("UNIT TEST 2: passed\n");
           // END UNIT TEST 2:
           // BEGIN UNIT TEST 3:
              out.println("UNIT TEST 3: removeHeader("+name3+")");

              mbp.addHeader(name3, value3);
	      mbp.removeHeader(name3);		// API TEST

              out.println("UNIT TEST 3: passed\n");
           // END UNIT TEST 3:

              checkStatus();
        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
