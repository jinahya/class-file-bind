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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class Code extends AttributeInfo {

    // -------------------------------------------------------------------------
    @Override
    void readInfo(final DataInput in) throws IOException {
        setMaxStack(in.readUnsignedShort());
        setMaxLocals(in.readUnsignedShort());
        final int codeLength = in.readInt();
        final byte[] code = new byte[codeLength];
        in.readFully(code);
        setCode(code);
        final int exceptionTableLength = in.readUnsignedShort();
        for (int i = 0; i < exceptionTableLength; i++) {
            final ExceptionTable exceptionTable = new ExceptionTable();
            exceptionTable.read(in);
            getExceptionTables().add(exceptionTable);
        }
        final int attributesCount = in.readUnsignedShort();
        for (int i = 0; i < attributesCount; i++) {
            final AttributeInfo attribute = null;
//                    = AttributeInfo.readInstance(in, classFile);
            getAttributes().add(attribute);
        }
    }

    @Override
    void writeInfo(final DataOutput out) throws IOException {
        out.writeShort(getMaxStack());
        out.writeShort(getMaxLocals());
        final byte[] code = getCode();
        out.writeInt(code.length);
        out.write(code);
        final List<ExceptionTable> exceptionTables = getExceptionTables();
        out.writeShort(exceptionTables.size());
        for (final ExceptionTable exceptionTable : exceptionTables) {
            exceptionTable.write(out);
        }
        final List<AttributeInfo> attributes = getAttributes();
        out.writeShort(attributes.size());
        for (final AttributeInfo attribute : attributes) {
            attribute.writeInfo(out);
        }
    }

    // -------------------------------------------------------------------------
    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(final int maxStack) {
        this.maxStack = maxStack;
    }

    // -------------------------------------------------------------------------
    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(final int maxLocals) {
        this.maxLocals = maxLocals;
    }

    // -------------------------------------------------------------------------
    public byte[] getCode() {
        return code;
    }

    public void setCode(final byte[] code) {
        this.code = code;
    }

    // -------------------------------------------------------------------------
    public List<ExceptionTable> getExceptionTables() {
        if (exceptionTable == null) {
            exceptionTable = new ArrayList<ExceptionTable>();
        }
        return exceptionTable;
    }

    // -------------------------------------------------------------------------
    public List<AttributeInfo> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<AttributeInfo>();
        }
        return attributes;
    }

    // -------------------------------------------------------------------------
    private int maxStack;

    private int maxLocals;

    private byte[] code;

    @OneToMany
    private List<ExceptionTable> exceptionTable;

    private List<AttributeInfo> attributes;
}
