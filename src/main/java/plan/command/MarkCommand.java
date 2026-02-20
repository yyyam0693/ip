package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

/**
 * Marks a task as done using its 1-based index.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a mark command for the given 1-based index.
     *
     * @param index 1-based task index to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as done, saves changes, and shows feedback.
     *
     * @param tasks Task list containing the task.
     * @param ui UI handler for printing output.
     * @param storage Storage handler for saving tasks.
     * @throws Exception If the index is invalid or saving fails.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception {
        requireValidIndex(index, tasks.size(), "mark");
        Task t = tasks.get(index - 1);
        t.markAsDone();
        storage.save(tasks);
        ui.showMarked(t, true);
    }
}
