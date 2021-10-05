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
 * This class tests the <strong>getTransport()</strong> API.
 * It does this by passing various valid input values and then testing the type
 * of the object returned. <p>
 *
 *	Get a Transport object that implements this user's desired Transport protcol.<p>
 * api2test: public Transport getTransport() <p>
 *
 *	Get an instance of the transport specified in the Provider. <p>
 * api2test: public Transport getTransport(Provider)  <p>
 *
 *	Get a Transport object that can transport a Message to the specified address type.<p>
 * api2test: public Transport getTransport(Address)  <p>
 *
 *	Get a Transport object that implements the specified protocol. <p>
 * api2test: public Transport getTransport(String)  <p>
 *
 *	Get a Transport object for the given URLName. <p>
 * api2test: public Transport getTransport(URLName)  <p>
 *
 * how2test: Call these APIs with { null|String|Address|Provider|URLName } parameter values.
 *	     If it returns a object of type Transport then thes test passes. If an appropriate <p>
 *	     Transport object cannot be obtained, null is returned. Use smtp, imap, pop3
 */

public class getTransport_Test extends MailTest {

    private Transport trans = null;

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Session: getTransport()\n");

        try {
	     // Create some properties object
	     Properties props = new Properties();
	     if (transport_host != null)
		 props.put("mail."+ transport_protocol +".host", transport_host);
             props.put("mail.transport.protocol", transport_protocol);

	     // Get the default Session
	     session = Session.getInstance(props, null);

	     if( session == null ) {
                 return Status.failed("Warning: failed to get Session object!\n");
             }
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1: getTransport()");
             trans = session.getTransport();        // API TEST

             if( trans != null )
                 out.fine("UNIT TEST 1:  passed\n");
             else {
                   out.fine("UNIT TEST 1:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
	     out.fine("UNIT TEST 2: getTransport("+ transport_protocol +")");
             session = Session.getInstance(properties, null);

	     if( session == null ) {
                 return Status.failed("Warning: failed to get Session object!\n");
             }
	     // get a Transport object
             trans = session.getTransport(transport_protocol);        // API TEST

             if( trans != null )
                 out.fine("UNIT TEST 2:  passed\n");
             else {
                   out.fine("UNIT TEST 2:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
	     out.fine("UNIT TEST 3: getTransport("+ transport_protocol +")");

	     // get InternetAddress and Transport objects
	     InternetAddress addr = new InternetAddress(to);	// get address for 'to'
             trans = session.getTransport(addr);	// API TEST

             if( trans != null )
                 out.fine("UNIT TEST 3:  passed\n");
             else {
                   out.fine("UNIT TEST 3:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 3:
          // BEGIN UNIT TEST 4:
             out.fine("UNIT TEST 4: getTransport(Provider)");

             // get provider for user specified protocol
             Provider prov = session.getProvider(transport_protocol);
             trans = session.getTransport(prov);  // API TEST

             if( trans != null )
                 out.fine("UNIT TEST 4:  passed\n");
             else {
                   out.fine("UNIT TEST 4:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 4:
          // BEGIN UNIT TEST 5:
             out.fine("UNIT TEST 5: getTransport(URLName)");

             // create a URLName object
	     URLName urlname = new URLName(transport_protocol,transport_host,0,mailbox,user,password);
             trans = session.getTransport(urlname);  // API TEST

             if( trans != null )
                 out.fine("UNIT TEST 5:  passed\n");
             else {
                   out.fine("UNIT TEST 5:  FAILED\n");
                   errors++;
             }
          // END UNIT TEST 5:
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
