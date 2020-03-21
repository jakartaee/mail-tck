/*
 * Copyright (c) 2006, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.mail.util;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.util.*;
import com.sun.javatest.*;

/**
 * This class tests the <strong>SharedByteArrayInputStream()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * api2test: public SharedByteArrayInputStream(byte[])
 * api2test: public SharedByteArrayInputStream(byte[], int, int)
 * api2test: public SharedByteArrayInputStream.getPosition()
 * api2test: public SharedByteArrayInputStream.newStream(long, long)
 *
 * how2test: Call these APIs with pattern/string/message arguments and if
 *	     'match' returns boolean value, then this testcase passes.
 */

public class SharedByteArrayInputStream_Test extends MailTest {

    public static void main(String argv[]) {
        SharedByteArrayInputStream_Test test = 
            new SharedByteArrayInputStream_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out) {
	pattern = "java mail";
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options
        try {
   
	  // BEGIN UNIT TEST 1:
            out.println("UNIT TEST 1:  SharedByteArrayInputStream(byte[])");
            byte[] barr = new byte[2]; barr[0]='a'; barr[1]='b';
            SharedByteArrayInputStream sbais = 
                new SharedByteArrayInputStream(barr);
            
            if (sbais == null) {
                out.println("UNIT TEST 1: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 1: passed\n");
	  // END UNIT TEST 1:

	  // BEGIN UNIT TEST 2:
            out.println("UNIT TEST 2:  " +
                "SharedByteArrayInputStream(byte[], int, int)");
            
            byte[] bytearr = {'T','e','s','t','S','t','r','i','n','g'};
            sbais = new SharedByteArrayInputStream(bytearr, 4, 6);
            
            if (sbais == null) {
                out.println("UNIT TEST 2: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 2: passed\n");
	  // END UNIT TEST 2:

          // BEGIN UNIT TEST 3: sbais is "String"
            out.println("UNIT TEST 3:  " +
                "SharedByteArrayInputStream.getPosition()");
            
            if (0 == sbais.getPosition()) 
               out.println("UNIT TEST 3: passed\n");
            else {
               errors++; 
               out.println("UNIT TEST 3: failed\n");
            }
	  // END UNIT TEST 3:

          // BEGIN UNIT TEST 4: sbais is "String"
            out.println("UNIT TEST 4:  " +
                "SharedByteArrayInputStream.newStream(long, long) ");
            
            InputStream is = sbais.newStream(1, 5);
            byte[] newbarr = new byte[4];
            is.read(newbarr);
            String newbarrstr = new String(newbarr);

            if ("trin".equals(newbarrstr)) 
                out.println("UNIT TEST 4: passed\n");
	    else { 
                errors++; 
                out.println("UNIT TEST 4: FAILED\n");
            }
            if (is != null) 
                is.close();
	  // END UNIT TEST 4:

            checkStatus();
        } catch (Exception e) {
             handlException(e);
        } 
	return status;
     }
}
