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

package javasoft.sqe.tests.jakarta.mail.URLName;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>URLName()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Construct a URLName from the string. <p>
 * api2test: public URLName(String)  <p>
 *
 *		Creates a URL object from the specified info. <p>
 * api2test: public URLName(String,String,int,String,String,String)  <p>
 *
 *		Construct a URLName from a java.net.URL object. <p>
 * api2test: public URLName(URL)  <p>
 *
 * how2test: Call these constructors, verify the type of object created is
 *	     URLName. If so then this testcase passes, otherwise it fails.<p>
 *	     Note: This testcase is not currently run.
 */

public class urlName_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class URLName: URLName(..)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1:  URLName(String)");
	     URLName url1 = new URLName(protocol+"://"+user+":"+password+"@"+host+"/");  // API TEST

	     if( url1 != null )
		 out.fine("UNIT TEST 1: passed");
	     else {
		    out.fine("UNIT TEST 1: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.fine("UNIT TEST 2:  URLName(protocol,host,0,mailbox,user,password)");
             URLName url2 = new URLName(protocol,host,0,mailbox,user,password);   // API TEST

             if( url2 != null )
                 out.fine("UNIT TEST 2: passed");
             else {
                    out.fine("UNIT TEST 2: FAILED");
                    errors++;
             }
          // END UNIT TEST 2:
/*	  // BEGIN UNIT TEST 3:
             out.fine("UNIT TEST 3:  URLName(URL)");

             URLName url3 = new URLName( URL object );   // API TEST

             if( url3 != null )
                 out.fine("UNIT TEST 3: passed");
             else {
                    out.fine("UNIT TEST 3: FAILED");
                    errors++;
             }
          // END UNIT TEST 3:
*/
             checkStatus();
        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
