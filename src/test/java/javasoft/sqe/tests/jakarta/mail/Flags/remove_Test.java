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
 * This class tests the <strong>remove()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Remove the specified flag from this Flags object.<p>
 * api2test: public void remove(Flags flag | String flag)  <p>
 *
 * how2test: First add flags to a Flags object. Then check that this <p>
 *	     operation has successed. Now remove these flags and check <p>
 *	     their existence again. Test is considered passing if flags <p>
 *	     no-longer exist after the remove() api has been called.
 */

public class remove_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class Flags: remove( Flags | String)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.fine("\nUNIT TEST 1:  remove(Flags)");

	     Flags flag1 = new Flags();

             if(( flag1 != null ) && ( flag1 instanceof Flags ))
	     {
		   flag1.add(Flags.Flag.DELETED);
		   flag1.add(Flags.Flag.SEEN);
		   flag1.add(Flags.Flag.RECENT);
		   flag1.add(Flags.Flag.ANSWERED);

		   if( flag1.contains(Flags.Flag.DELETED) )
		   {
			flag1.remove(Flags.Flag.DELETED);	// API TEST

			if( !(flag1.contains(Flags.Flag.DELETED)) )
			    out.fine("DELETED flag removed from Flags object.");
		        else
			    errors++;
		   }

                   if( flag1.contains(Flags.Flag.SEEN) )
		   {
			flag1.remove(Flags.Flag.SEEN);	// API TEST

                        if( !(flag1.contains(Flags.Flag.SEEN)) )
                            out.fine("SEEN flag removed from Flags object.");
                        else
                            errors++;
                   }

                   if( flag1.contains(Flags.Flag.RECENT) )
		   {
			flag1.remove(Flags.Flag.RECENT);	// API TEST

			if( !(flag1.contains(Flags.Flag.RECENT)) )
                            out.fine("RECENT flag removed from Flags object.");
			else
			    errors++;
		   }

                   if( flag1.contains(Flags.Flag.ANSWERED) )
                   {
                        flag1.remove(Flags.Flag.ANSWERED);     // API TEST

                        if( !(flag1.contains(Flags.Flag.ANSWERED)) )
                            out.fine("ANSWERED flag removed from Flags object.");
                        else
                            errors++;
                   }

		   if( errors == 0 )
                       out.fine("UNIT TEST 1: passed\n");
		   else
		       out.fine("UNIT TEST 1: FAILED\n");
	     }
	     else {
		    out.fine("UNIT TEST 1: FAILED\n");
		    errors++;
	     }
	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:

             out.fine("\nUNIT TEST 2: remove(String)");

             Flags flag2 = new Flags(Flags.Flag.ANSWERED);
	     flag1.add("TEST_USER");
	     flag2.add("USER_TEST");

             if (( flag2 != null ) && ( flag2 instanceof Flags ))
	     {
                   if( flag1.contains("TEST_USER") ) 
		   {
			flag1.remove("TEST_USER");	// API TEST

			if( !(flag1.contains("TEST_USER")) )
                            out.fine("TEST_USER flag removed from Flags object.");
                        else
                            errors++;
		   }

                   if( flag2.contains("USER_TEST") )            
                   {
                        flag2.remove("USER_TEST");      // API TEST

                        if( !(flag2.contains("USER_TEST")) )
                            out.fine("USER_TEST flag removed from Flags object.");
                        else
                            errors++;
                   }

                   if( !(flag2.contains("NONEXIST")) )            
                   {
                        flag2.remove("NONEXIST");      // API TEST

                        if( !(flag2.contains("NONEXIST")) )
                            out.fine("NONEXIST flag removed from Flags object.");
                        else
                            errors++;
                   }

                   if( errors == 0 )
                       out.fine("UNIT TEST 2: passed\n");
                   else
                       out.fine("UNIT TEST 2: FAILED\n");
	     }
             else {
                    out.fine("UNIT TEST 2: FAILED\n");
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
