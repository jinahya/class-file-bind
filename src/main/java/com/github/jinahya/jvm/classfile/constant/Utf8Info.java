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

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class Utf8Info extends CpInfo {

    @Override
    public void write(final DataOutput out) throws IOException {
        out.writeShort(bytes.length);
        out.write(bytes);
    }

    @Override
    public void read(final DataInput in) throws IOException {
        bytes = new byte[in.readUnsignedShort()];
        in.readFully(bytes);
    }

    @XmlAttribute
    public String getBytesAsString() {
        return bytes == null
               ? null : new String(bytes, Charset.forName("UTF-8"));
    }

    public void setBytesAsString(final String bytesAsString) {
        bytes = bytesAsString == null
                ? null : bytesAsString.getBytes(Charset.forName("UTF-8"));
    }

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    private byte[] bytes;
}
