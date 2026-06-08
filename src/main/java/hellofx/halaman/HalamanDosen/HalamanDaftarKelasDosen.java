package hellofx.halaman.HalamanDosen;

import java.util.ArrayList;

import hellofx.Database.KoneksiDB;
import hellofx.kelasData.Dosen;
import hellofx.kelasData.KelasAjar;
import hellofx.kelasData.Semester;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanDaftarKelasDosen {

    private Stage stage;
    private Dosen dosen;

    public HalamanDaftarKelasDosen(Stage stage, Dosen dosen) {
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

        VBox sideBar = createSideBar();
        VBox content = createContent();

        mainContent.getChildren().addAll(sideBar, content);
        HBox.setHgrow(content, Priority.ALWAYS);

        layout.getChildren().addAll(header, mainContent);

        return new Scene(layout, 1200, 750);
    }

    private VBox createContent() {
        VBox content = new VBox(20);
        content.setMaxWidth(Double.MAX_VALUE);
        content.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(content, Priority.ALWAYS);
        HBox.setHgrow(content, Priority.ALWAYS);
        content.setPadding(new Insets(35));
        content.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 18;"
                        + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 18, 0, 0, 4);");

        Label title = new Label("Daftar Kelas Ajar");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1E293B;");

        ComboBox<Semester> semesterBox = new ComboBox<>();
        semesterBox.setItems(KoneksiDB.getAllSemester());

        VBox daftarCard = new VBox(20);

        ScrollPane scrollPane = new ScrollPane(daftarCard);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-background: transparent;"
                        + "-fx-border-color: transparent;");

        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        semesterBox.setOnAction(e -> {
            if (semesterBox.getValue() != null) {
                loadKelasAjar(daftarCard, semesterBox.getValue().getIdSemester());
            }
        });

        if (!semesterBox.getItems().isEmpty()) {
            semesterBox.setValue(semesterBox.getItems().get(0));
            loadKelasAjar(daftarCard, semesterBox.getValue().getIdSemester());
        }

        content.getChildren().addAll(title, semesterBox, scrollPane);

        return content;
    }

    private VBox createCardKelas(KelasAjar kelas) {
        VBox card = new VBox(12);
        card.setPrefWidth(420);
        card.setMinHeight(220);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: #F8FAFC;"
                        + "-fx-background-radius: 14;"
                        + "-fx-border-color: #E2E8F0;"
                        + "-fx-border-radius: 14;");

        Label namaMK = new Label(kelas.getNamaMK());
        namaMK.setWrapText(true);
        namaMK.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #0F172A;");

        card.getChildren().addAll(
                namaMK,
                createInfoRow("SKS", String.valueOf(kelas.getJumlahSKS())),
                createInfoRow("Jadwal", capitalize(kelas.getHari()) + ", " + kelas.getWaktuMulai().substring(0, 5)),
                createInfoRow("Durasi", kelas.getDurasi() + " menit"),
                createInfoRow("Pertemuan", kelas.getJenisPertemuan() + " - " + kelas.getMetodePertemuan()));

        return card;
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 30));
        topBar.setMinHeight(68);
        topBar.setPrefHeight(68);
        topBar.setMaxHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("DAFTAR KELAS AJAR");
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

    private VBox createSideBar() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(10, 0, 10, 0));
        sidebar.setPrefWidth(120);
        sidebar.setAlignment(Pos.TOP_CENTER);

        Button tombolBeranda = tombolIcon("home (2).png", "Beranda");
        Button tombolProfil = tombolIcon("user (1).png", "Profil");
        Button tombolJadwal = tombolIcon("calendar.png", "Jadwal");
        Button tombolDaftarKelas = tombolIcon("clipboard.png", "Daftar Kelas");

        tombolDaftarKelas.setStyle(
                "-fx-pref-width: 100;"
                        + "-fx-pref-height: 100;"
                        + "-fx-background-color: #0F2D7A;"
                        + "-fx-background-radius: 12;"
                        + "-fx-border-color: #60A5FA;"
                        + "-fx-border-width: 7;"
                        + "-fx-border-radius: 12;");

        tombolBeranda.setOnAction(e -> {
            HalamanBerandaDosen beranda = new HalamanBerandaDosen(stage, dosen);
            stage.setScene(beranda.getScene());
            stage.setTitle("Beranda Dosen");
        });

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

        sidebar.getChildren().addAll(tombolBeranda, tombolProfil, tombolJadwal, tombolDaftarKelas);

        return sidebar;
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
                        + "-fx-text-fill: #ffffff;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 12;");
        return button;
    }

    private HBox createInfoRow(String labelText, String valueText) {
        HBox row = new HBox(20);

        Label label = new Label(labelText);
        label.setPrefWidth(100);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #475569;");

        Label value = new Label(valueText);
        value.setStyle("-fx-font-size: 14px; -fx-text-fill: #0F172A;");

        row.getChildren().addAll(label, value);
        return row;
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    private void loadKelasAjar(VBox daftarCard, int idSemester) {
        daftarCard.getChildren().clear();

        ArrayList<KelasAjar> daftarKelas = KoneksiDB.getKelasAjarByDosen(dosen.getNip(), idSemester);

        if (daftarKelas.isEmpty()) {

            Label kosong = new Label("Belum terdapat data kelas ajar untuk semester yang dipilih.");

            kosong.setWrapText(true);
            kosong.setAlignment(Pos.CENTER);

            VBox wrapper = new VBox(kosong);
            wrapper.setAlignment(Pos.CENTER);
            wrapper.setPadding(new Insets(50));

            daftarCard.getChildren().add(wrapper);
            return;
        }

        for (KelasAjar kelas : daftarKelas) {
            daftarCard.getChildren().add(createCardKelas(kelas));
        }
    }
}
