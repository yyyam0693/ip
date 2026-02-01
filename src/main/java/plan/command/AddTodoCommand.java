package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

public class AddTodoCommand extends Command {
    private final Task task;

    public AddTodoCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception {
        tasks.add(task);
        storage.save(tasks);
        ui.showAdded(task, tasks.size());
    }
}
