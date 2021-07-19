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

package javasoft.sqe.tests.jakarta.mail.internet.MimePartDataSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getOutputStream()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		DataSource method to return an output stream. <p>
 * api2test: public OutputStream getOutputStream()  <p>
 *
 * how2test: Call API, verify that it returned an object of type OutputStream.
 *	     If so then testcase passes, otherwise it fails. However, this
 *	     implementation throws the UnknownServiceException. If we catch this
 *	     then we consider test to be "passing" for now.
 */

public class getOutputStream_Test extends MailTest {

    private Store store = null;
    private Folder folder = null;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class MimePartDataSource: getOutputStream()\n");

        try {
          // Connect to host server
             store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);

             if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
          // Get message object
             MimeMessage msg = (MimeMessage)folder.getMessage(1);

             if( msg == null ) {
                 return Status.failed("Warning: Failed to get message number: 1");
             }
          // Create a MimePartDataSource object
             MimePartDataSource mpds = new MimePartDataSource((MimePart)msg);

             if( mpds == null ) {
                 return Status.failed("Warning: Failed to create a MimePartDataSource object!");
             }
          // BEGIN UNIT TEST 1:
             out.println("UNIT TEST 1: getOutputStream()");

             OutputStream os = mpds.getOutputStream();    // API TEST ; throws the UnknownServiceException

             if( os != null )
                 out.println("UNIT TEST 1: passed\n");
             else {
                   out.println("UNIT TEST 1: FAILED\n");
                   errors++;
             }
          // END UNIT TEST 1:

	} catch ( Exception une ) {
		handlException(une);
		out.println("\nThis implementation throws the UnknownServiceException.");
		out.println("UNIT TEST 1: passed\n");

		try {
			folder.close(false);
                	store.close();
                	checkStatus();
		} catch ( MessagingException me ) {
			handlException(me);
		}
        }
	return status;
     }
}
