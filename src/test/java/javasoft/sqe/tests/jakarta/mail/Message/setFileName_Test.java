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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setFileName()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * Set the filename associated with this message, if possible.<p>
 * api2test: public void setFileName(String filename)  
 * The API will be tested with varying combinations for the values of 
 * mail.mime.encodefilename and mail.mime.decodefilename system properties <p>
 *
 * how2test: Call this API, with 'filename' string argument, then call 
 *           getFileName() api, verify that set value is the same as get value. 
 *           If this is true then the testcase passes, otherwise it fails.
 */

public class setFileName_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);
             
             if( msg == null) {
                 log.warning("WARNING: FAILED TO CREATE MESSAGE OBJECTS");
                 return Status.failed("Failed to create Message objects");
             }

	     // need to ensure that the filename can be encoded (by default)
	     // for unit test 3 below.

	     System.setProperty("mail.mime.charset", "utf-8");
             
             out.fine("\nTesting class Message: setFileName(String)\n");
             
	  // BEGIN UNIT TEST:
	     // Set body part filename
	     out.fine("UNIT TEST 1:  setFileName(String)");

 	     String fileName="mailworld.txt";
	     msg.setFileName(fileName);	// API TEST
	     out.fine("setFileName("+ fileName +")");
	     out.fine("Filename associated with this body: "+ msg.getFileName());

             if( fileName.equals(msg.getFileName()) )
                 out.fine("UNIT TEST 1:  passed\n");
             else {
                   out.fine("UNIT TEST 1:  FAILED\n");
                   errors++;
             }
	     out.fine("UNIT TEST 2:  setFileName(String)");

	     fileName="?#;%^+=-@	$&";
	     msg.setFileName(fileName); // API TEST
	     out.fine("setFileName("+ fileName +")");
             out.fine("Filename associated with this body: "+ msg.getFileName());

	     if( fileName.equals(msg.getFileName()) )
                 out.fine("UNIT TEST 2:  passed\n");
	     else {
		   out.fine("UNIT TEST 2:  FAILED\n");
		   errors++;
	     }
 
 	     fileName =
"\342\200\232\303\204\303\266\342\210\232\303\221\342\210\232\342\210\202\342" +
"\200\232\303\240\303\266\342\210\232\303\253\342\200\232\303\240\303\266\342" +
"\200\232\303\240\303\207\342\200\232\303\204\303\266\342\210\232\342\200\240" +
"\342\210\232\342\210\202\342\200\232\303\240\303\266\342\210\232\302\264\342" +
"\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232\342\210\202\342" +
"\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232\303\241\342\200" +
"\232\303\204\303\266\342\210\232\303\221\342\210\232\342\210\202\342\200\232" +
"\303\240\303\266\342\200\232\303\204\342\200\240\342\200\232\303\240\303\266" +
"\342\200\232\303\240\303\207\342\200\232\303\204\303\266\342\210\232\303\221" +
"\342\210\232\342\210\202\342\200\232\303\240\303\266\342\210\232\303\253\342" +
"\200\232\303\204\303\266\342\210\232\303\221\342\200\232\303\204\342\200\240" +
"\342\200\232\303\204\303\266\342\210\232\303\221\342\210\232\342\210\202\342" +
"\200\232\303\240\303\266\342\200\232\303\204\342\200\240\342\200\232\303\240" +
"\303\266\342\200\232\303\240\303\207\342\200\232\303\204\303\266\342\210\232" +
"\303\221\342\210\232\342\210\202\342\200\232\303\240\303\266\342\200\232\303" +
"\204\342\200\240\342\200\232\303\240\303\266\342\210\232\302\260\302\254\302" +
"\250\302\254\302\256\302\254\302\250\302\254\303\206\302\254\302\250\302\254" +
"\302\256\302\254\302\250\342\210\232\303\234\342\200\232\303\204\303\266\342" +
"\210\232\303\221\342\210\232\342\210\202\342\200\232\303\240\303\266\342\200" +
"\232\303\204\342\200\240\342\200\232\303\240\303\266\342\200\232\303\240\303" +
"\207\342\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232\342\210" +
"\202\302\254\302\250\342\200\232\303\240\302\264" +
		"-a_german_character";

             // testing the convenience system properties that actually 
             // violate MIME specification. 

             // 1.First test with default (false) values
             //  for mail.mime.encodefilename and mail.mime.decodefilename
             //  system properties.
             out.fine("UNIT TEST 3:  setFileName(String) with default " +
                 "mail.mime.encodefilename and mail.mime.decodefilename " +
                 "i.e. set to FALSE");
     
	     msg.setFileName(fileName); // API TEST
	     out.fine("setFileName("+ fileName +")");
             try { 
                 out.fine("Filename associated with this body: "+ msg.getFileName());

                 if( fileName.equals(msg.getFileName()) )
                     out.fine("UNIT TEST 3:  passed\n");
                 else {
                       out.fine("UNIT TEST 3:  FAILED\n");
                       errors++;
                 }
             } catch (Exception ex) {
                 if (! (ex instanceof UnsupportedEncodingException) ) throw ex;
                 else { 
                    // ex is due to not setting the system properties which is OK
                    out.fine("UNIT TEST 3:  passed\n");
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
