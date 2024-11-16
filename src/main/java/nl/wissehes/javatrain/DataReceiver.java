package nl.wissehes.javatrain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import nl.wissehes.javatrain.util.GZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

import java.io.IOException;

@Component
public class DataReceiver {

    private final ZMQ.Socket subscriber;
    private final DataStore dataStore;

    Logger logger = LoggerFactory.getLogger(DataReceiver.class);

    public DataReceiver(ZMQ.Socket subscriber, DataStore dataStore) {
        this.subscriber = subscriber;
        this.dataStore = dataStore;
    }

    @PostConstruct
    public void startListening() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                String topic = subscriber.recvStr(); // Receive topic
                byte[] messageBytes = subscriber.recv(); // Receive the compressed message as bytes
                this.handleMessage(topic, messageBytes);
            }
        }).start();
    }

    private void handleMessage(String topic, byte[] messageBytes) {
        try {
            logger.debug("Received message on topic: {}", topic);

            String message = GZipUtils.decompress(messageBytes);

            if(topic.equals(ZmqConfig.DVS_TOPIC)) {
                dataStore.addDeparture(message);
            } else if(topic.equals(ZmqConfig.RIT_TOPIC)) {
                dataStore.addJourney(message);
            }
        } catch (IOException e) {
            logger.error("Failed to decompress message: {}", e.getMessage());
        }
    }

    @PreDestroy
    public void stopListening() {
        subscriber.close(); // Clean up resources
    }
}
