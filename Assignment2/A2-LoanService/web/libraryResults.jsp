<%-- 
    Document   : libraryResults
    Created on : 24 mars 2020, 09:26:51
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Results</title>
    </head>
    <body>
        <h3>Library System Message:</h3>
        ${message}        
        <h3>Current Book Results:</h3>
        ${results}
        <h3>Call Number Results:</h3>
        ${callNumberResults}
        <p><a href="./index.jsp">Back to homepage</a></p>
    </body>
</html>
