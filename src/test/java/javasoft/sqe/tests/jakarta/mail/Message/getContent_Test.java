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
 * This class tests the <strong>getContent()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return the content as a Java object. <p>
 * api2test: public Object getContent()  <p>
 *
 * how2test: Call this API, then check the type of java object returned. We test <p>
 *	     for 'string','multipart', and non-null. The testcases passes if the api <p>
 *	     returns any one of these object types, otherwise we mark as it failing.
 */

public class getContent_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

        out.println("\nTesting class Message: getContent()\n");

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

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if ( msg == null ) {
		     log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		     continue;
	        }
	     // BEGIN UNIT TEST:
		// Get message content
	        out.println("UNIT TEST "+ i +":  getContent()");

		// what should be the "type" of content variable ?
	        Object contentype = msg.getContent();		// API TEST

	        out.println("getContent() :=> "+ contentype);

	        if ( contentype != null )
                     out.println("UNIT TEST "+ i +":  passed\n");
		else if (( contentype != null ) && ( contentype instanceof Multipart ))
                          out.println("UNIT TEST "+ i +":  passed\n");
	        else {
		      out.println("UNIT TEST "+ i +":  FAILED\n");
		      errors++;
	        }
	     // END UNIT TEST:
	     }
	     folder.close(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
