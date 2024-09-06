package BRMO;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm BetaRMinusOne; a.k.a. BRMO.");
    }

    public void showGoodbye() {
        System.out.println("We shall meet again.");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTasks(TaskList taskList) throws InvalidCommandException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println(i + 1 + "." + taskList.getTask(i));
        }
    }

    public void showTaskAdded(Task task, String type) {
        System.out.println("Added the following " + type + ":");
        System.out.println(task);
    }

    public void showTaskRemoved(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice. The following task has been marked as done:");
        System.out.println(task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Nice. The following task has been marked as undone:");
        System.out.println(task);
    }
}