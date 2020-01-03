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

package javasoft.sqe.tests.jakarta.mail.Folder;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getFolder(String)</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Return the Folder object corresponding to the given name <p>
 * api2test: public Folder getFolder(String name) throws MessagingException <p>
 *
 * how2test: Call this API with various parameters. Verify that the type of the <p>
 *	     object returned if its Folder, then the testcase passes, otherwise <p>
 *	     it fails. <p>
 *
 *	  a) Note that this folder does not physically have to exist in the Store. <p>
 *           [ The exists() method on a Folder indicates whether it really exists on
 *	       the Store. ] <p>
 *
 *	  b) name can be an absolute path if it starts with the hierarchy delimiter. <p>
 *	     Otherwise, it is interpreted relative to this Folder. <p>
 *
 *	  c) Invoking this method on the same name multiple times will return that <p>
 *	     many distinct Folder objects.  <p>
 *
 *	  d) This method can be invoked on a closed Folder.
 */

public class getFolder_Test extends MailTest {

    public static void main( String argv[] )
    {
        getFolder_Test test = new getFolder_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Folder: getFolder(String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

	  // BEGIN UNIT TEST 1:
	     // Invoked on closed an existing mail folder
	     out.println("UNIT TEST 1: getFolder(" + mailbox + ")");

             // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder_1 = root.getFolder(mailbox);	// API TEST

             if ( folder_1 != null )
	         out.println("UNIT TEST 1: passed\n");
             else {
	             out.println("Invalid folder name");
                     out.println("UNIT TEST 1: FAILED\n");
                     errors++;
       	     }
	     folder_1.open(Folder.READ_ONLY);
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
	     // Invoked on an already open mail folder
             out.println("UNIT TEST 2: getFolder(" + mailbox + ")");

             // Get another Folder object related the same mail folder
             Folder folder_2 = root.getFolder(mailbox);	// API TEST

             if ( folder_2 != null )
                 out.println("UNIT TEST 2: passed\n");
             else {
                   out.println("Unable to get a second folder object for same folder name");
                   out.println("UNIT TEST 2: FAILED\n");
                   errors++;
             }
	     folder_1.close(false);
          // END UNIT TEST 2:
	  // BEGIN UNIT TEST 3:
	     // Invoke on "relative" to this folder test name

	     String testbox = "testerFolder";
             out.println("UNIT TEST 3: getFolder(" + testbox + ")");

          // Get a Folder object
             Folder folder_3 = root.getFolder(testbox);	// API TEST

	     if ( folder_3 != null ) {
		  if ( folder_3.create(Folder.HOLDS_MESSAGES) ) {
		       if ( folder_3.exists() ) {
			    folder_3.delete(false);
			    out.println("UNIT TEST 3: passed\n");
		       } else {
				out.println("Specified folder name does not exist!");
				out.println("UNIT TEST 3: FAILED\n");
				errors++;
			}
		  } else {
			  out.println("Failed to create specified folder.");
			  out.println("UNIT TEST 3: FAILED\n");
			  errors++;
		    }
	     } else {
			out.println("Failed to get specified folder.");
			out.println("UNIT TEST 3: FAILED\n");
			errors++;
	     }
	  // END UNIT TEST 3:
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
