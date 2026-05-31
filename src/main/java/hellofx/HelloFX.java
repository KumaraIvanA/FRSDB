package hellofx;

import hellofx.halaman.HalamanBeranda;
import hellofx.halaman.HalamanJadwal;
import hellofx.halaman.HalamanLogin;
import hellofx.halaman.HalamanLogin;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        HalamanLogin login = new HalamanLogin(stage);
        HalamanJadwal jadwal = new HalamanJadwal(stage);
        HalamanBeranda beranda = new HalamanBeranda(stage);
        stage.setScene(jadwal.getScene());
        stage.setTitle("FRS App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}