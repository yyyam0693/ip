package plan.ui;

import java.util.Scanner;

import plan.Task;

/**
 * Handles all interactions with the user.
 * Responsible for displaying messages and reading input.
 */
public class Ui {

    private static final String LINE = "____________________________________________";

    private final Scanner scanner = new Scanner(System.in);

    // Stores output for GUI usage
    private final StringBuilder output = new StringBuilder();

    /* ===================== Utility Methods ===================== */

    public void clearOutput() {
        output.setLength(0);
    }

    public String getOutput() {
        return output.toString();
    }

    private void appendLine(String message) {
        System.out.println(message);      // still prints for CLI
        output.append(message).append(System.lineSeparator());
    }

    /* ===================== User Interaction ===================== */

    public void showGreeting() {
        String logo = " ____        _   \n"
                + "| __ )  ___ | |_    \n"
                + "|  _ \\ / _ \\| __|  \n"
                + "| |_) | (_) | |_    \n"
                + "|____/ \\___/ \\__|  \n";

        showLine();
        appendLine(" Hello! I'm Bot-With-A-Plan, a bot with a plan.");
        appendLine(" I have a plan... (that isn't planned yet). What can I do for you?");
        appendLine(logo);
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        appendLine(LINE);
    }

    public void showError(String msg) {
        showLine();
        appendLine(" " + msg);
        showLine();
    }

    public void showBye() {
        showLine();
        appendLine(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void showListHeader() {
        showLine();
        appendLine(" I have a plan. Here are the tasks in your list:");
    }

    public void showTaskInList(int displayIndex, Task task) {
        appendLine(" " + displayIndex + ". " + task);
    }

    public void showListFooter() {
        showLine();
    }

    public void showAdded(Task task, int count) {
        showLine();
        appendLine(" Got the plan. I've added this task:");
        appendLine(" " + task);
        appendLine(" Now you have " + count + " tasks in the list.");
        showLine();
    }

    public void showMarked(Task task, boolean isDone) {
        showLine();
        if (isDone) {
            appendLine(" OK, the plan is to mark this task as done, and ive gone ahead with the plan:");
        } else {
            appendLine(" OK, the plan is to mark this task as not done, and ive gone ahead with the plan:");
        }
        appendLine(" " + task);
        showLine();
    }

    public void showDeleted(Task removed, int count) {
        showLine();
        appendLine(" Noted. I've removed this task:");
        appendLine(" " + removed);
        appendLine(" Now you have " + count + " tasks in the list.");
        showLine();
    }

    // AI-assisted: Duplicate detection logic was designed with guidance from ChatGPT to improve code robustness.
    public void showDuplicate(Task task) {
        showLine();
        appendLine(" Nope. This task already exists:");
        appendLine(" " + task);
        showLine();
    }
}
