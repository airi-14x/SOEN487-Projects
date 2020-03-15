/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

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

public class MemberManagerImpl implements MemberManager {

    private static ConcurrentHashMap<Integer, Member> members = new ConcurrentHashMap<Integer, Member>();
    private static AtomicInteger memberMapKey = new AtomicInteger(); // Need to create memberID --> UNIQUE
    private static MemberManagerImpl memberManagerConnectionInstance; 
    
    public MemberManagerImpl() {
        System.out.println("Created an instance of MemberSystem()");
    }

    public ConcurrentHashMap<Integer, Member> getMembersMap() {
        return members;
    }
    
    // For setting memberID manually //
    public int memberMapKey(){
        return memberMapKey.get();
    }
    
    // SINGLETON    
    public static MemberManagerImpl getInstance() throws LoanException, IOException {
        if (memberManagerConnectionInstance == null) {
            memberManagerConnectionInstance = new MemberManagerImpl();
            System.out.println("MemberManager - Instance has been created!");
        }
        return memberManagerConnectionInstance;
    }

    // GET 
    // List members
    public synchronized String getMembers() {
        String currentMembers = members.toString();
        if (members.isEmpty()) {
            return ("No members to display");
        }
        return currentMembers;

    }

    // GET
    public synchronized String getMemberInfo(int memberID){
        // Return: memberID, String name, String contact
        if (!members.containsKey(memberID)) {
            return "Member doesn't exist";
        }
        
        Member member = members.get(memberID);
        String memberInfo = member.toString();
        return memberInfo;

    }

    // POST
    public synchronized void addMember(String memberName, String memberContact) throws LoanException {
        int memberID = memberMapKey.incrementAndGet();
        Member member = new Member(memberName, memberContact);
        member.setMemberID(memberID);
        members.put(memberID, member);

        if (!members.containsKey(memberID)) {
            throw new LoanException("Member Manager - Error in adding a member!");
        }
    }

    // UPDATE
    public synchronized void updateMember(int memberID, String memberName, String memberContact) throws LoanException {

        if (!members.containsKey(memberID)) {
            throw new LoanException("Member Manager - Member cannot be found. Cannot update!");
        } else {
            Member member = new Member(memberName, memberContact);
            member.setMemberID(memberID);
            members.put(memberID, member);
            if (!members.containsKey(memberID)) {
                throw new LoanException("Member Manager - Was unable to update the member!");
            }
        }
    }

    // DELETE
    public synchronized void deleteMember(int memberID) throws LoanException {
        if (!members.containsKey(memberID)) {
            throw new LoanException("Member Manager - Member cannot be found. Cannot delete!");
        } else {
            members.remove(memberID);
        }

    }

    // Database Version --> Implement after getting main memory version to work //
    /*
    // SINGLETON Pattern
    private static MemberManagerImpl memberManagerConnectionInstance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    private MemberManagerImpl() throws LoanException, FileNotFoundException, IOException {
        // 1. Get a connection to database
        try {
            InputStream input = new FileInputStream("loanSystemConfig.properties");
            Properties prop = new Properties();

            // Load Properties
            prop.load(input);

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoanException("MemberManagerImpl - Error in connecting to database");

        }
    }
    
    public Connection getConnectionInstance() {
        return connection;
    }

    // SINGLETON    
    public static MemberManagerImpl getInstance() throws LoanException, IOException {
        if (memberManagerConnectionInstance == null) {
            memberManagerConnectionInstance = new MemberManagerImpl();
            System.out.println("MemberManagerImpl - Instance has been created!");
        }
        return memberManagerConnectionInstance;
    }

    public Statement createStatement() throws LoanException {
        try {
            // 2. Create a statement
            statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new LoanException("MemberManagerImpl - Error in creating query!");
        }
        return statement;
    }
    
    public ResultSet executeQuery(String query) throws LoanException {
        try {
            createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            throw new LoanException("MemberManagerImpl - Error in executing query!");
        }
        return resultSet;
    }

    public int executeUpdate(String query) throws LoanException {
        int update = 0;
        try {
            createStatement();
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new LoanException("MemberManagerImpl - Error in executing update query!");
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
            throw new LoanException("MemberManagerImpl - Error in cleanup!");
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
            throw new LoanException("MemberManagerImpl - Error in creating member table!");
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
