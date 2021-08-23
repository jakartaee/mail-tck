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
 * This class tests the <strong>encodeText()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *		Encode a RFC 822 "text" token into mail-safe form as per RFC 2047. <p>
 * api2test: public static String encodeText(String)  <p>
 *
 * how2test: Call API "text" token strings, verify by calling encodeText(),
 *	     and then compare values, if equal then testcase passes, otherwise it fails.
 */

public class encodeText_Test extends MailTest {

public String[] datim = { "Hello World	",
			  "  Hello",
			  "        World	",
			  "FooBar Mailer, Japanese version 1.1",
			  "[mizuki 1007] Re: Hajimemasite!!",
			  "Kazuyuki Murata <hiroko@hamakko.or.jp>",
			};

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeUtility: encodeText(String);");

        try {
	  // BEGIN UNIT TEST:

	     for( int k = 0; k < datim.length; k++ )
	     {
	         out.fine("UNIT TEST "+ k +":  encodeText("+datim[k]+")");

	         out.fine("Encoded text = "+ MimeUtility.encodeText(datim[k]));
		 out.fine("\nUNIT TEST "+ k +": passed");
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
