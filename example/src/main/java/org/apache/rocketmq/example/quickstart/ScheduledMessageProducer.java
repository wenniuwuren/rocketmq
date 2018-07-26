package org.apache.rocketmq.example.quickstart;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author zhuyibin
 */
public class ScheduledMessageProducer {

    public static void main(String[] args) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("scheduler_message");
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();

            for (int i = 0; i < 3; i++) {
                Message message = new Message("ZTopicTest", ("Hello scheduled message " + i).getBytes());
                // This message will be delivered to consumer 10 seconds later.
                message.setDelayTimeLevel(3);
                producer.send(message);

                SendResult sendResult = producer.send(message);

                System.out.printf("%s%n", sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}