package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by baimugudu on 2019/4/8
 */

@Configuration
@Slf4j
public class MyConfig {


    @Bean
    public TransportClient client() throws UnknownHostException {
        log.info("=================");
        InetSocketTransportAddress node = new InetSocketTransportAddress(
                InetAddress.getByName("58.87.120.34"),
                9300
        );

        Settings settings = Settings.builder()
                .put("cluster.name","aisile")
                .put("node.name","master")
                .build();


        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;


    }
}
