/*
 * Copyright 2016 Jin Kwon &lt;onacit_at_gmail.com&gt;.
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
package com.github.jinahya.jvm.classfile.constant;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class CpUtil {

    // -------------------------------------------------------------------------
    static byte[] u2(final int i) {
        return new byte[]{
            (byte) ((i >> Byte.SIZE) & 0xFF),
            (byte) (i & 0xFF)
        };
    }

    // -------------------------------------------------------------------------
    private CpUtil() {
        super();
    }
}
