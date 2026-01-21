import java.util.Scanner;

public class BotWithAPlan {
    public static void main(String[] args) {
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

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            printLine();
            System.out.println(" " + input);
            printLine();
        }
    }

    private static void printLine() {
        System.out.println("____________________________________________");
    }

}
