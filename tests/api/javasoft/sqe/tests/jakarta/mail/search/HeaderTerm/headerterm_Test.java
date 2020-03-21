/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.mail.search.HeaderTerm;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>HeaderTerm()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		A contructor takes String arguments. <p>
 * api2test: public HeaderTerm(String, String)  <p>
 *		Return the name of the header to compare with. <p>
 * api2test: public String getHeaderName()  <p>
 *		The match method.   <p>
 * api2test: boolean match(Message)  <p>
 *
 * how2test: Call these APIs with pattern/string/message arguments and if
 *	     'match' returns boolean value, then this testcase passes.
 */

public class headerterm_Test extends MailTest {

    public static String name = "Subject";
    public static String pattern = "Java";

    public static void main( String argv[] )
    {
        headerterm_Test test = new headerterm_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class HeaderTerm: HeaderTerm(String, String)\n");

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
	     boolean foundit;

             for( int i = 1; i <= msgcount; i++ )
             {
             // Get the message
                MimeMessage msg =  (MimeMessage)folder.getMessage(i);

	        if( msg == null ) {
		    log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		    continue;
	        }
	     // BEGIN UNIT TEST:
		out.println("UNIT TEST "+ (i+1) +":  HeaderTerm("+ name +", "+ pattern +")");
		out.println("                     :  getHeaderName()");
	        out.println("                     :  match(Message)");

		HeaderTerm ht = new HeaderTerm(name, pattern); // API TEST

		if( ht == null ) {
		    log.println("Warning: HeaderTerm contructor returned a Null object!");
		    continue;
                }
		// Return the name of the header
		String hname = ht.getHeaderName();	// API TEST

		if( hname != null ) {
		    out.println("Header name: "+ hname);
		}
		// match the header
		foundit = ht.match(msg);	// API TEST

	        if( foundit ) {
	            out.println("Name: "+ name +" Pattern: "+ pattern +" found in Header!");
                    out.println("UNIT TEST "+ (i+1) +":  passed\n");
	        } else {
		        out.println("Name: "+ name +" Pattern: "+ pattern +" Not found in Header!");
			out.println("UNIT TEST "+ (i+1) +":  passed\n");
	        }
	     // END UNIT TEST:
	     }
	     folder.close(false);
	     store.close();
	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
