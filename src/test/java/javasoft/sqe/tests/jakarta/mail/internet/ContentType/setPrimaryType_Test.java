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

package javasoft.sqe.tests.jakarta.mail.internet.ContentType;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setPrimaryType()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *		Set the primary type string. <p>
 * api2test: public void setPrimaryType(String)  <p>
 *
 * how2test: Call API with string arg, verify by calling getPrimaryType(),
 *	     If both values are equal then testcase passes, otherwise it fails.
 */

public class setPrimaryType_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	 

        out.fine("\nTesting class ContentType: setPrimaryType()\n");

        try {
	   // Create ContentType object
              ContentType ct = new ContentType(pattern);

              if( ct == null )
		  return Status.failed("Failed to create ContentType object!");

           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1: setPrimaryType(String)");

	      String oldvalue = ct.getPrimaryType();
	      ct.setPrimaryType(oldvalue);	// API TEST
              String primeType = ct.getPrimaryType();

              if( primeType.equals(oldvalue) ) {
		  out.fine("Primary type is "+primeType);
                  out.fine("UNIT TEST 1: passed.\n");
              } else {
                    out.fine("UNIT TEST 1: FAILED.\n");
                    errors++;
              }
           // END UNIT TEST 1:

              checkStatus();
        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
