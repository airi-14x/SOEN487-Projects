<%-- 
    Document   : loanResults
    Created on : 25 mars 2020, 10:35:19
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loan Results</title>
    </head>
    <%
        try {
            if (session != null) {
                if(!session.getAttribute("username").equals("jasmine"))
                response.sendRedirect("login.jsp");
            }
        }
        catch(Exception e){
            response.sendRedirect("login.jsp");
        }
     
    %>
    <body>
        <h3>Loan System Message:</h3>
        ${message}
        <h3>Current Loan Results:</h3>
        ${results}
        <h4><a href="./loans_index.jsp">Back to LoanService homepage</a></h4>
        <h4><a href="./librarySystem.jsp">Back to Library System</a></h4>
    </body>
</html>
