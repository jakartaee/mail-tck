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

package javasoft.sqe.tests.jakarta.mail.event.FolderEvent;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.event.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>addMessageChangedListener()</strong> API.
 * It does this by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *              Listen and notifies changes in the messages in a folder. <p>
 * api2test: public addMessageChangedListener(MessageChanged)  <p>
 *
 * how2test: Call this API. Then verify that that the notification occur when changes
 *           occur to messages in a folder. If this happens then this test
 *           passed otherwise it fails.
 */

public class addMsgChangeList_Test extends MailTest implements MessageChangedListener {

    private boolean msgchanged = false;

    public static void main( String argv[] )
    {
        addMsgChangeList_Test test = new addMsgChangeList_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public void messageChanged(MessageChangedEvent e)
    {
        out.println("\nNotification: Message changed!");
        msgchanged = true;
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class FolderEvent: addMessageChangedListener(MessageChangedEvent)\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
                  return Status.failed("Invalid folder");
             }
             folder.open(Folder.READ_WRITE);
	  // BEGIN UNIT TEST:
	  
	     out.println("UNIT TEST:  addMessageChangedListener(this)\n");
	     folder.addMessageChangedListener(this);	// API TEST

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }

             for(int i = 1; i <= msgcount; i++)
             {
             // Get the message
                Message msg = folder.getMessage(i);

	        if( msg == null ) {
		    log.println("FAILED GET: "+ i +" MESSAGE");
		    continue;
	        }
	     // Set the flags for this message
	        msg.setFlag(Flags.Flag.SEEN, true);
		msg.setFlag(Flags.Flag.FLAGGED, true);
		msg.setFlag(Flags.Flag.FLAGGED, false);
	     }

	     // added so that notification has time to update
	     Thread.sleep(5);

	     if( msgchanged )
		 out.println("UNIT TEST:  passed\n");
	     else {
		   out.println("UNIT TEST:  FAILED\n");
		   errors++;
	     }
	  // END UNIT TEST:

	     folder.close(false);
	     store.close();
	     checkStatus();
	} catch ( Exception e ) {
	     handlException(e);
        }
        return status;
    }
}
