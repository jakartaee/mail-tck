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

package javasoft.sqe.tests.jakarta.mail.Flags;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getUserFlags()</strong> API.
 * It does this by invoking the test api and then checking
 * the type of the returned object.	<p>
 *
 *		Return all user flags in this Flags object. <p>
 * api2test: public String[] getUserFlags()  <p>
 *
 * how2test: Get flag object of a message and then call 'get()' method <p>
 *	     to obtain all the flags in this Flags object. Write the  <p>
 *	     content of array returned by the 'get()' api. <p>
 *	     If these operations are successful then the test passes.
 */

public class getUserFlags_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Flags: getUserFlags()\n");

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
	     msgcount = folder.getMessageCount();

	     if( msgcount < 1 )
		 return Status.failed("Mail folder is empty!");

	     for( int i = 1; i <= msgcount; i++ )
	     {
	     // Get message object
		MimeMessage msg = (MimeMessage)folder.getMessage(i);

		if( msg == null ) {
		    out.fine("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
		}
	     // Get Flags object for this message
		Flags flag = msg.getFlags();

                if( flag == null ) {
                    out.fine("WARNING: FAILED TO GET FLAGS OBJECT FOR MESSAGE: "+ i);
                    continue;
                }
	     // BEGIN UNIT TEST:

                out.fine("UNIT TEST "+ i +":  getUserFlags()");

		String[] flaglist = flag.getUserFlags();		// API TEST

		if( flaglist.length > 0 )
		{
		    for( int j = 0; j < flaglist.length; j++ )
		         out.fine(flaglist[j]);

		    out.fine("UNIT TEST " + i + ":  passed\n");
		}
		else if( flaglist.length == 0 ) {
			 out.fine("No user flags set for this Flags object.");
			 out.fine("UNIT TEST " + i + ":  passed\n");
		     } else {
		             out.fine("UNIT TEST "+ i +":  FAILED\n");
			     errors++;
		}
	     // END OF UNIT TEST:
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
