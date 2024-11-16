package nl.wissehes.javatrain.model.NDOV.RIT;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.LocalizedUiting;
import nl.wissehes.javatrain.model.NDOV.NSBoolean;
import nl.wissehes.javatrain.model.NDOV.Trein;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RitMaterieelDeel {

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
    public String materieelMummer;

    @JacksonXmlProperty(localName = "MaterieelDeelToegankelijk")
    public NSBoolean materieelDeelToegankelijk;

    @JacksonXmlProperty(localName = "MaterieelDeelEindBestemming")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Trein.Eindbestemming> eindBestemming;

    @JacksonXmlProperty(localName = "PresentatieMaterieelDeelEindBestemming")
    public LocalizedUiting presentatieEindBestemming;

    @JacksonXmlProperty(localName = "AchterBlijvenMaterieelDeel")
    public NSBoolean achterBlijvenMaterieelDeel;

    @JacksonXmlProperty(localName = "MaterieelDeelVolgendeLogischeRitDeel")
    public MaterieelDeelVolgendeLogischeRitDeel materieelDeelVolgendeLogischeRitDeel;

    public record MaterieelDeelVolgendeLogischeRitDeel(
            @JacksonXmlProperty(localName = "Treindatum") String treinDatum,
            @JacksonXmlProperty(localName = "LogischeRitDeelNummer") String logischeRitDeelNummer
    ) {}
}
