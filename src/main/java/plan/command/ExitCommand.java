package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
