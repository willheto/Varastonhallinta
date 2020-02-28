package fxVarasto;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import varasto.TaynnaException;
import varasto.Tuote;
import varasto.Varasto;

/**
 * @author henri
 * @version 27.2.2020
 * Käyttöliittymän tapahtumanhallinta-luokka.
 */
public class VarastonhallintaGUIController implements Initializable {
    
    private String varastonNimi = "Kuopio";
    
    @FXML private TextField haku;
    @FXML private ListChooser<Tuote> tuotteet;
    @FXML private TextArea valiaikainenTieto;
    
    
    @FXML private void handleHakeminen() {
        Dialogs.showMessageDialog("Ei osata hakea.");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    @FXML boolean handleAvaa() {
        ModalController.showModal(VarastonhallintaGUIController.class.getResource("fxml-tiedostot/NimenKyselyGUIView.fxml"), "Avaa", null, "");
        return true;
    }

    @FXML
    private void handleLisaaTuote(ActionEvent event) {
        ModalController.showModal(VarastonhallintaGUIController.class.getResource("fxml-tiedostot/LisaaGUIView.fxml"), "Lisää", null, "");
        uusiTuote();
    }

    @FXML
    private void handleLopeta(ActionEvent event) {
        Dialogs.showMessageDialog("Tallennetaan ja lopetetaan...");
        Platform.exit();
    }
    
    @FXML
    private void handleNayta(ContextMenuEvent event) {
        naytaTuote();
    }

    @FXML
    private void handleMuokkaa(ActionEvent event) {
        ModalController.showModal(VarastonhallintaGUIController.class.getResource("fxml-tiedostot/MuokkausGUIView.fxml"), "Muokkaa", null, "");
    }

    @FXML
    private void handlePoistaTuote(ActionEvent event) {
        Dialogs.showMessageDialog("Ei osata vielä postaa tuotetta");
    }

    @FXML
    private void handleTallenna(ActionEvent event) {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa");
    }

    @FXML
    private void handleTietoja(ActionEvent event) {
        ModalController.showModal(VarastonhallintaGUIController.class.getResource("fxml-tiedostot/TietojaGUIView.fxml"), "Tietoja", null, "");
    }

    @FXML
    private void handleTulosta(ActionEvent event) {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }

    @FXML
    private void handleVarastonkorjaus(ActionEvent event) {
        ModalController.showModal(VarastonhallintaGUIController.class.getResource("fxml-tiedostot/VarastonkorjausGUIView.fxml"), "Varastonkorjaus", null, "");
    }
    
    //-------------------------------------------------------------------------------------------------
    
    private Varasto varasto;
    
    
    private void uusiTuote() {
        Tuote tuote = new Tuote();
        tuote.aseta();
        try {
            varasto.lisaa(tuote);
        } catch (TaynnaException e) {
            Dialogs.showMessageDialog(e.getMessage());
            return;
        }        
        alustaLista();
    }
    
    
    private void alustaLista() {
        tuotteet.clear();
        
        for (int i = 0; i < varasto.getTuotteita(); i++) {
            Tuote tuote = varasto.annaTuote(i);
            tuotteet.add(tuote.getNimi() + tuote.getTuotenumero(), tuote);
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
        
        if(tuote == null) return;
        
        valiaikainenTieto.setText("");
        PrintStream os = TextAreaOutputStream.getTextPrintStream(valiaikainenTieto);
        tuote.tulostaTiedot(os);
    }

    /**
     * Asetetaan kontrollerin varastoviite kohdilleen.
     * @param varasto mihin viitataan
     */
    public void setVarasto(Varasto varasto) {
        this.varasto = varasto;
        
    }

}