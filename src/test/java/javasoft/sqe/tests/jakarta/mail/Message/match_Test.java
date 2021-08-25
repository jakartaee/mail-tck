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
import jakarta.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>match()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Apply the specified Search criterion to this message. <p>
 * api2test: public boolean match(Term term)  <p>
 *
 * how2test: Call this API for any message object, verify that it returns a boolean
 *	     value. Regardless of the value returned this testcase passes.
 */

public class match_Test extends MailTest {

    static String SUBJECT = "Subject";

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: match(Term)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);
	     BodyTerm freeStr = new BodyTerm(pattern);
	     HeaderTerm caseTerm  = new HeaderTerm(SUBJECT, pattern);

	     if ( freeStr == null || caseTerm == null ) {
		  return Status.failed("null Body/Header Term object returned!");
	     }

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     boolean foundit;

             for ( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                Message msg =  folder.getMessage(i);

	        if( msg == null ) {
		    log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST 1:
		// find the pattern in message body
	        out.fine("UNIT TEST "+ i +":  match(Term)");

	        if ( freeStr != null )
	             foundit = msg.match(freeStr);	// API TEST
		else
		     return null;

	        if ( foundit ) {
	             out.fine("Pattern "+ pattern +" found in message body.");
                     out.fine("UNIT TEST "+ i +":  passed\n");
	        } else {
		        out.fine("Caution: Pattern "+ pattern +" not found in message body!");
	        }
	     // END UNIT TEST 1:
             // BEGIN UNIT TEST 2:
                // find the pattern in message header
                out.fine("UNIT TEST "+ i +":  match(Term)");

		if ( caseTerm != null )
                     foundit = msg.match(caseTerm);    // API TEST
		else
		     return null;

                if ( foundit ) {
                    out.fine("Pattern "+ pattern +" found in message header.");
                    out.fine("UNIT TEST "+ i +":  passed\n");
                } else {
                        out.fine("Caution: Pattern "+ pattern +" not found in message header!");
                }
             // END UNIT TEST 2:
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
