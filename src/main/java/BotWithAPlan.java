import java.util.Scanner;

public class BotWithAPlan {

    private static final int MAX_TASKS = 100;
    private static final String LINE = "____________________________________________";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] tasks = new String[MAX_TASKS];
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                printLine();
                continue;
            }

            // add task
            tasks[taskCount] = input;
            taskCount++;

            printLine();
            System.out.println("added: " + input);
            printLine();
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
