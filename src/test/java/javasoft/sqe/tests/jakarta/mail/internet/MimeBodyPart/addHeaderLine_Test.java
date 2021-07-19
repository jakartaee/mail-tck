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
 * This class tests the <strong>addHeaderLine()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *          Add a header-line to BodyPart. <p>
 * api2test: public void addHeaderLine(String)  <p>
 *
 * how2test: Call API with various string arguments, then call 'getAllHeaderLines()'
 *           api to check that API under test did its jobs as expected. If it did
 *           then the testcase is passing, otherwsie it  fails.
 */

public class addHeaderLine_Test extends MailTest {

    public static String name1 = "Subject: Testing addHeaderLine() APIs";
    public static String name2 = "Nonsense: This~!@9#0$is%^2&*1+a=?>.<,:;test";
    public static String name3 = "X-Mailer: 1.0.1";
    public static String name4 = "Emptyfield:";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class MimeBodyPart: addHeaderLine(String)\n");

        try {
	   // Create MimeBodyPart object
	      MimeBodyPart mbp = new MimeBodyPart();

	      if( mbp == null )
		  return Status.failed("Failed to create a MimeBodyPart object");

	   // BEGIN UNIT TEST:
	      out.println("UNIT TEST 1: addHeaderLine("+name1+")");
	      out.println("		addHeaderLine("+name2+")");
	      out.println("		addHeaderLine("+name3+")");
	      out.println("		addHeaderLine("+name4+")\n");

	      mbp.addHeaderLine(name1);  	// API TEST
	      mbp.addHeaderLine(name2);         // API TEST
	      mbp.addHeaderLine(name3);         // API TEST
	      mbp.addHeaderLine(name4);		// API TEST

              Enumeration headline = mbp.getAllHeaderLines();

              while( headline.hasMoreElements() ) {
                     String headers = (String)headline.nextElement();
                     out.println(headers);
              }
              out.println("\nUNIT TEST 1: passed\n");
	   // END UNIT TEST 1:
              checkStatus();

        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
