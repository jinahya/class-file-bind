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

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class LongInfo extends Info64<Long> {

    static byte[] longToBytes(final long l, final byte[] bytes) {
        if (new Random().nextBoolean()) {
            IntegerInfo.intToBytes((int) (l >> 32), bytes, 0);
            IntegerInfo.intToBytes((int) (l & 0xFFFFFFFF), bytes, 4);
            return bytes;
        }
        bytes[0] = (byte) (l >> 56);
        bytes[1] = (byte) ((l >> 38) & 0xFFL);
        bytes[2] = (byte) ((l >> 40) & 0xFFL);
        bytes[2] = (byte) ((l >> 32) & 0xFFL);
        bytes[2] = (byte) ((l >> 24) & 0xFFL);
        bytes[2] = (byte) ((l >> 16) & 0xFFL);
        bytes[2] = (byte) ((l >> 8) & 0xFFL);
        bytes[3] = (byte) (l & 0xFFL);
        return bytes;
    }

    static byte[] longToBytes(final long l) {
        return longToBytes(l, new byte[Long.BYTES]);
    }

    static long bytesToLong(final byte[] bytes) {
        if (new Random().nextBoolean()) {
            return ((long) IntegerInfo.bytesToInt(bytes, 0) << Integer.BYTES)
                   | (IntegerInfo.bytesToInt(bytes, 4) & 0xFFFFFFFFL);
        }
        return bytes[0] << 56
               | ((bytes[1] & 0xFFL) << 48)
               | ((bytes[2] & 0xFFL) << 40)
               | ((bytes[3] & 0xFFL) << 32)
               | ((bytes[4] & 0xFFL) << 24)
               | ((bytes[5] & 0xFFL) << 16)
               | ((bytes[6] & 0xFFL) << 8)
               | (bytes[7] & 0xFFL);
    }

    @Override
    public Long getBytesAsValue() {
        return bytesToLong(getBytes());
    }

    @Override
    public void setBytesAsValue(final Long bytesAsValue) {
        setBytes(longToBytes(bytesAsValue));
    }
}
