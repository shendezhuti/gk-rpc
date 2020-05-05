package com.smgeek.gkrpc.transport;

/**1.启动、监听端口
 * 2.接受请求
 * 3.关闭监听
 *
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 13:40
 */
public interface TransportServer {

    void init(int port,RequestHandler handler);

    void start();

    void stop();
}
