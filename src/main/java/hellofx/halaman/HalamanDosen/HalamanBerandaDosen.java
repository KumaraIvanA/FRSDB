package hellofx.halaman.HalamanDosen;

import hellofx.halaman.HalamanLogin;
import hellofx.kelasData.Dosen;
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

public class HalamanBerandaDosen {
    private Stage stage;
    private Dosen dosen;

    public HalamanBerandaDosen(Stage stage, Dosen dosen) {
        this.stage = stage;
        this.dosen = dosen;
    }

    public Scene getScene() {
        VBox layout = new VBox();
        HBox header = createTopBar();
        Label welcome = new Label("SELAMAT DATANG, " + dosen.getNama().toUpperCase() + " \nDI PORTAL DOSEN");
        welcome.setStyle(
                "-fx-font-size : 20; -fx-font-weight : bold; -fx-text-fill : #0B20A7; -fx-text-alignment : center;");
        welcome.setAlignment(Pos.CENTER);

        Button tombolProfil = tombolIcon("user (1).png", "Profil");
        Button tombolJadwal = tombolIcon("calendar.png", "Jadwal");
        Button tombolDaftarKelas = tombolIcon("clipboard.png", "Daftar Kelas");
        Button tombolLogout = tombolIcon("logout.png", "Logout");

        tombolProfil.setOnAction(e -> {
            HalamanProfilDosen profil = new HalamanProfilDosen(stage, dosen);
            stage.setScene(profil.getScene());
            stage.setTitle("Profil Dosen");
        });

        tombolJadwal.setOnAction(e -> {
            HalamanJadwalDosen jadwal = new HalamanJadwalDosen(stage, dosen);
            stage.setScene(jadwal.getScene());
            stage.setTitle("Jadwal Dosen");

        });

        tombolDaftarKelas.setOnAction(e -> {
            HalamanDaftarKelasDosen daftarKelas = new HalamanDaftarKelasDosen(stage, dosen);
            stage.setScene(daftarKelas.getScene());
            stage.setTitle("Daftar Kelas Dosen");
        });

        tombolLogout.setOnAction(e -> {
            HalamanLogin login = new HalamanLogin(stage);
            stage.setScene(login.getScene());
            stage.setTitle("Login");
        });

        HBox tombolMenu = new HBox(50);
        tombolMenu.setAlignment(Pos.CENTER);
        tombolMenu.getChildren().addAll(tombolProfil, tombolJadwal, tombolDaftarKelas, tombolLogout);

        VBox bagianMenu = new VBox(30);
        bagianMenu.setAlignment(Pos.CENTER);
        bagianMenu.setPadding(new Insets(40));
        bagianMenu.getChildren().addAll(welcome, tombolMenu);
        VBox.setVgrow(bagianMenu, Priority.ALWAYS);

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
        topBar.setPadding(new Insets(0, 35, 0, 30));
        topBar.setMinHeight(68);
        topBar.setPrefHeight(68);
        topBar.setMaxHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("BERANDA DOSEN");
        title.setStyle(
                "-fx-font-size: 26px;" +
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

}
