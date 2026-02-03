package plan.command;

import plan.BotException;
import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws BotException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new BotException("Find needs a keyword. Example: find book");
        }

        String key = keyword.trim().toLowerCase();

        ui.showLine();
        System.out.println(" Here are the matching tasks in your list:");

        int shownIndex = 1;
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(key)) {
                System.out.println(" " + shownIndex + ". " + t);
                shownIndex++;
            }
        }

        if (shownIndex == 1) {
            System.out.println(" (no matches)");
        }

        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
