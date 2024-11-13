package nl.wissehes.javatrain.model.NDOV.DVS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import nl.wissehes.javatrain.model.NDOV.ReisInformatieProductDVS;

@JacksonXmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartureDocument {

    @JacksonXmlProperty(localName = "ReisInformatieProductDVS")
    public ReisInformatieProductDVS reisinformatieProduct;

}
