package com.smgeek.rpc.server;

import com.smgeek.gkrpc.Request;
import com.smgeek.gkrpc.Response;
import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;
import com.smgeek.gkrpc.transport.RequestHandler;
import com.smgeek.gkrpc.transport.TransportServer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 19:07
 */
@Slf4j
@Data
public class RpcServer {
    private RpcServerConfig config;

    private TransportServer net;

    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config){
        this.config = config;
        //net
        this.net =  ReflectionUtils.newInstance(config.getTranportClass());
        this.net.init(config.getPort(),this.handler);

        this.encoder=ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder=ReflectionUtils.newInstance(config.getDecoderClass());
        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();


    }
    public <T>void register(Class<T> interfaceClass, T bean) {
    serviceManager.register(interfaceClass,bean);

    }
    public void start(){
        this.net.start();

    }

    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream input, OutputStream output) {
            Response response = new Response();
            try {
                // 从输入流中获取客户端的数据，反序列化成Request对象
                byte[] inBytes = IOUtils.readFully(input, input.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request: {}", request);
                // 从服务管理对象中获取服务实例，并调用本地方法，将结果放入Response中
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object returnObject = serviceInvoker.invoke(serviceInstance, request);
                response.setData(returnObject);
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("rpc service got error: " + e.getClass().getName() + " : " + e.getMessage());
            } finally {
                try {
                    // 将Response序列化成字节流，返回给客户端
                    byte[] outBytes = encoder.encode(response);
                    output.write(outBytes);

                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };

}
