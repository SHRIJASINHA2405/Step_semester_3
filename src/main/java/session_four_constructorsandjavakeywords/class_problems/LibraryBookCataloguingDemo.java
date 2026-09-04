package session_four_constructorsandjavakeywords.class_problems;

class LibraryBook {

    String title;
    String isbn;

    // Constructor with title and ISBN
    public LibraryBook(String title, String isbn) {
        this.title = title;

        if (isbn == null || isbn.isEmpty()) {
            this.isbn = "PENDING";
        } else {
            this.isbn = isbn;
        }
    }

    // Constructor with only title
    // Uses constructor chaining
    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    public void displayStatus() {
        System.out.println(title + " | " + isbn + " | Catalogued: true");
    }
}


public class LibraryBookCataloguingDemo {

    public static void main(String[] args) {

        String[] titles = {
                "Clean Code",
                "Untitled Draft",
                "1984",
                "Notes"
        };

        String[] isbns = {
                "978-0132350884",
                "",
                "9780451524935",
                ""
        };

        // Process every book in a single pass
        for (int i = 0; i < titles.length; i++) {

            LibraryBook book;

            if (isbns[i].isEmpty()) {
                book = new LibraryBook(titles[i]);
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }

            book.displayStatus();
        }
    }
}