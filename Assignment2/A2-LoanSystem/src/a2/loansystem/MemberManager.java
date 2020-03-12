/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.librarycore.Book;
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
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Airi
 */
public class MemberManager {
    
    //private static ConcurrentHashMap<Integer, Book> books = new ConcurrentHashMap<Integer, Book>();
    //private static AtomicInteger bookMapKey = new AtomicInteger();
    // Need to create memberID --> UNIQUE
    
    private static ConcurrentHashMap<Integer, Member> members = new ConcurrentHashMap<Integer, Member>();
    private static AtomicInteger memberMapKey = new AtomicInteger(); // Need to create memberID --> UNIQUE
    
    private static MemberManager memberManagerConnectionInstance;
    
    private MemberManager(){
        
    }
    
    // SINGLETON    
    public static MemberManager getInstance() throws LoanException, IOException {
        if (memberManagerConnectionInstance == null) {
            memberManagerConnectionInstance = new MemberManager();
            System.out.println("MemberManager - Instance has been created!");
        }
        return memberManagerConnectionInstance;
    }
    
    public ConcurrentHashMap<Integer, Member> getMap() {
        return members;
    }
    
    // GET 
    // List members
    public void getMembers(){
        
    }
    public void getMemberInfo(int memberID){
        // Return: memberID, String name, String contact
    }
    
    
    // POST
    public void addMember(){
        
    }
    
    // UPDATE
    public void editMember(){
        
    }
    
    // DELETE
    public void deleteMember(){
        
    }
    
    // Database Version --> Implement after getting main memory version to work //
    /*
    // SINGLETON Pattern
    private static MemberManager memberManagerConnectionInstance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    private MemberManager() throws LoanException, FileNotFoundException, IOException {
        // 1. Get a connection to database
        try {
            InputStream input = new FileInputStream("loanSystemConfig.properties");
            Properties prop = new Properties();

            // Load Properties
            prop.load(input);

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoanException("MemberManager - Error in connecting to database");

        }
    }
    
    public Connection getConnectionInstance() {
        return connection;
    }

    // SINGLETON    
    public static MemberManager getInstance() throws LoanException, IOException {
        if (memberManagerConnectionInstance == null) {
            memberManagerConnectionInstance = new MemberManager();
            System.out.println("MemberManager - Instance has been created!");
        }
        return memberManagerConnectionInstance;
    }

    public Statement createStatement() throws LoanException {
        try {
            // 2. Create a statement
            statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new LoanException("MemberManager - Error in creating query!");
        }
        return statement;
    }
    
    public ResultSet executeQuery(String query) throws LoanException {
        try {
            createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            throw new LoanException("MemberManager - Error in executing query!");
        }
        return resultSet;
    }

    public int executeUpdate(String query) throws LoanException {
        int update = 0;
        try {
            createStatement();
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new LoanException("MemberManager - Error in executing update query!");
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
            throw new LoanException("MemberManager - Error in cleanup!");
        }
    }
        
    // Testing DB //
    public void createMemberTable() throws LoanException {
        try {
            // NEED TO MODIFY member_id to unique? And remove auto-increment
            memberManagerConnectionInstance.executeUpdate("CREATE TABLE `member`(\n"
                    + "    `member_id` INT AUTO_INCREMENT,\n"
                    + "    `name` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `contact` VARCHAR(64) DEFAULT NULL,\n"
                    + "    PRIMARY KEY(`member_id`)\n"
                    + ")AUTO_INCREMENT=1;");
        } catch (LoanException e) {
            throw new LoanException("MemberManager - Error in creating member table!");
        }
    }
    
        public void dropMemberTable() {
        try {
            memberManagerConnectionInstance.executeUpdate("DROP TABLE member");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}
