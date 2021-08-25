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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addHeaderLine()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Add a raw RFC822 header-line. <p>
 * api2test: public void addHeaderLine(String)  <p>
 *
 * how2test: Call API with various string arguments and then call getAllHeaderLines() method
 *	     if it returns the new header lines that were added then testcase passes.
 */

public class addHeaderLine_Test extends MailTest {

    public static String[] headerlist = { "JavaMail","#$%^&*@!=+","    xYz	", "", "012345678" };

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeMessage: addHeaderLine(String)\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.warning("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1: addHeaderLine(String)");

	     // add new headerline
	     for( int j = 0; j < headerlist.length; j++ )
		  msg.addHeaderLine( headerlist[j] );	// API TEST
		     
	     Enumeration allheaders = msg.getAllHeaderLines();
		
	     while( allheaders.hasMoreElements() ) {
                    String headers = (String)allheaders.nextElement();
                    out.fine(headers);
             }
	     out.fine("UNIT TEST 1: passed\n");

	  // END UNIT TEST:

             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
