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

package javasoft.sqe.tests.jakarta.mail.FetchProfile;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>add()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Add the given special item as one of the attributes to be prefetched. <p>
 * api2test: public void add(FetchProfile.Item)  <p>
 *
 *	    Add the specified header-field to the list of attributes to be prefetched. <p>
 * api2test: public void add(String)  <p>
 *
 * how2test: Call these APIs, verify by calling contains()/getHeaderNames(), compare
 *	     values if the same then this testcase passes, otherwise it fails.
 */

public class add_Test extends MailTest {

    public static void main( String argv[] )
    {
        add_Test test = new add_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class FetchProfile: add(FetchProfile.Item | String)\n");

        try {
           // Connect to host server
              Store store = this.connect2host(protocol, host, user, password);

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

	      out.println("UNIT TEST 1: add(FetchProfile.Item.ENVELOPE)");

	      fp.add(FetchProfile.Item.ENVELOPE);	// API TEST

	      if( fp.contains(FetchProfile.Item.ENVELOPE) )
		  out.println("UNIT TEST 1: passed.\n");
	      else {
		    out.println("UNIT TEST 1: FAILED.\n");
		    errors++;
	      }
	   // END UNIT TEST 1:
	   // BEGIN UNIT TEST 2:

	      out.println("UNIT TEST 2: add(FetchProfile.Item.FLAGS)");

	      fp.add(FetchProfile.Item.FLAGS);		// API TEST

              if( fp.contains(FetchProfile.Item.FLAGS) )
                  out.println("UNIT TEST 2: passed.\n");
              else {
                    out.println("UNIT TEST 2: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 2:
	   // BEGIN UNIT TEST 3:

	      out.println("UNIT TEST 3: add(FetchProfile.Item.CONTENT_INFO)");

	      fp.add(FetchProfile.Item.CONTENT_INFO);	// API TEST

              if( fp.contains(FetchProfile.Item.CONTENT_INFO) )
                  out.println("UNIT TEST 3: passed.\n");
              else {
                    out.println("UNIT TEST 3: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 3:
	   // BEGIN UNIT TEST 4:

	      out.println("UNIT TEST 4: add(Subject)");

	      fp.add("Subject");	// API TEST

              if( fp.contains("Subject") )
                  out.println("UNIT TEST 4: passed.\n");
              else {
                    out.println("UNIT TEST 4: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 4:
	   // BEGIN UNIT TEST 5:

              out.println("UNIT TEST 5: add(From)");

              fp.add("From");        // API TEST

              if( fp.contains("From") )
                  out.println("UNIT TEST 5: passed.\n");
              else {
                    out.println("UNIT TEST 5: FAILED.\n");
                    errors++;
              }
	   // END UNIT TEST 5:
	   // BEGIN UNIT TEST 6:

              out.println("UNIT TEST 6: add(X-mailer)");

              fp.add("X-mailer");        // API TEST

              if( fp.contains("X-mailer") )
                  out.println("UNIT TEST 6: passed.\n");
              else {
                    out.println("UNIT TEST 6: FAILED.\n");
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
