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
 * This class tests the <strong>size()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    	Returns the number of parameters in ParameterList. <p>
 * api2test: public void size(String)  <p>
 *
 * how2test: Call API, verify that it returns int object. If this is
 *	     so then testcase passes, otherwise it fails.
 */

public class size_Test extends MailTest {

    public static String[] pname  = { "charset","html","audio","i18set","image" };
    public static String[] pvalue = { "us-ascii","html-4.x","us-dolby","ISO-9000-XZ","US-VHS" };

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class ParameterList: size()\n");

        try {
	  // create a ParameterList object
	     ParameterList parmlist = new ParameterList();

	     if( parmlist == null ) {
		 return Status.failed("Warning: Failed to create ParameterList object!");
	     }
	  // BEGIN UNIT TEST:

	     for( int i = 0; i < pname.length; i++ )
	     {
		// set a parameter
		  parmlist.set(pname[i], pvalue[i]);
	     }
	     out.fine("UNIT TEST 1:  size()");

	    // get the number of parameters in list
	     int num_param = parmlist.size();		// API TEST

	     if( num_param > 0 )
		 out.fine("UNIT TEST 1: passed");
	     else {
		   out.fine("UNIT TEST 1: FAILED");
		   errors++;
	     }
	  // END UNIT TEST:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
