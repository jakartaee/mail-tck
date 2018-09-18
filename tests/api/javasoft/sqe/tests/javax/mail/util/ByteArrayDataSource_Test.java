/*
 * Copyright (c) 2006, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.javax.mail.util;

import java.io.*;
import javax.mail.*;
import javax.mail.util.*;
import com.sun.javatest.*;

/**
 * This class tests the <strong>ByteArrayDataSource()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * api2test: public ByteArrayDataSource(InputStream, String) <p>
 * api2test: public ByteArrayDataSource(byte[], String) <p>
 * api2test: public ByteArrayDataSource(String, String) <p>
 * api2test: public InputStream getInputStream() <p>
 * api2test: public OutputStream getOutputStream() <p>
 * api2test: public String getContentType()
 * api2test: public String getName()
 * api2test: public void setName(String name)
 */

public class ByteArrayDataSource_Test extends MailTest {

    public static void main(String argv[]) {
        ByteArrayDataSource_Test test = new ByteArrayDataSource_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out) {
	pattern = "java mail";
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options
        InputStream is = null;
        
        try {
            String filename = workdir + 
                System.getProperty("file.separator") + iofile;
            out.println("filename is="+ filename);
            is = new FileInputStream(filename);
	  // BEGIN UNIT TEST 1:
            out.println("UNIT TEST 1:  ByteArrayDataSource(InputStream, String)");
            
            ByteArrayDataSource badsrc = new ByteArrayDataSource(is, "text");
            if (badsrc == null) {
                out.println("UNIT TEST 1: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 1: passed\n");
	  // END UNIT TEST 1:

	  // BEGIN UNIT TEST 2:
            out.println("UNIT TEST 2:  ByteArrayDataSource(String, String)");
            String inputStr = "Test String";
            badsrc = new ByteArrayDataSource(inputStr, "text");
            if (badsrc == null) {
                out.println("UNIT TEST 2: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 2: passed\n");
	  // END UNIT TEST 2:

	  // BEGIN UNIT TEST 3:
            out.println("UNIT TEST 3:  ByteArrayDataSource(byte[], String)");
            byte[] barr = new byte[2]; barr[0]='a'; barr[1]='b';
            badsrc = new ByteArrayDataSource(barr, "text");
            if(badsrc == null) {
                out.println("UNIT TEST 3: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 3: passed\n");
	  // END UNIT TEST 3:

          // BEGIN UNIT TEST 4: badsrc is "ab" at this point
            out.println("UNIT TEST 4:  ByteArrayDataSource.getInputStream()");
            byte[] newbarr = new byte[2];
            badsrc.getInputStream().read(newbarr);
            if (newbarr[0] == 'a' && newbarr[1] == 'b') 
               out.println("UNIT TEST 4: passed\n");
            else {
               errors++; 
               out.println("UNIT TEST 4: failed\n");
            }
	  // END UNIT TEST 4:

          // BEGIN UNIT TEST 5: 
            out.println("UNIT TEST 5:  ByteArrayDataSource.getOutputStream()");
            try {
                badsrc.getOutputStream();
                errors++; 
                out.println("UNIT TEST 5: failed\n");                 
            } catch (IOException ex) {
                out.println("UNIT TEST 5: passed\n");
            } 
	  // END UNIT TEST 5:

          // BEGIN UNIT TEST 6: 
            out.println("UNIT TEST 6:  ByteArrayDataSource.getContentType()");
            if ("text".equals(badsrc.getContentType()))
                out.println("UNIT TEST 6: passed\n");
            else { 
                errors++;
                out.println("UNIT TEST 6: failed\n");                 
            }
	  // END UNIT TEST 6:            

          // BEGIN UNIT TEST 7: 
            out.println("UNIT TEST 7:  ByteArrayDataSource.getName() and " +
                "ByteArrayDataSource.setName()");
            badsrc.setName("TestDataSource"); // API TEST
            if ("TestDataSource".equals(badsrc.getName()))
                out.println("UNIT TEST 7: passed\n");
            else { 
                errors++;
                out.println("UNIT TEST 7: failed\n");                 
            }
	  // END UNIT TEST 7:            

            checkStatus();
            
        } catch (Exception e) {
             handlException(e);
        } finally {
            try {
                if (is != null) 
                    is.close();
            } catch (IOException ioex) { 
                //ignore;
            }
        }
	return status;
     }
}
