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
 * This class tests the <strong>getReplyTo()</strong> API.
 * It does this by invoking the test api and then checking
 * the type of the returned object.	<p>
 *
 *		Get the addresses to which replies should be directed. <p>
 * api2test: public Address[] getReplyTo()  <p>
 *
 * how2test: Call this API, verify that it returns array of addresses. Write out
 *	     this address list to stdio. If this operation is successfull then this
 *	     testcase passes, otherwise it fails. <p>
 *
 *	     If this header is unavailable or its value is absent, then
 *	     getFrom() is called and its value is returned.
 */

public class getReplyTo_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: getReplyTo()\n");

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

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     Address[] replyto  = new Address[1];

             for( int i = 1; i <= msgcount; i++ )
             {
              // Get the message
                 MimeMessage msg = (MimeMessage)folder.getMessage(i);

                 if ( msg == null ) {
                     log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
                     continue;
                 }
              // BEGIN UNIT TEST:
		 // Get whom the message is from
	         out.fine("UNIT TEST "+ i +":  getReplyTo()");

	         replyto = msg.getReplyTo();	// API TEST

	         if( replyto != null ) {
	             out.fine("getReplyTo() :=> '" + replyto[0] + "'");
                     out.fine("UNIT TEST "+ i +":  passed\n");
	         } else
		       out.fine("WARNING: Message "+ i +" has null reply to field");

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
