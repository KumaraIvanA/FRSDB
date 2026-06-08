package hellofx.halaman.Common;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class TopBar {
    public static HBox create(Object obj, String title) {
        HBox topBar = new HBox(25);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(0, 35, 0, 16));
        topBar.setPrefHeight(68);
        topBar.setStyle("-fx-background-color: #243F91;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView notif = new ImageView(new Image(obj.getClass().getResourceAsStream("/Gambar/notification.png")));
        notif.setFitWidth(28);
        notif.setFitHeight(28);
        notif.setPreserveRatio(true);

        ImageView profile = new ImageView(new Image(obj.getClass().getResourceAsStream("/Gambar/user (2).png")));
        profile.setFitWidth(28);
        profile.setFitHeight(28);
        profile.setPreserveRatio(true);
        topBar.getChildren().addAll(titleLabel, spacer, notif, profile);

        return topBar;
    }
	
}
