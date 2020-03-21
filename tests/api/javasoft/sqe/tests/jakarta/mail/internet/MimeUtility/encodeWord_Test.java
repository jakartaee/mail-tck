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

package javasoft.sqe.tests.jakarta.mail.internet.MimeUtility;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>encodeWord()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 *		Encode a RFC 822 "word" token into mail-safe form as per RFC 2047. <p>
 * api2test: public static String encodeWord(String)  <p>
 *
 * how2test: Call API with strings, if outputs message
 *	     then testcase passes, otherwise it fails.
 */

public class encodeWord_Test extends MailTest {

public String[] datim = { "Keld J" +
"\342\210\232\303\256\342\210\232\342\210\217\305\222\302\251" + "rn Simonsen",
			  "Andr" +
"\342\210\232\303\256\342\210\232\342\210\217\305\222\302\251",
			  "If you can read yo",
			  "u understand the example.",
			  "Olle J" +
"\342\210\232\303\256\342\210\232\342\210\217\305\222\302\251" +
"rnefors",
			  "Patrik F" +
"\342\210\232\303\256\342\210\232\342\210\217\305\222\302\251" + "ltstr" +
"\342\210\232\303\256\342\210\232\342\210\217\305\222\302\251" + "m"
                        };

    public static void main( String argv[] )
    {
        encodeWord_Test test = new encodeWord_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	// parse command-line options
	parseArgs(argv);

        out.println("\nTesting class MimeUtility: encodeWord(String);");

        try {
	  // BEGIN UNIT TEST:

	     for( int k = 0; k < datim.length; k++ )
	     {
	         out.println("UNIT TEST "+ (k+1) +":  encodeWord("+datim[k]+")");

	         out.println("Encoded word = "+ MimeUtility.encodeWord(datim[k]));
		 out.println("\nUNIT TEST "+ (k+1) +": passed");
	         out.println("----------------------------------------");
	     }
	  // END UNIT TEST:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
