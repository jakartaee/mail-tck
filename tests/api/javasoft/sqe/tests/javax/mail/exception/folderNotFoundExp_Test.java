/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.javax.mail.exception;

import java.io.*;
import javax.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>FolderNotFoundException()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * api2test: public FolderNotFoundException(String, Folder | void) <p>
 *
 * how2test: Try to open non-existing folder and if this results in
 *	     an FolderNotFound exception, then this testcase passes.
 */

public class folderNotFoundExp_Test extends MailTest {

    public static void main( String argv[] )
    {
        folderNotFoundExp_Test test = new folderNotFoundExp_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options
	mailbox="xyzfolder";

        out.println("\nTesting class FolderNotFoundException: FolderNotFoundException()\n");

        try {
           // Connect to host server
              Store store = connect2host(protocol, host, user, password);

           // Get a Folder object
              Folder folder = store.getFolder(mailbox);

              if( folder == null ) {
                  return Status.failed("Invalid folder object!");
              }
           // BEGIN UNIT TEST 1:
	      out.println("UNIT TEST 1: FolderNotFoundException(String, Folder)");
	      folder.open(Folder.READ_ONLY);	    // API TEST
           // END UNIT TEST 1:

	      status = Status.failed(" Failed to catch FolderNotFoundException ");
        } catch ( FolderNotFoundException e ) {
		out.println("UNIT TEST 1: passed.\n");
		ExceptionTest(e);
	} catch ( MessagingException me ) {
                handlException(me);
        }
	return status;
     }
}
