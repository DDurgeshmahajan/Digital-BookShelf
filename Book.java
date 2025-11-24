/**
 * Represents a single book in the digital bookshelf collection.
 * Stores details like title, author, genre, publication year, and read status.
 */
public class Book {
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private boolean isRead;

    /**
     * Constructs a new Book object.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param genre The genre of the book.
     * @param publicationYear The year the book was published.
     * @param isRead The initial read status of the book (true if read, false if unread).
     */
    public Book(String title, String author, String genre, int publicationYear, boolean isRead) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.isRead = isRead;
    }

    // --- Getters ---
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isRead() {
        return isRead;
    }

    // --- Setter ---

    /**
     * Sets the read status of the book.
     * @param readStatus The new read status (true for read, false for unread).
     */
    public void setReadStatus(boolean readStatus) {
        isRead = readStatus;
    }

    /**
     * Toggles the read status of the book from read to unread or vice-versa.
     */
    public void toggleReadStatus() {
        this.isRead = !this.isRead;
    }

    /**
     * Provides a human-readable string representation of the Book object.
     * @return A formatted string with book details.
     */
    @Override
    public String toString() {
        return String.format(
                "Title: %s, Author: %s, Genre: %s, Year: %d, Status: %s",
                title, author, genre, publicationYear, (isRead ? "Read" : "Unread")
        );
    }

    /**
     * Formats the book's data into a string suitable for file storage.
     * Attributes are separated by a '|' delimiter.
     * @return A delimited string representation of the book.
     */
    public String toFileString() {
        return String.join("|",
                title,
                author,
                genre,
                String.valueOf(publicationYear),
                String.valueOf(isRead)
        );
    }
}
