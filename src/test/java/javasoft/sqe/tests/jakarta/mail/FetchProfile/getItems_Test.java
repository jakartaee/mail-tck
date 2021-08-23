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

package javasoft.sqe.tests.jakarta.mail.FetchProfile;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getItems()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Get the items set in this profile. <p>
 * api2test: public void getItems()  <p>
 *
 * how2test: Call this API, check the returned item values. If these are
 *	     same as add() arguments, then this testcase passes, otherwise it fails.
 */

public class getItems_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	

        out.fine("\nTesting class FetchProfile: getItems()\n");

        try {
	   // Create an empty FetchProfile
	      FetchProfile fp = new FetchProfile();
	     
	      if( fp == null ) {
		  return Status.failed("Failed to create an empty FetchProfile object!");
	      }
	   // Add header names to Profile object

              fp.add(FetchProfile.Item.ENVELOPE);
              fp.add(FetchProfile.Item.FLAGS);
              fp.add(FetchProfile.Item.CONTENT_INFO);
              fp.add(FetchProfile.Item.SIZE);

	   // BEGIN UNIT TEST 1:

	      out.fine("UNIT TEST 1: getItems()");

	      FetchProfile.Item[] items = fp.getItems(); 	// API TEST

	      boolean foundEnv = false, foundFlags = false,
			foundCont = false, foundSize = false;
	      for (int j = 0; j < items.length; j++) {
		   if( items[j] == FetchProfile.Item.ENVELOPE )
			foundEnv = true;
		   else if( items[j] == FetchProfile.Item.FLAGS )
			foundFlags = true;
		   else if( items[j] == FetchProfile.Item.CONTENT_INFO )
			foundCont = true;
		   else if( items[j] == FetchProfile.Item.SIZE )
			foundSize = true;
              }

	      if (foundEnv && foundFlags && foundCont && foundSize)
		  out.fine("UNIT TEST 1: passed.\n");
	      else {
		     out.fine("UNIT TEST 1: FAILED.\n");
		     errors++;
	      }
	   // END UNIT TEST 1:

              checkStatus();

        } catch ( Exception e ) {
              handlException(e);
        }
	return status;
     }
}
