/*
 * Copyright (c) 2006, 2020 Oracle and/or its affiliates. All rights reserved.
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
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>createMessage()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * Create a subclassed MimeMessage object and in reply(boolean) return instance 
 * of this new subclassed object. <p>
 * api2test: protected void createMimeMessage()  <p>
 *
 * how2test: Call API with various arguments, then call getRecipients() api, 
 *           verify that user specified recipient address types have been added. 
 *           If so then this testcase passes, otherwise it fails.
 */

public class createMimeMessage_Test extends MailTest {

    public static void main(String argv[]) {
        createMimeMessage_Test test = new createMimeMessage_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out) {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        try {
          // Create a custom MimeMessage objectcreateMimeMessage_Test 
          // with custom message ID algorithm
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MyMimeMessage(session);
             
             if (msg == null) {
                 log.println("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create custom " +
                                      "MimeMessage object");
             }
             
	  // BEGIN UNIT TEST:
	     out.println("UNIT TEST 1: createMimeMessage");

          // create Message ID
	     Message replyMsg = msg.reply(false);// API TEST for 
             // createMimeMessage called in reply(boolean). 
             // See the reply() in the inner class below
            
             if (replyMsg instanceof MyReplyMimeMessage) {
                out.println("UNIT TEST 1: passed\n");
             } else {
                 out.println("UNIT TEST 1: FAILED\n");
                errors++;
             }
             
	  // END UNIT TEST:
             checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
    
    class MyMimeMessage extends MimeMessage {
        
        public MyMimeMessage(Session session) {
            super(session);
        }
        
        public MimeMessage createMimeMessage(Session session)
        throws MessagingException {
            return new MyReplyMimeMessage(session);
        }
    }

    class MyReplyMimeMessage extends MimeMessage {
        
        public MyReplyMimeMessage(Session session) {
            super(session);
        }
    }
}
