package hellofx.halaman;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HalamanJadwal {
    private Stage stage;
    private Mahasiswa mahasiswa;

    public HalamanJadwal(Stage stage, Mahasiswa mahasiswa) {
        this.stage = stage;
        this.mahasiswa = mahasiswa;
    }

    public Scene getScene() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #ffffff;");

        HBox header = createTopBar();

        VBox menu = new VBox();
        menu.setPadding(new Insets(10, 0, 10, 0));
        menu.setSpacing(25);
        menu.setPrefWidth(200);

        Button tombolProfil = tombolIcon("user (1).png", "Profil");

        Button tombolFrs = tombolIcon("google-docs (1).png", "FRS");

        Button tombolBeranda = tombolIcon("home (2).png", "Beranda");

        tombolProfil.setOnAction(e -> {
            HalamanProfil profil = new HalamanProfil(stage, mahasiswa);
            stage.setScene(profil.getScene());
            stage.setTitle("Profil");
        });

        tombolFrs.setOnAction(e -> {
            HalamanFRS frs = new HalamanFRS(stage, mahasiswa);
            stage.setScene(frs.getScene());
            stage.setTitle("FRS");
        });

        tombolBeranda.setOnAction(e -> {
            HalamanBeranda beranda = new HalamanBeranda(stage, mahasiswa);
            stage.setScene(beranda.getScene());
            stage.setTitle("Beranda");
        });

        menu.getChildren().addAll(tombolBeranda, tombolFrs, tombolProfil);

        VBox kolomSenin = kolomHari("Senin");
        VBox kolomSelasa = kolomHari("Selasa");
        VBox kolomRabu = kolomHari("Rabu");
        VBox kolomKamis = kolomHari("Kamis");
        VBox kolomJumat = kolomHari("Jumat");
        VBox kolomSabtu = kolomHari("Sabtu");

        HBox jadwal = new HBox();
        jadwal.setStyle("-fx-background-color : #ffffff");
        jadwal.setFillHeight(true);
        jadwal.setPadding(new Insets(0));
        VBox.setVgrow(jadwal, Priority.ALWAYS);
        HBox.setHgrow(jadwal, Priority.ALWAYS);

        jadwal.setSpacing(10);
        jadwal.setPadding(new Insets(0, 5, 10, 5));
        jadwal.getChildren().addAll(kolomSenin, kolomSelasa, kolomRabu, kolomKamis, kolomJumat, kolomSabtu);

        for (var kolom : jadwal.getChildren()) {
            HBox.setHgrow((VBox) kolom, Priority.ALWAYS);
        }

        VBox kontenJadwal = new VBox();
        kontenJadwal.setStyle("-fx-background-color : #ffffff");
        VBox.setVgrow(kontenJadwal, Priority.ALWAYS);
        HBox.setHgrow(kontenJadwal, Priority.ALWAYS);

        kontenJadwal.getChildren().addAll(jadwal);

        kolomSenin.getChildren().add(
                buatKotakJadwal("Bahasa Indonesia", "Ruangan 08.101", "Kelas C", "08:00", "11:00", "#AAAAAA"));

        kolomSenin.getChildren().add(
                buatKotakJadwal("Matematika Dasar", "Ruangan 10.109", "Kelas B", "12:00", "14:00", "#C040A0"));

        kolomKamis.getChildren().add(
                buatKotakJadwal("Pemrograman Kompetitif", "Ruangan 09.0.01", "Kelas A", "09:00", "11:00", "#00BCD4"));

        kolomJumat.getChildren().add(
                buatKotakJadwal("Dasar Pemrograman", "Ruangan 10.130", "Kelas C", "08:00", "11:00", "#8BC34A"));

        HBox bagianTengah = new HBox();
        bagianTengah.setStyle("-fx-background-color : #ffffff");
        bagianTengah.setPadding(new Insets(35, 0, 0, 0));
        VBox.setVgrow(bagianTengah, Priority.ALWAYS);
        bagianTengah.getChildren().addAll(menu, kontenJadwal);

        HBox bagianBawah = new HBox();
        bagianBawah.setPrefHeight(100);
        bagianBawah.setStyle("-fx-background-color : #ffffff");

        layout.getChildren().addAll(header, bagianTengah, bagianBawah);

        return new Scene(layout, 1200, 750);
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 16));
        topBar.setPrefHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("JADWAL");
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

    private VBox kolomHari(String hari) {
        Label label = new Label(hari);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(6, 3, 6, 3));
        label.setPrefWidth(100);
        label.setStyle(
                "-fx-background-color: #1E3A8A; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 12; -fx-background-radius: 16;");

        VBox kolom = new VBox(5);
        kolom.setPadding(new Insets(8, 4, 0, 4));
        kolom.setAlignment(Pos.TOP_CENTER);
        kolom.setStyle("-fx-border-color: transparent #1E3A8A transparent transparent; -fx-border-width: 0 1 0 0;");
        kolom.getChildren().addAll(label);

        return kolom;
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

    private VBox buatKotakJadwal(String namaMK, String ruangan, String kelas, String jamMulai, String jamSelesai,
            String warna) {
        Label lblJam = new Label(jamMulai + " - " + jamSelesai);
        lblJam.setStyle("-fx-font-size: 10px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label lblNama = new Label(namaMK);
        lblNama.setWrapText(true);
        lblNama.setStyle("-fx-font-size: 11px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label lblRuangan = new Label(ruangan);
        lblRuangan.setStyle("-fx-font-size: 10px; -fx-text-fill: white;");

        Label lblKelas = new Label(kelas);
        lblKelas.setStyle("-fx-font-size: 10px; -fx-text-fill: white;");

        VBox kotak = new VBox(3);
        kotak.setPadding(new Insets(6));
        kotak.setMaxWidth(Double.MAX_VALUE);
        kotak.setStyle(
                "-fx-background-color: " + warna + ";" +
                        "-fx-background-radius: 6;");
        kotak.getChildren().addAll(lblJam, lblNama, lblRuangan, lblKelas);

        return kotak;
    }
}
