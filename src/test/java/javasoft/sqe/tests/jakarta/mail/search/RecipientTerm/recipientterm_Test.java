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

package javasoft.sqe.tests.jakarta.mail.search.RecipientTerm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>RecipientTerm()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		A contructor that takes an integer and address arguments. <p>
 * api2test: public RecipientTerm(int, Address)  <p>
 *		The match method.   <p>
 * api2test: boolean match(Message)  <p>
 *
 * how2test: Call these APIs with int/address/message arguments and if 'match'
 *	     returns boolean value, then this testcase passes.
 */

public class recipientterm_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class RecipientTerm: RecipientTerm(int, Address)\n");

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
	     boolean foundit;

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg =  (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
		Address[] recipient = msg.getRecipients(Message.RecipientType.TO);

		if( recipient[0] == null )
		    continue;

             // BEGIN UNIT TEST:
                out.fine("UNIT TEST "+ i +":  RecipientTerm(Message.RecipientType.TO, "+ (recipient[0]).toString() +")");

                RecipientTerm rt = new RecipientTerm(Message.RecipientType.TO, recipient[0]);    // API TEST

                if( rt == null ) {
                    log.warning("Warning: RecipientTerm contructor returned a Null object!");
		    continue;
                } else
                      out.fine("UNIT TEST "+ i +": passed\n");

	        out.fine("UNIT TEST "+ i +":  match(Message)");

		// match the message number
		foundit = rt.match(msg);	// API TEST

	        if( foundit ) {
	            out.fine("Expected recipients found in message header.");
                    out.fine("UNIT TEST "+ i +":  passed\n");
	        } else {
		        out.fine("Expected recipients not found in message header!");
			out.fine("UNIT TEST "+ i +":  passed\n");
	        }
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
