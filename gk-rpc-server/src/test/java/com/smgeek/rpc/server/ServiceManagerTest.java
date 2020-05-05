package com.smgeek.rpc.server;

import com.smgeek.gkrpc.Request;
import com.smgeek.gkrpc.ServiceDescriptor;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 18:14
 */
public class ServiceManagerTest {

    ServiceManager sm;

    @Before
    public void init(){
        sm  = new ServiceManager();

        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);
    }
    @Test
    public void register() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class,method);

        Request request = new Request();
        request.setService(sdp);
        ServiceInstance sis = sm.lookup(request);

        assertNotNull(sis);


    }
}