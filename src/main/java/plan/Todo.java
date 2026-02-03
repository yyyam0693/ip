package plan;

/**
 * Represents a simple to-do task without any date or time.
 */
public class Todo extends Task{

    /**
     * Creates a Todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String desc){
        super(desc);
    }

    /**
     * Creates a Todo task with a specified completion status.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is completed.
     */
    public Todo(String desc, boolean isDone){
        super(desc);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }


}
