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
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
        
        
    <%-- start web service invocation --%><hr/>
    <%
    try {
	helloWorldClient.HelloWorld_Service service = new helloWorldClient.HelloWorld_Service();
	helloWorldClient.HelloWorld port = service.getHelloWorldPort();
	 // TODO initialize WS operation arguments here
	java.lang.String name = "";
	// TODO process result here
	java.lang.String result = port.hello(name);
	out.println("Result = "+result);
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
	out.println("Result = "+result);
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
	 // TODO initialize WS operation arguments here
	java.lang.String arg0 = "hello";
	java.lang.String arg1 = "dskj";
	port.addMember(arg0, arg1);
        java.lang.String result = port.getMembers();
	out.println("Result = "+result);
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
	 // TODO initialize WS operation arguments here
	int arg0 = 1;
	// TODO process result here
	java.lang.String result = port.getMemberInfo(arg0);
	out.println("Result = "+result);
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
	 // TODO initialize WS operation arguments here
	int arg0 = 2;
	java.lang.String arg1 = "NewAiri";
	java.lang.String arg2 = "124@gmail.com";
	port.updateMember(arg0, arg1, arg2);
        java.lang.String result = port.getMembers();
	out.println("Result = "+result);
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
	 // TODO initialize WS operation arguments here
	int arg0 = 1;
	port.deleteMember(arg0);
        java.lang.String result = port.getMembers();
	out.println("Result = "+result);
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
                Member Name: <input type="text" name="memberName"><br>
                Member Contact Info: <input type="text" name="memberContact"><br>
                <input type="submit" name="members" value="addMember" />
    </form>
    
    </body>  
    
</html>
