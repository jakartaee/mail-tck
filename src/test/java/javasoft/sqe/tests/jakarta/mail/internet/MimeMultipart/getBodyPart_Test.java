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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMultipart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getBodyPart()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Get the specified bodypart object. <p>
 * api2test: public BodyPart getBodyPart(int)  <p>
 *		Get the Mimepart referred to by the given ContentID (CID). <p>
 * api2test: public BodyPart getBodyPart(String)  <p>
 
 * how2test: Call these APIs, then verify both return objects of type BodyPart. If
 *	     is so then this testcase passes, otherwise it fails.
 */

public class getBodyPart_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeMultipart: getBodyPart(int | String)\n");

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
	     boolean istrue = false;
	     boolean isfalse = false;

             for( int i = 1; i <= msgcount; i++ )
             {
                // Get message object
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

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

		if( co instanceof Multipart )
		{
		  // cast object to MimeMultipart
		     MimeMultipart mp = (MimeMultipart)co;

                     out.fine("UNIT TEST "+ i +": getBodyPart(int|String)");

                  // get the number of bodyparts enclosed for message
                     int partCount = mp.getCount();

                     if( partCount > 0 )
		     {
		         for( int j = 0; j < partCount; j++ )
		         {
			     // get the bodyparts enclosed for message
			     BodyPart bp1 = mp.getBodyPart(j);	// API TEST

			     if( bp1 != null )
			         istrue = true;
			     else
			         isfalse = true;

			     String strid = msg.getContentID();

			     if( strid != null )
			     {
			         BodyPart bp2 = mp.getBodyPart(strid);   // API TEST

			         if( bp2 != null )
			             istrue = true;
			         else
			             isfalse = true;
			     }
		         }
		         if( istrue && !isfalse )
			     out.fine("UNIT TEST "+ i +": passed");
		         else {
			       out.fine("UNIT TEST "+ i +": FAILED");
			       errors++;
		         }
                     } else {
                             out.fine("Message's has no bodyparts!");
                             out.fine("UNIT TEST "+ i +": FAILED");
                             errors++;
                       }
		} else {
			out.fine("This Message is not a Multipart!\n");
			continue;
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
