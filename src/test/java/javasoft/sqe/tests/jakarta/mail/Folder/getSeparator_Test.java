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
 * This class tests the <strong>getSeparator()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * Return the delimiter character that separates this Folder's pathname <p>
 * api2test: public char getSeparator()  <p>
 * api2test: public char getSeparator() on a closed folder <p>
 *
 * how2test: Call this API. If the type of object returned is 'char' <p>
 *	     then this testcase passes, otherwise it fails. <p>
 *	     This method can be invoked on a closed Folder.
 */

public class getSeparator_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: getSeparator()\n");

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
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1: getSeparator()");

	     char separator = folder.getSeparator();	// API TEST
	     out.fine("Delimiter character that separates this " +
                     "Folder's pathname is " + separator);

	     if (( separator == '/' ) || ( separator == '\\' ) || ( separator == '.' ))
                   out.fine("UNIT TEST 1: passed\n");
             else {
                     out.fine("UNIT TEST 1: FAILED\n");
                     errors++;
	     }
	  // END UNIT TEST 1:
             
          // BEGIN UNIT TEST 2:
             out.fine("UNIT TEST 2: IMAP specific test: getSeparator() " +
                         "on a closed folder");
             Folder childfolder = root.getFolder(mailbox);
             if (childfolder == null) 
                 return Status.failed("Invalid folder object!");
             if (childfolder.isOpen()) childfolder.close(false);
	     separator = childfolder.getSeparator();	// API TEST
	     out.fine("Delimiter character that separates the Child " +
                     "Folder's pathname is " + separator);

	     if (( separator == '/' ) || ( separator == '\\' ) || ( separator == '.' ))
                   out.fine("UNIT TEST 2: passed\n");
             else { 
                   out.fine("UNIT TEST 2: FAILED\n");
                   errors++;
	     }
          // END UNIT TEST 2:
                          
	     folder.close(false);
	     store.close();
             checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
}
