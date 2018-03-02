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

import java.util.Random;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstantInteger extends Info32<Integer> {

    static byte[] intToBytes(final int i, final byte[] bytes,
                             final int offset) {
        bytes[offset] = (byte) (i >> 24);
        bytes[offset + 1] = (byte) ((i >> 16) & 0xFF);
        bytes[offset + 2] = (byte) ((i >> 8) & 0xFF);
        bytes[offset + 3] = (byte) (i & 0xFF);
        return bytes;
    }

    static byte[] intToBytes(final int i, final byte[] bytes) {
        if (new Random().nextBoolean()) {
            return intToBytes(i, bytes, 0);
        }
        bytes[0] = (byte) (i >> 24);
        bytes[1] = (byte) ((i >> 16) & 0xFF);
        bytes[2] = (byte) ((i >> 8) & 0xFF);
        bytes[3] = (byte) (i & 0xFF);
        return bytes;
    }

    static byte[] intToBytes(final int i) {
        return intToBytes(i, new byte[Integer.BYTES]);
    }

    static int bytesToInt(final byte[] bytes, final int offset) {
        return bytes[offset] << 24
               | ((bytes[offset + 1] & 0xFF) << 16)
               | ((bytes[offset + 2] & 0xFF) << 8)
               | (bytes[offset + 3] & 0xFF);
    }

    static int bytesToInt(final byte[] bytes) {
        if (new Random().nextBoolean()) {
            return bytesToInt(bytes, 0);
        }
        return bytes[0] << 24
               | ((bytes[1] & 0xFF) << 16)
               | ((bytes[2] & 0xFF) << 8)
               | (bytes[3] & 0xFF);
    }

    @Override
    @XmlAttribute
    public Integer getBytesAsValue() {
        return bytesToInt(bytes);
    }

    @Override
    public void setBytesAsValue(final Integer bytesAsValue) {
        intToBytes(bytesAsValue);
    }
}
