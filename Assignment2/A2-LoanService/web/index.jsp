<%-- 
    Document   : index
    Created on : 15 mars 2020, 10:50:10
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loan Service JSP</title>
    </head>
    <body>
        <h3>Generating LoanService WSDL file</h3>
        <p>Check if this exists:</p>
        <p><a href="http://localhost:8080/A2-LoanService/LoanServiceMemberManagerImpl?wsdl">MemberManager WSDL</a></p>

        <h4>Link to: <a href="./librarySystem.jsp">Library System</a></h4>
        
        <h2>Member Manager</h2>
        <h3>Check Member Information</h3>
        <form action="MemberManagerController" method="GET">
            View All Members: <input type="submit" name="members" value="displayAll"/><br>
        </form>

        <form action="MemberManagerController" method="GET">
            View Member with ID:<input type="text" name="viewMemberID">
            <input type="submit" name="members" value="displayMember"/><br>
        </form>

        <h3>Add a Member</h3>
        <form action="MemberManagerController" method="POST">
            Member Name: <input type="text" name="addMemberName"><br>
            Member Contact Info: <input type="text" name="addMemberContact"><br>
            <input type="submit" name="members" value="addMember" />
        </form>

        <h3>Update a Member</h3>
        <form action="MemberManagerController" method="POST">
            Member ID: <input type="text" name="updateMemberID"><br>
            Member Name: <input type="text" name="updateMemberName"><br>
            Member Contact Info: <input type="text" name="updateMemberContact"><br>
            <input type="submit" name="members" value="updateMember" />
        </form>

        <h3>Delete a Member</h3>
        <form action="MemberManagerController" method="GET">
            Delete member with memberID:<input type="text" name="deleteMemberID">
            <input type="submit" name="members" value="deleteMember"/><br>
        </form>
        
        <h2>Loan Manager</h2>
        <h3>Borrow Book</h3>
        
        <h3>Edit Book Loan</h3>
        
        <h3>Return Book</h3>
        
        <h3>Delete Book Loan</h3>
        
        <h3>List Loan with memberID:</h3>
        
        <h3>List Loan with Book Title:</h3>
    </body>  

</html>
