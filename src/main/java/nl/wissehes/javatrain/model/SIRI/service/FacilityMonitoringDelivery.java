package nl.wissehes.javatrain.model.SIRI.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacilityMonitoringDelivery {

    @JacksonXmlProperty(localName = "ResponseTimestamp")
    public LocalDateTime timestamp;

    @JacksonXmlProperty(localName = "FacilityCondition")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<FacilityCondition> facilityCondition;

}
