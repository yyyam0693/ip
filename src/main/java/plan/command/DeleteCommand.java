package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception {
        requireValidIndex(index, tasks.size(), "delete");
        Task removed = tasks.remove(index - 1);
        storage.save(tasks);
        ui.showDeleted(removed, tasks.size());
    }
}
