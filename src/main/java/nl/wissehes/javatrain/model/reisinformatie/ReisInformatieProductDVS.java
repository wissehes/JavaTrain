package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReisInformatieProductDVS {
    @JacksonXmlProperty(localName = "Versie", isAttribute = true)
    public String versie;

    @JacksonXmlProperty(localName = "RIPAdministratie")
    public RIPAdministratie ripAdministratie;

    @JacksonXmlProperty(localName = "DynamischeVertrekStaat")
    public DynamischeVertrekStaat dynamischeVertrekStaat;
}

record RIPAdministratie(
    @JacksonXmlProperty(localName = "ReisInformatieProductID") String reisInformatieProductID,
    @JacksonXmlProperty(localName = "AbonnementId") String abonnementId,
    @JacksonXmlProperty(localName = "ReisInformatieTijdstip") String reisInformatieTijdstip
){}