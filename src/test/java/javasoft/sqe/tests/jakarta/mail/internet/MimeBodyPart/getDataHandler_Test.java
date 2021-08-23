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

package javasoft.sqe.tests.jakarta.mail.internet.MimeBodyPart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.activation.DataHandler;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getDataHandler()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return a DataHandler for BodyPart's content. <p>
 * api2test: public DataHandler getDataHandler()  <p>
 *
 * how2test: Call API, if the returned object is of type 'DataHandler', 
 *           then testcase passes, otherwise it fails.
 */

public class getDataHandler_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeBodyPart: getDataHandler()\n");

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

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
	        out.fine("UNIT TEST "+ i +":  getDataHandler()");

		// Get the "type" of content
	        Object content = msg.getContent();

		if ( content instanceof Multipart ) {
		     int bodycount = ((MimeMultipart)content).getCount();
		     for( int k = 0; k < bodycount; k++ )
		     {
		     	  BodyPart bp = ((MimeMultipart)content).getBodyPart(k);
			 // get bodypart's datahandler
			  DataHandler dh = ((MimeBodyPart)bp).getDataHandler();	// API TEST

			  if ( dh != null )
			       out.fine("UNIT TEST "+ i +":  passed\n");
			  else {
				out.fine("UNIT TEST "+ i +":  FAILED\n");
				errors++;
			  }
		     }
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
