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

package javasoft.sqe.tests.jakarta.mail.Transport;

/**
 * This class tests the <strong>isConnected()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *              Is this Transport currently connected.  <p>
 * api2test: public boolean isConnected()  <p>
 *
 * how2test: Call this API and check the returned boolean value.
 *           If its TRUE/FALSE then this testcase passes. <p>
 */

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

public class isConnected_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

	out.fine("\nTesting Transport class API => isConnected()\n");

	try {
           // Get a Session object
              Session session = Session.getInstance(properties, null);

           // Get a Transport object
              Transport transport = session.getTransport(transport_protocol);

           // Connect
              if( transport_host != null ) {
		  if( auth )
		      transport.connect(transport_host, user, password);
		  else
		      transport.connect(transport_host, null, null);
              } else
                  transport.connect();

	   // BEGIN UNIT TEST 1:
	      out.fine("UNIT TEST 1:  isConnected()");

	      if ( transport.isConnected() ) {		// API TEST
		   out.fine("Transport is open.");
	           out.fine("UNIT TEST 1: passed\n");
	      }
	   // END UNIT TEST 1:
           // BEGIN UNIT TEST 2:
              out.fine("UNIT TEST 2:  isConnected()");
	      transport.close();

              if ( ! transport.isConnected() ) {	// API TEST
		   out.fine("Transport is closed.");
                   out.fine("UNIT TEST 2: passed\n");
	      }
           // END UNIT TEST 2:

	      status = Status.passed("OKAY");
        } catch (Exception e) {
              handlException(e);
          }
	  return status;
    }
}
