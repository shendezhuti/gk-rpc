package com.smgeek.gkrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**处理网络请求的handler
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 13:42
 */
public interface RequestHandler {

    void onRequest(InputStream receive, OutputStream toRequest);

}
