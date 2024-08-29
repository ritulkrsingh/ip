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
            } else if(checkInput(input, 0, 5, "mark ")) {
                int taskN = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskN].mark();
                System.out.println("Nice. The following task has been marked as done:");
                System.out.println(tasks[taskN]);
            } else if(checkInput(input, 0, 7, "unmark ")) {
                int taskN = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskN].unmark();
                System.out.println("Nice. The following task has been marked as undone:");
                System.out.println(tasks[taskN]);
            } else if(listN < 100) {
                tasks[listN++] = new Task(input);
                System.out.println("added: " + input);
            }
        }

        System.out.println("We shall meet again.");
        scanner.close();
    }
    private static boolean checkInput(String a, int l, int r, String b) {
        return a.length() >= (r - l) && a.substring(l, r).equals(b);
    }
}
