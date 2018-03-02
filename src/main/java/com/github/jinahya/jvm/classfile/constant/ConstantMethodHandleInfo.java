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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstantMethodHandleInfo extends CpInfo {

    public static final int MIN_REFERENCE_KIND = 1;

    public static final int MAX_REFERENCE_KIND = 9;

    // -------------------------------------------------------------------------
    public ConstantMethodHandleInfo() {
        super(CpInfoTag.CONSTANT_MethodHandle.getTagValue());
    }

    // -------------------------------------------------------------------------
    @Override
    public void writeInfo(final DataOutput out) throws IOException {
        out.writeByte(referenceKind);
        out.writeShort(referenceIndex);
    }

    @Override
    public void readInfo(final DataInput in) throws IOException {
        referenceKind = in.readUnsignedByte();
        referenceIndex = in.readUnsignedShort();
    }

    // -------------------------------------------------------------------------
    @XmlElement(required = true)
    @Min(MIN_REFERENCE_KIND)
    @Max(MAX_REFERENCE_KIND)
    private int referenceKind;

    @XmlElement(required = true)
    private int referenceIndex;
}
