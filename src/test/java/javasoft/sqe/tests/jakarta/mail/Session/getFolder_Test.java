/*
 * Copyright (c) 2002, 2021 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.mail.Session;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getFolder(URLName)</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the object returned.  <p>
 *
 * Test getting a closed Folder object for the given URL. If the requested Folder object
 * cannot be obtained, null is returned. <p>
 *
 * api2test: public Folder getFolder(URLName)  <p>
 *
 * how2test: Test getting a existing closed Folder object for the given URLName.
 *	     Pass a valid URLName object, if this API returns a valid Folder object
 *	     then the test passes. Do this for non-existing folder as well.
 */

public class getFolder_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

	out.println("\nTesting class Session: getFolder(URLName)\n");

        try {
          // BEGIN UNIT TEST 1:
	     // Get Session object
             Session session = Session.getInstance(properties, null);

             String fqhost = portnum > 0 ? host + ":" + portnum : host;
             String rootprefix = (rootpath != null && rootpath.length() > 0) ? rootpath + "." : rootpath;

             out.println("UNIT TEST 1: getFolder("+protocol+"://"+user+":"+password+"@"+fqhost+"/"+rootprefix+testbox+")");
	     URLName urlname = new URLName(protocol, host, portnum, rootprefix+testbox, user, password);

	     // Get folder for specified URL name
             Folder folder = session.getFolder(urlname); // API TEST

             if( folder != null ) {
		 if( ! folder.exists() )
                     out.println("UNIT TEST 2:  passed\n");
                 else {
                       out.println("UNIT TEST 2:  FAILED\n");
                       errors++;
             	 }
	     }
          // END UNIT TEST 1:
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
