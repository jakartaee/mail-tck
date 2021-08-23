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
 * This class tests the <strong>setText()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * Set the given String(s) as part's content. <p>
 * api2test: public void setText(String)  <p>
 * api2test: public void setText(String,String)  <p>
 * api3test: public void setText(String, String,String)  <p>
 *
 * how2test: Call these APIs with string arguments, if is done without
 *	     any problems then testcase passes, otherwise it fails.
 */

public class setText_Test extends MailTest {

    public static String[] setc = 
        { "abdcefghijklmnopqrstuwxyz","+=_-!~@#$%^&*9876543210",
          "Keld J\170rn Simonsen","Patrik F\144ltstr\166m",""
        };
    public static String[] chars = 
        { "US-ASCII","US-ASCII","NON-US-ASCII","NON-US-ASCII","US-ASCII" };

    public static String[] textMimeSubtypes = 
        { "html", "plain", "richtext", "css", "x-vcard"};

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeBodyPart: setText()\n");

        try {
          // Create a MimeBodyPart object
	     MimeBodyPart bp = new MimeBodyPart();

             if (bp == null)
                 return Status.failed("Failed to create MimeBodyPart object!");

	  // BEGIN UNIT TEST:
             for (int i = 0; i < setc.length; i++) {
                  out.fine("UNIT TEST " + i + ": setText(" + setc[i] + ")");
		  bp.setText(setc[i]);			// API TEST

                  out.fine("UNIT TEST " + i + ":  passed.\n");
             }

             for (int i = 0; i < setc.length; i++) {
                  out.fine("UNIT TEST " + i + ": setText(" 
                      + setc[i] + "," + chars[i] + ")");
		  bp.setText(setc[i], chars[i]);	// API TEST

                  out.fine("UNIT TEST "+i+":  passed.\n");
             }

             for (int i = 0; i < setc.length; i++) {
                  out.fine("UNIT TEST "+i+": setText(" + setc[i] + "," + 
                      chars[i]+ "," + textMimeSubtypes[i] + ")");
		  
                  bp.setText(setc[i], chars[i], textMimeSubtypes[i]);// API TEST

                  out.fine("UNIT TEST "+i+":  passed.\n");
             }
           // END UNIT TEST:
             
	     checkStatus();

        } catch (Exception e) {
	     handlException(e);
        }
	return status;
     }
}
