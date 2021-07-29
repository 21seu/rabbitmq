# rabbitmq

## 1. MQ引言

### 1.1 什么是MQ

`MQ`（Message quene）;翻译为`消息队列`，通过典型的`生产者`和`消费者`模型，生产者不断向消息队列中生产消息，消费者不断地从队列中获取消息。因为消息的生产和消费都是异步的，而且只关心消息的发送和接收，没有业务逻辑的侵入，轻松的实现系统间的解耦。别名为`消息中间件`。通过利用高效可靠的消息传递机制进行平台无关的数据交流，并基于数据通信来进行分布式系统的集成。



### 1.2 MQ有哪些

当今市面上有很多主流的消息中间件，如老牌的`ActiveMQ`、`RabbitMQ`，炙手可热的`Kafka`，阿里巴巴自主开发`RocketMQ`等。

### 1.3 不同MQ的特点

| **ActiveMQ** | ActiveMQ 是Apache出品，最流行的，能力强劲的开源消息总线。它是一个完全支持JMS规范的的消息中间件。丰富的API,多种集群架构模式让ActiveMQ在业界成为老牌的消息中间件,在中小型企业颇受欢迎! |
| ------------ | ------------------------------------------------------------ |
| **Kafka**    | Kafka是LinkedIn开源的分布式发布-订阅消息系统，目前归属于Apache顶级项目。Kafka主要特点是基于Pull的模式来处理消息消费，追求高吞吐量，一开始的目的就是用于日志收集和传输。0.8版本开始支持复制，不支持事务，对消息的重复、丢失、错误没有严格要求，适合产生大量数据的互联网服务的数据收集业务。 |
| **RocketMQ** | RocketMQ是阿里开源的消息中间件，它是纯Java开发，具有高吞吐量、高可用性、适合大规模分布式系统应用的特点。RocketMQ思路起源于Kafka，但并不是Kafka的一个Copy，它对消息的可靠传输及事务性做了优化，目前在阿里集团被广泛应用于交易、充值、流计算、消息推送、日志流式处理、binglog分发等场景。 |
| **RabbitMQ** | RabbitMQ是使用Erlang语言开发的开源消息队列系统，基于AMQP协议来实现。AMQP的主要特征是面向消息、队列、路由（包括点对点和发布/订阅）、可靠性、安全。AMQP协议更多用在企业系统内对数据一致性、稳定性和可靠性要求很高的场景，对性能和吞吐量的要求还在其次。 |

> 总结：RabbitMQ比Kafka可靠，Kafka更适合IO高吞吐的处理，一般用在大数据日志的处理或对实时性（少量延迟）、可靠性（少量丢失数据）要求稍低的场景使用，比如ELK日志收集。



## 2. RabbitMQ引言

### 2.1 RabbitMQ

> 基于`AMQP`协议，erlang语言开发，是部署最广泛的开源消息中间件，是最受欢迎的消息中间件之一

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1626623579399-55f017e6-37d8-4da7-8749-f2df26bc9058.png?x-oss-process=image%2Fresize%2Cw_2000)



官网：[https://www.rabbitmq.com](https://www.rabbitmq.com/#features)

官网教程：[rabbitmq.com/getstarted.html](http://rabbitmq.com/getstarted.html)



> `AMQP`（Advanced Message Queuing Protocal）协议：在2003年时被提出，最早用于解决金融领不同平台之间的消息传递交互问题。顾名思义，AMQP是一种协议，更准确的说是一种binary wire-level protocol（链接协议）。这是其和JMS的本质差别，AMQP不从API层进行限定，而是直接定义网络交换的数据格式。这使得实现了AMQP的provider天然性就是跨平台的。以下是AMQP协议模型：

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1626624594261-9a3b2f5b-95e8-4960-bde1-35962302fbce.png)

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1626624567060-041bb36c-9a37-440b-927c-1a829ef93fe6.png)

- **Broker**：接收和分发消息的应用，消息队列服务器实体
- **Virtual Host**：出于多租户和安全因素设计，把AMQP基本组件分到一个虚拟的分组中，类似于网络中的namespace概念。当多个用户使用同一个RabbitMQ server提供的服务时，可以划分多个vhost，每个用户在自己的vhost中创建exchange/queue等
- **Connection**：publisher/consumer和broker之间的TCP连接。断开连接的操作只会在client端进行，Broker不会断开连接，除非出现网络故障或broker服务出现问题
- **Channel**：如果每一次访问RabbitMQ都建立一个Connection，在消息量大的时候建立TCP Connection的开销将是巨大的，效率也较低。Channel是在connection内部建立的逻辑连接，如果应用程序支持多线程，通常每个thread创建单独的channel进行通讯，AMQP method包含了channel id帮助客户端和message broker识别channel，所以channel之间是完全隔离的。Channel作为轻量级的Connection极大减少了操作系统建立TCP connection的开销
- **Exchange**：message到达broker的第一站，根据分发规则，匹配查询表中的routing key，分发消息到queue中去。常用的类型有：direct (point-to-point), topic (publish-subscribe) and fanout (multicast)
- **Queue**：消息最终被送到这里等待consumer取走。一个message可以被同时拷贝到多个queue中。
- **Binding**：exchange和queue之间的虚拟连接，binding中可以包含routing key。Binding信息被保存到exchange中的查询表中，用于message的分发依据。



### 2.2 RabbitMQ安装



## 3. RabbitMQ Tutorials



### 3.1 AMQP协议

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1626824973015-dcfe6d68-aca3-4eb5-a8bf-7b7454ace0d9.png)



### 3.2 RabbitMQ支持的消息模型

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1626825172717-e9b4ced6-ae63-49c9-847c-0e2e63c8fa86.png)

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1626825191615-e7404adf-14a0-4e81-9e27-7202c8d75369.png)



### 3.3 直连模式

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1626825251221-20367fc5-8c4f-40fa-8e16-428788c9395b.png)

- **P**：生产者
- **C**：消费者
- **queue**：消息队列，图中红色部分。类似一个邮箱，可以缓存消息；生产者向其中投递消息，消费者从其中取出消息。



> 生产者

```java
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
```

> 消费者

```java
public static void getMessage() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.111.72.203");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("123");
        //获取连接对象
        Connection connection = factory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定对象
        channel.queueDeclare("hello", false, false, false, null);
        //消费消息
        //参数1：消费哪个队列的消息  参数2：开启消息的自动确认机制  参数3：消费消息时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            //最后一个参数 body 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });
        //关闭资源  消费消息的时候不建议关闭资源 因为这里是两个线程，主线程把资源关闭了，另一边其实还在监听这个消息
        //channel.close();
        //connection.close();
    }
```

> 参数说明：
>
> Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
>                Map<String, Object> arguments) throws IOException;



### 3.4 work queue

![image.png](https://cdn.nlark.com/yuque/0/2021/png/12759906/1627395465344-5e4cc5f0-716a-4abe-af38-0b96e292d02a.png)

- P：生产者：任务的发布者
- C1：消费者-1，领取任务并且完成任务，假设完成速度较慢
- C2：消费者-2：领取任务并完成任务，假设完成速度快



> 生产者

```java
public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "work", null, (i + "---->hello work queue").getBytes());
        }
        RabbitMQUtils.closeConnectionAndChanel(connection, channel);
    }
```

> 消费者1

```java
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
```

> 消费者2

```java
public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("work", true, false, false, null);

        channel.basicConsume("work", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
            }
        });
    }
```



### 3.5 fanout（广播）
