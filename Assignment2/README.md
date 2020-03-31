# Assignment 2
```
Jasmine Latendresse (#40011419)
Airi Chow (#40003396)
```

### Project Description:
- The project's goal is to implement a RESTful Web Service for a library system and a SOAP Web Service for a loan system using a layered architecture.

## Library REST Service
- Library Core: Contains "Book" class which is used by the other classes.
- Library System: Uses the Library Core and stores the Book objects. The basic CRUD operations are implemented here.
- Library Service: A Web App that implements REST methods for the CRUD operations from Library System. Annotations are used to call the appropriate methods(e.g. @GET).
- Client: A Web App that calls a REST Client that will use the Library Service via WADL.

## Loan SOAP Service
- Loan Core: Contains "Member" and "Loan" class which is used by the "Loan" classes.
- Loan System: Uses the Loan Core, Library Core and Library System to store members and loan-related information. The basic CRUD operations are implemented for LoanManager and MemberManager. The "Book" is retrieved using the callNumber hashmap where the keys are callNumbers and the values are Books. In the case of exceptions, LoanException will be thrown.
- Loan Service: A Web App that implements a RPC-Style SOAP service. The Loan Service uses Loan System and implements two additional methods on top of this system. Exceptions are caught and thrown as "SOAP Faults". When this layer is run, the WSDL files are generated for interfaces.
- Client: A Web App that auto-generates the SOAP clients to use the Loan Service via WSDL files.

### Tasks
- Jasmine: Worked on Library REST Service
- Airi: Worked on Loan SOAP Service

### Notes on Project Setup and Libraries
All Projects: JDK 1.8
- Library Core: Serializable and Javax(XmlRootElement)
- Library System: LibraryCore.jar
- Library Service: JAX-RS 2.0, Jersey 2.5.1(JAX-RS-RI), LibrarySystem.jar, LibraryCore.jar, EclipseLink, Eclipse.Persistency, Jackson, Tomcat

- Loan Core: Serializable and Javax(XmlRootElement)
- Loan System: LoanCore.jar, LibraryCore.jar, LibrarySystem.jar
- Loan Service: Metro 2.0, JAX-WS 2.2.6, LoanCore.jar, LoanSystem.jar, LibraryCore.jar, LibrarySystem.jar, Tomcat

- Client: Metro 2.0, JAX-WS 2.2.6, JAX-RS 2.0, Jersey 2.5.1(JAX-RS-RI), LoanCore.jar, LoanSystem.jar, LibrarySystem.jar, LibraryCore.jar, JSON-Simple, JSTL 1.2.1, Tomcat


### Running the application:
1. Start server
2. Run the library service layer
3. Run the loan service layer
4. Run the client

- **Persistency was not implemented, everything runs in memory**
- **If things fail, clean build Library Service then re-run Library Client's console.**
- **If SOAP clients are not building, make sure that the WSDL files have been generated. Clean and build: Loan Service and click on the WSDL hyperlinks on the JSP page**
