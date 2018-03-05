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
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class StackMapFrame {

    // -------------------------------------------------------------------------
    public static class SameFrame extends StackMapFrame {

        public static final int MIN_FRAME_TYPE = 0;

        public static final int MAX_FRAME_TYPE = 63;
    }

    // -------------------------------------------------------------------------
    public static class SameLocals1StackItemFrame extends StackMapFrame {

        public static final int MIN_FRAME_TYPE = 64;

        public static final int MAX_FRAME_TYPE = 127;

        // ---------------------------------------------------------------------
        @Override
        void read(final DataInput in) throws IOException {
            super.read(in);
            setStack(VerificationType.readInstance(in));
        }

        // ---------------------------------------------------------------------
        @Override
        void write(DataOutput out) throws IOException {
            super.write(out);
            VerificationType.writeInstance(out, getStack());
        }

        // --------------------------------------------------------------- stack
        public VerificationType getStack() {
            return stack;
        }

        public void setStack(final VerificationType stack) {
            this.stack = stack;
        }

        // ---------------------------------------------------------------------
        @XmlElement(required = true)
        @NotNull
        private VerificationType stack;
    }

    public static class SameLocals1StackItemFrameExtended
            extends StackMapFrame {

        public static final int MIN_FRAME_TYPE = 247;

        public static final int MAX_FRAME_TYPE = 247;

        // ---------------------------------------------------------------------
        @Override
        void read(final DataInput in) throws IOException {
            super.read(in);
            setOffsetDelta(in.readUnsignedShort());
            setStack(VerificationType.readInstance(in));
        }

        // ---------------------------------------------------------------------
        @Override
        void write(DataOutput out) throws IOException {
            super.write(out);
            out.writeShort(getOffsetDelta());
            VerificationType.writeInstance(out, getStack());
        }

        // --------------------------------------------------------- offsetDelta
        public int getOffsetDelta() {
            return offsetDelta;
        }

        public void setOffsetDelta(final int offsetDelta) {
            this.offsetDelta = offsetDelta;
        }

        // --------------------------------------------------------------- stack
        public VerificationType getStack() {
            return stack;
        }

        public void setStack(final VerificationType stack) {
            this.stack = stack;
        }

        // ---------------------------------------------------------------------
        @XmlElement(required = true)
        @Min(0)
        @Max(65535)
        private int offsetDelta;

        @XmlElement(required = true)
        @NotNull
        private VerificationType stack;
    }

    public static class ChopFrame extends StackMapFrame {

        public static final int MIN_FRAME_TYPE = 248;

        public static final int MAX_FRAME_TYPE = 250;

        // ---------------------------------------------------------------------
        @Override
        void read(final DataInput in) throws IOException {
            super.read(in);
            setOffsetDelta(in.readUnsignedShort());
        }

        // ---------------------------------------------------------------------
        @Override
        void write(DataOutput out) throws IOException {
            super.write(out);
            out.writeShort(getOffsetDelta());
        }

        // --------------------------------------------------------- offsetDelta
        public int getOffsetDelta() {
            return offsetDelta;
        }

        public void setOffsetDelta(final int offsetDelta) {
            this.offsetDelta = offsetDelta;
        }

        // ---------------------------------------------------------------------
        @XmlElement(required = true)
        @Min(0)
        @Max(65535)
        private int offsetDelta;
    }

    public static class SameFrameExtended extends StackMapFrame {

        public static final int MIN_FRAME_TYPE = 248;

        public static final int MAX_FRAME_TYPE = 250;

        // ---------------------------------------------------------------------
        @Override
        void read(final DataInput in) throws IOException {
            super.read(in);
            setOffsetDelta(in.readUnsignedShort());
        }

        // ---------------------------------------------------------------------
        @Override
        void write(DataOutput out) throws IOException {
            super.write(out);
            out.writeShort(getOffsetDelta());
        }

        // --------------------------------------------------------- offsetDelta
        public int getOffsetDelta() {
            return offsetDelta;
        }

        public void setOffsetDelta(final int offsetDelta) {
            this.offsetDelta = offsetDelta;
        }

        // ---------------------------------------------------------------------
        @XmlElement(required = true)
        @Min(0)
        @Max(65535)
        private int offsetDelta;
    }

    public static class AppendFrame extends StackMapFrame {

        public static final int MIN_FRAME_TYPE = 252;

        public static final int MAX_FRAME_TYPE = 254;

        // ---------------------------------------------------------------------
        @Override
        void read(final DataInput in) throws IOException {
            super.read(in);
            setOffsetDelta(in.readUnsignedShort());
            for (int i = 0; i < getFrameType() - 251; i++) {
                getLocals().add(VerificationType.readInstance(in));
            }
        }

        // ---------------------------------------------------------------------
        @Override
        void write(DataOutput out) throws IOException {
            super.write(out);
            out.writeShort(getOffsetDelta());
            for (int i = 0; i < getFrameType() - 251; i++) {
                VerificationType.writeInstance(out, getLocals().get(i));
            }
        }

        // ---------------------------------------------------------------------
        @AssertTrue
        private boolean isLocalsSizeEqualsToFrameTypeMinus251() {
            return getLocals().size() == getFrameType() - 251;
        }

        // --------------------------------------------------------- offsetDelta
        public int getOffsetDelta() {
            return offsetDelta;
        }

        public void setOffsetDelta(final int offsetDelta) {
            this.offsetDelta = offsetDelta;
        }

        // -------------------------------------------------------------- locals
        public List<VerificationType> getLocals() {
            if (locals == null) {
                locals = new ArrayList<VerificationType>();
            }
            return locals;
        }

        // ---------------------------------------------------------------------
        @XmlElement(required = true)
        @Min(0)
        @Max(65535)
        private int offsetDelta;

        @XmlElement(required = true)
        @NotNull
        private List<VerificationType> locals;
    }

    public static class FullFrame extends StackMapFrame {

        public static final int MIN_FRAME_TYPE = 255;

        public static final int MAX_FRAME_TYPE = 255;

        // ---------------------------------------------------------------------
        @Override
        void read(final DataInput in) throws IOException {
            super.read(in);
            setOffsetDelta(in.readUnsignedShort());
            final int numberOfLocals = in.readUnsignedShort();
            final List<VerificationType> locals_ = getLocals();
            for (int i = 0; i < numberOfLocals; i++) {
                locals_.add(VerificationType.readInstance(in));
            }
            final int numberOfStackItems = in.readUnsignedShort();
            final List<VerificationType> stack_ = getStack();
            for (int i = 0; i < numberOfStackItems; i++) {
                stack_.add(VerificationType.readInstance(in));
            }
        }

        // ---------------------------------------------------------------------
        @Override
        void write(DataOutput out) throws IOException {
            super.write(out);
            out.writeShort(getOffsetDelta());
            final List<VerificationType> locals_ = getLocals();
            out.writeShort(locals_.size());
            for (final VerificationType local : locals_) {
                VerificationType.writeInstance(out, local);
            }
            final List<VerificationType> stack_ = getStack();
            out.writeShort(stack_.size());
            for (final VerificationType stackItem : stack_) {
                VerificationType.writeInstance(out, stackItem);
            }
        }

        // --------------------------------------------------------- offsetDelta
        public int getOffsetDelta() {
            return offsetDelta;
        }

        public void setOffsetDelta(final int offsetDelta) {
            this.offsetDelta = offsetDelta;
        }

        // -------------------------------------------------------------- locals
        public List<VerificationType> getLocals() {
            if (locals == null) {
                locals = new ArrayList<VerificationType>();
            }
            return locals;
        }

        // --------------------------------------------------------------- stack
        public List<VerificationType> getStack() {
            if (stack == null) {
                stack = new ArrayList<VerificationType>();
            }
            return stack;
        }

        // ---------------------------------------------------------------------
        @XmlElement(required = true)
        @Min(0)
        @Max(65535)
        private int offsetDelta;

        @XmlElement(required = true)
        @NotNull
        private List<VerificationType> locals;

        @XmlElement(required = true)
        @NotNull
        private List<VerificationType> stack;
    }

    // -------------------------------------------------------------------------
    static enum Type {

        SAME_FRAME(SameFrame.MIN_FRAME_TYPE, SameFrame.MAX_FRAME_TYPE,
                   SameFrame.class),
        SAME_LOCALS_1_STACK_ITEM_FRAME(SameLocals1StackItemFrame.MIN_FRAME_TYPE,
                                       SameLocals1StackItemFrame.MAX_FRAME_TYPE,
                                       SameLocals1StackItemFrame.class),
        SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED(
                SameLocals1StackItemFrameExtended.MIN_FRAME_TYPE,
                SameLocals1StackItemFrameExtended.MAX_FRAME_TYPE,
                SameLocals1StackItemFrameExtended.class),
        CHOP_FRAME(ChopFrame.MIN_FRAME_TYPE, ChopFrame.MAX_FRAME_TYPE,
                   ChopFrame.class),
        SAME_FRAME_EXTENDED(SameFrameExtended.MIN_FRAME_TYPE,
                            SameFrameExtended.MAX_FRAME_TYPE,
                            SameFrameExtended.class),
        APPEND_FRAME(AppendFrame.MIN_FRAME_TYPE, AppendFrame.MAX_FRAME_TYPE,
                     AppendFrame.class),
        FULL_FRAME(FullFrame.MIN_FRAME_TYPE, FullFrame.MAX_FRAME_TYPE,
                   FullFrame.class),;

        // ---------------------------------------------------------------------
        static Type valueOf(final int frameType) {
            for (final Type value : values()) {
                if (value.frameTypeMin <= frameType
                    && value.frameTypeMax >= frameType) {
                    return value;
                }
            }
            throw new IllegalArgumentException(
                    "unknown frame type: " + frameType);
        }

        static Type valueOf(final Class<? extends StackMapFrame> frameClass) {
            for (final Type value : values()) {
                if (value.frameClass == frameClass) {
                    return value;
                }
            }
            throw new IllegalArgumentException(
                    "unknown frame class: " + frameClass);
        }

        // ---------------------------------------------------------------------
        private Type(final int frameTypeMin, final int frameTypeMax,
                     final Class<? extends StackMapFrame> typeClass) {
            this.frameTypeMin = frameTypeMin;
            this.frameTypeMax = frameTypeMax;
            this.frameClass = typeClass;
        }

        // ---------------------------------------------------------------------
        private final int frameTypeMin;

        private final int frameTypeMax;

        private final Class<? extends StackMapFrame> frameClass;
    }

    // -------------------------------------------------------------------------
    static StackMapFrame readInstance(final DataInput in) throws IOException {
        final int frameType = in.readUnsignedByte();
        final Class<? extends StackMapFrame> frameClass
                = Type.valueOf(frameType).frameClass;
        final StackMapFrame instance;
        try {
            instance = frameClass.getDeclaredConstructor().newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
        instance.setFrameType(frameType);
        instance.read(in);
        return instance;
    }

    static void writeInstance(final DataOutput out,
                              final StackMapFrame instance)
            throws IOException {
        out.writeByte(instance.getFrameType());
        instance.write(out);
    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    void read(final DataInput in) throws IOException {
    }

    void write(final DataOutput out) throws IOException {
    }

    // --------------------------------------------------------------- frameType
    public int getFrameType() {
        return frameType;
    }

    public void setFrameType(final int frameType) {
        this.frameType = frameType;
    }

    // -------------------------------------------------------------------------
    @XmlElement(required = true)
    private int frameType;
}
