package service;

import model.Book;
import model.Library;
import model.Librarian;

import dao.BookDao;
import dao.LibraryDao;
import dao.LibrarianDao;

import java.util.List;

public class LibrarianService {
    private BookDao bookDao;
    private LibraryDao libraryDao;
    private LibrarianDao librarianDao;

    public LibrarianService(BookDao bookDao, LibraryDao libraryDao, LibrarianDao librarianDao) {
        this.bookDao = bookDao;
        this.libraryDao = libraryDao;
        this.librarianDao = librarianDao;
    }

    // Librarian CRUD
    public boolean addLibrarian(Librarian librarian) {
        try {
            librarianDao.addLibrarian(librarian);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding librarian: " + e.getMessage());
            return false;
        }
    }

    public Librarian getLibrarianByIdNumber(String idNumber) {
        try {
            return librarianDao.getLibrarianByIdNumber(idNumber);
        } catch (Exception e) {
            System.out.println("Error fetching librarian: " + e.getMessage());
            return null;
        }
    }

    public boolean updateLibrarian(Librarian librarian) {
        try {
            librarianDao.updateLibrarian(librarian);
            return true;
        } catch (Exception e) {
            System.out.println("Error updating librarian: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteLibrarian(int id) {
        try {
            librarianDao.deleteLibrarian(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting librarian: " + e.getMessage());
            return false;
        }
    }

    public List<Librarian> listLibrarians() {
        try {
            return librarianDao.getAllLibrarians();
        } catch (Exception e) {
            System.out.println("Error listing librarians: " + e.getMessage());
            return null;
        }
    }

    // Book operations
    public boolean addBook(Book book) {
        try {
            bookDao.addBook(book);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteBook(int id) {
        try {
            bookDao.deleteBook(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting book: " + e.getMessage());
            return false;
        }
    }

    public List<Book> listBooks() {
        try {
            return bookDao.listBooks();
        } catch (Exception e) {
            System.out.println("Error listing books: " + e.getMessage());
            return null;
        }
    }

    // Library operations
    public boolean addLibrary(Library library) {
        try {
            libraryDao.addLibrary(library);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding library: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteLibrary(int id) {
        try {
            libraryDao.deleteLibrary(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting library: " + e.getMessage());
            return false;
        }
    }

    public List<Library> listLibraries() {
        try {
            return libraryDao.listLibraries();
        } catch (Exception e) {
            System.out.println("Error listing libraries: " + e.getMessage());
            return null;
        }
    }
}
