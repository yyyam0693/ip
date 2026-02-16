package plan;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a generic task with a description and completion status.
 * Subclasses specify concrete task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status icon of the task.
     *
     * @return "X" if completed, otherwise a blank space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Converts the task into a string suitable for file storage.
     *
     * @return A string representation used for saving to disk.
     */
    public abstract String toFileString();

    /**
     * Reconstructs a Task object from a saved file string.
     *
     * @param line A single line from the data file.
     * @return The corresponding Task object.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    public static Task fromFileString(String line) {
        assert line != null : "fromFileString: line should not be null";
        String[] parts = line.split(" \\| ");
        assert parts.length >= 3 : "fromFileString: invalid format, parts=" + parts.length;

        String type = parts[0];
        assert type.equals("T") || type.equals("D") || type.equals("E") : "fromFileString: unknown type " + type;

        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        switch (type) {
            case "T":
                return new Todo(desc, isDone);
            case "D":
                return new Deadline(desc, LocalDate.parse(parts[3]), isDone);
            case "E":
                return new Event(desc, parts[3], parts[4], isDone);
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
    }

    private String getDuplicateKey() {
        String normalizedDesc = description == null ? "" : description.trim().toLowerCase(Locale.ROOT);
        return getClass().getName() + "|" + normalizedDesc;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Task otherTask = (Task) other;
        return Objects.equals(this.getDuplicateKey(), otherTask.getDuplicateKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDuplicateKey());
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
