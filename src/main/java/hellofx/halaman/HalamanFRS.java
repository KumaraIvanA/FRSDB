package hellofx.halaman;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanFRS {
    private Stage stage;

    public HalamanFRS(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox tes = new VBox();

        Label label = new Label("halaman FRS");

        tes.getChildren().addAll(label);

        return new Scene(tes, 800, 500);
    }
}
