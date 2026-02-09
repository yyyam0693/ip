package plan;

import java.util.ArrayList;

import plan.command.Command;
import plan.parser.Parser;
import plan.storage.Storage;
import plan.ui.Ui;

/**
 * The main entry point of the Bot-With-A-Plan application.
 * <p>
 * Handles program initialization and starts the main interaction loop.
 */
public class BotWithAPlan {

    private final Ui ui;
    private final Storage storage;
    private final ArrayList<Task> tasks;

    /**
     * Constructs a BotWithAPlan instance with the given file path.
     *
     * @param filePath Path to the data file used for persistent storage.
     */
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

    /**
     * Runs the main command loop of the chatbot.
     * Reads user input, parses commands, and executes them until exit.
     */
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

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Bot-with-a-plan heard: " + input;
    }

    /**
     * Program entry point.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new BotWithAPlan("data/duke.txt").run();
    }

}
