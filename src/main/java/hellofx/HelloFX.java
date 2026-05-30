package hellofx;

import hellofx.halaman.HalamanBeranda;
import hellofx.halaman.HalamanJadwal;
import hellofx.halaman.halamanLogin;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        halamanLogin login = new halamanLogin(stage);
        HalamanJadwal jadwal = new HalamanJadwal(stage);
        HalamanBeranda beranda = new HalamanBeranda(stage);
        stage.setScene(login.getScene());
        stage.setTitle("FRS App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}