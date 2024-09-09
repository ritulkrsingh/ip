package BRMO;
import java.text.ParseException;

/**
 * The Parser class handles the interpretation of user commands.
 * It parses input commands, processes them, and modifies the task list accordingly.
 */
public class Parser {

    /**
     * Parses the user input command and executes the corresponding action.
     *
     * @param input    the input command from the user
     * @param taskList the TaskList to be modified based on the command
     * @param ui       the Ui to handle displaying output to the user
     * @throws InvalidCommandException if the user input is invalid or has incorrect formatting
     * @throws ParseException          if there is an error in parsing date-related commands
     */
    public static void parse(String input, TaskList taskList, Ui ui) throws InvalidCommandException, ParseException {
        
        if (input.equals("list")) {
            ui.showTasks(taskList);
        } else if (checkInput(input, "mark ")) {
            int i = Integer.parseInt(input.substring(5)) - 1;

            taskList.getTask(i).mark();
            ui.showTaskMarked(taskList.getTask(i));
        } else if (checkInput(input, "unmark ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;
            
            taskList.getTask(i).unmark();
            ui.showTaskUnmarked(taskList.getTask(i));
        } else if (checkInput(input, "todo ")) {
            ui.showTaskAdded(taskList.addTask(new Todo(input.substring(5))), "todo task");
        } else if (checkInput(input, "deadline ")) {
            String[] split = input.substring(9).split(" /by ");
            if (split.length != 2) {
                throw new InvalidCommandException("Invalid deadline format.");
            }

            ui.showTaskAdded(taskList.addTask(new Deadline(split[0], split[1])), "deadline");
        } else if (checkInput(input, "event ")) {
            String[] split = input.substring(6).split(" /from | /to ");
            if (split.length != 3) {
                throw new InvalidCommandException("Invalid event format.");
            }

            ui.showTaskAdded(taskList.addTask(new Event(split[0], split[1], split[2])), "event");
        } else if (checkInput(input, "delete ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;

            ui.showTaskRemoved(taskList.getTask(i));
            taskList.removeTask(i);
        } else {
            ui.showError("Command not found.");
        }
    }
    /**
     * Checks whether the user input starts with a specific command.
     *
     * @param a the user input string
     * @param b the command to check
     * @return true if the input starts with the specified command, false otherwise
     */
    private static boolean checkInput(String a, String b) {
        return a.length() >= b.length() && a.substring(0, b.length()).equals(b);
    }
}