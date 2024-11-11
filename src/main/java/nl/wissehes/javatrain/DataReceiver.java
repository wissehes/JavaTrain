package nl.wissehes.javatrain;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import nl.wissehes.javatrain.model.reisinformatie.DepartureRoot;
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
    public static List<DepartureRoot> receivedMessages = new ArrayList<>();
    public static List<String> receivedMessagesRaw = new ArrayList<>();

    private final XmlMapper mapper = new XmlMapper();

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

                    String message = decompress(messageBytes);
                    receivedMessagesRaw.add(message);

                    DepartureRoot departureRoot = parseXML(message);
                    receivedMessages.add(departureRoot);
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

    private DepartureRoot parseXML(String xml) {
        try {
            return mapper.readValue(xml, DepartureRoot.class);
        } catch (Exception e) {
            System.err.println("Failed to parse XML: " + e.getMessage());
            return null;
        }
    }
}
