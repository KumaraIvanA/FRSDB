package hellofx.halaman;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanJadwal {
    private Stage stage;

    public HalamanJadwal(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox tes = new VBox();

        Label label = new Label("Halaman Jadwal");

        tes.getChildren().addAll(label);

        return new Scene(tes, 800, 500);
    }
}
