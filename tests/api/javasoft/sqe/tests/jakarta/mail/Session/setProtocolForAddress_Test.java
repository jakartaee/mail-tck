/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
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
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.Provider.Type;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getClassName()</strong> API.
 * It does this by passing various valid input values and then checking the type
 * of the object returned.  <p>
 *		Returns name of the class that implements the protocol. <p>
 * api2test: public String getClassName()  <p>
 *
 * how2test: Call this API, check that it returns the expected class name.
 *	     If it does then this testcase passes, otherwise it fails.
 */

public class setProtocolForAddress_Test extends MailTest {

    public static void main( String argv[] )
    {
        setProtocolForAddress_Test test = new setProtocolForAddress_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        try {

            // Get Session object
            Session session = Session.getInstance(properties, null);

            if (session  == null) 
                return Status.failed("Failed to get Session object!");

            Provider prov = new Provider(Type.TRANSPORT, "airmail", 
                "javasoft.sqe.tests.jakarta.mail.Session.setProtocolForAddress_Test$AirMailTestTransport", 
                "Sun Microsystems, Inc", "1.0"); // API TEST
            
            session.setProvider(prov);
            
            // BEGIN UNIT TEST 1:
            out.println("UNIT TEST 1: setProtocolForAddress()");
            String addressType = "rfc999999";
            String protocol = "airmail";
            session.setProtocolForAddress(addressType, protocol); // API TEST
            
            // Session object's addressMap
            Transport transport = session.getTransport(new AirMailAddress());            
            if (transport instanceof AirMailTestTransport)  {
                out.println("UNIT TEST 1:  passed\n");
            } else {
                out.println("UNIT TEST 1:  FAILED\n");
                errors++;
            }
            // END UNIT TEST 1:

	    checkStatus();

        } catch (Exception e) {
	    handlException(e);
        }
	return status;
}

    class AirMailAddress extends Address {
 
        public String getType() { return "rfc999999"; }
        
        public String toString() { return "rfc999999:airmail"; }
        
        public boolean equals(Object address) { 
            return true;
        }
    }
    
    public static class AirMailTestTransport extends Transport {
        public void sendMessage(Message msg, Address[] addresses) 
                            throws MessagingException { 
            // no-op
        }

        public AirMailTestTransport(Session session, URLName urlname) {
            super(session, urlname);
        }
    }     
}
