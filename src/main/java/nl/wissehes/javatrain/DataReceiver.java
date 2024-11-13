package nl.wissehes.javatrain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import nl.wissehes.javatrain.util.GZipUtils;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

import java.io.IOException;

@Component
public class DataReceiver {

    private final ZMQ.Socket subscriber;

    public DataReceiver(ZMQ.Socket subscriber) {
        this.subscriber = subscriber;
    }

    @PostConstruct
    public void startListening() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                String topic = subscriber.recvStr(); // Receive topic
                byte[] messageBytes = subscriber.recv(); // Receive the compressed message as bytes

                try {
                    System.out.println("Received message on topic: " + topic);

                    String message = GZipUtils.decompress(messageBytes);
                    DataStore.getInstance().addDeparture(message);
                } catch (IOException e) {
                    System.err.println("Failed to decompress message: " + e.getMessage());
                }
            }
        }).start();
    }

    @PreDestroy
    public void stopListening() {
        subscriber.close(); // Clean up resources
    }
}
