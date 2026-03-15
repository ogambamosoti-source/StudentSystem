package model;


public class Book{
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int yearPublished;
    private String description;


    public Book(int id,
        String title,
        String author,
        String isbn,
        int yearPublished
        ,String description){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.description = description;
    }
    // Getters
    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
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
        "id='"+id+'\''+
                ",title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

