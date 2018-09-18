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
 * This class tests the <strong>getPersonal()</strong> API.
 * It does by invoking the test API and then checking
 * the type of the returned object.	<p>
 *
 *		get the personal name <p>
 * api2test: public String getPersonal() <p>
 *
 * how2test: Invoke the getPersonal() APIs, if the method returns a non-null
 *	     object of type String, then the testcase passes, otherwise it fails.
 */

public class getPersonal_Test extends MailTest {

    public static void main ( String argv[] )
    {
        getPersonal_Test test = new getPersonal_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class InternetAddress: getPersonal()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if( folder == null ) {
                 return Status.failed("Invalid folder");
             }
	  // BEGIN UNIT TEST 1:
	     out.println("\nUNIT TEST 1:  getPersonal()");
	     InternetAddress addr = new InternetAddress();

	     addr.setPersonal("the quick fox jumped over the lazy cow.");
	     String personal1 = addr.getPersonal();	// API TEST
	     
	     addr.setPersonal("hotmail ~!@,#$%^&*.+=,<.>?", "US-ASCII");
	     String personal2 = addr.getPersonal();	// API TEST
	     
             if(( personal1 != null && personal2 != null )) {
		  out.println(personal1);
		  out.println(personal2);
                  out.println("UNIT TEST 1: passed\n");
	     } else {
		   out.println("UNIT TEST 1: FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST 1:
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
