package BRMO;

import java.util.Scanner;

/**
 * Handles user interaction and displays output to the console.
 * Provides methods to show messages, handle errors, read user input, 
 * and display task-related information.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm BetaRMinusOne; a.k.a. BRMO.");
    }

    /**
     * Displays the goodbye message when the application ends.
     */
    public void showGoodbye() {
        System.out.println("We shall meet again.");
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Reads a command from the user input.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays all tasks in the task list.
     * If the task list is empty, it shows a message indicating there are no tasks.
     *
     * @param taskList the list of tasks to be displayed
     * @throws InvalidCommandException if an invalid task index is encountered
     */
    public void showTasks(TaskList taskList) throws InvalidCommandException {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println(i + 1 + "." + taskList.getTask(i));
        }
    }

    /**
     * Displays a message when a task is added to the list.
     *
     * @param task the task that was added
     * @param type the type of task (e.g., todo, deadline, event)
     */
    public void showTaskAdded(Task task, String type) {
        System.out.println("Added the following " + type + ":");
        System.out.println(task);
    }

    /**
     * Displays a message when a task is removed from the list.
     *
     * @param task the task that was removed
     */
    public void showTaskRemoved(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task the task that was marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice. The following task has been marked as done:");
        System.out.println(task);
    }

    /**
     * Displays a message when a task is marked as undone.
     *
     * @param task the task that was marked as undone
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Nice. The following task has been marked as undone:");
        System.out.println(task);
    }

    public void showFoundTasks(TaskList tasks) {
        if (tasks.size() < 1) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(tasks);
        }
    }
}
