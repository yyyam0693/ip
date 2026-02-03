package plan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate by;
    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by, INPUT);
    }

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
