package nl.wissehes.javatrain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Service
public class ZmqConfig {

    @Value("${infoplus.endpoint}")
    private String ZMQ_SUBSCRIBER_ADDRESS;

    private String SIRI_ADDRESS = "tcp://pubsub.besteffort.ndovloket.nl:7666";

    /** Departures */
    public static final String DVS_TOPIC = "/RIG/InfoPlusDVSInterface4";
    /** Arrivals */
    public static final String DAS_TOPIC = "/RIG/InfoPlusDASInterface4";
    /** Services */
    public static final String RIT_TOPIC = "/RIG/InfoPlusRITInterface5";
    /** Positions */
    public static final String POS_TOPIC = "/RIG/NStreinpositiesInterface5";

//    @Bean(destroyMethod = "close")
    public ZMQ.Socket infoplusSubscriber() {
        ZContext context = new ZContext();
        ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
        subscriber.connect(ZMQ_SUBSCRIBER_ADDRESS);

        subscriber.subscribe(DVS_TOPIC.getBytes(ZMQ.CHARSET));
        subscriber.subscribe(RIT_TOPIC.getBytes(ZMQ.CHARSET));
        subscriber.subscribe(POS_TOPIC.getBytes(ZMQ.CHARSET));

        return subscriber;
    }

    public ZMQ.Socket siriSubscriber() {
        ZContext context = new ZContext();
        ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
        subscriber.connect(SIRI_ADDRESS);

        subscriber.subscribe("/".getBytes(ZMQ.CHARSET));

        return subscriber;
    }
}
