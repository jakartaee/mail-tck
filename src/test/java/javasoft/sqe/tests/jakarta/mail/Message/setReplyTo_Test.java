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
 * This class tests the <strong>setReplyTo()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the addresses to which replies should be directed. <p>
 * api2test: public void setReplyTo(Address[])  <p>
 *
 * how2test: Call this API with address arguments, then call getReply, if this operation
 *	     is successfull then the testcase passes, otherwise it fails.
 */

public class setReplyTo_Test extends MailTest {

    public static String TO = "tester@aol.com";

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    
	TO = to;

        out.fine("\nTesting class Message: setReplyTo(Address[])\n");

        try {
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

	     if( msg == null ) {
		 return Status.failed("Warning: Failed to create a Message object.");
	     }
             InternetAddress addr = new InternetAddress(TO);
             InternetAddress addrs[] = new InternetAddress[1];
             addrs[0] = addr;

          // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1:  setReplyTo(Address[])");

	  // set whom the message is from
	     msg.setReplyTo(addrs);	// API TEST
	     Address[] replyto = msg.getReplyTo();

	     if(replyto[0].toString().equals(TO)) {
                out.fine("UNIT TEST 1:  passed\n");
	     }
	  // END UNIT TEST:

	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
