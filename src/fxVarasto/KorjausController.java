package fxVarasto;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
 * @version 30 Mar 2020
 * Varastonkorjauksen tietojen syöttö luokka
 */
public class KorjausController implements ModalControllerInterface<String> {

    @FXML
    private ComboBoxChooser<String> selitys;

    @FXML
    private Label virhe;

    @FXML
    private TextField maara;
    private String vastaus = "";

    
    /**
     * Mitä tapahtuu kun hyväksytään korjaus, eli siirrytään oikeellisuustarkistukseen.
     */
    @FXML
    private void handleDefaultOK() {
        oikeellisuusTarkistus();
    }
    

    /**
     * Mitä tapahtuu kun ei hyväksytäkkään korjausta, eli suljetaan ikkuna.
     */
    @FXML
    private void handleDefaultCancel() {
        ModalController.closeStage(selitys);
    }


    /**
     * Palauttaa tehdyn muutoksen määrän
     */
    @Override
    public String getResult() {
        return vastaus.toString();
    }


    /**
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

    
    /**
     * Ei käytössä
     */
    @Override
    public void setDefault(String arg0) {
        //
    }

}
