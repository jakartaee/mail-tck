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
 * This class tests the <strong>getDisposition()</strong> API.
 * It does this by invoking the test api and then checking
 * the type of the returned object.	<p>
 *
 *		Returns the value of the "Content-Disposition" header field. <p>
 * api2test: public int getDisposition()  <p>
 *
 * how2test: Call this API and check that it returns a int value of the "Content-Disposition"
 *	     header field. If this is true then this testcase passes otherwise it fails. <p>
 *
 *           If the Content-Disposition field is unavailable, the value 'NONE' <p>
 *	     is returned. If the value of this field is neither "inline" nor
 *	     "attachment", it defaults to ATTACHMENT.
 */

public class getDisposition_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: getDisposition()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
                  return Status.failed("Invalid folder");
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

	        if( msg == null ) {
		    log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
		// Get body-part disposition
	        out.fine("UNIT TEST "+ i +":  getDisposition()");

	        String disposition = msg.getDisposition();	// API TEST
	        out.fine("getDisposition() :=> "+ disposition);

	        if( disposition != null ) {
		    if (( disposition != Part.INLINE ) || ( disposition != Part.ATTACHMENT ) )
                    	  out.fine("UNIT TEST "+ i +":  passed\n");
	            else {
			  out.fine("UNIT TEST "+ i +":  FAILED\n");
			  errors++;
	            }
		}
	      }
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
