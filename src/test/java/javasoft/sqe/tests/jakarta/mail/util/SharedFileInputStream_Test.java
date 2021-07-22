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

package javasoft.sqe.tests.jakarta.mail.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.util.*;
import com.sun.javatest.*;

/**
 * This class tests the <strong>SharedFileInputStream()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * A contructor that takes 'pattern' string argument. <p>
 * api2test: public SharedFileInputStream(File) <p>
 * api2test: public SharedFileInputStream(String) <p> 
 * api2test: public SharedFileInputStream(File, int) <p>
 * api2test: public SharedFileInputStream(String, int) <p> 
 * api2test: public int read() <p>
 * api2test: public int read(byte b[], int off, int len) <p> 
 * api2test: public long skip(long n) <p> 
 * api2test: public int available() <p> 
 * api2test: public void mark(int readlimit) <p> 
 * api2test: public void reset() <p> 
 * api2test: public void close() <p> 
 * api2test: public long getPosition() <p> 
 * api2test: public InputStream newStream(long, long) 
 *
 */

public class SharedFileInputStream_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out) {
	pattern = "java mail";
	super.run(log, out);

        try {
   
	  // BEGIN UNIT TEST 1:
            out.println("UNIT TEST 1: SharedFileInputStream(File)");
            String filename = workdir + 
                System.getProperty("file.separator") + iofile;
            File file = new File(filename);
            SharedFileInputStream sfis = new SharedFileInputStream(file);
            
            if (sfis == null) {
                out.println("UNIT TEST 1: FAILED\n");
                errors++;
            }
	    else out.println("UNIT TEST 1: passed\n");
	  // END UNIT TEST 1:

	  // BEGIN UNIT TEST 2:
            out.println("UNIT TEST 2:  SharedFileInputStream(String)");
            sfis = new SharedFileInputStream(filename);
            if (sfis == null) {
                out.println("UNIT TEST 2: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 2: passed\n");
	  // END UNIT TEST 2:

	  // BEGIN UNIT TEST 3:
            out.println("UNIT TEST 3:  SharedFileInputStream(File, int)");
            sfis = new SharedFileInputStream(file, 1024);
            if (sfis == null) {
                out.println("UNIT TEST 3: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 3: passed\n");
	  // END UNIT TEST 3:

	  // BEGIN UNIT TEST 4:
            out.println("UNIT TEST 4:  SharedFileInputStream(String, int)");
            sfis = new SharedFileInputStream(filename, 1024);
            if (sfis == null) {
                out.println("UNIT TEST 4: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 4: passed\n");
	  // END UNIT TEST 4:

	  // BEGIN UNIT TEST 5:
            out.println("UNIT TEST 5:  read()");
            sfis = new SharedFileInputStream(filename);
            
            //peek into input.txt to understand the reads
            
            //reading letter 'T' : 
            if (sfis.read() == -1) {
                out.println("UNIT TEST 5: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 5: passed\n");
	  // END UNIT TEST 5:

          // BEGIN UNIT TEST 6:
            out.println("UNIT TEST 6:  read(byte b[], int off, int len)");
            byte[] b = new byte[2];
            //reading letter 'h' and 'i' : 
            if (sfis.read(b) == -1) {
                out.println("UNIT TEST 6: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 6: passed\n");
	  // END UNIT TEST 6:

	  // BEGIN UNIT TEST 7:
            out.println("UNIT TEST 7:  skip(long n)");
            //skipping letter 's' : 
            if (sfis == null) {
                out.println("UNIT TEST 7: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 7: passed\n");
	  // END UNIT TEST 7:

          // BEGIN UNIT TEST 8:
            out.println("UNIT TEST 8:  getPosition()");
            //position will be 5 corresponding to the first space
            if (sfis.getPosition() <= 0) {
                out.println("UNIT TEST 8: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 8: passed\n");
	  // END UNIT TEST 8:

	  // BEGIN UNIT TEST 9:
            out.println("UNIT TEST 9:  available()");
            //we still have data to be read, so if available returns 0 flag error
            if (sfis.available() == 0) {
                out.println("UNIT TEST 9: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 9: passed\n");
	  // END UNIT TEST 9:

          // BEGIN UNIT TEST 10:
            out.println("UNIT TEST 10:  newStream(long, long)");
            //read 'is' into newsfis
            InputStream newsfis = 
                sfis.newStream(sfis.getPosition()+1,  sfis.getPosition()+3);
            if (newsfis.read() == -1) {
                out.println("UNIT TEST 10: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 10: passed\n");
	  // END UNIT TEST 10:

	  // BEGIN UNIT TEST 11:
            out.println("UNIT TEST 11:  mark(int readlimit)");
            // if reader goes past "a test" reset() will throw exception 
            long currpos = sfis.getPosition();
            sfis.mark(8);
            if (sfis == null) {
                out.println("UNIT TEST 11: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 11: passed\n");
	  // END UNIT TEST 11:

          // BEGIN UNIT TEST 12:
            out.println("UNIT TEST 12: reset()");
            sfis.skip(5);
            sfis.reset(); 
            if (currpos != sfis.getPosition()) {
                out.println("UNIT TEST 12: FAILED\n");
                errors++;
            } else out.println("UNIT TEST 12: passed\n");
	  // END UNIT TEST 12:

          // BEGIN UNIT TEST 13:
            out.println("UNIT TEST 13:  close()");
            try {
                sfis.close();
                out.println("UNIT TEST 13: passed\n");
            } catch (IOException ex) {
                out.println("UNIT TEST 13: FAILED\n");
            }
	  // END UNIT TEST 13:

            checkStatus();
        } catch (Exception e) {
             handlException(e);
        } 
	return status;
     }
}
