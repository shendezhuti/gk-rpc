package com.smgeek.rpc.server;

import com.alibaba.fastjson.JSON;
import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.codec.JSONDecoder;
import com.smgeek.gkrpc.codec.JSONEncoder;
import com.smgeek.gkrpc.transport.HttpTransportServer;
import com.smgeek.gkrpc.transport.TransportServer;
import lombok.Data;

/** server配置
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 14:28
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer>  tranportClass = HttpTransportServer.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private int port =3000;
}
