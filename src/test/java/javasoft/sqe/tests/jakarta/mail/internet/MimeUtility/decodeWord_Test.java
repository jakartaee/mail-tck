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

package javasoft.sqe.tests.jakarta.mail.internet.MimeUtility;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>decodeWord()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *	The string is parsed using the rules in RFC 2047 for parsing an "encoded-word". <p>
 * api2test: public static String decodeWord(String)  <p>
 *
 * how2test: Call API decoded strings, if outputs decoded messages
 *	     then testcase passes, otherwise it fails.
 */

public class decodeWord_Test extends MailTest {

public String[] datim = { "=?ISO-8859-1?Q?Keld_J=F8rn_Simonsen?=",
			  "=?ISO-8859-1?Q?Andr=E9?=",
			  "=?ISO-8859-1?B?SWYgeW91IGNhbiByZWFkIHRoaXMgeW8=?=",
			  "=?ISO-8859-2?B?dSB1bmRlcnN0YW5kIHRoZSBleGFtcGxlLg==?=",
			  "=?ISO-8859-1?Q?Olle_J=E4rnefors?=",
			  "=?ISO-8859-1?Q?Patrik_F=E4ltstr=F6m?="
			};

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeUtility: decodeWord(String);");

        try {
	  // BEGIN UNIT TEST:

	     for( int k = 0; k < datim.length; k++ )
	     {
	         out.fine("UNIT TEST "+ (k+1) +":  decodeWord("+datim[k]+")");

	         out.fine("Decoded text = "+ MimeUtility.decodeWord(datim[k]));
		 out.fine("\nUNIT TEST "+ (k+1) +": passed");
	         out.fine("----------------------------------------");
	     }
	  // END UNIT TEST:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
