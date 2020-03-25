<%-- 
    Document   : viewbook
    Created on : 2020-03-25, 17:02:16
    Author     : jasminelatendresse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View requested book</title>
    </head>
    <body>
        <h1>View requested book</h1>
        <h2>Plain text format</h2>
        ${book}
        <h2>JSON format</h2>
        ${bookJson}
        <h2>XML format</h2>
        <c:out value="${bookXml}"/>
        <h2>HTML format</h2>
        ${bookHtml}
    </body>
</html>
