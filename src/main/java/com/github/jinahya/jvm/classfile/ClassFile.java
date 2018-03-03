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
package com.github.jinahya.jvm.classfile;

import com.github.jinahya.jvm.classfile.constant.CpInfo;
import com.github.jinahya.jvm.classfile.constant.CpInfoTag;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@XmlRootElement
public class ClassFile implements Serializable {

    // -------------------------------------------------------------------------
    public static final int MIN_CONSTANT_POOL_COUNT = 1;

    public static final int MAX_CONSTANT_POOL_COUNT = 6535;

    public static final int MIN_CONSTANT_POOL_INDEX = MIN_CONSTANT_POOL_COUNT;

    // -------------------------------------------------------------------------
    public static final int MAGIC = 0xCAFEBABE;

    // -------------------------------------------------------------------------
    public void read(final DataInput in) throws IOException {
        if ((magic = in.readInt()) != MAGIC) {
            throw new IOException("wrong magic: " + magic);
        }
        minorVersion = in.readUnsignedShort();
        majorVersion = in.readUnsignedShort();
        final int constantPoolCount = in.readUnsignedShort();
        if (constantPool == null) {
            constantPool = new ArrayList<>(constantPoolCount - 1);
        }
        for (int i = 1; i < constantPoolCount; i++) {
            final int tag = in.readUnsignedByte();
            try {
                final CpInfo cpInfo = CpInfoTag.valueOf(tag).getInfoClass()
                        .getDeclaredConstructor().newInstance();
                cpInfo.readInfo(in);
                cpInfo.setClassFile(this);
                constantPool.add(cpInfo);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        accessFlags = in.readUnsignedShort();
        thisClass = in.readUnsignedShort();
        superClass = in.readUnsignedShort();
        final int interfacesCount = in.readUnsignedShort();
        interfaces = new ArrayList<>(interfacesCount);
        final int fieldsCount = in.readUnsignedShort();
    }

    public void write(final DataOutput out) throws IOException {
    }

    public List<CpInfo> getConstantPool() {
        if (constantPool == null) {
            constantPool = new ArrayList<CpInfo>();
        }
        return constantPool;
    }

    public CpInfo constantPoolEntry(final int constantPoolIndex) {
        if (constantPoolIndex <= 0) {
            throw new IllegalArgumentException(
                    "constantPoolIndex(" + constantPoolIndex + ") <= 0");
        }
        if (constantPoolIndex > getConstantPool().size()) {
            throw new IllegalArgumentException(
                    "constantPoolIndex(" + constantPoolIndex
                    + ") >= constant_pool_count("
                    + (getConstantPool().size() + 1) + ")");
        }
        return getConstantPool().get(constantPoolIndex - 1);
    }

    // -------------------------------------------------------------------------
    @XmlElement(required = true)
    private int magic = MAGIC;

    @XmlElement(required = true)
    @Min(0)
    @Max(65535)
    private int minorVersion;

    @XmlElement(required = true)
    @Min(0)
    @Max(65535)
    private int majorVersion;

    @XmlElement
    private List<CpInfo> constantPool;

    @XmlElement(required = true)
    private int accessFlags;

    @XmlElement(required = true)
    private int thisClass;

    @XmlElement(required = true)
    private int superClass;

    @XmlElement(required = true)
    private List<Integer> interfaces;
    // fields
    // methods
    // attributes;
}
