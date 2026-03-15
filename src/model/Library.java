package model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Library {
    // Attributes
   private Person borrower;
   private List<Book> borrowedBooks;

    private static final int MAX_BOOKS = 3;

    // Constructor
    public Library(Person borrower) {
this.borrower = borrower;
        this.borrowedBooks = new ArrayList<>();
    }

    // Borrow method (fixed to 3 days)
    public boolean borrowBook(int numberOfBooks) {
        if (borrowedBooks + numberOfBooks <= MAX_BOOKS) {
            borrowedBooks += numberOfBooks;
            dueDate = LocalDate.now().plusDays(BORROW_DAYS);
            System.out.println(numberOfBooks + " book(s) borrowed. Return by: " + dueDate);
            return true;
        } else {
            System.out.println("Borrowing limit exceeded! Max allowed is " + MAX_BOOKS);
            return false;
        }
    }

    // Return method
    public void returnBooks(int numberOfBooks) {
        if (numberOfBooks <= borrowedBooks) {
            borrowedBooks -= numberOfBooks;
            System.out.println(numberOfBooks + " book(s) returned. Remaining borrowed: " + borrowedBooks);
        } else {
            System.out.println("Error: You cannot return more books than borrowed.");
        }
    }
public List<Book> getBorrowedBooks(){
    return borrowedBooks;
}
}

