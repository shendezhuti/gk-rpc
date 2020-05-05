package com.smgeek.gkrpc.client;

import com.smgeek.gkrpc.Peer;
import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.codec.JSONDecoder;
import com.smgeek.gkrpc.codec.JSONEncoder;
import com.smgeek.gkrpc.transport.HttpTransportClient;
import com.smgeek.gkrpc.transport.TransportClient;
import lombok.Data;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 23:29
 */
@Data
public class RpcClientConfig {

    private Class<? extends TransportClient> transportClass
            = HttpTransportClient.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> DecoderClass = JSONDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectCount = 1;

    private List<Peer> servers;

    {
        Peer peer = new Peer("127.0.0.1", 3000);
        servers = new ArrayList<>();
        servers.add(peer);
    }

}
