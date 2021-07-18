package com.fanout;

import com.rabbitmq.client.*;
import com.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * Created by fengtj on 2021/7/18 17:13
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("register", "fanout");
        //临时队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queueName, "register", "");
        //消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
