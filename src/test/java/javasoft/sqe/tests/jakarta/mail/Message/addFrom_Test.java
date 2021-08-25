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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addFrom()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.  <p>
 *
 *		Add these addresses to the existing "From" attribute <p>
 * api2test: public void addFrom(Address addresses[])  <p>
 *
 * how2test: Call this API with various address arguments. If this operation 
 *	     is successfull then the testcase passes, otherwise it fails. <p>
 *	     We also call getFrom() after we have called the above API to see
 *	     if 'addFrom()' did its jobs successfully!
 */

public class addFrom_Test extends MailTest {

    public static String FROM1 = "tester@eng.sun.com";
    public static String FROM2 = "javatest@aol.com";

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: addFrom(Address[])\n");

        try {
          // Create a MimeMessage object
	     Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.warning("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
	     // create address object

	     Address[] from;
	     InternetAddress addr1 = new InternetAddress(FROM1);
	     InternetAddress addr2 = new InternetAddress(FROM2);

	     InternetAddress addrs[] = new InternetAddress[2];
             addrs[0] = addr1;
	     addrs[1] = addr2;

	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1:  addFrom(Address[])");

	  // add message recipients

	     msg.addFrom(addrs);	// API TEST
	     from = msg.getFrom();
	     StringBuilder sb = new StringBuilder();
	     sb.append("addFrom(from) :=> '");

	     if ( from != null && from.length != 0 ) {
                  for ( int j = 0; j < from.length; j++ )
			if ( j > 0 )
			    sb.append(", " + from[j]);
			else
			    sb.append("" + from[j]);

                  sb.append("')");
             } else
                 sb.append("empty field')");
	     out.fine(sb.toString());
             out.fine("UNIT TEST 1:  passed\n");
	  // END UNIT TEST:

	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
