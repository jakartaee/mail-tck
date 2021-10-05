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
 * This class tests the <strong>MimeMessage()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Default constructor <p>
 * api2test: public MimeMessage(Session)  <p>
 *	    Constructs a MimeMessage by reading and parsing the data from the specified
 *	    MIME inputstream <p>
 * api2test: public MimeMessage(Session, InputStream); <p>
 *
 * how2test: Call these constructors, then verify the type of object created to be that of
 *	     MimeMessage. If is so then this testcase passes, otherwise it fails.
 */

public class MimeMessage_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeMessage: MimeMessage(Session, InputStream)\n");

        try {	     
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1:  MimeMessage(Session)");
	     
	     Session session = Session.getInstance(properties, null);
	     MimeMessage msgObj1 = new MimeMessage(session);	    // API TEST
	     
	     if(( msgObj1 != null ) && ( msgObj1 instanceof MimeMessage )) {
		  msgObj1.setFileName("javamail");
		  out.fine( msgObj1.getFileName() );
		  out.fine("UNIT TEST 1: passed");
	     } else {
		     out.fine("UNIT TEST 1: FAILED");
		     errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
             // Connect to host server
             Store store = connect2host(protocol, host, user, password);

             // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid mail folder");
             }
             folder.open(Folder.READ_ONLY);

             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
             // Get the first message
             MimeMessage msg = (MimeMessage)folder.getMessage(1);

             if( msg == null ) {
                 return Status.failed("Warning: Failed to get message number: 1");
             }
	     out.fine("UNIT TEST 2:  MimeMessage(Session, InputStream)");

	     ByteArrayInputStream istream = createInputStream(msg);
	     MimeMessage msgObj2 = new MimeMessage(session, istream);	    // API TEST

	     if(( msgObj2 != null ) && ( msgObj2 instanceof MimeMessage )) {
		  msgObj2.setSubject("This is another javamail test!");
		  out.fine( msgObj2.getSubject() );
		  out.fine("UNIT TEST 2: passed");
	     } else {
		     out.fine("UNIT TEST 2: FAILED");
		     errors++;
	     }
	  // END UNIT TEST 2:

	     store.close();
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
