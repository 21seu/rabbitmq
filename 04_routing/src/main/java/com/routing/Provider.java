package com.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * Created by fengtj on 2021/7/21 23:41
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();


    }
}
