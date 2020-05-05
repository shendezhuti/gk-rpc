package com.smgeek.gkrpc;

import lombok.Data;

/**表示PRC的返回
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-23 23:28
 */
@Data
public class Response {
    /**
     * 服务返回编码，0-成功，非0失败
     */
    private int code;
    /**
     * 具体的错误信息
     */
    private String message="ok";
    /**
     * 返回的数据
     */
    private Object data;


}
