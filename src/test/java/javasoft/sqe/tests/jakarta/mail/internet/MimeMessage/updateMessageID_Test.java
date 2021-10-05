/*
 * Copyright (c) 2006, 2021 Oracle and/or its affiliates. All rights reserved.
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
 * This class tests the <strong>updateMessageID()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * Create a subclassed MimeMessage object and set the message ID. <p>
 * api2test: protected void updateMessageID()  <p>
 *
 * how2test: Call API with various arguments, then call getRecipients() api, 
 *           verify that user specified recipient address types have been added. 
 *           If so then this testcase passes, otherwise it fails.
 */

public class updateMessageID_Test extends MailTest {

    private static final String msgIDSuffix = 
        "CustomAlgorithmGeneratedMessageID";
    
    private String msgID;
    
    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run() {
	

        try {
          // Create a custom MimeMessage object with custom message ID algorithm
             Session session = Session.getInstance(properties, null);
             MyMimeMessage msg = new MyMimeMessage(session); 
             if (msg == null) {
                 log.warning("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed(
                     "Failed to create custom MimeMessage object");
             }
             
	  // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1: MyMimeMessage.updateMessageID()");

             String originalMsgID = msg.getMessageID();
             out.fine("Original Message ID = " + originalMsgID);
             msgID = originalMsgID + msgIDSuffix;
             
          // update message ID
	     msg.saveChanges();	// API TEST for updateMessageID()
            
             String newMsgID = msg.getMessageID();
             out.fine("New updated Message ID = " + newMsgID);

             if (newMsgID.equals(msgID)) 
                out.fine("UNIT TEST 1: passed\n");
             else {
                errors++;
                out.fine("UNIT TEST 1: FAILED\n");
             }
	  // END UNIT TEST:

             checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
    
     class MyMimeMessage extends MimeMessage {
         
        protected void updateMessageID() throws MessagingException {
            setHeader("Message-ID", msgID);
        }
        
        public MyMimeMessage(Session session) {
            super(session);
        }
     }
}
