package model;


public class Book{
    private String title;
    private String author;
    private String isbn;
    private String description;


    public Book(String title,String author,String isbn,String description){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
    }
    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    // Optional: toString for easy printing
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

