package com.smgeek.gkrpc.transport;

import com.smgeek.gkrpc.Peer;

import java.io.InputStream;

/**1.创建连接
 * 2.发送数据，并且等待响应
 * 3.关闭连接
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 12:06
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();


}
