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
 * This class tests the <strong>getPasswordAuthentication()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return any saved PasswordAuthentication for this store/transport) URLName. <p>
 * api2test: public PasswordAuthentication getPasswordAuthentication(URLName)  <p>
 *
 * how2test: Call this API, check the value/type of returned object.
 *	     If its a PasswordAuthentication then this testcase passes.
 */

public class getPasswdAuthen_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Session: getPasswordAuthentication(URLName)\n");

        try {
           // Get Session object
              Session session = Session.getInstance(properties, null);

	   // Create a URLName object
              URLName url = new URLName(protocol,host,0,mailbox,user,password);

              if( url == null )
                  return Status.failed("Failed to create a URLName object!");

           // Initialize a new PasswordAuthentication
              PasswordAuthentication pwa = new PasswordAuthentication(user, password);

              if( pwa == null )
                  return Status.failed("Failed to initialize a PasswordAuthentication object!");

	   // Set password authentication
              session.setPasswordAuthentication(url, pwa);

           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1:  getPasswordAuthentication(URLName)");

              PasswordAuthentication passauth = session.getPasswordAuthentication(url);  // API TEST

              if( passauth != null ) {
		  out.fine("User name is "+ passauth.getUserName());
		  out.fine("User password is "+ passauth.getPassword());
                  out.fine("UNIT TEST 1: passed");
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
