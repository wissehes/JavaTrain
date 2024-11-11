package nl.wissehes.javatrain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Configuration
public class ZmqConfig {

    private static final String ZMQ_SUBSCRIBER_ADDRESS = "tcp://pubsub.besteffort.ndovloket.nl:7664";
    private static final String TOPIC = "/RIG/InfoPlusDVSInterface4";

    @Bean(destroyMethod = "close")
    public ZMQ.Socket zmqSubscriber() {
        ZContext context = new ZContext();
        ZMQ.Socket subscriber = context.createSocket(ZMQ.SUB);
        subscriber.connect(ZMQ_SUBSCRIBER_ADDRESS);
        subscriber.subscribe(TOPIC.getBytes(ZMQ.CHARSET)); // Subscribe to the topic
        return subscriber;
    }
}
