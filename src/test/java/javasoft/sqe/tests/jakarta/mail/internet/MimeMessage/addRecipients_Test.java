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
 * This class tests the <strong>addRecipients()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Add the given addresses to the specified recipient type. <p>
 * api2test: public void addRecipients(int, Address[])  <p>
 *
 * how2test: Call API with various arguments, then call getRecipients() api, verify
 *	     that user specified recipient address types have been added. If so then this
 *	     testcase passes, otherwise it fails.
 */

public class addRecipients_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeMessage: addRecipients(int, Address[])\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_WRITE);

             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	  // Get message object
	     MimeMessage dmsg = (MimeMessage)folder.getMessage(1);

	     if( dmsg == null ) {
		 log.warning("Warning: Failed to get message number 1");
		 return Status.failed("Failed to get message number 1");
	     }
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.warning("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1: addRecipients(int, Address[])");

	     // add new To: recipients
	     msg.addRecipients(Message.RecipientType.TO, dmsg.getRecipients(Message.RecipientType.TO));	// API TEST
	     Address[] addrTOlist = msg.getRecipients(Message.RecipientType.TO);

	     if( addrTOlist != null ) {
		 for( int j = 0; j < addrTOlist.length; j++ ) {
		      if ( addrTOlist[j] != null )
			   out.fine( ((InternetAddress)addrTOlist[j]).getAddress() );
		 }
	     } else
		   out.fine("Warning: getRecipients(Message.RecipientType.TO) returned null pointer");

	     // add new Cc: recipients
	     msg.addRecipients(Message.RecipientType.CC, dmsg.getRecipients(Message.RecipientType.TO));	// API TEST
	     Address[] addrCClist = msg.getRecipients(Message.RecipientType.CC);

	     if( addrCClist != null ) {
		 for( int k = 0; k < addrCClist.length; k++ ) {
		      if( addrCClist[k] != null )
			  out.fine( ((InternetAddress)addrCClist[k]).getAddress() );
		 }
	     } else
		   out.fine("Warning: getRecipients(Message.RecipientType.CC) returned null pointer");

	     // add new Bcc; recipients
	     msg.addRecipients(Message.RecipientType.BCC, dmsg.getRecipients(Message.RecipientType.TO));	// API TEST
	     Address[] addrBCClist = msg.getRecipients(Message.RecipientType.BCC);

	     if( addrBCClist != null ) {
		 for( int n = 0; n < addrBCClist.length; n++ ) {
		      if( addrBCClist[n] != null )
			  out.fine( ((InternetAddress)addrBCClist[n]).getAddress() );
		 }
	     } else
		   out.fine("Warning: getRecipients(Message.RecipientType.BCC) returned null pointer");

	     out.fine("UNIT TEST 1: passed\n");
	  // END UNIT TEST:

             folder.close(false);
             store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
