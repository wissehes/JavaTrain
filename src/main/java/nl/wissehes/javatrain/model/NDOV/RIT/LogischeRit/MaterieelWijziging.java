package nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * <MaterieelWijziging>
 *     <MaterieelWijzigingType>Aftrappen</MaterieelWijzigingType>
 *     <GewijzigdMaterieel>
 *         <MaterieelDeelID>9595</MaterieelDeelID>
 *         <MaterieelNummer>000000-09595-0</MaterieelNummer>
 *     </GewijzigdMaterieel>
 * </MaterieelWijziging>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterieelWijziging {

    @JacksonXmlProperty(localName = "MaterieelWijzigingType")
    public String wijzigingType;

    @JacksonXmlProperty(localName = "GewijzigdMaterieel")
    public GewijzigdMaterieel gewijzigdMaterieel;

    public record GewijzigdMaterieel(
            @JacksonXmlProperty(localName = "MaterieelDeelID") String materieelDeelId,
            @JacksonXmlProperty(localName = "MaterieelNummer") String materieelNummer
    ) {}
}
