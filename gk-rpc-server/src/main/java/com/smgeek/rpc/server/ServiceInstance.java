package com.smgeek.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**表示一个具体服务
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 14:33
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
