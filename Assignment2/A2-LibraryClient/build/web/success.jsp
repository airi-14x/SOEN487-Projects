<%-- 
    Document   : success
    Created on : 2020-03-22, 17:47:54
    Author     : jasminelatendresse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
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
        <p>${resMessage}</p>
        <br/>
        <p>${successMessage}</p>
        <br/>
        <a href="index.jsp">Back to the main page</a>
    </body>
</html>
