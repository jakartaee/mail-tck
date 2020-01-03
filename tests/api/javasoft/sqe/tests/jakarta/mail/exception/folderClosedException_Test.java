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
 * This class tests the <strong>FolderClosedException()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * api2test: public FolderClosedException(Folder | String) <p>
 *
 * how2test: Try to get message from closed folder and if this results
 *	     in an FolderClosed exception, then this testcase passes.
 */

public class folderClosedException_Test extends MailTest {

    private Store store;

    public static void main( String argv[] )
    {
        folderClosedException_Test test = new folderClosedException_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class FolderClosedException: FolderClosedException()\n");

        try {
           // Connect to host server
              store = connect2host(protocol, host, user, password);

           // Get a Folder object
	      Folder root = getRootFolder(store);
              Folder folder = root.getFolder(mailbox);

              if( folder == null ) {
                  return Status.failed("Invalid folder object!");
              }
           // BEGIN UNIT TEST 1:
	      out.println("UNIT TEST 1: FolderClosedException(Folder | String)");
	      // XXX - this only really tests IllegalStateException because
	      // there's no way to force a FolderClosedException, which only
	      // happens when the folder closes "unexpectedly".

              Message msg = folder.getMessage(1);	// API TEST

              if( msg != null )
                  out.println("UNIT TEST 1: FAILED.\n");

           // END UNIT TEST 1:
	      status = Status.failed(" Failed to catch FolderClosedException ");

        } catch ( FolderClosedException fe ) {
		out.println("UNIT TEST 1: passed.\n");
		try {
		      store.close();
		      ExceptionTest(fe);
		} catch ( MessagingException e ) {
		      handlException(e);
		}
	} catch ( java.lang.IllegalStateException ise ) {
		out.println("UNIT TEST 1: passed.\n");
                try {
                      store.close();
                      ExceptionTest(ise);
                } catch ( MessagingException e ) {
		      handlException(e);
		}
        } catch ( MessagingException me ) {
		handlException(me);
	}
	return status;
     }
}
