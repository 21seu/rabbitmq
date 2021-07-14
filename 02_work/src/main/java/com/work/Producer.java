package com.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * Created by fengtj on 2021/7/13 23:04
 */
public class Producer {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "work", null, (i + "---->hello work queue").getBytes());
        }
        RabbitMQUtils.closeConnectionAndChanel(connection, channel);
    }
}
