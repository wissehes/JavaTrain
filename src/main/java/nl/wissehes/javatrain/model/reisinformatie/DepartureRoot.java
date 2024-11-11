package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartureRoot {

    @JacksonXmlProperty(localName = "ReisInformatieProductDVS")
    public ReisInformatieProductDVS reisinformatieProduct;

}
