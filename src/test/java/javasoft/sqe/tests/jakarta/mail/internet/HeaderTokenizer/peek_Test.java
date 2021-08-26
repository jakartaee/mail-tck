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
 * This class tests the <strong>peek()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.     <p>
 *
 *	Show the next token without actually removing it from the parse stream. <p>
 * api2test: public HeaderTokenizer.Token peek()  <p>
 *
 * how2test: Call API, if it returns HeaderTokenizer.Token object
 *	     values then testcase passes otherwise it fails.
 */

public class peek_Test extends MailTest {

    static boolean return_comments = false;	// return comments as tokens
    static boolean mime = false;		// use MIME specials

    public String value = "ggere, /tmp/mail.out, +mailbox, ~user/mailbox, ~/mailbox, /PN=x400.address/PRMD=ibmmail/ADMD=ibmx400/C=us/@mhs-mci.ebay, C'est bien moche <paris@france>, Mad Genius <george@boole>, two@weeks (It Will Take), /tmp/mail.out, laborious (But Bug Free), cannot@waste (My, Intellectual, Cycles), users:get,what,they,deserve;, it (takes, no (time, at) all), if@you (could, see (almost, as, (badly, you) would) agree), famous <French@physicists>, it@is (brilliant (genius, and) superb), confused (about, being, french)";

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	
        out.fine("\nTesting class HeaderTokenizer: peek()\n");

	try {
	   // Create HeaderTokenizer object
	      HeaderTokenizer ht = new HeaderTokenizer(value,
			mime ? HeaderTokenizer.MIME : HeaderTokenizer.RFC822,
			!return_comments);

	      if( ht == null )
		  return Status.failed("Failed to create HeaderTokenizer object!");

	      HeaderTokenizer.Token tok;
	      Vector<HeaderTokenizer.Token> toklist =
					new Vector<HeaderTokenizer.Token>();

	   // BEGIN UNIT TEST 1:
	      out.fine("UNIT TEST 1: peek()");

	      while((tok = ht.next()).getType() != HeaderTokenizer.Token.EOF) {
		     toklist.addElement(tok);
		     int pk = (ht.peek()).getType();     // API TEST
	      }

	      for(int i = 0; i < toklist.size(); i++) {
		  tok = toklist.elementAt(i);
		        out.fine("\t" + type(tok.getType()) +
					"\t" + tok.getValue());
	      }
	      out.fine("UNIT TEST 1: passed.\n");
	   // END UNIT TEST 1:

	      status = Status.passed("OKAY");
	} catch (Exception e) {
	      handlException(e);
	}
	return status;
    }

    private static String type(int t) {
	if (t == HeaderTokenizer.Token.ATOM)
	    return "ATOM";
	else if (t == HeaderTokenizer.Token.QUOTEDSTRING)
	    return "QUOTEDSTRING";
	else if (t == HeaderTokenizer.Token.COMMENT)
	    return "COMMENT";
	else if (t == HeaderTokenizer.Token.EOF)
	    return "EOF";
	else if (t < 0)
	    return "UNKNOWN";
	else
	    return "SPECIAL";
    }

    private static int type(String s) {
	if (s.equals("ATOM"))
	    return HeaderTokenizer.Token.ATOM;
	else if (s.equals("QUOTEDSTRING"))
	    return HeaderTokenizer.Token.QUOTEDSTRING;
	else if (s.equals("COMMENT"))
	    return HeaderTokenizer.Token.COMMENT;
	else if (s.equals("EOF"))
	    return HeaderTokenizer.Token.EOF;
	else // if (s.equals("SPECIAL"))
	    return 0;
    }
}
