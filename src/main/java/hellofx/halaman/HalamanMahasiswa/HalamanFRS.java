package hellofx.halaman.HalamanMahasiswa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import hellofx.Database.KoneksiDB;
import hellofx.kelasData.Mahasiswa;
import hellofx.kelasData.MataKuliah;
import hellofx.kelasData.Semester;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanFRS {
    private Stage stage;
    private Label totalSksValue;
    private Mahasiswa mahasiswa;
    private ComboBox<Semester> semesterBox;

    private final Map<CheckBox, Course> courseMap = new LinkedHashMap<>();

    public HalamanFRS(Stage stage, Mahasiswa mahasiswa) {
        this.stage = stage;
        this.mahasiswa = mahasiswa;
    }

    public Scene getScene() {
        courseMap.clear();

        BorderPane root = new BorderPane();
        root.setStyle(
                "-fx-background-color: white;" +
                        "-fx-font-family: Arial;");

        root.setTop(createTopBar());

        HBox body = new HBox(20);
        body.setPadding(new Insets(15, 30, 20, 0));
        body.setAlignment(Pos.TOP_CENTER);
        body.setStyle("-fx-background-color: white;");

        VBox sidebar = createSidebar();
        VBox centerContent = createCenterContent();
        VBox rightContent = createRightContent();

        HBox.setHgrow(centerContent, Priority.ALWAYS);

        body.getChildren().addAll(sidebar, centerContent, rightContent);

        root.setCenter(body);

        updateTotalSks();

        Scene scene = new Scene(root, 1200, 750);
        return scene;
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 16));
        topBar.setPrefHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label title = new Label("FRS");
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

    private VBox createSidebar() {
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

    private VBox createCenterContent() {
        VBox center = new VBox(12);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPrefWidth(560);

        semesterBox = new ComboBox<>();
        semesterBox.setItems(KoneksiDB.getAllSemester());

        if (!semesterBox.getItems().isEmpty()) {
            semesterBox.setValue(semesterBox.getItems().get(0));
        }

        VBox courseList = new VBox(0);
        courseList.setMaxWidth(Double.MAX_VALUE);

        if (semesterBox.getValue() != null) {
            loadMataKuliahFromDB(courseList);
        }

        semesterBox.setOnAction(e -> {
            courseList.getChildren().clear();
            courseMap.clear();

            Semester selectedSemester = semesterBox.getValue();

            if (selectedSemester != null) {
                loadMataKuliahFromDB(courseList);
            }

            updateTotalSks();
        });

        ScrollPane scrollPane = new ScrollPane(courseList);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(560);
        scrollPane.setPrefHeight(800);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;" +
                        "-fx-border-color: transparent;");

        center.getChildren().addAll(semesterBox, scrollPane);

        return center;
    }

    private void loadMataKuliahFromDB(VBox courselist) {
        ObservableList<MataKuliah> list = KoneksiDB.getAllMatakuliahBerdasarkanIdJurusan(mahasiswa.getIdJurusan());

        int semesterSebelumnya = -1;

        for (MataKuliah mk : list) {
            int semesterSekarang = mk.getIdSemester();

            if (semesterSekarang != semesterSebelumnya) {
                addSemesterHeader(courselist, "Semester " + semesterSekarang);
                semesterSebelumnya = semesterSekarang;
            }

            Course course = new Course(mk.getNamaMK(), mk.getJumlahSKS(), false, mk.getkodeMK());
            addCourseRow(courselist, course);
        }

    }

    private void addSemesterHeader(VBox parent, String title) {
        Label header = new Label(title);
        header.setMaxWidth(Double.MAX_VALUE);
        header.setPrefHeight(42);
        header.setPadding(new Insets(0, 0, 0, 20));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle(
                "-fx-background-color: #AFC2D9;" +
                        "-fx-background-radius: 7;" +
                        "-fx-font-size: 19px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;");

        VBox.setMargin(header, new Insets(8, 0, 0, 0));
        parent.getChildren().add(header);
    }

    private void addCourseRow(VBox parent, Course course) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPrefHeight(44);  
        row.setPadding(new Insets(0, 20, 0, 16));
        row.setStyle(
                "-fx-background-color: #F8F3F3;" +
                        "-fx-border-color: #E8E8E8;" +
                        "-fx-border-width: 0 0 1 0;");

        Label nameLabel = new Label(course.namaMK + " (" + course.sks + " SKS)");
        nameLabel.setPrefWidth(330);
        nameLabel.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1D1D1D;");

        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(course.selected);
        checkBox.setStyle(
                "-fx-mark-color: #6C5EB5;" +
                        "-fx-cursor: hand;");

        courseMap.put(checkBox, course);

        checkBox.selectedProperty().addListener((obs, oldValue, newValue) -> {
            updateTotalSks();
        });

        row.getChildren().addAll(nameLabel, checkBox);
        parent.getChildren().add(row);
    }

    private VBox createRightContent() {
        VBox right = new VBox(8);
        right.setAlignment(Pos.TOP_CENTER);
        right.setPrefWidth(145);
        right.setPadding(new Insets(230, 0, 0, 0));

        Label totalLabel = new Label("Total sks :");
        totalLabel.setStyle(
                "-fx-font-size: 21px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;");

        totalSksValue = new Label("0");
        totalSksValue.setStyle(
                "-fx-font-size: 21px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;");

        Button submitButton = new Button("Submit");
        submitButton.setPrefWidth(120);
        submitButton.setPrefHeight(46);
        submitButton.setStyle(
                "-fx-background-color: #243F91;" +
                        "-fx-background-radius: 10;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-cursor: hand;");

        submitButton.setOnAction(e -> {
            Semester semesterRN = semesterBox.getValue();

            ArrayList<Course> selectedCourse = new ArrayList<>();
            for (Map.Entry<CheckBox, Course> entry : courseMap.entrySet()) {
                if (entry.getKey().isSelected()) {
                    selectedCourse.add(entry.getValue());
                }
            }

            if (selectedCourse.isEmpty()) {
                Alert warn = new Alert(Alert.AlertType.WARNING);
                warn.initOwner(stage);
                warn.setHeaderText("Belum ada mata kuliah yang dipilih");
                warn.showAndWait();
                return;
            }

            // dia akan membuat idBaru pada setiap kali submit dipencet
            int idFRS = KoneksiDB.makeNewIdFRS(semesterRN.getIdSemester());

            // kalo dia gagal
            if (idFRS == -1) {
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.initOwner(stage);
                err.setHeaderText("Gagal membuat FRS baru");
                err.setContentText("Data tidak dapat disimpan. Coba lagi.");
                err.showAndWait();
                return;
            }

            KoneksiDB.isiDataEnroll(selectedCourse, mahasiswa.getNPM(), semesterRN.getIdSemester(), idFRS);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Submit FRS");
            alert.setHeaderText("FRS berhasil disubmit");
            alert.setContentText("Total SKS yang dipilih: " + totalSksValue.getText());
            alert.showAndWait();
        });

        VBox.setMargin(submitButton, new Insets(130, 0, 0, 0));

        right.getChildren().addAll(totalLabel, totalSksValue, submitButton);

        return right;
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

    private void updateTotalSks() {
        int total = 0;

        for (Map.Entry<CheckBox, Course> entry : courseMap.entrySet()) {
            CheckBox checkBox = entry.getKey();
            Course course = entry.getValue();

            if (checkBox.isSelected()) {
                total += course.sks;
            }
        }

        if (totalSksValue != null) {
            totalSksValue.setText(String.valueOf(total));
        }
    }

    public static class Course {
        int kodeMK;
        String namaMK;
        int sks;
        boolean selected;

        Course(String namaMK, int sks, boolean selected, int kodeMK) {
            this.kodeMK = kodeMK;
            this.namaMK = namaMK;
            this.sks = sks;
            this.selected = selected;
        }

        public String getNamaMK() {
            return namaMK;
        }

        public int getSks() {
            return sks;
        }

        public int getKodeMK() {
            return kodeMK;
        }
    }
}