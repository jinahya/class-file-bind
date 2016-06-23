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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
abstract class Info64 extends Constant {

    public Info64(final ConstantType type) {
        super(type);
    }

    @Override
    protected void writeInfo(final ObjectOutput out) throws IOException {
        out.writeInt(highBytes);
        out.writeInt(lowBytes);
    }

    @Override
    protected void readInfo(final ObjectInput in)
            throws IOException, ClassNotFoundException {
        highBytes = in.readInt();
        lowBytes = in.readInt();
    }

    @XmlElement(required = true)
    private int highBytes;

    @XmlElement(required = true)
    private int lowBytes;
}
