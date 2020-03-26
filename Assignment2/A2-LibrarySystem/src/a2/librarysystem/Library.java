/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.librarysystem;

import a2.librarycore.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * @author Airi
 */
public class Library {

    private static ConcurrentHashMap<Integer, Book> books = new ConcurrentHashMap<Integer, Book>();
    private static ConcurrentHashMap<String, Book> callNumbers = new ConcurrentHashMap<String, Book>();
    private static Library libraryConnectionInstance;
    private static AtomicInteger bookMapKey = new AtomicInteger();

    // Cannot be private
    public Library() {
        System.out.println("Created an instance of Library");
    }

    // SINGLETON    
    public static Library getInstance() throws LibraryException, IOException {
        if (libraryConnectionInstance == null) {
            libraryConnectionInstance = new Library();
            System.out.println("Library - Instance has been created!");
        }
        return libraryConnectionInstance;
    }

    public ConcurrentHashMap<Integer, Book> getBooksMap() {
        return books;
    }

    public ConcurrentHashMap<String, Book> getCallNumbersMap() {
        return callNumbers;
    }

    // GET
    public synchronized String displayBooks() {
        String currentBooks = books.toString();
        if (books.isEmpty()) {
            return ("No books to display");
        }
        return currentBooks;
    }

    //GET
    public synchronized String getBook(int id) {
        if (!books.containsKey(id)) {
            return "Book doesn't exist";
        } else {
            Book currentBook = books.get(id);
            String bookInfo = currentBook.toString();
            return bookInfo;
        }
    }

    //GET
    public synchronized Book getBookById(int id) throws LibraryException {
        if (!books.containsKey(id)) {
            throw new LibraryException("Book does not exist.");
        } else {
            return books.get(id);
        }
    }

    //POST
    public synchronized void addBook(String title, String description, String isbn, String author, String publisher, String callNumber) throws LibraryException {
        Book book = new Book(title, description, isbn, author, publisher, callNumber);
        // Check if callNumber exists

        if (callNumbers.containsKey(callNumber)) {
            throw new LibraryException("Library - Error duplicate call number. Book cannot be created!");
        } else {
            int bookID = bookMapKey.incrementAndGet();
            book.setId(bookID);
            books.put(bookID, book);
            if (!books.containsKey(bookID)) {
                throw new LibraryException("Library - Error in adding a book!");
            } else {
                callNumbers.put(callNumber, book); // Add to ConcurrentHashMap
            }
        }

    }

    //POST
    public synchronized void addBookComplex(Book bookObject) throws LibraryException {
        Book book = new Book();
        String title = bookObject.getTitle();
        String description = bookObject.getDescription();
        String isbn = bookObject.getIsbn();
        String author = bookObject.getAuthor();
        String publisher = bookObject.getPublisher();
        String callNumber = bookObject.getCallNumber();

        // Check if callNumber exists
        if (callNumbers.containsKey(callNumber)) {
            throw new LibraryException("Library - Error duplicate call number. Book cannot be created!");
        } else {
            int bookID = bookMapKey.incrementAndGet();
            book.setId(bookID);
            book.setTitle(title);
            book.setDescription(description);
            book.setIsbn(isbn);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setCallNumber(callNumber);
            books.put(bookID, book);
            if (!books.containsKey(bookID)) {
                throw new LibraryException("Library - Error in adding a book!");
            } else {
                callNumbers.put(callNumber, book); // Add to ConcurrentHashMap
            }
        }

    }

    //PUT
    public synchronized void updateBook(int id, String title, String description, String isbn, String author, String publisher, String callNumber) throws LibraryException {
        Book book = books.get(id);
        if(book == null) {
            throw new LibraryException("No book with id: " + id);
        }
        if (callNumbers.containsKey(callNumber)) {
            throw new LibraryException("Library - Error duplicate call number. Book cannot be updated!");
        }
        String previousCallNumber = book.getCallNumber();
        callNumbers.remove(previousCallNumber);
        callNumbers.put(callNumber, book);
        book.setTitle(title);
        book.setDescription(description);
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCallNumber(callNumber);
    }

    //PUT
    public synchronized void updateBookComplex(int id, Book bookObject) throws LibraryException {
        
        String title = bookObject.getTitle();
        String description = bookObject.getDescription();
        String isbn = bookObject.getIsbn();
        String author = bookObject.getAuthor();
        String publisher = bookObject.getPublisher();
        String callNumber = bookObject.getCallNumber();
        
        Book book = books.get(id);
        if(book == null) {
            throw new LibraryException("No book with id: " + id);
        }
        if (callNumbers.containsKey(callNumber)) {
            throw new LibraryException("Library - Error duplicate call number. Book cannot be updated!");
        }
        
        String previousCallNumber = book.getCallNumber();
        callNumbers.remove(previousCallNumber);
        callNumbers.put(callNumber, book);
        
        book.setTitle(title);
        book.setDescription(description);
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCallNumber(callNumber);
    }

    //DELETE
    public synchronized void removeBook(int id) throws LibraryException {
        if (!books.containsKey(id)) {
            throw new LibraryException("Library - Book cannot be removed!");
        } else {
            String callNumber = books.get(id).getCallNumber();
            System.out.println(callNumber);
            callNumbers.remove(callNumber);
            books.remove(id);
        }

    }

    public String booksToHtml() {
        List<String> bookList = new ArrayList(books.values());
        StringBuilder output = new StringBuilder();
        output.append("<html><body><table style={border: 1px solid black}>");
        for (Iterator iter = bookList.iterator(); iter.hasNext();) {
            output.append("<tr><td>" + iter.next() + "</td></tr>");
        }
        output.append("</table></body></html>");
        return output.toString();
    }

    public String bookToHtml(Book book) {
        return "<html> " + "<title>" + "Book Html" + "</title>"
                + "<body><h1>" + book.toString() + "</body></h1>" + "</html> ";
    }

}
