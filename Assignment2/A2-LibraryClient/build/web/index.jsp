<%-- 
    Document   : index
    Created on : 2020-03-27, 13:37:50
    Author     : jasminelatendresse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <div>
            <h1>Welcome to RESTful Library Service.</h1>
            <ul>
                <li><a href="ListBooks">View all books</a></li>
                <li><a href="addbook.jsp">Add a book</a></li>
                <li><a href="updatebook.jsp">Update a book</a></li>
                <li><a href="Logout">Logout</a></li>
            </ul>
            <hr/>
            <h3>View book</h3>
            <form action="ViewBook" method="POST">
                <label for="id">Book id</label>
                <input name="id" required />
                <input type="submit" value="Submit" />
            </form>
            <h3>Delete a book</h3>
            <form action="DeleteBook" method="POST">
                <label for="id">Book id</label>
                <input name="id" required />
                <input type="submit" value="Submit" />
            </form>
        </div>
    </body>
</html>
