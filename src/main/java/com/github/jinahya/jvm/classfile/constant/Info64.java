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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import static java.lang.System.arraycopy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @param <T> value type parameter
 */
abstract class Info64<T> extends CpInfo {

    @Override
    public void write(final DataOutput out) throws IOException {
        out.write(highBytes);
        out.write(lowBytes);
    }

    @Override
    public void read(final DataInput in) throws IOException {
        if (highBytes == null) {
            highBytes = new byte[4];
        }
        in.readFully(highBytes);
        if (lowBytes == null) {
            lowBytes = new byte[4];
        }
        in.readFully(lowBytes);
    }

    public byte[] getBytes() {
        final byte[] bytes = new byte[8];
        arraycopy(highBytes, 0, bytes, 0, 4);
        arraycopy(lowBytes, 0, bytes, 4, 4);
        return bytes;
    }

    public void setBytes(final byte[] bytes) {
        if (bytes != null && bytes.length != 8) {
            throw new IllegalArgumentException("bytes.length != 8");
        }
        if (highBytes == null) {
            highBytes = new byte[4];
        }
        arraycopy(bytes, 0, highBytes, 0, 4);
        if (lowBytes == null) {
            lowBytes = new byte[4];
        }
        arraycopy(bytes, 4, lowBytes, 0, 4);
    }

    public abstract T getBytesAsValue();

    public abstract void setBytesAsValue(T bytesAsValue);

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    @NotNull
    @Size(min = 4, max = 4)
    byte[] highBytes;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    @NotNull
    @Size(min = 4, max = 4)
    byte[] lowBytes;
}
