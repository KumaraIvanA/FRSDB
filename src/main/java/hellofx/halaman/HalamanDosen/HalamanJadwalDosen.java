package hellofx.halaman.HalamanDosen;

import java.util.LinkedHashMap;
import java.util.Map;

import hellofx.Database.KoneksiDB;
import hellofx.kelasData.Dosen;
import hellofx.kelasData.Jadwal;
import hellofx.kelasData.JadwalKelas;
import hellofx.kelasData.Semester;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private boolean modeJadwalSaya = true;

    public HalamanJadwalDosen(Stage stage, Dosen dosen) {
        this.stage = stage;
        this.dosen = dosen;
    }

    public Scene getScene() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #F7F9FC;");

        HBox header = createTopBar();
        HBox bagianTengah = center();

        layout.getChildren().addAll(header, bagianTengah);

        return new Scene(layout, 1200, 750);
    }

    private HBox center() {
        HBox center = new HBox();
        center.setPadding(new Insets(25, 35, 25, 0));
        center.setSpacing(30);
        center.setStyle("-fx-background-color: #F7F9FC;");
        VBox.setVgrow(center, Priority.ALWAYS);

        VBox sideBar = createSideBar();

        VBox content = new VBox(20);
        content.setPadding(new Insets(35));
        content.setMaxWidth(Double.MAX_VALUE);
        content.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(content, Priority.ALWAYS);
        VBox.setVgrow(content, Priority.ALWAYS);
        content.setStyle(
                "-fx-background-color: #ffffff;"
                        + "-fx-background-radius: 18;"
                        + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 18, 0, 0, 4);");

        Label title = new Label("Jadwal Mengajar");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1E293B;");

        ComboBox<Semester> semesterBox = new ComboBox<>();
        semesterBox.setItems(KoneksiDB.getAllSemester());
        semesterBox.setPrefWidth(220);

        TableView<Jadwal> tabelJadwal = createTableJadwal();

        Button tombolJadwalSaya = new Button("Jadwal Saya");
        Button tombolSemuaJadwal = new Button("Semua Jadwal");

        setButtonAktif(tombolJadwalSaya);
        setButtonNonAktif(tombolSemuaJadwal);

        tombolJadwalSaya.setOnAction(e -> {
            modeJadwalSaya = true;
            setButtonAktif(tombolJadwalSaya);
            setButtonNonAktif(tombolSemuaJadwal);

            if (semesterBox.getValue() != null) {
                tampilkanJadwalSaya(tabelJadwal, semesterBox.getValue().getIdSemester());
            }
        });

        tombolSemuaJadwal.setOnAction(e -> {
            modeJadwalSaya = false;
            setButtonAktif(tombolSemuaJadwal);
            setButtonNonAktif(tombolJadwalSaya);

            if (semesterBox.getValue() != null) {
                tampilkanSemuaJadwal(tabelJadwal, semesterBox.getValue().getIdSemester());
            }
        });

        semesterBox.setOnAction(e -> {
            if (semesterBox.getValue() != null) {
                if (modeJadwalSaya) {
                    tampilkanJadwalSaya(tabelJadwal, semesterBox.getValue().getIdSemester());
                } else {
                    tampilkanSemuaJadwal(tabelJadwal, semesterBox.getValue().getIdSemester());
                }
            }
        });

        if (!semesterBox.getItems().isEmpty()) {
            semesterBox.setValue(semesterBox.getItems().get(0));
            tampilkanJadwalSaya(tabelJadwal, semesterBox.getValue().getIdSemester());
        }

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox tombolMode = new HBox(12);
        tombolMode.getChildren().addAll(tombolJadwalSaya, tombolSemuaJadwal);

        HBox topContent = new HBox(20);
        topContent.setAlignment(Pos.CENTER_LEFT);
        topContent.getChildren().addAll(title, spacer, semesterBox, tombolMode);

        content.getChildren().addAll(topContent, tabelJadwal);
        VBox.setVgrow(tabelJadwal, Priority.ALWAYS);

        center.getChildren().addAll(sideBar, content);
        HBox.setHgrow(content, Priority.ALWAYS);

        return center;
    }

    private TableView<Jadwal> createTableJadwal() {
        TableView<Jadwal> jadwal = new TableView<>();
        jadwal.setFixedCellSize(70);
        jadwal.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Jadwal, String> jam = new TableColumn<>("Jam");
        jam.setCellValueFactory(new PropertyValueFactory<>("jam"));

        TableColumn<Jadwal, String> senin = new TableColumn<>("Senin");
        senin.setCellValueFactory(new PropertyValueFactory<>("senin"));

        TableColumn<Jadwal, String> selasa = new TableColumn<>("Selasa");
        selasa.setCellValueFactory(new PropertyValueFactory<>("selasa"));

        TableColumn<Jadwal, String> rabu = new TableColumn<>("Rabu");
        rabu.setCellValueFactory(new PropertyValueFactory<>("rabu"));

        TableColumn<Jadwal, String> kamis = new TableColumn<>("Kamis");
        kamis.setCellValueFactory(new PropertyValueFactory<>("kamis"));

        TableColumn<Jadwal, String> jumat = new TableColumn<>("Jumat");
        jumat.setCellValueFactory(new PropertyValueFactory<>("jumat"));

        TableColumn<Jadwal, String> sabtu = new TableColumn<>("Sabtu");
        sabtu.setCellValueFactory(new PropertyValueFactory<>("sabtu"));

        jadwal.getColumns().addAll(jam, senin, selasa, rabu, kamis, jumat, sabtu);

        jam.setStyle("-fx-alignment: CENTER;");
        jam.setMinWidth(80);
        jam.setMaxWidth(80);

        setWrapColumn(senin);
        setWrapColumn(selasa);
        setWrapColumn(rabu);
        setWrapColumn(kamis);
        setWrapColumn(jumat);
        setWrapColumn(sabtu);

        HBox.setHgrow(jadwal, Priority.ALWAYS);
        VBox.setVgrow(jadwal, Priority.ALWAYS);
        jadwal.setMaxWidth(Double.MAX_VALUE);
        jadwal.setMaxHeight(Double.MAX_VALUE);

        return jadwal;
    }

    private void tampilkanJadwalSaya(TableView<Jadwal> tabelJadwal, int idSemester) {
        ObservableList<JadwalKelas> list = KoneksiDB.getJadwalDosen(idSemester, dosen.getNip());
        isiTabelJadwal(tabelJadwal, list);
    }

    private void tampilkanSemuaJadwal(TableView<Jadwal> tabelJadwal, int idSemester) {
        ObservableList<JadwalKelas> list = KoneksiDB.getAllJadwal(idSemester);
        isiTabelJadwal(tabelJadwal, list);
    }

    private void isiTabelJadwal(TableView<Jadwal> tabelJadwal, ObservableList<JadwalKelas> list) {
        LinkedHashMap<String, Jadwal> dataMap = new LinkedHashMap<>();

        for (int i = 7; i <= 18; i++) {
            String jamValue = String.format("%02d:00", i);
            dataMap.put(jamValue, new Jadwal(jamValue, null, null, null, null, null, null));
        }

        for (JadwalKelas x : list) {
            ambilJadwal(dataMap, x);

            int jamSekarang = Integer.parseInt(x.getWaktuMulai().substring(0, 2));
            int durasi = x.getDurasi();
            int totalMenit = 60;

            while (totalMenit < durasi) {
                jamSekarang++;
                totalMenit += 60;

                String nextJam = String.format("%02d:00", jamSekarang);
                x.setJam(nextJam);
                ambilJadwal(dataMap, x);
            }
        }

        ObservableList<Jadwal> data = FXCollections.observableArrayList(dataMap.values());
        tabelJadwal.setItems(data);
    }

    private void ambilJadwal(Map<String, Jadwal> map, JadwalKelas mk) {
        Jadwal temp = map.get(mk.getWaktuMulai().substring(0, 5));

        if (temp == null) {
            return;
        }

        switch (mk.getHari().toLowerCase()) {
            case "senin":
                temp.setSenin(mk);
                break;
            case "selasa":
                temp.setSelasa(mk);
                break;
            case "rabu":
                temp.setRabu(mk);
                break;
            case "kamis":
                temp.setKamis(mk);
                break;
            case "jumat":
                temp.setJumat(mk);
                break;
            case "sabtu":
                temp.setSabtu(mk);
                break;
            default:
                break;
        }
    }

    private VBox createSideBar() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(10, 0, 10, 0));
        sidebar.setPrefWidth(120);
        sidebar.setAlignment(Pos.TOP_CENTER);

        Button tombolBeranda = tombolIcon("home (2).png", "Beranda");
        Button tombolProfil = tombolIcon("user (1).png", "Profil");
        Button tombolJadwal = tombolIcon("calendar.png", "Jadwal");
        Button tombolKelolaMatkul = tombolIcon("clipboard.png", "Kelola Matkul");

        tombolJadwal.setStyle(
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

        tombolKelolaMatkul.setOnAction(e -> {
            HalamanKelolaMataKuliahDosen daftarKelas = new HalamanKelolaMataKuliahDosen(stage, dosen);
            stage.setScene(daftarKelas.getScene());
            stage.setTitle("Kelola Matkul");
        });

        sidebar.getChildren().addAll(tombolBeranda, tombolProfil, tombolJadwal, tombolKelolaMatkul);

        return sidebar;
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 30));
        topBar.setMinHeight(68);
        topBar.setPrefHeight(68);
        topBar.setMaxHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("JADWAL DOSEN");
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
                        + "-fx-text-fill: #ffffff;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 12;");
        return button;
    }

    private void setButtonAktif(Button button) {
        button.setCursor(Cursor.HAND);
        button.setStyle(
                "-fx-background-color: #243F91;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 10;"
                        + "-fx-padding: 10 18 10 18;");
    }

    private void setButtonNonAktif(Button button) {
        button.setCursor(Cursor.HAND);
        button.setStyle(
                "-fx-background-color: #E2E8F0;"
                        + "-fx-text-fill: #1E293B;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 10;"
                        + "-fx-padding: 10 18 10 18;");
    }

    private void setWrapColumn(TableColumn<Jadwal, String> column) {
        column.setCellFactory(tc -> new TableCell<Jadwal, String>() {
            private final Label label = new Label();

            {
                label.setWrapText(true);
                label.setAlignment(Pos.CENTER);
                label.setMaxWidth(Double.MAX_VALUE);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item);
                    setGraphic(label);
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }
}