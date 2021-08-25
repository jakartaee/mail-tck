/*
 * Copyright (c) 2006, 2021 Oracle and/or its affiliates. All rights reserved.
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
import jakarta.mail.*;
import jakarta.mail.Provider.Type;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addProvider()</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the object returned.  <p>
 *
 * api2test: public void addProvider(Provider provider)
 *
 */

public class addProvider_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

	out.fine("\nTesting class Provider\n");

        try {

            // Get Session object
            Session session = Session.getInstance(properties, null);

            // BEGIN UNIT TEST 1:
            out.fine("UNIT TEST 1: Provider() constructor");
            Provider prov = new Provider(Type.STORE, "airmail", 
                "com.sun.mail.airmail.AirMailStore", 
                "Sun Microsystems, Inc", "1.0"); //API TEST

            if (prov == null) {
                out.fine("UNIT TEST 1:  FAILED\n");
                errors++;
            } else out.fine("UNIT TEST 1:  passed\n");
            // END UNIT TEST 1:

            // BEGIN UNIT TEST 2:
            out.fine("UNIT TEST 2: addProvider()");

            session.addProvider(prov); // API TEST
            Provider provider = session.getProvider("airmail");
            
            if (provider.toString().equals(prov.toString()))        
                out.fine("UNIT TEST 2:  passed\n");
            else {
                out.fine("UNIT TEST 2:  FAILED\n");
                errors++;
            }
            // END UNIT TEST 2:

	    checkStatus();

        } catch (Exception e) {
	    handlException(e);
        }
	return status;
     }
}
