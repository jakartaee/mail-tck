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
 * This class tests the <strong>renameTo(Folder)</strong> API.
 * It does this by passing various valid input values and then checking
 * the value of the returned object.	<p>
 *
 *		    Rename this Folder <p>
 * api2test: public boolean renameTo(Folder name)  <p>
 *
 * how2test: Call this API with new folder name string, then check the boolean value
 *	     returned, if its TRUE then testcase passes, otherwise it fails <p>
 *
 *	  a) Test using various input names/strings. <p>
 *	  b) This method will succeed only on a closed Folder.
 */

public class renameTo_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class Folder: renameTo(Folder)\n");

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
	     out.println("UNIT TEST 1: renameTo("+testbox+")");

	     String oldName = folder.getFullName();
	     out.println("Old folder name is: " + oldName);

	  // Get a new Folder object
             Folder newFolder = root.getFolder(testbox);

             if( newFolder == null ) {
                 return Status.failed("Invalid new folder object!");
             }

	     if (newFolder.exists())
		newFolder.delete(false);

	     if( folder.renameTo(newFolder) )		// API TEST
	     {
		 out.println("New folder name is: "+ newFolder.getFullName());

		 if ( !(oldName.equals(newFolder.getFullName())) )
                       out.println("UNIT TEST 1: passed\n");
		 else {
		       out.println("UNIT TEST 1: FAILED\n");
                       errors++;
                 }
		 // rename it back to original name
		 newFolder.renameTo(folder);	// API TEST
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
