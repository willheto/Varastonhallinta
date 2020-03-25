package fxVarasto;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import varasto.TaynnaException;
import varasto.Tuote;
import varasto.Varasto;
import varasto.Varastonkorjaus;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
 * @version 21.03.2020
 * Käyttöliittymän tapahtumanhallinta-luokka.
 */
public class VarastonhallintaGUIController implements Initializable {

    private String varastonNimi;

    @FXML
    private ComboBoxChooser<String> statusHaku;

    @FXML
    private Label varastonNimiPalkissa = new Label();
    @FXML
    private TextField haku;
    @FXML
    private ListChooser<Tuote> tuotteet;
    @FXML
    private TextField nimi;

    @FXML
    private TextField tuotenumero;

    @FXML
    private TextField arvo;

    @FXML
    private TextField kapasi;

    @FXML
    private TextField kollit;

    @FXML
    private TextField status;

    @FXML
    private StringGrid<Varastonkorjaus> korjaukset;

    @FXML
    private void handleHakeminen() {
        hae();
    }


    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }


    @FXML
    private void handleLisaaTuote() {
        uusiTuote();
    }


    @FXML
    void handleLopeta() {
        Platform.exit();
    }


    @FXML
    private void handleNayta() {
        naytaTuote();
    }
    

    @FXML
    private void handleMuokkaa() {
        muokkaa();
    }


    @FXML
    private void handlePoistaTuote() {
        poistaTuote();
    }
    
    
    @FXML
    private void handleTulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }


    @FXML
    private void handleTietoja() {
        ModalController.showModal(
                VarastonhallintaGUIController.class
                        .getResource("fxml-tiedostot/TietojaGUIView.fxml"),
                "Tietoja", null, "");
    }
    

    @FXML
    private void handleVarastonkorjaus() {
        uusiKorjaus();

    }

    // -------------------------------------------------------------------------------------------------

    private Varasto varasto;

    /**
     * Avaa varaston
     * @return true jos käyttäjä asetti nimen
     */
    public boolean avaa() {
        String varastonNimi2 = VarastonNimiController.kysyNimi(null,
                varastonNimi);
        if (varastonNimi2 == null) {
            return false;
        }
        tyhjaa();
        lueTiedosto(varastonNimi2);

        varastonNimiPalkissa.setText("Varasto: " + varastonNimi);
        return true;
    }


    /**
     * Muokkaa tuotetta
     */
    public void muokkaa() {
        Tuote tuote = tuotteet.getSelectedObject();

        String vastaus = ModalController.showModal(
                VarastonhallintaGUIController.class
                        .getResource("fxml-tiedostot/MuokkausGUIView.fxml"),
                "Muokkaa", null, "");
        if (vastaus.contentEquals(""))
            return;
        StringBuilder tiedot = new StringBuilder(vastaus);

        tuote.aseta(Mjonot.erota(tiedot, '|').trim(),
                Mjonot.erotaEx(tiedot, '|', 0), Mjonot.erotaEx(tiedot, '|', 0),
                tiedot.toString().trim(), 0);
        alustaLista();
        tyhjaa();
        tallenna(varastonNimi);

    }


    /**
     * Poistaa tuotteen varastosta
     */
    public void poistaTuote() {
        if (varasto.getTuotteita() < 1) {
            return;
        }
        String vastaus = ModalController.showModal(
                VarastonhallintaGUIController.class
                        .getResource("fxml-tiedostot/PoistaGUIView.fxml"),
                "Poista", null, "");

        if (vastaus == "ok") {
            Tuote tuote = tuotteet.getSelectedObject();
            varasto.poistaTuote(tuote);
            tallenna(varastonNimi);
            alustaLista();
            tyhjaa();
        }

    }


    /**
     * Hakee tuotteita listasta nimen perusteella
     */
    private void hae() {
        String haettava = haku.getText();
        String hakuehto2 = statusHaku.getSelectedText();

        tuotteet.clear();

        for (int i = 0; i < varasto.getTuotteita(); i++) {
            Tuote tuote = varasto.annaTuote(i);

            switch (hakuehto2) {
            case "Kaikki":
                if (tuote.getNimi().toLowerCase().startsWith(haettava.toLowerCase())) {
                    tuotteet.add(tuote.getNimi(), tuote);
                }
                break;
            case "Aktiivinen":
                if (tuote.getNimi().toLowerCase().startsWith(haettava.toLowerCase())
                        && tuote.getStatus().contains("Aktiivinen")) {
                    tuotteet.add(tuote.getNimi(), tuote);
                }
                break;
            case "Poistunut":
                if (tuote.getNimi().toLowerCase().startsWith(haettava.toLowerCase())
                        && tuote.getStatus().contains("Poistunut")) {
                    tuotteet.add(tuote.getNimi(), tuote);
                }
                break;

            default:
                break;
            }

        }

    }


    /**
     * Tyhjentää korjaukset ja tuotteen tiedot
     */
    public void tyhjaa() {
        korjaukset.clear();
        nimi.clear();
        kapasi.clear();
        kollit.clear();
        tuotenumero.clear();
        arvo.clear();
        status.clear();
    }


    private void lueTiedosto(String nimi2) {
        varastonNimi = nimi2;
        varasto.pyyhi();

        varasto.lue(varastonNimi);
        alustaLista();

    }


    private void tallenna(String uudenVarastonNimi) {
        varasto.tallenna(uudenVarastonNimi);
    }


    private void uusiTuote() {
        String tiedot = ModalController
                .showModal(
                        VarastonhallintaGUIController.class.getResource(
                                "fxml-tiedostot/LisaaGUIView.fxml"),
                        "Lisää", null, "");
        if (tiedot.contentEquals(""))
            return;
        StringBuilder vastaus = new StringBuilder(tiedot);
        Tuote tuote = new Tuote();
        tuote.aseta(Mjonot.erota(vastaus, '|').trim(),
                Mjonot.erotaEx(vastaus, '|', 0),
                Mjonot.erotaEx(vastaus, '|', 0), vastaus.toString().trim());
        try {
            varasto.lisaa(tuote);
        } catch (TaynnaException e) {
            Dialogs.showMessageDialog(e.getMessage());
            return;
        }
        alustaLista();
        tallenna(varastonNimi);
    }


    private void uusiKorjaus() {
        String tiedot = ModalController.showModal(
                VarastonhallintaGUIController.class.getResource(
                        "fxml-tiedostot/VarastonkorjausGUIView.fxml"),
                "Varastonkorjaus", null, "");

        if (tiedot.contentEquals(""))
            return;
        StringBuilder vastaus = new StringBuilder(tiedot);
        Tuote tuote = tuotteet.getSelectedObject();

        if (tuote == null)
            return;

        Varastonkorjaus korjaus = new Varastonkorjaus();
        korjaus.alusta(tuote.getTuotenumero(), Mjonot.erota(vastaus),
                Integer.valueOf(vastaus.toString()));

        varasto.lisaaKorjaus(korjaus);
        naytaTuote();
        tallenna(varastonNimi);
    }


    private void alustaLista() {
        tuotteet.clear();

        for (int i = 0; i < varasto.getTuotteita(); i++) {
            Tuote tuote = varasto.annaTuote(i);
            tuotteet.add(tuote.getNimi(), tuote);
        }
    }


    /**
     * Tyhjennetään tuotteiden lista, ja mennään naytaTuotteeseen jos uusi tuote valitaan.
     */
    private void alusta() {
        tuotteet.clear();
        tuotteet.addSelectionListener(e -> naytaTuote());
    }


    /**
     * Otetaan valitun tuotteen tiedot näkyville
     */
    private void naytaTuote() {
        Tuote tuote = tuotteet.getSelectedObject();

        if (tuote == null)
            return;

        nimi.setText(tuote.getNimi());
        tuotenumero.setText(String.valueOf(tuote.getTuotenumero()));
        arvo.setText(String.valueOf(tuote.getVarastoarvo()));
        kapasi.setText(String.valueOf(tuote.getVarastokapasiteetti()));
        kollit.setText(String.valueOf(tuote.getKollit()));
        status.setText(tuote.getStatus());

        korjaukset.clear();
        if (varasto.getVarastonkorjauksia() > 0) {
            for (int i = 0; i < varasto.getVarastonkorjauksia(); i++) {
                Varastonkorjaus korjaus = varasto.annaKorjaus(i);
                if (tuote.getTuotenumero() == korjaus.getTuotenumero()) {
                    String[] rivi = new String[] { korjaus.getPaiva(),
                            String.valueOf(korjaus.getTuotenumero()),
                            korjaus.getTapahtuma(),
                            String.valueOf(korjaus.getMuutos()) };

                    korjaukset.add(korjaus, rivi);

                }
            }
        }

    }


    /**
     * Asetetaan kontrollerin varastoviite kohdilleen.
     * @param varasto mihin viitataan
     */
    public void setVarasto(Varasto varasto) {
        this.varasto = varasto;
    }

}