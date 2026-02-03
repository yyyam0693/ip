package plan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {

    private final LocalDate by;
    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates a Deadline task.
     *
     * @param description Description of the task.
     * @param by Due date in yyyy-mm-dd format.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by, INPUT);
    }

    /**
     * Creates a Deadline task with a specified completion status.
     *
     * @param description Description of the task.
     * @param by Due date.
     * @param isDone Whether the task is completed.
     */
    public Deadline(String desc, LocalDate by, boolean isDone) {
        super(desc);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

}
