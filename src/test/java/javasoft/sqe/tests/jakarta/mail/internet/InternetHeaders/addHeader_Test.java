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

package javasoft.sqe.tests.jakarta.mail.internet.InternetHeaders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addHeaders()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	     Add a header with the specified name and value to the end of the header list. <p>
 * api2test: public void addHeader(String, String); <p>
 *
 * how2test: Call API with various arguments, then verify by calling getHeader() api and
 *	     comparing the 'add/'get' values. If they are equal then testcase passes,
 *	     otherwise it fails.
 */

public class addHeader_Test extends MailTest {

    public static String[] head = { "Date","From","Subject","To","Cc" };
    public static String[] value = { "Fri Dec  5 17:48:51 PST 1997-1998","test120&@sun.com","JavaMail testing","Javamail@sun.com","xyz@aol.com" };
    
    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class InternetHeaders: addHeader(String, String)\n");

        try {
	  // create internetheader object
	     InternetHeaders ih = new InternetHeaders();
		  
	     if( ih == null ) {
		 return Status.failed("Warning: failed to created InternetHeaders object");
	     }
	  // BEGIN UNIT TEST:
	     // add header 'name|value' pairs

	     for( int i = 0; i < 5; i++ )
	     {
		  ih.addHeader(head[i], value[i]);	// API TEST
	     }
	     // compare the set and get 'value'

	     for( int j = 0; j < 5; j++ )
	     {
		  out.fine("UNIT TEST "+ (j+1) +":  addHeader("+ head[j] +", "+ value[j] +")");

		  String[] header = ih.getHeader(head[j]);

		  if( header[0].equals(value[j]) )
		      out.fine("UNIT TEST "+ (j+1) +": passed");
		  else {
			out.fine("The value returned: "+ header[0]);
			out.fine("UNIT TEST "+ (j+1) +": FAILED");
			errors++;
		  }
	     }
	  // END UNIT TEST:

             checkStatus();
        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
