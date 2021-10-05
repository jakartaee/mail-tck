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

package javasoft.sqe.tests.jakarta.mail.Store;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getFolder(..)</strong> API.
 * It does this by passing various valid input value and then checking the
 * type of the returned object.	<p>
 *
 *		Return the Folder object corresponding to the given name. <p>
 * api2test: public Folder getFolder(String) throws MessagingException	<p>
 *
 *		Return the Folder object corresponding to the given URL <p>
 * api2test: public Folder getFolder(URL) throws MessagingException	<p>
 *
 * how2test: Call getFolder(..) API with valid string/URL names. Test is <p>
 *	     considered passing if the object returned type is Folder,
 *	     otherwise it fails.
 */

public class getFolder_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Store: getFolder(String | URL)\n");

        try {
          // Connect to host server
             Store store_1 = connect2host(protocol, host, user, password);

          // BEGIN UNIT TEST 1:
             out.fine("UNIT TEST 1: getFolder(" + mailbox + ")");

          // Get a Folder.
	     Folder root = getRootFolder(store_1);
             Folder folder_1 = root.getFolder(mailbox);	// API TEST

             if(( folder_1 != null ) && ( folder_1 instanceof Folder ))
		  out.fine("UNIT TEST 1:  passed\n");
             else if( folder_1 == null ) {
		      out.fine("UNIT TEST 1:  FAILED\n");
		      return Status.failed("Invalid folder object!");
	     }
	     store_1.close();
          // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:

	  // Connect to host server
             Store store_2 = this.connect2host(protocol, host, user, password);

             out.fine("UNIT TEST 2: getFolder("+ protocol +"://"+ user +"@"+ host +"/"+ mailbox +")");

             Folder folder_2 = store_2.getFolder(new URLName(protocol +"://"+ user +"@"+ host +"/"+ mailbox));	// API TEST

	     if (( folder_2 != null ) && ( folder_2 instanceof Folder ))
		   out.fine("UNIT TEST 2:  passed\n");
             else if ( folder_2 == null ) {
		       out.fine("UNIT TEST 2:  FAILED\n");
		       return Status.failed("Invalid URL path");
	     }
             store_2.close();
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:

	  // Connect to host server
             Store store_3 = this.connect2host(protocol, host, user, password);

             out.fine("UNIT TEST 3: getFolder("+ protocol +"://"+ user +"@"+ host +"/testinbox)");

		// need to pass URLName object here !
             Folder folder_3 = store_3.getFolder(new URLName(protocol +"://"+ user +"@"+ host +"/testinbox")); // API TEST

             if ( folder_3 == null || ( folder_3 instanceof Folder ) )
                  out.fine("UNIT TEST 3:  passed\n");
             else {
                    out.fine("UNIT TEST 3:  FAILED\n");
                    errors++;
             }
	     store_3.close();
          // END UNIT TEST 3:
             status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
