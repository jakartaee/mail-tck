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

package javasoft.sqe.tests.jakarta.mail.internet.MailDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintWriter;
import java.util.Date;
import jakarta.mail.internet.MailDateFormat;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the methods that must throw UnsupportedOperationException.
 * It does by calling the methods and checking for the exception. <p>
 *
 * api2test: public void setCalendar(Calendar)  <p>
 * api2test: public void setNumberFormat(NumberFormat)  <p>
 * api2test: public void applyLocalizedPattern(String)  <p>
 * api2test: public void applyPattern(String)  <p>
 * api2test: public Date date2DigitYearStart()  <p>
 * api2test: public void set2DigitYearStart(Date)  <p>
 * api2test: public void setDateFormatSymbols(DateFormatSymbols)  <p>
 *
 * how2test: Call API, if throws UnsupprotedOperationException
 *	     then testcase passes otherwise it fails.
 */

public class unsupportedOperation_Test extends MailTest {

    @org.junit.jupiter.api.Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
	
        out.fine("\nTesting class MailDateFormat: UnsupportedOperationException\n");

	try {
	   MailDateFormat mdf = new MailDateFormat();

	   // BEGIN UNIT TEST 1:
	      out.fine("UNIT TEST 1: setCalendar()");
	      try {
	      	  mdf.setCalendar(null);
	          out.fine("\nUNIT TEST 1: FAILED.\n");
	          errors++;
	      } catch (UnsupportedOperationException ex) {
	          out.fine("\nUNIT TEST 1: passed.\n");
	      }
	   // END UNIT TEST 1:

	   // BEGIN UNIT TEST 2:
	      out.fine("UNIT TEST 2: setNumberFormat()");
	      try {
	      	  mdf.setNumberFormat(null);
	          out.fine("\nUNIT TEST 2: FAILED.\n");
	          errors++;
	      } catch (UnsupportedOperationException ex) {
	          out.fine("\nUNIT TEST 2: passed.\n");
	      }
	   // END UNIT TEST 2:

	   // BEGIN UNIT TEST 3:
	      out.fine("UNIT TEST 3: applyLocalizedPattern()");
	      try {
	      	  mdf.applyLocalizedPattern(null);
	          out.fine("\nUNIT TEST 3: FAILED.\n");
	          errors++;
	      } catch (UnsupportedOperationException ex) {
	          out.fine("\nUNIT TEST 3: passed.\n");
	      }
	   // END UNIT TEST 3:

	   // BEGIN UNIT TEST 4:
	      out.fine("UNIT TEST 4: applyPattern()");
	      try {
	      	  mdf.applyPattern(null);
	          out.fine("\nUNIT TEST 4: FAILED.\n");
	          errors++;
	      } catch (UnsupportedOperationException ex) {
	          out.fine("\nUNIT TEST 4: passed.\n");
	      }
	   // END UNIT TEST 4:

	   // BEGIN UNIT TEST 5:
	      out.fine("UNIT TEST 5: get2DigitYearStart()");
	      try {
	      	  Date d = mdf.get2DigitYearStart();
	          out.fine("\nUNIT TEST 5: FAILED.\n");
	          errors++;
	      } catch (UnsupportedOperationException ex) {
	          out.fine("\nUNIT TEST 5: passed.\n");
	      }
	   // END UNIT TEST 5:

	   // BEGIN UNIT TEST 6:
	      out.fine("UNIT TEST 6: set2DigitYearStart()");
	      try {
	      	  mdf.set2DigitYearStart(null);
	          out.fine("\nUNIT TEST 6: FAILED.\n");
	          errors++;
	      } catch (UnsupportedOperationException ex) {
	          out.fine("\nUNIT TEST 6: passed.\n");
	      }
	   // END UNIT TEST 6:

	   // BEGIN UNIT TEST 7:
	      out.fine("UNIT TEST 7: setDateFormatSymbols()");
	      try {
	      	  mdf.setDateFormatSymbols(null);
	          out.fine("\nUNIT TEST 7: FAILED.\n");
	          errors++;
	      } catch (UnsupportedOperationException ex) {
	          out.fine("\nUNIT TEST 7: passed.\n");
	      }
	   // END UNIT TEST 7:

	      checkStatus();
	} catch (Exception e) {
	      handlException(e);
	}
	return status;
    }
}
