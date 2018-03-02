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
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @param <T> value type parameter
 */
abstract class Constant64Info<T extends Constant64Info<T>> extends CpInfo {

    // -------------------------------------------------------------------------
    Constant64Info(final int tag) {
        super(tag);
    }

    // -------------------------------------------------------------------------
    @Override
    public void writeInfo(final DataOutput out) throws IOException {
        out.writeInt(getHighBytes());
        out.writeInt(getLowBytes());
    }

    @Override
    public void readInfo(final DataInput in) throws IOException {
        setHighBytes(in.readInt());
        setLowBytes(in.readInt());
    }

    // --------------------------------------------------------------- highBytes
    public int getHighBytes() {
        return highBytes;
    }

    public void setHighBytes(final int highBytes) {
        this.highBytes = highBytes;
    }

    // ---------------------------------------------------------------- lowBytes
    public int getLowBytes() {
        return lowBytes;
    }

    public void setLowBytes(final int lowBytes) {
        this.lowBytes = lowBytes;
    }

    // ------------------------------------------------------------- bytesAsLong
    long getBytesAsLong() {
        return (((getHighBytes() & 0xFFFFFFFFL) << Integer.SIZE)
                | (getLowBytes() & 0xFFFFFFFFL));
    }

    void setBytesAsLong(final long bytesAsLong) {
        setHighBytes((int) ((bytesAsLong >> Integer.SIZE) & 0xFFFFFFFFL));
        setLowBytes((int) (bytesAsLong & 0xFFFFFFFFL));
    }

    // -------------------------------------------------------------------------
    @XmlElement(required = true)
    private int highBytes;

    @XmlElement(required = true)
    private int lowBytes;
}
