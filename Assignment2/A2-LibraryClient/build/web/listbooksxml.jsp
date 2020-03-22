<%-- 
    Document   : listbooksxml
    Created on : 2020-03-22, 15:57:50
    Author     : jasminelatendresse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>List books XML</title>
    </head>
    <body>
        <h1>List of current books in XML format</h1>
        <pre>
            <c:out value="${books}"/>
        </pre>
    <li><a href="ListBooks">List books - PlAIN-TEXT</a></li>
    <li><a href="ListBooksJson">List books - JSON</a></li>
    <li><a href="ListBooksHtml">List books - HTML</a></li>
</body>
</html>