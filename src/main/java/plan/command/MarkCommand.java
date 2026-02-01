package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception {
        requireValidIndex(index, tasks.size(), "mark");
        Task t = tasks.get(index - 1);
        t.markAsDone();
        storage.save(tasks);
        ui.showMarked(t, true);
    }
}
