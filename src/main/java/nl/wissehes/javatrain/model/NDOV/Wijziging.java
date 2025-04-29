package nl.wissehes.javatrain.model.NDOV;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Wijziging {
    @JacksonXmlProperty(localName = "WijzigingType")
    public int wijzigingType;

    @JacksonXmlProperty(localName = "WijzigingOorzaakKort")
    public String wijzigingOorzaakKort;

    @JacksonXmlProperty(localName = "WijzigingOorzaakLang")
    public String wijzigingOorzaakLang;

    @JacksonXmlProperty(localName = "PresentatieWijziging")
    public LocalizedUiting presentatieWijziging;

    @JacksonXmlProperty(localName = "WijzigingStation")
    public Station station;
}