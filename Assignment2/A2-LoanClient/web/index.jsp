<%-- 
    Document   : index
    Created on : 15 mars 2020, 10:50:10
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loan Client JSP Page</title>
    </head>
    <body>    

        <%-- start web service invocation --%><hr/>
        <%
            try {
                helloWorldClient.HelloWorld_Service service = new helloWorldClient.HelloWorld_Service();
                helloWorldClient.HelloWorld port = service.getHelloWorldPort();
                // TODO initialize WS operation arguments here
                java.lang.String name = "";
                // TODO process result here
                java.lang.String result = port.hello(name);
                out.println("Result = " + result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <%-- end web service invocation --%><hr/>

        <%-- start web service invocation --%><hr/>
        <%
            try {
                a2.loanservice.client.LoanServiceMemberManagerImplService service = new a2.loanservice.client.LoanServiceMemberManagerImplService();
                a2.loanservice.client.LoanServiceMemberManager port = service.getLoanServiceMemberManagerImplPort();
                // TODO process result here
                java.lang.String result = port.getMembers();
                out.println("Result = " + result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <%-- end web service invocation --%><hr/>

        <form action="MemberManagerController" method="GET">
            View All Members: <input type="submit" name="members" value="displayAll"/><br>
        </form>

        <form action="MemberManagerController" method="GET">
            View Member with ID:<input type="text" name="viewMemberID">
            <input type="submit" name="members" value="displayMember"/><br>
        </form>

        <h3>Add a Member</h3>
        <form action="MemberManagerController" method="POST">
            Member Name: <input type="text" name="addMemberName"><br>
            Member Contact Info: <input type="text" name="addMemberContact"><br>
            <input type="submit" name="members" value="addMember" />
        </form>

        <h3>Update a Member</h3>
        <form action="MemberManagerController" method="POST">
            Member ID: <input type="text" name="updateMemberID"><br>
            Member Name: <input type="text" name="updateMemberName"><br>
            Member Contact Info: <input type="text" name="updateMemberContact"><br>
            <input type="submit" name="members" value="updateMember" />
        </form>

        <h3>Delete a Member</h3>
        <form action="MemberManagerController" method="GET">
            Delete member with memberID:<input type="text" name="deleteMemberID">
            <input type="submit" name="members" value="deleteMember"/><br>

        </form>

    </body>  

</html>
