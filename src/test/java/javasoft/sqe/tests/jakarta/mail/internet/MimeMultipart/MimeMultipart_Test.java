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
 * This class tests the <strong>MimeMultipart()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	    Default constructor <p>
 * api2test: public MimeMultipart()  <p>
 *	    Constructs a MimeMultipart object and its bodyparts from the given DataSource.<p>
 * api2test: public MimeMultipart(Datasource); <p>
 *
 * how2test: Call these constructors, then verify the type of object created to be that of
 *	     MimeMultipart. If is so then this testcase passes, otherwise it fails.
 */

public class MimeMultipart_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class MimeMultipart: MimeMultipart(void | DataSource)\n");

        try {
	  // BEGIN UNIT TEST 1:
	     out.fine("UNIT TEST 1:  MimeMultipart()");
	     MimeMultipart mp = new MimeMultipart();	    // API TEST
	     
	     if(( mp != null ) && ( mp instanceof MimeMultipart ))
		  out.fine("UNIT TEST 1: passed");
	     else {
		    out.fine("UNIT TEST 1: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
             // Create a file DataSource object
             FileDataSource ds = new FileDataSource(workdir + iofile);

             if ( ds == null ) {
                  return Status.failed("WARNING: null FileDataSource object");
             }
	     out.fine("UNIT TEST 2:  MimeMultipart(DataSource)");

	     MimeMultipart mmp = new MimeMultipart((DataSource)ds);	// API TEST

	     if(( mmp != null ) && ( mmp instanceof MimeMultipart ))
		  out.fine("UNIT TEST 2: passed");
	     else {
		    out.fine("UNIT TEST 2: FAILED");
		    errors++;
	     }
	  // END UNIT TEST 2:
	  // BEGIN UNIT TEST 3:
	     out.fine("UNIT TEST 3:  MimeMultipart(String, BodyParts...)");
	     MimeBodyPart mbp1 = new MimeBodyPart();
	     String cont1 = "part1";
	     mbp1.setText(cont1);
	     MimeBodyPart mbp2 = new MimeBodyPart();
	     String cont2 = "part2";
	     mbp2.setText(cont2);
	     MimeMultipart mpp = new MimeMultipart("mixed", mbp1, mbp2);// API TEST

	     if (mpp.getCount() == 2 &&
		    mpp.getBodyPart(0).getContent().equals(cont1) &&
		    mpp.getBodyPart(1).getContent().equals(cont2))
		  out.fine("UNIT TEST 3: passed");
	     else {
		  out.fine("UNIT TEST 3: FAILED");
		  errors++;
	     }
	  // END UNIT TEST 3:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
