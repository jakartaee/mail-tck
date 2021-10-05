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
 * This class tests the <strong>updateHeaders()</strong> API. <p>
 *
 * api2test: protected void updateHeaders()  <p>
 *
 * how2test: Create a MimeMessage object and verify that the Mime-Version and
 *	     Date headers are set.
 *           If so then this testcase passes, otherwise it fails.
 */

public class updateHeaders_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run() {
	

        try {
          // Create a custom MimeMessage object with custom message ID algorithm
             Session session = Session.getInstance(properties, null);

	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1: MimeMessage.updateHeaders()");

             MimeMessage msg = new MimeMessage(session); 
	     msg.setSubject("test");
	     msg.setText("test");
	     msg.saveChanges();
	     if (msg.getHeader("Mime-Version") != null &&
		    msg.getHeader("Date") != null)
                out.fine("UNIT TEST 1: passed\n");
             else {
                errors++;
                out.fine("UNIT TEST 1: FAILED\n");
             }
	  // END UNIT TEST:

	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 2: MimeMessage.updateHeaders date set()");

             MimeMessage msg2 = new MimeMessage(session); 
	     msg.setSubject("test");
	     msg.setText("test");
	     Date d = new Date(1472598000000L);
	     msg.setSentDate(d);
	     msg.saveChanges();
	     if (msg.getHeader("Date", null) != null &&
		 msg.getSentDate().equals(d))
                out.fine("UNIT TEST 2: passed\n");
             else {
                errors++;
                out.fine("UNIT TEST 2: FAILED\n");
             }
	  // END UNIT TEST:

             checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
}
