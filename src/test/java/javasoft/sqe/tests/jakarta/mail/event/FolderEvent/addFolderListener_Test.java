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

package javasoft.sqe.tests.jakarta.mail.event.FolderEvent;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.event.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addFolderListener()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type/value of the returned object.	<p>
 *
 *              Listen and notifies of changes to a folder. <p>
 * api2test: public addFolderListener(FolderListener)  <p>
 *
 * how2test: Call this API. Then verify that that the notification occur when
 *           changes occur to a folder. If this happens then this test passed
 *           otherwise it fails.
 */

public class addFolderListener_Test extends MailTest implements FolderListener {

    boolean return_code;
    boolean created = false;
    boolean deleted = false;
    boolean renamed = false;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }


    public void folderCreated(FolderEvent e)
    {
	out.fine("\nFolder created successfully!");
	created = true;
    }

    public void folderDeleted(FolderEvent e)
    {
        out.fine("\nFolder deleted successfully!");
        deleted = true;
    }

    public void folderRenamed(FolderEvent e)
    {
        out.fine("\nFolder renamed successfully!");
        renamed = true;
    }

    public Status run()
    {
	

        out.fine("\nTesting class FolderEvent: addFolderListener(FolderListener)");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
	     Folder folder1 = root.getFolder("testfolder");

	     if( folder1 == null ) {
                 return Status.failed("Invalid testfolder object!");
             }
          // BEGIN UNIT TEST:
	     out.fine("UNIT TEST 1: addConnectionListener(ConnectionListener)\n");

	     // make sure the folder doesn't exist before we start
	     folder1.delete(false);
	     
	     folder1.addFolderListener(this);	    // API TEST

	     if( !(folder1.exists()) )
	     {
		  return_code = folder1.create(Folder.HOLDS_MESSAGES);

		  if( return_code ) {
		      Folder folder2 = root.getFolder("newfolder");

		      if( folder2 == null ) {
			  return Status.failed("Invalid newfolder object!");
                      }
		      folder2.addFolderListener(this);
		      folder1.renameTo(folder2);
		      folder2.delete(false);
		  }
	     }
	     // added so that noticification has time to update
	     Thread.sleep(1000);

	     if(( created && renamed ) && ( deleted ))
		  out.fine("UNIT TEST 1:  passed\n");
	     else {
		   out.fine("Failed to invoke FolderListener events!");
		   out.fine("UNIT TEST 1:  FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST:

	     folder1.removeFolderListener(this);
	     store.close();
	     checkStatus();
        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
