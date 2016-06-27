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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@XmlEnum(value = Integer.class)
public enum CpTag {

    @XmlEnumValue(value = "7")
    CONSTANT_Class(7, ClassInfo.class),
    @XmlEnumValue(value = "9")
    CONSTANT_Fieldref(9, FieldRefInfo.class),
    @XmlEnumValue(value = "10")
    CONSTANT_Methodref(10, MethodRefInfo.class),
    @XmlEnumValue(value = "11")
    CONSTANT_InterfaceMethodref(11, InterfaceMethodRefInfo.class),
    @XmlEnumValue(value = "8")
    CONSTANT_String(8, StringInfo.class),
    @XmlEnumValue(value = "3")
    CONSTANT_Integer(3, IntegerInfo.class),
    @XmlEnumValue(value = "4")
    CONSTANT_Float(4, FloatInfo.class),
    @XmlEnumValue(value = "5")
    CONSTANT_Long(5, LongInfo.class),
    @XmlEnumValue(value = "6")
    CONSTANT_Double(6, DoubleInfo.class),
    @XmlEnumValue(value = "12")
    CONSTANT_NameAndType(12, NameAndTypeInfo.class),
    @XmlEnumValue(value = "1")
    CONSTANT_Utf8(1, Utf8Info.class),
    @XmlEnumValue(value = "15")
    CONSTANT_MethodHandle(15, MethodHandleInfo.class),
    @XmlEnumValue(value = "16")
    CONSTANT_MethodType(16, MethodTypeInfo.class),
    @XmlEnumValue(value = "18")
    CONSTANT_InvokeDynamic(18, InvokeDynamicInfo.class);

    public static CpTag valueOf(final int value) {
        for (final CpTag v : values()) {
            if (v.value == value) {
                return v;
            }
        }
        throw new IllegalArgumentException("unknown value: " + value);
    }

    public static CpTag valueOf(final Class<? extends CpInfo> klass) {
        for (final CpTag v : values()) {
            if (v.infoClass == klass) {
                return v;
            }
        }
        throw new IllegalArgumentException("unknown klass: " + klass);
    }

    private CpTag(final int value, final Class<? extends CpInfo> infoClass) {
        this.value = value;
        this.infoClass = infoClass;
    }

    public int getValue() {
        return value;
    }

    public Class<? extends CpInfo> getInfoClass() {
        return infoClass;
    }

    private int value;

    private Class<? extends CpInfo> infoClass;
}
