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
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>removeBodyPart()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Remove specified part from Multipart object. <p>
 * api2test: public void removeBodyPart(int)  <p>
 *
 * how2test: Call this API with integer argument, then verify by calling getCount. If these
 *	     parts does get removed then this testcase passes, otherwise it fails.
 */

public class removeBodyPart2_Test extends MailTest {

    static String msgText1 = "This is a message body.\nHere's line two.";
    static String msgText2 = "This is the text in the message attachment.";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Multipart: removeBodyPart(int)\n");

	try {
           // create some properties and get the default Session
              Properties props = new Properties();

              Session session = Session.getInstance(props, null);

           // create a message
              MimeMessage msg = new MimeMessage(session);

	      msg.setFrom(new InternetAddress(from));
	      InternetAddress[] address = {new InternetAddress(to)};

	      msg.setRecipients(Message.RecipientType.TO, address);
	      msg.setSubject("JavaMail APIs Multipart Test");

	   // create and fill the first message part
	      MimeBodyPart mbp1 = new MimeBodyPart();
	      mbp1.setText(msgText1);

	   // create and fill the second message part
	      MimeBodyPart mbp2 = new MimeBodyPart();

	   // Use setText(text, charset), to show it off !
	      mbp2.setText(msgText2, "us-ascii");

	   // create the Multipart and its parts to it
	      Multipart mp = new MimeMultipart();

	   // add parts to Multipart object
              mp.addBodyPart(mbp1);
              mp.addBodyPart(mbp2);
              mp.addBodyPart(mbp1);
	      mp.addBodyPart(mbp2);

           // BEGIN UNIT TEST:

              out.fine("UNIT TEST 1: removeBodyPart(int)");

	      int partCount = mp.getCount();

	   // remove part from Multipart object
	      out.fine("Number of parts = "+ partCount);

	      int k = 0;
	      while(k < partCount ) {
		    mp.removeBodyPart(0);	// API TEST
		    k++;
	      }
	      if( mp.getCount() == 0 )
                  out.fine("UNIT TEST 1: passed.\n");
	      else {
		    out.fine("UNIT TEST 1: FAILEd.\n");
		    errors++;
	      }
           // END UNIT TEST:

              checkStatus();
        } catch ( Exception e ) {
              handlException(e);
        }
	return status;
     }
}
