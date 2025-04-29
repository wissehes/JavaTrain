package nl.wissehes.javatrain.model.NDOV.DVS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.LocalizedUiting;
import nl.wissehes.javatrain.model.NDOV.Station;
import nl.wissehes.javatrain.model.NDOV.Trein;
import nl.wissehes.javatrain.model.NDOV.TreinSpoor;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstapTip implements DvsTip {

    @JacksonXmlProperty(localName = "InstapTipTreinSoort")
    public Trein.TreinSoort instapTipTreinSoort;

    @JacksonXmlProperty(localName = "InstapTipUitstapStation")
    public Station instapTipUitstapStation;

    @JacksonXmlProperty(localName = "InstapTipTreinEindBestemming")
    public Station instapTipTreinEindBestemming;

    @JacksonXmlProperty(localName = "InstapTipVertrekTijd")
    public Date instapTipVertrekTijd;

    @JacksonXmlProperty(localName = "InstapTipVertrekSpoor")
    public TreinSpoor instapTipVertrekSpoor;

    @JacksonXmlProperty(localName = "PresentatieInstapTip")
    public LocalizedUiting presentatieInstapTip;

    @Override
    public LocalizedUiting getPresentatie() {
        return presentatieInstapTip;
    }
}
