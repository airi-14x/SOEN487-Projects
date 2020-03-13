/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.librarysystem;

import a2.librarycore.Book;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Airi
 */
public class Library {

    private static ConcurrentHashMap<Integer, Book> books = new ConcurrentHashMap<Integer, Book>();
    private static ConcurrentHashMap<String, Book> callNumbers = new ConcurrentHashMap<String, Book>();
    private static Library libraryConnectionInstance; 
    private static AtomicInteger bookMapKey = new AtomicInteger();

    // Cannot be private
    public Library() {
        System.out.println("Created an instance of Library");
    }

        
    // SINGLETON    
    public static Library getInstance() throws LibraryException, IOException {
        if (libraryConnectionInstance == null) {
            libraryConnectionInstance = new Library();
            System.out.println("Library - Instance has been created!");
        }
        return libraryConnectionInstance;
    }
    
    public ConcurrentHashMap<Integer, Book> getBooksMap() {
        return books;
    }
    
    public ConcurrentHashMap<String, Book> getCallNumbersMap(){
        return callNumbers;
    }

    // GET
    public synchronized String displayBooks() {
        String currentBooks = books.toString();
        if (books.isEmpty()) {
            return ("No books to display");
        }
        return currentBooks;
    }
    
        
    //GET
    public synchronized String getBook(int id){
        if (!books.containsKey(id)){
            return "Book doesn't exist";
        }
        else{
            Book currentBook = books.get(id);
            String bookInfo = currentBook.toString();
            return bookInfo;
        }
    }

    //POST
    public synchronized void addBook(String title, String description, String isbn, String author, String publisher, String callNumber) throws LibraryException {
        Book book = new Book(title, description, isbn, author, publisher, callNumber);
        // Check if callNumber exists

        if (callNumbers.containsKey(callNumber)) {
            throw new LibraryException("Library - Error duplicate call number. Book cannot be created!");
        } else {
            int bookID = bookMapKey.incrementAndGet();
            book.setId(bookID);
            books.put(bookID, book);
            if (!books.containsKey(bookID)) {
                throw new LibraryException("Library - Error in adding a book!");
            }
            else{
                callNumbers.put(callNumber, book); // Add to ConcurrentHashMap
            }
        }

    }
    
    //PUT
    public synchronized void updateBook(int id, String title, String description, String isbn, String author, String publisher, String callNumber) throws LibraryException {
        Book book = new Book(title, description, isbn, author, publisher, callNumber);
        if (callNumbers.containsKey(callNumber)) {
            throw new LibraryException("Library - Error duplicate call number. Book cannot be updated!");
        }
        book.setId(id);
        if (!books.containsKey(id)) {
            throw new LibraryException("Library - Error in updating a book!");        
        } else {
            String oldCallNumber = books.get(id).getCallNumber();
            callNumbers.remove(oldCallNumber); // Manually remove oldCallNumber from hashmap
            books.put(id, book); // Replace with current book object
            callNumbers.put(callNumber, book); // Update callNumber hashmap
        }
    }
    
    //DELETE
    public synchronized void removeBook(int id) throws LibraryException {
        if (!books.containsKey(id)) {
            throw new LibraryException("Library - Book cannot be removed!");
        } else {
            String callNumber = books.get(id).getCallNumber();
            System.out.println(callNumber);
            callNumbers.remove(callNumber);
            books.remove(id);
        }
    }
    

    /*
    // SINGLETON Pattern
    private static Library libraryConnectionInstance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    //private String user = "root";
    //private String pass = "root1234";

    // !!! Create a "LibraryRepo" Schema
    private Library() throws LibraryException, FileNotFoundException, IOException {
        // 1. Get a connection to database
        try {
            InputStream input = new FileInputStream("librarySystemConfig.properties");
            Properties prop = new Properties();
            
            // Load Properties
            prop.load(input);
            
            Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryRepo?serverTimezone=UTC", user, pass);
            connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LibraryException("Library - Error in connecting to database");
            //Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnectionInstance() {
        return connection;
    }

    // SINGLETON    
    public static Library getInstance() throws LibraryException, IOException {
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
            throw new LibraryException("Library - Error in creating query!");
        }
        return statement;
    }

    public ResultSet executeQuery(String query) throws LibraryException {
        try {
            createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            throw new LibraryException("Library - Error in executing query!");
        }
        return resultSet;
    }

    public int executeUpdate(String query) throws LibraryException {
        int update = 0;
        try {
            createStatement();
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new LibraryException("Library - Error in executing update query!");
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
            throw new LibraryException("Library - Error in cleanup!");
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
            throw new LibraryException("Library - Error in creating table!");
        }
    }

    // CREATE
    public void addNewBook(Book book, String callNumber) throws LibraryException {
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
            throw new LibraryException("Library - Error in adding a book!");
            //Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // READ
    public ResultSet listAllBooks() throws LibraryException, SQLException {
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
            throw new LibraryException("Library - Error in updating a book!");
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
     */
}
