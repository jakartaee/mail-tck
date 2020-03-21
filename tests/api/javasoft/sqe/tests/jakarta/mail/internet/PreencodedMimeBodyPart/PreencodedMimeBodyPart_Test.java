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

package javasoft.sqe.tests.jakarta.mail.internet.PreencodedMimeBodyPart;

import java.util.*;
import java.io.*;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;
import jakarta.mail.util.ByteArrayDataSource;

/**
 * This class tests the <strong>PreencodedMimeBodyPart()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * api2test: public PreencodedMimeBodyPart(String) <p>
 * api2test: public getEncoding() <p>
 * api2test: public writeTo(OutputStream os) <p>
 * api2test: public updateHeaders()
 * 
 */

public class PreencodedMimeBodyPart_Test extends MailTest {

    private static final String humbugEncoding = "my-quoted-printable";
    private static final String sevenBit = "7bit";
    private static final String baseSixtyFour = "base64";
    
    public static void main(String argv[]) {
        PreencodedMimeBodyPart_Test test = new PreencodedMimeBodyPart_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out) {
        
	super.run(argv, log, out);
	parseArgs(argv); // parse command-line options

        out.println("\nTesting class PreencodedMimeBodyPart:" +
                " PreencodedMimeBodyPart(String)\n");

        try {
            out.println("UNIT TEST 1: Testing PreencodedMimeBodyPart("
                    + sevenBit +")");

            PreencodedMimeBodyPart preencMbp = 
                new PreencodedMimeBodyPart(sevenBit); // API TEST

           // BEGIN UNIT TEST 1:
            out.println("UNIT TEST 1: Testing getEncoding()");
            String enc = preencMbp.getEncoding(); // API TEST
            if (sevenBit.equals(enc)) out.println("UNIT TEST 1: Passed.\n"); 
            else {
                out.println("UNIT TEST 1: FAILED.\n");
                errors++;
            }
           // END UNIT TEST 1:
            
           // BEGIN UNIT TEST 2:            
            out.println("UNIT TEST 2: Testing writeTo(OutputStream)");            
            String infoText = "This is informational text. ??-a_german_character";            
            byte[] initialbarrout = infoText.getBytes();                        
            
            //create a base64 encoded string from infoText            
            ByteArrayOutputStream baos0 = new ByteArrayOutputStream();             
            OutputStream b64os = MimeUtility.encode(baos0, baseSixtyFour,"");            
            b64os.write(initialbarrout); //obtained base64 encoded byte array            
            b64os.flush();            
            out.println("base64 encoded byte array = " + 
                    new String(baos0.toByteArray()));            
            
            //set this base64 encoded string as the content             
            //of a PreencodedMimeBodyPart             
            ByteArrayDataSource bads = 
                new ByteArrayDataSource(baos0.toByteArray(), 
                    "application/octet-stream");            
            PreencodedMimeBodyPart preencMbp1 = 
                new PETest(baseSixtyFour); // API TEST            
            preencMbp1.setDataHandler(new DataHandler(bads));            
            //preencMbp1.setHeader("Content-Type", "text/plain");
            
            //write out this encoded stuff in PreencodedMimeBodyPart to a             
            //ByteArrayOutputStream and get those bytes as rf2822 format stream            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();            
            preencMbp1.writeTo(baos);            
            byte[] encodedbarr = baos.toByteArray();                        
            out.println("PreencodedMimeBodyPart.writeTo = " +                 
                new String(encodedbarr));            
            MimeBodyPart mbp1 =                
                new MimeBodyPart(new ByteArrayInputStream(encodedbarr));             
            InputStream mbp1is = mbp1.getInputStream();            
            byte[] finalbarrout = new byte[initialbarrout.length];            
            mbp1is.read(finalbarrout);                        
            out.println("finalbarrout = " + new String(finalbarrout));                        
            if (Arrays.equals(finalbarrout, initialbarrout)) {                
                out.println("UNIT TEST 2: Passed.\n");             
            } else {                
                out.println("UNIT TEST 2: FAILED.\n");                
                errors++;            
            }           
           // END UNIT TEST 2:
            
           // BEGIN UNIT TEST 3:
            out.println("UNIT TEST 3: Testing updateHeaders(OutputStream)");
            
            infoText = " Hedge Fund strategies. \n" +
                   " 1. Trading Options and Derivatives. \n" + 
                   " 2. Using Arbitrage. \n" + 
                   " 3. Using Leverage. \n" +
               "--abc \n";
            
            MyPreencodedMimeBodyPart myPreencMbp = 
                new MyPreencodedMimeBodyPart(sevenBit); // API TEST
            myPreencMbp.setText(infoText);
            
            //NOTE THE DIFF ENCODING
            myPreencMbp.addHeader("Content-transfer-encoding", baseSixtyFour);
            myPreencMbp.testUpdateHeaders();
            String[] hdr = myPreencMbp.getHeader("Content-transfer-encoding");
            
            if (hdr[0].equals(sevenBit))
                out.println("UNIT TEST 3: Passed.\n"); 
            else {
                out.println("UNIT TEST 3: FAILED.\n");
                errors++;
            }
           // END UNIT TEST 3:
            
           checkStatus();
        } catch (Exception e) {
           handlException(e);
        }
	return status;
     }
    
    class MyPreencodedMimeBodyPart extends PreencodedMimeBodyPart {
        
        public MyPreencodedMimeBodyPart(String encoding) {
            super(encoding);
        }
        
        void testUpdateHeaders() throws MessagingException {
            this.updateHeaders();
        }
    }
    
    class PETest extends PreencodedMimeBodyPart {    
        public PETest(String encoding) {        
            super(encoding);    
        }    
        
        public void writeTo(OutputStream os) throws IOException {        
            try {
                super.updateHeaders();        
                super.writeTo(os);    
            } catch (MessagingException mex) {
                mex.printStackTrace();
                throw new IOException(mex.getMessage());
            }
        }
    }    
}
