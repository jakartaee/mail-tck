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

package javasoft.sqe.tests.javax.mail.Session;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests that resource files are loaded as expected. <p>
 * api2test: public Provider getProvider(String)  <p>
 *
 * how2test: Create a resource file and see if it's loaded.
 */

public class loadFromLib_Test extends MailTest {

    public static void main( String argv[] )
    {
        loadFromLib_Test test = new loadFromLib_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

	out.println("\nTesting class Session: load from lib\n");

        try {
          // BEGIN UNIT TEST 1:

	     initialize();

	     // Get Session object
             Session session = Session.getInstance(properties, null);
             out.println("UNIT TEST 1: getProvider(\"test\")");

             Provider provider = session.getProvider("test"); // API TEST

             if (provider != null)
                 out.println("UNIT TEST 1:  passed\n");
             else {
		    out.println("UNIT TEST 1:  FAILED\n");
                    errors++;
             }
          // END UNIT TEST 1:
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
    }

    /**
     * We don't expect to be able to write into java.home so
     * create a fake java.home and point the System property
     * to it.
     *
     * @throws	Exception	if anything goes wrong
     */
    protected void initialize() throws Exception {

	File home = File.createTempFile("javahome", "conf");
	home.delete();	// delete the temp file
	home.mkdir();	// reuse the name for a directory
	home.deleteOnExit();
	System.setProperty("java.home", home.getPath());
	File lib = new File(home, "lib");
	lib.mkdir();
	lib.deleteOnExit();
	File providers = new File(lib, "javamail.providers");
	providers.deleteOnExit();
	PrintWriter pw = new PrintWriter(providers);
	pw.println("protocol=test; type=store; class=TestStore; vendor=Test;");
	pw.close();
    }
}
