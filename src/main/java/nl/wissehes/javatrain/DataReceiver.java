package nl.wissehes.javatrain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import nl.wissehes.javatrain.model.SiriMessage;
import nl.wissehes.javatrain.util.GZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

import java.io.IOException;

@Component
public class DataReceiver {

    private final ZMQ.Socket infoplusSubscriber;
    private final ZMQ.Socket siriSubscriber;
    private final DataStore dataStore;

    Logger logger = LoggerFactory.getLogger(DataReceiver.class);

    @Value("${infoplus.log-messages:false}")
    private boolean shouldLogMessages;

    public DataReceiver(ZmqConfig config, DataStore dataStore) {
        this.infoplusSubscriber = config.infoplusSubscriber();
        this.siriSubscriber = config.siriSubscriber();
        this.dataStore = dataStore;
    }

    @PostConstruct
    public void startListening() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                String topic = infoplusSubscriber.recvStr(); // Receive topic
                byte[] messageBytes = infoplusSubscriber.recv(); // Receive the compressed message as bytes
                this.handleMessage(topic, messageBytes);
            }
        }).start();

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                String topic = siriSubscriber.recvStr(); // Receive topic
                byte[] messageBytes = siriSubscriber.recv(); // Receive the compressed message as bytes
                if(!topic.startsWith("/HTM") && !topic.startsWith("/GVB")) {
                    System.out.println("Topic: " + topic);
                    System.out.println("Message: " + messageBytes.length);
                    this.handleMessage(topic, messageBytes);
                }
            }
        }).start();
    }

    private void handleMessage(String topic, byte[] messageBytes) {
        try {
            if(shouldLogMessages) {
                logger.info("Received message on topic: {}", topic);
            }

            String message = GZipUtils.decompress(messageBytes);

            switch (topic) {
                case ZmqConfig.DVS_TOPIC -> dataStore.addDeparture(message);
                case ZmqConfig.RIT_TOPIC -> dataStore.addJourney(message);
                case ZmqConfig.POS_TOPIC -> dataStore.addPosition(message);
                default -> dataStore.addRawSiriMessage(new SiriMessage(topic, message));
            }
        } catch (IOException e) {
            logger.error("Failed to decompress message: {}", e.getMessage());
        }
    }

    @PreDestroy
    public void stopListening() {
        infoplusSubscriber.close(); // Clean up resources
    }
}
