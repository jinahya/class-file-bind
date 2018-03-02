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
package com.github.jinahya.jvm.classfile.constant;

import java.util.HashSet;
import java.util.Set;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class CpInfoTagTest {

    // -------------------------------------------------------------------------    
    @Test
    static void assertUniqueValue() {
        final Set<Integer> values = new HashSet<>();
        for (final CpInfoTag value : CpInfoTag.values()) {
            assertTrue(values.add(value.getTagValue()));
        }
    }

    @Test
    static void assertUniqueKlass() {
        final Set<Class<?>> values = new HashSet<>();
        for (final CpInfoTag value : CpInfoTag.values()) {
            assertTrue(values.add(value.getInfoClass()));
        }
    }
}
