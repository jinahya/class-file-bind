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
package com.github.jinahya.jvm.classfile;

import com.github.jinahya.jvm.classfile.constant.Constant;
import java.io.DataInput;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ClassFile implements Serializable {

    public static final int MAGIC = 0xCAFEBABE;

    public void read(final DataInput in) throws IOException {
        if ((magic = in.readInt()) != MAGIC) {
            throw new IOException("wrong magic: " + magic);
        }
        minorVersion = in.readUnsignedShort();
        majorVersion = in.readUnsignedShort();
        final int constantPoolCount = in.readUnsignedShort();
    }

    private int magic = MAGIC;

    @XmlElement(required = true)
    private int minorVersion;

    @XmlElement(required = true)
    private int majorVersion;

    private List<Constant> constantPool;

    private int accessFlags;

    private int thisClass;

    private int superClass;

    // interfaces
    // fields
    // methods
    // attributes;
}
