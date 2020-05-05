package com.smgeek.gkrpc.client;

import com.smgeek.gkrpc.Request;
import com.smgeek.gkrpc.Response;
import com.smgeek.gkrpc.ServiceDescriptor;
import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.transport.TransportClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**调用远程服务的代理类
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-25 00:19
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encode;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encode, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.decoder = decoder;
        this.encode = encode;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + response);
        }
        return response.getData();
    }

    /**
     * 调用远程服务
     * @param request
     * @return
     */
    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;
        try {
            client = selector.select();
            byte[] outBytes = encode.encode(request);
            InputStream review = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(review, review.available());
            response = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMessage("rpc client got error: " + e.getClass() + " : " + e.getMessage());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return response;
    }
}