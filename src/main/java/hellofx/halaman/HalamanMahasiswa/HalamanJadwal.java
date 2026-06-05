package hellofx.halaman.HalamanMahasiswa;

import hellofx.kelasData.JadwalKelas;
import hellofx.kelasData.Mahasiswa;
import hellofx.kelasData.Semester;
import hellofx.Database.KoneksiDB;
import hellofx.kelasData.Jadwal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

import java.util.LinkedHashMap;
import java.util.Map;

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

        HBox bagianTengah = center();

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

    private HBox center() {
        HBox center = new HBox();

        VBox menu = createSideBar();

        TableView<Jadwal> jadwal = new TableView<>();
        jadwal.setFixedCellSize(35);
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

        HBox.setHgrow(jadwal, Priority.ALWAYS);
        VBox.setVgrow(jadwal, Priority.ALWAYS);
        jadwal.setMaxHeight(Double.MAX_VALUE);
        jadwal.setMaxWidth(Double.MAX_VALUE);

        ComboBox<Semester> semesterBox = new ComboBox<>();
        semesterBox.setItems(KoneksiDB.getAllSemester());

        if (!semesterBox.getItems().isEmpty()) {
            semesterBox.setValue(semesterBox.getItems().get(0));
            isiJadwal(jadwal, semesterBox.getValue().getIdSemester());
        }

        semesterBox.setOnAction(e -> {
            if (semesterBox.getValue() != null) {
                isiJadwal(jadwal, semesterBox.getValue().getIdSemester());
            }
        });

        VBox isi = new VBox();
        isi.setAlignment(Pos.CENTER);
        isi.setPadding(new Insets(0, 10, 30, 10));
        isi.setSpacing(30);
        isi.getChildren().addAll(semesterBox, jadwal);
        HBox.setHgrow(isi, Priority.ALWAYS);
        VBox.setVgrow(isi, Priority.ALWAYS);

        center.setStyle("-fx-background-color : #ffffff");
        center.setPadding(new Insets(35, 0, 0, 0));
        center.getChildren().addAll(menu, isi);
        VBox.setVgrow(center, Priority.ALWAYS);

        return center;
    }

    private VBox createSideBar() {
        VBox sidebar = new VBox(12);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPrefWidth(105);

        Button tombolBeranda = tombolIcon("home (2).png", "Beranda");
        Button tombolFrs = tombolIcon("google-docs (1).png", "FRS");
        Button tombolProfil = tombolIcon("user (1).png", "Profil");
        Button tombolJadwal = tombolIcon("calendar.png", "Jadwal");

        tombolProfil.setOnAction(e -> {
            HalamanProfil profil = new HalamanProfil(stage, mahasiswa);
            stage.setScene(profil.getScene());
            stage.setTitle("FRS");
        });

        tombolFrs.setOnAction(e -> {
            HalamanFRS frs = new HalamanFRS(stage, mahasiswa);
            stage.setScene(frs.getScene());
            stage.setTitle("FRS");
        });

        tombolBeranda.setOnAction(e -> {
            HalamanBeranda beranda = new HalamanBeranda(stage, mahasiswa);
            stage.setScene(beranda.getScene());
            stage.setTitle("FRS");
        });

        tombolJadwal.setOnAction(e -> {
            HalamanJadwal jadwal = new HalamanJadwal(stage, mahasiswa);
            stage.setScene(jadwal.getScene());
            stage.setTitle("FRS");
        });

        sidebar.getChildren().addAll(tombolBeranda, tombolProfil, tombolFrs, tombolJadwal);

        return sidebar;
    }

    private void isiJadwal(TableView<Jadwal> jadwal, int idSemester) {
        LinkedHashMap<String, Jadwal> data2 = new LinkedHashMap<>();

        for (int i = 7; i <= 18; i++) {
            String jamValue = String.format("%02d:00", i);
            data2.put(jamValue, new Jadwal(jamValue, null, null, null, null, null, null));
        }

        ObservableList<JadwalKelas> list = KoneksiDB.getAllJadwal(idSemester);

        for (JadwalKelas x : list) {
            ambilJadwal(data2, x);
            int jamSekarang = Integer.parseInt(x.getWaktuMulai().substring(0, 2));
            int durasi = x.getDurasi();
            int ct = 60;

            while (ct < durasi) {
                jamSekarang++;
                ct += 60;

                String nextJam = String.format("%02d:00", jamSekarang);
                x.setJam(nextJam);
                ambilJadwal(data2, x);
            }
        }

        ObservableList<Jadwal> data = FXCollections.observableArrayList(data2.values());

        jadwal.setItems(data);
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
                ;
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
