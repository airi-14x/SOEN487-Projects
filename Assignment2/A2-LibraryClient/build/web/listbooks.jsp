<%-- 
    Document   : listbooks
    Created on : 2020-03-22, 12:27:37
    Author     : jasminelatendresse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Books Plain</title>
    </head>
    <body>
        <h1>List of current books in text/plain format</h1>
        ${books}
        <ul>
            <li><a href="ListBooksJson">List books - JSON</a></li>
        </ul>
    </body>
</html>
