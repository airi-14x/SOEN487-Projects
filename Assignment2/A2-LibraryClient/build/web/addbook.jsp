<%-- 
    Document   : addbook
    Created on : 2020-03-22, 17:10:41
    Author     : jasminelatendresse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add book</title>
    </head>
    <body>
        <h1>Add a book - Parameterized Argument</h1>
        <form action="AddBook" method="POST">
            <label for="title">Title</label>
            <input name="title" required />
            <br/>
            Description:<br/>
            <TEXTAREA NAME="description" COLS=40 ROWS=6 required></TEXTAREA>
            <br/>
            <label for="isbn">ISBN</label>
            <input name="isbn" required />
            <br/>
            <label for="Author">Author</label>
            <input name="author" required/>
            <br/>
            <label for="publisher">Publisher</label>
            <input name="publisher" required/>
            <br/>
            <label for="callNumber">Call Number</label>
            <input name="callNumber" required/> 
            <br/>
            <input type="submit" value="Submit" />
        </form> 
        <hr/>
        <h1>Add a book - Complex Data Type</h1>
        <form action="AddBookXml" method="POST">
            <label for="title">Title</label>
            <input name="title" required />
            <br/>
            Description:<br/>
            <TEXTAREA NAME="description" COLS=40 ROWS=6 required></TEXTAREA>
            <br/>
            <label for="isbn">ISBN</label>
            <input name="isbn" required />
            <br/>
            <label for="Author">Author</label>
            <input name="author" required/>
            <br/>
            <label for="publisher">Publisher</label>
            <input name="publisher" required/>
            <br/>
            <label for="callNumber">Call Number</label>
            <input name="callNumber" required/> 
            <br/>
            <input type="submit" value="Submit" />
        </form> 
    </body>
</html>
