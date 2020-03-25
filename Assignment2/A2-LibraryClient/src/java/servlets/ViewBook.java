/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import libraryclient.LibraryClient;

/**
 *
 * @author jasminelatendresse
 */
public class ViewBook extends HttpServlet {


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        LibraryClient client = new LibraryClient();
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            String book = client.getBookPlain(String.class, id);
            String bookJson = client.getBookJson(String.class, id);
            String bookXml = client.getBookPlain(String.class, id);
            String bookHtml = client.getBookPlain(String.class, id);
            
            request.setAttribute("book", book);
            request.setAttribute("bookJson", bookJson);
            request.setAttribute("bookXml", bookXml);
            request.setAttribute("bookHtml", bookHtml);
            
            request.getRequestDispatcher("viewbook.jsp").forward(request, response);
            
        }
        catch(Exception e){
            request.setAttribute("message", "Book could not be found.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


}
