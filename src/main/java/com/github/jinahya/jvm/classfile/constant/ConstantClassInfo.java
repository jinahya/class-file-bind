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

import com.github.jinahya.jvm.classfile.ClassFileUtil;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstantClassInfo extends CpInfo {

    // -------------------------------------------------------------------------
    public ConstantClassInfo() {
        super();
    }

    @Override
    public void write(final DataOutput out) throws IOException {
        out.writeShort(nameIndex);
    }

    @Override
    public void read(final DataInput in) throws IOException {
        nameIndex = in.readUnsignedShort();
    }

    // -------------------------------------------------------------------------
    // --------------------------------------------------------------- nameIndex
    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(final int nameIndex) {
        this.nameIndex = nameIndex;
        setInfo(ClassFileUtil.u2(nameIndex));
    }

    // -------------------------------------------------------------------------
    @XmlAttribute(required = true)
    private int nameIndex;
}
