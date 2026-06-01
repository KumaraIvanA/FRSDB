package hellofx.halaman.HalamanDosen;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanProfilDosen {
    private Stage stage;

    public HalamanProfilDosen(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox layout = new VBox();

        return new Scene(layout, 1200, 750);
    }
}
