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

            if (input.trim().isEmpty()) {
                continue;
            }

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

            if (input.equals("mark")) {
                continue;
            }

            if (input.equals("unmark")) {
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

            if (input.startsWith("todo")) {
                String desc = input.length() > 5 ? input.substring("todo ".length()).trim() : "";
                if (desc.isEmpty()) {
                    continue;
                }
                tasks[taskCount] = new Todo(desc);
                taskCount++;
                printAdded(tasks[taskCount - 1], taskCount);
                continue;
            }

            if (input.startsWith("deadline")) {
                Task d = parseDeadline(input);
                if (d != null) {
                    tasks[taskCount] = d;
                    taskCount++;
                    printAdded(tasks[taskCount - 1], taskCount);
                }
                continue;
            }

            if (input.startsWith("event")) {
                Task e = parseEvent(input);
                if (e != null) {
                    tasks[taskCount] = e;
                    taskCount++;
                    printAdded(tasks[taskCount - 1], taskCount);
                }
                continue;
            }

            // add task
            tasks[taskCount] = new Todo(input);
            taskCount++;
            printAdded(tasks[taskCount - 1], taskCount);

        }
    }

    private static void printAdded(Task task, int count) {
        printLine();
        System.out.println(" Got the plan. I've added this task:");
        System.out.println(" " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        printLine();
    }

    private static Task parseDeadline(String input) {
        // "deadline <desc> /by <by>"
        String rest = input.substring("deadline".length()).trim();
        if (rest.isEmpty()) {
            return null;
        }
        int byPos = rest.indexOf(" /by ");
        if (byPos == -1) {
            return null;
        }
        String desc = rest.substring(0, byPos).trim();
        String by = rest.substring(byPos + " /by ".length()).trim();
        if (desc.isEmpty() || by.isEmpty()) return null;

        return new Deadline(desc, by);
    }

    private static Task parseEvent(String input) {
        // "event <desc> /from <from> /to <to>"
        String rest = input.substring("event".length()).trim();
        if (rest.isEmpty()) {
            return null;
        }

        int fromPos = rest.indexOf(" /from ");
        int toPos = rest.indexOf(" /to ");
        if (fromPos == -1 || toPos == -1 || toPos < fromPos) {
            return null;
        }
        String desc = rest.substring(0, fromPos).trim();
        String from = rest.substring(fromPos + " /from ".length(), toPos).trim();
        String to = rest.substring(toPos + " /to ".length()).trim();
        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) return null;

        return new Event(desc, from, to);
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
