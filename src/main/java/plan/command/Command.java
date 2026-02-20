package plan.command;

import plan.BotException;
import plan.storage.Storage;
import plan.ui.Ui;

import java.util.ArrayList;
import plan.Task;

/**
 * Represents a user command that can be executed by the bot.
 * Each command performs an action on the task list and may update storage and UI.
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param tasks Task list to operate on.
     * @param ui UI handler for printing output.
     * @param storage Storage handler for saving tasks.
     * @throws Exception If the command cannot be executed.
     */
    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Returns whether this command should terminate the program.
     *
     * @return True if the program should exit after this command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Ensures a 1-based task index is within range.
     *
     * @param idx 1-based index provided by the user.
     * @param size Current size of the task list.
     * @param action Action name used for error messages (e.g. "delete", "mark").
     * @throws BotException If the index is out of range.
     */
    protected void requireValidIndex(int idx, int size, String action) throws BotException {
        if (idx < 1 || idx > size) {
            throw new BotException(action + " needs a valid task number.");
        }
    }
}
