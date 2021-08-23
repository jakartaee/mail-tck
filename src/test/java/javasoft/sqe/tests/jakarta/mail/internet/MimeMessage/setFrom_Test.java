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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setFrom(String)</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the "From" attribute in this Message. <p>
 * api2test: public void setFrom(String)  <p>
 *
 * how2test: Call this API with various addresses, then call call getFrom() api,
 *	     if the setFrom values and getFrom values are the same, then this
 *	     testcase passes, otherwise it fails.
 */

public class setFrom_Test extends MailTest {

    private String[] addrlist = {
	"ksnijjar@eng", "ksnijjar@eng.sun.com", "French@physicists",
	"cannot@waste", "us/@mhs-mci.ebay", "it@is","tower@ihug.co.nz",
	"root@mxrelay.lanminds.com", "javaworld", "xx.zzz12@fea.net",
	"javamail-api-eng@icdev", "ksnijjar@java-test.Eng.Sun.COM"
    };

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run() {
	

        out.fine("\nTesting class MimeMessage: setFrom(String)\n");

        try {
	  // Create Session object
             Session session = createSession();

	  // Create Message object
             MimeMessage msg = new MimeMessage(session);

	     int i;
           // BEGIN UNIT TEST:
             for (i = 0; i < addrlist.length; i++) {
             // Create the Address object
                InternetAddress addr = new InternetAddress(addrlist[i]);

		// set whom the message is from
	        out.fine("UNIT TEST "+ (i+1) +":  setFrom(String)");

	        msg.setFrom(addrlist[i]);		// API TEST

	        Address[] nowfrom = msg.getFrom();
	        String newFrom = nowfrom[0].toString();
 
	        if (newFrom != null) {
	            if (addrlist[i].equals(newFrom)) {
	                out.fine("setFrom("+ addrlist[i] +")");
                        out.fine("UNIT TEST "+ (i+1) +":  passed\n");
	             } else {
			out.fine("getFrom() :=> "+ newFrom);
			out.fine("setFrom("+ addrlist[i] +")");
			out.fine("UNIT TEST "+ (i+1) +":  FAILED\n");
			errors++;
	            }
	        } else {
			out.fine("WARNING: Message "+ (i+1) +
				    " has null 'From' header");
			out.fine("UNIT TEST "+ (i+1) +":  FAILED\n");
			errors++;
	        }
	      }
	   // END UNIT TEST:

           // BEGIN UNIT TEST:
		// now try with more than one From address
	        out.fine("UNIT TEST "+ (i+1) +":  setFrom(String)");

		String addr1 = "joe@example.com";
		String addr2 = "bob@example.com";
	        msg.setFrom(addr1 + "," + addr2);		// API TEST

	        Address[] afrom = msg.getFrom();
		if (afrom != null && afrom.length == 2 &&
		    ((afrom[0].toString().equals(addr1) &&
			afrom[1].toString().equals(addr2)) ||
		    (afrom[0].toString().equals(addr2) &&
			afrom[1].toString().equals(addr1)))) {
		    out.fine("setFrom("+ addr1 + "," + addr2 +")");
		    out.fine("UNIT TEST "+ (i+1) +":  passed\n");
		 } else {
		    out.fine("getFrom() :=> "+ afrom);
		    out.fine("setFrom("+ addr1 + "," + addr2 +")");
		    out.fine("UNIT TEST "+ (i+1) +":  FAILED\n");
		    errors++;
		}
	   // END UNIT TEST:
	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
