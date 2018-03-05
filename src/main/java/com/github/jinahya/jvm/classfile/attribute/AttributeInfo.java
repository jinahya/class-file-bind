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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Function;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class AttributeInfo {

//    public static final Function<String, Class<? extends AttributeInfo>> DEFAULT
//            = name -> {
//                try {
//                    return Class.forName(
//                            AttributeInfo.class.getPackage().getName() + "."
//                            + name)
//                            .asSubclass(AttributeInfo.class);
//                } catch (final ClassNotFoundException cnfe) {
//                    return AttributeInfo.class;
//                }
//            };

    // -------------------------------------------------------------------------
    public static AttributeInfo readInstance(
            final DataInput in, final ClassFile classFile) 
//            final Function<String, Class<? extends AttributeInfo>> function)
            throws IOException {
        if (in == null) {
            throw new NullPointerException("in is null");
        }
        if (classFile == null) {
            throw new NullPointerException("classFile is null");
        }
//        if (function == null) {
//            throw new NullPointerException("function is null");
//        }
        final int attributeNameIndex = in.readUnsignedShort();
        final CpInfo constantPoolEntry
                = classFile.constantPoolEntry(attributeNameIndex);
        if (!(constantPoolEntry instanceof ConstantUtf8Info)) {
            throw new RuntimeException(
                    "constant_pool entry at attribute_name_index("
                    + attributeNameIndex
                    + ") is not an instance of CONSTANT_Utf8_info");
        }
        final String name
                = ((ConstantUtf8Info) constantPoolEntry).getBytesAsString();
        final Class<? extends AttributeInfo> klass = null; //function.apply(name);
        final AttributeInfo instance;
        try {
            instance = klass.getDeclaredConstructor().newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
        final int attributeLength = in.readInt();
        final byte[] info = new byte[attributeLength];
        in.readFully(info);
        instance.readInfo(new DataInputStream(new ByteArrayInputStream(info)));
        return instance;
    }

//    public static AttributeInfo readInstance(final DataInput in,
//                                             final ClassFile classFile)
//            throws IOException {
//        return readInstance(
//                in, classFile, name -> {
//                    try {
//                        return Class.forName(
//                                AttributeInfo.class.getPackage().getName() + "."
//                                + name)
//                                .asSubclass(AttributeInfo.class);
//                    } catch (final ClassNotFoundException cnfe) {
//                        throw new RuntimeException(cnfe);
//                    }
//                });
//    }

    private static void writeInstance(final DataOutput dataOutput,
                                      final AttributeInfo attributeInfo,
                                      final ByteArrayOutputStream baos,
                                      final DataOutputStream infoOut)
            throws IOException {
        dataOutput.writeShort(attributeInfo.getNameIndex());
        attributeInfo.writeInfo(infoOut);
        infoOut.flush();
        final byte[] info = baos.toByteArray();
        dataOutput.writeInt(info.length);
        dataOutput.write(info);
    }

    private static void writeInstance(final DataOutput dataOutput,
                                      final AttributeInfo attributeInfo,
                                      final ByteArrayOutputStream baos)
            throws IOException {
        final DataOutputStream dos = new DataOutputStream(baos);
        try {
            writeInstance(dataOutput, attributeInfo, baos, dos);
        } finally {
            dos.close();
        }
    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    void readInfo(final DataInput in) throws IOException {
    }

    void writeInfo(final DataOutput out) throws IOException {
    }

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

    public void setInfo(final byte[] info) {
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
    @XmlElement(required = true)
    private int nameIndex;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    private byte[] info;

    // -------------------------------------------------------------------------
    @NotNull
    ClassFile classFile;
}
