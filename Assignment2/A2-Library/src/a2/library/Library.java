/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.library;

import java.sql.Connection;
import java.sql.DriverManager;
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
    private static Library libraryInstance;
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
        if(libraryInstance == null){
            libraryInstance = new Library();
            System.out.println("Library - Instance has been created!");
        }
        return libraryInstance;
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
    
}
