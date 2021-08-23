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
 * This class tests the <strong>getNonMatchingHeaderLines()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Get non-matching header lines as an Enumeration of Strings. <p>
 * api2test: public Enumeration getNonMatchingHeaderLines(String[])  <p>
 *
 * how2test: Call API, it will return an enumerated list of nonmatching headerlines. If
 *           operation is a success then this testcase passes, otherwise it fails.
 */

public class getNonMatchHeadLines_Test extends MailTest {

    public static String[] headerlist = { "Date","To","From","#$@%&+1","Subject","Return-Path" };
    
    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class InternetHeaders: getNonMatchingHeaderLines(String[])\n");

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
	     out.fine("UNIT TEST 1: getNonMatchingHeaderLines(String[])");

	  // get non-matching headerlines
	     Enumeration nomatch = ih.getNonMatchingHeaderLines(headerlist);   // API TEST

	     while( nomatch.hasMoreElements() ) {
                    String headers = (String)nomatch.nextElement();
                    out.fine(headers);
	     }
	     out.fine("UNIT TEST 1: passed");
          // END UNIT TEST:

	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
