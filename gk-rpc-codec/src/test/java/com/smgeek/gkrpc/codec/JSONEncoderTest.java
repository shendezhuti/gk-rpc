package com.smgeek.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 11:09
 */
public class JSONEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();

        bean.setName("smgeek");
        bean.setAge(18);
        byte[]bytes = encoder.encode(bean);

        assertNotNull(bytes);

    }
}