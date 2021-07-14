package com.work;

import com.rabbitmq.client.*;
import com.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * Created by fengtj on 2021/7/14 22:29
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException{

        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("work",true,false,false,null);

        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1："+new String(body));
            }
        });

    }
}
