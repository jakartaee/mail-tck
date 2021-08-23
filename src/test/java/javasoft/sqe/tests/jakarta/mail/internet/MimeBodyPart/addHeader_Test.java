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
 * This class tests the <strong>addHeader()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *          Add value to the existing values for this header_name. <p>
 * api2test: public void addHeader(String, String)  <p>
 *
 * how2test: Call API with various string arguments, then call 'getHeader()'
 *           api to check that API under test did its jobs as expected. If it did
 *           then the testcase is passing, otherwsie it  fails.
 */

public class addHeader_Test extends MailTest {

    public static String name1 = "Subject";
    public static String value1 = "Testing addHeader() APIs";
    public static String name2 = "Nonsense";
    public static String value2 = "This~!@9#0$is%^2&*1+a=?>.<,:;test";
    public static String name3 = "EmptyField";
    public static String value3 = "";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeBodyPart: addHeader(String, String)\n");

        try {
	   // Create MimeBodyPart object
	      MimeBodyPart mbp = new MimeBodyPart();

	      if( mbp == null )
		  return Status.failed("Failed to create a MimeBodyPart object");

	   // BEGIN UNIT TEST 1:
	      out.fine("UNIT TEST 1: addHeader("+name1+","+value1+")");

	      mbp.addHeader(name1, value1);	// API TEST
	      String[] value = mbp.getHeader(name1);

	      if( value1.equals(value[0]) )
		  out.fine("UNIT TEST 1: passed\n");
	      else {
		    out.fine("UNIT TEST 1: FAILED\n");
		    errors++;
	      }
	   // END UNIT TEST 1:
           // BEGIN UNIT TEST 2:
              out.fine("UNIT TEST 2: addHeader("+name2+","+value2+")");

              mbp.addHeader(name2, value2);     // API TEST
	      value = mbp.getHeader(name2);

              if( value2.equals(value[0]) )
                  out.fine("UNIT TEST 2: passed\n");              
              else {
                    out.fine("UNIT TEST 2: FAILED\n");
                    errors++;
              }
           // END UNIT TEST 2:
           // BEGIN UNIT TEST 3:
              out.fine("UNIT TEST 3: addHeader("+name3+","+value3+")");

              mbp.addHeader(name3, value3);     // API TEST
              value = mbp.getHeader(name3);

              if( value3.equals(value[0]) )
                  out.fine("UNIT TEST 3: passed\n");
              else {
                    out.fine("UNIT TEST 3: FAILED\n");
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
