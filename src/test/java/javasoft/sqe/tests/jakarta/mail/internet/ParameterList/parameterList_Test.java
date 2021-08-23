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

package javasoft.sqe.tests.jakarta.mail.internet.ParameterList;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>ParameterList()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    	Default constructor <p>
 * api2test: public ParameterList()  <p>
 *		Constructor that takes a parameter-list string. <p>
 * api2test: public ParameterList(String); <p>
 *
 * how2test: Call these constructors, then verify the type of object created to be that of
 *	     ParameterList. If is so then this testcase passes, otherwise it fails.
 */

public class parameterList_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	pattern = ";i18set=ISO-8859-1;charset=us-acii;abc=xyz";

	

        out.fine("\nTesting class ParameterList: ParameterList(void | String)\n");

        try {	     
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1:  ParameterList()");

	     ParameterList parmlist1 = new ParameterList();    // API TEST

	     if( parmlist1 != null )
		 out.fine("UNIT TEST 1: passed");
	     else {
		    out.fine("UNIT TEST 1: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.fine("UNIT TEST 2:  ParameterList("+pattern+")");

	   // Constructor that takes a parameter-list string
	     ParameterList parmlist2 = new ParameterList(pattern);	// API TEST

	     if( parmlist2 != null ) {
		 out.fine( parmlist2.get("filename") );
		 out.fine("UNIT TEST 2: passed");
	     } else {
		     out.fine("UNIT TEST 2: FAILED");
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
