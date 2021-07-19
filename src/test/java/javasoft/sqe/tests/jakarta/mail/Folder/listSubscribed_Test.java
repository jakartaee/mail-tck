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
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>listSubscribed(..)</strong> API.
 * It does this by invoking api under test and then checking
 * the value of the returned object.	<p>
 *
 *	Convenience method that returns the list of subscribed folders under this Folder <p>
 * api2test: public Folder[] listSubscribed() throws MessagingException <p>
 *
 * how2test: Call this API on a folder object. Check for the list folder objects <p>
 *	     returned. This method just calls the list(pattern) method with "%" as the <p>
 *	     match pattern. This method can be invoked on a closed/open Folder. <p>
 *
 *	Returns a list of subscribed Folders belonging to this Folder's namespace
 *	that match the specified pattern.	<p>
 * api2test: public Folder[] listSubscribed(String) throws MessagingException <p>
 *
 * how2test: Same as above, except that now we pass various string arguments to this
 *	     API. <p>
 *
 * NOTE: Make sure that you have the following directory structure on the server before
 * 	 you run this testcase.
 *<p>
 *	default-dir/ <p>
 *		test[?]/
 *<p>
 *	  a) The pattern can contain wildcards (in patttern) for list(). <p>
 *	  b) Invoking this method on the same pattern multiple times	<p>
 *	     will return that many distinct Folder objects.	<p>
 *	  c) This method can be invoked on a closed/open Folder.	<p>
 *	  d) If the folder does not support subscription, this method	<p>
 *	     should resolve to list() [(the default implementation
 *	     provided here, does just this).
 */

public class listSubscribed_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class Folder: listSubscribed(String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

	     if ( store == null ) {
                  return Status.failed("Invalid store object!");
             }
	  // Get the default (top-level) folder
	     Folder root = getRootFolder(store);

	     if ( root == null ) {
		  return Status.failed("Failed to get default folder!");
	     }
	  // Get test folder object
	     Folder tst = root.getFolder(pattern); // This MUST exist on server

	     if ( tst == null ) {
                  return Status.failed("Failed to get testfolder: "+pattern);
             }
	  // Now subscribe test folder

	     try {
	          tst.setSubscribed(true);
	     } catch (MethodNotSupportedException mex) {
		     out.println("Folder doesn't support subscriptions");
		     checkStatus();
		     return status;
	             //return Status.failed("This store doesn't support subscription");
	     } catch (MessagingException mex) {
		     return Status.failed("MessagingException caught!");
	     }
          // BEGIN UNIT TEST 1:
             out.println("UNIT TEST 1: listSubscribed()");

             Folder[] slist1 = root.listSubscribed();       // API TEST

             if ( slist1.length > 0 ) {
                  for ( int i = 0; i < slist1.length; i++ ) {
			if (slist1[i].getName().equals(pattern) ) {
                            out.println(slist1[i].getName());
                            out.println("UNIT TEST 1: passed\n");
			}
		  }
             } else {
                     out.println("UNIT TEST 1: FAILED\n");
                     errors++;
             }
          // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.println("UNIT TEST 2: listSubscribed("+pattern+")");

	     Folder[] slist2 = root.listSubscribed(pattern);	    // API TEST

	     if ( slist2.length > 0 ) {
                  for ( int i = 0; i < slist2.length; i++ ) {
			if (slist2[i].getName().equals(pattern)) {
                            out.println(slist2[i].getName());
			    out.println("UNIT TEST 2: passed\n");
			    break;
			}
		  }
             } else {
                     out.println("UNIT TEST 2: FAILED\n");
                     errors++;
             }
	  // END UNIT TEST 2:
	  // BEGIN UNIT TEST 3:
	     out.println("UNIT TEST 3: listSubscribed("+pattern+"%)");

	     Folder[] slist3 = root.listSubscribed(pattern+"%");	// API TEST

             if ( slist3.length > 0 ) {
                  for ( int i = 0; i < slist3.length; i++ ) {
			if (slist3[i].getName().equals(pattern)) {
                            out.println(slist3[i].getName());
                            out.println("UNIT TEST 3: passed\n");
			    break;
			}
		  }
             } else {
                   out.println("UNIT TEST 3: FAILED\n");
                   errors++;
             }
	  // END UNIT TEST 3:
          // BEGIN UNIT TEST 4:
             out.println("UNIT TEST 4: listSubscribed("+pattern+"*)");

             Folder[] slist4 = root.listSubscribed(pattern+"*");	  // API TEST

             if ( slist4.length > 0 ) {
                  for ( int i = 0; i < slist4.length; i++ ) {
			if (slist4[i].getName().equals(pattern) ) {
                           out.println(slist4[i].getName());
                           out.println("UNIT TEST 4: passed\n");
			   break;
			}
		  }
             } else {
                     out.println("UNIT TEST 4: FAILED\n");
                     errors++;
             }
          // END UNIT TEST 4:
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
