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

        VBox profil = createProfileContent();
        HBox.setHgrow(profil, Priority.ALWAYS);

        HBox bagianTengah = new HBox();
        bagianTengah.getChildren().addAll(menu, profil);
        bagianTengah.setPadding(new Insets(25, 35, 25, 0));
        bagianTengah.setSpacing(30);
        VBox.setVgrow(bagianTengah, Priority.ALWAYS);

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

    private VBox createProfileContent() {
        VBox container = new VBox(25);
        container.setPadding(new Insets(35));
        container.setAlignment(Pos.TOP_CENTER);
        container.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 18;"
                        + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 18, 0, 0, 4);");

        ImageView avatar = new ImageView(new Image(getClass().getResourceAsStream("/Gambar/user (2).png")));
        avatar.setFitWidth(120);
        avatar.setFitHeight(120);
        avatar.setPreserveRatio(true);

        Label nama = new Label(mahasiswa.getNama());
        nama.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #1E293B;");

        Label role = new Label("Mahasiswa Universitas Jaya Jaya");
        role.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B;");

        VBox identityBox = new VBox(5);
        identityBox.setAlignment(Pos.CENTER);
        identityBox.getChildren().addAll(nama, role);

        VBox dataCard = new VBox(18);
        dataCard.setPadding(new Insets(25));
        dataCard.setMaxWidth(650);
        dataCard.setStyle(
                "-fx-background-color: #F8FAFC;"
                        + "-fx-background-radius: 14;"
                        + "-fx-border-color: #E2E8F0;"
                        + "-fx-border-radius: 14;");

        dataCard.getChildren().addAll(
                createInfoRow("NPM", mahasiswa.getNPM()),
                createInfoRow("Nama", mahasiswa.getNama()),
                createInfoRow("Email", mahasiswa.getEmail()),
                createInfoRow("Jurusan", String.valueOf(mahasiswa.getIdJurusan())));

        container.getChildren().addAll(avatar, identityBox, dataCard);

        return container;
    }

    private HBox createInfoRow(String labelText, String valueText) {
        HBox row = new HBox(25);
        row.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(labelText);
        label.setPrefWidth(120);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #475569;");

        Label value = new Label(valueText);
        value.setStyle("-fx-font-size: 14px; -fx-text-fill: #0F172A;");

        row.getChildren().addAll(label, value);
        return row;
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
