package BRMO;

import BRMO.task.Task;
import BRMO.task.TaskList;

public class Ui {

    public Ui() {}

    public String showWelcome() {
        return "Hello! I'm BetaRMinusOne; a.k.a. BRMO.";
    }

    public String showGoodbye() {
        return "We shall meet again.";
    }

    public String showError(String message) {
        return "Error: " + message;
    }

    // /**
    //  * Reads a command from the user input.
    //  *
    //  * @return the command entered by the user
    //  */
    // public String readCommand() {
    //     return scanner.nextLine();
    // }

    public String showTasks(TaskList taskList) throws InvalidCommandException {
        if (taskList.size() == 0) {
            return "There are no tasks in your list.";
        }
        String res = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); ++i) {
            res += i + 1 + "." + taskList.getTask(i) + "\n";
        }

        return res;
    }

    public String showTaskAdded(Task task, String type) {
        return "Added the following " + type + ":\n" + task;
    }

    public String showTaskRemoved(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    public String showTaskMarked(Task task) {
        return "Nice. The following task has been marked as done:\n" + task;
    }

    public String showTaskUnmarked(Task task) {
        return "Nice. The following task has been marked as undone:\n" + task;
    }

    public String showFoundTasks(TaskList tasks) {
        if (tasks.size() < 1) {
            return "No tasks found.";
        }
        return "Here are the matching tasks in your list:\n" + tasks;
    }
}
