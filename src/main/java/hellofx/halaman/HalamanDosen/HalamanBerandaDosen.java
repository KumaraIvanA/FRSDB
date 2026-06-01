package hellofx.halaman.HalamanDosen;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanBerandaDosen {
    private Stage stage;

    public HalamanBerandaDosen(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox layout = new VBox();

        HBox header = new HBox();
        header.setPrefHeight(50);
        header.setStyle("-fx-background-color: #1E3A8A;");
        header.setPadding(new Insets(10, 15, 10, 15));

        Label label = new Label("BERANDA");
        label.setStyle("-fx-text-fill : #FFFFFF; -fx-font-size : 25; -fx-font-weight : 700;");
        label.setLayoutY(25);
        header.getChildren().add(label);

        Label welcome = new Label("SELAMAT DATANG DI\nPORTAL DOSEN");
        welcome.setStyle(
                "-fx-font-size : 20; -fx-font-weight : bold; -fx-text-fill : #0B20A7; -fx-text-alignment : center;");
        welcome.setAlignment(Pos.CENTER);

        Button tombolProfil = tombolIcon("calendar.png", "Profil");
        Button tombolJadwal = tombolIcon("calendar.png", "Jadwal");
        Button tombolDaftarKelas = tombolIcon("calendar.png", "Daftar Kelas");

        tombolProfil.setOnAction(e -> {
            HalamanProfilDosen profil = new HalamanProfilDosen(stage);
            stage.setScene(profil.getScene());
            stage.setTitle("FRS");
        });

        tombolJadwal.setOnAction(e -> {
            HalamanJadwalDosen jadwal = new HalamanJadwalDosen(stage);
            stage.setScene(jadwal.getScene());
            stage.setTitle("FRS");

        });

        tombolDaftarKelas.setOnAction(e -> {
            HalamanDaftarKelas daftarKelas = new HalamanDaftarKelas(stage);
            stage.setScene(daftarKelas.getScene());
            stage.setTitle("FRS");
        });

        HBox tombolMenu = new HBox(50);
        tombolMenu.setAlignment(Pos.CENTER);
        tombolMenu.getChildren().addAll(tombolProfil, tombolJadwal, tombolDaftarKelas);

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
