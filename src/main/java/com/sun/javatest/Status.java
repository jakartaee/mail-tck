/*
 * Copyright (c) 2021 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javatest;

public class Status {
    public static final int PASSED = 0;
    public static final int FAILED = 1;
    public static final int ERROR = 2;
    public static final int NOT_RUN = 3;
    public static final int NUM_STATES = 4;
    private final int type;
    private final String reason;
    public static final String EXIT_PREFIX = "STATUS:";
    private static String[] texts = new String[]{"Passed.", "Failed.", "Error.", "Not run."};
    public static final int[] exitCodes = new int[]{95, 97, 98, 99};
    private static final String ENC_PREFFIX = "<EncodeD>";
    private static final String ENC_SUFFFIX = "</EncodeD>";
    private static final String ENC_SEPARATOR = " ";

    public static Status passed(String reason) {
        return new Status(0, reason);
    }

    public static Status failed(String reason) {
        return new Status(1, reason);
    }

    public static Status error(String reason) {
        return new Status(2, reason);
    }

    public static Status notApplicable(String reason) {
        return new Status(1, "Not Applicable: " + reason);
    }

    static Status notRun(String reason) {
        return new Status(3, reason);
    }

    public boolean isPassed() {
        return this.type == 0;
    }

    public boolean isFailed() {
        return this.type == 1;
    }

    public boolean isError() {
        return this.type == 2;
    }

    public boolean isNotRun() {
        return this.type == 3;
    }

    public int getType() {
        return this.type;
    }

    public String getReason() {
        return this.reason;
    }

    public Status augment(String aux) {
        if (aux == null || aux.length() == 0) {
            return this;
        }
        return new Status(this.type, this.reason + " [" + aux + "]");
    }

    public Status augment(Status aux) {
        return aux == null ? this : this.augment(aux.reason);
    }

    public static Status parse(String s) {
        try {
            return new Status(Status.decode(s));
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    private Status(String s) {
        for (int t = 0; t < texts.length; ++t) {
            if (!s.startsWith(texts[t])) continue;
            int l = texts[t].length();
            String r = l < s.length() ? (s.charAt(l) == ' ' ? s.substring(l + 1) : s.substring(l)) : "";
            this.type = t;
            this.reason = Status.normalize(r);
            return;
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        if (this.reason == null || this.reason.length() == 0) {
            return texts[this.type];
        }
        return texts[this.type] + ENC_SEPARATOR + this.reason;
    }

    public static String typeToString(int typeNum) {
        if (typeNum < 4) {
            return texts[typeNum];
        }
        return null;
    }

    public void exit() {
        if (System.err != null) {
            System.err.print(EXIT_PREFIX);
            System.err.print(texts[this.type]);
            System.err.println(Status.encode(this.reason));
            System.err.flush();
        }
        System.exit(exitCodes[this.type]);
    }

    public Status(int type, String reason) throws IllegalArgumentException {
        if (type < 0 || type >= 4) {
            throw new IllegalArgumentException(String.valueOf(type));
        }
        this.type = type;
        this.reason = Status.normalize(reason);
    }

    private static String normalize(String msg) {
        boolean ok = true;
        boolean prevIsWhite = false;
        for (int i = 0; ok && i < msg.length(); ++i) {
            char ch = msg.charAt(i);
            if (Character.isWhitespace(ch)) {
                if (prevIsWhite || ch != ' ' || i == 0) {
                    ok = false;
                    break;
                }
                prevIsWhite = true;
                continue;
            }
            prevIsWhite = false;
        }
        if (prevIsWhite) {
            ok = false;
        }
        if (ok) {
            return msg;
        }
        StringBuffer sb = new StringBuffer();
        boolean needWhite = false;
        for (int i = 0; i < msg.length(); ++i) {
            char ch = msg.charAt(i);
            if (Character.isWhitespace(ch)) {
                if (sb.length() <= 0) continue;
                needWhite = true;
                continue;
            }
            if (needWhite) {
                sb.append(' ');
            }
            sb.append(ch);
            needWhite = false;
        }
        return sb.toString();
    }

    private static final boolean isPrintable(char c) {
        return ' ' <= c && c < '';
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        boolean isAscii = true;
        for (int i = 0; i < str.length(); ++i) {
            if (Status.isPrintable(str.charAt(i))) continue;
            isAscii = false;
            break;
        }
        if (isAscii) {
            return str;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(ENC_PREFFIX);
        for (int i = 0; i < str.length(); ++i) {
            sb.append(Status.encodeChar(str.charAt(i)));
            sb.append(ENC_SEPARATOR);
        }
        sb.append(ENC_SUFFFIX);
        return sb.toString();
    }

    public static String decode(String str) {
        if (str == null) {
            return null;
        }
        int ind = str.indexOf(ENC_PREFFIX);
        if (ind < 0 || !str.endsWith(ENC_SUFFFIX)) {
            return str;
        }
        String encoded = str.substring(ind + ENC_PREFFIX.length(), str.length() - ENC_SUFFFIX.length());
        StringBuffer sb = new StringBuffer();
        sb.append(str.substring(0, ind));
        int begin = 0;
        int end = encoded.indexOf(ENC_SEPARATOR);
        while (end >= 0) {
            sb.append(Status.decodeChar(encoded.substring(begin, end)));
            begin = end + ENC_SEPARATOR.length();
            end = encoded.indexOf(ENC_SEPARATOR, begin);
        }
        sb.append(encoded.substring(begin));
        return sb.toString();
    }

    private static final String encodeChar(char c) {
        return Integer.toString(c, 16);
    }

    private static final char decodeChar(String s) {
        return (char)Integer.parseInt(s, 16);
    }
}