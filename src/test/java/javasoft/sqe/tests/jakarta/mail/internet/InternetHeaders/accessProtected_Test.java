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

package javasoft.sqe.tests.jakarta.mail.internet.InternetHeaders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the 
 * <strong>1. List Headers variable's protected access</strong> .
 * <strong>2. String name variable's protected access</strong> .
 * <strong>3. String value variable's protected access</strong> .
 * It does by subclassing InternetHeader and InternetHeaders class 
 * and then attempting access to these variables
 */

public class accessProtected_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        try {
	  // BEGIN UNIT TEST:
            MyInternetHeaders mih = new MyInternetHeaders();
            mih.printTestResult();
	  // END UNIT TEST:
             checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
    
     class MyInternetHeaders extends InternetHeaders {
        //Test class to simply extend from InternetHeaders. This class accesses 
        //the new JavaMail 1.4 changes which give access to some InternetHeaders
        // for e.g: list of headers, header name and header value
        
        void printTestResult() {
            out.fine("UNIT TEST 1: accessing protected variables: \n" +
                        "    class InternetHeaders: 'headers' \n" +
                        "    class InternetHeader: 'name' and 'value'\n");
            //Simply creating an InternetHeader will test the protected access of 
            //name and value variables
            InternetHeader hdr = new InternetHeader("GreetingLine", "How are you?");
            headers.add(hdr); 
            out.fine("UNIT TEST 1 passed");
       } 
    }
}
