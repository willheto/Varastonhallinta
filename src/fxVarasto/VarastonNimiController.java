package fxVarasto;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
 * @version 23 Mar 2020
 * Varaston nimen kysely
 */
public class VarastonNimiController
        implements ModalControllerInterface<String> {

    @FXML
    private TextField textVastaus;
    @FXML
    private ComboBoxChooser<String> varastoLista;
    
    private String vastaus = null;

    /**
     * @param modalityStage modaalisuus
     * @param oletus oletus
     * @return varaston nimen
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {     
        return ModalController.showModal(
                VarastonNimiController.class
                        .getResource("fxml-tiedostot/NimenKyselyGUIView.fxml"),
                "Varasto", modalityStage, oletus);
    }


    /**
     * Mitä tapahtuu kun hyväksytään varasto.
     */
    @FXML
    private void handleDefaultOK() {
        if(varastoLista.getSelectedText() != "Valitse") {
            vastaus = varastoLista.getSelectedText();
            ModalController.closeStage(textVastaus);
            return;
        }
        String eiRiipuKirjainKoosta = textVastaus.getText().toLowerCase();
        vastaus = eiRiipuKirjainKoosta.substring(0, 1).toUpperCase()
                + eiRiipuKirjainKoosta.substring(1);
        ModalController.closeStage(textVastaus);
    }

    
    /**
     * Mitä tehdään kun painetaankin peru
     */
    @FXML
    private void handleDefaultCancel() {
        ModalController.closeStage(textVastaus);
    }
    

    /**
     * Palauttaa varaston nimen, joka valittiin
     */
    @Override
    public String getResult() {
        return vastaus;
    }

    
    /**
     * Asettaa avattavien varastojen listaan varastojen nimet.
     */
    @Override
    public void setDefault(String oletus) {
        varastoLista.setRivit(oletus);
    }


    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        textVastaus.requestFocus();
    }

}
