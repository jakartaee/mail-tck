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

package javasoft.sqe.tests.jakarta.mail.internet.MimeBodyPart;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>attachFile and saveFile</strong> API.
 * It does by first creating a file and then attaching to the MimeBodyPart
 * and then tests the output of getFilename() method.
 *
 * api2test: public void attachFile(String)  <p>
 * api2test: public void attachFile(File)  <p>
 * api2test: public void saveFile(String)  <p>
 * api2test: public void saveFile(File)  <p>
 * api2test: public void attachFile(String, String, String)  <p>
 * api2test: public void attachFile(File, String, String)
 */

public class attachFile_saveFile_Test extends MailTest {

    private static String fn = "testfile.txt";
    private static String savedfn = "mbp-content.txt";

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run() {
	

	Session session = createSession();

        // create file to attach
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(fn));
            fw.write("This is a test file.");
            fw.close();
        } catch (IOException e) {
            out.fine("\nError writing to file.");
        }

        out.fine("\nTesting class MimeBodyPart: attachFile\n");

        File file = null;
        File savedfile = null;
        try {
         // Create a MimeBodyPart object
            MimeBodyPart bp = new MimeBodyPart();

	 // BEGIN UNIT TEST:

            out.fine("UNIT TEST 1 : attachFile(String) " +
                    "And saveFile(String)");

            out.fine("attached file name = " + fn
                       + "\nsaved file name = " + savedfn);
            bp.attachFile(fn); 	// API TEST

            if (fn.equals(bp.getFileName())) {
               out.fine(bp.getFileName());
               out.fine("UNIT TEST passed.");
            } else {
               out.fine("UNIT TEST FAILED");
	       errors++;
            }

            bp.attachFile(fn);          // API TEST
	    if (!bp.getDisposition().equals(Part.ATTACHMENT)) {
               out.fine("UNIT TEST FAILED");
	       errors++;
	    }
            bp.saveFile(savedfn);       // API TEST
            compareOriginalFileToAttachedFile(fn, savedfn);

         // END UNIT TEST:

	 // BEGIN UNIT TEST:

            out.fine("UNIT TEST 2 : attachFile(File) " +
                    "And saveFile(File)");

            file = new File(fn);
            savedfile = new File(savedfn);
            bp.attachFile(file); 	// API TEST
            bp.saveFile(savedfile);     // API TEST
            compareOriginalFileToAttachedFile(file, savedfile);

         // END UNIT TEST:

	 // BEGIN UNIT TEST:

            out.fine("UNIT TEST 3 : attachFile(String, String, String)");

            out.fine("attached file name = " + fn);
	    MimeMessage msg = new MimeMessage(session);
	    MimeMultipart mp = new MimeMultipart();
	    bp = new MimeBodyPart();
            bp.attachFile(fn, "test/test", "base64"); 	// API TEST
	    mp.addBodyPart(bp);
	    msg.setContent(mp);
	    msg.saveChanges();	// force headers to be updated

	    if (bp.isMimeType("test/test") &&
		    bp.getEncoding().equals("base64")) {
                out.fine("UNIT TEST passed.");
            } else {
		out.fine("content type = " + bp.getContentType());
		out.fine("encoding = " + bp.getEncoding());
                out.fine("UNIT TEST FAILED");
	        errors++;
            }

         // END UNIT TEST:

	 // BEGIN UNIT TEST:

            out.fine("UNIT TEST 4 : attachFile(File, String, String)");

            out.fine("attached file name = " + fn);
	    msg = new MimeMessage(session);
	    mp = new MimeMultipart();
	    bp = new MimeBodyPart();
            bp.attachFile(fn, "test/test", "base64"); 	// API TEST
	    mp.addBodyPart(bp);
	    msg.setContent(mp);
	    msg.saveChanges();	// force headers to be updated

	    if (bp.isMimeType("test/test") &&
		    bp.getEncoding().equals("base64")) {
                out.fine("UNIT TEST passed.");
            } else {
		out.fine("content type = " + bp.getContentType());
		out.fine("encoding = " + bp.getEncoding());
                out.fine("UNIT TEST FAILED");
	        errors++;
            }

         // END UNIT TEST:

            checkStatus();
        } catch (Exception e) {
	     handlException(e);
        } finally {
            if (file != null) file.delete();
            if (savedfile != null) savedfile.delete();
        }

        return status;
    }

    private void compareOriginalFileToAttachedFile(
        File origFile, File newFile) throws IOException {

        FileInputStream origfis = new FileInputStream(origFile);
        FileInputStream newfis = new FileInputStream(newFile);
        int bytecontent = origfis.read();
        boolean samecontents = true;

        while (bytecontent != -1) {
            if (bytecontent != newfis.read()) {
                samecontents = false;
                out.fine("UNIT TEST FAILED.\n");
                break;
            }
            bytecontent = origfis.read();
        }

        if (samecontents && newfis.read() == -1)
            out.fine("UNIT TEST passed.\n");
        else errors++;

        if (origfis != null)
            origfis.close();
        if (newfis != null)
            newfis.close();
    }

    private void compareOriginalFileToAttachedFile(
        String origFilename, String newFilename) throws IOException {

       File file = new File(origFilename);
       File savedfile = new File(newFilename);
       compareOriginalFileToAttachedFile(file, savedfile);
    }
}
