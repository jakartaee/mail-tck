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

package javasoft.sqe.tests.jakarta.mail.Address;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>toString()</strong> API.
 * It does this by invoking the test API and then checking
 * the type of the returned object.  <p>
 *
 *		Return a String representation of this address object.  <p>
 * api2test: public String toString()  <p>
 *
 * how2test: Invoke the toString() API, if it returns returns a String value,
 *	     then the testcase passes, otherwise it fails.
 */

public class toString_Test extends MailTest {

    public static void main ( String argv[] )
    {
        toString_Test test = new toString_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Address: toString()\n");

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

	     msgcount = folder.getMessageCount();
	     if( msgcount < 1 )
		 return Status.failed("Mail folder is empty!");

          // BEGIN UNIT TEST:

	     for( int i = 1; i <= msgcount; i++ )
	     {
	     // Get a Message object
		Message msg = folder.getMessage(i);

		if( msg == null ) {
                    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
                    continue;
		}
		// Get a From address object(s)
		Address[] addrs = msg.getFrom();

		if( addrs == null ) {
                    log.println("WARNING: FAILED TO GET FROM ADDRESS FOR MESSAGE NUMBER: "+ i);
                    continue;
		}
		out.println("UNIT TEST "+ i +": toString()");

		String tostr = addrs[0].toString();	// API TEST

		if( tostr != null ) {
		    out.println("Address string is "+ tostr);
		    out.println("UNIT TEST "+ i +": passed\n");
		} else {
			out.println("The address string is null!");
			out.println("UNIT TEST "+ i +": passed\n");
		}
	     }
          // END UNIT TEST:

	     folder.close(false);
	     store.close();
	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
