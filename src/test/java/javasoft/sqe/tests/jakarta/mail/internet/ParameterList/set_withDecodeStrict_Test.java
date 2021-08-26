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

package javasoft.sqe.tests.jakarta.mail.internet.ParameterList;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 *      api2test: public void set(String, String)  <p>
 *      api2test: public void set(String, String, String)  <p>
 *
 * how2test: Call API, then verify calling getNames() method. If this operation
 *	     is successfull then testcase passes, otherwise it fails.
 */

public class set_withDecodeStrict_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        try {
            // BEGIN UNIT TEST:
            System.setProperty("mail.mime.decodeparameters", "true");
            System.setProperty("mail.mime.decodeparameters.strict", "true");        
            // missing charset        
            //String s = "; filename*='en-us'This%20is%20%2A%2A%2Afun%2A%2A%2A";
            String[] wrongParameters = {
                /*missing language*/
                "; filename*=us-ascii'This%20is%20%2A%2A%2Afun%2A%2A%2A",
                /*missing language*/        
                "; filename*=us-ascii'This%20is%20%2A%2A%2Afun%2A%2A%2A",
                /*bad format number*/
                "; filename*=us-ascii'en-us'This%2xis%20%2A%2A%2Afun%2A%2A%2A",
                /*bad charset*/
                "; filename*=unknown'en-us'This%20is%20%2A%2A%2Afun%2A%2A%2A"};            
            
            for (int i =0; i< wrongParameters.length; i++) {
                try {        
                    ParameterList pl = new ParameterList(wrongParameters[i]);
                    pl.get("filename");
                    out.fine("UNIT TEST FAILED for input parameter: " 
                        + wrongParameters[i]);
                    errors++;
                } catch (ParseException ex) {
                    out.fine("UNIT TEST passed for input parameter: " 
                        + wrongParameters[i]);
                }
            }
            // END UNIT TEST:
            checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
}
