```markdown
# Digital BookShelf

Welcome to your Digital BookShelf! This is a simple, easy-to-use console application written in Java that helps you keep track of your book collection. Whether you're an avid reader or just starting your library, this tool lets you manage your books right from your command line.

## What it Does

This application provides a straightforward way to organize your books. You can add new books, see what you have, search for specific titles or authors, mark books as read or unread, and remove them when you're done. All your changes are automatically saved, so you don't lose track of your progress!

## Cool Features

*   **Add New Books:** Easily add books with their title, author, genre, and publication year.
*   **View Your Collection:** See a complete list of all the books on your digital shelf.
*   **Smart Search:** Quickly find books by typing in part of their title or author's name.
*   **Track Your Reading:** Mark books as 'Read' or 'Unread' with a simple toggle.
*   **Remove Books:** Clear out books you no longer want in your collection.
*   **Automatic Saving:** Your book data is automatically saved to a file (`books.txt`) and loaded when you restart the application, so your collection is always up-to-date.
*   **User-Friendly Input:** The app guides you with clear prompts and handles incorrect entries gracefully.

## How to Use It

Getting your Digital BookShelf up and running is super easy!

### 1. Get the Files

First, you'll need the Java source files. Make sure all `.java` files (`DigitalBookShelfApp.java`, `Book.java`, `BookManager.java`, `InputHandler.java`) are in the same folder on your computer.

### 2. Compile the Code

Open your command prompt or terminal. Navigate to the folder where you saved the `.java` files. Then, run this command to compile them:

```bash
javac *.java
```

This command will create `.class` files for each Java source file.

### 3. Run the Application

Once compiled, you can start the application with this command:

```bash
java DigitalBookShelfApp
```

### 4. Enjoy Your Digital BookShelf!

The application will start and show you the main menu. Just follow the on-screen instructions to manage your books.

**Important Note:** The app will create a file named `books.txt` in the same directory where you run it. This file is where all your book data is stored, so don't delete it if you want to keep your collection!

## What You Need

To run this application, you only need:

*   **Java Development Kit (JDK):** Version 8 or newer. If you don't have Java installed, you can download it from Oracle's website or Adoptium.

## A Sneak Peek

When you run the application, you'll see a menu like this:

```
--- Digital BookShelf Menu ---
1. Add New Book
2. View All Books
3. Search Books
4. Mark Book as Read/Unread
5. Remove Book
6. Exit
Choose an option:
```

Just type the number corresponding to your choice and press Enter. The app will then guide you through the next steps for adding, searching, or managing your books.

## About the Author

This project was created by Durgesh Mahajan.
You can reach me at: ashamahajan955@gmail.com
```