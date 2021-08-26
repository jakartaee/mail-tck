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

package javasoft.sqe.tests.jakarta.mail.Session;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.nio.file.Files;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests that resource files are loaded as expected. <p>
 * api2test: public Provider getProvider(String)  <p>
 *
 * how2test: Create a resource file and see if it's loaded.
 */

public class loadFromLib_Test extends MailTest {

    private boolean skip = false;


    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

	out.fine("\nTesting class Session: load from lib\n");

        try {
          // BEGIN UNIT TEST 1:

	     initialize();
	     if (skip)
		return Status.passed("loadFromConf skipped");

	     // Get Session object
             Session session = Session.getInstance(properties, null);
             out.fine("UNIT TEST 1: getProvider(\"test\")");

             Provider provider = session.getProvider("test"); // API TEST

             if (provider != null)
                 out.fine("UNIT TEST 1:  passed\n");
             else {
		    out.fine("UNIT TEST 1:  FAILED\n");
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
	File realhome = new File(System.getProperty("java.home"));
	File realmod = new File(new File(realhome, "lib"), "modules");
	System.setProperty("java.home", home.getPath());
	File lib = new File(home, "lib");
	lib.mkdir();
	lib.deleteOnExit();

	// Linux needs the <java.home>/lib/modules file so create a
	// symlink to the original.
	File mod = new File(lib, "modules");
	mod.deleteOnExit();
	try {
	    Files.createSymbolicLink(mod.toPath(), realmod.toPath());
	} catch (IOException|UnsupportedOperationException ex) {
	    System.out.printf("Can't create symbolic link (%s -> %s), " +
		    "skipping test", mod, realmod);
	    skip = true;
	    return;
	}

	File providers = new File(lib, "javamail.providers");
	providers.deleteOnExit();
	PrintWriter pw = new PrintWriter(providers);
	pw.println("protocol=test; type=store; class=TestStore; vendor=Test;");
	pw.close();
    }
}
