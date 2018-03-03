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
import javax.persistence.ManyToOne;

/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class ExceptionTable {

    // -------------------------------------------------------------------------
    void read(DataInput in) throws IOException {
        setStartPc(in.readUnsignedShort());
        setEndPc(in.readUnsignedShort());
        setHandlerPc(in.readUnsignedShort());
        setCatchType(in.readUnsignedShort());
    }

    void write(DataOutput out) throws IOException {
        out.writeShort(getStartPc());
        out.writeShort(getEndPc());
        out.writeShort(getHandlerPc());
        out.writeShort(getCatchType());
    }

    // -------------------------------------------------------------------------
    public int getStartPc() {
        return startPc;
    }

    public void setStartPc(int startPc) {
        this.startPc = startPc;
    }

    // -------------------------------------------------------------------------
    public int getEndPc() {
        return endPc;
    }

    public void setEndPc(int endPc) {
        this.endPc = endPc;
    }

    // -------------------------------------------------------------------------
    public int getHandlerPc() {
        return handlerPc;
    }

    public void setHandlerPc(int handlerPc) {
        this.handlerPc = handlerPc;
    }

    // -------------------------------------------------------------------------
    public int getCatchType() {
        return catchType;
    }

    public void setCatchType(int catchType) {
        this.catchType = catchType;
    }

    // -------------------------------------------------------------------------
    public Code getCode() {
        return code;
    }

    public void setCode(final Code code) {
        this.code = code;
    }

    // -------------------------------------------------------------------------
    private int startPc;

    private int endPc;

    private int handlerPc;

    private int catchType;

    // -------------------------------------------------------------------------
    @ManyToOne
    private Code code;
}
