package nl.wissehes.javatrain.model.shared;

import nl.wissehes.javatrain.model.NDOV.RIT.RitMaterieelDeel;

public class JourneyMaterialPart extends MaterialPart {

    public JourneyMaterialPart(RitMaterieelDeel materieelDeel) {
        super(materieelDeel);

        if(materieelDeel.materieelDeelToegankelijk != null) {
            this.accessible = materieelDeel.materieelDeelToegankelijk.toBoolean();
        }

        if(materieelDeel.achterBlijvenMaterieelDeel != null) {
            this.staysBehind = materieelDeel.achterBlijvenMaterieelDeel.toBoolean();
        }
    }

    public Boolean accessible;
    public Boolean staysBehind;
}
