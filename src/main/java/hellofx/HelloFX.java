package hellofx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
        hBox3.setStyle("-fx-background-color: #F2F0EA");
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
        button2.setText("SUBMIT");
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
        colNama.setPrefWidth(100);

        TableColumn<MataKuliah, String> colJurusan = new TableColumn<>("Jurusan");
        colJurusan.setCellValueFactory(new PropertyValueFactory<>("jurusan"));
        colJurusan.setPrefWidth(100);

        TableColumn<MataKuliah, Integer> colSKS = new TableColumn<>("Jumlah SKS");
        colSKS.setCellValueFactory(new PropertyValueFactory<>("jumlahSKS"));
        colSKS.setPrefWidth(75);

        TableColumn<MataKuliah, Boolean> colCheck = new TableColumn<>();
        colCheck.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        colCheck.setCellFactory(CheckBoxTableCell.forTableColumn(colCheck));
        colCheck.setPrefWidth(60);

        TableView<MataKuliah> tableView = new TableView<>();
        tableView.getColumns().addAll(colNama, colJurusan, colSKS, colCheck);
        tableView.setVisible(false);

        hBox3.getChildren().addAll(tableView);
        HBox.setHgrow(tableView, Priority.ALWAYS);

        button.setOnAction(e -> {
            ObservableList<MataKuliah> data = KoneksiDB.getAllMataKuliah();
            tableView.setItems(data);
            tableView.setVisible(true);
            tableView.setEditable(true);
            colCheck.setEditable(true);
        });

        // TextField txtNamaMK = new TextField();
        // txtNamaMK.setPromptText("Nama Mata Kuliah");

        // TextField txtNamaJurusan = new TextField();
        // txtNamaJurusan.setPromptText("Nama Jurusan");

        // TextField txtJumlahSKS = new TextField();
        // txtJumlahSKS.setPromptText("Jumlah SKS");

        // HBox formInput = new HBox(10);
        // formInput.setAlignment(Pos.CENTER);
        // formInput.getChildren().addAll(txtNamaMK, txtNamaJurusan, txtJumlahSKS, button2);

        TableColumn<MataKuliah, String> colNama2 = new TableColumn<>("Nama MK");
        colNama2.setCellValueFactory(new PropertyValueFactory<>("namaMK"));
        colNama2.setPrefWidth(100);

        TableColumn<MataKuliah, String> colJurusan2 = new TableColumn<>("Jurusan");
        colJurusan2.setCellValueFactory(new PropertyValueFactory<>("jurusan"));
        colJurusan2.setPrefWidth(100);

        TableColumn<MataKuliah, Integer> colSKS2= new TableColumn<>("Jumlah SKS");
        colSKS2.setCellValueFactory(new PropertyValueFactory<>("jumlahSKS"));
        colSKS2.setPrefWidth(75);

        TableView<MataKuliah> matkulDipilih = new TableView<>();
        matkulDipilih.getColumns().addAll(colNama2, colJurusan2, colSKS2);
        matkulDipilih.setVisible(false);

        button2.setOnAction(e -> {
            matkulDipilih.getItems().clear();
            for (MataKuliah mk : tableView.getItems()) {
                if (mk.isSelected()) {
                    matkulDipilih.getItems().add(mk);
                    System.out.println(KoneksiDB.getKodeMkByNamaMK(mk.getNamaMK()));
                    System.out.println("Dipilih: " + mk.getNamaMK());
                }
            }

            matkulDipilih.setVisible(true);

            // try {
            //     String namaMK = txtNamaMK.getText();
            //     String namaJurusan = txtNamaJurusan.getText();
            //     int jumlahSKS = Integer.parseInt(txtJumlahSKS.getText());

            //     KoneksiDB.insertMataKuliah(namaMK, namaJurusan, jumlahSKS);

            //     ObservableList<MataKuliah> data = KoneksiDB.getAllMataKuliah();
            //     tableView.setItems(data);
            //     tableView.setVisible(true);

            //     txtNamaMK.clear();
            //     txtNamaJurusan.clear();
            //     txtJumlahSKS.clear();

            //     System.out.println("Data berhasil dimasukkan");

            // } catch (NumberFormatException ex) {
            //     System.out.println("Jumlah SKS harus berupa angka");
            // }
        });

        hBox4.getChildren().addAll(button, button2);
        hBox3.getChildren().addAll(matkulDipilih);

        hBox4.setSpacing(20);
        hBox4.setPadding(new Insets(20));
        hBox4.setAlignment(Pos.CENTER);

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