package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Shows the goodbye message.
     *
     * @param tasks Current task list (unused).
     * @param ui UI handler for printing output.
     * @param storage Storage handler (unused).
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Indicates that this command terminates the program.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
