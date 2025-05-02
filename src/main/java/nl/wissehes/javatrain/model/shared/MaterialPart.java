package nl.wissehes.javatrain.model.shared;

import nl.wissehes.javatrain.model.NDOV.InfoStatus;
import nl.wissehes.javatrain.model.NDOV.MaterieelDeel;
import nl.wissehes.javatrain.model.NDOV.RIT.RitMaterieelDeel;
import nl.wissehes.javatrain.util.StringUtilities;

import java.util.List;

import static nl.wissehes.javatrain.mapper.Shared.getDestination;

public class MaterialPart {
    public String materialNumber;
    public Station plannedDestination;
    public Station actualDestination;
    public String type;
    /** Length in meters */
    public int length;
    public int departurePosition;
    public int departureOrder;
    public List<ScheduleChange> scheduleChange;

    public MaterialPart(MaterieelDeel materieelDeel) {
        this.materialNumber = StringUtilities.formatMaterialNumber(materieelDeel.materieelNummer);
        this.plannedDestination = getDestination(materieelDeel.eindBestemming, InfoStatus.GEPLAND);
        this.actualDestination = getDestination(materieelDeel.eindBestemming, InfoStatus.ACTUEEL);
        this.type = materieelDeel.materieelSoort + "-" + materieelDeel.materieelAanduiding;
        this.length = materieelDeel.materieelLengte;
        this.departurePosition = materieelDeel.materieelDeelVertrekPositie;
        this.departureOrder = materieelDeel.materieelDeelVolgordeVertrek;
        this.scheduleChange = materieelDeel.wijzigingen.stream().map(ScheduleChange::new).toList();
    }

    public MaterialPart(RitMaterieelDeel materieelDeel) {
        this.materialNumber = StringUtilities.formatMaterialNumber(materieelDeel.materieelNummer);
        this.plannedDestination = getDestination(materieelDeel.eindBestemming, InfoStatus.GEPLAND);
        this.actualDestination = getDestination(materieelDeel.eindBestemming, InfoStatus.ACTUEEL);
        this.type = materieelDeel.materieelSoort + "-" + materieelDeel.materieelAanduiding;
        this.length = materieelDeel.materieelLengte;
        this.departurePosition = materieelDeel.materieelDeelVertrekPositie;
        this.departureOrder = materieelDeel.materieelDeelVolgordeVertrek;
        this.scheduleChange = materieelDeel.wijzigingen.stream().map(ScheduleChange::new).toList();
    }
}
