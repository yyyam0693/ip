package plan.command;

import plan.BotException;
import plan.Task;
import plan.storage.Storage;
import plan.ui.Ui;

import java.util.ArrayList;

/**
 * Finds tasks whose descriptions contain a given keyword (case-insensitive).
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a find command with the given keyword.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for matching tasks and prints results via the UI.
     *
     * @param tasks Task list to search through.
     * @param ui UI handler for printing output.
     * @param storage Storage handler (unused).
     * @throws BotException If the keyword is missing or blank.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws BotException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new BotException("Find needs a keyword. Example: find book");
        }

        String key = keyword.trim().toLowerCase();

        ui.showFindHeader();

        int shownIndex = 1;
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(key)) {
                ui.showTaskInList(shownIndex, t);
                shownIndex++;
            }
        }

        if (shownIndex == 1) {
            ui.showNoMatches();
        }

        ui.showLine();
    }

    /**
     * Find does not terminate the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
