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
 * This class tests the <strong>Flags()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Construct an empty Flag object. <p>
 * api2test: public Flags()  <p>
 *
 * how2test: Invoke the Flags() API. The test is considered passing if <p>
 *	     this constructor returns non-null object of type Flags. <p>
 *
 *	
 *		Construct a Flag object initialized with the given flag. <p>
 * api2test: public Flags(String)  <p>
 *
 * how2test: Invoke the Flags(..) API with 'string' parameters. The test is <p>
 *	     considered passing if this constructor returns non-null object <p>
 *           of type Flags.
 */

public class Flags_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
	super.run(log, out);

        out.println("\nTesting class Flags: Flags( void | String)\n");

        try {
	  // BEGIN UNIT TEST 1:

	     out.println("\nUNIT TEST 1:  Flags()");

	     Flags flag1 = new Flags(); 	// API TEST

             if(( flag1 != null ) && ( flag1 instanceof Flags ))
	     {
		   flag1.add(Flags.Flag.DELETED);
		   flag1.add(Flags.Flag.SEEN);
		   flag1.add(Flags.Flag.RECENT);
		   flag1.add(Flags.Flag.ANSWERED);
		   flag1.add("USERTEST");

		   if ( flag1.contains(Flags.Flag.DELETED) )
			out.println("Flag DELETED added successfuly");
		   else
			errors++;

                   if ( flag1.contains(Flags.Flag.SEEN) )
                        out.println("Flag SEEN added successfuly");                
                   else
                        errors++;

                   if ( flag1.contains(Flags.Flag.RECENT) )
                        out.println("Flag RECENT added successfuly");                
                   else
                        errors++;

                   if ( flag1.contains(Flags.Flag.ANSWERED) )
                        out.println("Flag ANSWERED added successfuly");                
                   else
                        errors++;

                   if ( flag1.contains("USERTEST") )
                        out.println("Flag USERTEST added successfuly");                
                   else
                        errors++;

		   if ( errors == 0 )
                        out.println("UNIT TEST 1: passed\n");
		   else
			out.println("UNIT TEST 1: FAILED\n");
	     }
	     else {
		    out.println("UNIT TEST 1: FAILED\n");
		    errors++;
	     }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:

             out.println("\nUNIT TEST 2: Flags(String)");

             Flags flag2 = new Flags(Flags.Flag.SEEN);         // API TEST
	     flag2.add("USER_TEST");

             if (( flag2 != null ) && ( flag2 instanceof Flags ))
	     {
                   if ( flag2.contains(Flags.Flag.SEEN) )
                        out.println("Flag SEEN added successfuly");
                   else
                        errors++;

                   if ( flag2.contains("USER_TEST") )
                        out.println("Flag USER_TEST added successfuly");
                   else
                        errors++;

                   if ( errors == 0 )
                        out.println("UNIT TEST 2: passed\n");
                   else
                        out.println("UNIT TEST 2: FAILED\n");
	     }
             else {
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
