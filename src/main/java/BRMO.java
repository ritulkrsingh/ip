import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BRMO {
    public static void main(String[] args) throws InvalidCommandException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm BetaRMinusOne; a.k.a. BRMO.");

        ArrayList<Task> tasks = new ArrayList<Task>();

        File file = new File("./data/BRMO.txt");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the data file: " + e.getMessage());
            }
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] split = line.split(" \\| ");

                switch (split[0]) {
                case "T":
                    tasks.add(new Todo(split[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(split[2], split[3]));
                    break;
                case "E":
                    tasks.add(new Event(split[2], split[3], split[4]));
                    break;
                }
                if (split[1].equals("1")) {
                    tasks.get(tasks.size() - 1).mark();
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data file: " + e.getMessage());
        }

        for (String input = scanner.nextLine(); !input.equals("bye"); input = scanner.nextLine()) {
            
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); ++i) {
                    System.out.println(i + 1 + "." + tasks.get(i));
                }
            } else if (checkInput(input, "mark ")) {
                int i = Integer.parseInt(input.substring(5)) - 1;
                if (i < 0 || i >= tasks.size()) {
                    throw new InvalidCommandException("Invalid task number.");
                }

                tasks.get(i).mark();
                System.out.println("Nice. The following task has been marked as done:");
                System.out.println(tasks.get(i));
            } else if (checkInput(input, "unmark ")) {
                int i = Integer.parseInt(input.substring(7)) - 1;
                if (i < 0 || i >= tasks.size()) {
                    throw new InvalidCommandException("Invalid task number.");
                }
                
                tasks.get(i).unmark();
                System.out.println("Nice. The following task has been marked as undone:");
                System.out.println(tasks.get(i));
            } else if (checkInput(input, "todo ")) {
                tasks.add(new Todo(input.substring(5)));
                System.out.println("Added the following todo task:");
                System.out.println(tasks.get(tasks.size() - 1));
            } else if (checkInput(input, "deadline ")) {
                String[] split = input.substring(9).split(" /by ");
                if (split.length != 2) {
                    throw new InvalidCommandException("Invalid deadline format.");
                }

                tasks.add(new Deadline(split[0], split[1]));
                System.out.println("Added the following deadline:");
                System.out.println(tasks.get(tasks.size() - 1));
            } else if (checkInput(input, "event ")) {
                String[] split = input.substring(6).split(" /from | /to ");
                if (split.length != 3) {
                    throw new InvalidCommandException("Invalid event format.");
                }

                tasks.add(new Event(split[0], split[1], split[2]));
                System.out.println("Added the following event:");
                System.out.println(tasks.get(tasks.size() - 1));
            } else if (checkInput(input, "delete ")) {
                int i = Integer.parseInt(input.substring(7)) - 1;
                if (i < 0 || i >= tasks.size()) {
                    throw new InvalidCommandException("Invalid task number.");
                }

                System.out.println("The following task has been removed:");
                System.out.println(tasks.get(i));
                tasks.remove(i);
            } else {
                System.out.println("Command not found.");
            }
        }

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toText() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the data file: " + e.getMessage());
        }

        System.out.println("We shall meet again.");
        scanner.close();
    }
    private static boolean checkInput(String a, String b) {
        return a.length() >= b.length() && a.substring(0, b.length()).equals(b);
    }
}
