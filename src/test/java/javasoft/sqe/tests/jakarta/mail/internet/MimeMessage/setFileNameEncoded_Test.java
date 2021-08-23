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
 * This class tests the <strong>setFileName()</strong> API.
 * It tests that a non-ASCII filename is actually being encoded by default.
 *
 * how2test: Call this API, with a non-ASCII 'filename' string argument, then
 *           examine the Content-Disposition header to ensure that it was
 *           encoded.
 *           If this is true then the testcase passes, otherwise it fails.
 */

public class setFileNameEncoded_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);
             
	     // need to ensure that the filename can be encoded (by default)
	     // for unit test below.

	     System.setProperty("mail.mime.charset", "utf-8");
             
             out.fine("\nTesting class MimeMessage: setFileName(String)\n");
             
	  // BEGIN UNIT TEST:
	     // Set message filename
	     out.fine("UNIT TEST 1:  msg.setFileName(String)");

 	     String fileName="\u00a1";
	     msg.setFileName(fileName);	// API TEST
	     msg.saveChanges();
	     out.fine("setFileName("+ fileName +")");

	     String cd = msg.getHeader("Content-Disposition", null);
	     out.fine("Content-Disposition associated with this message: "+ cd);
             if (cd.indexOf("filename*=utf-8''%C2%A1") >= 0)
                 out.fine("UNIT TEST 1:  passed\n");
             else {
                   out.fine("UNIT TEST 1:  FAILED\n");
                   errors++;
             }
            // END UNIT TEST:
             checkStatus();
        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
}
