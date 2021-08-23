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
 * This class tests the <strong>setFileName()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the filename associated with BodyPart. <p>
 * api2test: public void setFileName(String)  <p>
 *
 * how2test: Call API with string arguments, then call getFileName(), if the
 *	     set/get values are equal then testcase passes, otherwise it fails.
 */

public class setFileName_Test extends MailTest {

    public static String[] setfn = { "fooabc!@#$%^&!xzy","/user/foo/testfile",":A:B:FOOTEST" };

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeBodyPart: setFileName()\n");

        try {
          // Create a MimeBodyPart object
	     MimeBodyPart bp = new MimeBodyPart();

             if( bp == null )
                 return Status.failed("Failed to create MimeBodyPart object!");

	  // BEGIN UNIT TEST:

             for( int i = 0; i < setfn.length; i++ )
             {
                  out.fine("UNIT TEST "+i+": setFileName("+setfn[i]+")");
		  bp.setFileName(setfn[i]); 	// API TEST

                  if( setfn[i].equals(bp.getFileName()) )
                      out.fine("UNIT TEST "+i+":  passed.\n");
                  else {
                        out.fine("UNIT TEST "+i+":  FAILED.\n");
                        errors++;
                  }
             }
          // END UNIT TEST:
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
