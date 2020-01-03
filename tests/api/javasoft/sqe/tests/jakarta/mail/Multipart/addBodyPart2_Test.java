/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
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
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addBodyPart()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Adds a BodyPart at position index. <p>
 * api2test: public void addBodyPart(BodyPart, int)  <p>
 
 * how2test: Call this API with part argument and position, then verify by calling getCount. 
 *           If this part does get added then this testcase passes, otherwise it fails.
 */

public class addBodyPart2_Test extends MailTest {

    static String msgText1 = "This is a message body.\nHere's line two.";
    static String msgText2 = "This is the text in the message attachment.";

    public static void main( String argv[] )
    {
        addBodyPart2_Test test = new addBodyPart2_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Multipart: addBodyPart(BodyPart, int)\n");

	try {
           // get the default Session
              Session session = createSession();

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

	   // BEGIN UNIT TEST:
	      out.println("UNIT TEST 1: addBodyPart(BodyPart, int)");

	      mp.addBodyPart(mbp1, 0);	    // API TEST
	      mp.addBodyPart(mbp2, 1);	    // API TEST
	      mp.addBodyPart(mbp1, 2);	    // API TEST
	      mp.addBodyPart(mbp2, 3);	    // API TEST

	      if( mp.getCount() == 4 )
	          out.println("UNIT TEST 1: passed.\n");
	      else {
		     out.println("UNIT TEST 1: FAILED.\n");
		     errors++;
	      }
	   // END UNIT TEST:

	   // add the Multipart to the message
	      msg.setContent(mp);

	   // send the message
	      Transport.send(msg);

              checkStatus();
        } catch ( Exception e ) {
              handlException(e);
        }
	return status;
     }
}
