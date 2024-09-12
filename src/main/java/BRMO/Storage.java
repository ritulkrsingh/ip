package BRMO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws IOException, ParseException, InvalidCommandException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

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
            default:
                throw new InvalidCommandException("Unknown task type: " + split[0]);
            }
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        PrintWriter writer = new PrintWriter(file);
        for (Task task : tasks) {
            writer.println(task.toText());
        }
        writer.close();
    }
}
