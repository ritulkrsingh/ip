package BRMO;

import java.text.ParseException;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainWindow {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private BRMO brmo;

    @FXML
    public void initialize() throws InvalidCommandException, ParseException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        brmo = new BRMO();
    }

    @FXML
    private void handleUserInput() throws ParseException {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            String response = brmo.getResponse(input);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getBotDialog(response)
            );
            userInput.clear();

            if (input.equalsIgnoreCase("bye")) {
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
        }
    }
}