/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
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
import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>requestPasswordAuthentication()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	Call back to the application to get the needed user name and password. <p>
 * api2test: public PasswordAuthentication requestPasswordAuthentication(InetAddress,int,String,String,String)  <p>
 *
 * how2test: First call setPasswordAuthentication() and then call this API with arguments,
 *	     check the value/type of returned object. If its a PasswordAuthentication then
 *	     this testcase passes. <p>
 */

class DummyAuthenticator extends Authenticator {
    PasswordAuthentication pa;
    public DummyAuthenticator(PasswordAuthentication p) {
        pa = p;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}

public class reqPasswdAuthen_Test extends MailTest {

    public static void main( String argv[] )
    {
        reqPasswdAuthen_Test test = new reqPasswdAuthen_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Session: requestPasswordAuthentication(InetAddress,int,String,String,String)\n");

        try {
	   // Initialize a new PasswordAuthentication
	      PasswordAuthentication pwa = new PasswordAuthentication(user,password);

	      if( pwa == null ) {
                  return Status.failed("Failed to initialize a PasswordAuthentication object!");
              }
           // Get Session object
              Session session = Session.getInstance(properties, new DummyAuthenticator(pwa));

	      if( session == null ) {
                  return Status.failed("Failed to get a Session object!");
              }
           // Create a URLName object
              URLName url = new URLName(protocol,host,0,mailbox,null,null);

              if( url == null ) {
                  return Status.failed("Failed to create a URLName object!");
	      }
	   // Set password authentication
              session.setPasswordAuthentication(url, pwa);

           // BEGIN UNIT TEST 1:
              out.println("UNIT TEST 1:  requestPasswordAuthentication()");

              PasswordAuthentication rpwa = session.requestPasswordAuthentication(null,0,protocol,"", user); // API TEST

              if( rpwa == pwa ) {
		  out.println("User name is "+ rpwa.getUserName());
		  out.println("User password is "+ rpwa.getPassword());
                  out.println("UNIT TEST 1: passed\n");
	      } else {
			out.println("UNIT TEST 1: FAILED\n");
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
