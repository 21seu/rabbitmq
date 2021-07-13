package com.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        sendMessage();
    }

    //生产消息
    public static void sendMessage() throws IOException, TimeoutException {
        //创建连接mq的工厂对象
        ConnectionFactory factory = new ConnectionFactory();
        //设置连接rabbitmq的主机
        factory.setHost("47.111.72.203");
        //设置rabbitmq的端口号
        factory.setPort(5672);
        //设置连接哪个虚拟主机
        factory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        factory.setUsername("ems");
        factory.setPassword("123");

        //获取连接对象
        Connection connection = factory.newConnection();
        //获取连接中的通道
        Channel channel = connection.createChannel();
        //通道去绑定对应的消息队列
        //参数1：队列的名称，如果队列不存在则自动创建
        //参数2：用来定义队列的特性是否要持久化 true 持久化队列   false 不持久化
        //参数3：exclusive 是否独占队列 true 独占队列  false 不独占
        //参数4 autoDelete：是否在消费完成后自动删除队列 true 自动删除 false 不自动删除
        //参数5：额外附加参数
        channel.queueDeclare("hello", true, false, false, null);

        //发布消息
        //参数1：交换机名称 参数2：队列名称  参数3：传递消息的额外设置  参数4：消息的具体内容
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());

        //关闭通道
        channel.close();
        //关闭连接
        connection.close();
    }
}
