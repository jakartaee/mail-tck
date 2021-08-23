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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>saveChanges()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *	Updates the appropriate header fields of message to be consistent with the message's contents. <p>
 * api2test: public void saveChanges()  <p>
 *
 * how2test: Call this API after we have used 'setFlags()' api. Check the value of the set flags.
 *	     If they match expected values then this testcase passes otherwise it fails.
 */

public class saveChanges_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Message: saveChanges()\n");

        try {
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

	     if( msg == null ) {
		 return Status.failed("Warning: Failed to create a Message object!");
	     }
	  // BEGIN UNIT TEST:
	     // Set flags for this cloned message

             msg.setFlag(Flags.Flag.ANSWERED, true);
             msg.setFlag(Flags.Flag.SEEN, false);
             msg.setFlag(Flags.Flag.DELETED, true);

             // Save changes to this message object
             out.fine("UNIT TEST 1:  saveChanges()\n");

             msg.saveChanges();	// API TEST

	     Flags flag = msg.getFlags();
	     Flags.Flag[] sf = flag.getSystemFlags();
	     boolean flagstat = false;
	
	     for( int i = 0; i < sf.length; i++ )
	     {
                  if( sf[i] == Flags.Flag.ANSWERED )
                      flagstat = true;
                  else if( sf[i] == Flags.Flag.SEEN )
                           flagstat = true;
		  else if( sf[i] == Flags.Flag.DELETED )
			   flagstat = true;
	     }
	     if( flagstat )
                 out.fine("UNIT TEST 1:  passed\n");
	     else {
		   out.fine("UNIT TEST 1: FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST:

	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
