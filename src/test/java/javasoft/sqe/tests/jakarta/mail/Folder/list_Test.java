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
 * This class tests the <strong>list(String)</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.  <p>
 *
 *		Convenience method that returns the list of folders under this Folder.<p>
 * api2test: public Folder list()   <p>
 *
 *	Returns a list of Folders belonging to this Folder's namespace that match
 *	the specified pattern. <p>
 * api2test: public Folder[] list(String)  <p>
 *
 * how2test: Call this API on a folder object. Test with various string parameters. <p>
 *	     If this method returns an array of folders object then it passes,
 *	     otherwise it fails. <p>
 *	     Patterns may contain the wildcard characters "%", which matches any <p>
 *	     character except hierarchy delimiters, and "*", which matches any
 *	     character. <p>
 *
 *		As an example, given the folder hierarchy: <p>
 *
 *         	Personal/		<p>
 *            	   Finance/		<p>
 *               	Stocks		<p>
 *               	Bonus		<p>
 *               	StockOptions	<p>
 *            	   Jokes		<p>
 *
 *		list("*") on "Personal" will return the whole hierarchy. 	<p>
 *		list("%") on "Personal" will return "Finance" and "Jokes". 	<p>
 *		list("Jokes") on "Personal" will return "Jokes".		<p>
 *		list("Stock*") on "Finance" will return "Stocks" and "StockOptions". <p>
 *
 *	     Invoking this method on the same pattern multiple times will return <p>
 *	     that many distinct Folder objects. This method can be invoked on a
 *	     closed Folder.
 */

public class list_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Folder: list(String)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

       	  // Get a Folder object
	     Folder folder = getRootFolder(store);

             if( folder == null ) {
	         return Status.failed("Invalid root path object!");
             }
          // BEGIN UNIT TEST 1:
             out.fine("UNIT TEST 1: list()");

	     Folder[] folderlist1 = folder.list();	// API TEST

	     if ( folderlist1.length > 0 ) {
                  for ( int i = 0; i < folderlist1.length; i++ )
                        out.fine(folderlist1[i].getName());

                  out.fine("UNIT TEST 1: passed\n");
	     } else {
                     out.fine("UNIT TEST 1: FAILED\n");
		     errors++;
	     }
          // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.fine("UNIT TEST 2: list(*)");

             Folder[] folderlist2 = folder.list("*");      // API TEST

             if ( folderlist2.length > 0 ) {
		  for ( int i = 0; i < folderlist2.length; i++ )
			out.fine(folderlist2[i].getName());

                  out.fine("UNIT TEST 2: passed\n");
             } else {
                     out.fine("UNIT TEST 2: FAILED\n");
                     errors++;
             }
          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
             out.fine("UNIT TEST 3: list(%)");

             Folder[] folderlist3 = folder.list("%");      // API TEST

             if ( folderlist3.length > 0 ) {
                  for ( int i = 0; i < folderlist3.length; i++ )
                        out.fine(folderlist3[i].getName());

                  out.fine("UNIT TEST 3: passed\n");
             } else {
                     out.fine("UNIT TEST 3: FAILED\n");
                     errors++;
             }
          // END UNIT TEST 3:
          // BEGIN UNIT TEST 4:
             out.fine("UNIT TEST 4: list("+ pattern +")");

             Folder[] folderlist4 = folder.list(pattern+"%");      // API TEST

             if( folderlist4.length > 0 ) {
                 for( int i = 0; i < folderlist4.length; i++ )
                      out.fine(folderlist4[i].getName());

                  out.fine("UNIT TEST 4: passed\n");
             } else {
                     out.fine("UNIT TEST 4: FAILED\n");
                     errors++;
             }
          // END UNIT TEST 4:

	     store.close();
             checkStatus();

	} catch ( Exception e) {
             handlException(e);
        }
	return status;
     }
}
