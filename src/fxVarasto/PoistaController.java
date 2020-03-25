package fxVarasto;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
 * @version 23 Mar 2020
 * Varastonkorjauksen tietojen syöttö
 */
public class PoistaController implements ModalControllerInterface<String> {

    private String vastaus = null;
    
    @FXML
    private Button nappi;

    @FXML
    private void handleDefaultOK() {
        vastaus = "ok";
        ModalController.closeStage(nappi);
    }


    @FXML
    private void handleDefaultCancel() {
        vastaus = "cancel";
        ModalController.closeStage(nappi);
    }


    @Override
    public String getResult() {
        return vastaus.toString();
    }


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
