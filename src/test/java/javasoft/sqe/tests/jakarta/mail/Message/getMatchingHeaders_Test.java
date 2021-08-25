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
 * This class tests the <strong>getMatchingHeaders()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	Return matching headers from this part as an Enumeration of Header objects.<p>
 * api2test: public Enumeration getMatchingHeaders(String[])  <p>
 *
 * how2test: Call this API with desired header strings, verify that it returns a list of <p>
 *	     matching headers. Write out this list to stdio. If this operation is a success,
 *	     then this testcase passes, otherwise it fails.
 */

public class getMatchingHeaders_Test extends MailTest {

    public static String[] headerlist = { "Return-Path","Message-ID","Date","From","Reply-To",
                                          "X-Mailer","MIME-Version","To","Cc","Subject",
                                          "Content-Type","Content-Transfer-Encoding",
                                          "Content-MD5","Content-Length"
                                        };

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: getMatchingHeaders(String[])\n");

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

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }

             for(int i = 1; i <= msgcount; i++)
             {
             // Get the message
                Message msg = folder.getMessage(i);

	        if( msg == null ) {
		    log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
		// Get matching headers for given message(s)
	        out.fine("UNIT TEST "+ i +":  getMatchingHeaders(String[])");

	        Enumeration matcheaders = msg.getMatchingHeaders(headerlist);	// API TEST

	        while( matcheaders.hasMoreElements() ) {
		       Header headers = (Header)matcheaders.nextElement();
		       out.fine(headers.getName());
	        }
                out.fine("UNIT TEST "+ i +":  passed\n");
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
