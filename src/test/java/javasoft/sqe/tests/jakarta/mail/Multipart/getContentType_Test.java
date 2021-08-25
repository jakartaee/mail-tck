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

package javasoft.sqe.tests.jakarta.mail.Multipart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getContentType()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return the value of the contentType field. <p>
 * api2test: public String getContentType()  <p>
 *
 * how2test: Call this API, then verify it returns string value. If so
 *	     then this testcase passes, otherwise it fails.
 */

public class getContentType_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Multipart: getContentType()\n");

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
                // Get message object
                Message msg = folder.getMessage(i);

                if( msg == null ) {
                    log.warning("Warning: Failed to get message number "+ i);
                    continue;
                }
             // BEGIN UNIT TEST:
		// get message content
		Object co = msg.getContent();
		
		if ( co == null ) {
		     log.warning("Warning: Failed to get message content "+ i);
                     continue;
                }
		if ( co instanceof Multipart )
		{
		   // cast object to Multipart
		     Multipart mp = (Multipart)co;

                     out.fine("UNIT TEST "+ i +": getContentType()");
		
                  // get the number of bodyparts enclosed for this message
                     String contype = mp.getContentType();   // API TEST

                     if( contype != null ) {
                         out.fine("The body content type is "+ contype);
                         out.fine("UNIT TEST "+ i +": passed");
                     } else {
			     out.fine("The body content type is null");
                             out.fine("UNIT TEST "+ i +": passed\n");
			}
		} else
			continue;

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
