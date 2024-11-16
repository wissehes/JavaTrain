package nl.wissehes.javatrain.model.NDOV.RIT;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.NSBoolean;
import nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit.LogischeRit;
import nl.wissehes.javatrain.model.NDOV.Trein;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RitInfo {

    @JacksonXmlProperty(localName = "TreinNummer")
    public String nummer;

    @JacksonXmlProperty(localName = "TreinDatum")
    public String datum;

    @JacksonXmlProperty(localName = "TreinSoort")
    public Trein.TreinSoort soort;

    @JacksonXmlProperty(localName = "Vervoerder")
    public String vervoerder;

    @JacksonXmlProperty(localName = "Reserveren")
    public NSBoolean reserveren;

    @JacksonXmlProperty(localName = "Toeslag")
    public NSBoolean toeslag;

    @JacksonXmlProperty(localName = "SpeciaalKaartje")
    public NSBoolean speciaalKaartje;

    @JacksonXmlProperty(localName = "Reisplanner")
    public NSBoolean reisplanner;

    @JacksonXmlProperty(localName = "LogischeRit")
    public LogischeRit logischeRit;

}
