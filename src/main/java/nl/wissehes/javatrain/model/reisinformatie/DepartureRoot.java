package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartureRoot {

    /**
     * Get unique ID of this departure item
     */
    public String getId() {
        DynamischeVertrekStaat dvs = reisinformatieProduct.dynamischeVertrekStaat;
        return dvs.ritStation.code + "-" + dvs.ritId + "-" + dvs.ritDatum;
    }

    @JacksonXmlProperty(localName = "ReisInformatieProductDVS")
    public ReisInformatieProductDVS reisinformatieProduct;

}
