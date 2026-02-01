package plan;

import java.util.ArrayList;

import plan.command.Command;
import plan.parser.Parser;
import plan.storage.Storage;
import plan.ui.Ui;

public class BotWithAPlan {

    private final Ui ui;
    private final Storage storage;
    private final ArrayList<Task> tasks;

    public BotWithAPlan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        ArrayList<Task> loaded;
        try {
            loaded = storage.load();
        } catch (Exception e) {
            loaded = new ArrayList<>();
        }
        tasks = loaded;
    }

    public void run() {
        ui.showGreeting();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            if (input.trim().isEmpty()) {
                continue;
            }

            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BotException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                // Keep it simple: unexpected errors
                ui.showError("something went wrong... but i still have a plan.");
            }
        }
    }

    public static void main(String[] args) {
        new BotWithAPlan("data/duke.txt").run();
    }

}
