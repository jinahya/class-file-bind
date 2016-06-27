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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@XmlSeeAlso({ClassInfo.class, DoubleInfo.class, FieldRefInfo.class,
             FloatInfo.class, IntegerInfo.class, InterfaceMethodRefInfo.class,
             InvokeDynamicInfo.class, LongInfo.class, MethodHandleInfo.class,
             Utf8Info.class})
public abstract class CpInfo {

//    public static final Map<CpTag, Class<? extends CpInfo>> CLASSES;
//
//    static {
//        final EnumMap<CpTag, Class<? extends CpInfo>> classes
//                = new EnumMap<CpTag, Class<? extends CpInfo>>(CpTag.class);
//        classes.put(CpTag.CONSTANT_Class, ClassInfo.class);
//        classes.put(CpTag.CONSTANT_Fieldref, FieldRefInfo.class);
//        classes.put(CpTag.CONSTANT_Methodref, MethodRefInfo.class);
//        classes.put(CpTag.CONSTANT_InterfaceMethodref, InterfaceMethodRefInfo.class);
//        classes.put(CpTag.CONSTANT_String, StringInfo.class);
//        classes.put(CpTag.CONSTANT_Integer, IntegerInfo.class);
//        classes.put(CpTag.CONSTANT_Float, FloatInfo.class);
//        classes.put(CpTag.CONSTANT_Long, LongInfo.class);
//        classes.put(CpTag.CONSTANT_Double, DoubleInfo.class);
//        classes.put(CpTag.CONSTANT_NameAndType, NameAndTypeInfo.class);
//        classes.put(CpTag.CONSTANT_Utf8, Utf8Info.class);
//        classes.put(CpTag.CONSTANT_MethodHandle, MethodHandleInfo.class);
//        classes.put(CpTag.CONSTANT_MethodType, MethodTypeInfo.class);
//        classes.put(CpTag.CONSTANT_InvokeDynamic, InvokeDynamicInfo.class);
//        CLASSES = Collections.unmodifiableMap(classes);
//    }
    public abstract void read(DataInput in) throws IOException;

    public abstract void write(DataOutput out) throws IOException;
}
