
package service;

import model.Student;
import model.Book;
import model.LibraryTransaction;
import dao.BookDao;
import dao.LibraryTransactionDao;

import java.util.Date;
import java.util.List;

public class LibraryService {
    private BookDao bookDao;
    private LibraryTransactionDao transactionDao;

    public LibraryService(BookDao bookDao, LibraryTransactionDao transactionDao) {
        this.bookDao = bookDao;
        this.transactionDao = transactionDao;
    }

    // Borrow a book
    public void borrowBook(Student student, Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            bookDao.updateBook(book);

            LibraryTransaction tx = new LibraryTransaction(
                0, student.getId(), book.getId(), new Date(), null
            );
            transactionDao.addTransaction(tx);
        } else {
            throw new IllegalStateException("Book is not available");
        }
    }

    // Return a book
    public void returnBook(Student student, Book book) {
        book.setAvailable(true);
        bookDao.updateBook(book);

        LibraryTransaction tx = transactionDao.findActiveTransaction(student.getId(), book.getId());
        if (tx != null) {
            tx.setReturnDate(new Date());
            transactionDao.updateTransaction(tx);
        }
    }

    // List all borrowed books for a student
    public List<Book> listBorrowedBooks(Student student) {
        return transactionDao.findBooksByStudent(student.getId());
    }
}
