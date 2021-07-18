package com.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * Created by fengtj on 2021/7/18 16:52
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //将通道声明指定的交换机  参数1：交换机名称 参数2：交换机类型（fanout----广播类型）
        channel.exchangeDeclare("register", "fanout");
        //发送消息
        channel.basicPublish("register", "", null, "fanout type message".getBytes());
        //释放资源
        RabbitMQUtils.closeConnectionAndChanel(connection, channel);
    }
}
