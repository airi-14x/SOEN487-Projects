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
	java.lang.String name = "TestingHelloWorld";
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
    
    </body>
</html>
