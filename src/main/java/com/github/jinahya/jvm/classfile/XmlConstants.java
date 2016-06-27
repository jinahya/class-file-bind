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

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class XmlConstants {

    public static final String CLASSFILE_BIND_NS_URI
            = "http://github.com/jinahya/jvm/classfile/bind";

    static final String CLASSFILE_BIND_NS_PREFIX = "cf";

    public static final String CLASSFILE_BIND_ATTRIBUTE_NS_URI
            = "http://github.com/jinahya/jvm/classfile/bind/attribute";

    static final String CLASSFILE_BIND_ATTRIBUTE_NS_PREFIX = "cfa";

    public static final String CLASSFILE_BIND_CONSTANT_NS_URI
            = "http://github.com/jinahya/jvm/classfile/bind";

    static final String CLASSFILE_BIND_CONSTANT_NS_PREFIX = "cfc";

    private XmlConstants() {
        super();
    }
}
