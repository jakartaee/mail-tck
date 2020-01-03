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

package javasoft.sqe.tests.jakarta.mail.util;

import java.io.*;
import java.util.*;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import com.sun.javatest.*;

/**
 * This class declares common fields and defines utility methods for
 * parsing command-line arguments and checking pass/fail status of
 * testcases. It also has methods for memory checking and dumping
 * stack trace to some file after an exception occurs.
 */

public class MailTest implements Test {

	public String testname;		// testcase name
	public String protocol;		// server protocol being used
	public String transport_protocol;	// Transport protocol
	public String host;		// host server name
	public String transport_host;	// transport host server name
	public String user;		// user login id
	public String password;		// user password
	public boolean auth;		// use smtp authentication?
	public String mailbox;		// test mailbox
	public String testbox;		// temporary mailbox
	public String from;		// from email address
	public String to;		// to email address
	public String rootpath;		// search path to mailbox
	public String pattern;		// search pattern
	public String iofile;		// input/output file
	public String newName;		// new mailbox name
	public String subject;          // the subject to search for
	public String portvalue;	// protocol port parameter
	public String tportvalue;	// transport protocol port parameter
	public String workdir;		// test work directory
	public String proxy;		// SOCKS5 proxy
	public int msgcount = -1;	// message number
	public int portnum = -1;	// protocol port number
	public int tportnum = -1;	// transport protocol port number
	public int errors = 0;		// number of unit test errors
	public Status status;		// JavaTest harness pass/fail status
	public Properties properties;	// the global properties object
	public PrintWriter out;		// output stream
        public PrintWriter log;		// error output stream
	public Session session;		// Session object variable
	public boolean debug = false;	// debug mode


	/**
	 * Default main() method ; overidden in subclasses.
	 *
	 * @param	argv	command line arguments
	 */
	public static void main(String argv[]) {
		MailTest test = new MailTest();
		Status s = test.run(argv, System.err, System.out);
		s.exit();
	}

	/**
	 * Create System properties object.
	 */
	public MailTest() {
	      properties = new Properties();
	}

	/**
	 * Convert streams to Writers.
	 *
	 * @param	argv	command line arguments
	 * @param	log	the log stream
	 * @param	out	the output stream
	 * @return	the Status of the test
	 */
	public Status run(String argv[], PrintStream log, PrintStream out) {
		return run(argv, new PrintWriter(log, true),
		    new PrintWriter(out, true));
	}

	/**
	 * Default run() method ; sets i/o streams.
	 *
	 * @param	argv	command line arguments
	 * @param	log	the log stream
	 * @param	out	the output stream
	 * @return	null
	 */
	public Status run(String argv[], PrintWriter log, PrintWriter out) {
		this.out = out;
		this.log = log;
		return null;
	}

	/**
	 * Get command-line arguments and 
	 * stuff the values into member fields.
	 *
	 * @param	argv	command line arguments
	 */
	public void parseArgs(String argv[]) {
	  int optind;

          for (optind = 0; optind < argv.length; optind++) {
		if (argv[optind].equals("-t"))
		    	protocol = argv[++optind];
		else if (argv[optind].equals("-tp"))
			transport_protocol = argv[++optind];
		else if (argv[optind].equals("-th"))
			transport_host = ifnull(argv[++optind]);
		else if (argv[optind].equals("-h"))
			host = ifnull(argv[++optind]);
		else if (argv[optind].equals("-u"))
			user = ifnull(argv[++optind]);
		else if (argv[optind].equals("-p"))
			password = ifnull(argv[++optind]);
		else if (argv[optind].equals("-m"))
			mailbox = argv[++optind];
		else if (argv[optind].equals("-test"))
			testbox = argv[++optind];
		else if (argv[optind].equals("-from"))
			from = argv[++optind];
		else if (argv[optind].equals("-to"))
			to = argv[++optind];
		else if (argv[optind].equals("-r"))
			rootpath = argv[++optind];
		else if (argv[optind].equals("-io"))
			iofile = argv[++optind];
		else if (argv[optind].equals("-s"))
			pattern = argv[++optind];
		else if (argv[optind].equals("-n"))
			newName = argv[++optind];
		else if (argv[optind].equals("-subject"))
			subject = argv[++optind];
		else if (argv[optind].equals("-pn"))
                        portvalue = argv[++optind];
		else if (argv[optind].equals("-tpn"))
                        tportvalue = argv[++optind];
		else if (argv[optind].equals("-WorkDir"))
			workdir = argv[++optind];
		else if (argv[optind].equals("-A"))
			auth = true;
		else if (argv[optind].equals("-proxy"))
			proxy = argv[++optind];
		else if (argv[optind].equals("-D"))
			debug = true;
		else if (argv[optind].equals("--")) {
			optind++;
			break;
		} else if (argv[optind].startsWith("-")) {
			  out.println(
"Usage: test [-D] [-t protocol] [-tp transport_protocol] [-th transport_host]");
			  out.println(
"\t[-h host] [-u user] [-p password] [-r rootpath] [-m mailbox]");
			  out.println(
"\t[-test testbox] [-from from_address] [-to to_address] [-io iofile]");
			  out.println(
"\t[-s pattern] [-n newname] [-subject subject] [-pn port_number]");
			  out.println(
"\t[-tpn transport_port_number]");
			  out.println(
"\t[-WorkDir workdirpath] [-A] [-proxy socks-proxy] [msgcount]");
			System.exit(1);
		} else
			break;
	    }
	    // get integer number from the command-line

	    if (optind < argv.length)
		msgcount = Integer.parseInt(argv[optind]);

	    if (portvalue != null)
		portnum = Integer.parseInt(portvalue);

	    if (tportvalue != null)
		tportnum = Integer.parseInt(tportvalue);

	    if (transport_host == null)
		transport_host = host;

	    if (protocol != null) {
		properties.setProperty("mail.store.protocol", protocol);
		if (host != null)
		    properties.setProperty("mail." + protocol + ".host", host);
		if (user != null)
		    properties.setProperty("mail." + protocol + ".user", user);
		if (portnum > 0)
		    properties.setProperty("mail." + protocol + ".port",
								"" + portnum);
		if (proxy != null)
		    properties.setProperty("mail." + protocol + ".socks.host",
									proxy);
	    }
	    if (transport_protocol != null) {
		properties.setProperty(
			"mail.transport.protocol", transport_protocol);
		properties.setProperty(
			"mail.transport.protocol.rfc822", transport_protocol);
		if (transport_host != null)
		    properties.setProperty(
			"mail." + transport_protocol + ".host", transport_host);
		if (auth && user != null)
		    properties.setProperty(
			"mail." + transport_protocol + ".user", user);
		if (tportnum > 0)
		    properties.setProperty(
			"mail." + transport_protocol + ".port", "" + tportnum);
		properties.setProperty(
			"mail." + transport_protocol + ".auth", "" + auth);
		if (proxy != null)
		    properties.setProperty(
			"mail." + transport_protocol + ".socks.host", proxy);
	    }
	    if (debug) {
		System.out.println("Session properties:");
		for (String s : properties.stringPropertyNames())
		    System.out.println(s + "=" + properties.getProperty(s));
	    }
	}

	/**
	 * If the command line argument has the special value "NULL",
	 * return null.
	 */
	private static String ifnull(String arg) {
	    return arg.equals("NULL") ? null : arg;
	}

	/**
	 * Create a Session.
	 *
	 * @return	the Session
	 */
	public Session createSession() {
	    final String user0 = user, password0 = password;
	    Session session = Session.getInstance(properties,
		new Authenticator() {
		    public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user0, password0);
		    }
		});
	    session.setDebug(debug);
	    return session;
	}

	/**
	 * Connect to host machine.
	 *
	 * @param	proto	the protocol (e.g., "imap")
	 * @param	host	the host name
	 * @param	user	the user name
	 * @param	password the password
	 * @return	the Store
	 */
	public Store connect2host(String proto, String host, String user,
							    String password) {
	    Store store = null;

	    try {
	     // Get a Session object
		session = createSession();
		session.setDebug(debug);

		if (debug) {
		    out.println("Password is:" + password);
		    out.println("User is:" + user);
		    out.println("Host:" + host);
		    out.println("Port:" + portnum);
		    out.println("Protocol:" + proto);
		}

		if (session == null) {
		    out.println("Warning: Failed to create a Session object!");
		    return null;
		}
	
	     // Get a Store object
		store = session.getStore(proto);

                if (store == null) {
                    out.println("Warning: Failed to create a Store object!");
		    return null;
		}
             // Connect
                if (host != null || user != null || password != null)
		    if (portnum > 0)
			store.connect(host, portnum, user, password);
		    else
			store.connect(host, user, password);
                else
                    store.connect();

	    } catch (Exception e) {
            	e.printStackTrace(out);
            }
	    return store;
	}

	/**
	 * Get the root folder.
	 *
	 * @param	store	the Store
	 * @return	the Folder
	 */
	public Folder getRootFolder(Store store) {
	   Folder folder = null;
	   try {
		if (rootpath.equals(""))
		    folder = store.getDefaultFolder();
		else
		    folder = store.getFolder(rootpath);
	   } catch (Exception e) {
		e.printStackTrace(out);
	   }
	   return folder;
	}

	/**
	 * Check run-time memory and pass/fail status.
	 *
	 * @return	the Status
	 */
	public Status checkMem() {
	   Runtime rt = Runtime.getRuntime();

           out.println("Free Memory = " + rt.freeMemory());
           out.println("Test Aborted!");

           if (errors == 0)
               status = Status.passed("OKAY");
           else
               status = Status.failed("");

           return status;
	}

	/**
	 * Creates and returns a ByteArrayInputStream for given Message object
	 *
	 * @param	msg	the Message
	 * @return	the ByteArrayInputStream
	 */
	public ByteArrayInputStream createInputStream(Message msg) {
	  ByteArrayInputStream bis = null;

	  try {
		// Create a ByteArrayOutStream object
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		if (bos == null) {
                    out.println("Warning: Failed to create a ByteArrayOutputStream object!");
                    return null;
                }
		msg.writeTo(bos);

		// Create a ByteArrayInputStream object
		bis = new ByteArrayInputStream(bos.toByteArray());

		if (bis == null) {
                    out.println("Warning: Failed to create a ByteArrayInputStream object!");
                    return null;
                }
	  } catch (Exception e) {
		handlException(e);
	  }
	  return bis;
	}

	/**
	 * Check testcase pass/fail status.
	 */
	public void checkStatus() {
           if (errors == 0)
               status = Status.passed("OKAY");
           else
               status = Status.failed("");
	}

	/**
	 * Deals with exception, produces stack trace, writes test report.
	 *
	 * @param	e	the Exception
	 */
	public void handlException(Exception e) {
           out.println("\nException caught!");
           status = Status.failed("EXCEPTION");
           e.printStackTrace(out);
	}

        /**
	 * Deals with exception, produces stack trace, writes test report.
	 *
	 * @param	e	the Exception
         */
        public void ExceptionTest(Exception e) {
           out.println("\nException caught!");
           status = Status.passed("OKAY");
           e.printStackTrace(out);
        }
}
