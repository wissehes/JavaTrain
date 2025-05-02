package nl.wissehes.javatrain.model.NDOV.RIT;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.MaterieelDeel;
import nl.wissehes.javatrain.model.NDOV.NSBoolean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RitMaterieelDeel extends MaterieelDeel {

    @JacksonXmlProperty(localName = "MaterieelDeelSoort")
    public String materieelSoort;

    @JacksonXmlProperty(localName = "MaterieelDeelAanduiding")
    public String materieelAanduiding;

    @JacksonXmlProperty(localName = "MaterieelDeelLengte")
    public int materieelLengte;

    @JacksonXmlProperty(localName = "MaterieelDeelToegankelijk")
    public NSBoolean materieelDeelToegankelijk;

    @JacksonXmlProperty(localName = "AchterBlijvenMaterieelDeel")
    public NSBoolean achterBlijvenMaterieelDeel;

    @JacksonXmlProperty(localName = "MaterieelDeelVolgendeLogischeRitDeel")
    public MaterieelDeelVolgendeLogischeRitDeel materieelDeelVolgendeLogischeRitDeel;

    public record MaterieelDeelVolgendeLogischeRitDeel(
            @JacksonXmlProperty(localName = "TreinDatum") String treinDatum,
            @JacksonXmlProperty(localName = "LogischeRitDeelNummer") String logischeRitDeelNummer
    ) {}
}
