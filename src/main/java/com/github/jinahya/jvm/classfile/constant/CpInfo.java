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

import com.github.jinahya.jvm.classfile.ClassFile;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@XmlSeeAlso({ConstantClassInfo.class, ConstantFieldrefInfo.class,
             ConstantMethodrefInfo.class, ConstantInterfaceMethodrefInfo.class,
             ConstantString.class, ConstantInteger.class, ConstantFloat.class,
             ConstantLong.class, ConstantDouble.class,
             ConstantNameAndType.class, ConstantUtf8.class,
             ConstantMethodHandle.class, ConstantMethodType.class,
             ConstantInvokeDynamic.class, ConstantModule.class,
             ConstantPackage.class})
public abstract class CpInfo {

    // -------------------------------------------------------------------------
    static byte[] u2(final int i) {
        return new byte[]{
            (byte) ((i >> Byte.SIZE) & 0xFF),
            (byte) (i & 0xFF)
        };
    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    public abstract void read(DataInput in) throws IOException;

    public abstract void write(DataOutput out) throws IOException;

    // --------------------------------------------------------------------- tag
    public int getTag() {
        return tag;
    }

    public void setTag(final int tag) {
        this.tag = tag;
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
    //@Min(0)
    //@Max(255)
    private int tag;

    private byte[] info;

    // -------------------------------------------------------------------------
    ClassFile classFile;
}
