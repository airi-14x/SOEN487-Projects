# Assignment 1
```
Jasmine Latendresse (#40011419)
Airi Chow (#40003396)
```

### Project Description:
- The project's goal is to implement a RESTful Web Service for a library system
  using 4 separate projects.
- Library Core: Contains "Book" class which is used by the other classes.
- Library System: Uses the Library Core and stores the Book objects. The basic CRUD operations are implemented here.
- Library Service: A Web App that implements REST methods for the CRUD operations from Library System. Annotations are used to call the appropriate methods(e.g. @GET)
- Library Client: A console app that calls a REST Client that will use the Library Service via WADL.

### Tasks
- Jasmine: Mostly worked on the Library Client.
- Airi: Mostly worked on Library System and Library Service.
- But, we mostly ended up working on all the parts together because there were bugs to be fixed here and there.

### Notes on Project Setup and Libraries
All Projects: JDK 1.8
- Library Core: Serializable and Javax(XmlRootElement)
- Library System: LibraryCore.jar
- Library Service: JAX-RS 2.0, Jersey 2.5.1(JAX-RS-RI), LibrarySystem.jar, LibraryCore.jar, Tomcat
- Library Client: JAX-RS 2.0, Jersey 2.5.1(JAX-RS-RI), LibraryCore.jar, Tomcat

** If things fail, clean build Library Service then re-run Library Client's console.** 
