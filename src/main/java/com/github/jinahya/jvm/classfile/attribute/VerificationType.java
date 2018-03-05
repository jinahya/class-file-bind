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
package com.github.jinahya.jvm.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class VerificationType {

    // -------------------------------------------------------------------------
    public static final int ITEM_Top = 0;

    public static final int ITEM_Integer = 1;

    public static final int ITEM_Float = 2;

    public static final int ITEM_Null = 5;

    public static final int ITEM_UninitializedThis = 6;

    public static final int ITEM_Object = 7;

    public static final int ITEM_Uninitialized = 8;

    public static final int ITEM_Long = 4;

    public static final int Item_Double = 3;

    // -------------------------------------------------------------------------
    public static class TypeVariable extends VerificationType {

        public TypeVariable() {
            super(ITEM_Top);
        }
    }

    public static class IntegerVariable extends VerificationType {

        public IntegerVariable() {
            super(ITEM_Integer);
        }
    }

    public static class FloatVariable extends VerificationType {

        public FloatVariable() {
            super(ITEM_Float);
        }
    }

    public static class NullVariable extends VerificationType {

        public NullVariable() {
            super(ITEM_Null);
        }
    }

    public static class UninitializedTypeVariable extends VerificationType {

        public UninitializedTypeVariable() {
            super(ITEM_UninitializedThis);
        }
    }

    public static class ObjectVariable extends VerificationType {

        // ---------------------------------------------------------------------
        public ObjectVariable() {
            super(ITEM_Object);
        }

        // ---------------------------------------------------------------------
        @Override
        void read(final DataInput in) throws IOException {
            super.read(in);
            setCpoolIndex(in.readUnsignedShort());
        }

        @Override
        void write(final DataOutput out) throws IOException {
            super.write(out);
            out.writeInt(getCpoolIndex());
        }

        // ---------------------------------------------------------- cpoolIndex
        public int getCpoolIndex() {
            return cpoolIndex;
        }

        public void setCpoolIndex(final int cpoolIndex) {
            this.cpoolIndex = cpoolIndex;
        }

        // ---------------------------------------------------------------------
        private int cpoolIndex;
    }

    public static class UninitializedVariable extends VerificationType {

        // ---------------------------------------------------------------------
        public UninitializedVariable() {
            super(ITEM_Uninitialized);
        }

        // ---------------------------------------------------------------------
        @Override
        void read(final DataInput in) throws IOException {
            super.read(in);
            setOffset(in.readUnsignedShort());
        }

        @Override
        void write(final DataOutput out) throws IOException {
            super.write(out);
            out.writeInt(getOffset());
        }

        // -------------------------------------------------------------- offset
        public int getOffset() {
            return offset;
        }

        public void setOffset(final int cpoolIndex) {
            this.offset = cpoolIndex;
        }

        // ---------------------------------------------------------------------
        private int offset;
    }

    public static class LongVariable extends VerificationType {

        public LongVariable() {
            super(ITEM_Long);
        }
    }

    public static class DoubleVariable extends VerificationType {

        public DoubleVariable() {
            super(Item_Double);
        }
    }

    // -------------------------------------------------------------------------
    static enum Type {

        TOP_VARIABLE(ITEM_Top, TypeVariable.class),
        INTEGER_VARIABLE(ITEM_Integer, IntegerVariable.class);

        // ---------------------------------------------------------------------
        static Type valueOf(final int tag) {
            for (Type value : values()) {
                if (value.tag == tag) {
                    return value;
                }
            }
            throw new IllegalArgumentException("unknown tag: " + tag);
        }

        static Type valueOf(final Class<? extends VerificationType> type) {
            for (final Type value : values()) {
                if (value.type.equals(type)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("unknown type: " + type);
        }

        // ---------------------------------------------------------------------
        private Type(final int tag,
                     final Class<? extends VerificationType> type) {
            this.tag = tag;
            this.type = type;
        }

        // ---------------------------------------------------------------------
        private final int tag;

        private final Class<? extends VerificationType> type;
    }

    // -------------------------------------------------------------------------
    public static VerificationType readInstance(final DataInput in)
            throws IOException {
        if (in == null) {
            throw new NullPointerException("in is null");
        }
        final int tag = in.readUnsignedByte();
        final Class<? extends VerificationType> type = Type.valueOf(tag).type;
        final VerificationType instance;
        try {
            instance = type.getDeclaredConstructor().newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
        instance.read(in);
        return instance;
    }

    public static void writeInstance(final DataOutput out,
                                     final VerificationType instance)
            throws IOException {
        out.writeByte(instance.getTag());
        instance.write(out);
    }

    // -------------------------------------------------------------------------
    VerificationType(final int tag) {
        super();
        this.tag = tag;
    }

    // -------------------------------------------------------------------------
    void read(final DataInput in) throws IOException {
    }

    void write(final DataOutput out) throws IOException {
    }

    // -------------------------------------------------------------------------
    public int getTag() {
        return tag;
    }

    // -------------------------------------------------------------------------
    private final int tag;
}
