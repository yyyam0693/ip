package plan.parser;

import plan.BotException;
import plan.Deadline;
import plan.Event;
import plan.Todo;
import plan.Task;

import plan.command.AddDeadlineCommand;
import plan.command.AddEventCommand;
import plan.command.AddTodoCommand;
import plan.command.Command;
import plan.command.DeleteCommand;
import plan.command.ExitCommand;
import plan.command.ListCommand;
import plan.command.MarkCommand;
import plan.command.UnmarkCommand;
import plan.command.FindCommand;

public class Parser {

    public static Command parse(String input) throws BotException {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new BotException("MANNNNN i dont get the plan.... Try: todo/deadline/event/list/mark/unmark/bye");
        }

        if (trimmed.equals("bye")) {
            return new ExitCommand();
        }

        if (trimmed.equals("list")) {
            return new ListCommand();
        }

        if (trimmed.equals("mark") || trimmed.equals("unmark")) {
            // keep your old behavior: just ignore these
            throw new BotException("use: mark <number> or unmark <number>");
        }

        if (trimmed.startsWith("mark ")) {
            int idx = parseIndex(trimmed, "mark ");
            return new MarkCommand(idx);
        }

        if (trimmed.startsWith("unmark ")) {
            int idx = parseIndex(trimmed, "unmark ");
            return new UnmarkCommand(idx);
        }

        if (trimmed.startsWith("todo")) {
            Task t = parseTodo(trimmed);
            return new AddTodoCommand(t);
        }

        if (trimmed.startsWith("deadline")) {
            Task d = parseDeadlineOrThrow(trimmed);
            return new AddDeadlineCommand(d);
        }

        if (trimmed.startsWith("event")) {
            Task e = parseEventOrThrow(trimmed);
            return new AddEventCommand(e);
        }

        if (trimmed.equals("delete") || trimmed.startsWith("delete ")) {
            int idx = parseIndex(trimmed, "delete ");
            return new DeleteCommand(idx);
        }

        if (input.startsWith("find")) {
            String keyword = input.length() > 4 ? input.substring(4).trim() : "";
            return new FindCommand(keyword);
        }

        throw new BotException("MANNNNN i dont get the plan.... Try: todo/deadline/event/list/mark/unmark/bye");
    }

    private static Task parseTodo(String input) throws BotException {
        String desc = input.length() > 5 ? input.substring("todo ".length()).trim() : "";
        if (desc.isEmpty()) {
            throw new BotException("dude.. you seriously telling me, the bot with a plan, about your nonexistent plan. try harder.");
        }
        return new Todo(desc);
    }

    private static Task parseDeadlineOrThrow(String input) throws BotException {
        String rest = input.substring("deadline".length()).trim();
        if (rest.isEmpty()) {
            throw new BotException("where are the details??? wake up. Deadline needs a description and /by <yyyy-mm-dd>.");
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

    private static int parseIndex(String input, String prefix) throws BotException {
        try {
            int idx = Integer.parseInt(input.substring(prefix.length()).trim());
            return idx;
        } catch (NumberFormatException e) {
            throw new BotException(prefix.trim() + " needs a valid task number.");
        }
    }
}
