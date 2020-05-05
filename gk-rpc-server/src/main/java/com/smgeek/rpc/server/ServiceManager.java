package com.smgeek.rpc.server;

import com.smgeek.gkrpc.Request;
import com.smgeek.gkrpc.ServiceDescriptor;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;
import com.sun.deploy.util.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**管理rpc暴露的服务
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 17:41
 */
@Slf4j
public class ServiceManager {

    private Map <ServiceDescriptor,ServiceInstance> services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();

    }

    public <T>void register(Class<T> interfaceClass, T bean){
       Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);

       for(Method method:methods){
           ServiceInstance sis = new ServiceInstance(bean,method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass,method);
            services.put(sdp,sis);
            log.info("register service: {} {} ",sdp.getClazz(), sdp.getMethod());
       }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
