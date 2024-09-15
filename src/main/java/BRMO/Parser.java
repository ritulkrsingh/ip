package BRMO;

import java.text.ParseException;
import java.util.ArrayList;

import BRMO.task.Deadline;
import BRMO.task.Event;
import BRMO.task.TaskList;
import BRMO.task.Todo;

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
    public static String parse(String input, TaskList taskList, Ui ui) throws InvalidCommandException, ParseException {
        assert input != null : "Input should not be null";
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";

        if (input.equals("list")) {
            return ui.showTasks(taskList);
        } else if (checkInput(input, "mark ")) {
            int i = Integer.parseInt(input.substring(5)) - 1;

            taskList.getTask(i).mark();
            return ui.showTaskMarked(taskList.getTask(i));
        } else if (checkInput(input, "unmark ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;

            taskList.getTask(i).unmark();
            return ui.showTaskUnmarked(taskList.getTask(i));
        } else if (checkInput(input, "todo ")) {
            return ui.showTaskAdded(taskList.addTask(new Todo(input.substring(5))), "todo task");
        } else if (checkInput(input, "deadline ")) {
            String[] split = input.substring(9).split(" /by ");
            if (split.length != 2) {
                throw new InvalidCommandException("Invalid deadline format.");
            }

            return ui.showTaskAdded(taskList.addTask(new Deadline(split[0], split[1])), "deadline");
        } else if (checkInput(input, "event ")) {
            String[] split = input.substring(6).split(" /from | /to ");
            if (split.length != 3) {
                throw new InvalidCommandException("Invalid event format.");
            }

            return ui.showTaskAdded(taskList.addTask(new Event(split[0], split[1], split[2])), "event");
        } else if (checkInput(input, "delete ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;

            String res = ui.showTaskRemoved(taskList.getTask(i));
            taskList.removeTask(i);
            return res;
        } else if (checkInput(input, "find ")) {
            String[] split = input.split(" ");
            if (split.length != 2) {
                throw new InvalidCommandException("Invalid search term.");
            }
            String searchTerm = split[1];
            TaskList foundTasks = taskList.find(searchTerm);
            return ui.showFoundTasks(foundTasks);
        } else {
            return ui.showError("Command not found.");
        }
    }
    /**
     * Checks whether the user input starts with a specific command.
     *
     * @param a the user input string
     * @param b the command to check
     * @return true if the input starts with the specified command, false otherwise
     */
    private static boolean checkInput(final String a, final String b) {
        return a.length() >= b.length() && a.substring(0, b.length()).equals(b);
    }
}
