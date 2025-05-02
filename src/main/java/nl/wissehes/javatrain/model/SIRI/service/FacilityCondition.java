package nl.wissehes.javatrain.model.SIRI.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacilityCondition {

    @JacksonXmlProperty(localName = "FacilityRef")
    public String facilityRef;

    @JacksonXmlProperty(localName = "FacilityStatus")
    public FacilityStatus facilityStatus;

    @JacksonXmlProperty(localName = "ValidityPeriod")
    public ValidityPeriod validityPeriod;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record FacilityStatus(
            @JacksonXmlProperty(localName = "Status") String status
    ) { }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ValidityPeriod(
            @JacksonXmlProperty(localName = "StartTime") OffsetDateTime startTime
    ) { }
}
