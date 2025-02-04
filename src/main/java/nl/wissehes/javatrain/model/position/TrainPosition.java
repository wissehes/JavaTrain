package nl.wissehes.javatrain.model.position;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainPosition {

    public String trainNumber;
    public String materialNumber;
    public Integer sequenceNumber;

    public Double longitude;
    public Double latitude;
    public Double elevation;
    public Double speed;

    public Date locationDate;
    public String source;

    }
