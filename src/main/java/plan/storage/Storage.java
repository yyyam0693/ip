package plan.storage;

import plan.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to a file for persistent storage.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a Storage object using the specified file path.
     *
     * @param path Path to the data file.
     */
    public Storage(String path) {
        this.filePath = Paths.get(path);
    }

    /**
     * Loads tasks from the data file.
     *
     * @return A list of tasks loaded from storage.
     * @throws IOException If an error occurs while reading the file.
     */
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

    /**
     * Saves the given list of tasks to the data file.
     *
     * @param tasks The tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
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
