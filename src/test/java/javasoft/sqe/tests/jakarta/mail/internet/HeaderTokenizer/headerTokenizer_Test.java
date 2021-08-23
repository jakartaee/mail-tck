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

package javasoft.sqe.tests.jakarta.mail.internet.HeaderTokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.Vector;
import jakarta.mail.*;
import jakarta.mail.internet.HeaderTokenizer;
import jakarta.mail.internet.ParseException;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>HeaderTokenizer()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *              Creates headtokenizer object. <p>
 * api2test: public HeaderTokenizer(String)  <p>
 * api2test: public HeaderTokenizer(String, String)  <p>
 * api2test: public HeaderTokenizer(String,String,boolean)  <p>
 *
 * how2test: Call these contructors with given values, if they create an object of 
 *	     type HeaderTokenizer then testcase passes otherwise it fails.
 */

public class headerTokenizer_Test extends MailTest {

    static boolean return_comments = false;     // return comments as tokens
    static boolean mime = false;                // use MIME specials

    public String value = "ggere, /tmp/mail.out, +mailbox, ~user/mailbox, ~/mailbox, /PN=x400.address/PRMD=ibmmail/ADMD=ibmx400/C=us/@mhs-mci.ebay, C'est bien moche <paris@france>, Mad Genius <george@boole>, two@weeks (It Will Take), /tmp/mail.out, laborious (But Bug Free), cannot@waste (My, Intellectual, Cycles), users:get,what,they,deserve;, it (takes, no (time, at) all), if@you (could, see (almost, as, (badly, you) would) agree), famous <French@physicists>, it@is (brilliant (genius, and) superb), confused (about, being, french)";

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	
        out.fine("\nTesting class HeaderTokenizer: HeaderTokenizer()\n");

	try {
           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1: HeaderTokenizer(String)");
              HeaderTokenizer ht1 = new HeaderTokenizer(value);		// API TEST

              if( ht1 != null )
                  out.fine("UNIT TEST 1: passed.\n");
              else {
                    out.fine("UNIT TEST 1: Failed.\n");
                    errors++;
              }
           // END UNIT TEST 1:
           // BEGIN UNIT TEST 2:
              out.fine("UNIT TEST 2: HeaderTokenizer(String, String)");

              HeaderTokenizer ht2 = new HeaderTokenizer(value,
                                    mime ? HeaderTokenizer.MIME : HeaderTokenizer.RFC822);	// API TEST

              if( ht2 != null )
                  out.fine("UNIT TEST 2: passed.\n");
              else {
                    out.fine("UNIT TEST 2: Failed.\n");
                    errors++;
              }
           // END UNIT TEST 2:
	   // BEGIN UNIT TEST 3:
	      out.fine("UNIT TEST 3: HeaderTokenizer(String, String, boolean)");

	      HeaderTokenizer ht3 = new HeaderTokenizer(value,
			mime ? HeaderTokenizer.MIME : HeaderTokenizer.RFC822,
			!return_comments);				// API TEST

	      if( ht3 != null )
		  out.fine("UNIT TEST 3: passed.\n");
	      else {
		    out.fine("UNIT TEST 3: Failed.\n");
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
