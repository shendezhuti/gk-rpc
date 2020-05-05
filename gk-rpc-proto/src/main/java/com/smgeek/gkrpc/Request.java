package com.smgeek.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**表示RPC的一个请求
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-23 23:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private ServiceDescriptor service;
    private Object []parameters;


}
