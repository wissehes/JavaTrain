package nl.wissehes.javatrain.model.NDOV;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterieelDeel {
    @JacksonXmlProperty(localName = "MaterieelDeelId")
    public String id;

    @JacksonXmlProperty(localName = "MaterieelSoort")
    public String materieelSoort;

    @JacksonXmlProperty(localName = "MaterieelAanduiding")
    public String materieelAanduiding;

    @JacksonXmlProperty(localName = "MaterieelLengte")
    public String materieelLengte;

    @JacksonXmlProperty(localName = "MaterieelDeelVertrekPositie")
    public String materieelDeelVertrekPositie;

    @JacksonXmlProperty(localName = "MaterieelDeelVolgordeVertrek")
    public String materieelDeelVolgordeVertrek;

    @JacksonXmlProperty(localName = "MaterieelNummer")
    public String materieelNummer;

    @JacksonXmlProperty(localName = "MaterieelDeelEindBestemming")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Trein.Eindbestemming> eindBestemming;

    @JacksonXmlProperty(localName = "PresentatieMaterieelDeelEindBestemming")
    public LocalizedUiting presentatieEindBestemming;

    @JacksonXmlProperty(localName = "Wijziging")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Wijziging> wijzigingen = List.of();
}
