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

package javasoft.sqe.tests.jakarta.mail.Provider;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getType()</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the object returned.  <p>
 *		Returns the type of this Provider. <p>
 * api2test: public String getType()  <p>
 *
 * how2test: Call this API, check that it returns either STORE|TRANSPORT type.
 *	     If it does then this testcase passes, otherwise it fails.
 */

public class getType_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

	out.fine("\nTesting class Provider: getType()\n");

        try {
	   // Get Session object
              Session session = Session.getInstance(properties, null);
	      Provider prov1 = session.getProvider(protocol);
	      Provider prov2 = session.getProvider(transport_protocol);

              if( prov1 == null || prov2 == null ) {
                  return Status.failed("Failed to get Provider object!");
              }
           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1: getType()");

	      Provider.Type provType1 = prov1.getType();	// API TEST
	      Provider.Type provType2 = prov2.getType();	// API TEST

              if( provType1 == Provider.Type.STORE && provType2 == Provider.Type.TRANSPORT )
                  out.fine("UNIT TEST 1:  passed\n");
              else {
		    out.fine("UNIT TEST 2:  FAILED\n");
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
