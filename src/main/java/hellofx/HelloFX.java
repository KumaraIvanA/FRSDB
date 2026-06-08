package hellofx;

import hellofx.halaman.HalamanLogin;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloFX extends Application {
    @Override
    public void start(Stage stage) {
        HalamanLogin login = new HalamanLogin(stage);
        stage.setScene(login.getScene());
        stage.setTitle("FRS App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
