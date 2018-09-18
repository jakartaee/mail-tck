/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
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

package  com.oracle.ts.lib.harness;

import  com.sun.javatest.*;
import  java.io.*;
import  java.net.*;
import  java.util.*;

/**
 * This class is used to capture output from the tests (which goes to the .jtr
 * files) and to also send it to the standard output stream if the test fails.
 *
 * @author	Kyle Grucci
 * @author	Bill Shannon
 */
public class TSHarnessObserver
        implements Harness.Observer, TestResult.Observer {
    private static int iPassedCount;
    private static int iFailedCount;
    private static int iErrorCount;
    private static int iNotRunCount;
    private static int iFinishedTests;
    private static List<String> testResults = new ArrayList<String>();
    private static StringBuilder msg;
    private static PrintStream ps;

    private static final String BSEP =
	"***************************************************************";
    private static final String SEP =
	"---------------------------------------------------------------";

    //------methods from Harness.Observer---------------------------------------

    @Override
    public void startingTestRun(com.sun.javatest.Parameters p) {
        
            System.out.println("Starting tests");
    }

    @Override
    public void stoppingTestRun() {}

    @Override
    public void finishedTestRun(boolean allOK) {}

    @Override
    public synchronized void finishedTesting() {
        if (testResults.size() == 0)
            return;

	System.out.println(BSEP);
        System.out.printf("Completed running %d tests.\n", iFinishedTests);
        System.out.printf("Number of Tests Passed      = %d%n", iPassedCount);
        System.out.printf("Number of Tests Failed      = %d%n", iFailedCount);
        System.out.printf("Number of Tests with Errors = %d%n", iErrorCount);
        System.out.printf("Number of Tests Not Run     = %d%n", iNotRunCount);

	System.out.println(SEP);
	for (String s : testResults)
	    System.out.println(s);

        testResults.clear();
        iFinishedTests = 0;
        iPassedCount = 0;
        iErrorCount = 0;
        iNotRunCount = 0;
        iFailedCount = 0;
    }

    @Override
    public void error(String s) {
        //TestUtil.logHarness(s);
    }

    @Override
    public synchronized void startingTest(TestResult tr) {
	TestDescription td = null;
        try {
            td = tr.getDescription();
        } catch (TestResult.Fault e) {
            e.printStackTrace();
        }
 
        tr.addObserver(this);
	System.out.println(BSEP);
	System.out.printf("Beginning Test:  %s%n",
	    testName(td.getParameter("executeClass"), td.getParameter("id")));
	msg = new StringBuilder();
    }

    @Override
    public synchronized void finishedTest(TestResult tr) {
        TestDescription td = null;
        String clazz = "<unknownClass>";
        String id = null;
        try {
            td = tr.getDescription();
	    clazz = td.getParameter("executeClass");
	    id = td.getParameter("id");
        } catch (TestResult.Fault e) {
            e.printStackTrace();
        }

	boolean passed = false;
        String testNameAndStatus = "";
	switch (tr.getStatus().getType()) {
	case Status.PASSED:
	    testNameAndStatus = "PASSED........" + testName(clazz, id);
	    iPassedCount++;
	    passed = true;
	    break;
	case Status.ERROR:
	    testNameAndStatus = "ERROR........" + testName(clazz, id);
	    iErrorCount++;
	    break;
	case Status.NOT_RUN:
	    testNameAndStatus = "NOT RUN........" + testName(clazz, id);
	    iNotRunCount++;
	    break;
	default:
	    testNameAndStatus = "FAILED........" + testName(clazz, id);
	    iFailedCount++;
	    break;
	}
	testResults.add(testNameAndStatus);
        iFinishedTests++;

	System.out.println(SEP);
        System.out.printf("Finished Test:  %s%n", testNameAndStatus);
	System.out.println(SEP);

	// only print test output for tests that fail
	if (!passed) {
	    System.out.print(msg.toString());
	    System.out.println(SEP);
	}
	System.out.printf("Number of tests completed:  %d " +
	    "(%d pass, %d fail, %d errors)%n",
		iFinishedTests, iPassedCount, iFailedCount, iErrorCount);
    }

    private static String testName(String clazz, String id) {
	return clazz.replace('.', '/') + ".java" +
					    (id == null ? "" : ("#" + id));
    }

    //------methods from TestResult.Observer----------------------------------

    @Override
    public void createdSection(TestResult tr, TestResult.Section section) {}

    @Override
    public void completedSection(TestResult tr, TestResult.Section section) {}

    @Override
    public void createdOutput(TestResult tr, TestResult.Section section,
	    String outputName) {}

    @Override
    public void completedOutput(TestResult tr, TestResult.Section section,
	    String outputName) {}

    @Override
    public void updatedOutput(TestResult tr, TestResult.Section section,
	    String outputName, int start, int end, String text) {
	msg.append(text);
    }

    @Override
    public void updatedProperty(TestResult tr, String name, String value) {}

    @Override
    public void completed(TestResult tr) {}
}
