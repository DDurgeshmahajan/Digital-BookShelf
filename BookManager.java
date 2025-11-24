import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Manages the collection of Book objects, providing functionalities
 * for adding, viewing, searching, modifying, removing, saving, and loading books.
 */
public class BookManager {
    private List<Book> books;

    /**
     * Constructs a new BookManager with an empty list of books.
     */
    public BookManager() {
        this.books = new ArrayList<>();
    }

    /**
     * Adds a new book to the collection. Title uniqueness is enforced.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param genre The genre of the book.
     * @param publicationYear The publication year of the book.
     * @return true if the book was added successfully, false if a book with the same title already exists.
     */
    public boolean addBook(String title, String author, String genre, int publicationYear) {
        // Check for duplicate title (case-insensitive) before adding
        if (books.stream().anyMatch(b -> b.getTitle().equalsIgnoreCase(title))) {
            return false; // Book with this title already exists
        }
        Book newBook = new Book(title, author, genre, publicationYear, false);
        books.add(newBook);
        return true;
    }

    /**
     * Returns an unmodifiable list of all books in the collection.
     * @return A list of all books.
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books); // Return a copy to prevent external modification
    }

    /**
     * Searches for books by title or author (case-insensitive).
     * @param query The search string.
     * @return A list of books matching the query.
     */
    public List<Book> searchBooks(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                                 book.getAuthor().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

    /**
     * Finds a book by its title (case-insensitive).
     * @param title The title of the book to find.
     * @return The Book object if found, otherwise null.
     */
    private Book findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    /**
     * Toggles the read status of a specific book identified by its title.
     * @param title The title of the book to update.
     * @return true if the book was found and its status toggled, false otherwise.
     */
    public boolean toggleBookReadStatus(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.toggleReadStatus();
            return true;
        }
        return false;
    }

    /**
     * Removes a book from the collection identified by its title.
     * @param title The title of the book to remove.
     * @return true if the book was found and removed, false otherwise.
     */
    public boolean removeBook(String title) {
        Book bookToRemove = findBookByTitle(title);
        if (bookToRemove != null) {
            return books.remove(bookToRemove);
        }
        return false;
    }

    /**
     * Saves the current list of books to a specified text file.
     * Each book is stored as a single line with attributes separated by '|'.
     * @param filename The name of the file to save to.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void saveBooksToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : books) {
                writer.write(book.toFileString());
                writer.newLine();
            }
        }
    }

    /**
     * Loads books from a specified text file, clearing the current collection and populating it.
     * Assumes each line in the file represents a book with attributes separated by '|'.
     * Handles potential file not found or parsing errors.
     * @param filename The name of the file to load from.
     * @throws IOException If an I/O error occurs during reading, other than FileNotFound.
     * @throws IllegalArgumentException If a line in the file is malformed.
     */
    public void loadBooksFromFile(String filename) throws IOException, IllegalArgumentException {
        books.clear(); // Clear existing books before loading new ones
        File file = new File(filename);
        if (!file.exists() || file.length() == 0) {
            System.out.println("Data file not found or empty: " + filename + ". Starting with an empty collection.");
            return; // No file or empty file, so nothing to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                try {
                    String[] parts = line.split("\\|");
                    if (parts.length == 5) {
                        String title = parts[0];
                        String author = parts[1];
                        String genre = parts[2];
                        int publicationYear = Integer.parseInt(parts[3]);
                        boolean isRead = Boolean.parseBoolean(parts[4]);
                        // Add the book only if a book with the same title doesn't exist to prevent duplicates on reload
                        if (books.stream().noneMatch(b -> b.getTitle().equalsIgnoreCase(title))) {
                            books.add(new Book(title, author, genre, publicationYear, isRead));
                        } else {
                            System.err.println("Warning: Duplicate book title '" + title + "' found in file on line " + lineNumber + ". Skipping.");
                        }
                    } else {
                        throw new IllegalArgumentException("Malformed line format.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing number on line " + lineNumber + ": " + line + ". Skipping this entry.");
                } catch (IllegalArgumentException e) {
                    System.err.println("Error parsing book data on line " + lineNumber + ": " + line + " - " + e.getMessage() + ". Skipping this entry.");
                }
            }
        } catch (FileNotFoundException e) {
            // This case should ideally be caught by file.exists() check, but good for robustness
            System.out.println("No existing data file found. Starting with an empty bookshelf.");
        }
    }
}
