package plan.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import plan.Task;


public class Storage {
    private final Path filePath;

    public Storage(String path) {
        this.filePath = Paths.get(path);
    }

    public ArrayList<Task> load() throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : Files.readAllLines(filePath)) {
            tasks.add(Task.fromFileString(line));
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());

        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toFileString());
        }
        Files.write(filePath, lines);
    }

}
