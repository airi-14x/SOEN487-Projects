/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.loancore.Loan;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Airi
 */
public class LoanManager {
    
    private static ConcurrentHashMap<Integer, Loan> loans = new ConcurrentHashMap<Integer, Loan>();
    private static LoanManager loanManagerConnectionInstance;
    
    private LoanManager(){
        
    }
    
    // SINGLETON    
    public static LoanManager getInstance() throws LoanException, IOException {
        if (loanManagerConnectionInstance == null) {
            loanManagerConnectionInstance = new LoanManager();
            System.out.println("LoanManager - Instance has been created!");
        }
        return loanManagerConnectionInstance;
    }
    
    // Borrow a book --> Create loan
    public void borrowBook(String callNumber, int memberID){
        
    }
    
    // Edit a Book Loan
    public void editLoan(Loan loan){
        
    }
    
    // Return Book
    // Set memberID to null
    public void returnBook(Loan loan){
        
    }
    
    // Delete a Book Loan
    
    // Database Version --> To implement after in-memory version //
    /*
    // SINGLETON Pattern
    private static LoanManager loanManagerConnectionInstance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private LoanManager() throws LoanException, FileNotFoundException, IOException {
        // 1. Get a connection to database
        try {
            InputStream input = new FileInputStream("loanSystemConfig.properties");
            Properties prop = new Properties();

            // Load Properties
            prop.load(input);

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryRepo?serverTimezone=UTC", user, pass);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoanException("LoanManager - Error in connecting to database");

        }
    }

    public Connection getConnectionInstance() {
        return connection;
    }

    // SINGLETON    
    public static LoanManager getInstance() throws LoanException, IOException {
        if (loanManagerConnectionInstance == null) {
            loanManagerConnectionInstance = new LoanManager();
            System.out.println("LoanManager - Instance has been created!");
        }
        return loanManagerConnectionInstance;
    }

    public Statement createStatement() throws LoanException {
        try {
            // 2. Create a statement
            statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new LoanException("LoanManager - Error in creating query!");
        }
        return statement;
    }

    public ResultSet executeQuery(String query) throws LoanException {
        try {
            createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            throw new LoanException("LoanManager - Error in executing query!");
        }
        return resultSet;
    }

    public int executeUpdate(String query) throws LoanException {
        int update = 0;
        try {
            createStatement();
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new LoanException("LoanManager - Error in executing update query!");
        }
        return update;
    }

    public void cleanup() throws LoanException {

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
            throw new LoanException("LoanManager - Error in cleanup!");
        }
    }

    // Testing DB //
    public void createLoanTable() throws LoanException {
        try {
            // NEED TO MODIFY member_id to unique? And remove auto-increment
            loanManagerConnectionInstance.executeUpdate("CREATE TABLE `loan`(\n"
                    + "    `title` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `call_number` VARCHAR(64) DEFAULT NULL UNIQUE,\n"
                    + "    `loan_member_id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "    `borrowing_date` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `return_date` VARCHAR(64) DEFAULT NULL,\n"
                    + ");");
        } catch (LoanException e) {
            throw new LoanException("LoanManager - Error in creating loan table!");
        }
    }

    public void dropLoanTable() {
        try {
            loanManagerConnectionInstance.executeUpdate("DROP TABLE loan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}
