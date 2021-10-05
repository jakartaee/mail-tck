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
 * This class tests the <strong>equals()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Compares two URLNames objects. <p>
 * api2test: public boolean equals(Object)  <p>
 *
 * how2test: Call this API with URLName argument, check the value of
 *	     returned. If its TRUE/FALSE then this testcase passes.
 */

public class equals_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class URLName: equals(Object)\n");

        try {
	   // Create a URLName object
              URLName urlname = new URLName(protocol, host, 0, mailbox, user, password);

              if( urlname == null )
                  return Status.failed(" Failed to create a URLName object!");

           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1:  equals(Object)");
              boolean isEq = urlname.equals(urlname);	// API TEST

              if( isEq || !isEq )
                  out.fine("UNIT TEST 1: passed");
           // END UNIT TEST 1:

	      status = Status.passed("OKAY");
        } catch ( Exception e ) {
	      handlException(e);
        }
	return status;
     }
}
