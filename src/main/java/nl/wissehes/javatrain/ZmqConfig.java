package nl.wissehes.javatrain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Configuration
public class ZmqConfig {

    @Value("${infoplus.endpoint}")
    private String ZMQ_SUBSCRIBER_ADDRESS;

    /** Departures */
    public static final String DVS_TOPIC = "/RIG/InfoPlusDVSInterface4";
    /** Arrivals */
    public static final String DAS_TOPIC = "/RIG/InfoPlusDASInterface4";
    /** Services */
    public static final String RIT_TOPIC = "/RIG/InfoPlusRITInterface5";
    /** Positions */
    public static final String POS_TOPIC = "/RIG/NStreinpositiesInterface5";

    @Bean(destroyMethod = "close")
    public ZMQ.Socket zmqSubscriber() {
        ZContext context = new ZContext();
        ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
        subscriber.connect(ZMQ_SUBSCRIBER_ADDRESS);

        subscriber.subscribe(DVS_TOPIC.getBytes(ZMQ.CHARSET));
        subscriber.subscribe(RIT_TOPIC.getBytes(ZMQ.CHARSET));
        subscriber.subscribe(POS_TOPIC.getBytes(ZMQ.CHARSET));

        return subscriber;
    }
}
