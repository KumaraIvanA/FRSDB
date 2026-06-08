package hellofx.halaman.HalamanDosen;

import hellofx.Database.KoneksiDB;
import hellofx.kelasData.Dosen;
import hellofx.kelasData.MataKuliah;
import hellofx.kelasData.Semester;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class HalamanKelolaMataKuliahDosen {

    private Stage stage;
    private Dosen dosen;

    private ComboBox<MataKuliah> mataKuliahBox;
    private VBox semesterListBox;
    private Map<Integer, CheckBox> semesterCheckBoxMap = new HashMap<>();

    public HalamanKelolaMataKuliahDosen(Stage stage, Dosen dosen) {
        this.stage = stage;
        this.dosen = dosen;
    }

    public Scene getScene() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #F7F9FC;");

        layout.getChildren().addAll(createTopBar(), createCenter());

        return new Scene(layout, 1200, 750);
    }

    private HBox createCenter() {
        HBox center = new HBox();
        center.setPadding(new Insets(25, 35, 25, 0));
        center.setSpacing(30);
        VBox.setVgrow(center, Priority.ALWAYS);

        VBox content = new VBox(24);
        content.setPadding(new Insets(35));
        HBox.setHgrow(content, Priority.ALWAYS);
        VBox.setVgrow(content, Priority.ALWAYS);
        content.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 18;"
                        + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 18, 0, 0, 4);");

        Label title = new Label("Kelola Mata Kuliah");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1E293B;");

        content.getChildren().addAll(title, createFormCard());

        center.getChildren().addAll(createSideBar(), content);

        return center;
    }

    private VBox createFormCard() {
        VBox card = new VBox(20);
        card.setPadding(new Insets(25));
        card.setStyle(
                "-fx-background-color: #F8FAFC;"
                        + "-fx-background-radius: 14;"
                        + "-fx-border-color: #E2E8F0;"
                        + "-fx-border-radius: 14;");

        Label pilihMKLabel = new Label("Pilih Mata Kuliah");
        pilihMKLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        mataKuliahBox = new ComboBox<>();
        mataKuliahBox.setPrefWidth(420);

        ObservableList<MataKuliah> daftarMK = KoneksiDB.getMataKuliahByDosen(dosen.getNip());
        mataKuliahBox.setItems(daftarMK);
        mataKuliahBox.setOnAction(e -> tampilkanSemesterUntukMK());

        HBox pilihMKBox = new HBox(15);
        pilihMKBox.setAlignment(Pos.CENTER_LEFT);
        pilihMKBox.getChildren().addAll(pilihMKLabel, mataKuliahBox);

        Label statusLabel = new Label("Status Pembukaan Semester");
        statusLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #1E293B;");

        Button btnTambahSemester = new Button("+ Tambah Semester");
        btnTambahSemester.setCursor(Cursor.HAND);
        btnTambahSemester.setStyle(buttonPrimaryStyle());
        btnTambahSemester.setOnAction(e -> showDialogTambahSemester());

        Region spacerStatus = new Region();
        HBox.setHgrow(spacerStatus, Priority.ALWAYS);

        HBox statusHeader = new HBox(15);
        statusHeader.setAlignment(Pos.CENTER_LEFT);
        statusHeader.getChildren().addAll(statusLabel, spacerStatus, btnTambahSemester);

        semesterListBox = new VBox(12);
        semesterListBox.setPadding(new Insets(10, 0, 10, 0));

        ScrollPane semesterScroll = new ScrollPane(semesterListBox);
        semesterScroll.setFitToWidth(true);
        semesterScroll.setPrefHeight(320);
        semesterScroll.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        Button btnSimpan = new Button("Simpan Perubahan");
        btnSimpan.setCursor(Cursor.HAND);
        btnSimpan.setStyle(buttonPrimaryStyle());
        btnSimpan.setOnAction(e -> simpanPerubahan());

        card.getChildren().addAll(
                pilihMKBox,
                new Separator(),
                statusHeader,
                semesterScroll,
                btnSimpan);

        if (!daftarMK.isEmpty()) {
            mataKuliahBox.setValue(daftarMK.get(0));
            tampilkanSemesterUntukMK();
        }

        return card;
    }

    private void tampilkanSemesterUntukMK() {
        semesterListBox.getChildren().clear();
        semesterCheckBoxMap.clear();

        MataKuliah selectedMK = mataKuliahBox.getValue();

        if (selectedMK == null) {
            Label empty = new Label("Pilih mata kuliah terlebih dahulu.");
            empty.setStyle("-fx-text-fill: #64748B;");
            semesterListBox.getChildren().add(empty);
            return;
        }

        ObservableList<Semester> daftarSemester = KoneksiDB.getAllSemester();

        for (Semester semester : daftarSemester) {
            HBox row = new HBox();
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(8, 0, 8, 0));

            Label semesterLabel = new Label(semester.toString());
            semesterLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #0F172A;");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            CheckBox checkBox = new CheckBox();

            boolean terbuka = KoneksiDB.isMataKuliahTerbuka(
                    selectedMK.getkodeMK(),
                    semester.getIdSemester());

            checkBox.setSelected(terbuka);

            row.getChildren().addAll(semesterLabel, spacer, checkBox);

            semesterCheckBoxMap.put(semester.getIdSemester(), checkBox);
            semesterListBox.getChildren().add(row);
        }
    }

    private void simpanPerubahan() {
        MataKuliah selectedMK = mataKuliahBox.getValue();

        if (selectedMK == null) {
            showAlert(Alert.AlertType.ERROR, "Gagal", "Mata kuliah belum dipilih.");
            return;
        }

        boolean semuaBerhasil = true;

        for (Map.Entry<Integer, CheckBox> entry : semesterCheckBoxMap.entrySet()) {
            int idSemester = entry.getKey();
            boolean dibuka = entry.getValue().isSelected();

            boolean berhasil = KoneksiDB.updateStatusMataKuliahTerbuka(
                    selectedMK.getkodeMK(),
                    idSemester,
                    dibuka);

            if (!berhasil) {
                semuaBerhasil = false;
            }
        }

        if (semuaBerhasil) {
            showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Perubahan berhasil disimpan.");
            tampilkanSemesterUntukMK();
        } else {
            showAlert(Alert.AlertType.ERROR, "Gagal", "Ada perubahan yang gagal disimpan.");
        }
    }

    private void showDialogTambahSemester() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Tambah Semester");

        ButtonType simpanButton = new ButtonType("Simpan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(simpanButton, ButtonType.CANCEL);

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);
        form.setPadding(new Insets(20));

        TextField tfTahunAjaran = new TextField();
        tfTahunAjaran.setPromptText("Contoh: 2025/2026");

        ComboBox<String> jenisBox = new ComboBox<>();
        jenisBox.getItems().addAll("ganjil", "genap");
        jenisBox.setPrefWidth(200);

        form.add(new Label("Tahun Ajaran"), 0, 0);
        form.add(tfTahunAjaran, 1, 0);

        form.add(new Label("Jenis"), 0, 1);
        form.add(jenisBox, 1, 1);

        dialog.getDialogPane().setContent(form);

        dialog.setResultConverter(button -> {
            if (button == simpanButton) {
                String tahunAjaran = tfTahunAjaran.getText();
                String jenis = jenisBox.getValue();

                if (tahunAjaran == null || tahunAjaran.trim().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Gagal", "Tahun ajaran belum diisi.");
                    return null;
                }

                if (jenis == null || jenis.trim().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Gagal", "Jenis semester belum dipilih.");
                    return null;
                }

                boolean berhasil = KoneksiDB.tambahSemester(tahunAjaran, jenis);

                if (berhasil) {
                    showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Semester berhasil ditambahkan.");
                    tampilkanSemesterUntukMK();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Gagal", "Semester sudah ada atau gagal ditambahkan.");
                }
            }

            return null;
        });

        dialog.showAndWait();
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 30));
        topBar.setMinHeight(68);
        topBar.setPrefHeight(68);
        topBar.setMaxHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("KELOLA MATA KULIAH");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: white;");

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
        Button tombolKelolaMK = tombolIcon("clipboard.png", "Kelola Matkul");

        tombolKelolaMK.setStyle(
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

        sidebar.getChildren().addAll(tombolBeranda, tombolProfil, tombolJadwal, tombolKelolaMK);

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

    private String buttonPrimaryStyle() {
        return "-fx-background-color: #243F91;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10 18 10 18;";
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}