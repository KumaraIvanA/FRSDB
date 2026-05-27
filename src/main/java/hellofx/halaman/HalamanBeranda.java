package hellofx.halaman;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanBeranda {
    private Stage stage;

    public HalamanBeranda(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox layout = new VBox();

        Label label = new Label("Ini Halaman Beranda");

        layout.getChildren().addAll(label);

        return new Scene(layout, 800, 500);
    }
    

}
