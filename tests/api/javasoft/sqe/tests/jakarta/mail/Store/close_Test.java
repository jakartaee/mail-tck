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

package javasoft.sqe.tests.jakarta.mail.Store;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>close()</strong> API.
 * It closes this store and terminates connection.  <p>
 *
 *		Closes this store and terminates connection. <p>
 * api2test: public void close()   <p>
 *
 * how2test: Call the 'close()' API. If this operation is successfull <p>
 *	     then the test passes otherwise it fails.
 */

public class close_Test extends MailTest {

    public static void main( String argv[] )
    {
        close_Test test = new close_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Store: close()\n");

        try {
          // Connect to host server
             Store istore = connect2host(protocol, host, user, password);

          // Open a Folder. Don't create if it does not exist
	     Folder root = getRootFolder(istore);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1: close()");

             istore.close();		// API TEST
             out.println("UNIT TEST 1:  passed\n");

          // END UNIT TEST 1:
             status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
