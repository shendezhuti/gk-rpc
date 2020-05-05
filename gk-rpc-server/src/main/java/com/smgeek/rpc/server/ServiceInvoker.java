package com.smgeek.rpc.server;

import com.smgeek.gkrpc.Request;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;

/**调用具体服务
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 19:06
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }

}
