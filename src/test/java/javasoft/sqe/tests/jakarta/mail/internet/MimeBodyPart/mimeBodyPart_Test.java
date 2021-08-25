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

package javasoft.sqe.tests.jakarta.mail.internet.MimeBodyPart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>MimeBodyPart()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Default constructor <p>
 * api2test: public MimeBodyPart() <p>
 *
 *	    Constructs a MimeBodyPart by reading and parsing the data from
 *	    the specified input stream.<p>
 * api2test: public MimeBodyPart(InputStream); <p>
 *
 *	    Constructs a MimeBodyPart using the given header and content bytes. <p>
 * api2test: public MimeBodyPart(InternetHeaders, byte[])  <p>
 *
 * how2test: Call these constructors, then verify the type of object created to be that of
 *	     MimeBodyPart. If is so then this testcase passes, otherwise it fails.
 */

public class mimeBodyPart_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeBodyPart: MimeBodyPart(..)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1:  MimeBodyPart()");
	     MimeBodyPart mbp1 = new MimeBodyPart();	    // API TEST

	     if( mbp1 != null )
		 out.fine("UNIT TEST 1: passed\n");
	     else {
		   out.fine("UNIT TEST 1: FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
             // Connect to host server
             Store store = connect2host(protocol, host, user, password);

             // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder");
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

	     out.fine("UNIT TEST 2:  MimeBodyPart(InputStream)");
	     MimeBodyPart mbp2 = new MimeBodyPart(bis); 	// API TEST

	     if( mbp2 != null )
		 out.fine("UNIT TEST 2: passed\n");
	     else {
		    out.fine("UNIT TEST 2: FAILED\n");
		    errors++;
	     }
	  // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
             // Create a ByteArrayOutStream object
             ByteArrayOutputStream bos = new ByteArrayOutputStream();
             msg.writeTo(bos);

             // Create a ByteArrayInputStream object
             bis = new ByteArrayInputStream(bos.toByteArray());

             // Create header object
	     InternetHeaders headers = new InternetHeaders(bis);

             if( headers == null ) {
                 return Status.failed("WARNING: Failed to create InternetHeaders object");
             }
	     ContentType cType = new ContentType("multipart/mixed");
             String contentType = cType.toString();
             String boundary = "--" + cType.getParameter("boundary");
             int bl = boundary.length();
             char[] bndbytes = new char[bl];
             boundary.getChars(0, bl, bndbytes, 0);

             /*
              * Read and save the content bytes in buf.
              */
	     int b;
	     boolean done = false;

             while((b = bis.read()) >= 0)
	     {
                    if (b == '\r' || b == '\n') {
                        /*
                         * Found the end of a line, check whether the
                         * next line is a boundary.
                         */
                        int i;
                        bis.mark(bl + 4 + 1);    // "4" for possible "--\r\n"

                        if (b == '\r' && bis.read() != '\n') {
                            bis.reset();
                            bis.mark(bl + 4);
                        }

                        // read bytes, matching against the boundary
                        for(i = 0; i < bl; i++)
                            if (bis.read() != bndbytes[i])
                                break;
                        if (i == bl) {
                            // matched the boundary, check for last boundary
                            int b2 = bis.read();
                            if (b2 == '-') {
                                if (bis.read() == '-') {
                                    done = true;
                                    b2 = bis.read();
                                }
                            }
                            // check for end of line
                            if (b2 == '\n')
                                break;  // got it!  break out of the while loop
                            if (b2 == '\r') {
                                bis.mark(1);
                                if (bis.read() != '\n')
                                    bis.reset();
                                break;  // got it!  break out of the while loop
                            }
                        }
                        // failed to match, reset and proceed normally
                        bis.reset();
                    }
                    bos.write(b);
             }
             out.fine("UNIT TEST 3:  MimeBodyPart(InternetHeaders, byte[])");
             MimeBodyPart mbp3 = new MimeBodyPart(headers, bos.toByteArray());   // API TEST

             if( mbp3 != null ) 
                 out.fine("UNIT TEST 3: passed\n");
             else {
                    out.fine("UNIT TEST 3: FAILED\n");
                    errors++;
             }
          // END UNIT TEST 3:

	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
