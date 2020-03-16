/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.librarycore.Book;
import a2.librarysystem.Library;
import a2.librarysystem.LibraryException;
import a2.loancore.Loan;
import a2.loancore.Member;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Airi
 */

public class LoanManagerImpl implements LoanManager {
    
    private static ConcurrentHashMap<Integer, Loan> loans = new ConcurrentHashMap<Integer, Loan>();
    private static AtomicInteger loanMapKey = new AtomicInteger();
    private static LoanManagerImpl loanManagerConnectionInstance;
    private static MemberManagerImpl memberManagerConnectionInstance;
    private static Library libraryConnectionInstance;
    
    public LoanManagerImpl() throws LoanException, IOException, LibraryException{
        System.out.println("Created an instance of LoanSystem");
        memberManagerConnectionInstance = memberManagerConnectionInstance.getInstance();
        libraryConnectionInstance = libraryConnectionInstance.getInstance();
    }
    
    // SINGLETON    
    public static LoanManagerImpl getInstance() throws LoanException, IOException, LibraryException {
        if (loanManagerConnectionInstance == null) {
            loanManagerConnectionInstance = new LoanManagerImpl();
            System.out.println("LoanManager - Instance has been created!");
        }
        return loanManagerConnectionInstance;
    }
    
    public ConcurrentHashMap<Integer, Loan> getLoansMap() {
        return loans;
    }
    
    public void listLoan(int memberID) {
        // Get all Keys
        
        /*ConcurrentHashMap<Integer, Loan> loans2 = loanManagerConnectionInstance.getLoansMap();
        
        
        
        for(Map.Entry<Integer, Loan> loan : loans2.entrySet()){
            System.out.println("Current MemberID: ");
            System.out.println(loan.getValue().getMember().getMemberID());
        }*/
        //for(int valuesloanManagerConnectionInstance.getLoansMap().keys();
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    // Borrow a book --> Create loan
    public void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException{
        ConcurrentHashMap<Integer, Member> memberMap = memberManagerConnectionInstance.getMembersMap();
        //System.out.println(memberMap);
        ConcurrentHashMap<String, Book> callNumberMap = libraryConnectionInstance.getCallNumbersMap();
        //System.out.println(callNumberMap);
        //Get current member with memberID
        if (!memberMap.containsKey(memberID)){
            throw new LoanException("Loan Manager - Member does not exist!");
        }
        else{
            if (!callNumberMap.containsKey(callNumber)){
                throw new LoanException("Loan Manager - Book's Call Number does not exist!");
            }
            else{
                Book book = callNumberMap.get(callNumber); // Get Book via callNumber
                Member member = memberMap.get(memberID);
                String bookTitle = book.getTitle();
                // Check if book is available => Associated with no memberID //
                
                
                Loan loan = new Loan(book.getTitle(), member, borrowDate, returnDate);
                int loanID = loanMapKey.incrementAndGet();
                loan.setLoanID(loanID);
                loans.put(loanID, loan);
            }
            
        } 
    }
    
    // Edit a Book Loan
    public void editBookLoan(int loanID, String bookTitle, Member member, String borrowDate, String returnDate) throws LoanException{
        if(!loans.containsKey(loanID))
        {
            throw new LoanException("Loan Manager - Loan does not exist!");
        }
        else{
            Loan loan = new Loan(bookTitle, member, borrowDate, returnDate);
            loans.put(loanID,loan); //Replace previous loan with updated book loan
        }
    }
    
    // Return Book
    // Set memberID and all attributes expect book to null
    public void returnBookLoan(int loanID) throws LoanException{
        if(!loans.containsKey(loanID))
        {
            throw new LoanException("Loan Manager - Loan does not exist!");
        }
        else{
            Loan loan = loans.get(loanID);
            // Keep book title there to get Call No.
            loan.setMember(null);
            loan.setBorrowDate(null);
            loan.setReturnDate(null);
            loans.put(loanID, loan); // Update book loan
        }
        
    }
    
    // Delete a Book Loan
    public void deleteBookLoan(int loanID) throws LoanException{
        if(!loans.containsKey(loanID))
        {
            throw new LoanException("Loan Manager - Loan does not exist!");
        }
        else{
            loans.remove(loanID); // Entirely remove loan from hashmap
        }
    }
    
    // Database Version --> To implement after in-memory version //
    /*
    // SINGLETON Pattern
    private static LoanManagerImpl loanManagerConnectionInstance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private LoanManagerImpl() throws LoanException, FileNotFoundException, IOException {
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
            throw new LoanException("LoanManagerImpl - Error in connecting to database");

        }
    }

    public Connection getConnectionInstance() {
        return connection;
    }

    // SINGLETON    
    public static LoanManagerImpl getInstance() throws LoanException, IOException {
        if (loanManagerConnectionInstance == null) {
            loanManagerConnectionInstance = new LoanManagerImpl();
            System.out.println("LoanManagerImpl - Instance has been created!");
        }
        return loanManagerConnectionInstance;
    }

    public Statement createStatement() throws LoanException {
        try {
            // 2. Create a statement
            statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new LoanException("LoanManagerImpl - Error in creating query!");
        }
        return statement;
    }

    public ResultSet executeQuery(String query) throws LoanException {
        try {
            createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            throw new LoanException("LoanManagerImpl - Error in executing query!");
        }
        return resultSet;
    }

    public int executeUpdate(String query) throws LoanException {
        int update = 0;
        try {
            createStatement();
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new LoanException("LoanManagerImpl - Error in executing update query!");
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
            throw new LoanException("LoanManagerImpl - Error in cleanup!");
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
            throw new LoanException("LoanManagerImpl - Error in creating loan table!");
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
