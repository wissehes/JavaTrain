package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InfoStatus {
    @JsonProperty("Actueel")
    ACTUEEL,
    @JsonProperty("Gepland")
    GEPLAND
}