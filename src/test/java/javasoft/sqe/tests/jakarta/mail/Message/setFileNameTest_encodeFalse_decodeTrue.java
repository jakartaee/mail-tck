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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setFileName()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * Set the filename associated with this message, if possible.<p>
 * api2test: public void setFileName(String filename)  
 * The API will be tested with varying combinations for the values of 
 * mail.mime.encodefilename and mail.mime.decodefilename system properties <p>
 *
 * how2test: Call this API, with 'filename' string argument, then call 
 *           getFileName() api, verify that set value is the same as get value. 
 *           If this is true then the testcase passes, otherwise it fails.
 */

public class setFileNameTest_encodeFalse_decodeTrue extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        try {
             // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);
             String fileName = "??-a_german_character";
             
             out.fine("\nTesting class Message: setFileName(String)\n");
             
	     // BEGIN UNIT TEST:
	     // Set body part filename
             // test with value set to true (one by one) for
             // mail.mime.encodefilename and mail.mime.decodefilename
             // system properties.
             out.fine("UNIT TEST 1: setFileName(String) with default " +
                                       "mail.mime.encodefilename = FALSE and " +
                                       "mail.mime.decodefilename = TRUE");             
             System.setProperty("mail.mime.encodefilename", "false");
             System.setProperty("mail.mime.decodefilename", "true");

             msg.setFileName("=?ISO646-US?Q?=3F=3F-a=5Fgerman=5Fcharacter?=");
           
             if (fileName.equals(msg.getFileName()))
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
