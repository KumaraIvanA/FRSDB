package hellofx.halaman.HalamanDosen;

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

public class HalamanJadwalDosen {
    private Stage stage;
    private Dosen dosen;

    public HalamanJadwalDosen(Stage stage, Dosen dosen) {
        this.stage = stage;
        this.dosen = dosen;
    }

    public Scene getScene() {
        VBox layout = new VBox();

        HBox header = createTopBar();

        Button tombolBeranda = tombolIcon("home (2).png", "Beranda");
        Button tombolProfil = tombolIcon("user (1).png", "Profil");
        Button tombolDaftarKelas = tombolIcon("clipboard.png", "Daftar Kelas");

        tombolBeranda.setOnAction(e -> {
            HalamanBerandaDosen berandaDosen = new HalamanBerandaDosen(stage, dosen);
            stage.setScene(berandaDosen.getScene());
            stage.setTitle("FRS");
        });

        tombolProfil.setOnAction(e -> {
            HalamanProfilDosen profil = new HalamanProfilDosen(stage, dosen);
            stage.setScene(profil.getScene());
            stage.setTitle("FRS");
        });

        tombolDaftarKelas.setOnAction(e -> {
            HalamanDaftarKelas daftarKelas = new HalamanDaftarKelas(stage, dosen);
            stage.setScene(daftarKelas.getScene());
            stage.setTitle("FRS");
        });

        VBox menu = new VBox();
        menu.setPadding(new Insets(10, 0, 10, 0));
        menu.setSpacing(25);
        menu.setPrefWidth(200);

        menu.getChildren().addAll(tombolBeranda, tombolProfil, tombolDaftarKelas);

        HBox bagianTengah = new HBox();
        bagianTengah.setStyle("-fx-background-color : #ffffff");
        bagianTengah.setPadding(new Insets(35, 0, 0, 0));
        VBox.setVgrow(bagianTengah, Priority.ALWAYS);
        bagianTengah.getChildren().addAll(menu);

        layout.getChildren().addAll(header, bagianTengah);

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
