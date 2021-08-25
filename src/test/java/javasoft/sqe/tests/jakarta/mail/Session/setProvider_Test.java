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
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setProvider()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	Set the passed Provider to be the default implementation for the protocol. <p>
 * api2test: public void setProvider(Provider)  <p>
 *
 * how2test: Call this API with a Provider argument, then check by calling getProvider()
 *	     If both values are same then this testcase passes.
 */

public class setProvider_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Session: setProvider(URLName, Provider)\n");

        try {
           // Get Session object
              Session session = Session.getInstance(properties, null);

	   // Initialize a new Provider
	      Provider prov1 = session.getProvider(protocol);

              if( prov1 == null )
                  return Status.failed("Failed to initialize a Provider object!");

           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1:  setProvider(Provider)");

              session.setProvider(prov1);  // API TEST
	      Provider prov2 = session.getProvider(protocol);

              if( prov1.equals(prov2) )
                  out.fine("UNIT TEST 1: passed");
	      else {
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
