package FRS.halaman.HalamanMahasiswa;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import FRS.Database.KoneksiDB;
import FRS.halaman.Common.Sidebar;
import FRS.halaman.Common.TopBar;
import FRS.kelasData.FRS;
import FRS.kelasData.Mahasiswa;
import FRS.kelasData.MataKuliah;
import FRS.kelasData.Semester;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class HistoryView extends VBox {
	public HistoryView(Semester semester, Mahasiswa mahasiswa) {
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10);

		for (FRS frs : KoneksiDB.getFRS(mahasiswa.getNPM(), semester.getIdSemester())) {
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.TOP_CENTER);
			vbox.setPadding(new Insets(30, 0, 0, 0));

			addHeader(vbox, frs.getDatetime());
			for (MataKuliah mk : frs.getCourseTaken()) {
				addRow(vbox, mk, frs);
			}

			this.getChildren().add(vbox);
		}
	}

	private void addRow(VBox vbox, MataKuliah mataKuliah, FRS frs) {
		HBox row = new HBox(10);
		row.setAlignment(Pos.CENTER_LEFT);
		row.setPrefHeight(44);
		row.setPadding(new Insets(0, 20, 0, 16));
		row.setStyle(
			"-fx-background-color: #F8F3F3;" +
			"-fx-border-color: #E8E8E8;" +
			"-fx-border-width: 0 0 1 0;");

		Label label = new Label(String.format(
			"%s (%d SKS)",
			mataKuliah.getNamaMK(),
			mataKuliah.getJumlahSKS()
		));

		label.setMinWidth(Control.USE_PREF_SIZE);
		label.setStyle(
			"-fx-font-size: 18px;" +
			"-fx-font-weight: bold;" +
			"-fx-text-fill: #1D1D1D;");


		Button deleteButton = new Button("×");
		deleteButton.setStyle(
			"-fx-background-color: transparent;" +
			"-fx-text-fill: #E74C3C;" +
			"-fx-font-size: 22px;" +
			"-fx-font-weight: bold;" +
			"-fx-cursor: hand;"

		);

		deleteButton.setOnAction(event -> {
			KoneksiDB.deleteEnrollment(frs.getID(), mataKuliah.getkodeMK());
			vbox.getChildren().remove(row);
		});

		javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		row.getChildren().addAll(label, spacer, deleteButton);

		vbox.getChildren().add(row);
	}

	private void addHeader(VBox vbox, LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());

		Label header = new Label(formatter.format(time));
        header.setPrefSize(545, 42);
        header.setPadding(new Insets(0, 0, 0, 20));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle(
            "-fx-background-color: #AFC2D9;" +
            "-fx-background-radius: 7;" +
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: black;");

		vbox.getChildren().add(header);
	}
}

public class HalamanRiwayatFRS {
	private Stage stage;
	private Mahasiswa mahasiswa;

	public HalamanRiwayatFRS(Stage stage, Mahasiswa mahasiswa) {
		this.stage = stage;
		this.mahasiswa = mahasiswa;
	}

	public Scene getScene() {
		BorderPane root = new BorderPane();
		root.setStyle(
			"-fx-background-color: white;" +
			"-fx-font-family: Arial;");

		root.setTop(TopBar.create(this, "Riwayat FRS"));

		HBox body = new HBox(20);
		body.setPadding(new Insets(40));
		body.setAlignment(Pos.TOP_CENTER);
		body.setStyle("-fx-background-color: white;");

		VBox sidebar = Sidebar.create(this, stage, mahasiswa);
		VBox centerContent = createCenterContent();

		HBox.setHgrow(centerContent, Priority.ALWAYS);

		body.getChildren().addAll(sidebar, centerContent);

		root.setCenter(body);
		
		Scene scene = new Scene(root, 1200, 750);
		return scene;
	}

	private VBox createCenterContent() {
		VBox center = new VBox(12);
		center.setAlignment(Pos.TOP_CENTER);
		center.setPrefWidth(560);

		ComboBox<Semester> semesterBox = new ComboBox<>();
		semesterBox.setItems(KoneksiDB.getAllSemester());
		semesterBox.setPrefWidth(350);
		semesterBox.setStyle(
			"-fx-font-size: 18px;" +
			"-fx-font-weight: bold;" +
			"-fx-background-color: #F8F3F3;" +
			"-fx-background-radius: 10;" +
			"-fx-border-color: #D6D0D0;" +
			"-fx-border-radius: 10;");

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setStyle(
			"-fx-background-color: transparent;"+
			"-fx-background: transparent;" +
			"-fx-border-color: transparent;"
		);
		VBox.setVgrow(scrollPane, Priority.ALWAYS);

		semesterBox.getSelectionModel().selectedItemProperty().addListener(
			(observable, oldValue, newValue) -> {
				scrollPane.setContent(new HistoryView(newValue, mahasiswa));
			}
		);

		if (!semesterBox.getItems().isEmpty()) {
			semesterBox.setValue(semesterBox.getItems().get(0));
		}

		center.getChildren().addAll(semesterBox, scrollPane);

		return center;
	}
}
