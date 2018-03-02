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
public enum CpInfoTag {

    @XmlEnumValue(value = "7")
    CONSTANT_Class(7, ConstantClassInfo.class),
    @XmlEnumValue(value = "9")
    CONSTANT_Fieldref(9, ConstantFieldrefInfo.class),
    @XmlEnumValue(value = "10")
    CONSTANT_Methodref(10, ConstantMethodrefInfo.class),
    @XmlEnumValue(value = "11")
    CONSTANT_InterfaceMethodref(11, ConstantInterfaceMethodrefInfo.class),
    @XmlEnumValue(value = "8")
    CONSTANT_String(8, ConstantString.class),
    @XmlEnumValue(value = "3")
    CONSTANT_Integer(3, ConstantInteger.class),
    @XmlEnumValue(value = "4")
    CONSTANT_Float(4, ConstantFloat.class),
    @XmlEnumValue(value = "5")
    CONSTANT_Long(5, ConstantLong.class),
    @XmlEnumValue(value = "6")
    CONSTANT_Double(6, ConstantDouble.class),
    @XmlEnumValue(value = "12")
    CONSTANT_NameAndType(12, ConstantNameAndType.class),
    @XmlEnumValue(value = "1")
    CONSTANT_Utf8(1, ConstantUtf8.class),
    @XmlEnumValue(value = "15")
    CONSTANT_MethodHandle(15, ConstantMethodHandle.class),
    @XmlEnumValue(value = "16")
    CONSTANT_MethodType(16, ConstantMethodType.class),
    @XmlEnumValue(value = "18")
    CONSTANT_InvokeDynamic(18, ConstantInvokeDynamic.class),
    @XmlEnumValue(value = "19")
    CONSTANT_Module(19, ConstantModule.class),
    @XmlEnumValue(value = "20")
    CONSTANT_Package(20, ConstantPackage.class),;

    // -------------------------------------------------------------------------
    public static CpInfoTag valueOf(final int value) {
        for (final CpInfoTag v : values()) {
            if (v.value == value) {
                return v;
            }
        }
        throw new IllegalArgumentException("unknown value: " + value);
    }

    public static CpInfoTag valueOf(final Class<? extends CpInfo> klass) {
        for (final CpInfoTag v : values()) {
            if (v.klass == klass) {
                return v;
            }
        }
        throw new IllegalArgumentException("unknown klass: " + klass);
    }

    // -------------------------------------------------------------------------
    private CpInfoTag(final int value, final Class<? extends CpInfo> klass) {
        this.value = value;
        this.klass = klass;
    }

    // -------------------------------------------------------------------------
    public int getValue() {
        return value;
    }

    public Class<? extends CpInfo> getInfoClass() {
        return klass;
    }

    // -------------------------------------------------------------------------
    private int value;

    private Class<? extends CpInfo> klass;
}
