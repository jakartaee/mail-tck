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

package javasoft.sqe.tests.jakarta.mail.Header;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>header()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *           	Construct a Header object.  <p>
 * api2test: public Header(String, String)  <p>
 *
 *		Returns the name of this header <p>
 * api2test: public String getName() <p>
 *
 *		Returns the value of this header <p>
 * api2test: public String getValue() <p>
 *
 * how2test: Call the Header() API with some various string parameters. The test is <p>
 *           considered passing if Header object is created. Then call getName() <p>
 *	     and getValue() APIs, if these return an expected string/value then <p>
 *	     this test is considered passing!
 */

public class header_Test extends MailTest {

    public static String hname[] =  {   "Message-ID","Date","To","Cc","Bcc","From","Reply-To",
					"Return-Path","Subject","X-Mailer","References",
					"MIME-Version","Content-Type","Content-Transfer-Encoding",
					"Content-Length","Content-MD5","Organization"
				    };

    public static String hvalue[] = {   "5421","Fri, 18 Jan 1997 00:14:58 +0100","ksnijjar@eng",
					"john.russo@eng","","ksnijjar@eng.sun.com","ksnijjar","x-y-z@aol.com",
					"Testing Header class methods","1.1","testing","1.0",
					"text/plain","gshd37kj9dn1081","4261","xcnzxcn489cnz3ks",
					"JavaSoft"
				    };

    public static Header header;

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
        out.println("\nTesting Header class APIs\n");

	try {
	  // BEGIN UNIT TEST:

	     for (int i = 0; i < hname.length; i++) 
	     {
	         out.println("UNIT TEST "+ (i+1) +":  Header(String, String);\n");

		 // create Header class object
	         header = new Header(hname[i], hvalue[i]); 	// API TEST

 	         if(( header != null ) && ( header instanceof Header ))
		      out.println("UNIT TEST "+ i +": Header("+ hname[i] +", "+ hvalue[i] +") passed");
	         else {
		       out.println("UNIT TEST "+ i +": Header("+ hname[i] +", "+ hvalue[i] +") FAILED");
		       errors++;
	         }
		 out.println("\nUNIT TEST " + i + ":  getName()\n");

	         if ( hname[i].equals(header.getName()) )		// API TEST
		      out.println("UNIT TEST " + i + ":  passed\n");
	         else {
		      out.println("UNIT TEST " + i + ":  FAILED\n");
		      errors++;
	         }
		 out.println("\nUNIT TEST " + i + ":  getValue()\n");

                 if ( hvalue[i].equals(header.getValue()) )	// API TEST
                      out.println("UNIT TEST " + i + ":  passed\n");
                 else {
                      out.println("UNIT TEST " + i + ":  FAILED\n");
		      errors++;
                 }
	     }
	  // END OF TEST:

	     checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
    }
}
