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

    public void showGreeting() {
        String logo = " ____        _   \n"
                + "| __ )  ___ | |_    \n"
                + "|  _ \\ / _ \\| __|  \n"
                + "| |_) | (_) | |_    \n"
                + "|____/ \\___/ \\__|  \n";

        showLine();
        System.out.println(" Hello! I'm Bot-With-A-Plan, a bot with a plan.");
        System.out.println(" I have a plan... (that isn't planned yet). What can I do for you?");
        System.out.println(logo);
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String msg) {
        showLine();
        System.out.println(" " + msg);
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void showListHeader() {
        showLine();
        System.out.println(" I have a plan. Here are the tasks in your list:");
    }

    public void showTaskInList(int displayIndex, Task task) {
        System.out.println(" " + displayIndex + ". " + task);
    }

    public void showListFooter() {
        showLine();
    }

    public void showAdded(Task task, int count) {
        showLine();
        System.out.println(" Got the plan. I've added this task:");
        System.out.println(" " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        showLine();
    }

    public void showMarked(Task task, boolean isDone) {
        showLine();
        if (isDone) {
            System.out.println(" OK, the plan is to mark this task as done, and ive gone ahead with the plan:");
        } else {
            System.out.println(" OK, the plan is to mark this task as not done, and ive gone ahead with the plan:");
        }
        System.out.println(" " + task);
        showLine();
    }

    public void showDeleted(Task removed, int count) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + removed);
        System.out.println(" Now you have " + count + " tasks in the list.");
        showLine();
    }
}
