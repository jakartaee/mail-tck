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

package javasoft.sqe.tests.jakarta.mail.Session;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getProvider()</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the object returned.  <p>
 *		Returns the default Provider for the protocol specified. <p>
 * api2test: public Provider getProvider(String)  <p>
 *
 * how2test: Test getting a closed Folder object for the given URL. Pass a valid URL <p>
 *	     object, if this API returns a valid Folder object then the test passes.
 */

public class getProvider_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

	out.println("\nTesting class Session: getProvider(String)\n");

        try {
          // BEGIN UNIT TEST 1:
	     // Get Session object
             Session session = Session.getInstance(properties, null);
             out.println("UNIT TEST 1: getProvider("+ protocol +")");

             Provider provide = session.getProvider(protocol); // API TEST

             if( provide != null )
                 out.println("UNIT TEST 1:  passed\n");
             else {
		    out.println("UNIT TEST 1:  FAILED\n");
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
