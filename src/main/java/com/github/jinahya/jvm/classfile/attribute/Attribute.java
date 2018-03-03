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
package com.github.jinahya.jvm.classfile.attribute;

import com.github.jinahya.jvm.classfile.ClassFile;
import com.github.jinahya.jvm.classfile.constant.ConstantUtf8Info;
import com.github.jinahya.jvm.classfile.constant.CpInfo;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class Attribute {

    public static Attribute readInstance(final DataInput in,
                                         final ClassFile classFile)
            throws IOException {
        if (in == null) {
            throw new NullPointerException("in is null");
        }
        if (classFile == null) {
            throw new NullPointerException("classFile is null");
        }
        final int attributeNameIndex = in.readUnsignedShort();
        final CpInfo constantPoolEntry
                = classFile.constantPoolEntry(attributeNameIndex);
        if (!(constantPoolEntry instanceof ConstantUtf8Info)) {
            throw new RuntimeException(
                    "constant_pool entry at attribute_name_index("
                    + attributeNameIndex + ") is not a CONSTANT_Utf8_info");
        }
        final String attributeName
                = ((ConstantUtf8Info) constantPoolEntry).getBytesAsString();
        final Class<? extends Attribute> attributeClass;
        try {
            attributeClass = Class.forName(
                    Attribute.class.getPackage().getName() + "."
                    + attributeName)
                    .asSubclass(Attribute.class);
        } catch (final ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }
        final Attribute attributeInstance;
        try {
            attributeInstance
                    = attributeClass.getDeclaredConstructor().newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
        return attributeInstance;
    }

    // -------------------------------------------------------------------------
    abstract void readInfo(DataInput in) throws IOException;

    abstract void writeInfo(DataOutput out) throws IOException;

    // --------------------------------------------------------------- nameIndex
    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    // -------------------------------------------------------------------- info
    public byte[] getInfo() {
        return info;
    }

    public void setInfo(byte[] info) {
        this.info = info;
    }

    // --------------------------------------------------------------- classFile
    public ClassFile getClassFile() {
        return classFile;
    }

    public void setClassFile(final ClassFile classFile) {
        this.classFile = classFile;
    }

    // -------------------------------------------------------------------------
    private int nameIndex;

    private byte[] info;

    // -------------------------------------------------------------------------
    ClassFile classFile;
}
