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
 * This class tests the <strong>open(int)</strong> API.
 * It does this by passing various valid input values then using
 * isOpen() api to test if open() method did its job.	<p>
 *
 *		Open this Folder  <p>
 * api2test: public void open(int mode)  <p>
 *
 * how2test: Call this API on a folder object. Then call 'isOpen()' on this <p>
 *	     same folder object, if this second API returns a TRUE value then
 *	     then testcase passes, otherwise it fails. <p>
 *
 *	  a) This method is valid only on Folders that can contain Messages. <p>
 *	  b) The effect of opening multiple connections to the same folder on
 *	     a specifc Store is implementation dependent.
 */

public class open_Test extends MailTest {

    public static void main( String argv[] )
    {
        open_Test test = new open_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Folder: open(int)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
	         return Status.failed("Invalid folder object!");
       	     }
	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1: open(READ_ONLY)");

	     boolean notOpen = folder.isOpen();
	     folder.open(Folder.READ_ONLY);		// API TEST
	     boolean nowOpen = folder.isOpen();

	     if ((! notOpen) && nowOpen )
                 out.println("UNIT TEST 1: passed\n");
             else {
                  out.println("UNIT TEST 1: FAILED\n");
                  errors++;
             }
	     folder.close(false);
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.println("UNIT TEST 2: open(READ_WRITE)");

	     notOpen = folder.isOpen();
	     folder.open(Folder.READ_WRITE);		// API TEST
	     nowOpen = folder.isOpen();

	     if(( ! notOpen) && nowOpen )
                  out.println("UNIT TEST 2: passed\n");
             else {
                   out.println("UNIT TEST 2: FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST 2:
             folder.close(false);
	     store.close();
             checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
