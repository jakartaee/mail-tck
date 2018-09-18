/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.javax.mail.Message;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>setFrom()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the "From" attribute in this Message. <p>
 * api2test: public void setFrom(Address)  <p>
 *
 * how2test: Call this API with various addresses, then call call getFrom() api, if
 *	     the setFrom values and getFrom values are the same, then this testcase
 *	     passes, otherwise it fails.
 */

public class setFrom_Test extends MailTest {

    private String[] addrlist = { "ksnijjar@eng", "ksnijjar@eng.sun.com", "French@physicists",
				  "cannot@waste", "us/@mhs-mci.ebay", "it@is","tower@ihug.co.nz",
				  "root@mxrelay.lanminds.com", "javaworld", "xx.zzz12@fea.net",
				  "javamail-api-eng@icdev", "ksnijjar@java-test.Eng.Sun.COM"
				};

    public static void main( String argv[] )
    {
        setFrom_Test test = new setFrom_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: setFrom(Address)\n");

        try {
	  // Create Session object
             Session session = Session.getInstance(properties, null);

	  // Create Message object
             MimeMessage msg = new MimeMessage(session);

	     if( msg == null ) {
		 status = Status.failed("Warning: Failed to create Message object!");
	     }

             for( int i = 0; i < addrlist.length; i++ )
             {
             // Create the Address object
                InternetAddress addr = new InternetAddress(addrlist[i]);

                if( addr == null ) {
                    log.println("WARNING: FAILED TO CREATE ADDRESS OBJECT: "+ (i+1));
                    continue;
                }
             // BEGIN UNIT TEST:
		// set whom the message is from
	        out.println("UNIT TEST "+ (i+1) +":  setFrom(Address)");

	        msg.setFrom(addr);		// API TEST

	        Address[] nowfrom = msg.getFrom();
	        String newFrom = nowfrom[0].toString();
 
	        if( newFrom != null ) {
	            if( addrlist[i].equals(newFrom) ) {
	                out.println("setFrom("+ addrlist[i] +")");
                        out.println("UNIT TEST "+ (i+1) +":  passed\n");
	             } else {
			     out.println("getFrom() :=> "+ newFrom);
	                     out.println("setFrom("+ addrlist[i] +")");
                             out.println("UNIT TEST "+ (i+1) +":  FAILED\n");
		             errors++;
	            }
	        } else {
			out.println("WARNING: Message "+ (i+1) +" has null 'From' header");
			out.println("UNIT TEST "+ (i+1) +":  FAILED\n");
			errors++;
	        }
	     // END UNIT TEST:
	     }
	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
