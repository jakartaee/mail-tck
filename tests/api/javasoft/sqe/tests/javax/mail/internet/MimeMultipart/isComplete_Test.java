/*
 * Copyright (c) 2006, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.javax.mail.internet.MimeMultipart;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>isComplete()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * api2test: public void MimeMultipart.isComplete()  <p>
 *
 * how2test: Call API. If this operation is successful then testcase 
 * passes, otherwise it fails.
 */

public class isComplete_Test extends MailTest {

    public static void main(String argv[]) {
        isComplete_Test test = new isComplete_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out) {
        
	super.run(argv, log, out);
	parseArgs(argv); // parse command-line options

        out.println("\nTesting MimeMultipart: isComplete()\n");
        
        try {
            Session session = Session.getInstance(properties, null);
            String filename = workdir + 
                    System.getProperty("file.separator") + iofile;
            FileInputStream fis = new FileInputStream(filename);

            // BEGIN UNIT TEST:
            out.println("UNIT TEST 1:  test for parse(InputStream) with " +
               "mail.mime.multipart.ignoremissingendboundary = TRUE " +
               "(default) \n and a mime message input where the ending " +
               "boundary is missing");
            
            try {
                // Create a MimeMessage object
                // construction of MimeMessage will internally call the 
                // protected MimeMessage.parse(InputStream) method.
                MimeMessage newmimemsg = new MimeMessage(session, fis);
                MimeMultipart mp = (MimeMultipart) newmimemsg.getContent();
                
                if (mp.isComplete() == false) 
                    out.println("UNIT TEST 1:  passed\n");
                else {
                    out.println("UNIT TEST 1:  FAILED\n");
                    errors++;
                }
            } catch (MessagingException ex) {
                // mail.mime.multipart.ignoremissingboundaryparameter NOT set 
                // i.e. defaults to TRUE. So parsing of the the iofile which 
                // has missing boundary parameter goes through fine. 
                return Status.failed("Failed due to exception: " + 
                        ex.getMessage());
            }
            // END UNIT TEST:

            checkStatus();
        } catch (Exception e) { 
	    handlException(e);
        }
	return status;        
    }
}
