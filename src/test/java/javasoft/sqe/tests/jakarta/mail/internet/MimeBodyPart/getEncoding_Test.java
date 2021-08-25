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
import jakarta.activation.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.util.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getEncoding()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * This class also tests use of the EncodingAware interface. <p>
 *
 *		Returns the value of the "Content-Transfer-Encoding" header field. <p>
 * api2test: public String getEncoding()  <p>
 *
 * how2test: Call API, if it returns a string value then it passes, otherwise it fails.
 */

public class getEncoding_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeBodyPart: getEncoding()\n");

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

	     // XXX - this is a pretty lame test...

	     int i;
             for( i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg = (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
	        out.fine("UNIT TEST "+ i +":  getEncoding()");

		// Get the "type" of content
	        Object content = msg.getContent();

		if ( content instanceof Multipart ) {
		     int bodycount = ((MimeMultipart)content).getCount();
		     for( int k = 0; k < bodycount; k++ )
		     {
		     	  BodyPart bp = ((MimeMultipart)content).getBodyPart(k);
			 // get the "Content-Transfer-Encoding" header field
			  String encode = ((MimeBodyPart)bp).getEncoding();	// API TEST

			  if ( encode != null ) {
			       out.fine("Encode for bodypart is "+ encode);
			       out.fine("UNIT TEST "+ i +":  passed\n");
			  } else {
				out.fine("Warning: content-encode field not defined!");
				out.fine("UNIT TEST "+ i +":  passed\n");
			  }
		     }
		}
	     // END UNIT TEST:
	     }

	    Session session = createSession();

	 // BEGIN UNIT TEST:
	    out.fine("UNIT TEST "+ i +":  getEncoding()");
	    i++;

	    MimeMessage msg = new MimeMessage(session);
	    MimeMultipart mp = new MimeMultipart();
	    MimeBodyPart bp = new MimeBodyPart();
	    DataSource ds = new ByteArrayDataSource("test", "text/plain");
            bp.setDataHandler(new DataHandler(ds));
	    mp.addBodyPart(bp);
	    msg.setContent(mp);
	    msg.saveChanges();	// force headers to be updated

	    if (bp.isMimeType("text/plain") &&
		    bp.getEncoding().equals("7bit")) {
                out.fine("UNIT TEST passed.");
            } else {
		out.fine("content type = " + bp.getContentType());
		out.fine("encoding = " + bp.getEncoding());
                out.fine("UNIT TEST FAILED");
	        errors++;
            }

	 // END UNIT TEST:

	 // BEGIN UNIT TEST:
	    out.fine("UNIT TEST "+ i +":  EncodingAware.getEncoding()");
	    i++;

	    msg = new MimeMessage(session);
	    mp = new MimeMultipart();
	    bp = new MimeBodyPart();
	    ds = new Base64DataSource("test", "text/plain");
            bp.setDataHandler(new DataHandler(ds));
	    mp.addBodyPart(bp);
	    msg.setContent(mp);
	    msg.saveChanges();	// force headers to be updated

	    if (bp.isMimeType("text/plain") &&
		    bp.getEncoding().equals("base64")) {
                out.fine("UNIT TEST passed.");
            } else {
		out.fine("content type = " + bp.getContentType());
		out.fine("encoding = " + bp.getEncoding());
                out.fine("UNIT TEST FAILED");
	        errors++;
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

class Base64DataSource extends ByteArrayDataSource implements EncodingAware {
    public Base64DataSource(String text, String type) throws IOException {
	super(text, type);
    }

    // implements EncodingAware.getEncoding
    public String getEncoding() {
	return "base64";
    }
}
