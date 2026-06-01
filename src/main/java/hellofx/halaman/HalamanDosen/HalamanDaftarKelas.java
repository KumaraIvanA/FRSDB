package hellofx.halaman.HalamanDosen;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanDaftarKelas {
    private Stage stage;

    public HalamanDaftarKelas(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox layout = new VBox();

        return new Scene(layout, 1200, 750);
    }
}
