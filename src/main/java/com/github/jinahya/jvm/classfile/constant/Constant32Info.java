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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @param <T> value type parameter
 */
@XmlTransient
abstract class Constant32Info<T extends Constant32Info<T>> extends CpInfo {

    public static final int BYTES_BYTES = 4;

    // -------------------------------------------------------------------------
    Constant32Info(final int tag) {
        super(tag);
    }

    // -------------------------------------------------------------------------
    @Override
    public void writeInfo(final DataOutput out) throws IOException {
        out.writeInt(getBytes());
    }

    @Override
    public void readInfo(final DataInput in) throws IOException {
        setBytes(in.readInt());
    }

    // -------------------------------------------------------------------------
    public int getBytes() {
        return bytes;
    }

    public void setBytes(final int bytes) {
        this.bytes = bytes;
    }

    // -------------------------------------------------------------------------
    @XmlElement(required = true)
    private int bytes;
}
