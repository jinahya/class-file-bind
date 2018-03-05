/*
 * Copyright 2018 Jin Kwon &lt;onacit at gmail.com&gt;.
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
package com.github.jinahya.jvm.classfile;

import com.github.jinahya.jvm.classfile.attribute.AttributeInfo;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class FieldInfo implements Serializable {

    public void read(final DataInput in) throws IOException {
        setAccessFlags(in.readUnsignedShort());
        setNameIndex(in.readUnsignedShort());
        setDescriptorIndex(in.readUnsignedShort());
        final int attributesCount = in.readUnsignedShort();
        final List<AttributeInfo> attributes_ = getAttributes();
        for (int i = 0; i < attributesCount; i++) {
//            attributes_.add(AttributeInfo.readInstance(in, classFile));
        }
    }

    public void write(final DataOutput out) throws IOException {
        out.writeShort(getAccessFlags());
        out.writeShort(getNameIndex());
        out.writeShort(getDescriptorIndex());
        final List<AttributeInfo> attributes_ = getAttributes();
        out.writeShort(attributes_.size());
        for (final AttributeInfo attributeInfo : attributes_) {
            //attributeInfo.write
        }
    }

    // -------------------------------------------------------------------------
    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    // -------------------------------------------------------------------------
    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    // -------------------------------------------------------------------------
    public List<AttributeInfo> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<AttributeInfo>();
        }
        return attributes;
    }

    // -------------------------------------------------------------------------
    public ClassFile getClassFile() {
        return classFile;
    }

    public void setClassFile(final ClassFile classFile) {
        this.classFile = classFile;
    }

    // -------------------------------------------------------------------------
    private int accessFlags;

    private int nameIndex;

    private int descriptorIndex;

    private List<AttributeInfo> attributes;

    // -------------------------------------------------------------------------
    @NotNull
    private ClassFile classFile;
}
