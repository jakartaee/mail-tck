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

package javasoft.sqe.tests.jakarta.mail.FetchProfile;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>contains()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Returns true if the fetch profile contains given special item. <p>
 * api2test: public void contains(FetchProfile.Item)  <p>
 *
 *	    Returns true if the fetch profile contains given header name. <p>
 * api2test: public void contains(String)  <p>
 *
 * how2test: Call these APIs, check the returned boolean value. If it
 *	     returns TRUE then this testcase passes, otherwise it fails.
 */

public class contains_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class FetchProfile: contains(FetchProfile.Item | String)\n");

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

	   // Create an empty FetchProfile
	      FetchProfile fp = new FetchProfile();
	     
	      if( fp == null ) {
		  return Status.failed("Failed to create an empty FetchProfile object!");
	      }
	   // Get all the messages
	      Message[] msgs = folder.getMessages();

	   // BEGIN UNIT TEST 1:

	      out.fine("UNIT TEST 1: contains(FetchProfile.Item.ENVELOPE)");

	      fp.add(FetchProfile.Item.ENVELOPE);

	      if( fp.contains(FetchProfile.Item.ENVELOPE) )	// API TEST
		  out.fine("UNIT TEST 1: passed.\n");
	      else {
		    out.fine("UNIT TEST 1: FAILED.\n");
		    errors++;
	      }
	   // END UNIT TEST 1:
	   // BEGIN UNIT TEST 2:

	      out.fine("UNIT TEST 2: contains(FetchProfile.Item.FLAGS)");

	      fp.add(FetchProfile.Item.FLAGS);

              if( fp.contains(FetchProfile.Item.FLAGS) )	// API TEST
                  out.fine("UNIT TEST 2: passed.\n");
              else {
                    out.fine("UNIT TEST 2: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 2:
	   // BEGIN UNIT TEST 3:

	      out.fine("UNIT TEST 3: contains(FetchProfile.Item.CONTENT_INFO)");

	      fp.add(FetchProfile.Item.CONTENT_INFO);

              if( fp.contains(FetchProfile.Item.CONTENT_INFO) ) 	// API TEST
                  out.fine("UNIT TEST 3: passed.\n");
              else {
                    out.fine("UNIT TEST 3: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 3:
	   // BEGIN UNIT TEST 4:

	      out.fine("UNIT TEST 4: contains(Subject)");

	      fp.add("Subject");

              if( fp.contains("Subject") )	// API TEST
                  out.fine("UNIT TEST 4: passed.\n");
              else {
                    out.fine("UNIT TEST 4: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 4:
	   // BEGIN UNIT TEST 5:

              out.fine("UNIT TEST 5: contains(From)");

              fp.add("From");

              if( fp.contains("From") ) 	// API TEST
                  out.fine("UNIT TEST 5: passed.\n");
              else {
                    out.fine("UNIT TEST 5: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 5:
	   // BEGIN UNIT TEST 6:

              out.fine("UNIT TEST 6: contains(X-mailer)");

              fp.add("X-mailer");

              if( fp.contains("X-mailer") )	// API TEST
                  out.fine("UNIT TEST 6: passed.\n");
              else {
                    out.fine("UNIT TEST 6: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 6:

	      folder.fetch(msgs, fp);
	      folder.close(false);
	      store.close();
              checkStatus();

        } catch ( Exception e ) {
              handlException(e);
        }
	return status;
     }
}
