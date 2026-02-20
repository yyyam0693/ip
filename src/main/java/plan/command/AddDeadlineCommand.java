package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

/**
 * Adds a Deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final Task task;

    /**
     * Creates an add-deadline command with the given task.
     *
     * @param task Deadline task to add.
     */
    public AddDeadlineCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task if it is not a duplicate, then saves and shows feedback.
     *
     * @param tasks Task list to add into.
     * @param ui UI handler for printing output.
     * @param storage Storage handler for saving tasks.
     * @throws Exception If saving fails.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception {
        if (tasks.contains(task)) {
            ui.showDuplicate(task);
            return;
        }

        tasks.add(task);
        storage.save(tasks);
        ui.showAdded(task, tasks.size());
    }
}
