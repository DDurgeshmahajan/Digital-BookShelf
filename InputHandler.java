import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class to handle robust user input from the console.
 * Provides methods for reading strings, integers, and booleans with validation and retry logic.
 */
public class InputHandler {
    private Scanner scanner;

    /**
     * Constructs an InputHandler with a given Scanner instance.
     * @param scanner The Scanner object to use for reading input.
     */
    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a non-empty string from the user.
     * @param prompt The message to display to the user.
     * @return The user's input string.
     */
    public String readString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }

    /**
     * Reads an integer from the user, handling invalid input with retries.
     * @param prompt The message to display to the user.
     * @return The user's integer input.
     */
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline character
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * Reads an integer from the user within a specified range, handling invalid input with retries.
     * @param prompt The message to display to the user.
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @return The user's integer input within the specified range.
     */
    public int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Input must be between " + min + " and " + max + ". Please try again.");
            }
        }
    }

    /**
     * Reads a boolean value (yes/no) from the user.
     * @param prompt The message to display to the user (e.g., "Is the book read (yes/no)? ").
     * @return true if the user enters 'yes' (case-insensitive), false if 'no'.
     */
    public boolean readBoolean(String prompt) {
        while (true) {
            String input = readString(prompt + " (yes/no): ").toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    /**
     * Closes the underlying Scanner object.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
