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

public class HalamanProfilDosen {

    private Stage stage;
    private Dosen dosen;

    public HalamanProfilDosen(Stage stage, Dosen dosen) {
        this.stage = stage;
        this.dosen = dosen;
    }

    public Scene getScene() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #F7F9FC;");

        HBox header = createTopBar();

        HBox mainContent = new HBox();
        mainContent.setPadding(new Insets(25, 35, 25, 0));
        mainContent.setSpacing(30);
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        VBox sideMenu = createSideMenu();
        VBox profileContent = createProfileContent();

        mainContent.getChildren().addAll(sideMenu, profileContent);
        HBox.setHgrow(profileContent, Priority.ALWAYS);

        layout.getChildren().addAll(header, mainContent);

        return new Scene(layout, 1200, 750);
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

        Label nama = new Label(dosen.getNama());
        nama.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #1E293B;");

        Label role = new Label("Dosen Universitas Jaya Jaya");
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
                createInfoRow("NIP", dosen.getNip()),
                createInfoRow("Nama", dosen.getNama()),
                createInfoRow("Email", dosen.getEmail()),
                createInfoRow("Jurusan", dosen.getNamaJurusan()));

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

    private VBox createSideMenu() {
        VBox menu = new VBox(20);
        menu.setPadding(new Insets(10, 0, 10, 0));
        menu.setPrefWidth(120);
        menu.setAlignment(Pos.TOP_CENTER);

        Button tombolBeranda = tombolIcon("home (2).png", "Beranda");
        Button tombolJadwal = tombolIcon("calendar.png", "Jadwal");
        Button tombolProfil = tombolIcon("user (1).png", "Profil");
        Button tombolDaftarKelas = tombolIcon("clipboard.png", "Daftar Kelas");

        tombolProfil.setStyle(
                "-fx-pref-width: 100;"
                        + "-fx-pref-height: 100;"
                        + "-fx-background-color: #0F2D7A;"
                        + "-fx-background-radius: 12;"
                        + "-fx-border-color: #60A5FA;"
                        + "-fx-border-width: 7;"
                        + "-fx-border-radius: 12;");

        // Nanti aktifkan kalau class-nya sudah ada
        tombolBeranda.setOnAction(e -> {
            HalamanBerandaDosen beranda = new HalamanBerandaDosen(stage, dosen);
            stage.setScene(beranda.getScene());
            stage.setTitle("Beranda Dosen");
        });

        tombolJadwal.setOnAction(e -> {
            HalamanJadwalDosen jadwal = new HalamanJadwalDosen(stage, dosen);
            stage.setScene(jadwal.getScene());
            stage.setTitle("Jadwal Dosen");
        });

        tombolDaftarKelas.setOnAction(e -> {
            HalamanDaftarKelas daftarKelas = new HalamanDaftarKelas(stage, dosen);
            stage.setScene(daftarKelas.getScene());
            stage.setTitle("FRS");
        });

        menu.getChildren().addAll(tombolBeranda, tombolJadwal,tombolDaftarKelas,  tombolProfil);
        return menu;
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 30));
        topBar.setPrefHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("PROFIL DOSEN");
        title.setStyle(
                "-fx-font-size: 26px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: white;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView notif = new ImageView(new Image(getClass().getResourceAsStream("/Gambar/notification.png")));
        notif.setFitWidth(28);
        notif.setFitHeight(28);
        notif.setPreserveRatio(true);

        ImageView profile = new ImageView(new Image(getClass().getResourceAsStream("/Gambar/user (2).png")));
        profile.setFitWidth(32);
        profile.setFitHeight(32);
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

        VBox isi = new VBox(6);
        isi.setAlignment(Pos.CENTER);
        isi.getChildren().addAll(image, label);

        Button button = new Button();
        button.setGraphic(isi);
        button.setCursor(Cursor.HAND);
        button.setStyle(
                "-fx-pref-width: 100;"
                        + "-fx-pref-height: 100;"
                        + "-fx-background-color: #1E3A8A;"
                        + "-fx-text-fill: white;"
                        + "-fx-background-radius: 12;");

        return button;
    }
}
