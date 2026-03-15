package dao;
import model.Book;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java .util.List;


public class BookDao {
    private Connection connection;

    public BookDao(Connection connection){
        this.connection = connection;
    }
public void AddBook(Book book){
    String sql = "INSERT INTO books (title,author,isbn,yearPublished)";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,book.getTitle);
        statement.setString(2,book.getAuthor);
        statement.setString(3,book.getIsbn);
        statement.setInt(4,book.getyearPublished);
        statement.executeUpdate();
  
  }catch(SQLException e){
    e.printStackTrace();
  }
}
public void getBookByTitle(String title){
    String sql = "SELECT *FROM book WHERE title=?";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,title);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Book(
resultSet.getString("isbn"),
resultSet.getString("author"),
resultSet.getInt("yearPublished")
            );
        }
    }catch(SQLException e){
            e.printStackTrace();
        }
}
public List<Book> getAllBooks(){
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM Books";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            books.add(new book(
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("isbn"),
                resultSet.getInt("YearPublished")
            ));
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
    return books;
}
public void updateBooks(Book book){
    String sql ="UPDATE books SET title=?,isbn=?,author=?,yearPublished=?";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString("1,book.getTitle");
        statement.setString("2,book.getAuthor");
        statement.setString("3,book.getIsbn");
        statement.setInt("4,book.getYearPublished");
        statement.executeQuery();
    }catch(SQLException e){
        e.printStackTrace();
    }
}
public void deleteBook(){
    String sql = "DELETE FROM books WHERE title =?";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,title);
        statement.executeQuery();
    }catch(SQLException e){
        e.printStackTrace();
    }
}
}

