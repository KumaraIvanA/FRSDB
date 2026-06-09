package FRS.halaman.HalamanMahasiswa;

import FRS.halaman.HalamanLogin;
import FRS.kelasData.Mahasiswa;
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

public class HalamanBeranda {
    private Stage stage;
    private Mahasiswa mahasiswa;

    public HalamanBeranda(Stage stage, Mahasiswa mahasiswa) {
        this.stage = stage;
        this.mahasiswa = mahasiswa;
    }

    public Scene getScene() {
        VBox layout = new VBox();

        HBox header = createTopBar();
        
        Label welcome = new Label("SELAMAT DATANG DI\nPORTAL MAHASISWA");
        welcome.setStyle(
                "-fx-font-size : 20; -fx-font-weight : bold; -fx-text-fill : #0B20A7; -fx-text-alignment : center;");
        welcome.setAlignment(Pos.CENTER);

        Button tombolProfil = tombolIcon("user (1).png", "Profil");

        Button tombolFrs = tombolIcon("google-docs (1).png", "FRS");

        Button tombolJadwal = tombolIcon("calendar.png", "Jadwal");

        Button tombolLogout = tombolIcon("logout.png", "Logout");

        tombolProfil.setOnAction(e -> {
            HalamanProfil profil = new HalamanProfil(stage, mahasiswa);
            stage.setScene(profil.getScene());
            stage.setTitle("Profil");
        });

        tombolJadwal.setOnAction(e -> {
            HalamanJadwal jadwal = new HalamanJadwal(stage, mahasiswa);
            stage.setScene(jadwal.getScene());
            stage.setTitle("Jadwal");
        });

        tombolFrs.setOnAction(e -> {
            HalamanFRS frs = new HalamanFRS(stage, mahasiswa);
            stage.setScene(frs.getScene());
            stage.setTitle("FRS");
        });

        tombolLogout.setOnAction(e -> {
            HalamanLogin login = new HalamanLogin(stage);
            stage.setScene(login.getScene());
            stage.setTitle("Login");
        });

        HBox tombolMenu = new HBox(50);
        tombolMenu.setAlignment(Pos.CENTER);
        tombolMenu.getChildren().addAll(tombolProfil, tombolJadwal, tombolFrs, tombolLogout);

        VBox bagianMenu = new VBox(30);
        bagianMenu.setAlignment(Pos.CENTER);
        bagianMenu.setPadding(new Insets(40));
        bagianMenu.getChildren().addAll(welcome, tombolMenu);
        VBox.setVgrow(bagianMenu, Priority.ALWAYS);
        HBox.setHgrow(bagianMenu, Priority.ALWAYS);

        // Bagian bawah
        Label namaUniv = new Label("Unirvesitas Jaya Jaya");
        namaUniv.setStyle("-fx-font-size : 15px; -fx-text-fill : #0B20A7;");

        HBox bagianBawah = new HBox();
        bagianBawah.setPrefHeight(100);
        bagianBawah.setAlignment(Pos.BOTTOM_RIGHT);
        bagianBawah.getChildren().addAll(namaUniv);

        layout.getChildren().addAll(header, bagianMenu, bagianBawah);

        return new Scene(layout, 1200, 750);
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 16));
        topBar.setPrefHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("BERANDA");
        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

       
        ImageView profile = new ImageView(new Image(getClass().getResourceAsStream("/Gambar/user (2).png")));
        profile.setFitWidth(28);
        profile.setFitHeight(28);
        profile.setPreserveRatio(true);
        topBar.getChildren().addAll(title, spacer, profile);

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
}
