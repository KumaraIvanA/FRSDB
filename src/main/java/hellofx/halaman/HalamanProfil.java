package hellofx.halaman;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanProfil {
    private Stage stage;

    public HalamanProfil(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox tes = new VBox();

        Label label = new Label("Halaman Profil");

        tes.getChildren().addAll(label);

        return new Scene(tes, 800, 500);
    }
}
