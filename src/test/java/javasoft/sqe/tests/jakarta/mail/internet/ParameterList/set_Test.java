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

package javasoft.sqe.tests.jakarta.mail.internet.ParameterList;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>set()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 * Set a parameter. <p>
 * api2test: public void set(String, String)  <p>
 * api2test: public void set(String, String, String)  <p>
 *
 * how2test: Call API, then verify calling getNames() method. If this operation
 *	     is successfull then testcase passes, otherwise it fails.
 */

public class set_Test extends MailTest {

    public static String[] pname  = 
        { "charset","html","audio","i18set","image" };
    public static String[] pvalue = 
        { "us-ascii","html-4.x","us-dolby","ISO-9000-XZ","US-VHS" };
    private static String pcharset = "iso-8859-1";
    
    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run(System.err, System.out);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run(PrintWriter log, PrintWriter out)
    {
    super.run(log, out);

        try {
	  // create a ParameterList object
            ParameterList parmlist = new ParameterList();

            if( parmlist == null )                 
                return Status.failed("Warning: Failed " +
                        "to create ParameterList object!");
           
            // BEGIN UNIT TEST:
            out.println("\nTesting class ParameterList: set(String, String)\n");

            int k = 0; // global unit test counter

            for( int i = 0; i < pname.length; i++ ) {
                
              out.println("UNIT TEST "+ k +":  set("+ pname[i] +", "
                            + pvalue[i] +")");

              // set a parameter
              parmlist.set(pname[i], pvalue[i]); // API TEST
              String value = parmlist.get(pname[i]);

              if( pvalue[i].equals(value) )
                  out.println("UNIT TEST "+ k +": passed");
              else {
                    out.println("UNIT TEST "+ k +": FAILED");
                    errors++;
              }
              k = i;
            }

            k++;
             
            out.println("\nTesting class " +
                    "ParameterList: set(String, String, String)\n");
            
            for( int i = 0; i < pname.length; i++ ) {
                
                out.println("UNIT TEST "+ k +":  set("+ pname[i] +", "
                    + pvalue[i] + ", " + pcharset + ")");

                // set a parameter 
                parmlist.set(pname[i], pvalue[i], pcharset); // API TEST
                String value = parmlist.get(pname[i]);

                if( pvalue[i].equals(value) )
                  out.println("UNIT TEST "+ k +": passed");
                else {
                    out.println("UNIT TEST "+ k +": FAILED");
                    errors++;
                }
                k += i;
            }

            k++;
             
            out.println("\nTesting effect of encoding/decoding system " +
               "properties on ParameterList: set(String, String, String)\n");
            System.setProperty("mail.mime.encodeparameters", "true");
            System.setProperty("mail.mime.decodeparameters", "true");

            // set a parameter 
            parmlist = new ParameterList();
            parmlist.set("boundary",
"\342\200\232\303\204\303\266\342\210\232\303\221\342\210\232\342\210\202\342" +
"\200\232\303\240\303\266\342\210\232\303\253\342\200\232\303\240\303\266\342" +
"\200\232\303\240\303\207\342\200\232\303\204\303\266\342\210\232\342\200\240" +
"\342\210\232\342\210\202\342\200\232\303\204\303\266\342\210\232\303\221\342" +
"\200\232\303\204\342\200\240\342\200\232\303\204\303\266\342\210\232\342\200" +
"\240\342\210\232\342\210\202\342\200\232\303\204\303\266\342\210\232\342\200" +
"\240\342\210\232\303\241\302\254\302\250\302\254\302\256\302\254\302\250\302" +
"\254\303\206\342\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232" +
"\342\210\202\342\200\232\303\240\303\266\302\254\342\210\253\342\200\232\303" +
"\204\303\266\342\210\232\303\221\342\210\232\342\210\202\342\200\232\303\240" +
"\303\266\342\210\232\303\253\342\200\232\303\240\303\266\342\200\232\303\240" +
"\303\207\342\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232\342" +
"\210\202\342\200\232\303\204\303\266\342\210\232\303\221\342\200\232\303\204" +
"\342\200\240\342\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232" +
"\342\210\202\342\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232" +
"\303\241\302\254\302\250\302\254\302\256\302\254\302\250\302\254\303\206\342" +
"\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232\342\210\202\342" +
"\200\232\303\240\303\266\302\254\342\210\253\342\200\232\303\204\303\266\342" +
"\210\232\303\221\342\210\232\342\210\202\342\200\232\303\240\303\266\342\210" +
"\232\303\253\342\200\232\303\240\303\266\342\200\232\303\240\303\207\342\200" +
"\232\303\204\303\266\342\210\232\342\200\240\342\210\232\342\210\202\342\200" +
"\232\303\204\303\266\342\210\232\303\221\342\200\232\303\204\342\200\240\342" +
"\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232\342\210\202\342" +
"\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232\303\241\302\254" +
"\302\250\302\254\302\256\302\254\302\250\302\254\303\206\342\200\232\303\204" +
"\303\266\342\210\232\342\200\240\342\210\232\342\210\202\342\200\232\303\240" +
"\303\266\302\254\342\210\253\342\200\232\303\204\303\266\342\210\232\303\221" +
"\342\210\232\342\210\202\342\200\232\303\240\303\266\342\210\232\303\253\342" +
"\200\232\303\240\303\266\342\200\232\303\240\303\207\342\200\232\303\204\303" +
"\266\342\210\232\342\200\240\342\210\232\342\210\202\342\200\232\303\204\303" +
"\266\342\210\232\303\221\342\200\232\303\204\342\200\240\342\200\232\303\204" +
"\303\266\342\210\232\342\200\240\342\210\232\342\210\202\342\200\232\303\204" +
"\303\266\342\210\232\342\200\240\342\210\232\303\241\302\254\302\250\302\254" +
"\302\256\302\254\302\250\302\254\303\206\342\200\232\303\204\303\266\342\210" +
"\232\342\200\240\342\210\232\342\210\202\342\200\232\303\240\303\266\302\254" +
"\342\210\253\342\200\232\303\204\303\266\342\210\232\303\221\342\210\232\342" +
"\210\202\342\200\232\303\240\303\266\342\210\232\303\253\342\200\232\303\240" +
"\303\266\342\200\232\303\240\303\207\342\200\232\303\204\303\266\342\210\232" +
"\342\200\240\342\210\232\342\210\202\342\200\232\303\204\303\266\342\210\232" +
"\303\221\342\200\232\303\204\342\200\240\342\200\232\303\204\303\266\342\210" +
"\232\342\200\240\342\210\232\342\210\202\342\200\232\303\204\303\266\342\210" +
"\232\342\200\240\342\210\232\303\241\302\254\302\250\302\254\302\256\302\254" +
"\302\250\302\254\303\206\342\200\232\303\204\303\266\342\210\232\342\200\240" +
"\342\210\232\342\210\202\342\200\232\303\240\303\266\302\254\342\210\253\342" +
"\200\232\303\204\303\266\342\210\232\303\221\342\210\232\342\210\202\342\200" +
"\232\303\240\303\266\342\210\232\303\253\342\200\232\303\240\303\266\342\200" +
"\232\303\240\303\207\342\200\232\303\204\303\266\342\210\232\342\200\240\342" +
"\210\232\342\210\202\342\200\232\303\204\303\266\342\210\232\303\221\342\200" +
"\232\303\204\342\200\240\342\200\232\303\204\303\266\342\210\232\342\200\240" +
"\342\210\232\342\210\202\342\200\232\303\204\303\266\342\210\232\342\200\240" +
"\342\210\232\303\241\302\254\302\250\302\254\302\256\302\254\302\250\302\254" +
"\303\206\342\200\232\303\204\303\266\342\210\232\342\200\240\342\210\232\342" +
"\210\202\342\200\232\303\240\303\266\302\254\342\210\253",
		"iso-8859-1"); // API TEST
            String listvalue = parmlist.toString();
            ParameterList newParamList = new ParameterList(listvalue);
            String newlistvalue = newParamList.toString();
            out.println("listvalue = " + listvalue);
            out.println("newlistvalue = " + newlistvalue);
            if( listvalue.equals(newlistvalue) )
                out.println("UNIT TEST passed");
            else {
                out.println("UNIT TEST FAILED");
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
