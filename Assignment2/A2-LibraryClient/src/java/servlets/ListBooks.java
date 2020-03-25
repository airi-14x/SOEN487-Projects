/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import libraryclient.LibraryClient;

/**
 *
 * @author jasminelatendresse
 */
@WebServlet(name = "ListBooks", urlPatterns = {"/ListBooks"})
public class ListBooks extends HttpServlet {

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        LibraryClient client = new LibraryClient();

        String books = client.listBooksPlain(String.class);
        request.setAttribute("books", books);

        if (books.equals("No books to display")) {
            request.setAttribute("bookJson", " ");
            request.setAttribute("booksXml", " ");
            request.setAttribute("booksHtml", " ");
        } else {
            String booksJson = client.listBooksJson(String.class);
            request.setAttribute("booksJson", booksJson);

            String booksXml = client.listBooksXml(String.class);
            request.setAttribute("booksXml", booksXml);

            String booksHtml = client.listBooksHtml(String.class);
            request.setAttribute("booksHtml", booksHtml);
        }

        request.getRequestDispatcher("listbooks.jsp").forward(request, response);
    }

}
