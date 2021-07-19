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

package javasoft.sqe.tests.jakarta.mail.Flags;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>clearSystemFlags and clearUserFlags</strong> APIs.
 *
 *		Clear the specified flags from this Flags object.<p>
 * api2test: public void clearSystemFlags() <p>
 * api2test: public void clearUserFlags() <p>
 *
 * how2test: First add system and user flags to a Flags object.
 *	     Now clear the flags and check that only the expected flags
 *	     have been removed.
 */

public class clear_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class Flags: clear{System,User}Flags)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.println("\nUNIT TEST 1:  clearSystemFlags()");

	     Flags flag1 = new Flags();
	     flag1.add(Flags.Flag.SEEN);
	     flag1.add("FLAG");
	     flag1.clearSystemFlags();
	     if (!flag1.contains(Flags.Flag.SEEN) && flag1.contains("FLAG")) {
		out.println("UNIT TEST 1: passed\n");
	     } else {
		out.println("UNIT TEST 1: FAILED\n");
		errors++;
	     }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:

             out.println("\nUNIT TEST 2: clearUserFlags()");

	     Flags flag2 = new Flags();
	     flag2.add(Flags.Flag.SEEN);
	     flag2.add("FLAG");
	     flag2.clearUserFlags();
	     if (flag2.contains(Flags.Flag.SEEN) && !flag2.contains("FLAG")) {
		out.println("UNIT TEST 2: passed\n");
	     } else {
		out.println("UNIT TEST 2: FAILED\n");
		errors++;
	     }
          // END UNIT TEST 2:

	     checkStatus();

        } catch ( Exception e ) {
             handlException(e);
        }
	return status;
     }
}
