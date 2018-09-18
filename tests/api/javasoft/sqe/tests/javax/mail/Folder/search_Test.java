/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.javax.mail.Folder;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.javax.mail.util.MailTest;

/**
 * This class tests the <strong>search(..)</strong> API.
 * It does this by passing various valid input values and then checking
 * the value/type of the returned object.	<p>
 *
 *	     Search this Folder for messages matching the specified search criterion. <p>
 * api2test: public Message[] search(Term term) throws MessagingException <p>
 *
 * api2test: public Message[] search(Term term, Message msgs[])    <p>
 *	     Search the specified messages in this Folder that match the specified search
 *	     criterion.  <p>
 *
 * how2test: Call this API with various 'Term' arguments for specified 'Messages' range.
 *	     Check for list of messages returned that match the pattern search. If this
 *	     operation is successfull then the testcase is considered passing.
 */

public class search_Test extends MailTest {

    private static String SUBJECT = "Subject";
    private static String DATE = "Date";

    public static void main( String argv[] )
    {
        search_Test test = new search_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Folder: search(Term)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
		  return Status.failed("Invalid folder object!");
       	     }
             folder.open(Folder.READ_ONLY);
          // BEGIN UNIT TEST 1:
	     out.println("UNIT TEST 1: search("+ pattern +") in message body.\n");

	     BodyTerm freeStr = new BodyTerm(pattern);
	     Message[] msgList1 = folder.search(freeStr);	// API TEST

	     if ( msgList1.length > 0 ) {
	          for ( int i = 0; i < msgList1.length; i++ )
		  	if ( msgList1[i] != null )
			     out.println("Pattern "+ pattern +" found in the message: "+ msgList1[i].getFrom()[0]);

		  out.println("UNIT TEST 1: passed\n");
	     }
	     else
		 out.println("Pattern "+ pattern +" not found in message body!\n");

	  // END UNIT TEST 1:
	  // BEGIN UNIT TEST 2:
	     out.println("UNIT TEST 2: search("+ pattern +") in message header.\n");

	     HeaderTerm caseTerm = new HeaderTerm(SUBJECT, pattern);
	     Message[] msgList2 = folder.search(caseTerm);	// API TEST

	     if ( msgList2.length > 0 ) {
		  for ( int i = 0; i < msgList2.length; i++ )
		  	if ( msgList2[i] != null )
			     out.println("Case-sensitive pattern "+ pattern +" found in the message: "+ msgList2[i].getFrom()[0]);

		  out.println("UNIT TEST 2: passed\n");
	     }
	     else
		 out.println("Pattern "+ pattern +" not found in message headers!\n");

          // END UNIT TEST 2:
          // BEGIN UNIT TEST 3:
	     // Get number of messages in folder
	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
             // Get all of messages in this folder
             Message[] msgs = folder.getMessages(1, msgcount);

             out.println("UNIT TEST 3: search("+ pattern +", Message[]) in message Subject.\n");

	     SubjectTerm subStr = new SubjectTerm(pattern);
	     Message[] msgList3 = folder.search(subStr, msgs);		// API TEST

	     if( msgList3.length > 0 ) {
		 for ( int i = 0; i < msgList3.length; i++ )
			if ( msgList3[i] != null )
			     out.println("Pattern "+ pattern +" found in the Subject of message: "+ msgList3[i].getFrom()[0]);

		  out.println("UNIT TEST 3: passed\n");
	     }
	     else
		 out.println("Pattern "+ pattern +" not found in specified message range!\n");

	  // END UNIT TEST 3:
	  // BEGIN UNIT TEST 4:
	     out.println("UNIT TEST 4: search("+ pattern +", Message[]);\n");

	     BodyTerm bodyTerm = new BodyTerm(pattern);
	     Message[] msgList4 = folder.search(bodyTerm, msgs);	// API TEST

	     if ( msgList4.length > 0 ) {
                  for ( int i = 0; i < msgList4.length; i++ )
			if ( msgList4[i] != null )
			     out.println("Pattern "+ pattern +" found in the body of message: " + msgList4[i].getFrom()[0]);
		  out.println("UNIT TEST 4: passed\n");
	     }
	     else
		 out.println("Pattern "+ pattern +" not found in bodies of given messages!\n");

	  // END UNIT TEST 4:
	     folder.close(false);
	     store.close();
	     status = Status.passed("OKAY");

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
