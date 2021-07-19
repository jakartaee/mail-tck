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

package javasoft.sqe.tests.jakarta.mail.internet.MimeUtility;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>fold() and unfold()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 * 
 * api2test: public static String fold(int, String)  <p>
 * api2test: public static String unfold(String)
 *
 */

public class foldUnfold_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out) {
	super.run(log, out);

        out.println("\nTesting class MimeUtility: " +
                "fold(int, String) and unfold(String)");

        try {
	  // BEGIN UNIT TEST:
	    // read from folddata file
            String filename = workdir + 
                System.getProperty("file.separator") + iofile;
            
	    BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename)));
            
	    test(in);
            if (errors > 0) 
                out.println("UNIT TEST : FAILED");
            else out.println("UNIT TEST : passed");

	  // END UNIT TEST:
             checkStatus();
        } catch (Exception e) {
	     handlException(e);
        }
	return status;
    }
    
    private void test(BufferedReader in) throws Exception {
	String line;
	while ((line = in.readLine()) != null) {
	    if (line.startsWith("#"))
		continue;
	    String orig = readString(in);
	    if (line.equals("BOTH")) {
		if (!test(orig)) errors++;
	    } else {
		String e = in.readLine();
		String expect = readString(in);
		if (line.equals("FOLD")) {
		    String t = MimeUtility.fold(0, orig);
		    if (!t.equals(expect)) 
			if (!t.equals(expect)) errors++;
		} else {
		    String t = MimeUtility.unfold(orig);
		    if (!t.equals(expect)) 
			if (!t.equals(expect)) errors++;
		}
	    }
	}
    }    
    
    /**
     * Read a string that ends with '$', preserving all characters,
     * especially including CR and LF.
     */
    private static String readString(BufferedReader in) throws Exception {
	StringBuffer sb = new StringBuffer();
	int c;
	while ((c = in.read()) != '$')
	    sb.append((char)c);
	in.readLine();	// throw away rest of line
	return sb.toString();
    }

    private static boolean test(String s) throws Exception {
	String fs = MimeUtility.fold(0, s);
	String us = MimeUtility.unfold(fs);
	if (!s.equals(us)) return false;
        return true;
    }    
}
