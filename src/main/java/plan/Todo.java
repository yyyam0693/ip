package plan;

public class Todo extends Task{
    public Todo(String desc){
        super(desc);
    }

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
