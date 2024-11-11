package nl.wissehes.javatrain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

@Component
public class DataReceiver {
    public static List<String> receivedMessages = new ArrayList<>();

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
                    String message = decompress(messageBytes);
                    receivedMessages.add(message);
                    System.out.println("Received message on topic: " + topic + " - " + message);
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

    private static String decompress(byte[] compressedData) throws IOException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            return byteArrayOutputStream.toString("UTF-8"); // Convert to string with appropriate encoding
        }
    }
}
