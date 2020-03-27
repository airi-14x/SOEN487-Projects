<%-- 
    Document   : listbooks
    Created on : 2020-03-22, 12:27:37
    Author     : jasminelatendresse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Books Plain</title>
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
        <h1>List of current books</h1>
        <h3>Text/plain format</h3>
        ${books}
        <br/>
        <h3>JSON format</h3>
        ${booksJson}
        <br/>
        <h3>XML format</h3>
        <c:out value="${booksXml}"/>
        <br/>
        <h3>HTML format</h3>
        ${booksHtml}
        <br/>
        <a href="index.jsp">Back to the main page</a>
    </body>
</html>
