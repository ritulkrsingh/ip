import java.util.Scanner;

public class BRMO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm BetaRMinusOne; a.k.a. BRMO.");

        Task tasks[] = new Task[100];
        int listN = 0;

        for(String input = scanner.nextLine(); !input.equals("bye"); input = scanner.nextLine()) {
            
            if(input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < listN; ++i) {
                    System.out.println(i + 1 + "." + tasks[i]);
                }
            } else if(checkInput(input, "mark ")) {
                int taskN = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskN].mark();
                System.out.println("Nice. The following task has been marked as done:");
                System.out.println(tasks[taskN]);
            } else if(checkInput(input, "unmark ")) {
                int taskN = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskN].unmark();
                System.out.println("Nice. The following task has been marked as undone:");
                System.out.println(tasks[taskN]);
            } else if(checkInput(input, "todo ")) {
                tasks[listN++] = new Todo(input.substring(5));
                System.out.println("Added the following todo task:");
                System.out.println(tasks[listN - 1]);
            } else if(checkInput(input, "deadline ")) {
                String[] split = input.substring(9).split(" /by ");
                tasks[listN++] = new Deadline(split[0], split[1]);
                System.out.println("Added the following deadline:");
                System.out.println(tasks[listN - 1]);
            } else if(checkInput(input, "event ")) {
                String[] split = input.substring(6).split(" /from | /to ");
                tasks[listN++] = new Event(split[0], split[1], split[2]);
                System.out.println("Added the following event:");
                System.out.println(tasks[listN - 1]);
            } else {
                System.out.println("Command not found.");
            }
        }

        System.out.println("We shall meet again.");
        scanner.close();
    }
    private static boolean checkInput(String a, String b) {
        return a.length() >= b.length() && a.substring(0, b.length()).equals(b);
    }
}
