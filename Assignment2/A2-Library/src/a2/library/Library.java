/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.library;

import a2.librarycore.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Airi
 */
public class Library {

    // SINGLETON Pattern
    private static Library libraryConnectionInstance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private String user = "root";
    private String pass = "root1234";

    private Library() throws LibraryException {
        // 1. Get a connection to database
        // !! Create "LibraryRepo" Schema
        try {
            // >> Need to: Storing in CONFIG file afterwards....
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryRepo?serverTimezone=UTC", user, pass);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LibraryException("Error in connecting to database");
            //Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnectionInstance() {
        return connection;
    }

    // SINGLETON    
    public static Library getInstance() throws LibraryException {
        if (libraryConnectionInstance == null) {
            libraryConnectionInstance = new Library();
            System.out.println("Library - Instance has been created!");
        }
        return libraryConnectionInstance;
    }

    public Statement createStatement() throws LibraryException {
        try {
            // 2. Create a statement
            statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new LibraryException("Error in creating query!");
            //Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }

    public ResultSet executeQuery(String query) throws LibraryException {
        try {
            createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            throw new LibraryException("Error in executing query!");
            //Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

    public int executeUpdate(String query) throws LibraryException {
        int update = 0;
        try {
            createStatement();
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new LibraryException("Error in executing update query!");
            //Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }

    public void cleanup() throws LibraryException {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new LibraryException("Error in cleanup!");
            //Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Testing DB //
    public void createLibraryTable() throws LibraryException {
        try {
            libraryConnectionInstance.executeUpdate("CREATE TABLE `book`(\n"
                    + "	`id` INT  NOT NULL AUTO_INCREMENT,\n"
                    + "    `title` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `description` VARCHAR(256) DEFAULT NULL,\n"
                    + "    `isbn` VARCHAR(64) DEFAULT NULL UNIQUE,\n"
                    + "    `author` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `publisher` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `call_number` VARCHAR(64) DEFAULT NULL UNIQUE,\n"
                    + "    PRIMARY KEY(`id`)\n"
                    + ")AUTO_INCREMENT=1;");
        } catch (LibraryException e) {
            throw new LibraryException("Error in creating table!");
        }
    }

    // CREATE
    public void addNewBook(Book book, String callNumber) {
        String title = book.getTitle();
        String description = book.getDescription();
        String isbn = book.getIsbn();
        String author = book.getAuthor();
        String publisher = book.getPublisher();
        
        String query = "INSERT INTO Book(title, description, isbn, author, publisher, call_number) values (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = libraryConnectionInstance.getConnectionInstance().prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, isbn);
            statement.setString(4, author);
            statement.setString(5, publisher);
            statement.setString(6, callNumber);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // READ
    public ResultSet listAllBooks() throws LibraryException {
        ResultSet resultSet = libraryConnectionInstance.executeQuery("SELECT * FROM book");
        return resultSet;
    }

    public ResultSet getBookInfo(int id) throws LibraryException {
        ResultSet resultSet = libraryConnectionInstance.executeQuery("SELECT * FROM book WHERE id=" + id);
        return resultSet;
    }

    // UPDATE
    public void updateBookInfo(int id, Book book, String callNumber) throws LibraryException {
        String title = book.getTitle();
        String description = book.getDescription();
        String isbn = book.getIsbn();
        String author = book.getAuthor();
        String publisher = book.getPublisher();
        
        String query = "UPDATE book SET title=?, description=?, isbn=?, author=?, publisher=?, call_number=? WHERE id=" + id;

        try {
            PreparedStatement statement = libraryConnectionInstance.getConnectionInstance().prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, isbn);
            statement.setString(4, author);
            statement.setString(5, publisher);
            statement.setString(6, callNumber);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new LibraryException("Error in updating table!");
            //Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // DELETE
    public void deleteBook(int id) throws LibraryException {
        libraryConnectionInstance.executeUpdate("DELETE FROM book WHERE id=" + id);
    }

    public void dropLibraryTable() {
        try {
            libraryConnectionInstance.executeUpdate("DROP TABLE book");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
