/*
 * Copyright (c) 2006, 2021 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>reply()</strong> APIs. <p>
 *
 * Create a reply MimeMessage object and check that it has the appropriate
 * headers. <p>
 * api2test: public Message reply()  <p>
 *
 * how2test: Call API with various arguments, then verify that the reply message
 *           has the required recipients and subject.
 *           If so then this testcase passes, otherwise it fails.
 */

public class reply_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run() {
	

        try {
             Session session = createSession();
	     InternetAddress from = new InternetAddress("joe@example.com");
	     InternetAddress to = new InternetAddress("bob@example.com");
	     String subj = "test";

             MimeMessage msg = new MimeMessage(session);
	     msg.setFrom(from);
	     msg.setRecipient(Message.RecipientType.TO, to);
	     msg.setSubject(subj);
	     msg.setText("test");

	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1: reply(false)");

	     Message replyMsg = msg.reply(false);	// API TEST

	     Address[] addrs = replyMsg.getRecipients(Message.RecipientType.TO);
	     if (addrs != null && addrs.length == 1 && addrs[0].equals(from) &&
		    replyMsg.getSubject().equals("Re: " + subj) &&
		    msg.isSet(Flags.Flag.ANSWERED)) {
                out.fine("UNIT TEST 1: passed\n");
             } else {
                out.fine("UNIT TEST 1: FAILED\n");
                errors++;
             }

	  // END UNIT TEST 1:

	  // BEGIN UNIT TEST 2:
	     out.fine("UNIT TEST 2: reply(true)");

	     replyMsg = msg.reply(true);	// API TEST

	     addrs = replyMsg.getRecipients(Message.RecipientType.TO);
	     if (addrs != null && addrs.length == 2 &&
		    ((addrs[0].equals(from) && addrs[1].equals(to)) ||
		     (addrs[0].equals(to) && addrs[1].equals(from)))) {
                out.fine("UNIT TEST 2: passed\n");
             } else {
                out.fine("UNIT TEST 2: FAILED\n");
                errors++;
             }

	  // END UNIT TEST 2:

	  // BEGIN UNIT TEST 3:
	     out.fine("UNIT TEST 3: reply(false, false)");

             msg = new MimeMessage(session);
	     msg.setFrom(from);
	     msg.setRecipient(Message.RecipientType.TO, to);
	     msg.setSubject(subj);
	     msg.setText("test");
	     replyMsg = msg.reply(false, false);	// API TEST

	     addrs = replyMsg.getRecipients(Message.RecipientType.TO);
	     if (addrs != null && addrs.length == 1 && addrs[0].equals(from) &&
		    replyMsg.getSubject().equals("Re: " + subj) &&
		    !msg.isSet(Flags.Flag.ANSWERED)) {
                out.fine("UNIT TEST 3: passed\n");
             } else {
                out.fine("UNIT TEST 3: FAILED\n");
                errors++;
             }

	  // END UNIT TEST 3:
             checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
}
