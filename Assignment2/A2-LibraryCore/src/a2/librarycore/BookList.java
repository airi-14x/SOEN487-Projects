/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.librarycore;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author jasminelatendresse
 */
@XmlRootElement(name = "bookList")
@XmlSeeAlso(Book.class)
public class BookList {

    private List<Book> book;

    public List<Book> getList() {
        return book;
    }

    public void setList(List<Book> book) {
        this.book = book;
    }

}