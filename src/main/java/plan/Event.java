package plan;

public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Creates an Event task.
     *
     * @param description Description of the event.
     * @param from Start date/time.
     * @param to End date/time.
     */
    public Event(String desc, String from, String to){
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event task with a specified completion status.
     *
     * @param description Description of the event.
     * @param from Start date/time.
     * @param to End date/time.
     * @param isDone Whether the task is completed.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }



}
