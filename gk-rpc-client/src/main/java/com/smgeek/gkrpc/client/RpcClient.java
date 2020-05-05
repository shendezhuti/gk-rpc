package com.smgeek.gkrpc.client;

import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 23:51
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());

        this.selector.init(this.config.getServers(), this.config.getConnectCount(), this.config.getTransportClass());
    }

    /**
     * 获取一个指定接口的代理对象， 对此对象的所有方法调用都会转发到{@link RemoteInvoker}
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz}, new RemoteInvoker(clazz, encoder, decoder, selector));
    }
}