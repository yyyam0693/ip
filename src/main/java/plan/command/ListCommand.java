package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

/**
 * Lists all tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Prints the full task list via the UI.
     *
     * @param tasks Task list to display.
     * @param ui UI handler for printing output.
     * @param storage Storage handler (unused).
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.showListHeader();
        for (int i = 0; i < tasks.size(); i++) {
            ui.showTaskInList(i + 1, tasks.get(i));
        }
        ui.showListFooter();
    }
}
