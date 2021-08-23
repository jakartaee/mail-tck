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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>removeHeader()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Remove all headers with this name. <p>
 * api2test: public void removeHeader(String name)  <p>
 *
 * how2test: Call this API with different header names. Then get all headers, the
 *	     removed header should not be present in the newly fetched list of header
 *	     names. If this true then this testcase passes otherwise it fails.
 */

public class removeHeader_Test extends MailTest {

    public static String[] header = { "Return-Path","Message-ID","Received",
				      "X-Mailer","Content-Transfer-Encoding",
				      "Content-MD5","Organization"
				    };

    public static String[] value  = { "/tmp/xyx/ksn/zz","738","Yes","x-mailer",
				      "!@#%$^%&+_~?","ZCDCS","Javasoft"
				    };

    public static String[] headerlist = { "Return-Path","Message-ID","Received",
                                          "X-Mailer","Content-Transfer-Encoding",
                                          "Content-MD5","Organization"
                                        };

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: removeHeader(String)\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.warning("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
	     // add the specified message headers to message object
	     for( int i = 0; i < header.length; i++ )
                  msg.addHeader(header[i], value[i]);

	  // BEGIN UNIT TEST:
	     // delete the specified message headers
	     out.fine("UNIT TEST 1:  removeHeader(String)");

	     for( int j = 0; j < headerlist.length; j++ )
                  msg.removeHeader(headerlist[j]);		// API TEST

	     Enumeration allHeaders = msg.getAllHeaders();
	     boolean unitest = false;

             while( allHeaders.hasMoreElements() ) {
                    Header h = (Header)(allHeaders.nextElement());
		    out.fine( h.getName() + ": " + h.getValue() );

		    for( int k = 0; k < headerlist.length; k++ ) {
			 if( h.getName().equals( headerlist[k] ))
			     unitest = true;
		    }
             }

	     if( !(unitest) )
		  out.fine("UNIT TEST 1:  passed\n");
	     else {
		   out.fine("UNIT TEST 1:  FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST:

	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
