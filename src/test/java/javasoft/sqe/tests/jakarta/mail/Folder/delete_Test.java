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

package javasoft.sqe.tests.jakarta.mail.Folder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>delete(boolean)</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the returned object.  <p>
 *
 *		Delete this Folder. <p>
 * api2test: public boolean delete(boolean recurse)  <p>
 *
 * how2test: Call this API. Check the value of boolean object returned,
 *	     if its TRUE then test passes otherwise it fails. <p>
 *
 *	  a) This method will succeed only on a closed Folder.  <p>
 *
 *	     The recurse flag controls whether the deletion affects subfolders or not. <p>
 *	  b) if true, all subfolders are deleted, then this folder itself is deleted.  <p>
 *	  c) if false, the behaviour is dependent on the folder type. See javdoc info. <p>
 *
 *   status: returns a JCK status object.
 */

public class delete_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class Folder: delete(boolean)\n");

        try {
          // Connect to host server
             Store store = this.connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
	     Folder f1 = root.getFolder(testbox);

	     if( f1 == null ) {
                 return Status.failed("Invalid folder object!");
             }
          // BEGIN UNIT TEST 1:

	     out.println("UNIT TEST 1: delete(false)\n");

	     if ( !(f1.exists()) ) {
		  boolean return_code = f1.create(Folder.HOLDS_MESSAGES);
		  if ( return_code ) {

		       f1.delete(false);		// API TEST

		       if ( !(f1.exists()) )
			    out.println("UNIT TEST 1: passed\n");
		       else {
			     out.println("Failed to delete folder " + testbox);
			     out.println("UNIT TEST 1: FAILED\n");
			     errors++;
		  	}
		  }
	     }
	     store.close();

	  // END OF UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     // Get another Session/Store object
	     session = Session.getInstance(properties, null);
	     session.setDebug(debug);
	     store = session.getStore(protocol);

             // Connect
             if( host != null || user != null || password != null)
		 if (portnum > 0)
		     store.connect(host, portnum, user, password);
		 else
		     store.connect(host, user, password);
             else
                 store.connect();

	     // Get a test folder
	     Folder root2 = getRootFolder(store);

	     String testbox = "topdog";
             Folder f2 = root2.getFolder(testbox);

             if( f2 == null ) {
                 return Status.failed("Invalid testfolder object!");
             }

	     if( f2.exists() )
		 f2.delete(true);

	     if(!(f2.create(Folder.HOLDS_FOLDERS))) {
		return Status.failed("Failed to create "+testbox+" folder");
	     }

	     // Get another test folder
	     testbox = "tomcat";
             Folder f3 = f2.getFolder("tomcat");

             if( f3.create(Folder.HOLDS_MESSAGES) )
	     {
		 out.println("UNIT TEST 2: delete(true)\n");

	         f2.delete(true);    // API TEST

		 if( !(f2.exists()) )
		     out.println("UNIT TEST 2: passed\n");
		 else {
                        out.println("Failed to create directory "+testbox+" folder(s)");
                        out.println("UNIT TEST 2: FAILED\n");
                        errors++;
                 }
             }
          // END OF UNIT TEST 2:

	     store.close();
	     this.checkStatus();

        } catch ( Exception e ) {
                this.handlException(e);
        }
	return status;
     }
}
