package BRMO;

import java.io.IOException;
import java.text.ParseException;

public class BRMO {
    public static void main(String[] args) throws InvalidCommandException, ParseException {
        Ui ui = new Ui();
        ui.showWelcome();

        Storage storage = new Storage("./data/BRMO.txt");
        TaskList taskList = new TaskList();

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("An error occurred while reading the data file: " + e.getMessage());
        } catch (ParseException e) {
            ui.showError("An error occurred while parsing the date: " + e.getMessage());
        } catch (InvalidCommandException e) {
            ui.showError("Invalid command: " + e.getMessage());
        }

        while (true) {
            String input = ui.readCommand();
            if (input.equals("bye")) {
                break;
            }
            try {
                Parser.parse(input, taskList, ui);
            } catch (InvalidCommandException e) {
                ui.showError(e.getMessage());
            }
        }

        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            ui.showError("An error occurred while saving the data file: " + e.getMessage());
        }

        ui.showGoodbye();
    }
}
