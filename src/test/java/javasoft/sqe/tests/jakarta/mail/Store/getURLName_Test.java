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

package javasoft.sqe.tests.jakarta.mail.Store;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getUrl()</strong> API. It returns a URL 
 * representing a specified store.	<p>
 *
 *		Return a URL representing this Store.  <p>
 * api2test: public URL getURLName()	<p>
 *
 * how2test: Call the 'getURLName()' API. If it returns an object <p>
 *	     of type URLName. Then test passes otherwise it fails.
 */

public class getURLName_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

        out.println("\nTesting class Store: getUrl()\n");

        try {
          // Connect to host server
             Store store_1 = connect2host(protocol, host, user, password);

          // BEGIN UNIT TEST 1:
             out.println("UNIT TEST 1: getURLName()");

          // Get a URL for the 'root' default namespace
             URLName url_1 = store_1.getURLName();      // API TEST

             if ( url_1 == null ) {
                  out.println("UNIT TEST 1:  FAILED\n");
		  return Status.failed("Invalid folder object!");
             } else if ( url_1 != null && ( url_1 instanceof URLName ))
                        out.println("UNIT TEST 1:  passed\n");

             store_1.close();
          // END UNIT TEST 1:
             status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
