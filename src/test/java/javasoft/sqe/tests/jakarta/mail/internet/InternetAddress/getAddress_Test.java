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
 * This class tests the <strong>getAddress()</strong> API.
 * It does by invoking the test API and then checking
 * the type of the returned object.	<p>
 *
 *		get the email address  <p>
 * api2test: public String getAddress()  <p>
 *
 * how2test: Invoke the getAddress() API, if it returns returns a non-null object
 *	     of type String, then the testcase passes, otherwise it fails.
 */

public class getAddress_Test extends MailTest {

    private String[] addrlist = { "ksnijjar@eng", "ksnijjar@eng.sun.com", "French@physicists",
                                  "cannot@waste", "us/@mhs-mci.ebay", "it@is","tower@ihug.co.nz",
                                  "root@mxrelay.lanminds.com", "javaworld", "xx.zzz12@fea.net",
                                  "javamail-api-eng@icdev", "ksnijjar@java-test.Eng.Sun.COM"
                                };

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class InternetAddress: getAddress()\n");

        try {
	  // BEGIN UNIT TEST

             for( int i = 0; i < addrlist.length; i++ )
             {
                // Create the Address object
                InternetAddress addr = new InternetAddress(addrlist[i]);

                if( addr == null ) {
                    log.println("WARNING: FAILED TO CREATE ADDRESS OBJECT: "+ (i+1));
                    continue;
                }
                out.println("\nUNIT TEST :"+(i+1)+" getAddress()");

		String strname = addr.getAddress();	// API TEST

		if( strname != null ) {
		    if( strname.equals(addrlist[i]) ) {
		        out.println("Address = "+ strname);
			out.println("UNIT TEST "+ (i+1) +": passed\n");
		    } else {
			    out.println("UNIT TEST "+ (i+1) +": FAILED\n");
			    errors++;
			  }
		} else
		      continue;
	     }
          // END UNIT TEST:
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
