package FRS.halaman.Common;

import javafx.stage.Stage;
import FRS.halaman.HalamanMahasiswa.HalamanBeranda;
import FRS.halaman.HalamanMahasiswa.HalamanFRS;
import FRS.halaman.HalamanMahasiswa.HalamanJadwal;
import FRS.halaman.HalamanMahasiswa.HalamanProfil;
import FRS.kelasData.Mahasiswa;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Sidebar {
	public static VBox create(Object obj, Stage stage, Mahasiswa mahasiswa) {
		VBox sidebar = new VBox(12);
		sidebar.setAlignment(Pos.TOP_CENTER);
		sidebar.setPrefWidth(105);

		Button tombolBeranda = tombolIcon(obj, "home (2).png", "Beranda");
		Button tombolFrs = tombolIcon(obj, "google-docs (1).png", "FRS");
		Button tombolProfil = tombolIcon(obj, "user (1).png", "Profil");
		Button tombolJadwal = tombolIcon(obj, "calendar.png", "Jadwal");

		tombolProfil.setOnAction(e -> {
			HalamanProfil profil = new HalamanProfil(stage, mahasiswa);
			stage.setScene(profil.getScene());
			stage.setTitle("FRS");
		});

		tombolFrs.setOnAction(e -> {
			HalamanFRS frs = new HalamanFRS(stage, mahasiswa);
			stage.setScene(frs.getScene());
			stage.setTitle("FRS");
		});

		tombolBeranda.setOnAction(e -> {
			HalamanBeranda beranda = new HalamanBeranda(stage, mahasiswa);
			stage.setScene(beranda.getScene());
			stage.setTitle("FRS");
		});

		tombolJadwal.setOnAction(e -> {
			HalamanJadwal jadwal = new HalamanJadwal(stage, mahasiswa);
			stage.setScene(jadwal.getScene());
			stage.setTitle("FRS");
		});

		sidebar.getChildren().addAll(tombolBeranda, tombolProfil, tombolFrs, tombolJadwal);

		return sidebar;
	}

    private static Button tombolIcon(Object obj, String pathIcon, String teks) {
        ImageView image = new ImageView(new Image(obj.getClass().getResourceAsStream("/Gambar/" + pathIcon)));
        
        image.setFitWidth(35);
        image.setFitHeight(35);
        image.setPreserveRatio(true);

        Label label = new Label(teks);
        label.setStyle("-fx-font-size: 11px; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        VBox isi = new VBox(5);
        isi.setAlignment(Pos.CENTER);
        isi.getChildren().addAll(image, label);

        Button button = new Button();
        button.setGraphic(isi);
        button.setCursor(Cursor.HAND);
        button.setStyle(
            "-fx-pref-width : 100; -fx-pref-height : 100; -fx-background-color : #1E3A8A; -fx-text-fill : #ffffff; -fx-font-weight : bold; -fx-background-radius: 10;");

        return button;
    }
}
