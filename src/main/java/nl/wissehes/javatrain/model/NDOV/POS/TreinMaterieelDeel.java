package nl.wissehes.javatrain.model.NDOV.POS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TreinMaterieelDeel {

    @JacksonXmlProperty(localName = "MaterieelDeelNummer")
    public String materieelDeelNummer;

    @JacksonXmlProperty(localName = "Materieelvolgnummer")
    public Integer volgNummer;

    @JacksonXmlProperty(localName = "GpsDatumTijd")
    public Date gpsDatumTijd;

    @JacksonXmlProperty(localName = "Bron")
    public String bron;

    @JacksonXmlProperty(localName = "Longitude")
    public Double longitude;

    @JacksonXmlProperty(localName = "Latitude")
    public Double latitude;

    @JacksonXmlProperty(localName = "Elevation")
    public Double elevation;

    @JacksonXmlProperty(localName = "Snelheid")
    public Double snelheid;

    @JacksonXmlProperty(localName = "Richting")
    public Double richting;

    @JacksonXmlProperty(localName = "Hdop")
    public Double hdop;

    @JacksonXmlProperty(localName = "AantalSatelieten")
    public Integer aantalSatelieten;

}
