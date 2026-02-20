package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

/**
 * Marks a task as not done using its 1-based index.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an unmark command for the given 1-based index.
     *
     * @param index 1-based task index to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as not done, saves changes, and shows feedback.
     *
     * @param tasks Task list containing the task.
     * @param ui UI handler for printing output.
     * @param storage Storage handler for saving tasks.
     * @throws Exception If the index is invalid or saving fails.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception {
        requireValidIndex(index, tasks.size(), "unmark");
        Task t = tasks.get(index - 1);
        t.markAsNotDone();
        storage.save(tasks);
        ui.showMarked(t, false);
    }
}
