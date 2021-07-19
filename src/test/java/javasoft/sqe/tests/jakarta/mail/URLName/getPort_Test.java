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

package javasoft.sqe.tests.jakarta.mail.URLName;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getPort()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Returns the port number of this URLName. <p>
 * api2test: public int getPort()  <p>
 *
 * how2test: Call this API, check the value/type of returned object.
 *	     If its an integer then this testcase passes.
 */

public class getPort_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

        out.println("\nTesting class URLName: getPort()\n");

        try {
	   // Create a URLName object
              URLName urlname = new URLName(protocol, host, 0, mailbox, user, password);

              if( urlname == null )
                  return Status.failed("Failed to create a URLName object!");

           // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1:  getPort()");
              int portid = urlname.getPort(); 	// API TEST

              if( portid > -1 ) {
		  out.println("Port number is "+ portid);
                  out.println("UNIT TEST 1: passed");
	      } else {
		      out.println("UNIT TEST 1: FAILED.\n");
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
