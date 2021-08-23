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

package javasoft.sqe.tests.jakarta.mail.internet.MimeBodyPart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>isMimeType()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Is Part of the specified MIME type? <p>
 * api2test: public boolean isMimeType(String)  <p>
 *
 * how2test: Call API, if it returns a boolean value. <p>
 *	     Then testcase passes.
 */

public class isMimeType_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeBodyPart: isMimeType(String)\n");

        try {
          // Create a MimeBodyPart object
	     MimeBodyPart bp = new MimeBodyPart();

             if( bp == null )
                 return Status.failed("Failed to create MimeBodyPart object!");

	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1: isMimeType(text/plain)");

	     if ( bp.isMimeType("text/plain") ) 	// API TEST
	          out.fine("UNIT TEST 1:  passed\n");
	     else
		  out.fine("UNIT TEST 1:  passed\n");

	  // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
             out.fine("UNIT TEST 2: isMimeType(text/html;charset=foobar)");

             if ( bp.isMimeType("text/html;charset=foobar") )         // API TEST
                  out.fine("UNIT TEST 2:  passed\n");
             else
                  out.fine("UNIT TEST 2:  passed\n");

          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
             out.fine("UNIT TEST 3: isMimeType(null)");

             if ( bp.isMimeType(null) )         // API TEST
                  out.fine("UNIT TEST 3:  passed\n");
             else
                  out.fine("UNIT TEST 3:  passed\n");

          // END UNIT TEST 3:
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
