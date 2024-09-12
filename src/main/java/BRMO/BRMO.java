package BRMO;

import java.io.IOException;
import java.text.ParseException;

/**
 * The main class for the BRMO task management application.
 * This class initializes the user interface, loads tasks from storage,
 * processes user commands, and saves tasks to storage upon exiting.
 */
public class BRMO {

    /**
     * The main entry point of the application.
     *
     * @param args command line arguments (not used)
     * @throws InvalidCommandException if an invalid command is entered
     * @throws ParseException if a task's date is in an invalid format
     */
    public static void main(String[] args) throws InvalidCommandException, ParseException {
        Ui ui = new Ui();
        ui.showWelcome();

        Storage storage = new Storage("./data/BRMO.txt");
        TaskList taskList = new TaskList();

        // Load tasks from storage, handle possible exceptions
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("An error occurred while reading the data file: " + e.getMessage());
        } catch (ParseException e) {
            ui.showError("An error occurred while parsing the date: " + e.getMessage());
        } catch (InvalidCommandException e) {
            ui.showError("Invalid command: " + e.getMessage());
        }

        // Main command loop
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

        // Save tasks to storage, handle possible exceptions
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            ui.showError("An error occurred while saving the data file: " + e.getMessage());
        }

        ui.showGoodbye();
    }
}
