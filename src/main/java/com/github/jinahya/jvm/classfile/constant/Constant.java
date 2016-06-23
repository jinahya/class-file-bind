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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.EnumMap;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class Constant implements Externalizable {

    @XmlEnum(value = Integer.class)
    public static enum ConstantType {

        @XmlEnumValue("7")
        CLASS(7),
        @XmlEnumValue("9")
        FIELDREF(9),
        @XmlEnumValue("10")
        METHODREF(10),
        @XmlEnumValue("11")
        INTERFACE_METHODREF(11),
        @XmlEnumValue("8")
        STRING(8),
        @XmlEnumValue("3")
        INTEGER(3),
        @XmlEnumValue("4")
        FLOAT(4),
        @XmlEnumValue("5")
        LONG(5),
        @XmlEnumValue("6")
        DOUBLE(6),
        @XmlEnumValue("12")
        NAME_AND_TYPE(12),
        @XmlEnumValue("1")
        UTF8(1),
        @XmlEnumValue("15")
        METHOD_HANDLE(15),
        @XmlEnumValue("16")
        METHOD_TYPE(16),
        @XmlEnumValue("18")
        INVOKE_DYNAMIC(18);

        public static ConstantType valueOf(final int tag) {
            for (final ConstantType value : values()) {
                if (value.tag == tag) {
                    return value;
                }
            }
            throw new IllegalArgumentException("unknown tag: " + tag);
        }

        private ConstantType(final int tag) {
            this.tag = tag;
        }

        public int getTag() {
            return tag;
        }

        private int tag;
    }

    public static final EnumMap<ConstantType, Class<?>> CLASSES;

    static {
        final EnumMap<ConstantType, Class<?>> classes
                = new EnumMap<ConstantType, Class<?>>(ConstantType.class);
        classes.put(ConstantType.CLASS, ClassInfo.class);
        CLASSES = classes;
    }

    protected Constant(final ConstantType tag) {
        super();
        this.tag = tag;
    }

    @Override
    //@todo make final
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        readInfo(in);
    }

    //@todo make package privatea
    protected abstract void readInfo(final ObjectInput in) throws IOException, ClassNotFoundException;

    @Override
    //@todo make final
    public void writeExternal(final ObjectOutput out) throws IOException {
        writeInfo(out);
    }

    //@todo make package private
    protected abstract void writeInfo(final ObjectOutput out) throws IOException;

    public final ConstantType tag;
}
