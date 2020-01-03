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

package javasoft.sqe.tests.jakarta.mail.exception;

import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>StoreClosedException()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * api2test: public StoreClosedException(Store | String) <p>
 *
 * how2test: Try to get message from closed folder/store, if this results
 *	     in an StoreClosed exception, then this testcase passes.
 */

public class storeClosedException_Test extends MailTest {

    private Folder folder;

    public static void main( String argv[] )
    {
        storeClosedException_Test test = new storeClosedException_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options
        out.println("\nTesting class StoreClosedException: StoreClosedException()\n");

        try {
           // Connect to host server
              Store store = connect2host(protocol, host, user, password);

           // Get a Folder object
	      Folder root = getRootFolder(store);
              folder = root.getFolder(mailbox);

              if( folder == null ) {
                  return Status.failed("Invalid folder object!");
              }
	      folder.open(Folder.READ_ONLY);
	      store.close();

	   // Wait for notification to occur
	      Thread.sleep(5000);

           // BEGIN UNIT TEST 1:
	      out.println("UNIT TEST 1: StoreClosedException(Store | String)");

              root.getFolder(mailbox);	    // API TEST

              out.println("UNIT TEST 1: FAILED.\n");
           // END UNIT TEST 1:

	      status = Status.failed(" Failed to catch StoreClosedException ");
        } catch ( StoreClosedException sce ) {
		out.println("UNIT TEST 1: passed.\n");
		try {
		      folder.close(false);
		      ExceptionTest(sce);
		} catch ( MessagingException e1 ) {
		      handlException(e1);
		}
        } catch ( Exception e2 ) {
		handlException(e2);
	}
	return status;
     }
}
