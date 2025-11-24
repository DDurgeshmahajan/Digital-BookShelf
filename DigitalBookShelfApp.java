/*
Author: Durgesh Mahajan
Date: 2023-10-27
Project: Digital BookShelf
*/

import java.util.List;
import java.util.Scanner;

/**
 * The main application class for the Digital BookShelf.
 * Manages user interaction, menu display, and orchestrates operations
 * on the book collection via the BookManager.
 */
public class DigitalBookShelfApp {

    private static final String DATA_FILE = "books.txt";
    private BookManager bookManager;
    private InputHandler inputHandler;

    public DigitalBookShelfApp() {
        this.bookManager = new BookManager();
        this.inputHandler = new InputHandler(new Scanner(System.in));
        loadBooks();
    }

    /**
     * Loads book data from the specified file when the application starts.
     */
    private void loadBooks() {
        System.out.println("Attempting to load books from " + DATA_FILE + "...");
        try {
            bookManager.loadBooksFromFile(DATA_FILE);
            System.out.println("Books loaded successfully!");
        } catch (Exception e) {
            System.err.println("Could not load books: " + e.getMessage() + ". Starting with an empty collection.");
        }
    }

    /**
     * Saves book data to the specified file before the application exits.
     */
    private void saveBooks() {
        System.out.println("Saving books to " + DATA_FILE + "...");
        try {
            bookManager.saveBooksToFile(DATA_FILE);
            System.out.println("Books saved successfully!");
        } catch (Exception e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    /**
     * Displays the main menu to the user.
     */
    private void displayMenu() {
        System.out.println("\n--- Digital BookShelf Menu ---");
        System.out.println("1. Add New Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Books");
        System.out.println("4. Mark Book as Read/Unread");
        System.out.println("5. Remove Book");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Runs the main application loop, handling user menu choices.
     */
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = inputHandler.readInt("", 1, 6);

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    markBookReadUnread();
                    break;
                case 5:
                    removeBook();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Digital BookShelf. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        saveBooks();
        inputHandler.closeScanner();
    }

    /**
     * Handles the 'Add New Book' functionality.
     */
    private void addBook() {
        System.out.println("\n--- Add New Book ---");
        String title = inputHandler.readString("Enter title: ");
        String author = inputHandler.readString("Enter author: ");
        String genre = inputHandler.readString("Enter genre: ");
        int publicationYear = inputHandler.readInt("Enter publication year: ", 1000, 2100);

        if (bookManager.addBook(title, author, genre, publicationYear)) {
            System.out.println("Book '" + title + "' added successfully!");
        } else {
            System.out.println("Book with title '" + title + "' already exists.");
        }
    }

    /**
     * Handles the 'View All Books' functionality.
     */
    private void viewAllBooks() {
        System.out.println("\n--- All Books in Collection ---");
        List<Book> allBooks = bookManager.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("Your bookshelf is currently empty.");
        } else {
            for (int i = 0; i < allBooks.size(); i++) {
                System.out.println((i + 1) + ". " + allBooks.get(i));
            }
        }
    }

    /**
     * Handles the 'Search Books' functionality.
     */
    private void searchBooks() {
        System.out.println("\n--- Search Books ---");
        String query = inputHandler.readString("Enter title or author to search: ");
        List<Book> searchResults = bookManager.searchBooks(query);

        if (searchResults.isEmpty()) {
            System.out.println("No books found matching '" + query + "'.");
        } else {
            System.out.println("\n--- Search Results for '" + query + "' ---");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i));
            }
        }
    }

    /**
     * Handles the 'Mark Book as Read/Unread' functionality.
     */
    private void markBookReadUnread() {
        System.out.println("\n--- Mark Book as Read/Unread ---");
        String title = inputHandler.readString("Enter the title of the book to mark: ");
        if (bookManager.toggleBookReadStatus(title)) {
            System.out.println("Read status for '" + title + "' has been toggled.");
        } else {
            System.out.println("Book '" + title + "' not found in your collection.");
        }
    }

    /**
     * Handles the 'Remove Book' functionality.
     */
    private void removeBook() {
        System.out.println("\n--- Remove Book ---");
        String title = inputHandler.readString("Enter the title of the book to remove: ");
        if (bookManager.removeBook(title)) {
            System.out.println("Book '" + title + "' removed successfully.");
        } else {
            System.out.println("Book '" + title + "' not found in your collection.");
        }
    }

    public static void main(String[] args) {
        DigitalBookShelfApp app = new DigitalBookShelfApp();
        app.run();
    }
}
