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

package javasoft.sqe.tests.javax.mail.internet.InternetAddress;

import java.io.*;
import java.util.Properties;
import java.nio.charset.StandardCharsets;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

public class unicode_Test extends MailTest {

    private static final String mailbox = "test\u03b1@example\u03b1.com";
    private static final String personal = "test\u03b1 user\u03b1";
    private static final String address = personal + " <" + mailbox + ">";
    private static final String qaddress = "\"" + personal + "\" <" + mailbox + ">";
    private static final String mailbox2 = "test\u03b2@example\u03b2.com";

    public static void main (String argv[])
    {
	unicode_Test test = new unicode_Test();
	Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String[] argv, PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting InternetAddress class Unicode APIs\n");

	try {
	     InternetAddress addr;

	  // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1: getAddress()\n");
	     addr = new InternetAddress(address);

	     if (addr.getAddress().equals(mailbox))	    // API TEST
		  out.println("UNIT TEST 1:  passed\n");
	     else {
		  out.println("UNIT TEST 1:  FAILED\n");
		  errors++;
	     }
	  // END UNIT TEST 1:

	  // BEGIN UNIT TEST 2:
	     out.println("UNIT TEST 2: getPersonal()\n");
	     addr = new InternetAddress(address);

	     if (addr.getPersonal().equals(personal))	    // API TEST
		  out.println("UNIT TEST 2:  passed\n");
	     else {
		  out.println("UNIT TEST 2:  FAILED\n");
		  errors++;
	     }
	  // END UNIT TEST 2:

	  // BEGIN UNIT TEST 3:
	     out.println("UNIT TEST 3: toUnicodeString()\n");
	     addr = new InternetAddress(mailbox, personal);
	     String uaddr = addr.toUnicodeString();

	     // either form is ok
	     if (uaddr.equals(address) || uaddr.equals(qaddress))    // API TEST
		  out.println("UNIT TEST 3:  passed\n");
	     else {
		  out.println("UNIT TEST 3:  FAILED\n");
		  errors++;
	     }
	  // END UNIT TEST 3:

	  // BEGIN UNIT TEST 4:
	     out.println("UNIT TEST 4: InternetAddress.toUnicodeString(addresses)\n");
	     InternetAddress addr1 = new InternetAddress(mailbox);
	     InternetAddress addr2 = new InternetAddress(mailbox2);
	     InternetAddress[] addresses = new InternetAddress[] {addr1, addr2};
	     String expected = mailbox + ", " + mailbox2;

	     if (InternetAddress.toUnicodeString(addresses).equals(expected))	// API TEST
		  out.println("UNIT TEST 4:  passed\n");
	     else {
		  out.println("UNIT TEST 4:  FAILED\n");
		  errors++;
	     }
	  // END UNIT TEST 4:

	  // BEGIN UNIT TEST 5:
	     out.println("UNIT TEST 5: InternetAddress.toUnicodeString(addresses,used)\n");
	     if (InternetAddress.toUnicodeString(addresses, 0).equals(expected)) // API TEST
		  out.println("UNIT TEST 5:  passed\n");
	     else {
		  out.println("UNIT TEST 5:  FAILED\n");
		  errors++;
	     }
	  // END UNIT TEST 5:

	     checkStatus();
        } catch (Exception e) {
	     handlException(e);
          }
	  return status;
    }
}
