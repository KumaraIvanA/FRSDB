package hellofx.halaman.HalamanMahasiswa;

import hellofx.kelasData.Mahasiswa;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanProfil {
    private Stage stage;
    private Mahasiswa mahasiswa;

    public HalamanProfil(Stage stage, Mahasiswa mahasiswa) {
        this.stage = stage;
        this.mahasiswa = mahasiswa;
    }

    public Scene getScene() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #ffffff;");

        HBox header = createTopBar();

        VBox menu = new VBox();
        menu.setPadding(new Insets(30, 0, 10, 0));
        menu.setSpacing(25);
        menu.setPrefWidth(100);

        Button tombolBeranda = tombolIcon("home (2).png", "Beranda");
        Button tombolFrs = tombolIcon("google-docs (1).png", "FRS");
        Button tombolJadwal = tombolIcon("calendar.png", "Jadwal");

        tombolBeranda.setOnAction(e -> {
            HalamanBeranda beranda = new HalamanBeranda(stage, mahasiswa);
            stage.setScene(beranda.getScene());
            stage.setTitle("Beranda");
        });

        tombolFrs.setOnAction(e -> {
            HalamanFRS frs = new HalamanFRS(stage, mahasiswa);
            stage.setScene(frs.getScene());
            stage.setTitle("FRS");
        });

        tombolJadwal.setOnAction(e -> {
            HalamanJadwal jadwal = new HalamanJadwal(stage, mahasiswa);
            stage.setScene(jadwal.getScene());
            stage.setTitle("Jadwal");

        });

        menu.getChildren().addAll(tombolBeranda, tombolFrs, tombolJadwal);

        VBox profil = new VBox();
        profil.setSpacing(15);
        profil.setAlignment(Pos.TOP_CENTER);
        profil.setPadding(new Insets(100, 40, 30, 40));
        HBox.setHgrow(profil, Priority.ALWAYS);

        ImageView fotoProfil = new ImageView(new Image(getClass().getResourceAsStream("/Gambar/user (2).png")));
        fotoProfil.setFitWidth(200);
        fotoProfil.setFitHeight(200);
        fotoProfil.setPreserveRatio(true);

        Label nama = new Label("Nama : " + mahasiswa.getNama());
        nama.setStyle(setStyle());
        Label npm = new Label("NPM : " + mahasiswa.getNPM());
        npm.setStyle(setStyle());

        Label jurusan = new Label("Jurusan : " + mahasiswa.getNamaJurusan());
        jurusan.setStyle(setStyle());

        profil.getChildren().addAll(fotoProfil, nama, npm, jurusan);

        HBox bagianTengah = new HBox();
        bagianTengah.getChildren().addAll(menu, profil);

        layout.getChildren().addAll(header, bagianTengah);

        return new Scene(layout, 1200, 750);
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 16));
        topBar.setPrefHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("PROFIL");
        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView notif = new ImageView(new Image(getClass().getResourceAsStream("/Gambar/notification.png")));
        notif.setFitWidth(28);
        notif.setFitHeight(28);
        notif.setPreserveRatio(true);

        ImageView profile = new ImageView(new Image(getClass().getResourceAsStream("/Gambar/user (2).png")));
        profile.setFitWidth(28);
        profile.setFitHeight(28);
        profile.setPreserveRatio(true);
        topBar.getChildren().addAll(title, spacer, notif, profile);

        return topBar;
    }

    private Button tombolIcon(String pathIcon, String teks) {
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/Gambar/" + pathIcon)));

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

    private String setStyle() {
        return "-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: black;";
    }
}
