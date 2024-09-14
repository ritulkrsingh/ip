package BRMO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private DialogBox(String text, ImageView displayPicture) {
        this.text = new Label(text);
        this.displayPicture = displayPicture;

        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(50.0);
        this.displayPicture.setFitHeight(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.displayPicture);
    }

    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox(text, new ImageView(new Image(DialogBox.class.getResourceAsStream("/images/user.png"))));
        db.setAlignment(Pos.TOP_RIGHT);
        db.getChildren().setAll(db.text, db.displayPicture);
        HBox.setMargin(db.displayPicture, new Insets(0, 0, 0, 4));
        HBox.setMargin(db.text, new Insets(0,4, 0, 0));
        return db;
    }

    public static DialogBox getBotDialog(String text) {
        var db = new DialogBox(text, new ImageView(new Image(DialogBox.class.getResourceAsStream("/images/bot.png"))));
        db.setAlignment(Pos.TOP_LEFT);
        db.getChildren().setAll(db.displayPicture, db.text);
        HBox.setMargin(db.displayPicture, new Insets(0, 0, 0, 4));
        HBox.setMargin(db.text, new Insets(0, 0, 0, 4));
        return db;
    }
}