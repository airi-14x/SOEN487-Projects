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
- Client: A web app that calls a REST Client that will use the Library Service via WADL.

## Loan SOAP Service 

### Tasks
- Jasmine: Worked on Library REST Service
- Airi: Worked on Loan SOAP Service

### Notes on Project Setup and Libraries
All Projects: JDK 1.8
- Library Core: Serializable and Javax(XmlRootElement)
- Library System: LibraryCore.jar
- Library Service: JAX-RS 2.0, Jersey 2.5.1(JAX-RS-RI), LibrarySystem.jar, LibraryCore.jar, EclipseLink, Eclipse.Persistency, Jackson, Tomcat

- Loan Core:
- Loan System:
- Loan Service:

- Client: JAX-RS 2.0, Jersey 2.5.1(JAX-RS-RI), LibraryCore.jar, JSON-Simple, Tomcat


### Running the application: 
1. Start server 
2. Run the library service layer
3. Run the loan service layer
4. Run the client

**Persistency was not implemented, everything runs in memory***
** If things fail, clean build Library Service then re-run Library Client's console.**
