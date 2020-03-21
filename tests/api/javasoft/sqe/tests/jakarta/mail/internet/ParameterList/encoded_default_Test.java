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

package javasoft.sqe.tests.jakarta.mail.internet.ParameterList;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>set()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * Set a parameter. <p>
 * api2test: public void set(String, String, String)  <p>
 *
 * how2test: Call API, then check for expected result in toString output.
 */

public class encoded_default_Test extends MailTest {

    public static void main( String argv[] )
    {
        encoded_default_Test test = new encoded_default_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        try {

            // BEGIN UNIT TEST:
            out.println("\nTesting effect of encoding/decoding system " +
               "properties on ParameterList: set(String, String, String)\n");
            //System.setProperty("mail.mime.encodeparameters", "true");
            //System.setProperty("mail.mime.decodeparameters", "true");

            ParameterList parmlist = new ParameterList();
	    parmlist.set("test", "\u00a1", "iso-8859-1");
            String listvalue = parmlist.toString();
	    if (listvalue.indexOf("test*") >= 0 &&
		    listvalue.indexOf("%A1") >= 0)
                out.println("UNIT TEST 1 passed");
            else {
                out.println("UNIT TEST 1 FAILED");
                errors++;
            }

            // END UNIT TEST:

            // BEGIN UNIT TEST:
            out.println("\nTesting effect of encoding/decoding system " +
               "properties on ParameterList: get(String)\n");

            parmlist = new ParameterList("; test*=iso-8859-1''%A1");
	    if (parmlist.get("test").equals("\u00a1"))
                out.println("UNIT TEST 2 passed");
            else {
                out.println("UNIT TEST 2 FAILED");
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
