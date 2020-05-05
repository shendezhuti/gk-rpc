package com.smgeek.gkrpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;
import java.sql.Ref;

import static org.junit.Assert.*;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-23 23:59
 */
public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    @Test
    public void getPublicMethods() {
        Method[]methods=ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);
        String mname = methods[0].getName();
        assertEquals("b",mname);
    }

    @Test
    public void invoke() {
        Method [] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method b = methods[0];

        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        Object r = ReflectionUtils.invoke(t,b);

        assertEquals("b",r);

    }
}