<%-- 
    Document   : librarySystem
    Created on : 24 mars 2020, 10:49:47
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library System JSP</title>
    </head>
    <body>
        <h4>Link to: <a href="./index.jsp">Loan System</a></h4>
        
        <h2>Library System</h2>
        <h3> Display Books in Library </h3>
        <form action="LibrarySystemController" method="GET">
            View All Books: <input type="submit" name="library" value="displayAll"/><br>
        </form>

        <form action="LibrarySystemController" method="GET">
            View Book with ID:<input type="text" name="viewBookID">
            <input type="submit" name="library" value="displayBook"/><br>
        </form>

        <h3>Add Book to Library</h3>
        <form action="LibrarySystemController" method="POST">
            Title <input type="text" name="title"><br>
            Description <input type="text" name="description"><br>
            ISBN <input type="text" name="isbn"><br>
            Author <input type="text" name="author"><br>
            Publisher <input type="text" name="publisher"><br>
            Call Number <input type="text" name="callNumber"><br>
            <input type="submit" name="library" value="addBook"/>
        </form>

        <h3>Update Book in Library </h3>
        <form action="LibrarySystemController" method="POST">
            Book ID <input type="text" name="updateBookID"><br>
            Title <input type="text" name="title"><br>
            Description <input type="text" name="description"><br>
            ISBN <input type="text" name="isbn"><br>
            Author <input type="text" name="author"><br>
            Publisher <input type="text" name="publisher"><br>
            Call Number <input type="text" name="callNumber"><br>
            <input type="submit" name="library" value="updateBook"/>
        </form>

        <h3>Delete a Book</h3>
        <form action="LibrarySystemController" method="GET">
            Delete book with BookID:<input type="text" name="deleteBookID">
            <input type="submit" name="library" value="deleteBook"/><br>
        </form>
    </body>
</html>
