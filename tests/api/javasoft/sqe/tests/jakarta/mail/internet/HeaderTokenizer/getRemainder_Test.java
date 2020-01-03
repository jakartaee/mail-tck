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

package javasoft.sqe.tests.jakarta.mail.internet.HeaderTokenizer;

import java.io.*;
import java.util.Vector;
import jakarta.mail.*;
import jakarta.mail.internet.HeaderTokenizer;
import jakarta.mail.internet.ParseException;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getRemainder()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *		Return rest of the header  <p>
 * api2test: public String getRemainder()  <p>
 *
 * how2test: Call API, if returns a string object
 *	     then testcase passes otherwise it fails.
 */

public class getRemainder_Test extends MailTest {

    static boolean return_comments = false;     // return comments as tokens
    static boolean mime = false;                // use MIME specials

    public String value = "ggere, /tmp/mail.out, +mailbox, ~user/mailbox, ~/mailbox, /PN=x400.address/PRMD=ibmmail/ADMD=ibmx400/C=us/@mhs-mci.ebay, C'est bien moche <paris@france>, Mad Genius <george@boole>, two@weeks (It Will Take), /tmp/mail.out, laborious (But Bug Free), cannot@waste (My, Intellectual, Cycles), users:get,what,they,deserve;, it (takes, no (time, at) all), if@you (could, see (almost, as, (badly, you) would) agree), famous <French@physicists>, it@is (brilliant (genius, and) superb), confused (about, being, french)";

    public static void main( String argv[] )
    {
        getRemainder_Test test = new getRemainder_Test();
        Status s = test.run(argv, System.err, System.out);
        s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
        out.println("\nTesting class HeaderTokenizer: getRemainder()\n");

	try {
	   // Create HeaderTokenizer object
	      HeaderTokenizer ht = new HeaderTokenizer(value,
			mime ? HeaderTokenizer.MIME : HeaderTokenizer.RFC822,
			!return_comments);

	      if( ht == null )
		  return Status.failed("Failed to create HeaderTokenizer object!");

	   // BEGIN UNIT TEST 1:
	      out.println("UNIT TEST 1: getRemainder()");
	      String remain = ht.getRemainder();	// API TEST

	      if( remain != null ) {
	          out.println("Remainder header = "+ remain);
	          out.println("\nUNIT TEST 1: passed.\n");
	      } else {
		      out.println("\nUNIT TEST 1: FAILED.\n");
		      errors++;
	      }
	   // END UNIT TEST 1:

	      checkStatus();
	} catch (Exception e) {
	      handlException(e);
	}
	return status;
    }
}
