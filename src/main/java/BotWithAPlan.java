import java.util.Scanner;

public class BotWithAPlan {

    private static final int MAX_TASKS = 100;
    private static final String LINE = "____________________________________________";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;
        printGreeting();

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            if (input.equals("list")) {
                printLine();
                System.out.println(" I have a plan. Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                printLine();
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = parseIndex(input, "mark ");
                if (index >= 1 && index <= taskCount) {
                    Task t = tasks[index - 1];
                    t.markAsDone();
                    printLine();
                    System.out.println(" OK, the plan is to mark this task as done, and ive gone ahead with the plan:");
                    System.out.println(" " + t);
                    printLine();
                }
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = parseIndex(input, "unmark ");
                if (index >= 1 && index <= taskCount) {
                    Task t = tasks[index - 1];
                    t.markAsNotDone();
                    printLine();
                    System.out.println(" OK, the plan is to mark this task as not done, and ive gone ahead with the plan:");
                    System.out.println(" " + t);
                    printLine();
                }
                continue;
            }

            // add task
            tasks[taskCount] = new Task(input);
            taskCount++;

            printLine();
            System.out.println("added: " + input);
            printLine();
        }
    }

    private static int parseIndex(String input, String prefix) {
        try {
            return Integer.parseInt(input.substring(prefix.length()).trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void printGreeting() {
        String logo = " ____        _   \n"
                + "| __ )  ___ | |_    \n"
                + "|  _ \\ / _ \\| __|  \n"
                + "| |_) | (_) | |_    \n"
                + "|____/ \\___/ \\__|  \n";

        printLine();
        System.out.println(" Hello! I'm Bot-With-A-Plan, a bot with a plan.");
        System.out.println(" I have a plan... (that isn't planned yet). What can I do for you?");
        System.out.println(logo);
        printLine();
    }

    private static void printLine() {
        System.out.println(LINE);
    }

}
