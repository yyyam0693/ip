package plan;

import java.util.Scanner;
import java.util.ArrayList;
import plan.storage.Storage;

public class BotWithAPlan {

    private static final int MAX_TASKS = 100;
    private static final String LINE = "____________________________________________";

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        Storage storage = new Storage("data/duke.txt");

        ArrayList<Task> tasks;
        try {
            tasks = storage.load();   // load saved tasks
        } catch (Exception e) {
            tasks = new ArrayList<>(); // start fresh if missing/corrupt
        }

        printGreeting();

        while (true) {
            String input = sc.nextLine();

            if (input.trim().isEmpty()) {
                continue;
            }

            try {
                if (input.equals("bye")) {
                    printLine();
                    System.out.println(" Bye. Hope to see you again soon!");
                    printLine();
                    break;
                }

                if (input.equals("list")) {
                    printLine();
                    System.out.println(" I have a plan. Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
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
                    if (index >= 1 && index <= tasks.size()) {
                        Task t = tasks.get(index - 1);
                        t.markAsDone();
                        storage.save(tasks);
                        printLine();
                        System.out.println(" OK, the plan is to mark this task as done, and ive gone ahead with the plan:");
                        System.out.println(" " + t);
                        printLine();
                    }
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    int index = parseIndex(input, "unmark ");
                    if (index >= 1 && index <= tasks.size()) {
                        Task t = tasks.get(index - 1);
                        t.markAsNotDone();
                        storage.save(tasks);
                        printLine();
                        System.out.println(" OK, the plan is to mark this task as not done, and ive gone ahead with the plan:");
                        System.out.println(" " + t);
                        printLine();
                    }
                    continue;
                }

                if (input.startsWith("todo")) {
                    Task t = parseTodo(input);
                    tasks.add(t);
                    storage.save(tasks);
                    printAdded(t, tasks.size());
                    continue;
                }

                if (input.startsWith("deadline")) {
                    Task d = parseDeadlineOrThrow(input);
                    tasks.add(d);
                    storage.save(tasks);
                    printAdded(d, tasks.size());
                    continue;
                }

                if (input.startsWith("event")) {
                    Task e = parseEventOrThrow(input);
                    tasks.add(e);
                    storage.save(tasks);
                    printAdded(e, tasks.size());
                    continue;
                }

                if (input.equals("delete") || input.startsWith("delete ")) {
                    int idx = parseIndex(input, "delete ");
                    if (idx < 1 || idx > tasks.size()) {
                        throw new BotException("delete needs a valid task number.");
                    }

                    Task removed = tasks.remove(idx - 1);
                    storage.save(tasks);

                    printLine();
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println(" " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                    continue;
                }


                //unknown command
                throw new BotException("MANNNNN i dont get the plan.... Try: todo/deadline/event/list/mark/unmark/bye");

            } catch (BotException e) {
                printError(e.getMessage());
                continue;
            }


        }
    }

    private static void printAdded(Task task, int count) {
        printLine();
        System.out.println(" Got the plan. I've added this task:");
        System.out.println(" " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        printLine();
    }

    private static Task parseTodo(String input) throws BotException {
        String desc = input.length() > 5 ? input.substring("todo ".length()).trim() : "";
        if (desc.isEmpty()) {
            throw new BotException("dude.. you seriously telling me, the bot with a plan, about your nonexistent plan. try harder.");
        }

        return new Todo(desc);
    }

    private static Task parseDeadlineOrThrow(String input) throws BotException {
        // "deadline <desc> /by <by>"
        String rest = input.substring("deadline".length()).trim();
        if (rest.isEmpty()) {
            throw new BotException("where are the details??? wake up. " +
                    "Deadline needs a description and /by <yyyy-mm-dd>.");
        }
        int byPos = rest.indexOf(" /by ");
        if (byPos == -1) {
            throw new BotException("do better. Plan. Deadline must be: deadline <task> /by <yyyy-mm-dd>.");
        }
        String desc = rest.substring(0, byPos).trim();
        String by = rest.substring(byPos + " /by ".length()).trim();
        if (desc.isEmpty()) {
            throw new BotException("where are the details??? wake up");
        }
        if (by.isEmpty()) {
            throw new BotException("do you expect me to know when the deadline is... as i said, i have a plan, not the plan.");
        }

        return new Deadline(desc, by);
    }

    private static Task parseEventOrThrow(String input) throws BotException {
        // "event <desc> /from <from> /to <to>"
        String rest = input.substring("event".length()).trim();
        if (rest.isEmpty()) {
            throw new BotException("where are the details??? wake up. Plan.Event must be: event <task> /from <start> /to <end>.");
        }

        int fromPos = rest.indexOf(" /from ");
        int toPos = rest.indexOf(" /to ");
        if (fromPos == -1 || toPos == -1 || toPos < fromPos) {
            throw new BotException("i'm disappointed in you. Plan.Event must be: event <task> /from <start> /to <end>.");
        }
        String desc = rest.substring(0, fromPos).trim();
        String from = rest.substring(fromPos + " /from ".length(), toPos).trim();
        String to = rest.substring(toPos + " /to ".length()).trim();
        if (desc.isEmpty()) throw new BotException("do you expect me to know what the event is... as i said, i have a plan, not the plan.");
        if (from.isEmpty()) throw new BotException("if you dont want to go for the event, just say. The /from part of an event cannot be empty.");
        if (to.isEmpty()) throw new BotException("how could you miss this! The /to part of an event cannot be empty.");

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

    private static void printError(String msg) {
        printLine();
        System.out.println(" " + msg);
        printLine();
    }

    private static void printLine() {
        System.out.println(LINE);
    }

}
