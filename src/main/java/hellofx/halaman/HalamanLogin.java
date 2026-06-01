package hellofx.halaman;

import hellofx.Database.KoneksiDB;
import hellofx.kelasData.Mahasiswa;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HalamanLogin {
        private Stage stage;

        public HalamanLogin(Stage stage) {
                this.stage = stage;
        }

        public Scene getScene() {
                VBox layout = new VBox();
                Label loginTitle = new Label("Login");
                loginTitle.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: white;");

                HBox headerBox = new HBox(loginTitle);
                headerBox.setAlignment(Pos.CENTER);
                headerBox.setPrefHeight(70);
                headerBox.setPrefWidth(430);
                headerBox.setStyle(
                                "-fx-background-color: #0f497e;" +
                                                "-fx-background-radius: 12 12 0 0;");

                TextField emailField = new TextField();
                emailField.setPromptText("Email");
                emailField.setPrefWidth(380);
                emailField.setPrefHeight(38);
                emailField.setStyle(
                                "-fx-background-color : white;" +
                                                "-fx-background-radius: 15;" +
                                                "-fx-border-color: #D0D0D0;" +
                                                "-fx-border-radius: 15;" +
                                                "-fx-border-width: 1.5;" +
                                                "-fx-padding: 0 15 0 15;" +
                                                "-fx-font-weight: bold;");

                PasswordField passwordField = new PasswordField();
                passwordField.setPromptText("Password");
                passwordField.setPrefWidth(380);
                passwordField.setPrefHeight(38);
                passwordField.setStyle(
                                "-fx-background-color : white;" +
                                                "-fx-background-radius: 15;" +
                                                "-fx-border-color: #D0D0D0;" +
                                                "-fx-border-radius: 15;" +
                                                "-fx-border-width: 1.5;" +
                                                "-fx-padding: 0 15 0 15;" +
                                                "-fx-font-weight: bold;");

                Button loginButton = new Button("Continue");
                loginButton.setPrefWidth(200);
                loginButton.setPrefHeight(50);
                loginButton.setStyle(
                                "-fx-background-color: #0f497e;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-custor: hand;");

                Label messageLabel = new Label();

                loginButton.setOnAction(e -> {
                        String email = emailField.getText();
                        String password = passwordField.getText();

                        KoneksiDB.hubungkan();

                        if (email.isEmpty() || password.isEmpty()) {
                                messageLabel.setText("Tolong input email atau password");
                                messageLabel.setStyle("-fx-text-fill : red;");
                                return;
                        }

                        boolean loginberhasil = KoneksiDB.checkLogin(email, password);
                        boolean loginDosenberhail = KoneksiDB.checkLoginDosen(email, password);

                        if (loginberhasil) {
                                Mahasiswa mahasiswa = KoneksiDB.getDataMahasiswa(email);

                                HalamanBeranda beranda = new HalamanBeranda(stage, mahasiswa);
                                stage.setScene(beranda.getScene());
                                stage.setTitle("FRS");
                        } else if (loginDosenberhail){
                                // HalamanBerandaDosen berandaDosen = new HalamanBerandaDosen(stage);
                                // stage.setScene(berandaDosen.getScene());
                                stage.setTitle("FRS");
                        }else{
                                messageLabel.setText("Salah email atau password");
                                messageLabel.setStyle("-fx-text-fill: red;");
                        }
                });

                VBox formBox = new VBox(20);
                formBox.setAlignment(Pos.CENTER);
                formBox.setPadding(new Insets(28));
                formBox.setPrefWidth(430);
                formBox.setStyle(
                                "-fx-background-color: #ECEFF1;" +
                                                "-fx-background-radius: 0 0 12 12;");

                formBox.getChildren().addAll(
                                emailField,
                                passwordField,
                                loginButton,
                                messageLabel);

                VBox loginCard = new VBox();
                loginCard.setAlignment(Pos.CENTER);
                loginCard.setMaxWidth(430);
                loginCard.getChildren().addAll(headerBox, formBox);

                StackPane whiteArea = new StackPane();
                whiteArea.setStyle("-fx-background-color: white;");
                whiteArea.setPrefSize(860, 520);
                whiteArea.setPadding(new Insets(80, 0, 0, 0));
                whiteArea.getChildren().add(loginCard);
                StackPane.setAlignment(loginCard, Pos.TOP_CENTER);

                VBox mainLayout = new VBox(10);

                mainLayout.setPadding(new Insets(15, 18, 18, 18));
                mainLayout.setStyle("-fx-background-color: #FFFFFF;");
                mainLayout.setPadding(new Insets(0));
                mainLayout.setStyle("-fx-background-color: white;");
                mainLayout.getChildren().addAll(whiteArea);

                return new Scene(mainLayout, 1200, 750);
        }
}
