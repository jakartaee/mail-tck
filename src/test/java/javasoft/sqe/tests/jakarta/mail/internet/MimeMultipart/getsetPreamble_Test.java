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

package javasoft.sqe.tests.jakarta.mail.internet.MimeMultipart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.activation.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>MimeMultipart.getPreamble()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object. <p>
 *
 * Note: IMAP does not allow access to preamble text. <p>
 *
 * Return the preamble of the mime part. <p>
 * api2test: public String getPreamble()  <p>
 *
 * how2test: Call API, then verify if it returns predefined 
 * preamble string - "Preamble" - from the input MIME format file. 
 * If so then testcase passes, otherwise it fails.
 */

public class getsetPreamble_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run() {
	
        FileInputStream fis = null;
        
        if (!protocol.equals("imap")) {
            checkStatus();
            return status;
        }
        
        try {
            Session session = Session.getInstance(properties, null);
            
            String filename = workdir + 
                System.getProperty("file.separator") + iofile;
            fis = new FileInputStream(filename);
            
            // BEGIN UNIT TEST:
            out.fine("UNIT TEST 1:  test for getPreamble()");
            
            String existingPreambleText = "Preamble"+ System.getProperty("line.separator");
            MimeMessage mimemsg = new MimeMessage(session, fis);
            MimeMultipart mimemp = (MimeMultipart) mimemsg.getContent();

            if (existingPreambleText.equals(mimemp.getPreamble())) { // API TEST
                out.fine("UNIT TEST 1:  passed\n");
            } else {
                out.fine("UNIT TEST 1:  FAILED\n");
                errors++;
            }
            fis.close();
            // END UNIT TEST:
            
            // BEGIN UNIT TEST:
            fis = new FileInputStream(filename);            
            String newPreambleText = "NewPreamble";
            MimeMessage newmimemsg = new MimeMessage(session, fis);
            MimeMultipart newmimemp = (MimeMultipart) newmimemsg.getContent();
            
            out.fine("UNIT TEST 2: test for setPreamble(" 
                        + newPreambleText + ")");
            out.fine("The Old preamble is " +  newmimemp.getPreamble()+"...");
            newmimemp.setPreamble(newPreambleText); //API TEST
            
            out.fine("The New preamble is " +  newmimemp.getPreamble()+"..."); 
            
            if (newPreambleText.equals(newmimemp.getPreamble()))
                out.fine("UNIT TEST 2:  passed\n");
            else {
                out.fine("UNIT TEST 2:  FAILED\n");
                errors++;
            }
            // END UNIT TEST:
            
            checkStatus();
        } catch (Exception e) {
	    handlException(e);
        } finally { 
            try {
                if (fis != null) 
                    fis.close();
            } catch (IOException ex) {
                //ignore
            }
        }
	return status;     
    }
   
}
