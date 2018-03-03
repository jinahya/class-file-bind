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

import java.io.DataInputStream;
import java.io.IOException;
import static java.lang.invoke.MethodHandles.lookup;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ClassFileTest {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    @Test
    public void test() throws IOException, JAXBException {
        try (DataInputStream in = new DataInputStream(
                getClass().getResourceAsStream(
                        getClass().getSimpleName() + ".class"))) {
            final ClassFile bound = new ClassFile();
            bound.read(in);
            logger.info("bound: {}", bound);
            try {
                final JAXBContext context
                        = JAXBContext.newInstance(ClassFile.class);
                final Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(
                        Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(bound, System.out);
            } catch (final JAXBException jaxbe) {
                jaxbe.printStackTrace(System.err);
                throw jaxbe;
            }
        }
    }

    // -------------------------------------------------------------------------
    private int i = 0;

    private float float_ = .0f;

    private long long_ = 0L;

    private double double_ = .0d;
}
