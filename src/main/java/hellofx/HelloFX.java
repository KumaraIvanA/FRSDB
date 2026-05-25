package hellofx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane rootUtama = new BorderPane();

        VBox vBox = new VBox();
        vBox.setPrefWidth(800);
        vBox.setPrefHeight(100);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15));

        Label label1 = new Label("FRS");
        label1.setStyle(
                "-fx-font-size : 20px; -fx-font-weight: bold; -fx-text-fill : black; -fx-font-family : Georgia;");

        Region space = new Region();

        HBox.setHgrow(space, Priority.ALWAYS);

        HBox menu = new HBox(20);
        menu.setAlignment(Pos.CENTER_RIGHT);

        menu.getChildren().addAll(new Label("Home"), new Label("Profile"), new Label("Notification"));

        hBox.getChildren().addAll(label1, space, menu);
        hBox.setStyle("-fx-background-color: #A8D5E3; -fx-font-weight: bold; ");
        hBox.setPrefHeight(70);

        HBox hBox2 = new HBox();
        hBox2.setStyle("-fx-background-color: #F2F0EA");
        hBox2.setPrefHeight(30);

        vBox.getChildren().addAll(hBox, hBox2);

        HBox hBox3 = new HBox();
        hBox3.setStyle("-fx-background-color: #FF78AC");
        hBox3.setPrefHeight(190);

        HBox hBox4 = new HBox();
        hBox4.setStyle("-fx-background-color: #F2F0EA");
        hBox4.setPrefHeight(210);
        Button button = new Button();
        button.setPrefHeight(25);
        button.setPrefWidth(100);
        button.setText("TEKAN");
        button.setBackground(null);
        button.setCursor(Cursor.HAND);
        hBox4.setAlignment(Pos.CENTER);

        button.setStyle(
                "-fx-background-color: #A8D5E3;" + // Warna latar belakang tombol
                        "-fx-text-fill: black;" + // Warna tulisan
                        "-fx-border-color: #4A90E2;" + // Warna garis tepi (border)
                        "-fx-border-width: 2px;" + // Ketebalan garis tepi
                        "-fx-border-radius: 8px;" + // Kelengkungan garis tepi
                        "-fx-background-radius: 8px;" // Kelengkungan latar belakang (HARUS SAMA dengan border-radius)
        );

        Button button2 = new Button();
        button2.setPrefHeight(25);
        button2.setPrefWidth(100);
        button2.setText("INSERT");
        button2.setBackground(null);
        button2.setCursor(Cursor.HAND);

        button2.setStyle(
                "-fx-background-color: #A8D5E3;" + // Warna latar belakang tombol
                        "-fx-text-fill: black;" + // Warna tulisan
                        "-fx-border-color: #4A90E2;" + // Warna garis tepi (border)
                        "-fx-border-width: 2px;" + // Ketebalan garis tepi
                        "-fx-border-radius: 8px;" + // Kelengkungan garis tepi
                        "-fx-background-radius: 8px;" // Kelengkungan latar belakang (HARUS SAMA dengan border-radius)
        );

        TableColumn<MataKuliah, String> colNama = new TableColumn<>("Nama MK");
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaMK"));
        colNama.setPrefWidth(200);

        TableColumn<MataKuliah, String> colJurusan = new TableColumn<>("Jurusan");
        colJurusan.setCellValueFactory(new PropertyValueFactory<>("jurusan"));
        colJurusan.setPrefWidth(200);

        TableColumn<MataKuliah, Integer> colSKS = new TableColumn<>("Jumlah SKS");
        colSKS.setCellValueFactory(new PropertyValueFactory<>("jumlahSKS"));
        colSKS.setPrefWidth(150);

        TableView<MataKuliah> tableView = new TableView<>();
        tableView.getColumns().addAll(colNama, colJurusan, colSKS);
        tableView.setVisible(false);

        hBox3.getChildren().addAll(tableView);
        HBox.setHgrow(tableView, Priority.ALWAYS);

        button2.setOnAction(e -> {
            KoneksiDB.insertMataKuliah("DAA", "Informatika", 4);

            ObservableList<MataKuliah> data = KoneksiDB.getAllMataKuliah();
            tableView.setItems(data);
            tableView.setVisible(true);
        });

        button.setOnAction(e -> {
            ObservableList<MataKuliah> data = KoneksiDB.getAllMataKuliah();
            tableView.setItems(data);
            tableView.setVisible(true);
        });

        hBox4.getChildren().addAll(button, button2);
        hBox3.setAlignment(Pos.CENTER);

        rootUtama.setTop(vBox);
        rootUtama.setCenter(hBox3);
        rootUtama.setBottom(hBox4);

        Scene scene = new Scene(rootUtama, 800, 500);
        stage.setTitle("Portfolio Layout");
        stage.setScene(scene);
        stage.show();

        KoneksiDB.hubungkan();
    }

    public static void main(String[] args) {
        launch();
    }

    public VBox creaetVBox() {
        VBox vBox = new VBox();

        return vBox;
    }
}