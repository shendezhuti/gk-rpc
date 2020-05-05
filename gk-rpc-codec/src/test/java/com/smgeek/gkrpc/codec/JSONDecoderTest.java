package com.smgeek.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 11:18
 */
public class JSONDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();

        bean.setName("smgeek");
        bean.setAge(18);
        byte[]bytes = encoder.encode(bean);
        assertNotNull(bytes);


        Decoder decoder = new JSONDecoder();
        TestBean bean2 = decoder.decode(bytes,TestBean.class);

        assertEquals(bean.getName(),bean2.getName());
        assertEquals(bean.getAge(),bean2.getAge());
    }
}