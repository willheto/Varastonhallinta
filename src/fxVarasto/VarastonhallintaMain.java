package fxVarasto;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import varasto.Varasto;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 * @author henri
 * @version 21.03.2020
 * Pääohjelma varastonhallinnalle
 */
public class VarastonhallintaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource(
                    "fxml-tiedostot/VarastonhallintaGUIView.fxml"));
            final Pane root = ldr.load();
            final VarastonhallintaGUIController varastoCtrl = (VarastonhallintaGUIController) ldr
                    .getController();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass()
                    .getResource("varastonhallinta.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Varastonhallinta");

            Varasto varasto = new Varasto();
            varastoCtrl.setVarasto(varasto);

            primaryStage.show();
            if (!varastoCtrl.avaa())
                Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}