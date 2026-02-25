package nl.wissehes.javatrain.model.SIRI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import nl.wissehes.javatrain.model.SIRI.heartbeat.HeartbeatNotification;
import nl.wissehes.javatrain.model.SIRI.service.ServiceDelivery;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement
public class SiriMessageDocument {

    @JacksonXmlProperty(localName = "ServiceDelivery")
    public ServiceDelivery serviceDelivery;

    @JacksonXmlProperty(localName = "HeartbeatNotification")
    public HeartbeatNotification heartbeatNotification;

}
