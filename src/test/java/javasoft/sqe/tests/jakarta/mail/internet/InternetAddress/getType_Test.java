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

package javasoft.sqe.tests.jakarta.mail.internet.InternetAddress;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getType()</strong> API.
 * It does by invoking the test API and then checking
 * the type of the returned object.  <p>
 *
 *		get the type of address  <p>
 * api2test: public String getType()  <p>
 *
 * how2test: Invoke the getType() API, if it returns returns a non-null object
 *	     of type String, then the testcase passes, otherwise it fails.
 */

public class getType_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class InternetAddress: getType()\n");

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

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
          // BEGIN UNIT TEST:
	     
	     for( int i = 1; i <= msgcount; i++ )
	     {
	     // Get a Message object
		Message msg = folder.getMessage(i);

		if( msg == null ) {
                    log.warning("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
                    continue;
		}
		// Get a From address object(s)
		Address[] addrs = msg.getFrom();

		if( addrs == null || addrs.length == 0 ) {
                    log.warning("WARNING: FAILED TO GET FROM ADDRESS FOR MESSAGE NUMBER: "+ i);
                    continue;
		}

		InternetAddress addr = (InternetAddress)addrs[0];
		String addtype = addr.getType();	// API TEST

		if ( addtype != null && addtype.equals("rfc822") )
		    out.fine("UNIT TEST "+ i +": passed\n");
		else {
		    out.fine("UNIT TEST "+ i +": FAILED\n");
		    errors++;
		}
	     }

	     // create new internet address object
	     InternetAddress addr = new InternetAddress("joe@example.com");

	     String addtype = addr.getType();	// API TEST

	     if ( addtype != null && addtype.equals("rfc822") )
		  out.fine("UNIT TEST "+ (msgcount+1) +": passed\n");
	     else {
		  out.fine("UNIT TEST "+ (msgcount+1) +": FAILED\n");
		  errors++;
	     }
          // END UNIT TEST:
	     folder.close(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
