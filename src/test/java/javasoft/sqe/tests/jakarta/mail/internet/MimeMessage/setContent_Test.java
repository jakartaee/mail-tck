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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setContent()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		This method sets the Message's content to a Multipart object. <p>
 *api2test: public void setContent(Multipart) <p>
 *		This method provides the mechanism to set a Message's content. <p>
 * api2test: public void setContent(Object obj, String type)  <p>
 *
 * how2test: Call API with required arguments, then call getContent() to check
 *	     that expected value was returned. If operation is successful then
 *	     testcase passes, otherwise it fails.
 */

public class setContent_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class MimeMessage: setContent(Object, String)\n");

        try {
          // Get a Session object
             session = Session.getInstance(properties, null);

             if( session == null ) {
                 return Status.failed("Warning: Failed to create a Session object!");
             }
	  // Create a MimeMessage object
	     MimeMessage mob = new MimeMessage(session);

             if( mob == null ) {
                 return Status.failed("Warning: Failed to create a MimeMessage object!");
             }
          // Create a Multipart object
             Multipart mmp = new MimeMultipart();

             if( mmp == null ) {
                 return Status.failed("Warning: Failed to create a Multipart object!");
             }
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1:  setContent(Multipart)");

	     mob.setContent(mmp);	// API TEST
	     Object content = mob.getContent();

	     if(( content != null ) && ( content instanceof String )) {
		  if( ((String)content).equals("text/html") )
		      out.println("UNIT TEST 1:  passed\n");
		  else {
			out.println("UNIT TEST 1:  FAILED\n");
			errors++;
		  }
	     } else if(( content != null ) && ( content instanceof Multipart )) {
			 out.println("This is a Multipart");
            		 Multipart mp1 = (Multipart)content;
            		 int count = mp1.getCount();
		         out.println("UNIT TEST 1:  passed\n");
	             } else {
		             out.println("UNIT TEST 1:  FAILED\n");
		             errors++;
	       }
	    // END UNIT TEST 1:
            // BEGIN UNIT TEST 2:
               out.println("UNIT TEST 2:  setContent(Object, String)");

               mob.setContent(mmp, "application/multipart");      // API TEST
               content = mob.getContent();

               if(( content != null ) && ( content instanceof String )) {
                    if( ((String)content).equals("text/html") )
                        out.println("UNIT TEST 2:  passed\n");
                    else {
                          out.println("UNIT TEST 2:  FAILED\n");
                          errors++;
                    }
               } else if(( content != null ) && ( content instanceof Multipart )) {
                            out.println("This is a Multipart");
                            Multipart mp2 = (Multipart)content;
                            int count = mp2.getCount();
                            out.println("UNIT TEST 2:  passed\n");
                      } else {
				out.println("UNIT TEST 2:  FAILED\n");
				errors++;
               }
            // END UNIT TEST 2:
	       checkStatus();

        } catch ( Exception e ) {
	       handlException(e);
        }
	return status;
     }
}
