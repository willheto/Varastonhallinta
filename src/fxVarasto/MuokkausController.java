package fxVarasto;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author henri
 * @version 27 Feb 2020
 * Tapahtumanhallinta muokkaus-näkymään.
 */
public class MuokkausController {

    @FXML
    void handleOk() {
        Platform.exit();
    }
    
}
