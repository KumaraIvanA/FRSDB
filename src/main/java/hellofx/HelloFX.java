package hellofx;

import hellofx.halaman.HalamanLogin;
import hellofx.halaman.HalamanDosen.HalamanBerandaDosen;
import hellofx.halaman.HalamanMahasiswa.HalamanBeranda;
import hellofx.halaman.HalamanMahasiswa.HalamanJadwal;
import hellofx.halaman.HalamanMahasiswa.HalamanProfil;
import hellofx.halaman.HalamanLogin;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloFX extends Application {
    @Override
    public void start(Stage stage) {
        HalamanLogin login = new HalamanLogin(stage);
        // HalamanBeranda berandaDosen = new HalamanBeranda(stage);
        // stage.setScene(berandaDosen.getScene());
        stage.setScene(login.getScene());
		stage.setTitle("FRS App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
