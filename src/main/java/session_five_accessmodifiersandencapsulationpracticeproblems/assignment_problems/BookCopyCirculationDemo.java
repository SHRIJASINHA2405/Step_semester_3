package session_five_accessmodifiersandencapsulationpracticeproblems.assignment_problems;

class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {

            throw new IllegalArgumentException(
                    "copiesTotal must be positive"
            );
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class BookCopyCirculationDemo {

    public static void main(String[] args) {

        BookInventory book =
                new BookInventory(3);

        book.checkOut();
        book.checkOut();
        book.checkOut();

        book.checkOut();

        System.out.println(
                "Available after checkout: "
                        + book.getCopiesAvailable()
        );

        book.checkIn();
        book.checkIn();
        book.checkIn();

        book.checkIn();

        System.out.println(
                "Available after check-in: "
                        + book.getCopiesAvailable()
        );
    }
}