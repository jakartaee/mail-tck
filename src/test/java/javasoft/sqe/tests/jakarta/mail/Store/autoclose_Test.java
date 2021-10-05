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

package javasoft.sqe.tests.jakarta.mail.Store;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>AutoCloseable</strong> API. <p>
 *
 *		Closes this store and terminates connection. <p>
 * api2test: AutoCloseable <p>
 *
 * how2test: Use try-with-resources to open the Store.
 *	     If the Store is closed (not connected) at the end of the try block
 *	     then the test passes otherwise it fails.
 */

public class autoclose_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Store: auto close()\n");

     // BEGIN UNIT TEST 1:
	out.fine("UNIT TEST 1: auto close()");

     // Connect to host server
	Store store = null;
        try (Store istore = connect2host(protocol, host, user, password)) {
	    if (!istore.isConnected()) {
		errors++;
		out.fine("UNIT TEST 1:  FAILED\n");
	    }
	    store = istore;

        } catch ( Exception e ) {
	     handlException(e);
        }

	if (errors == 0 && store != null) {
	    if (!store.isConnected()) {
		out.fine("UNIT TEST 1:  passed\n");
	    } else {
		errors++;
		out.fine("UNIT TEST 1:  FAILED\n");
	    }
	}

     // END UNIT TEST 1:
	checkStatus();
	return status;
     }
}
