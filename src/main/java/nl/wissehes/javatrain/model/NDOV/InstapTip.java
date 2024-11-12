package nl.wissehes.javatrain.model.NDOV;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstapTip {

    @JacksonXmlProperty(localName = "InstapTipTreinSoort")
    public Trein.TreinSoort instapTipTreinSoort;

    @JacksonXmlProperty(localName = "InstapTipUitstapStation")
    public Station instapTipUitstapStation;

    @JacksonXmlProperty(localName = "InstapTipTreinEindBestemming")
    public Station instapTipTreinEindBestemming;

    @JacksonXmlProperty(localName = "InstapTipVertrekTijd")
    public Date instapTipVertrekTijd;

    @JacksonXmlProperty(localName = "InstapTipVertrekSpoor")
    public Trein.TreinVertrekSpoor instapTipVertrekSpoor;

    @JacksonXmlProperty(localName = "PresentatieInstapTip")
    public LocalizedUiting presentatieInstapTip;

}
