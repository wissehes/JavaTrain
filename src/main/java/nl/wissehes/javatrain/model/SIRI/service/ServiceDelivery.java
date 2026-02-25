package nl.wissehes.javatrain.model.SIRI.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDelivery {

    @JacksonXmlProperty(localName = "ResponseTimestamp")
    public OffsetDateTime timestamp;

    @JacksonXmlProperty(localName = "ProducerRef")
    public String producerRef;

    @JacksonXmlProperty(localName = "FacilityMonitoringDelivery")
    public FacilityMonitoringDelivery facilityMonitoringDelivery;

}
