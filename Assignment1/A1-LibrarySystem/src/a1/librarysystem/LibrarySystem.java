/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.librarysystem;

import a1.librarycore.Book;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Airi
 */
public class LibrarySystem {
    private static ConcurrentHashMap<Integer, Book> books = new ConcurrentHashMap<Integer, Book>();
    private static AtomicInteger bookMapKey = new AtomicInteger();
    private static Book book;
    
    // Cannot be private
    public LibrarySystem(){
        System.out.println("Created an instance of LibrarySystem");
    }

    
    public ConcurrentHashMap<Integer, Book> getMap() {
        return books;
    }
    
    public synchronized String displayBooks(){
       String currentBooks = books.toString();
       if(books.isEmpty()) {
           return("No books to display");
       }
       return currentBooks;
    }
    
    /*
    public synchronized void setCurrentBook(Book book){
        this.book = book;
    }
    
    public synchronized String currentBook(){
        return book.toString();
    }*/

    //POST
    public synchronized void addBook(String title, String description, String isbn, String author, String publisher) throws Exception {
        Book book = new Book(title, description, isbn, author, publisher);
        int bookID = bookMapKey.incrementAndGet();
        book.setId(bookID);
        books.put(bookID, book);
        if (!books.containsKey(bookID)){
            throw new Exception("Book was not created!");       
        }
        //String bookInfo = book.toString();
        //return "You just created this book: " + bookInfo;
    }

    //GET
    public synchronized String getBook(int id){
        if (!books.containsKey(id)){
            return "Book doesn't exist";
        }
        else{
            Book currentBook = books.get(id);
            //setCurrentBook(currentBook);
            //System.out.println(currentBook.toString());
            String bookInfo = currentBook.toString();
            return bookInfo;
        }

    }

    //PUT
    public synchronized void updateBook(int id, String title, String description, String isbn, String author, String publisher) throws Exception {
        Book book = new Book(title, description, isbn, author, publisher);
        book.setId(id);
        if (!books.containsKey(id)) {
            throw new Exception("Book cannot be updated!");          
        } else {
            books.put(id, book); // Replace with current book object
        }
    }

    //DELETE
    public synchronized void removeBook(int id) throws Exception {
        if (!books.containsKey(id)) {
            throw new Exception("Book cannot be removed!");
        } else {
            books.remove(id);
            
        }
    }

    @Override
    public String toString() {
        return "LibrarySystem{" + "books=" + books + ", bookMapKey=" + bookMapKey + '}';
    }
    
}
