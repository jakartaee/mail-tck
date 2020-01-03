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

package javasoft.sqe.tests.jakarta.mail.internet.InternetHeaders;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>load()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Read and parse the given rfc822 message stream till the blank line separating
 *	    the header from the body. <p>
 * api2test: public void load(InputStream)  <p>
 * api2test: public void load(InputStream,boolean)  <p>
 *
 * how2test: Call API, then verify this operation by calling getAllHeaderLines() api on
 *	     InternetHeaders object. If this operation is successfull then this testcase
 *	     passes, otherwise it fails.
 */

public class load_Test extends MailTest {
    private static final String uhead = "Subject";
    private static final String head = "Header";
    private static final String uval = "test\u03b1";
    private static final String val = "test";
    private static final byte[] bytes =
			    (uhead + ": " + uval + "\r\n" +
			    head + ": " + val + "\r\n\r\n").
			    getBytes(StandardCharsets.UTF_8);

    public static void main( String argv[] )
    {
        load_Test test = new load_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class InternetHeaders: load(InputStream)\n");

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

	  // create InternetHeaders object
	     InternetHeaders ih = new InternetHeaders();

	  // BEGIN UNIT TEST 1:

	     if( ih != null )
	     {
		 out.println("UNIT TEST 1:  load(InputStream)");
		 ih.load(bis);	// API TEST

                 // get all headerlines
                 Enumeration allheaders = ih.getAllHeaderLines();

                 while( allheaders.hasMoreElements() ) {
                	String headers = (String)allheaders.nextElement();
                	out.println(headers);
                 }
		 out.println("UNIT TEST 1: passed");
	     } else {
		    out.println("UNIT TEST 1: FAILED");
		    errors++;
	     }
	     store.close();
	  // END UNIT TEST 1:

	  // BEGIN UNIT TEST 2:
	     InputStream is;
	     out.println("UNIT TEST 2:  load(InputStream)");
	     is = new ByteArrayInputStream(bytes);
	     ih = new InternetHeaders(); // API TEST
	     ih.load(is); // API TEST

	     if (!ih.getHeader(uhead, null).equals(uval) &&
	         ih.getHeader(head, null).equals(val))
		  out.println("UNIT TEST 2: passed");
	     else {
		    out.println("UNIT TEST 2: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 2:
	  // BEGIN UNIT TEST 3:
	     out.println("UNIT TEST 3:  load(InputStream,true)");
	     is = new ByteArrayInputStream(bytes);
	     InternetHeaders uih = new InternetHeaders();
	     uih.load(is, true); // API TEST

	     if (uih.getHeader(uhead, null).equals(uval) &&
	         uih.getHeader(head, null).equals(val))
		  out.println("UNIT TEST 3: passed");
	     else {
		    out.println("UNIT TEST 3: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 3:
	  // BEGIN UNIT TEST 4:
	     out.println("UNIT TEST 4:  load(InputStream,false)");
	     is = new ByteArrayInputStream(bytes);
	     uih = new InternetHeaders(); // API TEST
	     uih.load(is, false); // API TEST

	     if (!uih.getHeader(uhead, null).equals(uval) &&
	         uih.getHeader(head, null).equals(val))
		  out.println("UNIT TEST 4: passed");
	     else {
		    out.println("UNIT TEST 4: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 4:

             checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
