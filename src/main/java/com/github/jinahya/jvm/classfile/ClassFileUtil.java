/*
 * Copyright 2018 Jin Kwon &lt;onacit at gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.jvm.classfile;

/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public final class ClassFileUtil {

    // -------------------------------------------------------------------------
    public static byte[] u2b(final int u2i) {
        final byte[] u2b = new byte[Short.SIZE];
        for (int i = u2b.length - 1; i >= 0; i--) {
        }
        return new byte[]{
            (byte) ((u2i >> Byte.SIZE) & 0xFF),
            (byte) (u2i & 0xFF)
        };
    }

    public static int u2i(byte[] u2b) {
        if (u2b == null) {
            throw new NullPointerException("b == null");
        }
        if (u2b.length != Short.BYTES) {
            throw new IllegalArgumentException(
                    "b2i.length(" + u2b.length + ") != " + Short.BYTES);
        }
        int u2i = 0x00;
        for (int i = 0; i < Short.BYTES; i++) {
            u2i <<= Byte.SIZE;
            u2i |= (u2b[i] & 0xFF);
        }
        return u2i;
    }

    // -------------------------------------------------------------------------
    public static byte[] u4b(int u4i) {
        final byte[] u4b = new byte[Integer.BYTES];
        for (int i = u4b.length - 1; i >= 0; i--) {
            u4b[i] = (byte) (u4i & 0xFF);
            u4i >>= Byte.SIZE;
        }
        return u4b;
    }

    public static int u4i(final byte[] u4b) {
        if (u4b == null) {
            throw new NullPointerException("b == null");
        }
        if (u4b.length != Integer.BYTES) {
            throw new IllegalArgumentException(
                    "u4b.length(" + u4b.length + ") != " + Integer.BYTES);
        }
        int u4i = 0x00;
        for (int i = 0; i < Integer.BYTES; i++) {
            u4i <<= Byte.SIZE;
            u4i |= (u4b[i] & 0xFF);
        }
        return u4i;
    }
    // -------------------------------------------------------------------------
}
