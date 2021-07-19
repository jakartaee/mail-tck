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
import java.nio.charset.StandardCharsets;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>InternetHeaders()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Create an empty InternetHeaders object. <p>
 * api2test: public InternetHeaders()  <p>
 *		Read and parse the given rfc822 message stream till the blank line
 *		separating the header from the body. <p>
 * api2test: public InternetHeaders(InputStream); <p>
 * api2test: public InternetHeaders(InputStream, boolean); <p>
 *
 * how2test: Call these constructors, then verify the type of object created to be that of
 *	     InternetHeaders. If is so then this testcase passes, otherwise it fails.
 */

public class InternetHeaders_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class InternetHeaders: InternetHeaders(void | InputStream)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1:  InternetHeaders()");
	     InternetHeaders ih = new InternetHeaders();	    // API TEST
	     
	     if(( ih != null ) && ( ih instanceof InternetHeaders ))
		  out.println("UNIT TEST 1: passed");
	     else {
		    out.println("UNIT TEST 1: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     // XXX - this should NOT be accessing the mail server
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

	     out.println("UNIT TEST 2:  InternetHeaders(InputStream)");
	     InternetHeaders iheader = new InternetHeaders(bis);	// API TEST

	     if(( iheader != null ) && ( iheader instanceof InternetHeaders ))
		  out.println("UNIT TEST 2: passed");
	     else {
		    out.println("UNIT TEST 2: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 2:
	  // BEGIN UNIT TEST 3:
	     out.println("UNIT TEST 3:  InternetHeaders(InputStream,true)");
	     String val = "test";
	     String uval = "test\u03b1";
	     String headers = "Subject: " + uval + "\r\nH: " + val + "\r\n\r\n";
	     byte[] bytes = headers.getBytes(StandardCharsets.UTF_8);
	     InputStream is = new ByteArrayInputStream(bytes);
	     InternetHeaders uih = new InternetHeaders(is, true); // API TEST

	     if (uih.getHeader("Subject", null).equals(uval) &&
	         uih.getHeader("H", null).equals(val))
		  out.println("UNIT TEST 3: passed");
	     else {
		    out.println("UNIT TEST 3: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 3:
	  // BEGIN UNIT TEST 4:
	     out.println("UNIT TEST 4:  InternetHeaders(InputStream,false)");
	     is = new ByteArrayInputStream(bytes);
	     uih = new InternetHeaders(is, false); // API TEST

	     if (!uih.getHeader("Subject", null).equals(uval) &&
	         uih.getHeader("H", null).equals(val))
		  out.println("UNIT TEST 4: passed");
	     else {
		    out.println("UNIT TEST 4: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 4:

	     store.close();
             checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
