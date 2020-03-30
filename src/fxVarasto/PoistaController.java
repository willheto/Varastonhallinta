package fxVarasto;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
 * @version 30 Mar 2020
 * Varastonkorjauksen tietojen syöttö
 */
public class PoistaController implements ModalControllerInterface<String> {

    private String vastaus = "";
    
    @FXML
    private Button nappi;

    
    /**
     * Mitä tapahtuu kun hyväksytään poisto.
     */
    @FXML
    private void handleDefaultOK() {
        vastaus = "ok";
        ModalController.closeStage(nappi);
    }


    /**
     * Mitä tapahtuu kun perutaan poisto
     */
    @FXML
    private void handleDefaultCancel() {
        vastaus = "cancel";
        ModalController.closeStage(nappi);
    }


    /**
     * Palautetaan poiston tulos, eli kyllä vai ei
     */
    @Override
    public String getResult() {
        return vastaus.toString();
    }


    /**
     * Ei käytössä
     */
    @Override
    public void setDefault(String oletus) {
        //
    }


    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        //
    }

}
