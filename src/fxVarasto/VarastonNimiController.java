package fxVarasto;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author henri
 * @version 23 Mar 2020
 * Varaston nimen kysely
 */
public class VarastonNimiController
        implements ModalControllerInterface<String> {

    @FXML
    private TextField textVastaus;
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


    @FXML
    private void handleDefaultOK() {
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }


    @FXML
    private void handleDefaultCancel() {
        ModalController.closeStage(textVastaus);
    }


    @Override
    public String getResult() {
        return vastaus;
    }


    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus);
    }


    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        textVastaus.requestFocus();
    }

}
