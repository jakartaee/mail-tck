/*
 * Copyright (c) 2011, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.oracle.ts.lib.harness;

import java.util.*;
import java.io.*;
import com.sun.javatest.finder.*;
import com.sun.javatest.*;
import com.sun.javatest.util.BackupPolicy;
import com.sun.interview.Interview;

public class TS extends TestSuite {
    private TestEnvironment env;
    private boolean observerAdded;
    
    public TS(File root, Map tsInfo, ClassLoader cl) throws Fault {
        super(root, tsInfo, cl);
    }


    public void starting(Harness harness) throws Fault {
        if (observerAdded) {
            return;
        }
        
        harness.addObserver(new TSHarnessObserver());
        observerAdded = true;
    }

}


