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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getHeader()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Get all the headers for this header_name. <p>
 * api2test: public String[] getHeader(String name)  <p>
 *
 * how2test: Call this API with various input header names. Verity that it returns <p>
 *	     list/array of headers. Write out this list to stdio. If this operation <p>
 *	     is successfull then this testcase passes, otherwise it fails.
 */

public class getHeader_Test extends MailTest {

    public static String msgid = "Message-ID";
    public static String date = "Date";
    public static String to = "To";
    public static String cc = "Cc";
    public static String bcc = "Bcc";
    public static String from = "From";
    public static String replyto = "Reply-To";
    public static String returnpath = "Return-Path";
    public static String subject = "Subject";
    public static String xmailer = "X-Mailer";
    public static String reference = "References";
    public static String mime_version = "MIME-Version";
    public static String contentype = "Content-Type";
    public static String content_encode = "Content-Transfer-Encoding";
    public static String contentlength = "Content-Length";
    public static String content_md5 = "Content-MD5";

    public static void main( String argv[] )
    {
        getHeader_Test test = new getHeader_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: getHeader(String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
                  return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);
	     String[] header  = new String[2];

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     
             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
	        out.println("UNIT TEST "+ i +":  getHeader(String)");

		// Get the specified message headers
                header = msg.getHeader(returnpath);	// API TEST

	        if( header != null )
                    out.println("getHeaders(Return-Path) :=> '" + header[0] + "'");
	        else
		    out.println("getHeaders(Return-Path) :=> 'empty field'");

                header = msg.getHeader(msgid);		// API TEST

	        if ( header != null )
                     out.println("getHeaders(Message-ID) :=> '" + header[0] + "'");
	        else
		     out.println("getHeaders(Message-ID) :=> 'empty field'");

	        header = msg.getHeader(date);		// API TEST

	        if ( header != null )
	             out.println("getHeaders(Date) :=> '" + header[0] + "'");
                else
		     out.println("getHeaders(Date) :=> 'empty field'");

                header = msg.getHeader(from);		// API TEST

	        if ( header != null ) {
                     out.println("getHeaders(From) :=> '" + header[0] + "'");
		   //out.println("getHeaders(From) :=> '" + header[1] + "'");
	        } else
		     out.println("getHeaders(From) :=> 'empty field'");

                header = msg.getHeader(replyto);	// API TEST

	        if ( header != null )
                     out.println("getHeaders(Reply-To) :=> '" + header[0] + "'");
	        else
		     out.println("getHeaders(Reply-To) :=> 'empty field'");

                header = msg.getHeader(xmailer);	// API TEST

	        if ( header != null )
                     out.println("getHeaders(X-Mailer) :=> '" + header[0] + "'");
	        else
	             out.println("getHeaders(X-Mailer) :=> 'empty field'");

                header = msg.getHeader(mime_version);	// API TEST

                if ( header != null )
                     out.println("getHeaders(MIME-Version) :=> '" + header[0] + "'");
                else
                     out.println("getHeaders(MIME-Version) :=> 'empty field'");

                header = msg.getHeader(to);		// API TEST

	        if ( header != null )
                     out.println("getHeaders(To) :=> '" + header[0] + "'");
	        else
		     out.println("getHeaders(To) :=> 'empty field'");

                header = msg.getHeader(cc);		// API TEST

	        if ( header != null )
                     out.println("getHeaders(Cc) :=> '" + header[0] + "'");
	        else
		     out.println("getHeaders(Cc) :=> 'empty field'");

                header = msg.getHeader(bcc);		// API TEST

	        if ( header != null )
                     out.println("getHeaders(Bcc) :=> '" + header[0] + "'");
	        else
		     out.println("getHeaders(Bcc) :=> 'empty field'");

                header = msg.getHeader(subject);	// API TEST

	        if ( header != null )
                     out.println("getHeaders(Subject) :=> '" + header[0] + "'");
	        else
		     out.println("getHeaders(Subject) :=> 'empty field'");

                header = msg.getHeader(reference);	// API TEST

                if ( header != null )
                     out.println("getHeaders(References) :=> '" + header[0] + "'");
                else
                     out.println("getHeaders(References) :=> 'empty field'");

                header = msg.getHeader(contentype);	// API TEST

                if ( header != null )
                     out.println("getHeaders(Content-Type) :=> '" + header[0] + "'");
                else
                     out.println("getHeaders(Content-Type) :=> 'empty field'");

                header = msg.getHeader(content_encode);	// API TEST

	        if ( header != null )
                     out.println("getHeaders(Content-Transfer-Encoding) :=> '" + header[0] + "'");
	        else
		     out.println("getHeaders(Content-Transfer-Encoding) :=> 'empty field'");

                header = msg.getHeader(content_md5);	// API TEST

	        if ( header != null )
                     out.println("getHeaders(Content-MD5) :=> '" + header[0] + "'");
	        else
		     out.println("getHeaders(Content-MD5) :=> 'empty field'");

                header = msg.getHeader(contentlength);	// API TEST

                if ( header != null )
                     out.println("getHeader(Content-Length) :=> '" + header[0] + "'");
                else
                     out.println("getHeader(Content-Length) :=> 'empty field'");

                out.println("UNIT TEST " + (i+1) + ":  passed\n");

	     // END UNIT TEST:
	    }
	    folder.close(false);
	    store.close();
	    status = Status.passed("OKAY");

        } catch ( Exception e ) {
	    handlException(e);
        }
	return status;
     }
}
