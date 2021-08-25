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

package javasoft.sqe.tests.jakarta.mail.Session;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getProperty(String)</strong> API.
 * It does this by passing various valid input values and then checking
 * the return type and value. Returns null if this property does not exist. <p>
 *
 *		Returns the value of the specified property. <p>
 * api2test: public String getProperty(String name) <p>
 *
 * how2test: Call this API with various string parameters. Check that it <p>
 *	     returns a string value. <p> 
 *
 * Input:    	api parameter               | return   <p>
 *          ---------------------------------------------  <p>
 *          mail.protocol.host              | string value <p>
 *	    mail.protocol.user              | string value <p>
 *	    mail.store.protocol	            | string value
 */

public class getProperty_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        parseTestArgs(); Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
    

        out.fine("\nTesting class Session: getProperty()\n");
	String propis = null;
	
        try {
	   // Get properties object
              Properties props = new Properties();
              props.put("mail."+ protocol +".user", "testuser");
              props.put("mail."+ protocol +".host", "localhost");
	      props.put("mail.store.protocol", protocol);

	   // Create a Session object
	      Session session = Session.getInstance(props, null);

	      if( session == null ) {
		  return Status.failed("Warning: null session object!");
	      }
           // BEGIN UNIT TEST 1:
              out.fine("UNIT TEST 1: getProperty(mail."+ protocol +".host)");
              propis = session.getProperty("mail."+ protocol +".host");	    // API TEST

              if ( propis != null && propis.equals("localhost") ) {
                  out.fine("UNIT TEST 1:  passed\n");
              } else {
		     out.fine("The value returned is: "+ propis);
                     out.fine("UNIT TEST 1:  FAILED\n");
                     errors++;
              }
           // END UNIT TEST 1:
           // BEGIN UNIT TEST 2:
              out.fine("UNIT TEST 2: getProperty(mail."+ protocol +".user)");
              propis = session.getProperty("mail."+ protocol +".user");	    // API TEST

              if( propis != null && propis.equals("testuser") ) {
		  out.fine("The value returned is: "+ propis);
                  out.fine("UNIT TEST 2:  passed\n");
              } else {
                      out.fine("UNIT TEST 2:  FAILED\n");
                      errors++;
              }
           // END UNIT TEST 2:
           // BEGIN UNIT TEST 3:
              out.fine("UNIT TEST 3: getProperty(mail.store.protocol)");
              propis = session.getProperty("mail.store.protocol");	    // API TEST

              if( propis != null && propis.equals(protocol) ) {
		  out.fine("The value returned is: "+ propis);
                  out.fine("UNIT TEST 3:  passed\n");
              } else {
                      out.fine("UNIT TEST 3:  FAILED\n");
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
