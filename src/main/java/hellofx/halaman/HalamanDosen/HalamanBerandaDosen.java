package hellofx.halaman.HalamanDosen;
// package hellofx.halaman;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Cursor;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;

// public class HalamanBerandaDosen {
//     private Stage stage;

//     public HalamanBerandaDosen(Stage stage) {
//         this.stage = stage;
//     }

//     public Scene getScene() {
//         VBox layout = new VBox();

//         HBox header = new HBox();
//         header.setPrefHeight(50);
//         header.setStyle("-fx-background-color: #1E3A8A;");
//         header.setPadding(new Insets(10, 15, 10, 15));

//         Label label = new Label("BERANDA");
//         label.setStyle("-fx-text-fill : #FFFFFF; -fx-font-size : 25; -fx-font-weight : 700;");
//         label.setLayoutY(25);
//         header.getChildren().add(label);

//         Label welcome = new Label("SELAMAT DATANG DI\nPORTAL DOSEN");
//         welcome.setStyle(
//                 "-fx-font-size : 20; -fx-font-weight : bold; -fx-text-fill : #0B20A7; -fx-text-alignment : center;");
//         welcome.setAlignment(Pos.CENTER);

//         Button tombolProfil = new Button("Profil");
//         tombolProfil.setCursor(Cursor.HAND);
//         tombolProfil.setStyle(
//                 "-fx-pref-width : 65; -fx-pref-height : 65; -fx-background-color : #1E3A8A; -fx-text-fill : white; -fx-font-weight : bold; -fx-background-radius: 10;");

//         Button tombolJadwal = new Button("Jadwal");
//         tombolJadwal.setCursor(Cursor.HAND);
//         tombolJadwal.setStyle(
//                 "-fx-pref-width : 65; -fx-pref-height : 65; -fx-background-color : #1E3A8A; -fx-text-fill : white; -fx-font-weight : bold; -fx-background-radius: 10;");

//         Button tombolFrs = new Button("FRS");
//         tombolFrs.setCursor(Cursor.HAND);
//         tombolFrs.setStyle(
//                 "-fx-pref-width : 65; -fx-pref-height : 65; -fx-background-color : #1E3A8A; -fx-text-fill : white; -fx-font-weight : bold; -fx-background-radius: 10;");

//         tombolProfil.setOnAction(e -> {
//             HalamanProfil profil = new HalamanProfil(stage);
//             stage.setScene(profil.getScene());
//             stage.setTitle("FRS");
//         });

//         tombolJadwal.setOnAction(e -> {
//             HalamanJadwal jadwal = new HalamanJadwal(stage);
//             stage.setScene(jadwal.getScene());
//             stage.setTitle("FRS");

//         });

//         tombolFrs.setOnAction(e -> {
//             HalamanFRS frs = new HalamanFRS(stage);
//             stage.setScene(frs.getScene());
//             stage.setTitle("FRS");
//         });

//         HBox tombolMenu = new HBox(50);
//         tombolMenu.setAlignment(Pos.CENTER);
//         tombolMenu.getChildren().addAll(tombolProfil, tombolJadwal, tombolFrs);

//         VBox bagianMenu = new VBox(30);
//         bagianMenu.setAlignment(Pos.CENTER);
//         bagianMenu.setPadding(new Insets(40));
//         bagianMenu.getChildren().addAll(welcome, tombolMenu);
//         VBox.setVgrow(bagianMenu, Priority.ALWAYS);

//         // Bagian bawah
//         Label namaUniv = new Label("Unirvesitas Jaya Jaya");
//         namaUniv.setStyle("-fx-font-size : 15px; -fx-text-fill : #0B20A7;");

//         HBox bagianBawah = new HBox();
//         bagianBawah.setPrefHeight(100);
//         bagianBawah.setAlignment(Pos.BOTTOM_RIGHT);
//         bagianBawah.getChildren().addAll(namaUniv);

//         layout.getChildren().addAll(header, bagianMenu, bagianBawah);

//         return new Scene(layout, 800, 500);
//     }

// }
