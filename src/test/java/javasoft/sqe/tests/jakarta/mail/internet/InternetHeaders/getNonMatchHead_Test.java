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

package javasoft.sqe.tests.jakarta.mail.internet.InternetHeaders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getNonMatchingHeaders()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return all non-matching headers as an Enumeration of Header objects.<p>
 * api2test: public Enumeration getNonMatchingHeaders(String[])  <p>
 *
 * how2test: Call API with array of strings that you want to filter out. Then write
 *	     the enumerated list returned of non-matching header strings. If operation
 *	     is successfull then testcase passes, otherwise it fails.
 */

public class getNonMatchHead_Test extends MailTest {

    public static String[] headerlist = { "Return-Path","Message-ID","Received",
                                           "X-Mailer","Content-Transfer-Encoding",
                                           "Content-MD5","Organization","Precedence"
                                        };

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {

	

        out.fine("\nTesting class InternetHeaders: getNonMatchingHeaders(String[])\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object.");
             }
             folder.open(Folder.READ_ONLY);

             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
          // Get the first message
             MimeMessage msg = (MimeMessage)folder.getMessage(1);

             if( msg == null ) {
                 return Status.failed("Warning: Failed to get message number: 1");
             }
          // Get a ByteArrayInputStream object
             ByteArrayInputStream bis = createInputStream(msg);

          // create InternetHeader object
             InternetHeaders ih = new InternetHeaders(bis);

             if( ih == null ) {
                 return Status.failed("WARNING: Failed to create InternetHeaders object!");
             }
          // BEGIN UNIT TEST:
	     // Get non-matching headers for given message(s)
	     out.fine("UNIT TEST 1:  getMatchingHeaders(String[])");

	     Enumeration nonMatcheaders = ih.getNonMatchingHeaders(headerlist);   // API TEST

	     while ( nonMatcheaders.hasMoreElements() ) {
		     Header headers = (Header)nonMatcheaders.nextElement();
		     out.fine(headers.getName());
	     }
	     out.fine("UNIT TEST 1:  passed\n");
	  // END UNIT TEST:

	     store.close();
	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
