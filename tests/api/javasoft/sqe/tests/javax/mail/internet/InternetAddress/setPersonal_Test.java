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

package javasoft.sqe.tests.javax.mail.internet.InternetAddress;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>setPersonal()</strong> API.
 * It does by invoking the test API and then checking
 * the type of the returned object.	<p>
 *
 *		set the personal name <p>
 * api2test: public setPersonal(String) <p>
 *		set the personal name and character set <p>
 * api2test: public setPersonal(String, String) <p>
 *
 * how2test: Invoke the setPersonal() APIs, then call getPersonal() method, if it returns a
 *	     non-null object of type String, then the testcase passes, otherwise it fails.
 */

public class setPersonal_Test extends MailTest {

    public static void main ( String argv[] )
    {
        setPersonal_Test test = new setPersonal_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class InternetAddress: setPersonal(String|String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder object!");
             }
	  // BEGIN UNIT TEST 1:
	     out.println("\nUNIT TEST 1:  setPersonal(String)");
	     InternetAddress addr1 = new InternetAddress();

	     addr1.setPersonal("the quick fox jumped over the lazy cow.");   // API TEST
	     String personal1 = addr1.getPersonal();

	     if( personal1 != null ) {
		 out.println(personal1);
                 out.println("UNIT TEST 1: passed\n");
	     } else {
		     out.println("UNIT TEST 1: FAILED\n");
		     errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.println("\nUNIT TEST 2:  setPersonal(String, String)");
	     InternetAddress addr2 = new InternetAddress();

	     addr2.setPersonal("hotmail ~!@,#$%^&*.+=,<.>?", "US-ASCII");    // API TEST
	     String personal2 = addr2.getPersonal();
	     
             if( personal2 != null ) {
		 out.println(personal2);
                 out.println("UNIT TEST 2: passed\n");
	     } else {
		     out.println("UNIT TEST 2: FAILED\n");
		     errors++;
	     }
	  // END UNIT TEST 2:
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
