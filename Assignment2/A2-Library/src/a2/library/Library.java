/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.library;

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
    
    // >> Need to: Storing in CONFIG file afterwards....
    // SINGLETON Pattern
    private static Library libraryConnectionInstance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    private String user = "root";
    private String pass = "root1234";
    
    
    private Library(){
        // 1. Get a connection to database
        // !! Create "LibraryRepo" Schema
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryRepo?serverTimezone=UTC", user, pass);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnectionInstance() {
        return connection;
    }
    
    // SINGLETON    
    public static Library getInstance(){
        if(libraryConnectionInstance == null){
            libraryConnectionInstance = new Library();
            System.out.println("Library - Instance has been created!");
        }
        return libraryConnectionInstance;
    }
    
    public Statement createStatement() {
        try {
            // 2. Create a statement
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }
    
    public ResultSet executeQuery(String query) {
        try {
            createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }
    
    public int executeUpdate(String query) {
        int update = 0;
        try {
            createStatement();
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }
    
     public void cleanup() {

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
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    // Testing DB //
     
    public void createLibraryTable(){
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
        } catch (Exception e) {

        }
    }
    
    public void addNewBook(String title, String description, String isbn, String author, String publisher, String callNumber){
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
    
    public void dropLibraryTable() {
        try {
            libraryConnectionInstance.executeUpdate("DROP TABLE book");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
