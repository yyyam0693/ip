package plan;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String toFileString();

    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        switch (type) {
            case "T":
                return new Todo(desc, isDone);
            case "D":
                return new Deadline(desc, parts[3], isDone);
            case "E":
                return new Event(desc, parts[3], parts[4], isDone);
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
