package hellofx.halaman;

import java.util.LinkedHashMap;
import java.util.Map;

import hellofx.Database.KoneksiDB;
import hellofx.kelasData.MataKuliah;
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

    private final Map<CheckBox, Course> courseMap = new LinkedHashMap<>();

    public HalamanFRS(Stage stage) {
        this.stage = stage;
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
            HalamanProfil profil = new HalamanProfil(stage);
            stage.setScene(profil.getScene());
            stage.setTitle("FRS");
        });

        tombolFrs.setOnAction(e -> {
            HalamanFRS frs = new HalamanFRS(stage);
            stage.setScene(frs.getScene());
            stage.setTitle("FRS");
        });

        tombolBeranda.setOnAction(e -> {
            HalamanBeranda beranda = new HalamanBeranda(stage);
            stage.setScene(beranda.getScene());
            stage.setTitle("FRS");
        });

        tombolJadwal.setOnAction(e -> {
            HalamanJadwal jadwal = new HalamanJadwal(stage);
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

        ComboBox<String> semesterBox = new ComboBox<>();
        semesterBox.getItems().addAll(
                "Semester Ganjil 2024",
                "Semester Genap 2024",
                "Semester Ganjil 2025");
        semesterBox.setValue("Semester Ganjil 2024");
        semesterBox.setPrefWidth(350);
        semesterBox.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: #F8F3F3;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #D6D0D0;" +
                        "-fx-border-radius: 10;");

        VBox courseList = new VBox(0);
        courseList.setMaxWidth(545);

        loadMataKuliahFromDB(courseList);

        ScrollPane scrollPane = new ScrollPane(courseList);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(560);
        scrollPane.setPrefHeight(410);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;" +
                        "-fx-border-color: transparent;");

        center.getChildren().addAll(semesterBox, scrollPane);

        return center;
    }

    private void loadMataKuliahFromDB(VBox courselist) {
        ObservableList<MataKuliah> list = KoneksiDB.getAllMatakuliah();

        int semesterSebelumnya = -1;

        for (MataKuliah mk : list) {
            int semesterSekarang = mk.getIdSemester();

            if (semesterSekarang != semesterSebelumnya) {
                addSemesterHeader(courselist, "Semester " + semesterSekarang);
                semesterSebelumnya = semesterSekarang;
            }

            Course course = new Course(mk.getNamaMK(), mk.getJumlahSKS(), false);
            addCourseRow(courselist, course);
        }

    }

    private void addSemesterHeader(VBox parent, String title) {
        Label header = new Label(title);
        header.setPrefSize(545, 42);
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

        Label kodeLabel = new Label(course.kodeMK);
        kodeLabel.setPrefWidth(105);
        kodeLabel.setStyle(
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

        row.getChildren().addAll(nameLabel, kodeLabel, checkBox);
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

    private static class Course {
        String namaMK;
        String kodeMK;
        int sks;
        boolean selected;

        Course(String namaMK, int sks, boolean selected) {
            this.namaMK = namaMK;
            this.kodeMK = kodeMK;
            this.sks = sks;
            this.selected = selected;
        }
    }
}