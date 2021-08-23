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
 * This class tests the <strong>setFlag()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the specified flags on this message to the specified value. <p>
 * api2test: public void setFlag(Flags.Flag, boolean set)  <p>
 *
 * how2test: Call this API with various arguments, then call getFlags() api, if the
 *	     getFlag value is the same as setFlag then this test is successfull, 
 *	     otherwise it fails.
 */

public class setFlag_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }
    // check if setFlags does its job successfully

    public boolean testFlag(MimeMessage msg)
    {
        Flags flag;
        boolean unitest = false;

        try {
              flag = msg.getFlags();
              Flags.Flag[] sf = flag.getSystemFlags(); // get the system flags

              for( int i = 0; i < sf.length; i++ )
              {
                   Flags.Flag f = sf[i];

                   if( f == Flags.Flag.ANSWERED )
                       unitest = true;
                   else if( f == Flags.Flag.DELETED )
                            unitest = true;
                   else if( f == Flags.Flag.DRAFT )
                            unitest = true;
                   else if( f == Flags.Flag.FLAGGED )
                            unitest = true;
                   else if( f == Flags.Flag.SEEN )
                            unitest = true;
                   else
                       unitest = true;
              }
        } catch ( Exception e ) {
                this.handlException(e);
        }
        return unitest;
    }

    public Status run()
    {
	

        out.fine("\nTesting class Message: setFlag(Flags.Flag, boolean)\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.warning("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
	  // BEGIN UNIT TEST:
	     // Set the flags for this message

	     out.fine("UNIT TEST 1:  setFlags(Flags.Flag, boolean)\n");

	     out.fine("setFlag(Flag.ANSWERED, true)");
	     msg.setFlag(Flags.Flag.ANSWERED, true);		// APT TESTS

             if( testFlag(msg) )
                 out.fine("ANSWERED flag set to TRUE\n");
             else {
                   out.fine("ANSWERED flag not set to TRUE\n");
                   errors++;
             }
	     out.fine("setFlag(Flag.SEEN, true)");
	     msg.setFlag(Flags.Flag.SEEN, true);		// APT TESTS

             if( testFlag(msg) )
                 out.fine("SEEN flag set to TRUE\n");
             else {
                   out.fine("Warning: SEEN flag not set to TRUE\n");
                   errors++;
             }
	     out.fine("setFlag(Flags.RECENT, false)");
	     msg.setFlag(Flags.Flag.RECENT, false);		// APT TESTS

             if( testFlag(msg) )
                 out.fine("RECENT flag set to FALSE\n");
             else {
                   out.fine("Warning: RECENT flag not set to FALSE\n");
                   errors++;
             }
	     out.fine("setFlag(Flag.DRAFT, false)");
	     msg.setFlag(Flags.Flag.DRAFT, false);		// APT TESTS

             if( testFlag(msg) )
                 out.fine("DRAFT flag set to FALSE\n");
             else {
                   out.fine("Warning: DRAFT flag not set to FALSE\n");
                   errors++;
             }
	     out.fine("setFlag(Flag.DELETED, true)");
	     msg.setFlag(Flags.Flag.DELETED, true);		// APT TESTS

             if( testFlag(msg) )
                 out.fine("DELETED flag set to TRUE\n");
             else {
                   out.fine("Warning: DELETED flag not set to TRUE\n");
                   errors++;
             }

	     if( errors == 0 )
                 out.fine("UNIT TEST 1:  passed\n");
	     else
		 out.fine("UNIT TEST 1:  FAILED\n");

	  // END UNIT TEST

	     checkStatus();

	} catch ( Exception e ) {
             handlException(e);
        }
        return status;
    }
}
