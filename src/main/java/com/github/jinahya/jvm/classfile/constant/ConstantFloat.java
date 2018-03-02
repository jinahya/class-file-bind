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

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstantFloat extends Info32<Float> {

    static byte[] floatToBytes(final float f, final byte[] bytes) {
        return ConstantInteger.intToBytes(Float.floatToRawIntBits(f), bytes);
    }

    static byte[] floatToBytes(final float f) {
        return floatToBytes(f, new byte[Float.BYTES]);
    }

    static float bytesToFloat(final byte[] bytes) {
        return Float.intBitsToFloat(ConstantInteger.bytesToInt(bytes));
    }

    @Override
    @XmlAttribute
    public Float getBytesAsValue() {
        return bytesToFloat(getBytes());
    }

    @Override
    public void setBytesAsValue(final Float bytesAsValue) {
        setBytes(floatToBytes(bytesAsValue));
    }
}
