package fxVarasto;

import fi.jyu.mit.fxgui.*;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
 * @version 23 Mar 2020
 * Tuotteen tietojen syöttö
 */
public class MuokkausController implements ModalControllerInterface<String> {

    @FXML
    private TextField nimi;

    @FXML
    private TextField kapasi;

    @FXML
    private TextField kollit;

    @FXML
    private Label virhe;

    @FXML
    private ComboBoxChooser<String> status;

    private String vastaus = "";

    
    /**
     * Mitä tapahtuu kun hyväksytään muokkaus, eli siirrytään oikeellisuustarkistukseen.
     */
    @FXML
    private void handleDefaultOK() {
        oikeellisuusTarkistus();
    }


    /**
     * Tarkistaa että tuotteen tiedot ovat hyväksyttäviä
     */
    private void oikeellisuusTarkistus() {

        String[] merkkijonot = { nimi.getText() };

        if (nimi.getText().equals("") || kapasi.getText().equals("")
                || kollit.getText().equals("")) {
            virhe.setText("Täytä kaikki kentät!");
            return;
        }

        try {
            int[] luvut = { Integer.valueOf(kapasi.getText()),
                    Integer.valueOf(kollit.getText()) };

            for (int i = 0; i < luvut.length; i++) {
                if (luvut[i] < 0) {
                    virhe.setText(
                            "Kapasiteetti tai kollien määrä ei voi olla negatiivinen!");
                    return;
                }
            }
        } catch (Exception e) {
            virhe.setText("Kapasiteettiin ja kolleihin vain numeroita!");
            return;
        }

        for (int i = 0; i < merkkijonot.length; i++) {
            if (merkkijonot[i] == null || merkkijonot[i] == "") {
                virhe.setText("Täytä kaikki kentät!");
                return;
            }
        }

        vastaus = (nimi.getText() + "|" + kapasi.getText() + "|"
                + kollit.getText() + "|" + status.getSelectedText());
        ModalController.closeStage(nimi);

    }


    /**
     * Mitä tapahtuu kun ei hyväksytäkkään muokkausta, eli suljetaan ikkuna.
     */
    @FXML
    private void handleDefaultCancel() {
        ModalController.closeStage(nimi);
        return;
    }


    /**
     * Palauttaa tehdyn muutoksen
     */
    @Override
    public String getResult() {
        return vastaus;
    }


    /**
     * Ei käytössä
     */
    @Override
    public void setDefault(String oletus) {
        StringBuilder tuotteenTiedot = new StringBuilder(oletus);
        this.nimi.setText(Mjonot.erota(tuotteenTiedot));
        this.kapasi.setText(Mjonot.erota(tuotteenTiedot));
        this.kollit.setText(Mjonot.erota(tuotteenTiedot));
    }


    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        //
    }

}
