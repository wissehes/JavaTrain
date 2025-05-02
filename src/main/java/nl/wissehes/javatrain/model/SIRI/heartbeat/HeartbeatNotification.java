package nl.wissehes.javatrain.model.SIRI.heartbeat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.OffsetDateTime;

/*
        <RequestTimestamp>2025-05-01T17:04:30+02:00</RequestTimestamp>
        <ProducerRef>DOVA</ProducerRef>
        <Status>true</Status>
        <ServiceStartedTime>2025-05-01T16:09:49+02:00</ServiceStartedTime>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeartbeatNotification {

    @JacksonXmlProperty(localName = "RequestTimestamp")
    public OffsetDateTime requestTimestamp;

    @JacksonXmlProperty(localName = "ProducerRef")
    public String producerRef;

    @JacksonXmlProperty(localName = "Status")
    public boolean status;

    @JacksonXmlProperty(localName = "ServiceStartedTime")
    public OffsetDateTime serviceStartedTime;

}
