package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

/**
 * Deletes a task from the task list by its 1-based index.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a delete command for the given 1-based index.
     *
     * @param index 1-based task index to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the given index, saves changes, and shows feedback.
     *
     * @param tasks Task list to delete from.
     * @param ui UI handler for printing output.
     * @param storage Storage handler for saving tasks.
     * @throws Exception If the index is invalid or saving fails.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception {
        requireValidIndex(index, tasks.size(), "delete");
        Task removed = tasks.remove(index - 1);
        storage.save(tasks);
        ui.showDeleted(removed, tasks.size());
    }
}
