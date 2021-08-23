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

package javasoft.sqe.tests.jakarta.mail.Folder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>fetch(..)</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the returned object.  <p>
 *
 *		Prefetch the items specified in the FetchProfile for the given Messages. <p>
 * api2test: public void fetch(Message msgs[], FetchProfile fp) throws MessagingException <p>
 *
 * how2test: Call this API to fetch a profile for the given messages. Then to verify <p>
 *	     this operation invoking 'get...' methods on message objects. If all of <p>
 *	     this is successfull then the test passes otherwise it fails.
 */

public class fetch_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: fetch(Message msgs[], FetchProfile fp)\n");

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
	  // BEGIN UNIT TEST:

	     FetchProfile fp = new FetchProfile();
             fp.add(FetchProfile.Item.ENVELOPE);
             fp.add("X-mailer");

	     Message[] msgs = folder.getMessages();
             folder.fetch(msgs, fp);	// API TEST
	     
	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     
             for (int i = 0; i < msgcount; i++)
	     {
		  out.fine("UNIT TEST " + i + ": fetch(msgs["+i+"], fp)\n");

		  if ( msgs[i] != null && ( msgs[i] instanceof Message ) ) {
		       out.fine(msgs[i].getReceivedDate().toString());
                       out.fine(msgs[i].getFrom().toString());
                       out.fine(msgs[i].getSubject().toString());
                       if (msgs[i].getHeader("X-mailer") != null) {
                           out.fine(msgs[i].getHeader("X-mailer").toString());
                       }

		       out.fine("UNIT TEST "+ i +": passed\n");
		  } else {
			  out.fine("UNIT TEST "+ i +": FAILED\n");
			  errors++;
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
