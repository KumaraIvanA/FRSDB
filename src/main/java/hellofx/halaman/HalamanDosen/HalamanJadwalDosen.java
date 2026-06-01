package hellofx.halaman.HalamanDosen;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanJadwalDosen {
    private Stage stage;

    public HalamanJadwalDosen(Stage stage) {
        this.stage = new Stage();
    }

    public Scene getScene() {
        VBox layout = new VBox();

        return new Scene(layout, 1200, 750);
    }
}
