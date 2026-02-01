package plan.command;

import plan.BotException;
import plan.storage.Storage;
import plan.ui.Ui;

import java.util.ArrayList;
import plan.Task;

public abstract class Command {
    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws Exception;

    public boolean isExit() {
        return false;
    }

    protected void requireValidIndex(int idx, int size, String action) throws BotException {
        if (idx < 1 || idx > size) {
            throw new BotException(action + " needs a valid task number.");
        }
    }
}
