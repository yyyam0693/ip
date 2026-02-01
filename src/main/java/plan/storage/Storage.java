package plan.storage;

import plan.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            if (line == null) {
                continue;
            }
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            try {
                tasks.add(Task.fromFileString(trimmed));
            } catch (RuntimeException e) {
                // Corrupted/unknown line format -> skip line (stretch handling)
                // If you prefer: throw new IOException("Corrupted data file", e);
                continue;
            }
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toFileString());
        }

        Files.write(filePath, lines);
    }
}
