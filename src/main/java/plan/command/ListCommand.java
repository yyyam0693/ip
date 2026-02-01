package plan.command;

import java.util.ArrayList;

import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.showListHeader();
        for (int i = 0; i < tasks.size(); i++) {
            ui.showTaskInList(i + 1, tasks.get(i));
        }
        ui.showListFooter();
    }
}
