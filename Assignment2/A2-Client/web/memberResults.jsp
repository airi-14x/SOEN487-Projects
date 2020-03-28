<%-- 
    Document   : results
    Created on : 23 mars 2020, 13:08:56
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Results</title>
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
        <h3>Member Manager Message:</h3>
        ${results}
        <p><a href="./loans_index.jsp">Back to LoanService homepage</a></p>
    </body>
</html>
