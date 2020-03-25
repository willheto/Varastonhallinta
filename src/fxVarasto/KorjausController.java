package fxVarasto;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
 * @version 23 Mar 2020
 * Varastonkorjauksen tietojen syöttö
 */
public class KorjausController implements ModalControllerInterface<String> {

    @FXML
    private ComboBoxChooser<String> selitys;

    @FXML
    private Label virhe;

    @FXML
    private TextField maara;
    private String vastaus = "";

    @FXML
    private void handleDefaultOK() {
        oikeellisuusTarkistus();
    }


    @FXML
    private void handleDefaultCancel() {
        ModalController.closeStage(selitys);
    }


    @Override
    public String getResult() {
        return vastaus.toString();
    }


    @Override
    public void setDefault(String oletus) {
        //
    }


    /*
     * Tarkistaa, että määrään on sijoitettu luku
     */
    private void oikeellisuusTarkistus() {
        try {
            Integer.valueOf(maara.getText().toString());
            vastaus = (selitys.getSelectedText() + " " + maara.getText());
            ModalController.closeStage(virhe);
        } catch (Exception e) {
            virhe.setText("Määrän tulee olla numero!");
            return;
        }
    }


    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        //
    }

}
