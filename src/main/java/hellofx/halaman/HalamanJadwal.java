package hellofx.halaman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HalamanJadwal {
    private Stage stage;

    public HalamanJadwal(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #white;");

        HBox header = new HBox();
        header.setPrefHeight(50);
        header.setStyle("-fx-background-color: #1E3A8A;");
        header.setPadding(new Insets(10, 15, 10, 15));

        Label label = new Label("JADWAL");
        label.setStyle("-fx-text-fill : #FFFFFF; -fx-font-size : 25; -fx-font-weight : 700;");
        label.setLayoutY(25);
        header.getChildren().add(label);

        VBox menu = new VBox();
        menu.setPadding(new Insets(10, 0, 10, 0));
        menu.setSpacing(25);
        menu.setPrefWidth(200);

        Button tombolProfil = new Button("Profil");
        tombolProfil.setCursor(Cursor.HAND);
        tombolProfil.setStyle(styleTombol());

        Button tombolFrs = new Button("FRS");
        tombolFrs.setCursor(Cursor.HAND);
        tombolFrs.setStyle(styleTombol());

        Button tombolBeranda = new Button("Beranda");
        tombolBeranda.setCursor(Cursor.HAND);
        tombolBeranda.setStyle(styleTombol());

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

        menu.getChildren().addAll(tombolBeranda, tombolFrs, tombolProfil);

        VBox kolomSenin = kolomHari("Senin");
        VBox kolomSelasa = kolomHari("Selasa");
        VBox kolomRabu = kolomHari("Rabu");
        VBox kolomKamis = kolomHari("Kamis");
        VBox kolomJumat = kolomHari("Jumat");
        VBox kolomSabtu = kolomHari("Sabtu");

        HBox jadwal = new HBox();
        jadwal.setStyle("-fx-background-color : white");
        jadwal.setFillHeight(true);
        jadwal.setPadding(new Insets(0));
        VBox.setVgrow(jadwal, Priority.ALWAYS);
        HBox.setHgrow(jadwal, Priority.ALWAYS);

        jadwal.setSpacing(10);
        jadwal.setPadding(new Insets(0, 5, 10, 5));
        jadwal.getChildren().addAll(kolomSenin, kolomSelasa, kolomRabu, kolomKamis, kolomJumat, kolomSabtu);

        for (var kolom : jadwal.getChildren()) {
            HBox.setHgrow((VBox) kolom, Priority.ALWAYS);
        }

        HBox bagianTengah = new HBox();
        bagianTengah.setStyle("-fx-background-color : white");
        bagianTengah.setPadding(new Insets(35, 0, 0, 0));
        VBox.setVgrow(bagianTengah, Priority.ALWAYS);
        bagianTengah.getChildren().addAll(menu, jadwal);

        HBox bagianBawah = new HBox();
        bagianBawah.setPrefHeight(100);
        bagianBawah.setStyle("-fx-background-color : white");

        layout.getChildren().addAll(header, bagianTengah, bagianBawah);

        return new Scene(layout, 1200, 750);
    }

    private VBox kolomHari(String hari) {
        Label label = new Label(hari);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(6, 3, 6, 3));
        label.setPrefWidth(100);
        label.setStyle(
                "-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12; -fx-background-radius: 16;");

        VBox kolom = new VBox();
        kolom.setPadding(new Insets(0, 8, 0, 0));
        kolom.setAlignment(Pos.TOP_CENTER);
        kolom.setStyle("-fx-border-color: transparent #1E3A8A transparent transparent; -fx-border-width: 0 1 0 0;");
        kolom.getChildren().addAll(label);

        return kolom;
    }

    private String styleTombol() {
        return "-fx-pref-width : 100; -fx-pref-height : 100; -fx-background-color : #1E3A8A; -fx-text-fill : white; -fx-font-weight : bold; -fx-background-radius: 10;";
    }
}
